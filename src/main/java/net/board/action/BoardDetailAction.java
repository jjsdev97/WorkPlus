//글 내용 보기와 관련된 Action 클래스
package net.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

 public class BoardDetailAction implements Action {
	 public ActionForward execute(HttpServletRequest request,
			 HttpServletResponse response) throws ServletException, IOException { 
		 
		BoardDAO boarddao = new BoardDAO(); 
	   	BoardBean boarddata = new BoardBean();
	   	
	   	//글번호 파라미터 값을 num변수에 저장합니다.
		int num=Integer.parseInt(request.getParameter("num"));
		
		// 특정 주소로부터의 이동을 확인하는 데 사용할 수 있는 정보는 "Referer" 헤더에 있습니다.
		String referer = request.getHeader("Referer");
		if (referer != null && referer.contains("BoardList.bo")) {
			//내용을 확인할 글의 조회수를 증가시킵니다.
			boarddao.setReadCountUpdate(num);
		}
		
		//글의 내용을 DAO에서 읽은 후 얻은 결과를 boarddata 객체에 저장합니다.
	   	boarddata=boarddao.getDetail(num);
	   	
	   	//boarddata=null;//error테스트를 위한 값 설정
	   	//DAO에서 글의 내용을 읽지 못했을 경우 null을 반환합니다.
	   	if(boarddata==null){
	   		System.out.println("상세보기 실패");
	   		ActionForward forward = new ActionForward();
		   	forward.setRedirect(false);
		   	request.setAttribute("message", "데이터를 읽지 못했습니다.");
	   		forward.setPath("error/error.jsp");
	   		return forward;
	   	}
	   	System.out.println("상세보기 성공");
	   	
	   	//boarddata 객체를 request 객체에 저장합니다.
	   	request.setAttribute("boarddata", boarddata);
	   	
	   	ActionForward forward = new ActionForward();
	   	forward.setRedirect(false);	    
   		forward.setPath("board/boardView.jsp"); //글 내용 보기 페이지로 이동하기 위해 경로를 설정합니다.
   		return forward;
	 }
} 