package com.language.controllers;
import java.util.HashMap;
import java.util.Map;

import com.language.exceptions.ParsingException;
import com.language.model.expression.Expression;

public class Scope {
	private Map<String, Expression> variables; // name - variable
	private String name;
	
	
	public Scope(String _name){
		this.name = _name;
		this.variables = new HashMap<String,Expression>();
	}
	
	public boolean containsVariable(String var){
		return variables.containsKey(var);
	}
	
	public Object getVariable(String var){
		return variables.get(var);
	}
	
	public void addVariable(String name, Expression value){
		if(containsVariable(name))
			throw new ParsingException("La variable "+name+" ya está definida dentro del scope");
		else
			variables.put(name,value);
	}
}
