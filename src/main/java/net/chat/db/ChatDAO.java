package net.chat.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.member.db.Member;

public class ChatDAO {
	private DataSource ds;
	
	public ChatDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception ex) {
			System.out.println("DB 연결 실패:" + ex);
		}
	}
	
	public Member memberStatusUpdate(String id, String status) {

		String sql = "update member set CHAT_STATUS = ?"
				+ " where M_ID = ?";
		
		
		
		
		
		return null;
		
	}

	public List<Member> getMemberList() {
		
		String member_list_sql = "select * from member ";
		
		List<Member> list = new ArrayList<Member>();
		
		try (Connection con = ds.getConnection(); 
			PreparedStatement pstmt = con.prepareStatement(member_list_sql);){
			
			try(ResultSet rs = pstmt.executeQuery()){
				
				while(rs.next()) {
					Member m = new Member();
					m.setM_NUM(rs.getInt(1));
					m.setM_NAME(rs.getString(2));
					m.setM_ID(rs.getString(3));
					m.setM_PASS(rs.getString(4));
					m.setE_NUM(rs.getInt(5));
					m.setVERIFY_EMAIL(rs.getString(6));
					m.setM_HIREDATE(rs.getDate(7));
					m.setD_NUM(rs.getInt(8));
					m.setP_NUM(rs.getString(9));
					m.setR_ADMIT(rs.getString(10));
					m.setM_STATUS(rs.getString(11));
					m.setCHAT_STATUS(rs.getString(12));
					m.setM_ADMIN(rs.getString(13));
					
					
					list.add(m);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getMemberList()에러 : " + ex);
		}
		
		
		return list;
	}
	
}
