package net.cafeboard.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {

	private DataSource ds;

	public BoardDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB연결 실패: " + ex);
		}
	}

	public int getListCount() {
		String sql = "select count(*) from CAFE_ITEM";
		int x = 0;
		try (Connection con = ds.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					x = rs.getInt(1); // 1번째 column(count(*)) 가져와서 x에 넣기
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCount() 에러: " + ex);
		}
		return x;
	}

	public List<cafeitemBean> getBoardList(int page, int limit) {
		String sql = "SELECT * " 
				+ " FROM ( " 
				+ "     SELECT rownum rnum, j.* " 
				+ "     FROM ( "
				+ "         SELECT CAFE_ITEM.*, NVL(cnt, 0) AS cnt " 
				+ "         FROM CAFE_ITEM "
				+ "         LEFT OUTER JOIN ( " 
				+ "             SELECT comment_board_num, COUNT(*) AS cnt "
				+ "             FROM comm " 
				+ "             GROUP BY comment_board_num " 
				+ "         ) comm_count "
				+ "         ON CAFE_ITEM.ITEM_UID = comm_count.comment_board_num "
				+ "         ORDER BY ITEM_UID DESC " 
				+ "     ) j " 
				+ "     where rownum <= ? "
				+ "	   ) " 
				+ " WHERE rnum >= ? AND rnum <= ?";
		
		List<cafeitemBean> list = new ArrayList<cafeitemBean>();
		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;

		try (Connection con = ds.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, endrow); // 1번째 column(count(*)) 가져와서 x에 넣기
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					cafeitemBean item = new cafeitemBean();
					item.setITEM_UID(rs.getInt("ITEM_UID"));
					item.setITEM_NAME(rs.getString("ITEM_NAME"));
					item.setITEM_DETAIL(rs.getString("ITEM_DETAIL"));
					item.setITEM_IMG_PATH(rs.getString("ITEM_IMG_PATH"));
					item.setITEM_PRICE(rs.getInt("ITEM_PRICE"));
					item.setITEM_MENU(rs.getString("ITEM_MENU"));
					list.add(item);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCount() 에러: " + ex);
		}
		return list;
	}

	public boolean boardInsert(cafeitemBean b) {
		int result = 0;
		String max_sql = "(select nvl(max(ITEM_UID),0)+1 from CAFE_ITEM)";
		String sql = "INSERT INTO CAFE_ITEM " 
				+ "(ITEM_UID, ITEM_NAME, ITEM_PRICE, ITEM_MENU, ITEM_DETAIL, "
				+ "ITEM_IMG_PATH ) " 
				+ "VALUES (" 
				+ max_sql
				+ ", ?, ?, ?, ?, ?)"; 
		try (Connection con = ds.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, b.getITEM_NAME());
			pstmt.setInt(2, b.getITEM_PRICE());
			pstmt.setString(3, b.getITEM_MENU());
			pstmt.setString(4, b.getITEM_DETAIL());
			pstmt.setString(5, b.getITEM_IMG_PATH());

			// 원문글: (board_re_ref) board_re_lev, board_re_seq 필드 값 = 0
//			pstmt.setInt(4, 0);
//			pstmt.setInt(5, 0);
			

			result = pstmt.executeUpdate(); // 쿼리 실행 및 영향 받은 레코드 개수 반환
			if (result == 1) {
				System.out.println("데이터 삽입 모두 완료");
				return true;
			}
		} catch (Exception e) {
			System.out.println("boardInsert() 에러: " + e);
			e.printStackTrace();
		}
		return false;
	}


	public cafeitemBean getDetail(int num) {
		cafeitemBean item = null;
		String sql = "SELECT * FROM CAFE_ITEM WHERE ITEM_UID = ?";

		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, num);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					item = new cafeitemBean();
					item.setITEM_UID(rs.getInt("ITEM_UID"));
					item.setITEM_NAME(rs.getString("ITEM_NAME"));
					item.setITEM_DETAIL(rs.getString("ITEM_DETAIL"));
					item.setITEM_IMG_PATH(rs.getString("ITEM_IMG_PATH"));
					item.setITEM_PRICE(rs.getInt("ITEM_PRICE"));
					item.setITEM_MENU(rs.getString("ITEM_MENU"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getDetail() 에러: " + ex);
		}
		return item;
	}

	public boolean boardModify(cafeitemBean b) {
		int result = 0;
//		String max_sql = "(select nvl(max(board_num),0)+1 from board)";
	
		String update_sql = "update CAFE_ITEM "
						  + "set ITEM_NAME=?, ITEM_DETAIL=?, "
						  + " ITEM_IMG_PATH=?, ITEM_PRICE=?, ITEM_MENU=? "
		           		  + "where ITEM_UID=? ";

		try (Connection con = ds.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(update_sql);) {

			pstmt.setString(1, b.getITEM_NAME());
			pstmt.setString(2, b.getITEM_DETAIL());
			pstmt.setString(3, b.getITEM_IMG_PATH());
			pstmt.setInt(4, b.getITEM_PRICE());
			pstmt.setString(5, b.getITEM_MENU());
			pstmt.setInt(6, b.getITEM_UID());

			result = pstmt.executeUpdate(); // 쿼리 실행 및 영향 받은 레코드 개수 반환
			if (result == 1) {
				System.out.println("데이터 업데이트 모두 완료");
				return true;
			}
		} catch (Exception e) {
			System.out.println("boardInsert() 에러: " + e);
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean boardDelete(int num) {
	    String delete_sql = "DELETE FROM CAFE_ITEM WHERE ITEM_UID = ?";

	    boolean result_check = false;

	    try (Connection con = ds.getConnection();
	         PreparedStatement pstmtDelete = con.prepareStatement(delete_sql);) {

	        pstmtDelete.setInt(1, num);

	        int count = pstmtDelete.executeUpdate();
	        if (count >= 1) {
	            result_check = true; // 삭제가 안된 경우 false
	        }

	    } catch (Exception e) {
	        System.out.println("boardDelete() 에러: " + e);
	        e.printStackTrace();
	    }
	    return result_check;
	}
}
