package net.chat.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.chat.db.ChatBean;
import net.chat.db.ChatDAO;
import net.member.db.Member;

public class ChatListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.setAttribute("menu", "user"); // admin, user
		session.setAttribute("selectedmenu", "chatlist");

		
		
		
		
		
		ActionForward forward = new ActionForward();
		
		//나의 정보
		ChatDAO chatdao = new ChatDAO();
		String id = (String) session.getAttribute("id");
		System.out.println(id);
		Member m = chatdao.memberinfo(id);
		request.setAttribute("m", m);
		
		
		String f_id = request.getParameter("f_id");
        System.out.println("createRoom을 위한 f_id =" + f_id);
        
		
// 		chatdao.createRoom(id, f_id);
		
		
		//채팅방 리스트
		List <ChatBean> chatlist = new ArrayList<ChatBean>();
		request.setAttribute("chatlist", chatlist);
		
		forward.setRedirect(false);
		forward.setPath("chat/chatList.jsp");
		
		return forward;
	}

}
