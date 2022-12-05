package com.vtlions.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthorizationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		session.getAttribute(Constants.USER_ACCESS_GRANTED_KEY);
		if (session.getAttribute(Constants.USER_ACCESS_GRANTED_KEY) == null) {
			((HttpServletResponse) response).sendRedirect(Constants.SERVLET_AUTHORIZATION_ERROR);
		} else {
			filterChain.doFilter(request, response);
		}
	}
}
