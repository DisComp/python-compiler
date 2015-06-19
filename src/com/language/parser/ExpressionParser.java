package com.language.parser;

import java.io.ByteArrayInputStream;

import java_cup.runtime.Symbol;

import com.language.exceptions.ParsingException;
import com.language.model.expression.Expression;

public class ExpressionParser {

	public static Expression parse(String expText) {

		byte[] expbytes = expText.getBytes();
		ByteArrayInputStream bais = new ByteArrayInputStream(expbytes);

		Parser parser = new Parser(new Scanner(bais));
		try {
			Symbol topsym = parser.parse();

			Expression exp = (Expression) topsym.value;
			return exp;

		} catch (Throwable ex) {
			throw new ParsingException("Error parsing source: " + ex.getMessage());
		}

	}
}
