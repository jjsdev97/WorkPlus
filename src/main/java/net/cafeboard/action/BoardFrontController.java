package net.cafeboard.action;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.cafemember.action.Action;
import net.cafemember.action.ActionForward;

@WebServlet("*.ca")
public class BoardFrontController extends javax.servlet.http.HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	// 요청이 Get or Post 방식 모두 doProcess메소드로 처리
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
		case "/ItemList.ca":
			action = new BoardListAction();
			break;	
		case "/ItemWrite.ca":
			action = new BoardWriteAction();
			break;	
		case "/ItemAdd.ca":
			action = new BoardAddAction(); 
			break;		
		case "/ItemDetail.ca":
			action = new BoardDetailAction();
			break;	
		case "/ItemModifyView.ca":
			action = new BoardModifyView();
			break;	
		case "/ItemModify.ca":
			action = new BoardModifyAction();
			break;
		case "/ItemDelete.ca":
			action = new BoardDeleteAction();
			break;	
		case "/ItemFileDown.ca":
			action = new BoardFileDownAction();
			break;	
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