package net.dept.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.dept.db.DeptDAO;

public class DeptGetList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			DeptDAO dao = new DeptDAO();
			JsonArray array = dao.getList();
			
			JsonObject object = new JsonObject();
			
			object.add("deptList", array);
			response.setContentType("application/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(object.toString());
			System.out.println(object.toString());

		return null;
	}

}
