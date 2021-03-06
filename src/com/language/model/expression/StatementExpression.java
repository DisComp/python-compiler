package com.language.model.expression;

import com.language.controllers.ScopesController;

public class StatementExpression extends Expression {
	
	public static final String STMT = "Stmt";
	public static final String BREAK = "Break";
	public static final String CONTINUE = "Continue";
	/*private boolean isBody=false;
	
	public void setIsBody(){
		isBody=true;
	}*/
	public StatementExpression() {
		super();
	}
	
	public StatementExpression(String type, Object value, Expression left, Expression right,int _lineNumber) {
		super(type,value,left,right);
		this.setLn(_lineNumber);
		ScopesController.getInstance().actualLineTree=_lineNumber;
	}

	
	public StatementExpression(String type, Expression left, Expression right) {
		super(type, left, right);
		
	}
	public static Expression create(String type, Expression left, Expression right,int _lineNumber) {
		return new StatementExpression(type, null, left, right,_lineNumber);
	}
	public static Expression create(String type, Expression left, Expression right) {
		return new StatementExpression(type, null, left, right,0);
	}
	
	@Override
	public Object execute() throws Exception {
        //try {
        	/*if(!isBody)
        		ScopesController.getInstance().addLine();*/
			ScopesController.getInstance().setActualLine(this.getLn());
			
			ScopesController sc = ScopesController.getInstance();
			if(this.getType() == this.BREAK) {
				sc.setLoopBreacked(true);
			}
			else if(this.getType() == this.CONTINUE) {
				sc.setLoopContinue(true);
			}
			else {
				if(this.getLeft() != null && !sc.getLoopBreacked() && !sc.getLoopContinue()) {
			    	Object result = this.getLeft().execute();
			    }
			    
			    if(this.getRight() != null && !sc.getLoopBreacked() && !sc.getLoopContinue()) {
			    	Object result = this.getRight().execute();
			    }
			}

		/*} catch (Exception e) {
			e.printStackTrace();
		}*/
	    
	    return "FIN";
    		
    }
}
