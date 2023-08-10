package et.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;

public class MemberConfirmAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		MemberDAO mdao = new MemberDAO();
		String id = request.getParameter("id");
		String tab = request.getParameter("tab");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int result = mdao.confirm(id);
		
		if(result == 1) {
			out.println("<script>");
			out.println("alert('가입이 승인되었습니다.');");
			out.println("location.href='memberList.et?tab=" + tab + "';");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('가입 승인이 실패 되었습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		out.close();
		return null;
	}

}
