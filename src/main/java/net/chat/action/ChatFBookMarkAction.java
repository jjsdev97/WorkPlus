package net.chat.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.chat.db.ChatDAO;
import net.member.db.Member;

public class ChatFBookMarkAction implements Action {

	
	
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ChatDAO chatdao = new ChatDAO();
		Member m = chatdao.memberinfo(id);
		
		// URL 매개변수에서 f_id 값을 읽어옴
        String f_id = request.getParameter("f_id");
        System.out.println("bookmark를 위한 f_id =" + f_id);
        
//        Member m = chatdao.addFBookMark(id, f_id);
		
		ActionForward forward = new ActionForward();
		forward.setPath("Chatmain.chat");
		forward.setRedirect(false);
		request.setAttribute("m", m);
		
		return forward;
	}

}
