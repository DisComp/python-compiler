package com.language.controllers;
import com.language.model.expression.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import com.language.exceptions.ParsingException;
import com.language.model.expression.Expression;


public class ScopesController {
	
	private static ScopesController instance = null;	
	private int dedentToSend;
	private int expectedTabs;
	private List<String> synErrors;
	private Stack<Scope> scopes;
	private boolean logOn=false;
	private void log(){
		if(logOn){
			System.out.println("SCOPES BEGIN---------------------");
			System.out.println(scopes.toString());		
			System.out.println("SCOPES END---------------------");
		}
	}
	
	private ScopesController() {
		//tabsContados = 0;
		dedentToSend = 0;
		expectedTabs = 0;
		synErrors=new ArrayList<String>();
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
	
	
	public void addVariable(String name, Object val){
		scopes.peek().addVariable(name, val);
		log();
	}
	
	public Object getVariable(String var_name) throws Exception{
		for(int i= 0; i< scopes.size();i++){
			if(scopes.elementAt(i).containsVariable(var_name)){//si la enconre
				Object var = scopes.elementAt(i).getVariable(var_name);
				if(var!=null)//tiene valor asignado
					return var;
				else//no tiene valor asignado
					throw new Exception("Variable \'"+var_name+"\' sin instanciar");
			}
			
		}
		throw new Exception("Variable \'"+var_name+"\' no definida.");
	}
	
	public FunctionExpression getFunction(String name, int cant_params) throws Exception{
		for(int i= 0; i< scopes.size();i++){
			FunctionExpression fun = scopes.elementAt(i).getFunction(name,cant_params);
			if(fun!=null){//si la encontre
				return fun;
			}
		}
		throw new Exception("Función "+name+" no disponible en el scope");
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
		//expectedTabs--;//cuando se hace el set ya se manda un dedent entonces ahora espero un tab menos
	}
	//Used by ScannerChild to know if has to send dedent or call super
	public int getDedentToSend(){
		return dedentToSend;
	}
	/*private static int cantLines=0;
	public void addLine(){
		cantLines++;
	}
	public int getLines(){
		return cantLines;
	}*/
	
	/*Syntax error control*/
	public void addSynError(String err){
		synErrors.add(err);
	}
	public boolean parsingOk(){
		if(synErrors.size()==0)
			return true;
		System.out.println("Se encontraron "+synErrors.size()+" errores de sintaxis:");
		for (int i =0 ; i < synErrors.size();i++){
			System.out.println("\t"+(i+1)+" - "+synErrors.get(i));
		}
		return false;
	}
}
