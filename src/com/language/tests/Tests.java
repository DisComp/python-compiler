package com.language.tests;

import com.language.model.expression.ArithmeticalExpression;
import com.language.model.expression.Expression;

public class Tests {
	
	public static void beginSection(String section)
	{
		System.out.println("---------------------------- "+ section +" --------------------------------");
	}
	
	public static void endSection(String section)
	{
		System.out.println("---------------------------- END "+ section +"--------------------------------");
	}
	
	public static void displayResult(Object result,String expected)
	{
		System.out.println("Resultado: "+ result+ " | Esperado: "+ expected);
	}
	
	public static void doArithmeticalTest() throws Exception {

		long l1 = 23L,
			 l2 = 8000000L;
        ArithmeticalExpression ae1 = new ArithmeticalExpression(ArithmeticalExpression.DIV,0,new Expression(9),new Expression(4.5));
        ArithmeticalExpression ae2 = new ArithmeticalExpression(ArithmeticalExpression.DIV_INT,0,new Expression(l1),new Expression(4));
        ArithmeticalExpression ae3 = new ArithmeticalExpression(ArithmeticalExpression.EXP,0,new Expression(2),new Expression(3));
        ArithmeticalExpression ae4 = new ArithmeticalExpression(ArithmeticalExpression.MINUS,0,new Expression(20),new Expression(l2));
        ArithmeticalExpression ae5 = new ArithmeticalExpression(ArithmeticalExpression.MOD,0,new Expression(9),new Expression(7));
        ArithmeticalExpression ae6 = new ArithmeticalExpression(ArithmeticalExpression.PLUS,0,new Expression(2.6),new Expression(4));
        ArithmeticalExpression ae7 = new ArithmeticalExpression(ArithmeticalExpression.TIMES,0,new Expression(2.3),new Expression(4));
    
        Tests.beginSection("Arithmetical Tests");
        Tests.displayResult(ae1.getValue(),"2");
        Tests.displayResult(ae2.getValue(),"5");
        Tests.displayResult(ae3.getValue(),"8");
        Tests.displayResult(ae4.getValue(),"-7999980");
        Tests.displayResult(ae5.getValue(),"2");
        Tests.displayResult(ae6.getValue(),"6.6");
        Tests.displayResult(ae7.getValue(),"9.2");
        
        System.out.println("------------------------- END Arithmetical TEST --------------------------------------------");
        System.out.println();
	}
}