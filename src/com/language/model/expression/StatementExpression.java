package com.language.model.expression;

import com.language.controllers.ScopesController;

public class StatementExpression extends Expression {
	
	public static final String STMT = "Stmt";
	/*private boolean isBody=false;
	
	public void setIsBody(){
		isBody=true;
	}*/
	public StatementExpression() {
		super();
	}
	
	public StatementExpression(String type, Object value, Expression left, Expression right) {
		super(type,value,left,right);
	}
	
	public StatementExpression(String type, Expression left, Expression right) {
		super(type, left, right);
	}
	
	public static Expression create(String type, Expression left, Expression right) {
		return new StatementExpression(type, null, left, right);
	}
	
	@Override
	public Object execute() throws Exception {
        //try {
        	/*if(!isBody)
        		ScopesController.getInstance().addLine();*/
		    if(this.getLeft() != null) {
		    	Object result = this.getLeft().execute();
		    }
		    
		    if(this.getRight() != null) {
		    	Object result = this.getRight().execute();
		    }

		/*} catch (Exception e) {
			e.printStackTrace();
		}*/
	    
	    return "FIN";
    		
    }
}
