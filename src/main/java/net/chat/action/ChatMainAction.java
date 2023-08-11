package net.chat.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.chat.db.ChatDAO;
import net.member.db.Member;
import net.member.db.MemberDAO;

public class ChatMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.setAttribute("menu","user");
		
		// 프로필 
		ChatDAO chatdao = new ChatDAO();
		String id = (String) session.getAttribute("id");
		Member m = chatdao.memberinfo(id);
		
		
		//친구 리스트
		List<Member> memberlist = new ArrayList<Member>();
		memberlist = chatdao.getMemberList();
		request.setAttribute("memberlist", memberlist);		
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("chat/chatmain.jsp");
		forward.setRedirect(false);
		request.setAttribute("m", m);
		
		return forward;
	}

}
