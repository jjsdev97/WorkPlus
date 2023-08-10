package et.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.member.db.Member;
import net.member.db.MemberDAO;

public class MemberUpdateProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String realFolder="profileupload";
		
		int filesize =  5 * 1024 * 1024;	//업로드할 파일의 최대 사이즈 5MB
		
		//실제 경로 저장
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(realFolder);
		System.out.println("realFolder = " +  realFolder);
		
		try {
			MultipartRequest multi = new MultipartRequest(request, realFolder, filesize, 
					"utf-8", new DefaultFileRenamePolicy());
			
			String id = multi.getParameter("id");
			String name = multi.getParameter("name");
			int empnum = Integer.parseInt(multi.getParameter("empnum"));
			String pass = multi.getParameter("pass");
			
			String profilefile = multi.getFilesystemName("profilefile");
			System.out.println("memberfile= " + profilefile);
			
			Member m = new Member();
			m.setM_ID(id);
			m.setM_NAME(name);
			m.setE_NUM(empnum);
			m.setM_PASS(pass);
			m.setM_PROFILEFILE(profilefile);

			
			if(profilefile != null) {
				m.setM_PROFILEFILE(profilefile);
			} 
			else {
				m.setM_PROFILEFILE(multi.getParameter("profilefile"));
			}
			
			MemberDAO mdao = new MemberDAO();
			int result = mdao.update(m);
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			//삽입이 된 경우
			if (result == 1) {
				out.println("alert('수정되었습니다.');");
				out.println("location.href='main.com';");
			} else {
				out.println("alert('회원정보 수정에 실패했습니다.');");
				out.println("history.back()");
			}
			out.println("</script>");
			out.close();
			return null;
			
		} catch(IOException ex) {
			ex.printStackTrace();
			ActionForward forward = new ActionForward();
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "프로필 사진 업로드 실패입니다.");
			forward.setRedirect(false);
			return forward;
	} catch(NumberFormatException e) {
		e.printStackTrace();
		ActionForward forward = new ActionForward();
		forward.setPath("error/error.jsp");
		request.setAttribute("message", "프로필 사진 업로드 실패입니다.");
		forward.setRedirect(false);
		return forward;
	}
	}
}
