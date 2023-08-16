package net.cafeboard.action;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.cafeboard.db.cafeitemBean;
import net.cafeboard.db.BoardDAO;
import net.cafemember.action.Action;
import net.cafemember.action.ActionForward;

public class BoardAddAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardDAO boarddao = new BoardDAO();
		cafeitemBean boarddata = new cafeitemBean();
		ActionForward forward = new ActionForward();

		String realFolder = "";

		String saveFolder = "cafeitemupload";

		int fileSize = 5 * 1024 * 1024;

		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder: " + realFolder);
		boolean result = false;
		try {
			MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "utf-8",
					new DefaultFileRenamePolicy());

			// cafeitemBean 객체에 글 등록 폼에서 입력 받은 정보들을 저장
			boarddata.setITEM_PRICE(Integer.parseInt(multi.getParameter("ITEM_PRICE")));
			boarddata.setITEM_MENU(multi.getParameter("ITEM_MENU"));
			boarddata.setITEM_NAME(multi.getParameter("ITEM_NAME"));
			boarddata.setITEM_DETAIL(multi.getParameter("ITEM_DETAIL"));

			// 실제 파일명 get
			String filename = multi.getFilesystemName("ITEM_IMG_PATH");
			boarddata.setITEM_IMG_PATH(filename);

			// boardInsert(): 글 등록(boarddata: 글 등록 폼의 입력한 정보)
			result = boarddao.boardInsert(boarddata);

			// 게시판 등록에 실패할 경우
			if (result == false) {
				System.out.println("게시판 등록 실패");
				forward.setPath("error/error.jsp");
				request.setAttribute("message", "게시판 등록 실패");
				forward.setRedirect(false);
				return forward;
			}
			System.out.println("게시판 등록 완료");

			forward.setRedirect(true); // 주소 변경 Redirect 방식 on
			forward.setPath("ItemList.ca");
			return forward;
		} catch (IOException ex) {
			ex.printStackTrace();
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "게시판 업로드 실패");
			forward.setRedirect(false);
			return forward;
		}
	}
}
