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
WhiteSpace     = [ \f]

Identifier = [:jletter:][:jletterdigit:]* 

LongLiteral    =  (0 | [1-9][0-9]*)L
FloatLiteral   =  (0 | [1-9][0-9]*)\.[0-9]+
IntegerLiteral =   0 | [1-9][0-9]*


%%

<YYINITIAL> {

	/* Line Terminator */
	{LineTerminator} 	{ return symbol(sym.LINE_TERMINATOR,yytext()); }

	/* Numbers */
	
	{IntegerLiteral}	{ return symbol(sym.INTEGER, yytext()); }						
	{FloatLiteral} 		{ return symbol(sym.FLOAT, yytext()); }
	{LongLiteral} 		{ return symbol(sym.LONG_INT, yytext()); }
	
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
	
	\"\"\"([^\"\r\t]*)\"\"\"	{ return symbol(sym.STRING, yytext()); } /*Triple quotes*/
	\"([^\"\r\n\t]*)\"			{ return symbol(sym.STRING, yytext()); } /*Double quotes*/
	'([^\"\r\n\t]*)'			{ return symbol(sym.STRING, yytext()); } /*Single quotes*/
	"\\" 						{ return symbol(sym.ESCAPE, "\\"); }
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
	"!="				{ return symbol(sym.DISTINCT, "!="); }
	"<"					{ return symbol(sym.LESS, "<"); }
	">"					{ return symbol(sym.GREATER, ">"); }
	"<="				{ return symbol(sym.LESS_EQUAL, "<="); }
	">="				{ return symbol(sym.GREATER_EQUAL, ">="); }
	
	
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
	
	/* Structure control */
	
	"if"				{ return symbol(sym.IF, "if"); }
	"else"				{ return symbol(sym.ELSE, "else"); }
	"while"				{ return symbol(sym.WHILE, "while"); }
	"break"				{ return symbol(sym.BREAK, "break"); }
	"continue"			{ return symbol(sym.CONTINUE, "continue"); }
	"for"				{ return symbol(sym.FOR, "for"); }
	"in"				{ return symbol(sym.IN, "in"); }
	"def"				{ return symbol(sym.DEF, "def"); }
	"return"			{ return symbol(sym.RETURN, "return"); }
	
	
	/* Predefined functions */
	
	/* Dictionaries */
	"has_key"           { return symbol(sym.HAS_KEY_FUNC, "has_key"); }
	"items"            	{ return symbol(sym.ITEMS_FUNC, "items"); }
	"keys"	            { return symbol(sym.KEYS_FUNC, "keys"); }
	"pop"	            { return symbol(sym.POP_FUNC, "pop"); }          /* Also used for lists */
	"values"            { return symbol(sym.VALUES_FUNC, "values"); }
	
	/* Strings */
	"count"            	{ return symbol(sym.COUNT_FUNC, "count"); }      /* Also used for lists */
	"find"            	{ return symbol(sym.FIND_FUNC, "find"); }
	"join"            	{ return symbol(sym.JOIN_FUNC, "join"); }
	"split"            	{ return symbol(sym.SPLIT_FUNC, "split"); }
	"replace"          	{ return symbol(sym.REPLACE_FUNC, "replace"); }
	"length"           	{ return symbol(sym.LENGTH_FUNC, "length"); }
	
	/* Lists */
	"append"			{ return symbol(sym.APPEND_FUNC, "append"); }
	"extend"			{ return symbol(sym.EXTEND_FUNC, "extend"); }
	"index"				{ return symbol(sym.INDEX_FUNC, "index"); }
	"insert"			{ return symbol(sym.INSERT_FUNC, "index"); }
	"size"				{ return symbol(sym.SIZE_FUNC, "index"); }
	
	/* Input/Output */
	"raw_input"			{ return symbol(sym.RAW_INPUT_FUNC, "raw_input"); }
	"print"				{ return symbol(sym.PRINT_FUNC, "print"); }
	
	/* Type conversion */
	"string"			{ return symbol(sym.STRING_FUNC, "string"); }
	"int"				{ return symbol(sym.INT_FUNC, "int"); }
	"long"				{ return symbol(sym.LONG_FUNC, "long"); }
	"float"				{ return symbol(sym.FLOAT_FUNC, "float"); }
	"str"				{ return symbol(sym.STR_FUNC, "str"); }
	"tuple"				{ return symbol(sym.TUPLE_FUNC, "tuple"); }
	"list"				{ return symbol(sym.LIST_FUNC, "list"); }
	"dict"				{ return symbol(sym.DICT_FUNC, "dict"); }
	
	/* Others */
	
	"None"				{ return symbol(sym.NONE, "none"); }
	"type"	            { return symbol(sym.TYPE_FUNC, "type"); }
	{Identifier}		{ return symbol(sym.ID, yytext()); }

	{WhiteSpace}        { /* ignore */ }
	
}

/* Comment single line */

<YYINITIAL> 		"#" 		{ yybegin(COMMENT_LINE); }
<COMMENT_LINE> { 	
					[^\n] 		{ /*dismiss everything until eol*/}
				 	[\n] 		{ yybegin(YYINITIAL); }
}


. 					{
						throw new ParsingException("Illegal character at line " + yyline + ", column " + yycolumn + " >> " + yytext());
					}



