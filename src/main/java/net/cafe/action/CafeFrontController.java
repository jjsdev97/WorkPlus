package net.cafe.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.cafe")
public class CafeFrontController extends javax.servlet.http.HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// getRequestURI(): 요청된 전체 URL 중 포트 번호 다음 부터 마지막 문자열까지 반환
		// contextPath: "JspProject"인 경우
		// http://localhost:8088/JspProject/login.net으로 요청 시 
		// RequestURI: "JspProject/login.net"
		
		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI: " + RequestURI);
		
		// getContextPath(): 컨텍스트 경로 반환
		// contextPath: "JspProject"
		String contextPath = request.getContextPath();
		System.out.println("contextPath: " + contextPath);
		
		String command = RequestURI.substring(contextPath.length());
		System.out.println("command: " + command);
		
		ActionForward forward = null;
		Action action = null;
		
		switch(command) {
		case "/main.cafe":
			action = new CafeMainAction();
			break;	
		case "/coffee.cafe":
			action = new CafeCoffeeAction();
			break;
		case "/tea.cafe":
			action = new CafeTeaAction();
			break;
		case "/ade.cafe":
			action = new CafeAdeAction();
			break;
		case "/side.cafe":
			action = new CafeSideAction();
			break;	
		case "/payment.cafe":
			action = new CafePaymentAction(); 
			break;		
		case "/history.cafe":
			action = new CafeHistoryAction();
			break;	
//		case "/admin.cafe":
//			action = new CafeAdminAction();
//			break;	// itemlist 
//		case "/itemwrite.cafe":
//			action = new CafeWriteAction();
//			break;	
//		case "/itemadd.cafe":
//			action = new CafeAddAction();
//			break;		
//		case "/itemdetail.cafe":
//			action = new CafeItemDetail();
//			break;		
		}
		
		forward = action.execute(request, response);
		
		if (forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
		
	} // doProcss() end
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // post는 여기서 setCharacterEncoding 필요
											   // utf-8이면 utf-8로 상위와 맞춰야한다.
		doProcess(request, response);
	}
}
