package com.vtlions.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.vtlions.model.Product;
import com.vtlions.model.User;
import com.vtlions.repository.ProductRepository;
import com.vtlions.utils.DBOperator;

public class CartController extends HttpServlet {

	private static final long serialVersionUID = 7592699679752304995L;
	private ProductRepository productRepository;
	private DBOperator dbOperator;

	public CartController() {
	}

	@Override
	public void init() throws ServletException {
		dbOperator = new DBOperator();
		productRepository = new ProductRepository(dbOperator);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher;
		String key = request.getParameter("key");
		String buy = request.getParameter("buy");
		String piece = request.getParameter("piece");
		Map<Product, Integer> cart = null;
		HttpSession session = request.getSession();
		if (key != null) {
			dispatcher = request.getRequestDispatcher(Constants.CART_PAGE);
			dispatcher.forward(request, response);
		} else if (buy != null) { // block will be executed if user presses button "Купити"
			cart = (Map<Product, Integer>) session.getAttribute(Constants.CART_ATTRIBUTE);
			double totalSum = Double.parseDouble(request.getParameter("totalsum"));
			User user = (User) session.getAttribute("user");
			if (user == null) {
				dispatcher = request.getRequestDispatcher(Constants.LOGIN_FORM_PAGE);
				request.setAttribute("message",
						"Будь ласка, для оформлення замовлення, зайдіть в особистий кабінет або зареєструйтеся");
				dispatcher.forward(request, response);
			}
			if (cart != null) {
				try {
					dbOperator.saveCartIntoDB(cart, totalSum, user);
					session.setAttribute(Constants.CART_ATTRIBUTE, null);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			dispatcher = request.getRequestDispatcher(Constants.SUCCESS_PURCHASE_PAGE);
			dispatcher.forward(request, response);
		} else {
			String id = request.getParameter("id");
			String quantity = request.getParameter("quantity");
			int quantityOfTheSameProduct = 0;
			if (quantity != null) {
				quantityOfTheSameProduct = Integer.parseInt(quantity);
			}
			if (session.getAttribute(Constants.CART_ATTRIBUTE) == null) {
				session.setAttribute(Constants.CART_ATTRIBUTE, new HashMap<Product, Integer>());
			}
			cart = (Map<Product, Integer>) session.getAttribute(Constants.CART_ATTRIBUTE);
			Product product = productRepository.getProductById(id);
			if (cart.containsKey(product)) {
				quantityOfTheSameProduct = cart.get(product) + 1;
			}
			cart.put(product, quantityOfTheSameProduct);
			session.setAttribute(Constants.CART_ATTRIBUTE, cart);
			String url;
			if (piece != null) {
				url = "productcontroller";
			} else {
				url = request.getHeader("Referer");
			}
			response.sendRedirect(url);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
