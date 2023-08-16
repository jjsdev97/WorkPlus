package net.approval.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.apv")
public class ApprovalFrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String Request = request.getRemoteHost();
		System.out.println("접속 URI = " + Request);

		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);

		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);

		String command = RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);

		ActionForward forward = null;
		Action action = null;

		switch (command) {
		case "/approvalWrite.apv":
			action = new ApprovalWriteAction();
			break;
		case "/approvalAddAction.apv":
			action = new ApprovalAddAction();
			break;
		case "/approvalGetMemberList.apv":
			action = new ApprovalGetMemberListAction();
			break;
		case "/approvalGetTemplate.apv":
			action = new ApprovalGetTemplateAction();
			break;
		case "/approvalList.apv":
			action = new ApprovalListAction();
			break;
		}

		forward = action.execute(request, response);

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);

			}
		}

	}

	// doProcess(request, response)메서드를 구현하여 요청이 GET방식이든
	// POST방식으로 전송되어 모든 같은 메서드에서 요청을 처리 할 수 있도록 했습니다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doProcess(request, response);
	}
}
