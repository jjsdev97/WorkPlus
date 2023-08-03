package net.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.Member;
import net.member.db.MemberDAO;

public class MemberJoinProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// String num = request.getParameter(null);  //회원번호
		String name = request.getParameter("name");
		String id = request.getParameter("id");			//회원 아이디
		String pass = request.getParameter("pass");			//회원 비밀번호
		int empnum = Integer.parseInt(request.getParameter("empnum"));			//사원번호
		String email = request.getParameter("email");	//이메일(외부 인증 이메일)
		String domain = request.getParameter("domain");
		String emailaddr = email + "@" + domain;
		//Date M_HIREDATE;				//입사일
		int D_NUM = 0;					//부서번호(FK DEPT)
		//String P_NUM = request.getParameter("");			//직책 ENUM 클래스
		String admit = "1";			//가입승인
		String status = "1";		//이용정지
		String m_admin = "user";	//사용자 타입 user(기본값)
		String chat_status = "offline";
		
		Member m = new Member();
		m.setM_NAME(name);
		m.setM_ID(id);
		m.setM_PASS(pass);
		m.setE_NUM(empnum);
		m.setVERIFY_EMAIL(emailaddr);
		m.setD_NUM(D_NUM);
		m.setR_ADMIT(admit);
		m.setM_STATUS(status);
		m.setCHAT_STATUS(chat_status);
		m.setM_ADMIN(m_admin);
		
		MemberDAO mdao = new MemberDAO();
		int result = mdao.joininsert(m);
		
		if(result==0) {
			System.out.println("회원가입 실패입니다.");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "회원 가입 실패입니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		if(result == 1) {  //삽입이 된 경우
			out.println("alert('회원가입을 축하합니다.');");
			out.println("location.href='login.net';");
		}
		out.println("</script>");
		out.close();
		return null;
		
	}

}
