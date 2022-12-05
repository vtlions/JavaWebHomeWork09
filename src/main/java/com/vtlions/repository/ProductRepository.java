package com.vtlions.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.vtlions.model.Category;
import com.vtlions.model.Product;
import com.vtlions.utils.DBOperator;

public class ProductRepository {

	private DBOperator dbOperator;
	private Connection connection;
	private PreparedStatement statement;
	private static final String GET_ALL_PRODUCTS = "SELECT products.product_id , products.product_name , products.product_description, products.product_price, categories.category_id, categories.category_name FROM products JOIN categories ON products.category=categories.category_id ORDER BY product_id";
	private static final String GET_PRODUCTS_BY_CATEGORY = "SELECT products.product_id , products.product_name , products.product_description, products.product_price, categories.category_id, categories.category_name FROM products JOIN categories ON products.category=categories.category_id WHERE products.category=?";
	private static final String GET_PRODUCT_BY_ID = "SELECT products.product_id , products.product_name , products.product_description, products.product_price, categories.category_id, categories.category_name FROM products JOIN categories ON products.category=categories.category_id WHERE products.product_id=?";
	private final int QUANTITY_OF_CATEGORIES = 3;
	private final String PRODUCT_ID = "product_id";
	private final String PRODUCT_NAME = "product_name";
	private final String PRODUCT_DESCRIPTION = "product_description";
	private final String PRODUCT_PRICE = "product_price";
	private final String CATEGORY_ID = "category_id";
	private final String CATEGORY_NAME = "category_name";

	public ProductRepository(DBOperator dbOperator) {
		this.dbOperator = dbOperator;
	}

	public List<Product> getAllProductsList(String category) {

		connection = dbOperator.getConnection();
		List<Product> products = new ArrayList<>();
		String quiery = GET_ALL_PRODUCTS;
		try {
			if (category != null && Integer.parseInt(category) > 0
					&& Integer.parseInt(category) <= QUANTITY_OF_CATEGORIES) {
				quiery = GET_PRODUCTS_BY_CATEGORY;
				statement = connection.prepareStatement(quiery);
				statement.setInt(1, Integer.parseInt(category));
			} else {
				statement = connection.prepareStatement(quiery);
			}
			statement.execute(DBOperator.USE_DATABASE);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				products.add(new Product().setId(result.getInt(PRODUCT_ID))
						.setProduct_name(result.getString(PRODUCT_NAME))
						.setProduct_description(result.getString(PRODUCT_DESCRIPTION))
						.setProduct_price(result.getInt(PRODUCT_PRICE))
						.setCategory(new Category().setId(result.getInt(CATEGORY_ID))
								.setCategory_name(result.getString(CATEGORY_NAME))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	public Product getProductById(String id) {

		ResultSet result;
		connection = dbOperator.getConnection();
		Product product = null;
		if (connection != null) {
			try {
				statement = connection.prepareStatement(GET_PRODUCT_BY_ID);
				statement.setInt(1, Integer.parseInt(id));
				statement.execute(DBOperator.USE_DATABASE);
				result = statement.executeQuery();
				while (result.next()) {
					product = new Product().setId(result.getInt(PRODUCT_ID))
							.setProduct_name(result.getString(PRODUCT_NAME))
							.setProduct_description(result.getString(PRODUCT_DESCRIPTION))
							.setProduct_price(result.getInt(PRODUCT_PRICE))
							.setCategory(new Category().setId(result.getInt(CATEGORY_ID))
									.setCategory_name(result.getString(CATEGORY_NAME)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return product;
	}
}
