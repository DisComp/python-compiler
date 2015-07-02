package com.language.model.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LiteralExpression extends Expression {
	
	public static final String ID = "Id";
	public static final String NONE = "None";
	public static final String BOOLEAN = "Boolean";
	public static final String INTEGER = "Integer";
	public static final String FLOAT = "Float";
	public static final String LONG_INT = "LongInt";
	public static final String STRING = "String";
	public static final String LIST = "List";
	public static final String DICTIONARY = "Dictionary";
	public static final String TUPLE = "Tuple";
	public static final String ASSIGN = "Assign";
	
	private Object variableValue;

	public Object getVariableValue() {
		return variableValue;
	}

	public void setVariableValue(Object variableValue) {
		this.variableValue = variableValue;
	}
	
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
	
	public static Expression createAssignment(String identifier, Object value) {
		//Map<String, Object> assignValue = new HashMap<String, Object>();
		//assignValue.put(identifier, value);
		LiteralExpression le = new LiteralExpression(ASSIGN, identifier, null, null);
		le.setVariableValue(value);
		return le;
	}
	
	public static Expression createList(Object value) {		
		/*
		 	This method builds a list as a tree, where the next item
		 	of the list is on the right side, producing a right-balanced
		 	tree containing all list element objects
		*/
		List<Object> listValue = new ArrayList<Object>();
		Expression listElement = (Expression)value;
		
		if(listElement.getRight() != null){
			// One element on the list
			Object element = ((Expression)listElement.getLeft()).getValue();
			listValue.add(element); 
			listElement = (Expression)listElement.getRight();
			
			// Parse tree and add leaf values
			while(listElement.getRight() != null){
				element = ((Expression)listElement.getLeft()).getValue();
				listValue.add(element);
				listElement = (Expression)listElement.getRight();
			}
			// Last leaf contains last value
			element = listElement.getValue();
			listValue.add(element);						
		}
		return new Expression(LIST, listValue, null, null);
	}
	
	public static Expression createListElement(Expression left, Expression right){
		return new Expression(LIST, left, right); //type, left, right
	}
	
	public static Expression createDictionary(Object value) {		
		/*
		 	This method builds a dictionary as a tree, where the next key value pair
		 	of the dictionary is on the right side, producing a right-balanced
		 	tree containing all dictionary element objects
		*/
		Map<String, String> dictionaryValue = new HashMap<String, String>();
		Expression dictionaryElement = (Expression)value;
		
		
		if(dictionaryElement.getRight() != null){
			// One element on the list
			Object element = ((Expression)dictionaryElement.getLeft()).getValue();
			Map<String, String> mapElement = (Map<String, String>)element;
			
			dictionaryValue.putAll(mapElement); 
			dictionaryElement = (Expression)dictionaryElement.getRight();
			
			// Parse tree and add leaf values
			while(dictionaryElement.getRight() != null){
				element = ((Expression)dictionaryElement.getLeft()).getValue();
				mapElement = (Map<String, String>)element;
				
				dictionaryValue.putAll(mapElement);
				dictionaryElement = (Expression)dictionaryElement.getRight();
			}
			// Last leaf contains last value
			if(dictionaryElement.getValue() != null){
				element = dictionaryElement.getValue();
			} else {
				element = ((Expression)dictionaryElement.getLeft()).getValue();
			}
			mapElement = (Map<String, String>)element;
			
			dictionaryValue.putAll(mapElement);					
		}
		return new Expression(DICTIONARY, dictionaryValue, null, null);
	}
	
	public static Expression createDictionaryElement(Expression left, Expression right){
		return new Expression(DICTIONARY, left, right); //type, left, right
	}
	
	public static Expression createAtomDictionaryElement(String key, String value){
		Map<String, String> dictionaryKeyValue= new HashMap<String, String>();
		dictionaryKeyValue.put(key, value);
		return new Expression(DICTIONARY, dictionaryKeyValue, null, null); //type value left right
	}
	
	public static Expression createTuple(Object value) {		
		/*
		 	This method builds a tuple as a tree, where the next item
		 	of the list is on the right side, producing a right-balanced
		 	tree containing all tuple element objects
		*/
		List<Object> listValue = new ArrayList<Object>();
		Expression listElement = (Expression)value;
		
		if(listElement.getRight() != null){
			// One element on the list
			Object element = ((Expression)listElement.getLeft()).getValue();
			listValue.add(element); 
			listElement = (Expression)listElement.getRight();
			
			// Parse tree and add leaf values
			while(listElement.getRight() != null){
				element = ((Expression)listElement.getLeft()).getValue();
				listValue.add(element);
				listElement = (Expression)listElement.getRight();
			}
			// Last leaf contains last value
			if(listElement.getValue() != null){
				element = listElement.getValue();
			} else {
				element = ((Expression)listElement.getLeft()).getValue();
			}
			listValue.add(element);						
		} else {
			Object element = ((Expression)listElement.getLeft()).getValue();
			listValue.add(element);
		}
		return new Expression(TUPLE, listValue, null, null);
	}
	
	public static Expression createTupleElement(Expression left, Expression right){
		return new Expression(TUPLE, left, right); //type, left, right
	}
	
}
