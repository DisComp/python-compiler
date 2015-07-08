package com.language.model.expression;

import java.util.List;



public class FunctionExpression extends Expression {
	
	private List<String> parameters;
	private String name;
	
	public FunctionExpression(String id,Expression _body,List<String> params){
		parameters=params;//ya se sabe que no vienen repetidos
		this.setLeft(_body);
		name = id;
	}
	
	public static FunctionExpression create(String id,Expression _body,List<String> params){
		
		return new FunctionExpression(id,_body,params);
	}
	

	public String toString(){
		String res = "funcion parseada:/n";
		res+=name+"\n";
		res+=parameters.toString();
		return res;
		/*StringBuffer sb = new StringBuffer();	
		sb.append("def ");
		sb.append(this.func_name);
		sb.append("():\n\t");
		return sb.append(super.toString()).toString();*/
	}
	

}
