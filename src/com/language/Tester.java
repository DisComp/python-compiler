package com.language;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.language.model.expression.Expression;
import com.language.parser.ExpressionParser;

public class Tester {

	public static void main(String[] args) throws IOException {
				
		BufferedReader br = new BufferedReader(new FileReader("tests/Test1.txt"));
        String line = br.readLine();
        while (line != null) {         
            
	        String exptext = line.toString();
			
			System.out.println(exptext);
			Expression expobj = ExpressionParser.parse(exptext);
			
			System.out.println("Expresion obtenida: ");
			System.out.println(expobj.toString());
			
			line = br.readLine();
        }
        br.close();
		
	}
	
}
