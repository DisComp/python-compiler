package com.language.model.expression;


public class NumberExpression extends Expression {
	
	private static final String INTEGER = "Integer";
	private static final String FLOAT = "Float";	
	
	public NumberExpression(){
		super();
	}
	
	public NumberExpression(String type, Integer value, NumberExpression left, NumberExpression right){
		super();
	}	
	
	public Expression createInteger(Object value) {
		Integer intValue = new Integer((String)value);
		return new NumberExpression(INTEGER, intValue, null, null);
	}
	
	public static Expression createFloat(Object value) {
		Float floatValue = new Float((String)value);
		return new Expression(FLOAT, floatValue, null, null);
	}
}
