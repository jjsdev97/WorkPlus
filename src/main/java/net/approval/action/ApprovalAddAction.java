package net.approval.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.approval.db.ApprovalDAO;
import net.approval.db.ApprovalLine;

public class ApprovalAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		ApprovalDAO dao = new ApprovalDAO();

		HttpSession session = request.getSession();
		String session_id = (String) session.getAttribute("id");

		String period = request.getParameter("approval-period");
		String subject = request.getParameter("approval-subject");
		String templatetype = request.getParameter("approval-template");
		String content = request.getParameter("hiddenContent");

		String approval[] = request.getParameterValues("input-approval-item");
		String reference[] = request.getParameterValues("input-ref-item");
		String receiver[] = request.getParameterValues("input-rec-item");

		ArrayList<ApprovalLine> al = new ArrayList<>();
		
		

		return null;
	}

}
