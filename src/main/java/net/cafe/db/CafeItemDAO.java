package net.cafe.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.cafe.db.CafeItemDAO;

public class CafeItemDAO {

	private DataSource ds;

	public CafeItemDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
			System.out.println("DB 연결 성공");
		} catch (Exception ex) {
			System.out.println("DB연결 실패: " + ex);
		}
	}
	
	public CafeItemDAO(DataSource dataSource) {
        this.ds = dataSource;
    }
	
	public List<CafeItemBean> getItemsByMenu(String menu) {
	    List<CafeItemBean> itemList = new ArrayList<CafeItemBean>();

	    String sql = "SELECT * FROM CAFE_ITEM WHERE ITEM_MENU = ?";

	    try (Connection con = ds.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql);) {
	    		pstmt.setString(1, menu);
	        	 try(ResultSet rs = pstmt.executeQuery()) {
	        		 while (rs.next()) {
	     	            CafeItemBean item = new CafeItemBean();
	     	            item.setITEM_UID(rs.getInt("ITEM_UID"));
	     	            item.setITEM_NAME(rs.getString("ITEM_NAME"));
	     	            item.setITEM_PRICE(rs.getInt("ITEM_PRICE"));
	     	            item.setITEM_IMG_PATH(rs.getString("ITEM_IMG_PATH"));
	     	            item.setITEM_DETAIL(rs.getString("ITEM_DETAIL"));
	     	            item.setITEM_SOLDOUT(rs.getInt("ITEM_SOLDOUT"));
	     	            item.setITEM_MENU(rs.getString("ITEM_MENU"));
	     	            itemList.add(item);
	        		 }
	 			} catch (SQLException e) {
	 				e.printStackTrace();
	 			}
	 		} catch (Exception ex) {
	 			ex.printStackTrace();
	 			System.out.println("getListCount() 에러: " + ex);
	 		}
	 		return itemList;
	 	}

	public int getListCount() {
		String sql = "select count(*) from CAFE_ITEM";
		int listcount = 0;
		try (Connection con = ds.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					listcount = rs.getInt(1); // 1번째 column(count(*)) 가져와서 x에 넣기
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCount() 에러: " + ex);
		}
		return listcount;
	}
	public List<CafeItemBean> getItemList(int page, int limit) {
		String sql = "SELECT * "
                + "FROM ( "
                + "    SELECT rownum rnum, j.* "
                + "    FROM ( "
                + "        SELECT CAFE_ITEM.* "
                + "        FROM CAFE_ITEM "
                + "    ) j "
                + "    WHERE rownum <= ? "
                + ") "
                + "WHERE rnum >= ? AND rnum <= ?";
		List<CafeItemBean> itemlist = new ArrayList<CafeItemBean>();
		int startrow = (page - 1) * limit + 1;
		int endrow = startrow + limit - 1;

		try (Connection con = ds.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, endrow); // 1번째 column(count(*)) 가져와서 x에 넣기
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			try (ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					CafeItemBean item = new CafeItemBean();
					item.setITEM_UID(rs.getInt("ITEM_UID"));
					item.setITEM_NAME(rs.getString("ITEM_NAME"));
					item.setITEM_PRICE(Integer.parseInt(rs.getString("ITEM_PRICE")));
					itemlist.add(item);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getItemList() 에러: " + ex);
		}
		return itemlist;
	}
	public CafeItemBean getDetail(int num) {
		CafeItemBean item = null;
		String sql = "SELECT * FROM CAFE_ITEM WHERE ITEM_UID = ?";

		try (Connection con = ds.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, num);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					item = new CafeItemBean();
					item.setITEM_UID(rs.getInt("ITEM_UID"));
					item.setITEM_NAME(rs.getString("ITEM_NAME"));
					item.setITEM_PRICE(Integer.parseInt(rs.getString("ITEM_PRICE")));
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
}
