package net.board.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BoardWriteAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.setAttribute("menu", "user"); // admin, user
		session.setAttribute("selectedmenu", "board_write");
		
		ActionForward forward = new ActionForward();
		
		forward.setRedirect(false); //포워딩 방식으로 주소가 바뀌지 않아요
		forward.setPath("board/BoardWrite.jsp");
		return forward;
	}
} // class end  화면
