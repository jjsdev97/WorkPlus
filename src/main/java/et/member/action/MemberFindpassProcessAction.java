package et.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.Member;
import net.member.db.MemberDAO;

public class MemberFindpassProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		MemberDAO dao = new MemberDAO();
		String email = request.getParameter("email");
		int empnum = Integer.parseInt(request.getParameter("verifynum"));
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		Member member = dao.findpass(email, verifynum);
		
		if(member != null)  {
			ActionForward actionforward = new ActionForward();
			actionforward.setPath("member/findpass_result.jsp");
			actionforward.setRedirect(false);
			request.setAttribute("member", member);
			return actionforward;
			
			
		} else {
			out.println("<script>");
			out.println("alert('일치하지 않는 회원정보 입니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		out.close();
		return null;
	}

}
