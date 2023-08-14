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
	
	//chatstatus
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

	
	public List<Member> getMemberList(String id) {
		
		String member_list_sql = "select m_profilefile, m_name, chat_status, d_name, m_job, m_id , c_object  "
				+ "from member  "
				+ "left join dept on member.d_num = dept.d_num  "
				+ "left join position on member.p_num = position.p_num  "
				+ "full outer join CHAT_FRIEND_BOOKMARK on member.m_id = CHAT_FRIEND_BOOKMARK.c_object \r\n"
				+ "where member.m_id !=  'admin' "
				+ "and member.m_id != ? "
				+ "order by M_NAME";
		
		List<Member> list = new ArrayList<Member>();
		
		try (Connection con = ds.getConnection(); 
			PreparedStatement pstmt = con.prepareStatement(member_list_sql);){
			
			pstmt.setString(1, id);
			

			try(ResultSet rs = pstmt.executeQuery()){
				
				while(rs.next()) {
					
					Member m = new Member();
					m.setM_PROFILEFILE(rs.getString(1));
					m.setM_NAME(rs.getString(2));
					m.setCHAT_STATUS(rs.getString(3));
					m.setD_NAME(rs.getString(4));
					m.setM_JOB(rs.getString(5));
					m.setM_ID(rs.getString(6));
					m.setC_object(rs.getString(7));
					
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

	public List<Member> getMemberList(String id, String search_word) {
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

	
	//친구 즐겨찾기
	public Member addFBookMark(String id, String f_id) {
		int result = 0;
		
		String sql = "insert into CHAT_FRIEND_BOOKMARK  "
				+ "(FBOOKMARK_NUM, C_SUBJECT, C_OBJECT)  "
				+ "values ( fbookmark_seq.nextval , ?  , ? )";
		
		try( Connection con = ds.getConnection();
				 PreparedStatement pstmt = con.prepareStatement(sql);) {
				
				pstmt.setString(1, id);
				pstmt.setString(2, f_id);
				
				
				result = pstmt.executeUpdate();	//삽입 성공시 result는 1
				if(result==1) {
					System.out.println("FBookMark 삽입 성공");
				}else {
					System.out.println("FBookMark 삽입 실패");
				}
				
				 } 	catch (Exception e) {
						e.printStackTrace();
				}
		

		return null;
	}
	
//
//	public void createRoom(String id, String f_id) {
//		int result = 0;
//		String max_sql = "(select nvl(max(m_num),0)+1 from chat)";
//		
//		String sql = "insert into chat  "
//				+ "(CHAT_ID, CHAT_FROM, CHAT_TO) "
//				+ "values (1,  ?, ? )";
//		
//		try( Connection con = ds.getConnection();
//				 PreparedStatement pstmt = con.prepareStatement(sql);) {
//				
//				pstmt.setString(1, id);
//				pstmt.setString(2, f_id);
//				
//				
//				result = pstmt.executeUpdate();	//삽입 성공시 result는 1
//				 } 	catch (Exception e) {
//						e.printStackTrace();
//				}
//					
//		
//	}
	//친구 즐겨찾기 삭제
	public Member removeFBookMark(String id, String f_id) {
	int result = 0;
		
		String sql = "delete from CHAT_FRIEND_BOOKMARK "
				+ "where C_SUBJECT = ? "
				+ "and C_OBJECT = ? ";
		
		try( Connection con = ds.getConnection();
				 PreparedStatement pstmt = con.prepareStatement(sql);) {
				
				pstmt.setString(1, id);
				pstmt.setString(2, f_id);
				
				
				result = pstmt.executeUpdate();	//삽입 성공시 result는 1
				
				if(result==1) {
					System.out.println("FBookMark 삭제 성공");
				}else {
					System.out.println("FBookMark 삭제 실패");
				}
				
				 } 	catch (Exception e) {
						e.printStackTrace();
				}

		return null;
	}

	
}
