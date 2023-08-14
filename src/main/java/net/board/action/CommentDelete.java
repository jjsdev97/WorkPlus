package net.board.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommentDelete implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		net.comment.db.CommentDAO dao = new net.comment.db.CommentDAO();
		
		int result = dao.commentsDelete(num); // 다오와 연결됨
		PrintWriter out = response.getWriter();
		out.print(result);
		
		return null;
	}
}
