package net.cafeboard.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.cafeboard.db.cafeitemBean;
import net.cafeboard.db.BoardDAO;
import net.cafemember.action.Action;
import net.cafemember.action.ActionForward;

public class BoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardDAO boarddao = new BoardDAO();
		cafeitemBean boarddata = new cafeitemBean();
		
		// 글번호 파라미터 값을 num 변수에 저장
		int num = Integer.parseInt(request.getParameter("num"));
		// 글의 내용 => DAO에서 읽기 => boarddata 객체에 저장
		boarddata = boarddao.getDetail(num);
		
		if(boarddata == null) { // error 상황
			System.out.println("상세보기 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "데이터를 읽지 못했습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		System.out.println("상세보기 성공");
		request.setAttribute("boarddata", boarddata);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("cafeAdmin/boardView.jsp");
		return forward;
	}
}
