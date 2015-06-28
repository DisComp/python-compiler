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
}
