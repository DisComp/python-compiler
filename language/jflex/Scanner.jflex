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

%state COMMENT_LINE

%eofval{
    return symbol(sym.EOF);
%eofval}

LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \f]

Identifier = [:jletter:][:jletterdigit:]* 

IntegerLiteral =  0 | [1-9][0-9]*
FloatLiteral   = (0 | [1-9][0-9]*)\.[0-9]+

%%

<YYINITIAL> {

	/* Numbers */
	
	{IntegerLiteral}	{ return symbol(sym.INTEGER, yytext()); }						
	{FloatLiteral} 		{ return symbol(sym.DECIMAL, yytext()); }
	
	/*Arithmetic operators*/
	
	"+" 				{ return symbol(sym.PLUS, "+"); }  /*Also used to concat strings*/
	"-" 				{ return symbol(sym.MINUS, "-"); }
	"*" 				{ return symbol(sym.TIMES, "*"); } /*Also used to concat strings*/
	"**" 				{ return symbol(sym.EXP, "**"); }
	"/" 				{ return symbol(sym.DIV, "/"); }
	"//" 				{ return symbol(sym.DIV_INT, "/"); }
	"%" 				{ return symbol(sym.MOD, "%"); }
	
	/*Bitwise operators*/
	
	"&" 				{ return symbol(sym.AND_BIT, "&"); }
	"|" 				{ return symbol(sym.OR_BIT, "|"); }
	"^" 				{ return symbol(sym.XOR_BIT, "^"); }
	"~" 				{ return symbol(sym.NOT_BIT, "~"); }
	"<<" 				{ return symbol(sym.LSHIFT, "<<"); }
	">>" 				{ return symbol(sym.RSHIFT, ">>"); }
	
	/* Strings */
	
	\"([^\"\r\n\t]*)\"	{ return symbol(sym.STRING, yytext()); } /*Double quotes*/
	'([^\"\r\n\t]*)'	{ return symbol(sym.STRING, yytext()); } /*Single quotes*/
	"\\" 				{ return symbol(sym.ESCAPE, "\\"); }
																/*Three Double quotes*/
																/*Three Single quotes*/
	"\t"				{ return symbol(sym.TAB, "\t"); }
	
	
	/* Boolean */
	
	"True"				{ return symbol(sym.TRUE, "True"); }
	"False"				{ return symbol(sym.FALSE, "False"); }
	
	
	/* Boolean operators */
	
	"and"				{ return symbol(sym.AND, "and"); }
	"or"				{ return symbol(sym.OR, "or"); }
	"not"				{ return symbol(sym.NOT, "not"); }
	"=="				{ return symbol(sym.EQUALS, "=="); }
	"!="				{ return symbol(sym.DISCTINT, "!="); }
	"<"					{ return symbol(sym.LESS, "<"); }
	">"					{ return symbol(sym.GREATER, ">"); }
	"<="				{ return symbol(sym.LESS_EQUAL, ">"); }
	">="				{ return symbol(sym.GREATER_EQUAL, ">"); }
	
	
	/* Punctuation chars */
	"." 				{ return symbol(sym.POINT, "."); }
	";"					{ return symbol(sym.SEMICOLON, ";"); }
	","					{ return symbol(sym.COMMA, ","); }
	":"					{ return symbol(sym.COLON, ":"); }
	"(" 				{ return symbol(sym.LPAREN, "("); }
	")" 				{ return symbol(sym.RPAREN, ")"); }
	"{" 				{ return symbol(sym.LBRACE, "{"); }
	"}" 				{ return symbol(sym.RBRACE, "{"); }
	"[" 				{ return symbol(sym.LBRACKET, "["); }
	"]" 				{ return symbol(sym.RBRACKET, "]"); }
	"="					{ return symbol(sym.ASSIGN, "="); }
	
	/* Functions */
	
	"type"            {return symbol(sym.TYPE_FUNC, "type"); }
	
	
	{Identifier}		{ return symbol(sym.ID, yytext()); }

	{WhiteSpace}        { /* ignore */ }
	
}

/* Comment single line */
<YYINITIAL> 		"#" 		{ yybegin(COMMENT_LINE); }
<COMMENT_LINE> { 	
					[^\n] 		{ } //dismiss everything until eol
				 	[\n] 		{ yybegin(YYINITIAL); }
}


. 					{
						throw new ParsingException("Illegal character at line " + yyline + ", column " + yycolumn + " >> " + yytext());
					}



