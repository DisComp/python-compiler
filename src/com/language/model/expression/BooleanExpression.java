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
		Object left 		= this.getLeft();
		Object right 		= this.getRight();
		
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
	
	public static Object operation(String type,Object l,Object r) throws Exception{
		boolean obj;
		
		switch(type)
		{
			case BooleanExpression.EQUALS:
			{
				obj = (double)l == (double)r;
				break;
			}
			case BooleanExpression.DISTINCT:
			{
				obj = (double)l != (double)r;
				break;
			}
			case BooleanExpression.LESS:
			{
				obj = (double)l < (double)r;
				break;
			}
			case BooleanExpression.GREATER:
			{
				obj = (double)l > (double)r;
				break;
			}
			case BooleanExpression.LESS_EQUAL:
			{
				obj = (double)l <= (double)r;
				break;
			}
			case BooleanExpression.GREATER_EQUAL:
			{
				obj = (double)l >= (double)r;
				break;
			}
			case BooleanExpression.NOT:
			{
				obj = !((boolean)l);
				break;
			}
			case BooleanExpression.AND:
			{
				obj = (boolean)l && (boolean)r;
				break;
			}
			case BooleanExpression.OR:
			{
				obj = (boolean)l || (boolean)r;
				break;
			}
			default:
			{
				throw new Exception("Unrecognized boolean operator");
			}
		}
		
		return obj;
	}
}
