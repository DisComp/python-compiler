package com.language;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.language.model.expression.Expression;
import com.language.parser.ExpressionParser;

public class Tester {

	public static void main(String[] args) throws IOException {
				
		BufferedReader br = new BufferedReader(new FileReader("tests/TestPredefinedListFunctions.txt"));
		StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        
        while (line != null) {         
			 sb.append(line);
			 line = br.readLine();
			 if(line != null) {
			 	sb.append("\n");
			 }
        }
		String exptext = sb.toString();
		br.close();
		System.out.println(exptext);
		Expression expobj = ExpressionParser.parse(exptext);
			
		System.out.println("Expresion obtenida: ");
		System.out.println(expobj.toString());		
	}
	
}
