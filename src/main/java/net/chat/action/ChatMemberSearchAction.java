package net.chat.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.chat.db.ChatDAO;
import net.member.db.Member;

public class ChatMemberSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		
		
		HttpSession session = request.getSession();
		
		ActionForward forward = new ActionForward();
		ChatDAO chatdao = new ChatDAO();
		
		//나의 정보
		String id = (String) session.getAttribute("id");
		
		List<Member> memberlist = null;
		
		String search_word = "";
		
		if(request.getParameter("search_word") == null
				|| request.getParameter("search_word").equals("")) {
			
			//전체 멤버 리스트
			memberlist = chatdao.getMemberList(id);
		} else {//검색을 클릭한 경우 
			search_word = request.getParameter("search_word");
			memberlist = chatdao.getMemberList(id, search_word);
		}
		
		request.setAttribute("memberlist", memberlist);
		request.setAttribute("search_word", search_word);
		
		forward.setPath("Chatmain.chat");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
