//글 수정 화면을 출력해주는 Action 클래스
package net.board.action;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;
import net.member.action.Action;
import net.member.action.ActionForward;

public class BoardReplyView implements Action {
	 public ActionForward execute(HttpServletRequest request,
			 HttpServletResponse response) throws ServletException, IOException {
		 
		 	ActionForward forward = new ActionForward();	   		
		 	 BoardDAO boarddao = new BoardDAO(); 
		   	BoardBean boarddata=new BoardBean();
		   	
		   	//파라미터로 전달받은 수정할 글 번호를 num변수에 저장합니다.
			int num=Integer.parseInt(request.getParameter("num"));
			
			//글 내용을 불러와서 boarddata객체에 저장합니다.
		   	boarddata=boarddao.getDetail(num);
		   	
		   	//글 내용이 없는 경우
		   	if(boarddata==null){
		   		System.out.println("글이 존재하지 않습니다.");
			   	forward.setRedirect(false);
			   	request.setAttribute("message", "글이존재하지 않습니다.");
		   		forward.setPath("error/error.jsp");
		   		return forward;
		   	}
		   	System.out.println("답변페이지로 이동 완료");
		   	//답변폼 페이지로 이동할 때 원본 글 내용을 보여주기 위해
		   	//boarddata 객체를 reqeust객체에 저장합니다.
		   	request.setAttribute("boarddata", boarddata);
		   	forward.setRedirect(false);
		   	//글 답변 폼 페이지 경로 지정합니다.
	   		forward.setPath("board/boardReply.jsp");
	   		return forward;
	 }//execute end
}//class end