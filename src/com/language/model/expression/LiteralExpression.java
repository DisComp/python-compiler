package com.language.model.expression;


public class LiteralExpression extends Expression {
	
	public static final String INTEGER = "Integer";
	public static final String FLOAT = "Float";
	public static final String LONG_INT = "LongInt";
	public static final String STRING = "String";
	
	public LiteralExpression() {
		super();
	}
	
	public LiteralExpression(String type, LiteralExpression left, LiteralExpression right) {
		super(type, left, right);
	}
	
	public LiteralExpression(String type, Object value, LiteralExpression left, LiteralExpression right) {
		super(type,value,left,right);
	}
	
	public static Expression createInteger(Object value) {
		Integer intValue = new Integer((String)value);
		return new LiteralExpression(INTEGER, intValue, null, null);
	}
	
	public static Expression createFloat(Object value) {
		Float floatValue = new Float((String)value);
		return new Expression(FLOAT, floatValue, null, null);
	}
	
	public static Expression createLongInt(Object value) {
		String longStrValue = ((String)value).substring(0, ((String)value).indexOf('L'));
		Long longValue = new Long((String)longStrValue);
		return new Expression(LONG_INT, longValue, null, null);
	}
	
	public static Expression createString(Object value) {
		String stringValue = new String((String)value);
		return new Expression(STRING, stringValue, null, null);
	}
	
}
