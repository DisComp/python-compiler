package com.language.model.expression;

public class ArithmeticalExpression extends Expression {
	
	public static final String PLUS = "Plus";
	public static final String MINUS = "Minus";
	public static final String TIMES = "Times";
	public static final String DIV = "Div";
	
	public ArithmeticalExpression() {
		super();
	}
	
	public ArithmeticalExpression(String type, Expression left, Expression right) {
		super(type, null, left, right);
	}
	
	public static Expression create(String type, Expression left, Expression right) {
		return new ArithmeticalExpression(type, left, right);
	}	
}
