package com.language.model.expression;

import java.util.ArrayList;

public class Tuple<X> extends ArrayList<Object> {
		
	public final X x;
	
	public Tuple() { 
		this.x = null;
	}
	
	public Tuple(X x) { 
		this.x = x;
	}
	
	@Override
	public String toString(){
		ArrayList<Object> array = new ArrayList<Object>(this);
		String arrayString = array.toString();
		String tupleString = "(" + arrayString.substring(1, arrayString.length() - 1) + ")";
		return tupleString;
		
	}
	
}
