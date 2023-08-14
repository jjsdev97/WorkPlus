package net.cafemember.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.cafemember.db.cafeMember;
import net.cafemember.db.cafeMemberDAO;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(); // ★id는 session으로 가져오기★
		String id = (String) session.getAttribute("id");
		
		cafeMemberDAO mdao = new cafeMemberDAO();
		cafeMember m = mdao.member_info(id);
		
		ActionForward forward = new ActionForward();
		forward.setPath("cafeMember/updateForm.jsp");
		forward.setRedirect(false); // 주소 변경 없는 forward 방식 사용(jsp 페이지 show)
		request.setAttribute("memberinfo", m);
		return forward;
	}

}
