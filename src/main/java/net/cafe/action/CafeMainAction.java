package net.cafe.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CafeMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
	    session.setAttribute("menu", "user");
	    session.setAttribute("selectedmenu", "cafemain");
		
		ActionForward forward = new ActionForward();
		forward.setPath("cafe/main.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}
