package com.language.model.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Iterator;

import com.language.controllers.ScopesController;


public class StructureControlExpression extends Expression {
	
	public static final String IF = "if";
	public static final String WHILE = "while";
	public static final String FOR_IN = "for_in";
	
	private Expression expr = null;
	
	public StructureControlExpression() {
		super();
	}
	
	public StructureControlExpression(String type, Object value, Expression left, Expression right) {
		super(type,value,left,right);
	}

	
	public StructureControlExpression(String type, Expression left, Expression right) {
		super(type, left, right);
	}
	
	/*for*/
	public static Expression create(String type, Object o, Expression left, Expression right,int line) {
		StructureControlExpression sce = new StructureControlExpression(type, o, left, right,line);
		return sce;
	}
	
	/*public static Expression create(String type, Expression e, Expression left, Expression right,int line) {
		StructureControlExpression sce = new StructureControlExpression(type, null, left, right,line);
		sce.expr = e;
		return sce;
	}*/
	
	/*usadas por el if y el while*/
	public static Expression create(String type, Expression e, Expression left,int _line) {
		StructureControlExpression sce = new StructureControlExpression(type, null, left, null,_line);
		sce.expr = e;
		return sce;
	}
	public StructureControlExpression(String type, Object value, Expression left, Expression right,int line) {
		super(type,value,left,right);
		this.setLn(line);
		ScopesController.getInstance().actualLineTree=line;
	}
	
	@Override
	public Object execute() throws Exception {
		boolean expr;
		Object 	result 	= null;
		
		ScopesController sc = ScopesController.getInstance();
		sc.setActualLine(this.getLn());
		switch (this.getType()) {
		
			case StructureControlExpression.IF:
			{
				expr = (boolean)this.expr.execute();
				
				if (expr) {
					this.getLeft().execute();
				}
				else if(this.getRight() != null) {
					this.getRight().execute();
				}
				
				break;
			}
			case StructureControlExpression.FOR_IN:
			{
				// Getting the list and the iterator //
				List<Object> l 			= (List<Object>)this.getLeft().execute();
				Iterator<Object> iter 	= l.iterator();
				
				Object element;
				
				// Adding the new variable (that is in the header) to the new scope //
				String variableName = (String)this.getValue();
				sc.addVariable(variableName,null);
				
				Expression iis = this.getRight();
				
				// Iterating through the list //
				while(iter.hasNext()) {
					
					// Restoring to the normal state (without 'continue' flag on) //
					sc.setLoopContinue(false);
					
					// Replacing the variable value //
					element = iter.next();
					sc.addVariable(variableName,element);
					
					// Executing //
					iis.execute();
				}
				// Restoring to the normal state (without 'continue' flag on) //
				sc.setLoopContinue(false);
				break;
			}
			case StructureControlExpression.WHILE:
			{
				Expression iis = this.getLeft();
				
				while(!sc.getLoopBreacked() && (boolean)this.expr.execute()) {
					
					// Restoring to the normal state (without 'continue' flag on) //
					sc.setLoopContinue(false);
					
					// Executing //
					iis.execute();
				}
				
				// Setting to the default again the break//
				sc.setLoopBreacked(false);
				// Restoring to the normal state (without 'continue' flag on) //
				sc.setLoopContinue(false);
				break;
			}
			default :
			{
				throw new Exception("Cabezal del bloque no reconocido");
			}
		}
		
		return result;
	}
}
