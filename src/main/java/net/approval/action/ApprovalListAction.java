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
		
		int page = 1; // 보여줄 페이지
		int limit = 10; // 한 페이지에 보여줄 게시판 목록의 수

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		if (request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		
		
		int listcount = dao.getApprovalListCount();
		
		
		applist = dao.getApprovalList(page, limit);
		
		int maxpage = (listcount + limit - 1) / limit;	
		
		int startpage = ((page - 1) / 10 * 10 + 1);
		int endpage = startpage + 10 - 1;
		
		if (endpage > maxpage) {
			endpage = maxpage;
		}
		
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		
		// 현재 페이지에 표시할 첫 페이지 수
		request.setAttribute("startpage", startpage);
		
		// 현재 페이지에 표시할 끝 페이지 수
		request.setAttribute("endpage", endpage);
		
		request.setAttribute("listcount", listcount);
		
		// 해당 페이지의 글 목록을 갖고 오는 리스트
		request.setAttribute("limit", limit);
		
		request.setAttribute("applist", applist);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("approval/ApprovalList.jsp");

		return forward;
	}

}
