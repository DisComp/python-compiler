package com.language.controllers;

import java.util.ArrayList;

import com.language.model.expression.Expression;


public class InstructionsController {
	
	private static InstructionsController instance = null;
	public ArrayList<Expression> instructionsProgram = null;
	
	// Singleton class
	private InstructionsController() {
    	this.instructionsProgram = new ArrayList<Expression>();
    }

    public static InstructionsController getInstance() {
        if (instance == null) {
        	instance = new InstructionsController();
        }
        return instance;
    }

    public void addInstruction(Expression instr){
    	if(instr != null){
        	this.instructionsProgram.add(0, instr);
    	}
    }

    public void runProgram(){
    	for(int i = 0; i < this.instructionsProgram.size(); i++){
    		if(this.instructionsProgram.get(i) != null){
                try {
					Object result = ((Expression) this.instructionsProgram.get(i)).getValue();
					System.out.println("Linea procesada: " + i);
					System.out.println(result.toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    }
}
