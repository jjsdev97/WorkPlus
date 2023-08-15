package net.dept.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.dept.db.Dept;
import net.dept.db.DeptDAO;


public class DeptAdminAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Dept d = new Dept();
		DeptDAO dao = new DeptDAO();
		
		HttpSession session = request.getSession();
		session.setAttribute("menu", "admin"); // admin, user
		session.setAttribute("selectedmenu", "deptadmin");
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("dept/DeptAdmin.jsp");
		
		return forward;
	}

}
