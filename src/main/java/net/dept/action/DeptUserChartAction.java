package net.dept.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import net.dept.db.Dept;
import net.dept.db.DeptDAO;
import net.member.db.Member;


public class DeptUserChartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		DeptDAO dao = new DeptDAO();
		ArrayList<Dept> deptlist = new ArrayList<>();
		
		deptlist = dao.getDeptName();
		Member m = new Member();
		ArrayList<Member> memberlist = new ArrayList<>();
		
		memberlist = dao.getMemberList();
	
		JsonArray jsonarray = dao.getList();

	    Gson gson = new Gson();
        String jsonData = gson.toJson(jsonarray);

        HttpSession session = request.getSession();
		session.setAttribute("menu", "user"); // admin, user
		session.setAttribute("selectedmenu", "deptchart");
		
        request.setAttribute("jsonData", jsonData);
        
		request.setAttribute("memberList", memberlist);
		request.setAttribute("deptList", deptlist);

        
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("dept/DeptChart.jsp");

		return forward;
	}

}
