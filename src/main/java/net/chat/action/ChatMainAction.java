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
		session.setAttribute("menu", "user");
		session.setAttribute("selectedmenu", "chatmain");
		// 프로필
		ChatDAO chatdao = new ChatDAO();
		String id = (String) session.getAttribute("id");
		Member m = chatdao.memberinfo(id);

		List<Member> memberlist = null;

		String search_word = "";

		if (request.getParameter("search_word") == null || request.getParameter("search_word").equals("")) {

			// 전체 멤버 리스트
			memberlist = chatdao.getMemberList(id);
		} else {// 검색을 클릭한 경우
			search_word = request.getParameter("search_word");
			memberlist = chatdao.getMemberList(id, search_word);
		}
		request.setAttribute("memberlist", memberlist);
		request.setAttribute("search_word", search_word);
		ActionForward forward = new ActionForward();
		forward.setPath("chat/chatmain.jsp");
		forward.setRedirect(false);
		request.setAttribute("m", m);

		return forward;
	}

}
