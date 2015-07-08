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
	public Object getValue() throws Exception {
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
		Object 	obj = null;
		Object  leftObject  = l.getValue(),
				rightObject = r.getValue();
		
		String 	leftType = leftObject.getClass().getName(),
				rightType = rightObject.getClass().getName();
		
		if(leftType == "boolean" || rightType == "boolean")	{
			boolean leftValue  = (boolean)leftObject,
					rightValue = (boolean)rightObject;
			
			switch(type)
			{
				case BooleanExpression.NOT:
				{				
					obj = !leftValue;
					break;
				}
				case BooleanExpression.AND:
				{
					obj = leftValue && rightValue;
					break;
				}
				case BooleanExpression.OR:
				{
					obj = leftValue || rightValue;
					break;
				}
				case BooleanExpression.EQUALS:
				{
					obj = leftValue == rightValue;
					break;
				}
				case BooleanExpression.DISTINCT:
				{
					obj = leftValue != rightValue;
					break;
				}
				default:
				{
					throw new Exception("Unrecognized boolean operator");
				}
			}
		}
		else {
			Number  leftValue  = (Number)leftObject,
					rightValue = (Number)rightObject;
			
			switch(type)
			{
				
				
				case BooleanExpression.EQUALS:
				{
					obj = leftValue == rightValue;
					break;
				}
				case BooleanExpression.DISTINCT:
				{
					obj = leftValue != rightValue;
					break;
				}
				case BooleanExpression.LESS:
				{
					if(leftType == "Double" || rightType == "Double") {
						obj = (double)leftValue < (double)rightValue; 
					}
					else if(leftType == "Long" || rightType == "Long") {
						obj = (long)leftValue.intValue() < (long)rightValue.intValue();
					}
					else {
						obj = (int)leftValue.intValue() < (int)rightValue.intValue();
					}
					break;
				}
				case BooleanExpression.GREATER:
				{
					if(leftType == "Double" || rightType == "Double") {
						obj = (double)leftValue > (double)rightValue; 
					}
					else if(leftType == "Long" || rightType == "Long") {
						obj = (long)leftValue.intValue() > (long)rightValue.intValue();
					}
					else {
						obj = (int)leftValue.intValue() > (int)rightValue.intValue();
					}
					break;
				}
				case BooleanExpression.LESS_EQUAL:
				{
					if(leftType == "Double" || rightType == "Double") {
						obj = (double)leftValue <= (double)rightValue; 
					}
					else if(leftType == "Long" || rightType == "Long") {
						obj = (long)leftValue.intValue() <= (long)rightValue.intValue();
					}
					else {
						obj = (int)leftValue.intValue() <= (int)rightValue.intValue();
					}
					break;
				}
				case BooleanExpression.GREATER_EQUAL:
				{
					if(leftType == "Double" || rightType == "Double") {
						obj = (double)leftValue >= (double)rightValue; 
					}
					else if(leftType == "Long" || rightType == "Long") {
						obj = (long)leftValue.intValue() >= (long)rightValue.intValue();
					}
					else {
						obj = (int)leftValue.intValue() >= (int)rightValue.intValue();
					}
					break;
				}
				default:
				{
					throw new Exception("Unrecognized boolean operator");
				}
			}
		}
		
		
		return obj;
	}
}
