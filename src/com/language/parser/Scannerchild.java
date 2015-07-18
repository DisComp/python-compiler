package com.language.parser;

import com.language.controllers.*;


public class Scannerchild extends Scanner {
	
	public Scannerchild(java.io.InputStream in) {
		super(in);
	}
	
	@Override
	public java_cup.runtime.Symbol next_token() throws java.io.IOException {
		//System.out.println("pido token");
		java_cup.runtime.Symbol result;
		if(ScopesController.getInstance().getDedentToSend()>0){
			ScopesController.getInstance().sendDedent();
			result = symbol(sym.DEDENT, "Cierre de bloque");
			//if(result.sym == sym.DEDENT)
			
		}
		else
		{
			result= super.next_token();
		}
		

			/*if(result!=null&&result.value!=null){
				try{
				System.out.println("devuelvo: "+((token) result.value).getX());
				}
			catch (Exception e){
				System.out.println("devuelvo: "+result.value.toString());
			}
		}
			*/
		return result;
	}
	
	
}
