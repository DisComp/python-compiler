package com.language.controllers;

public class ScopesController {
	
	private static ScopesController instance = null;	
	private int tabsContados;
	private int tabsEsperados;
	
	private ScopesController() {
		tabsContados = 0;
		tabsEsperados = 0;
    }
	
	public static ScopesController getInstance() {
        if (instance == null) {
        	instance = new ScopesController();
        }
        return instance;
    }
	
	public void openScope(String scope/*se puede cambir el tipo*/){
		tabsEsperados++;
		System.out.println("Scope: "+scope+" - Expected tabs: " +  tabsEsperados);
	}
	
	//public void closeScope(String scope){
	//	tabsEsperados--;
	//}
	
	public void finDeInstruccion(){
		/*if(tabsContados==tabsEsperados-1){//se cierra el scope
			tabsEsperados--;
		}
		else*/
		//System.out.println("lei instr");
		
		if (tabsEsperados>tabsContados){
			System.out.println("Hay "+tabsContados+" tab y se esperaban: "+tabsEsperados);
		}
		tabsContados = 0;
	}
	
	public void consumirTab(){
		tabsContados++;
		//System.out.println("cosnumo tab");
		if (tabsEsperados<tabsContados){
			System.out.println("Hay "+tabsContados+" tab y se esperaban: "+tabsEsperados);
		}
	}
}
