package com.language.model.expression;

public class BitwiseExpression extends Expression {
	/* PRECEDENCE:	
	    - NOT_BIT
	    - LSHIFT, RSHIFT
	    - AND_BIT
	    - XOR_BIT
	 	- OR_BIT	 		 	 
	*/
	public static final String OR_BIT = "OrBit";
	public static final String AND_BIT = "AndBit";
	public static final String XOR_BIT = "XorBit";
	public static final String LSHIFT = "LShift";
	public static final String RSHIFT = "RShift";
	public static final String NOT_BIT = "NotBit";
	
	public BitwiseExpression() {
		super();
	}
	
	public BitwiseExpression(String type, Object value, Expression left, Expression right) {
		super(type,value,left,right);
	}
	
	public BitwiseExpression(String type, Expression left, Expression right) {
		super(type, left, right);
	}
	
	public static Expression create(String type, Object value, Expression left, Expression right) {
		return new BitwiseExpression(type, value, left, right);
	}
	
	public static Expression create(String type, Object value, Expression left) {
		return new BitwiseExpression(type, value, left, null);
	}
	
	@Override
	public Object getValue() throws Exception {
		Object returnObject = null;
		Expression left 	= this.getLeft();
		Expression right 	= this.getRight();
		
		if(left != null || right != null)
		{
			returnObject = BitwiseExpression.operation(this.getType(),left,right);
		}
		else
		{
			returnObject = super.getValue();
		}
		
		return returnObject;
	}
	
	public static Object operation(String type,Expression l, Expression r) throws Exception {
		Object 	obj = null,
				leftValue = l,
				rightValue = r;
		
		String 	leftType = "",
				rightType = "";
		
		if (leftValue != null) {
			leftValue = l.getValue();
			leftType = leftValue.getClass().getSimpleName();
		}
		
		if (rightValue != null) {
			rightValue = r.getValue();
			rightType = rightValue.getClass().getSimpleName();
		}
		
		if ((leftValue == null || leftType.equals("Integer")) && (rightValue == null || rightType.equals("Integer"))) {
			switch (type)
			{
				case BitwiseExpression.OR_BIT:
				{
					obj = (int)leftValue | (int)rightValue;
					break;
				}
				case BitwiseExpression.AND_BIT:
				{
					obj = (int)leftValue & (int)rightValue;
					break;
				}
				case BitwiseExpression.XOR_BIT:
				{
					obj = (int)leftValue ^ (int)rightValue;
					break;
				}
				case BitwiseExpression.LSHIFT:
				{
					obj = (int)leftValue << (int)rightValue;
					break;
				}
				case BitwiseExpression.RSHIFT:
				{
					obj = (int)leftValue >> (int)rightValue;
					break;
				}
				case BitwiseExpression.NOT_BIT:
				{
					obj = ~((int)leftValue);
					break;
				}
				default:
				{
					throw new Exception("Operador bit no reconocido");
				}
			}
		}
		else {
			throw new Exception("Los operandos no tienen el tipo correcto");
		}
		
		return obj;
	}
}
