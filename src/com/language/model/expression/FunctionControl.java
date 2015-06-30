package com.language.model.expression;

public class FunctionControl extends Expression {
	
	//TODO: agregar parámetros
	
	private String func_name;
	public static final String FUNC_DECLARATION = "FuncDeclaration";
	
	public FunctionControl(String _id){
		super(FUNC_DECLARATION,"aca va el cuerpo",null,null);
		func_name=_id;
		
	}
	
	public static FunctionControl create(String _id){
		
		return new FunctionControl(_id);
	}
	

	public String toString(){
		StringBuffer sb = new StringBuffer();	
		sb.append("def ");
		sb.append(this.func_name);
		sb.append("():\n\t");
		return sb.append(super.toString()).toString();
	}
	

}
