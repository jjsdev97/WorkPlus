package net.dept.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import net.dept.db.Dept;
import net.dept.db.DeptDAO;

public class DeptGetDataAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		JsonObject dept = new JsonObject();

		int num = Integer.parseInt(request.getParameter("num")); // ajax로 보드넘버 얻어오기

		DeptDAO dao = new DeptDAO();
		System.out.println(num);
		dept = dao.select(num);

		if (dept == null) {
			System.out.println("dept select 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("error/error.jsp");
			return forward;

		}

		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(dept.toString());

		return null;
	}

}
