package com.language.model.expression;

public class StructureControlExpression extends Expression {
	
	public static final String IF = "If";
	public static final String WHILE = "while";
	
	public StructureControlExpression() {
		super();
	}
	
	public StructureControlExpression(String type, Object value, Expression left, Expression right) {
		super(type,value,left,right);
	}
	
	public StructureControlExpression(String type, Expression left, Expression right) {
		super(type, left, right);
	}
	
	public static Expression create(String type, Object value, Expression left, Expression right) {
		return new StructureControlExpression(type, value, left, right);
	}
	
	public static Expression create(String type, Object value, Expression left) {
		return new StructureControlExpression(type, value, left, null);
	}
}
