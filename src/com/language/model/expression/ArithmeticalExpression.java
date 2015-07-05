package com.language.model.expression;

public class ArithmeticalExpression extends Expression {
	
	/* PRECEDENCE:
	 	- EXP
	 	- TIMES, DIV, DIV_INT, MOD
	 	- PLUS, MINUS
	*/
	public static final String PLUS = "Plus";
	public static final String MINUS = "Minus";
	public static final String TIMES = "Times";
	public static final String DIV = "Div";
	public static final String DIV_INT = "DivInt";
	public static final String EXP = "Exp";
	public static final String MOD = "Mod";
	
	public ArithmeticalExpression() {
		super();
	}
	
	public ArithmeticalExpression(String type, Object value, Expression left, Expression right) {
		super(type,value,left,right);
	}
	
	public ArithmeticalExpression(String type, Expression left, Expression right) {
		super(type, left, right);
	}
	
	public static Expression create(String type, Object value, Expression left, Expression right) {
		return new ArithmeticalExpression(type, value, left, right);
	}
	
	@Override
	public Object getValue() throws Exception {
		Object returnObject = null;
		Object left 		= this.getLeft();
		Object right 		= this.getRight();
		
		if(left != null || right != null)
		{
			returnObject = ArithmeticalExpression.operation(this.getType(),left,right);
		}
		else
		{
			returnObject = super.getValue();
		}
		
		return returnObject;
	}
	
	public static Object operation(String type, Object l, Object r) throws Exception {
		Object obj;
		
		switch(type) {
			case ArithmeticalExpression.PLUS:
			{
				obj = (double)l + (double)r;
				break;
			}
			case ArithmeticalExpression.MINUS:
			{
				obj = (double)l - (double)r;
				break;
			}
			case ArithmeticalExpression.TIMES:
			{
				obj = (double)l * (double)r;
				break;
			}
			case ArithmeticalExpression.DIV:
			{
				obj = (double)l / (double)r;
				break;
			}
			case ArithmeticalExpression.DIV_INT:
			{
				obj = (int)l / (int)r;
				break;
			}
			case ArithmeticalExpression.EXP:
			{
				obj = Math.pow((double)l, (double)r);
				break;
			}
			case ArithmeticalExpression.MOD:
			{
				obj = (double)l % (int)r;
				break;
			}
			default:
			{
				throw new Exception("Unrecognized arithmetical operation");
			}
		}
		
		return obj;
	}
}
