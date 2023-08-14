package net.approval.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.approval.db.ApprovalDAO;
import net.approval.db.ApprovalTemplate;
import net.dept.db.Dept;
import net.member.db.Member;

public class ApprovalWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		ArrayList<Dept> deptList = new ArrayList<>();
		ArrayList<Member> memberList = new ArrayList<>();
		ArrayList<ApprovalTemplate> atList = new ArrayList<>();
		
		Member member = new Member();
		ApprovalDAO dao = new ApprovalDAO();

		deptList = dao.getDeptList();
		memberList = dao.getMemberList();
		atList = dao.getTemplateList();

		HttpSession session = request.getSession();
		session.setAttribute("menu", "user"); // admin, user
		session.setAttribute("selectedmenu", "approval");
		
		String session_id = (String) session.getAttribute("id");
		member = dao.getMemberDetail(session_id);

		request.setAttribute("writer", member);
		request.setAttribute("deptList", deptList);
		request.setAttribute("memberList", memberList);
		request.setAttribute("atList", atList);
		

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("approval/ApprovalWrite.jsp");

		return forward;
	}

}
