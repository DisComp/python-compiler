package com.language.model.expression;

public class BooleanExpression extends Expression {
	/* PRECEDENCE:
	 	- EQUALS, DISTINCT, LESS, GREATER, LESS_EQUAL, GREATER_EQUAL
	    - NOT
	    - AND
	 	- OR	 		 	 
	*/
	public static final String EQUALS = "Equals";
	public static final String DISTINCT = "Distinct";
	public static final String LESS = "Less";
	public static final String GREATER = "Greater";
	public static final String LESS_EQUAL = "LessEqual";
	public static final String GREATER_EQUAL = "GreaterEqual";
	public static final String NOT = "Not";
	public static final String AND = "And";
	public static final String OR = "Or";
	
	public BooleanExpression() {
		super();
	}
	
	public BooleanExpression(String type, Object value, Expression left, Expression right) {
		super(type,value,left,right);
	}
	
	public BooleanExpression(String type, Expression left, Expression right) {
		super(type, left, right);
	}
	
	public static Expression create(String type, Object value, Expression left, Expression right) {
		return new BooleanExpression(type, value, left, right);
	}
	
	public static Expression create(String type, Object value, Expression left) {
		return new BooleanExpression(type, value, left, null);
	}
	
	@Override
	public Object execute() throws Exception {
		Object returnObject = null;
		Expression left 		= this.getLeft();
		Expression right 		= this.getRight();
		
		if(left != null || right != null)
		{
			returnObject = BooleanExpression.operation(this.getType(),left,right);
		}
		else
		{
			returnObject = super.getValue();
		}
		
		return returnObject;
	}
	
	public static Object operation(String type,Expression l,Expression r) throws Exception{
		Object 	obj = null,
				leftValue = l,
				rightValue = r;
		
		String 	leftType = "",
				rightType = "";
		
		if (leftValue != null) {
			leftValue = l.execute();
			leftType = leftValue.getClass().getSimpleName();
		}
		
		if (rightValue != null) {
			rightValue = r.execute();
			rightType = rightValue.getClass().getSimpleName();
		}
		
		if((leftValue == null || leftType.equals("Boolean")) && ( rightValue == null || rightType.equals("Boolean"))) 	{
			
			switch(type)
			{
				case BooleanExpression.NOT:
				{				
					obj = !(boolean)leftValue;
					break;
				}
				case BooleanExpression.AND:
				{
					obj = (boolean)leftValue && (boolean)rightValue;
					break;
				}
				case BooleanExpression.OR:
				{
					obj = (boolean)leftValue || (boolean)rightValue;
					break;
				}
				case BooleanExpression.EQUALS:
				{
					obj = (boolean)leftValue == (boolean)rightValue;
					break;
				}
				case BooleanExpression.DISTINCT:
				{
					obj = (boolean)leftValue != (boolean)rightValue;
					break;
				}
				default:
				{
					throw new Exception("Unrecognized boolean operator");
				}
			}
		}
		else {
			
			switch(type)
			{
				case BooleanExpression.EQUALS:
				{
					if(leftType.equals("String")) {
						obj = ((String)leftValue).equals(String.valueOf(rightValue));
					}
					else if (rightType.equals("String")) {
						obj = ((String)rightValue).equals(String.valueOf(leftValue));
					}
					else if(leftType.equals("Integer") && rightType.equals("Integer")) {
						obj = (int)leftValue == (int)rightValue;
					}
					else if(leftType.equals("Long") && rightType.equals("Long")) {
						obj = (long)leftValue == (long)rightValue;
					}
					else if(leftType.equals("Long") && rightType.equals("Integer")) {
						obj = (long)leftValue == (int)rightValue;
					}
					else if(leftType.equals("Integer") && rightType.equals("Long")) {
						obj = (int)leftValue == (long)rightValue;
					}
					else if(leftType.equals("Double") && rightType.equals("Double")) {
						obj = (double)leftValue == (double)rightValue;
					}
					else if(leftType.equals("Long") && rightType.equals("Double")) {
						obj = (long)leftValue == (double)rightValue;
					}
					else if(leftType.equals("Double") && rightType.equals("Long")) {
						obj = (double)leftValue == (long)rightValue;
					}
					else if(leftType.equals("Double") && rightType.equals("Integer")) {
						obj = (double)leftValue == (int)rightValue;
					}
					else if(leftType.equals("Integer") && rightType.equals("Double")) {
						obj = (int)leftValue == (double)rightValue;
					}
					else {
						obj = leftValue == rightValue;
					}
					
					break;
				}
				case BooleanExpression.DISTINCT:
				{
					if(leftType.equals("Integer") && rightType.equals("Integer")) {
						obj = (int)leftValue != (int)rightValue;
					}
					else if(leftType.equals("Long") && rightType.equals("Long")) {
						obj = (long)leftValue != (long)rightValue;
					}
					else if(leftType.equals("Long") && rightType.equals("Integer")) {
						obj = (long)leftValue != (int)rightValue;
					}
					else if(leftType.equals("Integer") && rightType.equals("Long")) {
						obj = (int)leftValue != (long)rightValue;
					}
					else if(leftType.equals("Double") && rightType.equals("Double")) {
						obj = (double)leftValue != (double)rightValue;
					}
					else if(leftType.equals("Long") && rightType.equals("Double")) {
						obj = (long)leftValue != (double)rightValue;
					}
					else if(leftType.equals("Double") && rightType.equals("Long")) {
						obj = (double)leftValue != (long)rightValue;
					}
					else if(leftType.equals("Double") && rightType.equals("Integer")) {
						obj = (double)leftValue != (int)rightValue;
					}
					else if(leftType.equals("Integer") && rightType.equals("Double")) {
						obj = (int)leftValue != (double)rightValue;
					}
					else {
						obj = leftValue != rightValue;
					}
					
					break;
				}
				case BooleanExpression.LESS:
				{
					if(leftType.equals("Integer") && rightType.equals("Integer")) {
						obj = (int)leftValue < (int)rightValue;
					}
					else if(leftType.equals("Long") && rightType.equals("Long")) {
						obj = (long)leftValue < (long)rightValue;
					}
					else if(leftType.equals("Long") && rightType.equals("Integer")) {
						obj = (long)leftValue < (int)rightValue;
					}
					else if(leftType.equals("Integer") && rightType.equals("Long")) {
						obj = (int)leftValue < (long)rightValue;
					}
					else if(leftType.equals("Double") && rightType.equals("Double")) {
						obj = (double)leftValue < (double)rightValue;
					}
					else if(leftType.equals("Long") && rightType.equals("Double")) {
						obj = (long)leftValue < (double)rightValue;
					}
					else if(leftType.equals("Double") && rightType.equals("Long")) {
						obj = (double)leftValue < (long)rightValue;
					}
					else if(leftType.equals("Double") && rightType.equals("Integer")) {
						obj = (double)leftValue < (int)rightValue;
					}
					else if(leftType.equals("Integer") && rightType.equals("Double")) {
						obj = (int)leftValue < (double)rightValue;
					}
					else {
						throw new Exception("El Tipo de los operandos son incorrectos el operador '<' ");
					}
					
					break;
				}
				case BooleanExpression.GREATER:
				{
					if(leftType.equals("Integer") && rightType.equals("Integer")) {
						obj = (int)leftValue > (int)rightValue;
					}
					else if(leftType.equals("Long") && rightType.equals("Long")) {
						obj = (long)leftValue > (long)rightValue;
					}
					else if(leftType.equals("Long") && rightType.equals("Integer")) {
						obj = (long)leftValue > (int)rightValue;
					}
					else if(leftType.equals("Integer") && rightType.equals("Long")) {
						obj = (int)leftValue > (long)rightValue;
					}
					else if(leftType.equals("Double") && rightType.equals("Double")) {
						obj = (double)leftValue > (double)rightValue;
					}
					else if(leftType.equals("Long") && rightType.equals("Double")) {
						obj = (long)leftValue > (double)rightValue;
					}
					else if(leftType.equals("Double") && rightType.equals("Long")) {
						obj = (double)leftValue > (long)rightValue;
					}
					else if(leftType.equals("Double") && rightType.equals("Integer")) {
						obj = (double)leftValue > (int)rightValue;
					}
					else if(leftType.equals("Integer") && rightType.equals("Double")) {
						obj = (int)leftValue > (double)rightValue;
					}
					else {
						throw new Exception("El Tipo de los operandos son incorrectos el operador '>' ");
					}
					
					break;
				}
				case BooleanExpression.LESS_EQUAL:
				{
					if(leftType.equals("Integer") && rightType.equals("Integer")) {
						obj = (int)leftValue <= (int)rightValue;
					}
					else if(leftType.equals("Long") && rightType.equals("Long")) {
						obj = (long)leftValue <= (long)rightValue;
					}
					else if(leftType.equals("Long") && rightType.equals("Integer")) {
						obj = (long)leftValue <= (int)rightValue;
					}
					else if(leftType.equals("Integer") && rightType.equals("Long")) {
						obj = (int)leftValue <= (long)rightValue;
					}
					else if(leftType.equals("Double") && rightType.equals("Double")) {
						obj = (double)leftValue <= (double)rightValue;
					}
					else if(leftType.equals("Long") && rightType.equals("Double")) {
						obj = (long)leftValue <= (double)rightValue;
					}
					else if(leftType.equals("Double") && rightType.equals("Long")) {
						obj = (double)leftValue <= (long)rightValue;
					}
					else if(leftType.equals("Double") && rightType.equals("Integer")) {
						obj = (double)leftValue <= (int)rightValue;
					}
					else if(leftType.equals("Integer") && rightType.equals("Double")) {
						obj = (int)leftValue <= (double)rightValue;
					}
					else {
						throw new Exception("El Tipo de los operandos son incorrectos el operador '<=' ");
					}
					
					break;

				}
				case BooleanExpression.GREATER_EQUAL:
				{
					if(leftType.equals("Integer") && rightType.equals("Integer")) {
						obj = (int)leftValue >= (int)rightValue;
					}
					else if(leftType.equals("Long") && rightType.equals("Long")) {
						obj = (long)leftValue >= (long)rightValue;
					}
					else if(leftType.equals("Long") && rightType.equals("Integer")) {
						obj = (long)leftValue >= (int)rightValue;
					}
					else if(leftType.equals("Integer") && rightType.equals("Long")) {
						obj = (int)leftValue >= (long)rightValue;
					}
					else if(leftType.equals("Double") && rightType.equals("Double")) {
						obj = (double)leftValue >= (double)rightValue;
					}
					else if(leftType.equals("Long") && rightType.equals("Double")) {
						obj = (long)leftValue >= (double)rightValue;
					}
					else if(leftType.equals("Double") && rightType.equals("Long")) {
						obj = (double)leftValue >= (long)rightValue;
					}
					else if(leftType.equals("Double") && rightType.equals("Integer")) {
						obj = (double)leftValue >= (int)rightValue;
					}
					else if(leftType.equals("Integer") && rightType.equals("Double")) {
						obj = (int)leftValue >= (double)rightValue;
					}
					else {
						throw new Exception("El Tipo de los operandos son incorrectos el operador '>=' ");
					}
					
					break;

				}
				default:
				{
					throw new Exception("Operador no reconocido");
				}
			}
		}
		
		
		return obj;
	}
}
