package com.language;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.language.model.expression.Expression;
import com.language.parser.ExpressionParser;
import com.language.tests.Tests;

public class Tester {

	public static void main(String[] args) throws IOException, Exception {

		String fileName = "P3.py";

		if(args.length > 0)
			fileName = args[0];
		
		BufferedReader br = new BufferedReader(new FileReader("tests/"+fileName));
		StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        
        while (line != null) {         
			 sb.append(line);
			 line = br.readLine();
			 sb.append("\n");
        }
		String exptext = sb.toString();
		br.close();

		Expression expobj = ExpressionParser.parse(exptext);			
	}
}

