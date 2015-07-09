package com.language.controllers;
import java.util.HashMap;
import java.util.Map;

import com.language.exceptions.ParsingException;
import com.language.model.expression.Expression;
import com.language.model.expression.FunctionExpression;

public class Scope {
	private Map<String, Object> variables; // name - variable
	private String name;
	private Map<String,FunctionExpression> functions;
	
	public Scope(String _name){
		this.name = _name;
		this.variables = new HashMap<String,Object>();
		functions = new HashMap<String,FunctionExpression>();
	}
	
	public boolean containsVariable(String var){
		return variables.containsKey(var);
	}
	
	public Object getVariable(String var){
		//si no existe retorna null
		return variables.get(var);
	}
	
	public void addVariable(String name, Object value){
		variables.put(name,value);//java remplaza el valor antiguo en caso de que exista la var
	}
	public void addFunction(FunctionExpression f){
		functions.put(f.getName(), f);
	}
	public FunctionExpression getFunction(String name){
		//si no existe retorna null
		return functions.get(name);
	}
	@Override
	public String toString(){
		String res=name+"\n";
		res="variables: "+variables.toString()+"\n";
		res+="functions: "+functions.toString();
		return res;
	}
}
