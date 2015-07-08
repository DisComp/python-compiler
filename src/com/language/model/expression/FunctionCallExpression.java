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
	public Object execute(){
		//pedir funcion(name,cantParams)busca fun con la misma firma
			//si no la encuentra tira exception
			//else abro scope
				//agrego parametros como variables y les asigno los valores de parametersValues
				//ejecuto cuerpo de la func
				//si la fun no tiene return devuelvo null, else devuelvo la ejecucion del return
				//cerrar scope
		return null;
	}
	
}
