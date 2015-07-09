package com.language.model.expression;

import java.util.List;

import com.language.controllers.ScopesController;


/*Nodo que define una funcion*/
public class FunctionExpression extends Expression {
	
	private List<String> parameters;
	private String name;
	private Expression returnExp;
	public String getName(){
		return name;
	}
	public FunctionExpression(String id,Expression _body,List<String> params){
		parameters=params;//ya se sabe que no vienen repetidos
		this.setLeft(_body.getLeft());//en el body viene el cuerpo y el return(izq y der respectivamente)
		returnExp = _body.getRight();
		name = id;
	}
	
	public static FunctionExpression create(String id,Expression _body,List<String> params){
		
		return new FunctionExpression(id,_body,params);
	}
	
	public Object RunFunction(/*valor de los parámetros*/) throws Exception{
		ScopesController.getInstance().openScope(name);
		//TODO: Cargar parámetros
		Object result = this.getLeft().execute();
		if(returnExp==null)
			result= null;
		else 
			result = returnExp.execute();
		ScopesController.getInstance().closeScope();
		return result;
		
	}
	public int cantParam() {
		return parameters.size();
	}
	@Override
	public Object execute(){
		ScopesController.getInstance().addFunction(this);
		return null;
	}
	public String toString(){
		String res = name+"\n";
		res+="parameters: "+parameters.toString()+"\n";
		res+="returnExpresion: "+returnExp;
		return res;
	}
	

}
