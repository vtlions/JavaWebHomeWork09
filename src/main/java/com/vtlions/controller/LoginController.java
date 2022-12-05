package com.vtlions.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.vtlions.utils.DBOperator;

public class LoginController extends HttpServlet {

	private static final long serialVersionUID = -9071757192018689176L;
	private RequestDispatcher dispatcher = null;
	private DBOperator dbOperator;
	private final String ACCESS_DENIED_MESSAGE = "Нажаль, ми не знайшли користувача з такими логіном та паролем";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.getAttribute(Constants.USER_ACCESS_GRANTED_KEY);
		String key = request.getParameter("key");
		if (key != null) {
			session.invalidate();
		}
		String url = Constants.LOGIN_PAGE;
		session = request.getSession(true);
		if (session.getAttribute(Constants.USER_ACCESS_GRANTED_KEY) != null) {
			request.setAttribute(Constants.USER_ATTRIBUTE, dbOperator.getUser());
			url = Constants.LOGIN_SUCCESS_PAGE;
		}
		dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter(Constants.LOGIN_PARAMETER);
		String password = request.getParameter(Constants.PASSWORD_PARAMETER);
		dbOperator = new DBOperator();
		if (login != null && password != null) {
			try {
				if (dbOperator.checkUserCredentials(login, password)) {
					HttpSession session = request.getSession();
					session.setAttribute(Constants.USER_ACCESS_GRANTED_KEY, dbOperator.getUser());
					RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.LOGIN_SUCCESS_PAGE);
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("message", ACCESS_DENIED_MESSAGE);
					RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.LOGIN_PAGE);
					dispatcher.forward(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
