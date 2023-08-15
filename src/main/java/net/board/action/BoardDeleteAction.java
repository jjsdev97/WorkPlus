//글 삭제에 대한 Action 클래스
package net.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;

public class BoardDeleteAction implements Action {
	 public ActionForward execute(HttpServletRequest request,
			 HttpServletResponse response) 	throws ServletException, IOException {		
	   	int result=0;
	   	
	   	int num=Integer.parseInt(request.getParameter("delete-num"));
	   	
	    BoardDAO boarddao = new BoardDAO(); 
	   	
	   	result=boarddao.boardDelete(num);	   	
	   	
	   	//삭제 처리 실패한 경우
	   	if(result==0){
	   		System.out.println("게시판 삭제 실패");
	   		ActionForward forward = new ActionForward();
		   	forward.setRedirect(false);
		   	request.setAttribute("message", "데이터를 삭제하지 못했습니다.");
	   		forward.setPath("error/error.jsp");
	   		return forward;
	   	}
	   	//삭제 처리 성공한 경우 - 글 목록 보기 요청을 전송하는 부분입니다.
	   	System.out.println("게시판 삭제 성공");
	   	response.setContentType("text/html;charset=utf-8");
   		PrintWriter out=response.getWriter();
   		out.println("<script>");
   		out.println("alert('삭제 되었습니다.');");
   		out.println("location.href='BoardList.bo';");
   		out.println("</script>");
   		out.close();
   		return null;
	 }
}