package com.language.parser;

import java.io.ByteArrayInputStream;

import java_cup.runtime.Symbol;

import com.language.exceptions.ParsingException;
import com.language.model.expression.Expression;

public class ExpressionParser {

	public static Expression parse(String expText) {

		byte[] expbytes = expText.getBytes(); 
		ByteArrayInputStream bais = new ByteArrayInputStream(expbytes);
		//Scanner s =new Scanner(bais);
		Scanner s = new Scannerchild(bais);
		
		Parser parser = new Parser(s,s);
		try {
			Symbol topsym = parser.parse();

			Expression exp = (Expression) topsym.value;
			return exp;

		
		} catch (ParsingException ex) {
			System.out.println("ERROR DE COMPILACIÓN (ERROR GENERADO POR NUESTRO COMPILADOR): "+ex.getMessage());
			return null;
		} catch (Exception ex) {
			System.out.println("Error parsing source: " + ex.getMessage());
			return null;
		}
	
		

	}
}
