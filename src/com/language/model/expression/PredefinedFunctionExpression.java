package com.language.model.expression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import com.language.controllers.ScopesController;


public class PredefinedFunctionExpression extends Expression {
	
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
		Expression argument = expr.getLeft();
		return new PredefinedFunctionExpression(expr.getType(), dict_left, argument);
	}
	
	public static Expression createDictionaryFunctionElement(String type, Expression left) {
		/* Save dictionary function parameters  */
		return new PredefinedFunctionExpression(type, left, null);
	}
	
	public static Expression createStringFunction(Expression string_left, Expression expr) {
		/*e.g: S.count(x) saves D on left and x on right */
		Expression arguments = expr.getLeft();
		return new PredefinedFunctionExpression(expr.getType(), string_left, arguments);
	}
	
	public static Expression createStringFunctionElement(String type, Expression left) {
		/* Save string function parameters on the left  */
		return new PredefinedFunctionExpression(type, left, null);
	}
	
	public static Expression createStringFunctionElement(String type, Expression right_param_one, Expression right_param_two) {
		/* 
		 	Save string function parameters on the right (String object will be on the left)
		 	This function receives two parameters, they're saved on an Expression 
		*/
		Expression arguments = new Expression(ARGUMENTS_FUNC, right_param_one, right_param_two);
		return new PredefinedFunctionExpression(type, arguments, null);
	}
	
	public static Expression createListFunction(Expression list_left, Expression expr) {
		/*e.g: L.append(x) saves L on left and x on right */
		return new PredefinedFunctionExpression(expr.getType(), list_left, expr);
	}
	
	public static Expression createListFunctionElement(String type, Expression left) {
		/* Save list function parameters on the left */
		return new PredefinedFunctionExpression(type, left, null);
	}
	
	public static Expression createListFunctionElement(String type, Expression right_param_one, Expression right_param_two) {
		/* 
		 	Save list function parameters on the right (List object will be on the left)
		 	This function receives two parameters, they're saved on an Expression 
		*/
		//Expression arguments = new Expression(ARGUMENTS_FUNC, right_param_one, right_param_two);
		//return new PredefinedFunctionExpression(type, null, arguments);
		/*
		 	Save the tow parameters as childs
		 */
		return new PredefinedFunctionExpression(type, right_param_one, right_param_two);
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
	/*private boolean isObject(Object o,String type) throws Exception{
		String dictValueClass = o.getClass().getSimpleName();
		if(!dictValueClass.equals(type)){
			throw new Exception("Esta funcion no esta definida para el tipo " + dictValueClass);
		}
		return true;
	}*/
	@Override
	public Object execute() throws Exception {
		

	Expression 	left 	= this.getLeft(),
				right	= this.getRight();
		switch(this.getType()){
		case INSERT_FUNC:{
			Object lObj = this.getLeft().execute();
			Object ObjParamToFind = this.getRight().getRight().execute();//any value expeted
			String dictValueClass = lObj.getClass().getSimpleName();
			if(!dictValueClass.equals("ArrayList")){
				throw new Exception("La función insert solo está definida para el tipo List");
			}
			int index = 0;
			if(this.getRight().getLeft()!=null){//exist second parameter
				try{
					index = (int) this.getRight().getLeft().execute();
				}catch(Exception e){
					throw new Exception("La función insert espera un entrero como segundo parámetro");
				}
			}			
			List<Object> l = (List<Object>) lObj;
			try{
				l.add(index, ObjParamToFind);
			}
			catch (Exception e){
				throw new Exception("Se quizo insertar un elemento en la lista "+this.getLeft().getValue()+" con un índice fuera de rango");
			}
			
			return l;
		}
		case INDEX_FUNC:{
			Object lObj = this.getLeft().execute();
			Object ObjParamToFind = this.getRight().getLeft().execute();//any value expeted
			String dictValueClass = lObj.getClass().getSimpleName();
			if(!dictValueClass.equals("ArrayList")){
				throw new Exception("La función index solo está definida para el tipo List.");
			}
			int start = 0;
			if(this.getRight().getRight()!=null){//exist second parameter
				try{
					start = (int) this.getRight().getRight().execute();
				}catch(Exception e){
					throw new Exception("La función index espera un entrero como segundo parámetro.");
				}
			}			
			List<Object> l = (List<Object>) lObj;
			int res=0;
			try{
				res=l.subList(start, l.size()-1).indexOf(ObjParamToFind)+start;
			}catch(Exception e){
				throw new Exception("Valor de start de la funcion index fuera de rango");
			}
			return res;
		}
			case EXTEND_FUNC:{
				Object lObj = this.getLeft().execute();
				Object lObjParam = this.getRight().getLeft().execute();
				String dictValueClass = lObjParam.getClass().getSimpleName();
				if(!dictValueClass.equals("ArrayList")){
					throw new Exception("Se esperaba un parámetro de tipo List");
				}
				dictValueClass = lObj.getClass().getSimpleName();
				if(!dictValueClass.equals("ArrayList")){
					throw new Exception("La función extend solo está definida para el tipo Lista.");
				}
				List<Object> l = (List<Object>) lObj;
				List<Object> lParam = (List<Object>) lObjParam;
				l.addAll(0, lParam);
				return l;
			}
			case APPEND_FUNC:{
				Object lObj = this.getLeft().execute();
				String dictValueClass = lObj.getClass().getSimpleName();
				if(!dictValueClass.equals("ArrayList")){
					throw new Exception("La función append solo está definida para el tipo Lista.");
				}
				List<Object> l = (List<Object>) lObj;
				l.add(this.getRight().getLeft().execute());
				return l;
			}
			case SIZE_FUNC:{
				Object lObj = this.getLeft().execute();
				String dictValueClass = lObj.getClass().getSimpleName();
				if(!dictValueClass.equals("ArrayList")){
					throw new Exception("La función append solo está definida para el tipo Lista.");
				}
				List<Object> l = (List<Object>) lObj;
				return l.size();
			}
	
			
			case HAS_KEY_FUNC:
				
				if(this.getLeft() == null){
					throw new Exception("Esta funcion no esta definida para este tipo.");
				}
				if(this.getRight() == null){
					throw new Exception("Se esperaba 1 argumento para la funcion y se recibieron 0.");
				}
				
				Object dictValue_HKF = this.getLeft().execute();
				Object argDictValue = this.getRight().execute();
				
				String dictValueClass_HKF = dictValue_HKF.getClass().getSimpleName();
				if(!dictValueClass_HKF.equals("HashMap")){
					throw new Exception("Esta funcion no esta definida para el tipo " + dictValueClass_HKF);
				}
				try {
					HashMap<Object, Object> dictionary = (HashMap<Object, Object>)dictValue_HKF;
					Boolean result = dictionary.containsKey(argDictValue);
					return result;
					
				} catch(Exception e){
					throw new Exception("Error al aplicar la funcion has_key sobre " + dictValue_HKF);
				}
				
			case KEYS_FUNC:
				
				if(this.getLeft() == null){
					throw new Exception("Esta funcion no esta definida para este tipo");
				}
				
				Object dictValue_KF = this.getLeft().execute();
				String dictValueClass_KF = dictValue_KF.getClass().getSimpleName();
				if(!dictValueClass_KF.equals("HashMap")){
					throw new Exception("Esta funcion no esta definida para el tipo " + dictValueClass_KF);
				}
				try {
					HashMap<Object, Object> dictionary = (HashMap<Object, Object>)dictValue_KF;
					return dictionary.keySet();
					
				} catch(Exception e){
					throw new Exception("Error al aplicar la funcion has_key sobre " + dictValue_KF);
				}
			
			case ITEMS_FUNC:
				
				if(this.getLeft() == null){
					throw new Exception("Esta funcion no esta definida para este tipo");
				}
				
				Object dictValue_IF = this.getLeft().execute();
				String dictValueClass_IF = dictValue_IF.getClass().getSimpleName();
				if(!dictValueClass_IF.equals("HashMap")){
					throw new Exception("Esta funcion no esta definida para el tipo " + dictValueClass_IF);
				}
				try {
					HashMap<Object, Object> dictionary = (HashMap<Object, Object>)dictValue_IF;
					return dictionary.entrySet(); //tal vez deberiamos devolver listas con expressions de tuplas adentro
					/*Set<Map.Entry<Object,Object>> dictionaryElements = dictionary.entrySet();
					Iterator iter = dictionaryElements.iterator();
					while(iter.hasNext()){
						
					}*/
					
				} catch(Exception e){
					throw new Exception("Error al aplicar la funcion has_key sobre " + dictValue_IF);
				}
			
			case POP_FUNC:{
				
				if(this.getLeft() == null){
					throw new Exception("Esta funcion no esta definida para este tipo.");
				}
				if(this.getRight() == null){
					throw new Exception("Se esperaba 1 argumento para la funcion y se recibieron 0.");
				}
				
				Object dictValue_PF = this.getLeft().execute();
				Object argDictValue_PF = this.getRight().execute();
				
				String dictValueClass_PF = dictValue_PF.getClass().getSimpleName();
				if(!dictValueClass_PF.equals("HashMap")&&!dictValueClass_PF.equals("ArrayList")){
					throw new Exception("La funcion pop solo se encuentra disponible para los tipos Lista y Diccionario.");
				}
				if(!dictValueClass_PF.equals("HashMap")){//pop in a list
					List<Object> l = (List<Object>) dictValue_PF;
					int index=0;
					try{
						index = (int) argDictValue_PF;
					}catch(Exception e){
						throw new Exception("La función pop para Lista espera un entrero como parámetro.");
					}
					try{
						l.remove(index);
						return true;
					}catch(Exception e){
						throw new Exception("Se quizo hacer un pop sobre la lista "+this.getLeft().getValue()+" con un índice fuera de rango");
					}
				}
				else{//pop in dicc
					try {
						HashMap<Object, Object> dictionary = (HashMap<Object, Object>)dictValue_PF;
						dictionary.remove(argDictValue_PF);
						return true;
						
					} catch(Exception e){
						throw new Exception("Error al aplicar la funcion has_key sobre " + dictValue_PF);
					}
				}
			}
			case VALUES_FUNC:
				
				if(this.getLeft() == null){
					throw new Exception("Esta funcion no esta definida para este tipo");
				}
				
				Object dictValue_VF = this.getLeft().execute();
				String dictValueClass_VF = dictValue_VF.getClass().getSimpleName();
				if(!dictValueClass_VF.equals("HashMap")){
					throw new Exception("Esta funcion no esta definida para el tipo " + dictValueClass_VF);
				}
				try {
					HashMap<Object, Object> dictionary = (HashMap<Object, Object>)dictValue_VF;
					return dictionary.values();
					
				} catch(Exception e){
					throw new Exception("Error al aplicar la funcion has_key sobre " + dictValue_VF);
				}
				
			case FIND_FUNC:
				
				if(this.getLeft() == null){
					throw new Exception("Esta funcion no esta definida para este tipo");
				}
				if(this.getRight() == null){
					throw new Exception("Se esperaba 1 argumento para la funcion y se recibieron 0");
				}
				
				Object strValue = this.getLeft().execute();
				Object argStrValue, argIntValue = null;
				
				if(this.getRight().getLeft() != null){ //find with two arguments
					argStrValue = this.getRight().getLeft().execute();
					argIntValue = this.getRight().getRight().execute();
				} else {
					argStrValue = this.getRight().execute();
				}				
				
				String strValueClass = strValue.getClass().getSimpleName();
				String argValueClass = argStrValue.getClass().getSimpleName();
				String argIntClass = argIntValue != null ? argIntValue.getClass().getSimpleName() : null;
				
				if(!strValueClass.equals("String")){
					throw new Exception("Esta funcion no esta definida para el tipo " + strValueClass);
				}
				if(!argValueClass.equals("String")){
					throw new Exception("Se esperaba argumento de tipo string pero se recibio " + strValueClass);
				}
				if(argIntValue != null && !argIntClass.equals("Integer")){
					throw new Exception("Se esperaba argumento de tipo int pero se recibio " + argIntClass);
				}
				try {
					String str = String.valueOf(strValue);
					String argStr = String.valueOf(argStrValue).toString();
					if(argIntValue != null){
						Integer argInt = (Integer)argIntValue;
						return str.indexOf(argStr, argInt);
					}
					return str.indexOf(argStr);
					
	
				} catch(Exception e){
					throw new Exception("Error al aplicar la funcion find sobre " + strValue);
				}

			case PRINT_FUNC:
				if(this.getLeft() != null) {
					System.out.println(this.getLeft().execute());
				}
				return null;
				
			case COUNT_FUNC:
				if(left != null && right != null) {
					Object 	str = left.execute(),
							list = right.execute();
					
					String  leftClass = str.getClass().getSimpleName(),
							rightClass = list.getClass().getSimpleName();
					if(leftClass.equals("String")) {
						throw new Exception("La variable no es de tipo String");
					}
					else if(rightClass.equals("String")) {
						throw new Exception("El parametro de la funcion no es de tipo String");
					}
					else {
						int index 		= 0,
							occurrences = 0;
						String 	leftStr  = (String)left.execute(),
								rightStr = (String)right.execute();

						index = leftStr.indexOf(rightStr,0);
						
						while(index != -1) {
							occurrences++;
							index = leftStr.indexOf(rightStr,index);
						}
						
						return occurrences;
					}
				}
				
				break;
				
			case JOIN_FUNC:
				if(left != null && right != null) {
					Object 	str = left.execute(),
							list = right.execute();
					String  leftClass = str.getClass().getSimpleName(),
							rightClass = list.getClass().getSimpleName();

					
					if(!leftClass.equals("String")) {
						throw new Exception("La variable no es del tipo esperado 'String'. Se encontro: '"+leftClass+"'");
					}
					else if(!rightClass.equals("ArrayList")) {
						throw new Exception("El parametro recibido no es del tipo esperado 'List'. Se encontro: "+rightClass+"'");
					}
					else {
						Iterator<Object> iter 	= ((List<Object>)list).iterator();
						String result 			= "",
							   separator 		= (String)str; 
						Object element 			= null;
						
						if(iter.hasNext()) {
							// Case base (no separator strings should be included) //
							element = iter.next();
							result 	= (String)element;
							
							// Iterating through the list //
							while(iter.hasNext()) {
								element = iter.next();
								
								if(!element.getClass().getSimpleName().equals("String")){
									throw new Exception("Uno de los elementos de la lista no es un String");
								}
								
								result = result + separator + new String((String)element);
							}
						}
						
						System.out.println(result);
						return result;
					}
				}
				
				break;
			case SPLIT_FUNC:
				if(left != null && right != null) {
					Object 	str = left.execute(),
							sep = right.execute();
					
					if(!str.getClass().getSimpleName().equals("String")) {
						throw new Exception("La variable no es un String");
					}
					else if(!sep.getClass().getSimpleName().equals("String")) {
						throw new Exception("El parametro no es un String");
					}
					else {
						String[] 		  arr = ((String)str).split(Pattern.quote((String)sep));
						ArrayList<Object> list = new ArrayList();
						int i 		= 0,
							count 	= arr.length;
						
						
						while( i < count) {
							list.add(arr[i]);
							i++;
						}
						
						return list;
					}
				}
				
				break;
				
			case REPLACE_FUNC:
				if (left != null  && right != null ) {
					Object 	str 		= left.execute(),
							substrOld 	= right.getLeft().execute(),
							substrNew 	= right.getRight().execute();
					
					if(!str.getClass().getSimpleName().equals("String") ||
					   !substrOld.getClass().getSimpleName().equals("String") ||
					   !substrNew.getClass().getSimpleName().equals("String")) {
						throw new Exception("La variable y sus parametros deben ser de tipo String");
					}
					else {
						System.out.println(((String)str).replace((String)substrOld,(String)substrNew).toString());
						return ((String)str).replace((String)substrOld,(String)substrNew);
					}
				}
				break;
			case LENGTH_FUNC:
				if (left != null  && right == null ) {
					Object str = left.execute();
					if(!str.getClass().getSimpleName().equals("String")) {
						throw new Exception("La variable no es un string");
					}
					else {
						return ((String)str).length();
					}
				}
				break;
			default:
				//return super.getValue();
				throw new Exception("Tipo literal no reconocido");
		}
		
		throw new Exception("Alguno de los parametros de la funcion '"+this.getType()+"' no fueron encontrados");
	}
	
}
