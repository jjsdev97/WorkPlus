package net.cafe.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.cafe.db.CafeOrderDAO;

public class CafePaymentAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // CafeOrderDAO를 활용하여 이전 장바구니에 있는 결제 정보(총 가격)를 가져옵니다.
        int ORDER_UID = 123; // ORDER_UID를 임의로 지정(실제로는 해당 정보를 파라미터 등으로 받아오기)
        CafeOrderDAO cafeOrderDAO = new CafeOrderDAO();
        int totalPrice = cafeOrderDAO.getTotalPrice(ORDER_UID);

        // totalPrice를 결제 위젯 샘플 페이지(index.html)로 전달하기 위해 request에 저장합니다.
        request.setAttribute("totalPrice", totalPrice);

        // index.html로 이동하기 위해 ActionForward 객체를 생성합니다.
        ActionForward forward = new ActionForward();
        forward.setRedirect(false);
        forward.setPath("/payments/payindex.html"); // 결제 위젯 샘플 페이지(index.html)으로 이동
        return forward;
    }
}
