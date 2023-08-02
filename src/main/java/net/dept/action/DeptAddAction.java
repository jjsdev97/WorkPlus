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
		int dupperteam = Integer.parseInt(request.getParameter("dupperteam"));
		String dcolor = request.getParameter("dcolor");

		Dept d = new Dept();
		DeptDAO dao = new DeptDAO();
		
		d.setD_name(dname);
		d.setD_level(dlevel);
		d.setD_upperlevel(dupperteam);
		d.setD_color(dcolor);
		
		int result = dao.insert(d);
		
		
		if (result == 0) {
			System.out.println("dept추가실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("error/error.jsp");
			return forward;

		}
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		
		if(result==1) {
			out.println("alert('알럿');");
			out.println("location.href='DeptAdmin.jsp';");
		}
		out.println("</script>");
		out.close();
		
		
		return null;
	}

}
