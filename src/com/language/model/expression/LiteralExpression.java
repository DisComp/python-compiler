package com.language.model.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.language.controllers.ScopesController;
import com.language.exceptions.ParsingException;


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
	public static final String STRUCTURE_ACCESS = "StructureAccess";
	public static final String STRUCTURE_ACCESS_ELEMENT = "StructureAccessElement";
	public static final String ASSIGN_STRUCTURE_ACCESS = "AssignStructureAccess";	
	
	public LiteralExpression() {
		super();
	}
		
	public LiteralExpression(String type, Expression left, Expression right) {
		super(type, left, right);
	}
	
	public LiteralExpression(String type, Object value, Expression left, Expression right) {
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
		return new LiteralExpression(FLOAT, floatValue, null, null);
	}
	
	public static Expression createLongInt(Object value) {
		String longStrValue = ((String)value).substring(0, ((String)value).indexOf('L'));
		Long longValue = new Long((String)longStrValue);
		return new LiteralExpression(LONG_INT, longValue, null, null);
	}
	
	public static Expression createString(Object value) {
		String stringValue = new String((String)value);
		return new LiteralExpression(STRING, stringValue, null, null);
	}
	
	public static Expression createAssignment(Expression expr, Expression value) throws Exception {
		LiteralExpression le = null;		
		if(expr.getType() == LiteralExpression.ID){
			String identifier = (String)expr.getValue();
			le = new LiteralExpression(ASSIGN, identifier, value, null);
		} else if (expr.getType() == LiteralExpression.STRUCTURE_ACCESS){
			Expression identifier = (Expression)expr.getValue();
			Expression structureAccess = (Expression)expr.getLeft(); 
			le = new LiteralExpression(ASSIGN_STRUCTURE_ACCESS, identifier, value, structureAccess);
		}
		return le;
	}
	
	public static Expression createList(Expression listElement) throws Exception {		
		/*
		 	This method builds a list as a tree, where the next item
		 	of the list is on the right side, producing a right-balanced
		 	tree containing all list element objects
		*/
		List<Object> listValue = new ArrayList<Object>();
		//Expression listElement = (Expression)value;
		
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
		return new LiteralExpression(LIST, listValue, null, null);
	}
	
	public static Expression createListElement(Expression left, Expression right){
		return new LiteralExpression(LIST, left, right); //type, left, right
	}
	
	public static Expression createDictionary(Expression value) throws Exception {		
		/*
		 	This method builds a dictionary as a tree, where the next key value pair
		 	of the dictionary is on the right side, producing a right-balanced
		 	tree containing all dictionary element objects
		*/
		Map<Object, Object> dictionaryValue = new HashMap<Object, Object>();
		Expression dictionaryElement = value;
		
		
		if(dictionaryElement.getRight() != null){
			// One element on the list
			Object element = ((Expression)dictionaryElement.getLeft()).getValue();
			Map<Object, Object> mapElement = (Map<Object, Object>)element;
			
			dictionaryValue.putAll(mapElement); 
			dictionaryElement = (Expression)dictionaryElement.getRight();
			
			// Parse tree and add leaf values
			while(dictionaryElement.getRight() != null){
				element = ((Expression)dictionaryElement.getLeft()).getValue();
				mapElement = (Map<Object, Object>)element;
				
				dictionaryValue.putAll(mapElement);
				dictionaryElement = (Expression)dictionaryElement.getRight();
			}
			// Last leaf contains last value
			if(dictionaryElement.getValue() != null){
				element = dictionaryElement.getValue();
			} else {
				element = ((Expression)dictionaryElement.getLeft()).getValue();
			}
			mapElement = (Map<Object, Object>)element;
			
			dictionaryValue.putAll(mapElement);					
		}
		return new LiteralExpression(DICTIONARY, dictionaryValue, null, null);
	}
	
	public static Expression createDictionaryElement(Expression left, Expression right){
		return new LiteralExpression(DICTIONARY, left, right); //type, left, right
	}
	
	public static Expression createAtomDictionaryElement(Expression key, Expression value){
		Map<Object, Object> dictionaryKeyValue= new HashMap<Object, Object>();
		try {
			dictionaryKeyValue.put(key.getValue(), value.getValue());
		} catch (Exception e) {
			throw new ParsingException("Error definiendo diccionario, tipo de clave o valor no permitidos");
		}
		return new LiteralExpression(DICTIONARY, dictionaryKeyValue, null, null); //type value left right
	}
	
	public static Expression createTuple(Object value) throws Exception {		
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
			
		} else if(listElement.getLeft() != null){			
			Object element = ((Expression)listElement.getLeft()).getValue();
			listValue.add(element);
		}
		return new LiteralExpression(TUPLE, listValue, null, null);
	}
	
	public static Expression createTupleElement(Expression left, Expression right){
		return new LiteralExpression(TUPLE, left, right); //type, left, right
	}
	
	public static Expression createStructureAccess(Expression id, Expression init, Expression end, Expression jump) {
		//save the parameters as a hash map and insert as left to the structure access
		Map<String, Object> accessElement = new HashMap<String, Object>();
		accessElement.put("init", init);
		accessElement.put("end", end);
		accessElement.put("jump", jump);
		LiteralExpression accessElementAtom = new LiteralExpression(STRUCTURE_ACCESS_ELEMENT, accessElement, null, null);
		return new LiteralExpression(STRUCTURE_ACCESS, id, accessElementAtom, null);
	}
	
	public static Expression createStructureAccess(Expression id, Expression init, Expression end) {
		//save the parameters as a hash map and insert as left to the structure access
		Map<String, Object> accessElement = new HashMap<String, Object>();
		accessElement.put("init", init);
		accessElement.put("end", end);
		LiteralExpression accessElementAtom = new LiteralExpression(STRUCTURE_ACCESS_ELEMENT, accessElement, null, null);
		return new Expression(STRUCTURE_ACCESS, id, accessElementAtom, null);
	}
	
	public static Expression createStructureAccess(Expression id, Expression position) {
		//save the parameters as a hash map and insert as left to the structure access
		Map<String, Object> accessElement = new HashMap<String, Object>();
		accessElement.put("position", position);
		LiteralExpression accessElementAtom = new LiteralExpression(STRUCTURE_ACCESS_ELEMENT, accessElement, null, null);
		return new Expression(STRUCTURE_ACCESS, id, accessElementAtom, null);
	}
	
	@Override
	public Object execute() throws Exception {
		
		ScopesController sc = ScopesController.getInstance();
		
		switch(this.getType()){
			case INTEGER:
			case FLOAT:
			case LONG_INT:
			case BOOLEAN:
			case STRING:
			case LIST:
			case DICTIONARY:
			case TUPLE:
				return super.getValue();
			case NONE:
				return null;
			case ID:
				if(this.getLeft() != null){
					Object var = sc.getVariable((String)this.getLeft().execute());
					return var;
				}
				return null;
				
			case ASSIGN:
				String name = (String)this.getValue();
				Expression value = this.getLeft();
				sc.addVariable(name, value);
				return null;
				
			case ASSIGN_STRUCTURE_ACCESS:
				//replace list value at index
				
			default:
				//return super.getValue();
				throw new Exception("Tipo literal no reconocido");
		}
	}
	
}
