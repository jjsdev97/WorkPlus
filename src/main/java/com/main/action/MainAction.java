package com.main.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.setAttribute("menu", "user");
		session.setAttribute("selectedmenu", "main");
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);

		forward.setPath("mainpage.jsp");
		return forward;
	}

}
