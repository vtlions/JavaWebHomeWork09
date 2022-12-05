package com.vtlions.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import com.vtlions.controller.Encoding;
import com.vtlions.model.Product;
import com.vtlions.model.User;

public class DBOperator {

	private Connection connection = null;
	private ResultSet resultSet = null;
	private PreparedStatement statement = null;
	private String userName;
	private String loginFromDB;
	private final String LINKTODB = "jdbc:mysql://localhost/?user=root&password=";
	public static final String SELECT = "SELECT login, name FROM credentials WHERE login = ? AND password = ?";
	public static final String CREATE_DATABASE = "CREATE DATABASE IF NOT EXISTS web;";
	public static final String USE_DATABASE = "USE web";
	public static final String CREATE_TABLE_CREDENTIALS = "CREATE TABLE IF NOT EXISTS credentials (id INT PRIMARY KEY AUTO_INCREMENT, "
			+ "login VARCHAR(255), password VARCHAR(512), name VARCHAR(255), region VARCHAR(40), gender VARCHAR(1), comment VARCHAR(255));";
	public static final String INSERT_INTO_CREDENTIALS = "INSERT INTO credentials (login, password, name,region, gender,comment ) VALUES ('%s','%s','%s','%s','%s','%s')";
	public static final String CREATE_TABLE_ORDERS = "CREATE TABLE IF NOT EXISTS orders (order_number INT PRIMARY KEY AUTO_INCREMENT, user_login VARCHAR(255), user_name VARCHAR(255), total_sum FLOAT, purchase_date DATE)";
	public static final String INSERT_INTO_ORDERS = "INSERT INTO orders (user_login, user_name, total_sum, purchase_date ) VALUES ('%s','%s','%s','%s')";
	public static final String GET_ORDER_NUMBER = "SELECT COUNT(*) AS order_number FROM orders";
	public static final String CREATE_TABLE_PURCHASE_PRODUCTS_ORDER = "CREATE TABLE IF NOT EXISTS purchase_products_order_%d (id INT PRIMARY KEY AUTO_INCREMENT, product_name VARCHAR(255), product_price INT,  product_quantity INT, order_number INT, FOREIGN KEY (order_number) REFERENCES orders (order_number))";
	public static final String INSERT_INTO_PURCHASE_PRODUCTS_ORDER = "INSERT INTO purchase_products_order_%d (product_name, product_price, product_quantity,order_number) VALUES ('%s','%d','%d','%d')";

	public DBOperator() {

		GetConnectionToDB getConnection = new GetConnectionToDB(LINKTODB);
		connection = getConnection.getConnectionToDB();
		if (connection != null) {
			try {
				statement = connection.prepareStatement(SELECT);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("The connection to Database is null. Is your database server online?");
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void saveUserToDB(String login, String password, String name, String region, String gender, String comment)
			throws SQLException {

		statement.execute(CREATE_DATABASE);
		statement.execute(USE_DATABASE);
		statement.execute(CREATE_TABLE_CREDENTIALS);
		statement.execute(String.format(INSERT_INTO_CREDENTIALS, login, password, name, region, gender, comment));
		closeResources();
	}

	public boolean checkUserCredentials(String login, String password) throws SQLException {

		statement = connection.prepareStatement(SELECT);
		statement.setString(1, login);
		statement.setString(2, Encoding.sha256Encoding(password));
		statement.execute(USE_DATABASE);
		resultSet = statement.executeQuery();
		boolean isCredentialsFound = false;
		while (resultSet.next()) {
			loginFromDB = resultSet.getString("login");
			userName = resultSet.getString("name");
			if (Objects.equals(loginFromDB, login)) {
				isCredentialsFound = true;
				break;
			}
		}
		closeResources();
		return isCredentialsFound;
	}

	private void closeResources() {
		try {
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public User getUser() {
		User user = new User();
		user.setName(userName);
		user.setLogin(loginFromDB);
		return user;
	}

	public void saveCartIntoDB(final Map<Product, Integer> cart, double totalSum, User user) throws SQLException {

		int orderNumber = 0;
		statement.execute(CREATE_DATABASE);
		statement.execute(USE_DATABASE);
		statement.execute(CREATE_TABLE_ORDERS);
		statement.execute(String.format(INSERT_INTO_ORDERS, user.getLogin(), user.getName(), totalSum,
				LocalDateTime.now().toLocalDate()));
		resultSet = statement.executeQuery(GET_ORDER_NUMBER);
		while (resultSet.next()) {
			orderNumber = resultSet.getInt("order_number");
		}
		statement.execute(String.format(CREATE_TABLE_PURCHASE_PRODUCTS_ORDER, orderNumber));
		Product product = null;
		for (Map.Entry entry : cart.entrySet()) {
			product = (Product) entry.getKey();
			statement.execute(String.format(INSERT_INTO_PURCHASE_PRODUCTS_ORDER, orderNumber, product.getProduct_name(),
					product.getProduct_price(), entry.getValue(), orderNumber));
		}
	}
}
