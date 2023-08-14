package et.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;

public class MemberAdminModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		MemberDAO mdao = new MemberDAO();
		String id = request.getParameter("id");
		String tab = request.getParameter("tab");
		String name = request.getParameter("name");
		int dnum = Integer.parseInt(request.getParameter("dname"));
		String pnum = request.getParameter("mjob");
		int empnum = Integer.parseInt(request.getParameter("empnum"));
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int result = mdao.adminmodify(name, dnum, pnum, empnum);
		
		if(result == 1 ) {
			out.println("<script>");
			out.println("alert('정보가 수정되었습니다.');");
			out.println("location.href='memberList.et?tab=3';");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('정보 수정이 실패 되었습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		out.close();
		return null;
	}

}
