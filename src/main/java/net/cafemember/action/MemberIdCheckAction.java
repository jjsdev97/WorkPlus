package net.cafemember.action;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.cafemember.db.cafeMemberDAO;
public class MemberIdCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		cafeMemberDAO dao = new cafeMemberDAO();
		int result = dao.isID(request.getParameter("id"));
		response.getWriter().print(result); // 여기서 값 실어서 출력
		System.out.println(result);
		return null; // 이미 출력 => return null
	}
}
