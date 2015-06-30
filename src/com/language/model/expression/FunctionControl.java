package com.language.model.expression;

public class FunctionControl extends Expression {
	
	public String id;
	public FunctionControl(String _id){
		super("funC",null,null,null);
		id=_id;
		
	}
	
	public static FunctionControl create(String _id){
		
		return new FunctionControl(_id);
	}

}
