package net.chat.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChatMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.setAttribute("menu","user");
		
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		// chatmain
		forward.setPath("chat/chatmain.jsp");
		return forward;
	}

}
