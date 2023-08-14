package net.cafemember.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class cafeMemberDAO {

	private DataSource ds;

	public cafeMemberDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB연결 실패: " + ex);
		}
	}

	public int isID(String id) {
		int result = -1; // DB에 해당 id가 없다는 값 => 사용 가능한 id
		String sql = "select id "
				+ "from CAFE_MEMBER "
				+ "where id=?";
		
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, id);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) { // 뭔가 값이 있을 경우(아이디 일치)
					result = 0; // DB에 이미 해당 id 존재 한다는 값 => 중복 id
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int isID(String id, String pass) {
		int result = -1; // DB에 해당 id가 없다는 값 => 사용 가능한 id
		String sql = "select id, pass "
				+ "from CAFE_MEMBER "
				+ "where id=?";
		
		try(Connection con = ds.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, id);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) { // 뭔가 값이 있을 경우(아이디 일치)
					// rs.getString(2): 내가 입력한 값중 2번째 column = password
					if(rs.getString(2).equals(pass)) {
						result = 1; // 비밀번호 일치
					} else {
						result = 0; // 비밀번호 불일치
					}
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int insert(cafeMember m) {
	    int result = 0; // DB 삽입 실패 // -1
	    
	    String sql = "INSERT INTO CAFE_MEMBER(id, pass, name) "
	    		   + "VALUES (?, ?, ?)";

	    try (Connection con = ds.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql);) {
	        pstmt.setString(1, m.getId());
	        pstmt.setString(2, m.getPassword());
	        pstmt.setString(3, m.getName());

	        result = pstmt.executeUpdate(); // 삽입 성공 시 1, 실패 시 0 반환

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}

	public cafeMember member_info(String id) {
		
		cafeMember m = null;
		String sql = "select * from CAFE_MEMBER where id = ? ";
		
		try (Connection con = ds.getConnection();
			 PreparedStatement pstmt = con.prepareStatement(sql);) {
			 pstmt.setString(1, id);
			 try(ResultSet rs = pstmt.executeQuery()) {
				 if(rs.next()) {
					 m = new cafeMember();
					 m.setId(rs.getString(1));
					 m.setPassword(rs.getString(2));
					 m.setName(rs.getString(3));
					
				 }
			 } catch (SQLException e) {
				 e.printStackTrace();
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return m;
	}

	public int update(cafeMember m) {
		int result = 0;
		String sql = "update CAFE_MEMBER "
				   + "set name=? "
				   + "where id=?";
		
		try (Connection con = ds.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setString(1, m.getName());
			pstmt.setString(2, m.getId());

			result = pstmt.executeUpdate(); // 쿼리 실행 및 영향 받은 레코드 개수 반환
		} catch (Exception e) {
			System.out.println("update() 에러: " + e);
			e.printStackTrace();
		}
		return result;
	}
	
	public int getListCount() {
	    int count = 0;
	    String sql = "select count(*) from CAFE_MEMBER";
	    
	    try (Connection con = ds.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery()) {
	        
	        if (rs.next()) {
	            count = rs.getInt(1);
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return count;
	}


	public List<cafeMember> getList(int page, int limit) {
	    List<cafeMember> memberList = new ArrayList<>();
	    String sql = "select * from (select rownum rnum, m.* from (select * from CAFE_MEMBER order by id) m) "
	               + "where rnum between ? and ?";
	    int startRow = (page - 1) * limit + 1;
	    int endRow = startRow + limit - 1;
	    
	    try (Connection con = ds.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {
	        
	        pstmt.setInt(1, startRow);
	        pstmt.setInt(2, endRow);
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	            	cafeMember m = new cafeMember();
	                m.setId(rs.getString("id"));
	                m.setPassword(rs.getString("pass"));
	                m.setName(rs.getString("name"));
	                memberList.add(m);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return memberList;
	}


	public int getListCount(String columnName, String search_word) {
	    int count = 0;
	    String sql = "select count(*) from CAFE_MEMBER where " + columnName + " like ?";
	    
	    try (Connection con = ds.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {
	        
	        pstmt.setString(1, "%" + search_word + "%");
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                count = rs.getInt(1);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return count;
	}


	public List<cafeMember> getList(String columnName, String search_word, int page, int limit) {
	    List<cafeMember> memberList = new ArrayList<>();
	    String sql = "select * from (select rownum rnum, m.* from (select * from CAFE_MEMBER where "
	               + columnName + " like ? order by id) m) "
	               + "where rnum between ? and ?";
	    int startRow = (page - 1) * limit + 1;
	    int endRow = startRow + limit - 1;
	    
	    try (Connection con = ds.getConnection();
	         PreparedStatement pstmt = con.prepareStatement(sql)) {
	        
	        pstmt.setString(1, "%" + search_word + "%");
	        pstmt.setInt(2, startRow);
	        pstmt.setInt(3, endRow);
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                cafeMember m = new cafeMember();
	                m.setId(rs.getString("id"));
	                m.setPassword(rs.getString("pass"));
	                m.setName(rs.getString("name"));
	                memberList.add(m);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return memberList;
	}
	
	public int delete(String id) {
		int result = 0;
		String sql = "delete from CAFE_MEMBER where id = ?";
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {
				pstmt.setString(1, id);
				result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
