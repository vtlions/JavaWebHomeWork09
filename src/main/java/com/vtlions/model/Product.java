package com.vtlions.model;

import java.util.Objects;

public class Product {
	private int id;
	private String product_name;
	private String product_description;
	private int product_price;
	private Category category;

	public int getId() {
		return id;
	}

	public Product setId(int id) {
		this.id = id;
		return this;
	}

	public String getProduct_name() {
		return product_name;
	}

	public Product setProduct_name(String product_name) {
		this.product_name = product_name;
		return this;
	}

	public String getProduct_description() {
		return product_description;
	}

	public Product setProduct_description(String product_description) {
		this.product_description = product_description;
		return this;
	}

	public int getProduct_price() {
		return product_price;
	}

	public Product setProduct_price(int product_price) {
		this.product_price = product_price;
		return this;
	}

	public Category getCategory() {
		return category;
	}

	public Product setCategory(Category category) {
		this.category = category;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", product_name=" + product_name + ", product_description=" + product_description
				+ ", product_price=" + product_price + ", category=" + category + "]";
	}
}
