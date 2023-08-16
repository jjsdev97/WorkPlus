package net.cafe.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CafeCartClear implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 세션에서 장바구니를 가져옵니다.
        HttpSession session = request.getSession();
        clearCart(session);

        // 사용자를 홈페이지로 리다이렉트합니다.
        ActionForward forward = new ActionForward();
        forward.setPath("/main.cafe");
        forward.setRedirect(true);
        return forward;
    }

    // 장바구니 초기화 기능을 수행하는 메소드
    public void clearCart(HttpSession session) {
        // 장바구니를 초기화합니다.
        session.removeAttribute("cartItemList");
        session.setAttribute("totalPrice", 0);
    }
}
