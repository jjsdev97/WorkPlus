package net.cafeboard.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.cafeboard.db.BoardDAO;
import net.cafemember.action.Action;
import net.cafemember.action.ActionForward;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		boolean result = false;
//		boolean usercheck = false;
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDAO boarddao = new BoardDAO();
		
//		// 입력한 비밀번호와 저장된 비밀번호 비교
//		usercheck = boarddao.isBoardWriter(num, request.getParameter("board_pass"));
//		
//		if(usercheck == false) { // 비밀번호가 일치하지 않는 경우
//			response.setContentType("text/html; charset=utf-8");
//			PrintWriter out = response.getWriter();
//			out.println("<script>");
//			out.println("alert('비밀번호가 다릅니다.');");
//			out.println("history.back();");
//			out.println("</script>");
//			out.close();
//			return null;
//		}
		
		result = boarddao.boardDelete(num);
		
		if(result == false) { // 삭제 처리 실패
			System.out.println("게시판 삭제 실패");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("message", "데이터를 삭제하지 못했습니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		// 삭제 처리 성공
		System.out.println("게시판 삭제 성공");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('삭제 되었습니다.');");
		out.println("location.href='ItemList.ca';");
		out.println("</script>");
		out.close();
		return null;	
		}
	
}
