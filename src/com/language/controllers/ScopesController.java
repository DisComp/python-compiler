package com.language.controllers;

public class ScopesController {
	
	private static ScopesController instance = null;
	private int tabsContados = 0;
	private int tabsEsperados = 0;
	
	private ScopesController() {
    	
    }
	
	public static ScopesController getInstance() {
        if (instance == null) {
        	instance = new ScopesController();
        }
        return instance;
    }
	
	public void openScope(String scope/*se puede cambir el tipo*/){
		tabsEsperados++;
	}
	
	public void closeScope(String scope/*se puede cambir el tipo*/){
		tabsEsperados--;
	}
	
	public void finDeInstruccion(){
		if(tabsContados==tabsEsperados-1){//se cierra el scope
			tabsEsperados--;
		}
		else if (tabsEsperados>tabsContados){
			System.out.println("Hay menos tabs de los esperados");
		}
	}
	
	public void consumirTab(){
		tabsContados++;
		if (tabsEsperados<tabsContados){
			System.out.println("Hay mas tabs de los esperados");
		}
	}
}
