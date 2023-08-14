package net.chat.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.chat.db.ChatDAO;
import net.member.db.Member;

public class ChatStatusUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		
		
		ChatDAO chatdao = new ChatDAO();
		Member m = chatdao.memberStatusUpdate(id, request.getParameter("status"));
		
		ActionForward forward = new ActionForward();
		forward.setPath("Chatmain.chat");
		forward.setRedirect(false);
		request.setAttribute("m", m);
		
		return forward;
	}

}
