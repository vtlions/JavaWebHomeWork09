package com.vtlions.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.vtlions.model.Product;
import com.vtlions.repository.ProductRepository;
import com.vtlions.utils.DBOperator;

public class ProductController extends HttpServlet {

	private static final long serialVersionUID = -7481195927029654423L;
	private List<Product> products;
	private ProductRepository productRepository;
	private DBOperator dbOperator = new DBOperator();
	private Product pieceofproducts;

	@Override
	public void init() throws ServletException {
		productRepository = new ProductRepository(dbOperator);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String url = null;
		if (id != null) {
			pieceofproducts = productRepository.getProductById(id);
			request.setAttribute(Constants.PIECE_OF_RODUCTS_ATTRIBUTE, pieceofproducts);
			url = Constants.PIECE_OF_PRODUCTS_PAGE;
		} else {
			String category = request.getParameter(Constants.CATEGORY_ATTRIBUTE);
			products = productRepository.getAllProductsList(category);
			request.setAttribute(Constants.PRODUCTS_ATTRIBUTE, products);
			url = Constants.PRODUCTS_PAGE;
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
