package net.cafemember.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.cafemember.db.cafeMemberDAO;

public class MemberDeleteAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");

        cafeMemberDAO mdao = new cafeMemberDAO();
        int result = mdao.delete(id);

        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<script>");
        if (result == 1) {
            out.println("alert('회원 삭제 완료.');");
            out.println("location.href='memberlist.cnet';");
        } else {
            out.println("alert('회원 삭제 실패.');");
            out.println("history.back();"); // 비밀번호를 제외한 다른 데이터는 유지
        }
        out.println("</script>");
        out.close();
        return null;
    }
}
