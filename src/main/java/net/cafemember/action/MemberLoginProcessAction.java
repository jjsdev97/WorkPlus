package net.cafemember.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.cafemember.db.cafeMemberDAO;
// LOGIN_SUCCESS = 1, ID_NOT_EXIST = -1, PASSWORD_MISMATCH = 0
public class MemberLoginProcessAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		cafeMemberDAO mdao = new cafeMemberDAO();
		int result = mdao.isID(id, pass);
		System.out.println("결과: " + result);
		
		if (result == 1) { // 로그인 성공시
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			
			String IDStore = request.getParameter("remember");
			Cookie cookie = new Cookie("id", id);
			
			// ID remember를 클릭한 경우
			if(IDStore != null && IDStore.equals("store")) { 
				cookie.setMaxAge(2*60); // 120s
				response.addCookie(cookie); // 클라이언트로 쿠키값 전송
				System.out.println("쿠키확인");
			} else {
				cookie.setMaxAge(0); // 쿠키 없음
				response.addCookie(cookie);
			}
			forward.setRedirect(true);
			forward.setPath("ItemList.ca");
			return forward;
		} else {
			String message = "비밀번호가 일치하지 않습니다.";
			if(result == -1) {
				message = "아이디가 존재하지 않습니다.";
			}
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + message + "');");
			out.println("location.href='login.cnet';");
			out.println("</script>");
			out.close();
			return null;
		}
	}
}
