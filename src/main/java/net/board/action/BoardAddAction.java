package net.board.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardAddAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BoardDAO boarddao = new BoardDAO();
		BoardBean boarddata = new BoardBean();
		ActionForward forward = new ActionForward();

		String realFolder = "";

		// wepapp 아래에 꼭 폴더 생성하세요
		String saveFolder = "boardupload";

		int fileSize = 5 * 1024 * 1024; // 업로드 시 파일의 최대 사이즈 . 5MB

		// 실제 저장경로 지정
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder =" + realFolder);
		boolean result = false;
		try {
			MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "utf-8",
					new DefaultFileRenamePolicy());

			// BoardBean 객체에 글 동록 폼에서 입력 받은 정보들을 저장합니다.
//	    boarddata.setBOARD_TYPE(multi.getParameter("BOARD_TYPE")); //게시판 종류 (1)
			boarddata.setBOARD_TYPE(multi.getParameter("BOARD_TYPE")); // 게시판 종류 (1)
			boarddata.setBOARD_SUBJECT(multi.getParameter("BOARD_SUBJECT")); // 게시판 제목 (2)
			boarddata.setBOARD_CONTENT(multi.getParameter("BOARD_CONTENT")); // 게시판 내용 (3)
			boarddata.setBOARD_WRITER(multi.getParameter("BOARD_WRITER")); // 작성자 (4)
			/*
			 * boarddata.setBOARD_DATE(Integer.parseInt(multi.getParameter("BOARD_DATE")));
			 * // 작성일자 (5)
			 * 
			 * 
			 * boarddata.setBOARD_READCOUNT(Integer.parseInt(multi.getParameter(
			 * "BOARD_READCOUNT"))); //좋아요 카운팅 (6)
			 * 
			 * boarddata.setBOARD_NOTICE(multi.getParameter("BOARD_NOTICE")); //공지글 (7)
			 * boarddata.setBOARD_LIKECOUNT(Integer.parseInt(multi.getParameter(
			 * "BOARD_LIKECOUNT"))); // 좋아요 개수 (8)
			 */
			// 시스템 상에 업로드된 실제 파일명을 얻어옵니다.
			String filename = multi.getFilesystemName("BOARD_FILE"); // 파일명을 얻어오는 메서드
//			boarddata.setBOARD_FILE(filename); // 첨부파일명 (9)
			boarddata.setBOARD_FILE("임시"); // 첨부파일명 (9)

			// 글등록 처리를 위해 DAO의 boardInsert()메서드를 호출합니다.
			// 글 등록 폼에서 입력한 정보가 저장되어 있는 boarddata객체를 전달합니다.
			result = boarddao.boardInsert(boarddata);

			// 글 동록에 실패할 경우 false를 반환합니다.
			if (result == false) {
				System.out.println("게시판 등록 실패");
				forward.setPath("error/error.jsp");
				request.setAttribute("message", "게시판 등록 실패입니다.");
				forward.setRedirect(false);
				return forward;
			}
			System.out.println("게시판 등록 완료");

			// 글 등록이 완료되면 글 목록을 보여주기 위해 "BoardList.bo"로 이동합니다.
			// Redirect 여부를 true 로 설정합니다.
			forward.setRedirect(true);
			forward.setPath("BoardList.bo"); // 이동할 경로를 지정합니다.
			return forward;
		} catch (IOException ex) {
			ex.printStackTrace();
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "게시판 업로드 실패입니다.");
			forward.setRedirect(false);
			return forward;
			
		} // catch end
	} // execute end 넘어가는 과정*/
}