package com.ruchi.logic;

import java.io.Serializable;
import java.util.List;

public class TypeAhead implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private List<String> local;
	public TypeAhead(String name, List<String> local) {
		this.name = name;
		this.local = local;
	}
	public String getName() {
		return name;
	}
	public List<String> getLocal() {
		return local;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLocal(List<String> local) {
		this.local = local;
	}
	
}
