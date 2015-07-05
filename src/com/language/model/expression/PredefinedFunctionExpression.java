package com.language.model.expression;


public class PredefinedFunctionExpression extends Expression{
	
	/* Dictionaries */
	public static final String HAS_KEY_FUNC = "HasKeyFunc";
	public static final String ITEMS_FUNC = "ItemsFunc";
	public static final String KEYS_FUNC = "KeysFunc";
	public static final String POP_FUNC = "PopFunc";
	public static final String VALUES_FUNC = "ValuesFunc";
	
	/* Strings */
	public static final String COUNT_FUNC = "CountFunc";
	public static final String FIND_FUNC = "FindFunc";
	public static final String JOIN_FUNC = "JoinFunc";
	public static final String SPLIT_FUNC = "SplitFunc";
	public static final String REPLACE_FUNC = "ReplaceFunc";
	public static final String LENGTH_FUNC = "LengthFunc";
	
	/* Lists */
	public static final String APPEND_FUNC = "AppendFunc";
	public static final String EXTEND_FUNC = "ExtendFunc";
	public static final String INDEX_FUNC = "IndexFunc";
	public static final String INSERT_FUNC = "InsertFunc";
	public static final String SIZE_FUNC = "SizeFunc";
	
	/* Input/Output */
	public static final String RAW_INPUT_FUNC = "RawInputFunc";
	public static final String PRINT_FUNC = "PrintFunc";
	
	/* Type conversion */
	public static final String INT_FUNC = "IntFunc"; 
	public static final String FLOAT_FUNC = "FloatFunc";
	public static final String STR_FUNC = "StrFunc";
	public static final String TUPLE_FUNC = "TupleFunc";
	public static final String LIST_FUNC = "ListFunc";
	public static final String DICT_FUNC= "DictFunc";

	/* Long */
	public static final String LONG_FUNC = "LongFunc";

	/* String */
	public static final String STRING_FUNC = "StringFunc";
		
	/* Others */
	public static final String TYPE_FUNC = "TypeFunc"; 
	
	/* For multiple parameters*/
	public static final String ARGUMENTS_FUNC = "ArgumentsFunc";
	
	
	public PredefinedFunctionExpression() {
		super();
	}
	
	public PredefinedFunctionExpression(String type, Object value, Expression left, Expression right) {
		super(type,value,left,right);
	}
	
	public PredefinedFunctionExpression(String type, Expression left, Expression right) {
		super(type, left, right);
	}
	
	public static Expression createDictionaryFunction(Expression dict_left, Expression expr) {
		/*e.g: D.has_key(x) saves D on left and x on right */
		return new PredefinedFunctionExpression(expr.getType(), dict_left, expr);
	}
	
	public static Expression createDictionaryFunctionElement(String type, Expression right) {
		/* Save dictionary function parameters on the right (dictionary object will be on the left) */
		return new PredefinedFunctionExpression(type, null, right);
	}
	
	public static Expression createStringFunction(Expression string_left, Expression expr) {
		/*e.g: S.count(x) saves D on left and x on right */
		return new PredefinedFunctionExpression(expr.getType(), string_left, expr);
	}
	
	public static Expression createStringFunctionElement(String type, Expression right) {
		/* Save string function parameters on the right (String object will be on the left) */
		return new PredefinedFunctionExpression(type, null, right);
	}
	
	public static Expression createStringFunctionElement(String type, Expression right_param_one, Expression right_param_two) {
		/* 
		 	Save string function parameters on the right (String object will be on the left)
		 	This function receives two parameters, they're saved on an Expression 
		*/
		Expression arguments = new Expression(ARGUMENTS_FUNC, right_param_one, right_param_two);
		return new PredefinedFunctionExpression(type, null, arguments);
	}
	
	public static Expression createListFunction(Expression list_left, Expression expr) {
		/*e.g: L.append(x) saves L on left and x on right */
		return new PredefinedFunctionExpression(expr.getType(), list_left, expr);
	}
	
	public static Expression createListFunctionElement(String type, Expression right) {
		/* Save list function parameters on the right (List object will be on the left) */
		return new PredefinedFunctionExpression(type, null, right);
	}
	
	public static Expression createListFunctionElement(String type, Expression right_param_one, Expression right_param_two) {
		/* 
		 	Save list function parameters on the right (List object will be on the left)
		 	This function receives two parameters, they're saved on an Expression 
		*/
		Expression arguments = new Expression(ARGUMENTS_FUNC, right_param_one, right_param_two);
		return new PredefinedFunctionExpression(type, null, arguments);
	}
	
	public static Expression createIOFunction(String type, Expression expr) {
		/*e.g: print x,  save on left the element  */
		return new PredefinedFunctionExpression(type, null, expr, null);
	}
	
	public static Expression createTypeConversionFunction(String type, Expression expr) {
		/*e.g: int(x),  save on left the element  */
		return new PredefinedFunctionExpression(type, null, expr, null);
	}
	
	public static Expression createEvaluateTypeFunction(Expression expr) {
		/*e.g: pop function, used for dictionaries and lists, left for object and right for the parameter */
		return new PredefinedFunctionExpression(TYPE_FUNC, null, expr, null);
	}
	
	public static Expression createPopFunction(Expression left, Expression right) {
		/*e.g: pop function, used for dictionaries and lists, left for object and right for the parameter */
		return new PredefinedFunctionExpression(POP_FUNC, null, left, right);
	}
	
	public static Expression createCountFunction(Expression left, Expression right) {
		/*e.g: pop function, used for dictionaries and lists, left for object and right for the parameter */
		return new PredefinedFunctionExpression(COUNT_FUNC, null, left, right);
	}
	
}
