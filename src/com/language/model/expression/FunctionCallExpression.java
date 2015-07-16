package com.language.model.expression;

import java.util.List;

import com.language.controllers.ScopesController;

public class FunctionCallExpression extends Expression {
	private List<Expression> parametersValues;
	private String fname;
	
	public static FunctionCallExpression create(String name,List<Expression> param){
		return new FunctionCallExpression(name,param);
	}
	public FunctionCallExpression(String name,List<Expression> param){
		parametersValues=param;
		fname=name;
	}
	
	@Override
	public Object execute() throws Exception{
		//Find func
		FunctionExpression fun = ScopesController.getInstance().getFunction(fname, parametersValues.size());		
		//Open new scope
		ScopesController.getInstance().openScope(fname);		
		//Load parameters in scope as variables
		List<String>internalParameters = fun.getParamNames();
		for(int i =0; i<internalParameters.size();i++){
			ScopesController.getInstance().addVariable(internalParameters.get(i), parametersValues.get(i).execute());
		}		
		//Execute Body
		Object res = fun.RunFunction();
		//Close Scope
		ScopesController.getInstance().closeScope();
		return res;
	}
	
}
