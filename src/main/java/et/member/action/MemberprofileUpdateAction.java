package et.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.Member;
import net.member.db.MemberDAO;

public class MemberprofileUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
	      session.setAttribute("menu", "admin"); // admin, user
	      session.setAttribute("selectedmenu", "profile");
		
		String id = (String) session.getAttribute("id");
		
		MemberDAO mdao = new MemberDAO();
		Member m = mdao.memberinfo(id);
		
		ActionForward forward = new ActionForward();
		forward.setPath("member/profileform.jsp");
		forward.setRedirect(false);
		request.setAttribute("m", m);
				
		return forward;
	}

}
