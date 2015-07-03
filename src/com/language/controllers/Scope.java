package com.language.controllers;
import java.util.HashMap;
import java.util.Map;

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
	
	public Object getVarValpublic (String var){
		return variables.get(var);
	}
}
