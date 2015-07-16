package com.language.controllers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.language.exceptions.ParsingException;
import com.language.model.expression.Expression;
import com.language.model.expression.FunctionExpression;

public class Scope {
	private Map<String, Object> variables; // name - variable
	private String name;
	private List<FunctionExpression> functions;
	
	public Scope(String _name){
		this.name = _name;
		this.variables = new HashMap<String,Object>();
		functions = new ArrayList<FunctionExpression>();
	}
	public String getName(){
		return name;
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
		int indexReplace =-1;
		for (int i=0;i<functions.size();i++){
			if(functions.get(i).getName().equals(f.getName())&&functions.get(i).cantParam()==f.cantParam()){
				indexReplace=i;
			}			
		}
		if(indexReplace!=-1){
			functions.remove(indexReplace);				
		}
		functions.add(f);
	}
	public FunctionExpression getFunction(String Fname, int cParam){
		//si no existe retorna null
		for (int i=0;i<functions.size();i++){
			if(functions.get(i).getName().equals(Fname)&&functions.get(i).cantParam()==cParam)
				return functions.get(i);
		}
		return null;
	}
	@Override
	public String toString(){
		String res=name+"\n";
		res="variables: "+variables.toString()+"\n";
		res+="functions: "+functions.toString();
		return res;
	}
}
