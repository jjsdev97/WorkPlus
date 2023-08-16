package net.approval.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.approval.db.Approval;
import net.approval.db.ApprovalDAO;

public class ApprovalDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Approval app = new Approval();
		ApprovalDAO dao = new ApprovalDAO();

		int num = Integer.parseInt(request.getParameter("num"));
		
//		app = boarddao.getDetail(num);
		return null;
	}

}
