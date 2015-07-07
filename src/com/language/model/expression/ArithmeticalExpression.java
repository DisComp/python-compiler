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
		Expression left 		= this.getLeft();
		Expression right 		= this.getRight();
		
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
	
	public static Object operation(String type, Expression l, Expression r) throws Exception {
		Object 	obj = null;
		Number	leftValue = (Number)l.getValue(),
				rightValue = (Number)r.getValue();
		
		String 	leftType = leftValue.getClass().getName(),
				rightType = rightValue.getClass().getName();
		
		switch(type) {
			case ArithmeticalExpression.PLUS:
			{
				
				System.out.println("The left type: "+leftType);
				
				if(leftType == "Double" || rightType == "Double") {
					obj = (double)leftValue + (double)rightValue; 
				}
				else if(leftType == "Long" || rightType == "Long") {
					obj = (long)leftValue.intValue() + (long)rightValue.intValue();
				}
				else {
					obj = (int)leftValue.intValue() + (int)rightValue.intValue();
				}
				break;
			}
			case ArithmeticalExpression.MINUS:
			{
				if(leftType == "Double" || rightType == "Double") {
					obj = (double)leftValue - (double)rightValue; 
				}
				else if(leftType == "Long" || rightType == "Long") {
					obj = (long)leftValue.intValue() - (long)rightValue.intValue();
				}
				else {
					obj = (int)leftValue.intValue() - (int)rightValue.intValue();
				}
				break;
			}
			case ArithmeticalExpression.TIMES:
			{
				if(leftType == "Double" || rightType == "Double") {
					obj = (double)leftValue * (double)rightValue; 
				}
				else if(leftType == "Long" || rightType == "Long") {
					obj = (long)leftValue.intValue() * (long)rightValue.intValue();
				}
				else {
					obj = (int)leftValue.intValue() * (int)rightValue.intValue();
				}
				break;
			}
			case ArithmeticalExpression.DIV:
			{
				// Checking if the result is a double, or not. If so then returning the value of the fakeResult//
				double fakeResult = (double)leftValue / (double)rightValue;
				
				if (leftType == "Double" || rightType == "Double" ||( (fakeResult == Math.floor(fakeResult)) && !Double.isInfinite(fakeResult)) ) {
					obj = (double)leftValue / (double)rightValue; 
				}
				else if(leftType == "Long" || rightType == "Long") {
					obj = (long)leftValue.intValue() / (long)rightValue.intValue();
				}
				else {
					obj = (int)leftValue.intValue() / (int)rightValue.intValue();
				}
				
				break;
			}
			case ArithmeticalExpression.DIV_INT:
			{
				if(leftType == "Long" || rightType == "Long")
					obj = (long)leftValue.intValue() / (long)rightValue.intValue();
				else
					obj= (int)leftValue.intValue() / (int)rightValue.intValue();
				
				break;
			}
			case ArithmeticalExpression.EXP:
			{
				// Checking if the result is a double, or not. If so then returning the value of the fakeResult//
				double fakeResult = (double)leftValue / (double)rightValue;
				
				if (leftType == "Double" || rightType == "Double" ||( (fakeResult == Math.floor(fakeResult)) && !Double.isInfinite(fakeResult)) ) {
					obj = (double)leftValue / (double)rightValue; 
				}
				else if(leftType == "Long" || rightType == "Long") {
					obj = (long)leftValue.intValue() / (long)rightValue.intValue();
				}
				else {
					obj = (int)leftValue.intValue() / (int)rightValue.intValue();
				}
				
				break;
			}
			case ArithmeticalExpression.MOD:
			{
				obj = (double)leftValue % (int)rightValue.intValue();
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
