package net.dept.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.dept.db.Dept;
import net.dept.db.DeptDAO;

public class DeptAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String dname = request.getParameter("dname");
		int dlevel = Integer.parseInt(request.getParameter("dlevel"));
		int dupperlevel = Integer.parseInt(request.getParameter("dupperlevel"));
		String dcolor = request.getParameter("dcolor");

		Dept d = new Dept();
		DeptDAO dao = new DeptDAO();
		
		d.setD_name(dname);
		d.setD_level(dlevel);
		d.setD_upperlevel(dupperlevel);
		d.setD_color(dcolor);
		
		int result = dao.insert(d);
		
		
		if (result == 0) {
			System.out.println("dept추가실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("error/error.jsp");
			return forward;

		}
		
		
		response.getWriter().print(result);
		
		return null;
	}

}
