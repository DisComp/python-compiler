package com.language.model.expression;

public class ArithmeticalExpression extends Expression {
	
	/* PRECEDENCE:
	 	- EXP
	 	- TIMES, DIV, DIV_INT, MOD
	 	- PLUS, MINUS
	*/
	public static final String PLUS = "Plus";
	public static final String MINUS = "Minus";
	public static final String TIMES = "Times";
	public static final String DIV = "Div";
	public static final String DIV_INT = "DivInt";
	public static final String EXP = "Exp";
	public static final String MOD = "Mod";
	
	public ArithmeticalExpression() {
		super();
	}
	
	public ArithmeticalExpression(String type, Object value, Expression left, Expression right) {
		super(type,value,left,right);
	}
	
	public ArithmeticalExpression(String type, Expression left, Expression right) {
		super(type, left, right);
	}
	
	public static Expression create(String type, Object value, Expression left, Expression right) {
		return new ArithmeticalExpression(type, value, left, right);
	}	
}
