package net.chat.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.chat.db.ChatDAO;
import net.member.db.Member;

public class ChatMemberSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		ChatDAO chatdao = new ChatDAO();
		
		List<Member> memberlist = null;
		
		String search_word = "";
		
		if(request.getParameter("search_word") == null
				|| request.getParameter("search_word").equals("")) {
			
			//전체 멤버 리스트
			memberlist = chatdao.getMemberList();
		} else {
			search_word = request.getParameter("search_word");
			memberlist = chatdao.getMemberList(search_word);
		}
		System.out.println(search_word = "search_word");
		
		memberlist = chatdao.getMemberList();
		request.setAttribute("searchMList", memberlist);
		
		
		forward.setPath("Chatmain.chat");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
