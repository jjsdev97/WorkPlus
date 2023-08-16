package net.approval.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ApprovalListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		session.setAttribute("menu", "user"); // admin, user
		session.setAttribute("selectedmenu", "approvallist");

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("approval/ApprovalList.jsp");

		return forward;
	}

}
