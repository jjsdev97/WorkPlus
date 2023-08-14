package net.cafemember.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.cafemember.db.cafeMember;
import net.cafemember.db.cafeMemberDAO;

public class MemberUpdateProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String realFolder = "";
		
		// webapp 아래에 해당 폴더 생성
		String saveFolder = "memberupload";
		
		int fileSize = 5 * 1024 * 1024; // 업로드할 파일 최대 사이즈(5MB)
		
		// 실제 저장 경로 지정
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder: " + realFolder);
		try {
			MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "utf-8",
					new DefaultFileRenamePolicy());
			String id = multi.getParameter("id");
			String name = multi.getParameter("name");
			
			String memberfile = multi.getFilesystemName("memberfile");
			System.out.println("memberfile: " + memberfile);
			
			cafeMember m = new cafeMember();
			m.setId(id);
			m.setName(name);
//			m.setPassword(pass);
			
			
			cafeMemberDAO mdao = new cafeMemberDAO();
			int result = mdao.update(m);
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			if(result == 1) {
				out.println("alert('수정되었습니다.');");
				out.println("location.href='ItemList.ca';");
			} else {
				out.println("alert('회원 정보 수정 실패.');");
				out.println("history.back()"); // 비밀번호를 제외한 다른 데이터는 유지
			}
			out.println("</script>");
			out.close();
			return null;
			
		} catch (IOException ex) {
			ex.printStackTrace();
			ActionForward forward = new ActionForward();
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "프로필 사진 업로드 실패");
			forward.setRedirect(false);
			return forward;
		}
	}
}
