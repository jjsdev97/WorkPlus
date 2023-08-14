package net.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.bo")
public class BoardFrontController extends javax.servlet.http.HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Request = request.getRemoteHost(); // 호스트값을 반환
		System.out.println("접속 URI =" + Request); 
		
		 /*
		   * 요청된 전체 URL 중에서 포트 번호 다음부터 마지막 문자열까지 반환합니다.
		     예) contextPath 가 "/JspProject" 인 경우
		          http://localhost:8088/JspProject/login.net 으로 요청하면 getRequestURI()는
		          "/JspProject/login.net"으로 반환합니다.
		    */
		     String requestURI = request.getRequestURI();
		     System.out.println("RequestURI= " + requestURI);
		     
		     //getcontextPath() : 컨텍스트 경로가 반환됩니다.
		     //contextPath는 "/JspProject" 가 반환됩니다.
		     
		     String contextPath = request.getContextPath();
		     System.out.println("contextPath=" + contextPath);
		    		 // 위의 변수명과 동일해야함.
		     
		     //RequestURI 에서 컨텍스트 경로 길이 값의 인덱스 위치의 문자부터 마지막 위치 문자까지 추출
		     //command 는 "/login.net" 반환됩니다.
		     
		     String command = requestURI.substring(contextPath.length());
		     System.out.println("command =" + command);
		     
		     //초기화
		     ActionForward forward = null;
		     Action action = null;
		     
		     switch (command) {
		     case "/BoardWrite.bo":
		    	 action = new BoardWriteAction();
		    	 break;
		    	 
		     case "/BoardAddAction.bo":
		    	 action = new BoardAddAction();
		         break;
		         
		     case "/BoardList.bo":
		    	 action = new BoardListAction();
		    	 break;
		    	 
		     case "/BoardDetailAction.bo":
		    	 action = new BoardDetailAction();
		         break;
		         
		     case "/BoardDeleteAction.bo" :
					action = new BoardDeleteAction();
					break;
		     case "/BoardFileDown.bo":
		    	  action = new BoardFileDownAction();
		    	  break;
		     case "/BoardModifyView.bo":
		    	 action = new BoardModifyView();
		    	 break;
		     case "/BoardModifyAction.bo":
		     	  action = new BoardModifyAction();
		          break;
		         
		     case "/CommentList.bo":
		     	  action = new CommentList();
		          break;
		          
		     case "/CommentAdd.bo":
		    	 action = new CommentAdd();
		    	 break;
		    	 
		     case "/CommentDelete.bo":
		     	  action = new CommentDelete();
		          break;	 
		          
		     case "/CommentUpdate.bo":
		     	  action = new CommentUpdate();
		          break;   
		    	  
		     case "/CommentReply.bo":
		     	  action = new CommentReply();
		          break;
		    	 
		 
		    	  
		   
		     }// switch end  /*
		     forward = action.execute(request, response);
		     
		     if(forward != null) {
		        if(forward.isRedirect()) { //리다이렉트 됩니다.
		        	response.sendRedirect(forward.getPath());
		        }else { //포워딩합니다.
		        	RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
		        	dispatcher.forward(request, response);
		        }
		   }
	} //doProess() end
		     
		     
	
	//doprocess(request, response)메서드를 구현하여 요청이 GET방식이든
	//POST 방식으로 전송되어 오든 같은 메서드에서 요청을 처리할 수 있도록 하였습니다.
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
