package com.language.model.expression;

public class StatementExpression extends Expression {
	
	public static final String STMT = "Stmt";
	
	
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
        try {
        	
		    if(this.getLeft() != null) {
		    	Object result = this.getLeft().execute();
		    }
		    
		    if(this.getRight() != null) {
		    	Object result = this.getRight().execute();
		    }

		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    return "FIN";
    		
    }
}
