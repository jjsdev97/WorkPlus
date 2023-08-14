package net.cafe.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CafeOrderDAO {
	
	private DataSource ds;

	public CafeOrderDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			System.out.println("DB 연결 성공");
		} catch (Exception ex) {
			System.out.println("DB연결 실패: " + ex);
		}
	}
	
	public CafeOrderDAO(DataSource dataSource) {
        this.ds = dataSource;
    }
    
    // 이전 장바구니에 있는 결제 정보(총 가격)를 가져오는 메서드 추가
    public int getTotalPrice(int ORDER_UID) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalPrice = 0;

        try {
            conn = ds.getConnection(); // 데이터베이스 커넥션을 가져옵니다.
            String sql = "SELECT TOTAL_PRICE FROM CAFE_ORDER WHERE ORDER_UID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ORDER_UID);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                totalPrice = rs.getInt("TOTAL_PRICE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return totalPrice;
    }

}
