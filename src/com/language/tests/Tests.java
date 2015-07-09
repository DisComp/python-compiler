package com.language.tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.language.model.expression.ArithmeticalExpression;
import com.language.model.expression.Expression;
import com.language.model.expression.LiteralExpression;
import com.language.model.expression.PredefinedFunctionExpression;

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
        Tests.displayResult(ae1.execute(),"2");
        Tests.displayResult(ae2.execute(),"5");
        Tests.displayResult(ae3.execute(),"8");
        Tests.displayResult(ae4.execute(),"-7999980");
        Tests.displayResult(ae5.execute(),"2");
        Tests.displayResult(ae6.execute(),"6.6");
        Tests.displayResult(ae7.execute(),"9.2");
        
        System.out.println("------------------------- END Arithmetical TEST --------------------------------------------");
        System.out.println();
	}
	
	public static void doPredefinedFunctionsTest() throws Exception {

		Map<Object, Object> l1 = new HashMap<Object, Object>();
		l1.put("Hola", 2);
		l1.put(true, false);
        PredefinedFunctionExpression pf1 = new PredefinedFunctionExpression(PredefinedFunctionExpression.HAS_KEY_FUNC, null, new Expression(LiteralExpression.DICTIONARY, l1, null, null), new LiteralExpression(LiteralExpression.STRING, "Hola", null, null));
    
        Tests.beginSection("Predefined Function Tests");
        Tests.displayResult(pf1.execute(),"true");
        
        System.out.println("------------------------- END Predefined Functions TEST --------------------------------------------");
        System.out.println();
	}
}