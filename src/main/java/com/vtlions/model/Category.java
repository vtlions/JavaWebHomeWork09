package com.vtlions.model;

public class Category {
	private int id;
	private String category_name;

	public Category() {
	}

	public int getId() {
		return id;
	}

	public Category setId(int id) {
		this.id = id;
		return this;
	}

	public String getCategory_name() {
		return category_name;
	}

	public Category setCategory_name(String category_name) {
		this.category_name = category_name;
		return this;
	}
}
