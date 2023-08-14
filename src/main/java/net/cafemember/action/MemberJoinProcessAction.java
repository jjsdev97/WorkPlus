package net.cafemember.action;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.cafemember.db.*;
public class MemberJoinProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		
		cafeMember m = new cafeMember();
		m.setId(id);   m.setName(name);	  m.setPassword(pass);
		
		cafeMemberDAO mdao = new cafeMemberDAO();
		int result = mdao.insert(m);
		
		if(result == 0) { // 삽입 실패
			System.out.println("회원가입 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "회원가입 실패");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		if(result == 1) { // 삽입 성공
			out.println("alert('회원 가입을 축하합니다.');");
			out.println("location.href = 'login.cnet';");
		}
		out.println("</script>");
		out.close();
		return null;
	}
}
