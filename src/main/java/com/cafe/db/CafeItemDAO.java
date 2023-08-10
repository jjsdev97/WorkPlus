package com.cafe.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CafeItemDAO {

	private DataSource ds;

	public CafeItemDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB연결 실패: " + ex);
		}
	}

	public List<CafeItemBean> getAllItems() {
		List<CafeItemBean> itemList = new ArrayList<>();

		String sql = "SELECT * FROM CAFE_ITEM";

		try (Connection con = ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			try (ResultSet rs = pstmt.executeQuery()) {
				CafeItemBean item = new CafeItemBean();
				item.setItemUid(rs.getInt("ITEM_UID"));
				item.setItemName(rs.getString("ITEM_NAME"));
				item.setItemPrice(rs.getInt("ITEM_PRICE"));
				item.setItemImgPath(rs.getString("ITEM_IMG_PATH"));
				item.setItemDetail(rs.getString("ITEM_DETAIL"));
				item.setItemSoldOut(rs.getInt("ITEM_SOLDOUT"));
				item.setItemMenu(rs.getString("ITEM_MENU"));

				itemList.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return itemList;
	}

	public List<CafeItemBean> getCoffeeItems() {
	    List<CafeItemBean> itemList = new ArrayList<>();

	    String sql = "SELECT * FROM CAFE_ITEM WHERE ITEM_MENU = 'coffee'";

	    try (Connection con = ds.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery()) {

	        while (rs.next()) {
	            CafeItemBean item = new CafeItemBean();
	            item.setItemUid(rs.getInt("ITEM_UID"));
	            item.setItemName(rs.getString("ITEM_NAME"));
	            item.setItemPrice(rs.getInt("ITEM_PRICE"));
	            item.setItemImgPath(rs.getString("ITEM_IMG_PATH"));
	            item.setItemDetail(rs.getString("ITEM_DETAIL"));
	            item.setItemSoldOut(rs.getInt("ITEM_SOLDOUT"));
	            item.setItemMenu(rs.getString("ITEM_MENU"));

	            itemList.add(item);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return itemList;
	}


}
