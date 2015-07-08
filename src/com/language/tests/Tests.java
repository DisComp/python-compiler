package com.language.tests;

import com.language.model.expression.ArithmeticalExpression;
import com.language.model.expression.BitwiseExpression;
import com.language.model.expression.Expression;

public class Tests {
	
	public static void beginSection(String section)
	{
		System.out.println("---------------------------- "+ section +" --------------------------------");
	}
	
	public static void endSection(String section)
	{
		System.out.println("---------------------------- END "+ section +"--------------------------------");
		System.out.println();
		System.out.println();
	}
	
	public static void displayResult(Object result,String expected)
	{
		System.out.println("Resultado: "+ result+ " | Esperado: "+ expected);
		System.out.println();
	}
	
	public static void doTests() throws Exception {
		Tests.doArithmeticalTest();
		Tests.doBitwiseTest();
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
        
        Tests.displayResult(ae1.getValue(),"2.0");
        Tests.displayResult(ae2.getValue(),"5");
        Tests.displayResult(ae3.getValue(),"8.0");
        Tests.displayResult(ae4.getValue(),"-7999980");
        Tests.displayResult(ae5.getValue(),"2");
        Tests.displayResult(ae6.getValue(),"6.6");
        Tests.displayResult(ae7.getValue(),"9.2");
        
        Tests.endSection("Arithmetical Tests");
	}
	
	public static void doBitwiseTest() throws Exception {
		BitwiseExpression be1 = new BitwiseExpression(BitwiseExpression.AND_BIT,0,new Expression(0xFFFFFFFF),new Expression(0x00000001));
		BitwiseExpression be2 = new BitwiseExpression(BitwiseExpression.LSHIFT,0,new Expression(0xFFFFFFFF),new Expression(0x00000001));
		BitwiseExpression be3 = new BitwiseExpression(BitwiseExpression.NOT_BIT,0,new Expression(0xFFFFFFFF),null);
		BitwiseExpression be4 = new BitwiseExpression(BitwiseExpression.OR_BIT,0,new Expression(0xFFFFFFFF),new Expression(0x00000001));
		BitwiseExpression be5 = new BitwiseExpression(BitwiseExpression.RSHIFT,0,new Expression(0x00000010),new Expression(0x00000001));
		BitwiseExpression be6 = new BitwiseExpression(BitwiseExpression.XOR_BIT,0,new Expression(0xFFFFFFFF),new Expression(0x00000001));
		
		Tests.beginSection("Bitwise Tests");
        
        Tests.displayResult(Integer.toHexString((int)be1.getValue()),"1");
        Tests.displayResult(Integer.toHexString((int)be2.getValue()),"fffffffe (1 bit a la izquierda)");
        Tests.displayResult(Integer.toHexString((int)be3.getValue()),"0");
        Tests.displayResult(Integer.toHexString((int)be4.getValue()),"ffffffff");
        Tests.displayResult(Integer.toHexString((int)be5.getValue()),"8 (1 bit a la derecha)");
        Tests.displayResult(Integer.toHexString((int)be6.getValue()),"fffffffe");
        
        Tests.endSection("Bitwise Tests");
	}
}