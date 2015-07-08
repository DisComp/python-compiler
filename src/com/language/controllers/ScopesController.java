package com.language.controllers;
import com.language.model.expression.*;
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
	
	private void log(){
		System.out.println("SCOPES BEGIN---------------------");
		System.out.println(scopes.toString());		
		System.out.println("SCOPES END---------------------");
	}
	
	private ScopesController() {
		//tabsContados = 0;
		dedentToSend = 0;
		expectedTabs = 0;
		scopes = new Stack<Scope>();
		scopes.push(new Scope("main"));

		log();
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
		log();
	}
	//Used by if,while, fun,.. After Execute()
	public void closeScope() {
		scopes.pop();
		log();
	}
	
	
	public void addVariable(String name, Expression val){
		scopes.peek().addVariable(name, val);
		log();
	}
	
	public Expression getVariable(String var_name){
		log();
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
	
	
	
	public void addFunction(FunctionExpression fun){
		scopes.peek().addFunction(fun);
		log();
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
