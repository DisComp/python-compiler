package com.language.tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.language.model.expression.ArithmeticalExpression;
import com.language.model.expression.BitwiseExpression;
import com.language.model.expression.BooleanExpression;
import com.language.model.expression.Expression;
import com.language.model.expression.LiteralExpression;
import com.language.model.expression.PredefinedFunctionExpression;

public class Tests {
	private static int testNumber = 0;
	private static String section = "";
	
	public static void beginSection(String sec)
	{
		System.out.println("---------------------------- "+ sec +" --------------------------------");
		Tests.testNumber = 0;
		Tests.section = sec;
	}
	
	public static void endSection()
	{
		System.out.println("---------------------------- END "+ Tests.section +"--------------------------------");
		System.out.println();
		System.out.println();
	}
	
	public static void displayResult(Object result,String expected)
	{
		Tests.testNumber++;
		System.out.println("Test "+Tests.testNumber+") Resultado: "+ result+ " || Esperado: "+ expected);
		System.out.println();
	}
	
	public static void doTests() throws Exception {
		Tests.doArithmeticalTest();
		Tests.doBitwiseTest();
		Tests.doBooleanTest();
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
        
        Tests.displayResult(ae1.execute(),"2.0");
        Tests.displayResult(ae2.execute(),"5");
        Tests.displayResult(ae3.execute(),"8.0");
        Tests.displayResult(ae4.execute(),"-7999980");
        Tests.displayResult(ae5.execute(),"2");
        Tests.displayResult(ae6.execute(),"6.6");
        Tests.displayResult(ae7.execute(),"9.2");
        
        Tests.endSection();
	}
	
	public static void doBitwiseTest() throws Exception {
		BitwiseExpression be1 = new BitwiseExpression(BitwiseExpression.AND_BIT,0,new Expression(0xFFFFFFFF),new Expression(0x00000001));
		BitwiseExpression be2 = new BitwiseExpression(BitwiseExpression.LSHIFT,0,new Expression(0xFFFFFFFF),new Expression(0x00000001));
		BitwiseExpression be3 = new BitwiseExpression(BitwiseExpression.NOT_BIT,0,new Expression(0xFFFFFFFF),null);
		BitwiseExpression be4 = new BitwiseExpression(BitwiseExpression.OR_BIT,0,new Expression(0xFFFFFFFF),new Expression(0x00000001));
		BitwiseExpression be5 = new BitwiseExpression(BitwiseExpression.RSHIFT,0,new Expression(0x00000010),new Expression(0x00000001));
		BitwiseExpression be6 = new BitwiseExpression(BitwiseExpression.XOR_BIT,0,new Expression(0xFFFFFFFF),new Expression(0x00000001));
		
		Tests.beginSection("Bitwise Tests");
        
        Tests.displayResult(Integer.toHexString((int)be1.execute()),"1");
        Tests.displayResult(Integer.toHexString((int)be2.execute()),"fffffffe (1 bit a la izquierda)");
        Tests.displayResult(Integer.toHexString((int)be3.execute()),"0");
        Tests.displayResult(Integer.toHexString((int)be4.execute()),"ffffffff");
        Tests.displayResult(Integer.toHexString((int)be5.execute()),"8 (1 bit a la derecha)");
        Tests.displayResult(Integer.toHexString((int)be6.execute()),"fffffffe");
        
        Tests.endSection();
	}
	
	public static void doBooleanTest() throws Exception {
		long l1 = 23L,
			 l2 = 1000000L;
		
		BooleanExpression be1 = new BooleanExpression(BooleanExpression.AND,0,new Expression(true),new Expression(false));
		BooleanExpression be2 = new BooleanExpression(BooleanExpression.DISTINCT,0,new Expression(true),new Expression(false));
		BooleanExpression be3 = new BooleanExpression(BooleanExpression.EQUALS,0,new Expression(true),new Expression(false));
		BooleanExpression be4 = new BooleanExpression(BooleanExpression.GREATER,0,new Expression(500),new Expression(l1));
		BooleanExpression be5 = new BooleanExpression(BooleanExpression.GREATER_EQUAL,0,new Expression(l1),new Expression(23));
		BooleanExpression be6 = new BooleanExpression(BooleanExpression.LESS,0,new Expression(l2),new Expression(80.899));
		BooleanExpression be7 = new BooleanExpression(BooleanExpression.LESS_EQUAL,0,new Expression(45.34),new Expression(l2));
		BooleanExpression be8 = new BooleanExpression(BooleanExpression.NOT,0,new Expression(false),null);
		BooleanExpression be9 = new BooleanExpression(BooleanExpression.OR,0,new Expression(false),new Expression(true));
		
		Tests.beginSection("Boolean Tests");
        
        Tests.displayResult(be1.execute(),"false");
        Tests.displayResult(be2.execute(),"true");
        Tests.displayResult(be3.execute(),"false");
        Tests.displayResult(be4.execute(),"true");
        Tests.displayResult(be5.execute(),"true");
        Tests.displayResult(be6.execute(),"false");
        Tests.displayResult(be7.execute(),"true");
        Tests.displayResult(be8.execute(),"true");
        Tests.displayResult(be9.execute(),"true");
        
        Tests.endSection();
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