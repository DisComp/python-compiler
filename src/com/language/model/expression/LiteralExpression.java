package com.language.model.expression;

import java.util.ArrayList;
import java.util.List;


public class LiteralExpression extends Expression {
	
	public static final String ID = "Id";
	public static final String NONE = "None";
	public static final String BOOLEAN = "Boolean";
	public static final String INTEGER = "Integer";
	public static final String FLOAT = "Float";
	public static final String LONG_INT = "LongInt";
	public static final String STRING = "String";
	public static final String LIST = "List";
	
	public LiteralExpression() {
		super();
	}
	
	public LiteralExpression(String type, LiteralExpression left, LiteralExpression right) {
		super(type, left, right);
	}
	
	public LiteralExpression(String type, Object value, LiteralExpression left, LiteralExpression right) {
		super(type,value,left,right);
	}
	
	public static Expression createIdentifier(Object value) {
		return new LiteralExpression(ID, value, null, null);
	}
	
	public static Expression createNone() {
		return new LiteralExpression(NONE, null, null, null);
	}
	
	public static Expression createBoolean(Object value) {
		Boolean boolValue = new Boolean((String)value);
		return new LiteralExpression(BOOLEAN, boolValue, null, null);
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
	
	public static Expression createList(Object value) {		
				
		List<Object> listValue = new ArrayList<Object>();
		Expression listElement = (Expression)value;
		
		if(listElement.getLeft() != null){
			// One element on the list
			Object element = ((Expression)listElement.getRight()).getValue();
			listValue.add(0, element); 
			listElement = (Expression)listElement.getLeft();
			
			// Parse tree and add leaf values
			while(listElement.getLeft() != null){
				element = ((Expression)listElement.getRight()).getValue();
				listValue.add(0, element);
				listElement = (Expression)listElement.getLeft();
			}
			// Last leaf contains last value
			element = listElement.getValue();
			listValue.add(0, element);						
		}
		return new Expression(LIST, listValue, null, null);
	}
	
	public static Expression createListElement(Expression left, Expression right){
		return new Expression(LIST, left, right); //type, left, right
	}
}
