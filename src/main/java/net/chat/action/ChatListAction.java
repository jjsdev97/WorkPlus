package net.chat.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChatListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.setAttribute("menu", "user"); // admin, user
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		//chatList
		forward.setPath("chat/chatList.jsp");
		return forward;
	}

}
