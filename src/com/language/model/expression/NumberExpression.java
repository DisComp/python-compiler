package com.language.model.expression;

import java.util.ArrayList;
import java.util.List;


public class NumberExpression {
	
	private static final int INTEGER 	= 1;
	private static final int FLOAT 		= 2;
	
	private Integer type;
	private Object value; 
	private NumberExpression left, right;
	
	public Object getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public NumberExpression(Integer type, Object value, NumberExpression left, NumberExpression right) {
		this.type 	= type;
		this.value 	= value;
		this.left 	= left;
		this.right 	= right;
	}
	
	public static NumberExpression createInteger(Object value) {
		Integer intValue = new Integer((String)value);
		return new NumberExpression(INTEGER, intValue, null, null);
	}
	
	public static NumberExpression createFloat(Object value) {
		Float floatValue = new Float((String)value);
		return new NumberExpression(FLOAT, floatValue, null, null);
	}
}
