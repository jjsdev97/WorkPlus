package net.approval.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.approval.db.Approval;
import net.approval.db.ApprovalDAO;

public class ApprovalListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		session.setAttribute("menu", "user"); // admin, user
		session.setAttribute("selectedmenu", "approvallist");

		ArrayList<Approval> applist = new ArrayList<>();
		ApprovalDAO dao = new ApprovalDAO();
		
		applist = dao.getApprovalList();
		
		request.setAttribute("applist", applist);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("approval/ApprovalList.jsp");

		return forward;
	}

}
