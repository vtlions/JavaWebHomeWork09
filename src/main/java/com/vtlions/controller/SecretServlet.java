package com.vtlions.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecretServlet extends HttpServlet {

	private static final long serialVersionUID = -6133996855302631136L;

	private RequestDispatcher dispatcher = null;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		writeMessage(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		writeMessage(request, response);
	}

	private void writeMessage(HttpServletRequest request, HttpServletResponse response) {

		dispatcher = request.getRequestDispatcher(Constants.SECRET_PAGE);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
