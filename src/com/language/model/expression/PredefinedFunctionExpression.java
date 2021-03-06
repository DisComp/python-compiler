package com.language.model.expression;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
	public static final String ARGUMENTS_FUNC_LIST = "ArgumentsFuncList";
	
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
		String tipo =expr.getType();
		expr.setType(ARGUMENTS_FUNC_LIST);
		return new PredefinedFunctionExpression(tipo, list_left, expr);
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
		
		Object 		leftObj = null, 
					rightObj = null;
		
		String 		leftClass = "", 
					rightClass = "";
		
		if(left != null){
			leftObj 	= left.execute();
			if(leftObj != null){
				leftClass 	= leftObj.getClass().getSimpleName();
			} else {
				leftClass = "None";
			}			
		}

		if(right != null) {
			rightObj 	= right.execute();
			if(rightObj != null) {
				rightClass 	= rightObj.getClass().getSimpleName();
			}
		}
		switch(this.getType()){
		case ARGUMENTS_FUNC_LIST:{
			return null;
			}
			case INSERT_FUNC:{
				Object lObj = this.getLeft().execute();
				Object ObjParamToFind = this.getRight().getRight().execute();//any value expeted
				String dictValueClass = lObj.getClass().getSimpleName();
				if(!dictValueClass.equals("ArrayList")){
					throw new Exception("La funci�n insert solo est� definida para el tipo list");
				}
				int index = 0;
				if(this.getRight().getLeft()!=null){//exist second parameter
					try{
						index = (int) this.getRight().getLeft().execute();
					}catch(Exception e){
						throw new Exception("La funci�n insert espera un entrero como segundo par�metro");
					}
				}			
				List<Object> l = (List<Object>) lObj;
				try{
					l.add(index, ObjParamToFind);
				}
				catch (Exception e){
					throw new Exception("Se quizo insertar un elemento en la lista "+this.getLeft().getValue()+" con un �ndice fuera de rango");
				}
				
				return l;
			}
			case INDEX_FUNC:{
				Object lObj = this.getLeft().execute();
				Object ObjParamToFind = this.getRight().getLeft().execute();//any value expeted
				String dictValueClass = lObj.getClass().getSimpleName();
				if(!dictValueClass.equals("ArrayList")){
					throw new Exception("La funci�n index solo est� definida para el tipo List.");
				}
				int start = 0;
				if(this.getRight().getRight()!=null){//exist second parameter
					try{
						start = (int) this.getRight().getRight().execute();
					}catch(Exception e){
						throw new Exception("La funci�n index espera un entrero como segundo par�metro.");
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
					throw new Exception("Se esperaba un par�metro de tipo List");
				}
				dictValueClass = lObj.getClass().getSimpleName();
				if(!dictValueClass.equals("ArrayList")){
					throw new Exception("La funci�n extend solo est� definida para el tipo Lista.");
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
					throw new Exception("La funci�n append solo est� definida para el tipo Lista.");
				}
				List<Object> l = (List<Object>) lObj;
				l.add(this.getRight().getLeft().execute());
				return l;
			}
			case SIZE_FUNC:{
				Object lObj = this.getLeft().execute();
				String dictValueClass = lObj.getClass().getSimpleName();
				if(!dictValueClass.equals("ArrayList")){
					throw new Exception("La funci�n append solo est� definida para el tipo Lista.");
				}
				List<Object> l = (List<Object>) lObj;
				return l.size();
			}
	
			
			case HAS_KEY_FUNC:{
				
				if(this.getLeft() == null){
					throw new Exception("Esta funcion no esta definida para este tipo.");
				}
				if(this.getRight() == null){
					throw new Exception("Se esperaba 1 argumento para la funcion y se recibieron 0.");
				}
				
				Object dictValue = this.getLeft().execute();
				Object argDictValue = this.getRight().execute();
				
				String dictValueClass = dictValue.getClass().getSimpleName();
				if(!dictValueClass.equals("HashMap")){
					throw new Exception("Esta funcion no esta definida para el tipo especificado");
				}
				try {
					HashMap<Object, Object> dictionary = (HashMap<Object, Object>)dictValue;
					Boolean result = dictionary.containsKey(argDictValue);
					return result;
					
				} catch(Exception e){
					throw new Exception("Error al aplicar la funcion has_key sobre " + dictValue);
				}
			}
			
			case KEYS_FUNC:{
				
				if(this.getLeft() == null){
					throw new Exception("Esta funcion no esta definida para este tipo");
				}
				
				Object dictValue = this.getLeft().execute();
				String dictValueClass = dictValue.getClass().getSimpleName();
				if(!dictValueClass.equals("HashMap")){
					throw new Exception("Esta funcion no esta definida para el tipo especificado");
				}
				try {
					HashMap<Object, Object> dictionary = (HashMap<Object, Object>)dictValue;
					return dictionary.keySet();
					
				} catch(Exception e){
					throw new Exception("Error al aplicar la funcion keys sobre " + dictValue);
				}
			}
			
			case ITEMS_FUNC:{
				
				if(this.getLeft() == null){
					throw new Exception("Esta funcion no esta definida para este tipo");
				}
				
				Object dictValue = this.getLeft().execute();
				String dictValueClass = dictValue.getClass().getSimpleName();
				if(!dictValueClass.equals("HashMap")){
					throw new Exception("Esta funcion no esta definida para el tipo especificado");
				}
				try {
					HashMap<Object, Object> dictionary = (HashMap<Object, Object>)dictValue;
					List<Object> resultList = new ArrayList<Object>();
					Set<Object> keys = dictionary.keySet();
					Iterator iter = keys.iterator();
					while(iter.hasNext()){
						Object key = iter.next();
						Object value = dictionary.get(key);
						Tuple t = new Tuple();
						t.add(key);
						t.add(value);
						resultList.add(t);						
					}
					return resultList;
					
				} catch(Exception e){
					throw new Exception("Error al aplicar la funcion items sobre " + dictValue);
				}
			}
			
			case POP_FUNC:{
				
				if(this.getLeft() == null){
					throw new Exception("Esta funcion no esta definida para este tipo.");
				}
				if(this.getRight() == null){
					throw new Exception("Se esperaba 1 argumento para la funcion y se recibieron 0.");
				}
				
				Object dictValue = this.getLeft().execute();
				Object argDictValue = this.getRight().execute();
				
				String dictValueClass = dictValue.getClass().getSimpleName();
				if(!dictValueClass.equals("HashMap")&&!dictValueClass.equals("ArrayList")){
					throw new Exception("La funcion pop solo se encuentra disponible para los tipos Lista y Diccionario.");
				}
				if(!dictValueClass.equals("HashMap")){//pop in a list
					List<Object> l = (List<Object>) dictValue;
					int index=0;
					try{
						index = (int) argDictValue;
					}catch(Exception e){
						throw new Exception("La funci�n pop para Lista espera un entrero como par�metro.");
					}
					try{
						l.remove(index);
						return true;
					}catch(Exception e){
						throw new Exception("Se quizo hacer un pop sobre la lista "+this.getLeft().getValue()+" con un �ndice fuera de rango");
					}
				}
				else{//pop in dicc
					try {
						HashMap<Object, Object> dictionary = (HashMap<Object, Object>)dictValue;
						dictionary.remove(argDictValue);
						return true;
						
					} catch(Exception e){
						throw new Exception("Error al aplicar la funcion pop sobre " + dictValue);
					}
				}
			}
			case VALUES_FUNC:{
				
				if(this.getLeft() == null){
					throw new Exception("Esta funcion no esta definida para este tipo");
				}
				
				Object dictValue = this.getLeft().execute();
				String dictValueClass = dictValue.getClass().getSimpleName();
				if(!dictValueClass.equals("HashMap")){
					throw new Exception("Esta funcion no esta definida para el tipo especificado");
				}
				try {
					HashMap<Object, Object> dictionary = (HashMap<Object, Object>)dictValue;
					return dictionary.values();
					
				} catch(Exception e){
					throw new Exception("Error al aplicar la funcion values sobre " + dictValue);
				}
			}	
			case FIND_FUNC:{
				
				if(this.getLeft() == null){
					throw new Exception("Esta funcion no esta definida para este tipo");
				}
				if(this.getRight() == null){
					throw new Exception("Se esperaba 1 argumento para la funcion y se recibieron 0");
				}
				
				Object strValue = leftObj;
				Object argStrValue, argIntValue = null;
				
				if(right.getLeft() != null){ //find with two arguments
					argStrValue = right.getLeft().execute();
					argIntValue = right.getRight().execute();
				} else {
					argStrValue = right.execute();
				}				
				
				String strValueClass = leftClass;
				String argValueClass = argStrValue.getClass().getSimpleName();
				String argIntClass = argIntValue != null ? argIntValue.getClass().getSimpleName() : null;
				
				if(!strValueClass.equals("String")){
					throw new Exception("Esta funcion no esta definida para el tipo especificado");
				}
				if(!argValueClass.equals("String")){
					throw new Exception("Se esperaba argumento de tipo string");
				}
				if(argIntValue != null && !argIntClass.equals("Integer")){
					throw new Exception("Se esperaba argumento de tipo int");
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
			}
			case PRINT_FUNC:{
				if(this.getLeft() != null) {
					Object result = this.getLeft().execute();					
					if(result != null){
						System.out.println(result);
					} else {
						System.out.println("None");
					}
				}
				return null;
			}	
			case COUNT_FUNC:{
				if(left != null && right != null) {
					
					if(leftClass.equals("ArrayList")) {
						//count for lists
						List<Object> list = (List<Object>)leftObj;
						Object l = (Object)rightObj;
						
						return Collections.frequency(list,l);
					}
					else if(!leftClass.equals("String")) {
						throw new Exception("La variable no es de tipo String");
					}
					else if(!rightClass.equals("String")) {
						throw new Exception("El parametro de la funcion no es de tipo String "+rightClass);
					}
					else {
						int index 		= 0,
							occurrences = 0;
						String 	leftStr  = (String)leftObj,
								rightStr = (String)rightObj;

						index = leftStr.indexOf(rightStr,0);
						
						while(index != -1) {
							occurrences++;
							index = leftStr.indexOf(rightStr,index + 1);
						}
						
						return occurrences;
					}
				}
				
			}
			case JOIN_FUNC:
				if(left != null && right != null) {
					
					if(!leftClass.equals("String")) {
						throw new Exception("La variable no es del tipo esperado 'string'");
					}
					else if(!rightClass.equals("ArrayList")) {
						throw new Exception("El parametro recibido no es del tipo esperado 'list'");
					}
					else {
						Iterator<Object> iter 	= ((List<Object>)rightObj).iterator();
						String result 			= "",
							   separator 		= (String)leftObj; 
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
						
						return result;
					}
				}
				
				break;
			case SPLIT_FUNC:
				if(left != null && right != null) {
					
					if(!leftClass.equals("String")) {
						throw new Exception("La variable no es un String");
					}
					else if(!rightClass.equals("String")) {
						throw new Exception("El parametro no es un String");
					}
					else {
						String[] 		  arr = ((String)leftObj).split(Pattern.quote((String)rightObj));
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
						//System.out.println(((String)str).replace((String)substrOld,(String)substrNew).toString());
						String result = ((String)str).replace((String)substrOld,(String)substrNew);
						ScopesController.getInstance().addVariable((String)left.getValue(), result);
						return result;
					}
				}
				break;
			case LENGTH_FUNC:
				if (left != null  && right == null ) {
					if(!leftClass.equals("String")) {
						throw new Exception("La variable no es un string");
					}
					else {
						return ((String)leftObj).length();
					}
				}
				
				break;
			case INT_FUNC:
				if (left != null && right == null) {
					
					switch(leftClass) {
						case "String":
							String str = (String)leftObj;
							try {
								return Integer.parseInt(str);
							}
							catch(Exception e) {
								throw new Exception("No se pudo convertir de 'String' a 'Integer'. Compruebe que la variable este bien formada");
							}
							
						case "Integer":
							return (int)leftObj;
							
						case "Double":
						case "Float":
							Double f = (Double)leftObj;
							return Math.round(f);
							
						case "Boolean":
							Boolean b = (boolean)leftObj;
							if(b) 
								return 1;
							else
								return 0;
							
						default:
							throw new Exception("El tipo " + leftClass + " no se puede convertir a 'Integer'");
					}
				}
				
				break;
				
			case FLOAT_FUNC:
				if (left != null && right == null) {
					
					switch(leftClass) {
						case "String":
							String str = (String)leftObj;
							try {
								return Double.parseDouble(str);
							}
							catch(Exception e) {
								throw new Exception("No se pudo convertir de 'String' a 'Float'. Compruebe que la variable este bien formada");
							}
							
						case "Integer":
							Integer i = (int)leftObj;
							Double d = (Double)(i * 1.0);
							return d;
							
						case "Double":
							return (Double)leftObj;
							
						default:
							throw new Exception("El tipo " + leftClass + " no se puede convertir a 'Float'");
					}
				}
				
				break;
			case STR_FUNC:
				if (left != null && right == null) {
					
					switch(leftClass) {
						case "String":
							return (String)leftObj;
							
						case "Integer":
							return String.valueOf((int)leftObj);
						
						case "Double":
							return String.valueOf((double)leftObj);
							
						case "Boolean":
							Boolean b = (boolean)leftObj;
							if(b)
								return "True";
							else
								return "False";
						
						case "ArrayList":
							ArrayList<Object> list = (ArrayList<Object>)leftObj;
							
							try {
								return list.toString();
							}
							catch(Exception e) {
								throw new Exception("No se pudo convertir la lista en 'String'");
							}
							
						default:
							throw new Exception("El tipo " + leftClass + " no se puede convertir a 'String'");
					}
				}
				
				break;
			case LIST_FUNC:
				if(left != null && right == null) {
					
					switch(leftClass) {
						case "String":
							String[] 		  arr = ((String)leftObj).split(",");
							ArrayList<Object> list = new ArrayList();
							int i 		= 0,
								count 	= arr.length;
							
							while( i < count) {
								list.add(arr[i]);
								i++;
							}
							
							return list;
						
						default:
							throw new Exception("El tipo " + leftClass + " no se puede convertir a 'List'");
					}
				}
				
			case DICT_FUNC:
				
				if(left != null && right == null) {
					switch(leftClass) {
						case "ArrayList":
							ArrayList<Object> list 		= (ArrayList)leftObj;
							Iterator<Object> iter 		= list.iterator();
							HashMap<Object,Object> map 	= new HashMap<Object,Object>();
							
							Object element 	= null;
							int i 			= 0;
							
							
							try {
								while( iter.hasNext()) {
									element = iter.next();
									map.put(i, element);
									
									i++;
								}
							}
							catch(Exception e) {
								throw new Exception("Error al insertar en el diccionario");
							}
							
							return map;
						
						default:
							throw new Exception("El tipo " + leftClass + " no se puede convertir a 'Dictionary'");
					}
				}
			case RAW_INPUT_FUNC:{
				Object rawObj = this.getLeft().execute();
				System.out.print(rawObj);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
				String input = bufferedReader.readLine();
				return input;
			}
			case TYPE_FUNC:{
				Object element = this.getLeft().execute();
				String className = element.getClass().getSimpleName();
				switch(className){
					case "Integer":
						return "int";
					case "Boolean":
						return "bool";
					case "Double":
						return "real";
					case "ArrayList":
						return "list";
					case "HashMap":
						return "dicctionary";
					default:
						return className.toLowerCase();
				}
			}
			
			default:
				//return super.getValue();
				throw new Exception("Tipo literal no reconocido");
		}
		
		throw new Exception("Alguno de los parametros de la funcion no fueron encontrados");
	}
	
}
