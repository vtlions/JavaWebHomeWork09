package com.vtlions.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.vtlions.utils.DBOperator;

public class RegistrationController extends HttpServlet {

	private static final long serialVersionUID = -8843749250963557837L;
	private String login;
	private String password;
	private String rePassword;
	private String fullName;
	private String region;
	private String gender;
	private String comment;
	private String agreement;
	private boolean isError;
	private RequestDispatcher dispatcher = null;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.getAttribute(Constants.USER_ACCESS_GRANTED_KEY);
		if (session.getAttribute(Constants.USER_ACCESS_GRANTED_KEY) == null) {
			getDataFromRequest(request);
			createForm(request, response, "");
		} else {
			String url = Constants.ALREADY_AUTHORIZED_PAGE;
			dispatcher = request.getRequestDispatcher(url);
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		getDataFromRequest(request);
		String errors = checkData();
		if (isError) {
			createForm(request, response, errors);
		} else {
			String url = Constants.SUCCESSFUL_REGISTRATION_PAGE;
			dispatcher = request.getRequestDispatcher(url);
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				DBOperator dbOperator = new DBOperator();
				dbOperator.saveUserToDB(login, Encoding.sha256Encoding(password), fullName, region, gender, comment);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void createForm(HttpServletRequest request, HttpServletResponse response, String errors) {

		request.setAttribute("login", login != null ? login : "");
		request.setAttribute("fullName", fullName != null ? fullName : "");
		request.setAttribute("lviv", "Lviv".equals(region) ? "selected" : "");
		request.setAttribute("kyiv", "Kyiv".equals(region) ? "selected" : "");
		request.setAttribute("kharkiv", "Kharkiv".equals(region) ? "selected" : "");
		request.setAttribute("f", "F".equals(gender) ? "checked" : "");
		request.setAttribute("m", "M".equals(gender) ? "checked" : "");
		request.setAttribute("comment", comment != null ? comment : "");
		request.setAttribute("errors", isError ? ("<td width='300'>" + errors + "</td>") : "");
		dispatcher = request.getRequestDispatcher(Constants.REGISTRATION_PAGE);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String checkData() {

		StringBuilder errorText = new StringBuilder("<ul>");
		isError = false;
		if (login == null || login.isEmpty()) {
			isError = true;
			errorText.append("<li>Не введено логін</li>");
		} else {
			if (!Pattern.matches("^(.+)@(.+)$", login)) {
				isError = true;
				errorText.append("<li>Некоректний логін: має бути корректна адреса Email</li>");
			}
		}
		if (password == null || password.isEmpty()) {
			isError = true;
			errorText.append("<li>Пустий пароль</li>");
		} else {
			if (!Pattern.matches("(?=.*[A-Z])(?=.*[a-z])(?=(.*\\d){2})[A-Za-z\\d]{8,}", password)) {
				isError = true;
				errorText.append(
						"<li>Занадто простий пароль: має містити маленькі та великі літери, мінімум 2 цифри, мінімальна довжина 8 символів</li>");
			}
		}
		if (rePassword == null || rePassword.isEmpty()) {
			isError = true;
			errorText.append("<li>Не введено перевірку пароля</li>");
		} else {
			if (!rePassword.equals(password)) {
				isError = true;
				errorText.append("<li>Пасворди не збігаються</li>");
			}
		}
		if (fullName == null || fullName.isEmpty()) {
			isError = true;
			errorText.append("<li>Не введено ім'я користувача</li>");
		}
		if (region == null || region.isEmpty()) {
			isError = true;
			errorText.append("<li>Не вибрано регіон</li>");
		} else {
			if (!("Lviv".equals(region) || "Kyiv".equals(region) || "Kharkiv".equals(region))) {
				isError = true;
				errorText.append("<li>Виберіть корректний регіон</li>");
			}
		}
		if (gender == null || gender.isEmpty()) {
			isError = true;
			errorText.append("<li>Не вибрано стать</li>");
		} else {
			if (!("M".equals(gender) || "F".equals(gender))) {
				isError = true;
				errorText.append("<li>Виберіть коректну стать</li>");
			}
		}
		if (agreement == null || agreement.isEmpty()) {
			isError = true;
			errorText.append("<li>Для реєстрації потрібно погодитися з умовами сайту</li>");
		}
		errorText.append("</ul>");
		errorText.trimToSize();
		return errorText.toString();
	}

	private void getDataFromRequest(HttpServletRequest request) {
		isError = false;
		login = request.getParameter("login");
		password = request.getParameter("password");
		rePassword = request.getParameter("rePassword");
		fullName = request.getParameter("fullName");
		region = request.getParameter("region");
		gender = request.getParameter("gender");
		comment = request.getParameter("comment");
		agreement = request.getParameter("agreement");
	}
}
