package com.language.controllers;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.language.exceptions.ParsingException;
import com.sun.org.apache.bcel.internal.generic.DDIV;
public class ScopesController {
	
	private static ScopesController instance = null;	
	//private int tabsContados;
	private int dedentToSend;
	private int expectedTabs;
	private Stack<Scope> scopes;
	private Map<String,Funcion> funciones;
	
	private boolean logs = true;
	
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
	
	public void openScope(String scope/*se puede cambir el tipo*/){
		expectedTabs++;// = tabsContados+1; //el open scope puede estar cerrando otros scopes
		//tabsContados = 0;
		scopes.push(new Scope(scope));
		if(logs)
			System.out.println("Scope: "+scope+" - Expected tabs: " +  expectedTabs);
	}
	
	//public void closeScope(String scope){
	//	tabsEsperados--;
	//}
	
	public void finDeInstruccion(){
		/*if(logs)
			System.out.println("lei instr");
		
		if (tabsContados>tabsEsperados){
			throw new ParsingException("Hay "+tabsContados+" tab y se esperaban: "+tabsEsperados);
		}
		else {
			//cierro todos los scopes segun la tabulacion
			while(tabsEsperados != tabsContados){
				tabsEsperados--;
				scopes.pop();
			}
				
		}
		
		tabsContados = 0;*/
	}
	
	public void consumirTab(){
		//tabsContados++;
		if(logs)
			System.out.println("cosnumo tab");
		//if (tabsContados>tabsEsperados)//se que se pasó de tabs, pero tiro el error al leer la sentencia
			
		
	}
	
	public void addFunctionParameter(String par){
		
	}
	
	public int getExpectedTabs(){
		return expectedTabs;
	}
	public int getDedentToSend(){
		return dedentToSend;
	}
	public void sendDedent(){
		dedentToSend--;
		expectedTabs--;
	}
	public void setDedent(int num){
		dedentToSend=num;
	}
	public void openScope(){
		expectedTabs++;
	}
}
