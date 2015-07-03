package com.language.controllers;
import java.util.Stack;

import com.language.exceptions.ParsingException;
public class ScopesController {
	
	private static ScopesController instance = null;	
	private int tabsContados;
	private int tabsEsperados;
	private Stack<Scope> scopes;
	
	private boolean logs = false;
	
	private ScopesController() {
		tabsContados = 0;
		tabsEsperados = 0;
		scopes = new Stack<Scope>();
		scopes.push(new Scope("main"));
    }
	
	public static ScopesController getInstance() {
        if (instance == null) {
        	instance = new ScopesController();
        }
        return instance;
    }
	
	public void openScope(String scope/*se puede cambir el tipo*/){
		tabsEsperados = tabsContados+1; //el open scope puede estar cerrando otros scopes
		tabsContados = 0;
		scopes.push(new Scope(scope));
		if(logs)
			System.out.println("Scope: "+scope+" - Expected tabs: " +  tabsEsperados);
	}
	
	//public void closeScope(String scope){
	//	tabsEsperados--;
	//}
	
	public void finDeInstruccion(){
		if(logs)
			System.out.println("lei instr");
		
		if (tabsContados>tabsEsperados){
			throw new ParsingException("Hay "+tabsContados+" tab y se esperaban: "+tabsEsperados);
		}
		else {//si tabsContados<tabsEsperados se cierra al menos un scope, sino sigo en el mismo scope
			tabsEsperados = tabsContados;
		}
		
		tabsContados = 0;
	}
	
	public void consumirTab(){
		tabsContados++;
		if(logs)
			System.out.println("cosnumo tab");
		//if (tabsContados>tabsEsperados)//se que se pasó de tabs, pero tiro el error al leer la sentencia
			
		
	}
	
	public void addFunctionParameter(String par){
		
	}
}
