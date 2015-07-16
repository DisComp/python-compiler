package com.language.model.expression;

import java.util.List;

import com.language.controllers.ScopesController;


/*Nodo que define una funcion*/
public class FunctionExpression extends Expression {
	
	private List<String> parameters;
	private String name;
	private Expression returnExp;
	private Expression body;
	public String getName(){
		return name;
	}
	public FunctionExpression(String id,Expression _body,List<String> params){
		parameters=params;//ya se sabe que no vienen repetidos
		//en el body viene el cuerpo y el return(izq y der respectivamente)
		body=_body.getLeft();
		returnExp = _body.getRight();
		name = id;
	}
	
	public static FunctionExpression create(String id,Expression _body,List<String> params){
		
		return new FunctionExpression(id,_body,params);
	}
	
	public Object RunFunction() throws Exception{
		if(body!=null){
			body.execute();	
		}
		if(returnExp!=null)
			return returnExp.execute();
		return null;
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
	
	public List<String> getParamNames(){
		return parameters;
	}

}
