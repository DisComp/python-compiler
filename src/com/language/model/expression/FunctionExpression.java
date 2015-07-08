package com.language.model.expression;

import java.util.List;

import com.language.controllers.ScopesController;


/*Nodo que define una funcion*/
public class FunctionExpression extends Expression {
	
	private List<String> parameters;
	private String name;
	
	public String getName(){
		return name;
	}
	public FunctionExpression(String id,Expression _body,List<String> params){
		parameters=params;//ya se sabe que no vienen repetidos
		this.setLeft(_body);
		name = id;
	}
	
	public static FunctionExpression create(String id,Expression _body,List<String> params){
		
		return new FunctionExpression(id,_body,params);
	}
	
	@Override
	public Object execute(){
		ScopesController.getInstance().addFunction(this);
		return null;
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
