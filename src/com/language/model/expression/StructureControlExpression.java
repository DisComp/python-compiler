package com.language.model.expression;

public class StructureControlExpression extends Expression {
	
	public static final String IF = "if";
	public static final String WHILE = "while";
	public static final String FOR_IN = "for_in";
	
	private Expression expr = null;
	
	public StructureControlExpression() {
		super();
	}
	
	public StructureControlExpression(String type, Object value, Expression left, Expression right) {
		super(type,value,left,right);
	}
	
	public StructureControlExpression(String type, Expression left, Expression right) {
		super(type, left, right);
	}
	
	// Adding a BooleanExpression to know which code should we return (for the getValue method) //
	public static Expression create(String type, Object value,Expression e, Expression left, Expression right) {
		StructureControlExpression sce = new StructureControlExpression(type, value, left, right);
		sce.expr = e;
		return sce;
	}
	
	public static Expression create(String type, Object value, Expression e, Expression left) {
		StructureControlExpression sce = new StructureControlExpression(type, value, left, null);
		sce.expr = e;
		return sce;
	}
}
