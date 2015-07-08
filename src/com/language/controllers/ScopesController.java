package com.language.controllers;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.language.exceptions.ParsingException;
import com.language.model.expression.Expression;


public class ScopesController {
	
	private static ScopesController instance = null;	
	private int dedentToSend;
	private int expectedTabs;
	
	private Stack<Scope> scopes;
	private Map<String,Funcion> funciones; /* va acá porque las funciónes se agregan en el parseo y no en la ejecucion, no?*/
	
	
	private ScopesController() {
		//tabsContados = 0;
		dedentToSend = 0;
		expectedTabs = 0;
		scopes = new Stack<Scope>();
		funciones = new HashMap<String,Funcion>();
		scopes.push(new Scope("main"));
    }
	
	public static ScopesController getInstance() {
        if (instance == null) {
        	instance = new ScopesController();
        }
        return instance;
    }
	
	/***SCOPE Control***/
	//Used by if,while, fun,.. Before Execute()
	public void openScope(String scope){
		scopes.push(new Scope(scope));
	}
	//Used by if,while, fun,.. After Execute()
	public void closeScope() {
		scopes.pop();
	}
	
	
	public void addVariable(String name, Expression val){
		scopes.peek().addVariable(name, val);
	}
	
	public Expression getVariable(String var_name){
		for(int i= 0; i< scopes.size();i++){
			if(scopes.elementAt(i).containsVariable(var_name)){
				Expression var = scopes.elementAt(i).getVariable(var_name);
				if(var!=null)
					return var;
				else
					throw new ParsingException("Variable "+var_name+" sin instanciar");
			}
			
		}
		throw new ParsingException("Variable "+var_name+" no definida");
	}
	
	
	
	public void addFunctionParameter(String par){
		
	}

	/***IDENT/DEDENT Control***/
	
	//Used by scanner to indicate a scope open
	public void addExpectedTab(){
		expectedTabs++;
	}
	//Used by scanner to compare with the tabs read
	public int getExpectedTabs(){
		return expectedTabs;
	}
	
	//Used by scanner and ScanerChild to indicate that a dedent was send
	public void sendDedent(){
		dedentToSend--;
		expectedTabs--;
	}
	//Used by scanner to indicate de number of dedent to send
	public void setDedent(int num){
		dedentToSend=num;
		expectedTabs--;//cuando se hace el set ya se manda un dedent entonces ahora espero un tab menos
	}
	//Used by ScannerChild to know if has to send dedent or call super
	public int getDedentToSend(){
		return dedentToSend;
	}
}
