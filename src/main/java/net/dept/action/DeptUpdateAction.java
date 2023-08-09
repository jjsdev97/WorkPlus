package net.dept.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.dept.db.Dept;
import net.dept.db.DeptDAO;

public class DeptUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Dept d = new Dept();
		int result = 0;		
		d.setD_num(Integer.parseInt(request.getParameter("dnum")));
		d.setD_name(request.getParameter("dname"));
		d.setD_level(Integer.parseInt(request.getParameter("dlevel")));
		d.setD_upperlevel(Integer.parseInt(request.getParameter("dupperlevel")));
		d.setD_color(request.getParameter("dcolor"));
		
		DeptDAO dao = new DeptDAO();
		result = dao.update(d);
		
		
		if (result == 0) {
			System.out.println("dept 수정실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("error/error.jsp");
			return forward;

		}
		
		return null;
	}
}
