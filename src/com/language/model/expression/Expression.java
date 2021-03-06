package com.language.model.expression;


public class Expression {	
	
	private String type;
	private Object value; 
	private Expression left, right;
	private int lineNumber=0;
	public void setLn(int n){
		lineNumber=n;
	}
	public int getLn(){
		return lineNumber;
	}
	public Expression(){
		
	}
	
	public Expression(Object value) {
		this.value = value;
	}	
	
	public Expression(String type,  Expression left, Expression right) {
		this.type = type;	
		this.left = left;
		this.right = right;
	}
	
	public Expression(String type, Object value, Expression left, Expression right) {
		this.type = type;
		this.value = value;		
		this.left = left;
		this.right = right;
	}
	
	/*
	 * This method is redefined for each Expression subclass 
	 * which handle its conditions to give the value from an expression
	*/
	public Object execute() throws Exception {
		return value;
	}
	
	public Object getValue() throws Exception {
		return value;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}	
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public Expression getLeft() {
		return left;
	}
	
	public void setLeft(Expression left) {
		this.left = left;
	}
	
	public Expression getRight() {
		return right;
	}
	
	public void setRight(Expression right) {
		this.right = right;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();		
		String tab = "	";
		
		sb.append(this.value);
		sb.append("\n");
		if(this.left != null){
			Expression exprL = this.left;
			sb.append(tab);
			String exprLStr = exprL.toString();
			sb.append(exprLStr);
		}
		if(this.right != null){
			Expression exprR = this.right;
			sb.append(tab);
			String exprRStr = exprR.toString();			
			sb.append(exprRStr);
		}
		sb.append(tab);
		return sb.toString();
	}

	/*public String toString(int level) {
		StringBuffer sb = new StringBuffer();
		
		String tab = "";
		for (int i = 0; i < level; i++) {
			tab += "   ";
		}
		
		sb.append(tab);
		sb.append(this.value);
		
		if (this.arguments.size() > 0) {
			sb.append("(\n");
	
			for (int i = 0; i < this.arguments.size(); i++) {
				Expression exparg = this.arguments.get(i);
				if (exparg == null) {
					sb.append("\n");
				} else {
					String exptext = exparg.toString(level + 1);
					sb.append(exptext);
				}
			}
			
			sb.append(tab);
			sb.append(")");
		} 
		
		sb.append("\n");
		
		return sb.toString();
	}*/
}
