package net.approval.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.approval.db.Approval;
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

		int period = Integer.parseInt(request.getParameter("approval-period"));
		String subject = request.getParameter("approval-subject");
		int templatetype = Integer.parseInt(request.getParameter("approval-template"));
		String content = request.getParameter("hiddenContent");

		String approvalArr[] = request.getParameterValues("input-approval-item");
		String referenceArr[] = request.getParameterValues("input-ref-item");
		String receiverArr[] = request.getParameterValues("input-rec-item");
		
		
		Approval app = new Approval();
		app.setApproval_template(templatetype);
		app.setApproval_period(period);
		app.setApproval_subject(subject);
		app.setApproval_writer(session_id);
		app.setApproval_content(content);

		ArrayList<ApprovalLine> alList = new ArrayList<>();
		
		boolean result = false;
		
		for (String appitem : approvalArr) {
		    ApprovalLine al = new ApprovalLine(); // 새로운 객체 생성
		    al.setA_line_target(appitem);
		    al.setA_line_type("결재");
		    alList.add(al);
		}

		for (String refitem : referenceArr) {
		    ApprovalLine al = new ApprovalLine(); // 새로운 객체 생성
		    al.setA_line_target(refitem);
		    al.setA_line_type("참조");
		    alList.add(al);
		}

		for (String recitem : receiverArr) {
		    ApprovalLine al = new ApprovalLine(); // 새로운 객체 생성
		    al.setA_line_target(recitem);
		    al.setA_line_type("수신");
		    alList.add(al);
		}
		
		System.out.println("배열 이동 완료");
		
		result = dao.insertApproval(app, alList);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("approvalList.apv");
		return forward;
	}

}
