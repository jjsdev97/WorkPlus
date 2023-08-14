package net.cafeboard.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.cafeboard.db.cafeitemBean;
import net.cafeboard.db.BoardDAO;
import net.cafemember.action.Action;
import net.cafemember.action.ActionForward;

public class BoardModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BoardDAO boarddao = new BoardDAO();
		cafeitemBean boarddata = new cafeitemBean();
		
		ActionForward forward = new ActionForward();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		boarddata = boarddao.getDetail(num);
		
		if(boarddata == null) {
			System.out.println("(수정)상세보기 실패");
			forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "게시판 수정 상세 보기 실패");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("(수정)상세보기 성공");
		
		request.setAttribute("boarddata", boarddata);
		forward.setRedirect(false);
		
		forward.setPath("cafeAdmin/boardModify.jsp");
		return forward;
	}
}