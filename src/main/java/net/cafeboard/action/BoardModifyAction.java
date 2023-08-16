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

public class BoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardDAO boarddao = new BoardDAO();
		cafeitemBean boarddata = new cafeitemBean();
		ActionForward forward = new ActionForward();
		
		String realFolder = "";
		String saveFolder = "cafeitemupload";
		
		int fileSize = 5 * 1024 * 1024; // 업로드 할 파일의 최대 사이즈(5MB)
		
		// 실제 저장 경로를 지정
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder: " + realFolder);
		boolean result = false;
		try {
			MultipartRequest multi = 
					new MultipartRequest(request, realFolder, fileSize, "utf-8",
							new DefaultFileRenamePolicy());
			int num = Integer.parseInt(multi.getParameter("ITEM_UID"));

			boarddata.setITEM_UID(num);
			boarddata.setITEM_PRICE(Integer.parseInt(multi.getParameter("ITEM_PRICE")));
			boarddata.setITEM_MENU(multi.getParameter("ITEM_MENU"));
			boarddata.setITEM_NAME(multi.getParameter("ITEM_NAME").trim());
			boarddata.setITEM_DETAIL(multi.getParameter("ITEM_DETAIL"));
			
			String check = multi.getParameter("check");
			System.out.println("check: " + check);
			if (check != null) {
				boarddata.setITEM_IMG_PATH(check);
			} 
			else {
				String filename = multi.getFilesystemName("ITEM_IMG_PATH");
				boarddata.setITEM_IMG_PATH(filename);
			}
			// DAO에서 수정 메소드 호출하여 true or false 반환
			result = boarddao.boardModify(boarddata);
			
			// 수정 실패 시 
			if (result == false) {
				System.out.println("게시판 수정 실패");
				forward.setRedirect(false);
				request.setAttribute("message", "게시판 수정 실패");
				forward.setPath("error/error.jsp");
				return forward;
			}
			// 수정 성공 시 
			System.out.println("게시판 수정 완료");
			
			forward.setRedirect(true); // 주소 변경 방식(forward)
			forward.setPath("ItemList.ca?num=" + boarddata.getITEM_UID());
			return forward;
			
		} catch (IOException e) {
			e.printStackTrace();
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "게시판 업로드 중 오류 발생");
			forward.setRedirect(false);
			return forward;
		}
	}
}
