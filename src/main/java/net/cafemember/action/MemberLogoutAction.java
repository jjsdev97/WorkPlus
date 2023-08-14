package net.cafemember.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.invalidate(); // 쿠기(로그인정보) 삭제
	
		ActionForward forward = new ActionForward();
		forward.setRedirect(true); // 주소 변경 
		forward.setPath("login.cnet");
		return forward;
	}

}