package net.approval.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.approval.db.Approval;
import net.approval.db.ApprovalDAO;
import net.approval.db.ApprovalLine;

public class ApprovalDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		Approval app = new Approval();
		ApprovalDAO dao = new ApprovalDAO();

		ArrayList<ApprovalLine> alList = new ArrayList<>();

		int num = Integer.parseInt(request.getParameter("num"));

		app = dao.getDetail(num);
		alList = dao.getApprovalLine(num);

		request.setAttribute("app", app);
		request.setAttribute("alList", alList);

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);

		forward.setPath("approval/ApprovalDetail.jsp");
		return forward;
	}

}
