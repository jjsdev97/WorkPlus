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
		int result = 0;
		String sql = "update member set CHAT_STATUS = ? "
				+ " where M_ID = ?";
		
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			
			pstmt.setString(1, status);
			pstmt.setString(2, id);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("memberStatusUpdate() 에러");
		}
		return null;
	}

	
	public List<Member> getMemberList() {
		
		String member_list_sql = "select m_profilefile, m_name, chat_status, d_name, m_job "
				+ "from member "
				+ "left join dept "
				+ "on member.d_num = dept.d_num "
				+ "left join position "
				+ "on member.p_num = position.p_num "
				+ "order by M_NAME";
		
		List<Member> list = new ArrayList<Member>();
		
		try (Connection con = ds.getConnection(); 
			PreparedStatement pstmt = con.prepareStatement(member_list_sql);){
			
			try(ResultSet rs = pstmt.executeQuery()){
				
				while(rs.next()) {
					Member m = new Member();
					m.setM_PROFILEFILE(rs.getString(1));
					m.setM_NAME(rs.getString(2));
					m.setCHAT_STATUS(rs.getString(3));
					m.setD_NAME(rs.getString(4));
					m.setM_JOB(rs.getString(5));
					
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

	public Member memberinfo(String id) {
		Member m = null;
		String member_list_sql = "select m_profilefile, m_name, chat_status, d_name, m_job, m_id "
				+ "from member "
				+ "left join dept "
				+ "on member.d_num = dept.d_num "
				+ "left join position "
				+ "on member.p_num = position.p_num "
				+ "where m_id = ? "
				+ "order by M_NAME ";

		try (Connection con = ds.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(member_list_sql);){
				pstmt.setString(1, id);
				try(ResultSet rs = pstmt.executeQuery()){
					
					while(rs.next()) {
						m = new Member();
						m.setM_PROFILEFILE(rs.getString(1));
						m.setM_NAME(rs.getString(2));
						m.setCHAT_STATUS(rs.getString(3));
						m.setD_NAME(rs.getString(4));
						m.setM_JOB(rs.getString(5));
						m.setM_ID(rs.getString(6));
						
					}
					
				} catch(SQLException e) {
					e.printStackTrace();
				}
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.out.println("getMemberList()에러 : " + ex);
			}
			
			
			return m;
		}

	public List<Member> getMemberList(String search_word) {
		String member_list_sql = "select m_profilefile, m_name, chat_status, d_name, m_job "
				+ "from member "
				+ "left join dept on member.d_num = dept.d_num "
				+ "left join position on member.p_num = position.p_num "
				+ "where m_name like ?";
		
		List<Member> list = new ArrayList<Member>();
		
		try (Connection con = ds.getConnection(); 
			PreparedStatement pstmt = con.prepareStatement(member_list_sql);){
			
			pstmt.setString(1, "%" + search_word + "%");
			try(ResultSet rs = pstmt.executeQuery()){
				
				while(rs.next()) {
					Member m = new Member();
					m.setM_PROFILEFILE(rs.getString(1));
					m.setM_NAME(rs.getString(2));
					m.setCHAT_STATUS(rs.getString(3));
					m.setD_NAME(rs.getString(4));
					m.setM_JOB(rs.getString(5));
					
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
