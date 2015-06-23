package com.language.model.expression;


public class Expression {	
	
	private String type;
	private Object value; 
	private Expression left, right;
	
	public Expression(){
		
	}
	
	public Expression(Object value) {
		this.value = value;
	}
	
	public Expression(Object value, Expression left, Expression right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
	public Expression(String type, Object value, Expression left, Expression right) {
		this.type = type;
		this.value = value;		
		this.left = left;
		this.right = right;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
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
