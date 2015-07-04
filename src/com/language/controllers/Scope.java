package com.language.controllers;
import java.util.HashMap;
import java.util.Map;

import com.language.exceptions.ParsingException;

public class Scope {
	private Map<String,Object> variables;
	private String name;
	
	
	public Scope(String _name){
		name=_name;
		variables=new HashMap<String,Object>();
	}
	
	public boolean containsVar(String var){
		return variables.containsKey(var);
	}
	
	public Object getVarVal(String var){
		return variables.get(var);
	}
	
	public void addVar(String name,Object value){
		if(containsVar(name))
			throw new ParsingException("La variable "+name+" ya está definida dentro del scope");
		else
			variables.put(name,value);
	}
}
