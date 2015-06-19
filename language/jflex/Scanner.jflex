package com.language.parser;

import java.util.*;
import java_cup.runtime.*;
import com.language.exceptions.*;
import com.language.model.expression.*;

%%

%cup
%line
%unicode
%column

%class Scanner
%{
	private SymbolFactory sf;
	private StringBuffer string = new StringBuffer();

	public Scanner(java.io.InputStream r, SymbolFactory sf) {
		this(r);
		this.sf=sf;
	}

	private Symbol symbol(int type) {
		return new Symbol(type, yyline, yycolumn);
	}
	private Symbol symbol(int type, Object value) {
		return new Symbol(type, yyline, yycolumn, value);
	}
%}

%eofval{
    return symbol(sym.EOF);
%eofval}

LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]

Identifier = [:jletter:][:jletterdigit:]* 

IntegerLiteral = 0 | [1-9][0-9]*
FloatLiteral = (0 | [1-9][0-9]*)\.[0-9]+

%%

"+" 				{ return symbol(sym.PLUS, "+"); }
"-" 				{ return symbol(sym.MINUS, "-"); }
"*" 				{ return symbol(sym.TIMES, "*"); }
"/" 				{ return symbol(sym.DIV, "/"); }

"(" 				{ return symbol(sym.LPAREN, "("); }
")" 				{ return symbol(sym.RPAREN, ")"); }

\"([^\"\r\n\t]*)\"	{ return symbol(sym.STRING, yytext()); }

'([^\"\r\n\t]*)'	{ return symbol(sym.STRING, yytext()); }

{Identifier}		{ return symbol(sym.ID, yytext()); }

{IntegerLiteral}	{ return symbol(sym.INTEGRAL, yytext()); }
					
{FloatLiteral} 		{ return symbol(sym.DECIMAL, yytext()); }

{WhiteSpace}        { /* ignore */ }

. 					{
						throw new ParsingException("Illegal character at line " + yyline + ", column " + yycolumn + " >> " + yytext());
					}



