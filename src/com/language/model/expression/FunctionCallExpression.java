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
		FunctionExpression fun = ScopesController.getInstance().getFunction(fname, parametersValues.size());
		return fun.RunFunction(/*parametersValues*/);
	}
	
}
