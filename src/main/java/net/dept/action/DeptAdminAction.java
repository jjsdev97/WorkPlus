package net.dept.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeptAdminAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("dept/DeptAdmin.jsp");
		
		return forward;
	}

}
