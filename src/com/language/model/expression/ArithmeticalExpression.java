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
	
	@Override
	public Object getValue() throws Exception {
		Object returnObject = null;
		Expression left 		= this.getLeft();
		Expression right 		= this.getRight();
		
		if(left != null || right != null)
		{
			returnObject = ArithmeticalExpression.operation(this.getType(),left,right);
		}
		else
		{
			returnObject = super.getValue();
		}
		
		return returnObject;
	}
	
	public static Object operation(String type, Expression l, Expression r) throws Exception {
		
		Number	obj = 0,
				leftValue = (Number)l.getValue(),
				rightValue = (Number)r.getValue();
		
		String 	leftType = leftValue.getClass().getSimpleName(),
				rightType = rightValue.getClass().getSimpleName();
		
		switch(type) {
			case ArithmeticalExpression.PLUS:
			{
				if(leftType.equals("Integer") && rightType.equals("Integer")) {
					obj = (int)leftValue + (int)rightValue;
				}
				else if(leftType.equals("Long") && rightType.equals("Long")) {
					obj = (long)leftValue + (long)rightValue;
				}
				else if(leftType.equals("Long") && rightType.equals("Integer")) {
					obj = (long)leftValue + (int)rightValue;
				}
				else if(leftType.equals("Integer") && rightType.equals("Long")) {
					obj = (int)leftValue + (long)rightValue;
				}
				else if(leftType.equals("Double") && rightType.equals("Double")) {
					obj = (double)leftValue + (double)rightValue;
				}
				else if(leftType.equals("Long") && rightType.equals("Double")) {
					obj = (long)leftValue + (double)rightValue;
				}
				else if(leftType.equals("Double") && rightType.equals("Long")) {
					obj = (double)leftValue + (long)rightValue;
				}
				else if(leftType.equals("Double") && rightType.equals("Integer")) {
					obj = (double)leftValue + (int)rightValue;
				}
				else if(leftType.equals("Integer") && rightType.equals("Double")) {
					obj = (int)leftValue + (double)rightValue;
				}
				else {
					throw new Exception("Tipo de dato primitivo no reconocido");
				}
				
				break;
			}
			case ArithmeticalExpression.MINUS:
			{
				if(leftType.equals("Integer") && rightType.equals("Integer")) {
					obj = (int)leftValue - (int)rightValue;
				}
				else if(leftType.equals("Long") && rightType.equals("Long")) {
					obj = (long)leftValue - (long)rightValue;
				}
				else if(leftType.equals("Long") && rightType.equals("Integer")) {
					obj = (long)leftValue - (int)rightValue;
				}
				else if(leftType.equals("Integer") && rightType.equals("Long")) {
					obj = (int)leftValue - (long)rightValue;
				}
				else if(leftType.equals("Double") && rightType.equals("Double")) {
					obj = (double)leftValue - (double)rightValue;
				}
				else if(leftType.equals("Long") && rightType.equals("Double")) {
					obj = (long)leftValue - (double)rightValue;
				}
				else if(leftType.equals("Double") && rightType.equals("Long")) {
					obj = (double)leftValue - (long)rightValue;
				}
				else if(leftType.equals("Double") && rightType.equals("Integer")) {
					obj = (double)leftValue - (int)rightValue;
				}
				else if(leftType.equals("Integer") && rightType.equals("Double")) {
					obj = (int)leftValue - (double)rightValue;
				}
				else {
					throw new Exception("Tipo de dato primitivo no reconocido");
				}
				
				break;
			}
			case ArithmeticalExpression.TIMES:
			{
				if(leftType.equals("Integer") && rightType.equals("Integer")) {
					obj = (int)leftValue * (int)rightValue;
				}
				else if(leftType.equals("Long") && rightType.equals("Long")) {
					obj = (long)leftValue * (long)rightValue;
				}
				else if(leftType.equals("Long") && rightType.equals("Integer")) {
					obj = (long)leftValue * (int)rightValue;
				}
				else if(leftType.equals("Integer") && rightType.equals("Long")) {
					obj = (int)leftValue * (long)rightValue;
				}
				else if(leftType.equals("Double") && rightType.equals("Double")) {
					obj = (double)leftValue * (double)rightValue;
				}
				else if(leftType.equals("Long") && rightType.equals("Double")) {
					obj = (long)leftValue * (double)rightValue;
				}
				else if(leftType.equals("Double") && rightType.equals("Long")) {
					obj = (double)leftValue * (long)rightValue;
				}
				else if(leftType.equals("Double") && rightType.equals("Integer")) {
					obj = (double)leftValue * (int)rightValue;
				}
				else if(leftType.equals("Integer") && rightType.equals("Double")) {
					obj = (int)leftValue * (double)rightValue;
				}
				else {
					throw new Exception("Tipo de dato primitivo no reconocido");
				}
				
				break;
			}
			case ArithmeticalExpression.DIV:
			{
				if(leftType.equals("Integer") && rightType.equals("Integer")) {
					obj = (int)leftValue / (int)rightValue;
				}
				else if(leftType.equals("Long") && rightType.equals("Long")) {
					obj = (long)leftValue / (long)rightValue;
				}
				else if(leftType.equals("Long") && rightType.equals("Integer")) {
					obj = (long)leftValue / (int)rightValue;
				}
				else if(leftType.equals("Integer") && rightType.equals("Long")) {
					obj = (int)leftValue / (long)rightValue;
				}
				else if(leftType.equals("Double") && rightType.equals("Double")) {
					obj = (double)leftValue / (double)rightValue;
				}
				else if(leftType.equals("Long") && rightType.equals("Double")) {
					obj = (long)leftValue / (double)rightValue;
				}
				else if(leftType.equals("Double") && rightType.equals("Long")) {
					obj = (double)leftValue / (long)rightValue;
				}
				else if(leftType.equals("Double") && rightType.equals("Integer")) {
					obj = (double)leftValue / (int)rightValue;
				}
				else if(leftType.equals("Integer") && rightType.equals("Double")) {
					obj = (int)leftValue / (double)rightValue;
				}
				else {
					throw new Exception("Tipo de dato primitivo no reconocido");
				}
				
				break;
			}
			case ArithmeticalExpression.DIV_INT:
			{
				if(leftType.equals("Integer") && rightType.equals("Integer")) {
					obj = (int)((int)leftValue / (int)rightValue);
				}
				else if(leftType.equals("Long") && rightType.equals("Long")) {
					obj = (int)((long)leftValue / (long)rightValue);
				}
				else if(leftType.equals("Long") && rightType.equals("Integer")) {
					obj = (int)((long)leftValue / (int)rightValue);
				}
				else if(leftType.equals("Integer") && rightType.equals("Long")) {
					obj = (int)((int)leftValue / (long)rightValue);
				}
				else if(leftType.equals("Double") && rightType.equals("Double")) {
					obj = (int)((double)leftValue / (double)rightValue);
				}
				else if(leftType.equals("Long") && rightType.equals("Double")) {
					obj = (int)((long)leftValue / (double)rightValue);
				}
				else if(leftType.equals("Double") && rightType.equals("Long")) {
					obj = (int)((double)leftValue / (long)rightValue);
				}
				else if(leftType.equals("Double") && rightType.equals("Integer")) {
					obj = (int)((double)leftValue / (int)rightValue);
				}
				else if(leftType.equals("Integer") && rightType.equals("Double")) {
					obj = (int)((int)leftValue / (double)rightValue);
				}
				else {
					throw new Exception("Tipo de dato primitivo no reconocido");
				}
				
				break;
			}
			case ArithmeticalExpression.EXP:
			{
				// Checking if the result is a double, or not. If so then returning the value of the fakeResult//
				if(leftType.equals("Integer") && rightType.equals("Integer")) {
					obj = Math.pow((int)leftValue,(int)rightValue);
				}
				else if(leftType.equals("Long") && rightType.equals("Long")) {
					obj = Math.pow((long)leftValue,(long)rightValue);
				}
				else if(leftType.equals("Long") && rightType.equals("Integer")) {
					obj = Math.pow((long)leftValue,(int)rightValue);
				}
				else if(leftType.equals("Integer") && rightType.equals("Long")) {
					obj = Math.pow((int)leftValue, (long)rightValue);
				}
				else if(leftType.equals("Double") && rightType.equals("Double")) {
					obj = Math.pow((double)leftValue, (double)rightValue);
				}
				else if(leftType.equals("Long") && rightType.equals("Double")) {
					obj = Math.pow((long)leftValue, (double)rightValue);
				}
				else if(leftType.equals("Double") && rightType.equals("Long")) {
					obj = Math.pow((double)leftValue, (long)rightValue);
				}
				else if(leftType.equals("Double") && rightType.equals("Integer")) {
					obj = Math.pow((double)leftValue, (int)rightValue);
				}
				else if(leftType.equals("Integer") && rightType.equals("Double")) {
					obj = Math.pow((int)leftValue, (double)rightValue);
				}
				else {
					throw new Exception("Tipo de dato primitivo no reconocido");
				}
				
				break;
			}
			case ArithmeticalExpression.MOD:
			{
				if(leftType.equals("Integer") && rightType.equals("Integer") ) {
					obj = (int)leftValue % (int)rightValue;
				}
				else {
					throw new Exception("Alguno de los operandos del modulo no es entero");
				}
				break;
			}
			default:
			{
				throw new Exception("Operacion aritmetica no reconozida");
			}
		}
		
		return (Object)obj;
	}
	
}
