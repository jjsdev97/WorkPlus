package et.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;

public class MemberpassResetAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		String pass = request.getParameter("pass");
		MemberDAO mdao = new MemberDAO();
		int result = mdao.resetPass(pass);
		System.out.println("결과는 " + result);
		
		
		return null;
	}

}
