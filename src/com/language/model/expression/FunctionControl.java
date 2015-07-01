package com.language.model.expression;

public class FunctionControl extends Expression {
	
	//TODO: agregar parámetros
	
	private String func_name;
	public static final String FUNC_DECLARATION = "FuncDeclaration";
	
	public FunctionControl(String _id,Expression _body){
		super(FUNC_DECLARATION,"aca van nomFunc, parametros, socpes, etc",_body,null);
		func_name=_id;
		
	}
	
	public static FunctionControl create(String _id,Expression _body){
		
		return new FunctionControl(_id,_body);
	}
	

	public String toString(){
		StringBuffer sb = new StringBuffer();	
		sb.append("def ");
		sb.append(this.func_name);
		sb.append("():\n\t");
		return sb.append(super.toString()).toString();
	}
	

}
