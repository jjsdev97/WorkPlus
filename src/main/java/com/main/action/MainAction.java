package com.main.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		String menu = (String) session.getAttribute("selectedmenu");

		if (menu.equals("memberjoin")) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("location.href='login.et';");
			out.println("</script>");
			out.close();
		}

		session.setAttribute("menu", "user");
		session.setAttribute("selectedmenu", "main");

		forward.setRedirect(false);
		forward.setPath("mainpage.jsp");

		return forward;
	}

}
