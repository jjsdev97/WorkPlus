package net.approval.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.approval.db.ApprovalDAO;

public class ApprovalGetTemplateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ApprovalDAO dao = new ApprovalDAO();
		
		
		String tnum = (String) request.getParameter("num");
		
		String templateContent = dao.getTemplate(tnum);
		
		JsonObject jsondata = new JsonObject();
		jsondata.addProperty("templateContent", templateContent);
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(jsondata.toString());
		
		return null;
	}

}
