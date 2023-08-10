package net.dept.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import net.dept.db.DeptDAO;

public class DeptUserChartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		DeptDAO dao = new DeptDAO();
		JsonArray array = dao.getList();

	    Gson gson = new Gson();
        String jsonData = gson.toJson(array);

        HttpSession session = request.getSession();
		session.setAttribute("menu", "user"); // admin, user
		
        request.setAttribute("jsonData", jsonData);
        
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("dept/DeptChart.jsp");

		return forward;
	}

}
