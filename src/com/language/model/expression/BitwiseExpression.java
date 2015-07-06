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
		Object left 		= this.getLeft();
		Object right 		= this.getRight();
		
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
	
	public static Object operation(String type,Object l, Object r) throws Exception {
		int obj;
		switch(type)
		{
			case BitwiseExpression.OR_BIT:
			{
				obj = (int)l | (int)r;
				break;
			}
			case BitwiseExpression.AND_BIT:
			{
				obj = (int)l & (int)r;
				break;
			}
			case BitwiseExpression.XOR_BIT:
			{
				obj = (int)l ^ (int)r;
				break;
			}
			case BitwiseExpression.LSHIFT:
			{
				obj = (int)l << (int)r;
				break;
			}
			case BitwiseExpression.RSHIFT:
			{
				obj = (int)l >> (int)r;
				break;
			}
			case BitwiseExpression.NOT_BIT:
			{
				obj = ~((int)l);
				break;
			}
			default:
			{
				throw new Exception("Unrecognized bit operator");
			}
		}
		
		return obj;
	}
}
