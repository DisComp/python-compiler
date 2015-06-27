package com.language.model.expression;


public class NumberExpression extends Expression {
	
	public static final String INTEGER = "Integer";
	public static final String FLOAT = "Float";	
	
	public NumberExpression() {
		super();
	}
	
	public NumberExpression(String type, NumberExpression left, NumberExpression right) {
		super(type, left, right);
	}
	
	public NumberExpression(String type, Object value, NumberExpression left, NumberExpression right) {
		super(type,value,left,right);
	}
	
	public static Expression createInteger(Object value) {
		Integer intValue = new Integer((String)value);
		return new NumberExpression(INTEGER, intValue, null, null);
	}
	
	public static Expression createFloat(Object value) {
		Float floatValue = new Float((String)value);
		return new Expression(FLOAT, floatValue, null, null);
	}
	
}
