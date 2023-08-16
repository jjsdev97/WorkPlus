package et.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberJoinAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
		session.setAttribute("menu", "user"); // admin, user
		session.setAttribute("selectedmenu", "memberjoin");
		
		forward.setRedirect(false);
		forward.setPath("member/joinForm.jsp");
		return forward;
	}
}
