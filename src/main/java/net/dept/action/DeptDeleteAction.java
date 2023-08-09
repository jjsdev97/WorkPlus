package net.dept.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.dept.db.DeptDAO;

public class DeptDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int result = 0;		
		int num = Integer.parseInt(request.getParameter("num")); // ajax로 보드넘버 얻어오기
		
		DeptDAO dao = new DeptDAO();
		System.out.println(num);
		result = dao.delete(num);
		
		
		if (result == 0) {
			System.out.println("dept추가실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("error/error.jsp");
			return forward;

		}
		
		return null;
	}

}
