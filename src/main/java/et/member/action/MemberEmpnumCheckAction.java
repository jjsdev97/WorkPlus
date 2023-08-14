package et.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;

public class MemberEmpnumCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		MemberDAO dao = new MemberDAO();
		int empnum = Integer.parseInt(request.getParameter("empnum"));
		int result = dao.isEmpnum(empnum);
		response.getWriter().print(result);
		System.out.println(result);
		return null;
	}

}
