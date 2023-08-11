package net.board.db;

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

	// 생성자 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다. ★보내주는 역할
	public BoardDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB 연결실패 : " + ex);
		}
	} // 통로만들기
	 // 글 등록하기
	 
	public boolean boardInsert(BoardBean board) {
		int result=0;
		String max_sql = "(select nvl(max(board_num),0)+1 from board)";
		
		//원문글의 BOARD_RE_REF 필드는 자신의 글번호입니다.
		String sql = "insert into board "   //내가 값을 넣을 모든 컬럼을 기재 insert into쿼리문 사용
				+ "(BOARD_NUM, BOARD_TYPE, BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, "
				+ "BOARD_WRITER, BOARD_DATE, BOARD_READCOUNT, BOARD_NOTICE, BOARD_LIKECOUNT) "
				+ "VALUES("+ max_sql +", ?,?,?,?,?, sysdate,0,0,0)";
				  // insert into(컬럼1,컬럼2)VALUES(값1, 값2) 컬럼과 값의 1대1 교환 ?와 개수가 맞아야함
				
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {  //con에 sql문을 실행하고 그 값을 pstmt 에 저장
			//새로운 글을 등록하는 부분입니다.
			pstmt.setString(1,  board.getBOARD_TYPE()); //게시판 종류
			pstmt.setString(2,  board.getBOARD_SUBJECT()); // 게시판 제목
			pstmt.setString(3,  board.getBOARD_CONTENT()); // 게시판 내용
			pstmt.setString(4,  board.getBOARD_FILE()); //파일첨부
			pstmt.setString(5,  board.getBOARD_WRITER()); //작성자
			/*
			 * pstmt.setInt(6, board.getBOARD_DATE()); //작성일자 pstmt.setInt(7,
			 * board.getBOARD_READCOUNT()); //조회수 카운팅 pstmt.setString(8,
			 * board.getBOARD_NOTICE()); // 공지글 pstmt.setInt(9, board.getBOARD_LIKECOUNT());
			 * // 좋아요 개수
			 */		    
		    result = pstmt.executeUpdate(); //sql문이 적용된 행의 갯수
		    if(result==1) {
		    	System.out.println("데이터 삽입이 모두 완료되었습니다.");
		    	return true;
		    	}
		    } catch (Exception ex) {
		    	System.out.println("boardInsert()에러 : " + ex);
		    	ex.printStackTrace();
		    }
		return false;
	}//boardInsert()메서드 end
	
	//글의 갯수 구하기
		public int getListCount() {
			String sql = "select count(*) from board"; 
			int x = 0;
			 try (Connection con = ds.getConnection();
				  PreparedStatement pstmt = con.prepareStatement(sql);){ 
				 
					try (ResultSet rs = pstmt.executeQuery()) { /* pstmt의 sql문을 실행 */
						if (rs.next()) { /* rs의 다음 값이 있을 시 */
						  x =rs.getInt(1);
					  }
				 } catch (SQLException e) {
					 e.printStackTrace();
				 }
			 } catch (Exception ex) {
				 ex.printStackTrace();
				 System.out.println("getListCount() 에러: " + ex);
			 }
			return x;
		}// getListCount() end
		
		public List<BoardBean> getBoardList(int page, int limit){
			
			// page : 페이지
			// limit : 페이지 당 목록의 수
			// board_re_ref desc, board_re_seq asc에 의해 정렬한 것을
			// 조건절에 맞는 rnum의 범위 만큼 가져오는 쿼리문입니다.
			
			String board_list_sql = "select * "
					+ "FROM (SELECT rownum rnum, j.* "
					+ "		FROM (SELECT BOARD.*, NVL(cnt, 0) AS cnt "
					+ "				FROM board LEFT OUTER JOIN ( SELECT comment_board_num, COUNT(*) AS cnt "
					+ "							 				 FROM comm  "
					+ "											GROUP BY comment_board_num ) comm_count "
					+ "				ON board.BOARD_NUM = comm_count.comment_board_num "
					+ "				ORDER BY BOARD_NUM desc ) j "
					+ "				WHERE rownum <= ? ) "
					+ "WHERE rnum >= ? AND rnum <= ?";
		
			List<BoardBean> list = new ArrayList<BoardBean>();
			// 한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지 ....
			int startrow = (page - 1) * limit + 1;	// 읽기 시작할 row 번호(1 11 21 31 ...
			int endrow = startrow + limit - 1; 		// 읽을 마지막 row 번호(10 20 30 40 ...
			try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(board_list_sql);){
				pstmt.setInt(1, endrow);
				pstmt.setInt(2, startrow);
				pstmt.setInt(3, endrow);
				
				 try(ResultSet rs = pstmt.executeQuery()){
					 
					 //DB에서 가져온 데이터를 BoardBean에 담습니다.
					 while(rs.next()) {
						 BoardBean board = new BoardBean();
						 board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
						 board.setBOARD_TYPE(rs.getString("BOARD_TYPE"));
						 board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
						 board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
						 board.setBOARD_FILE(rs.getString("BOARD_FILE"));
						 board.setBOARD_WRITER(rs.getString("BOARD_WRITER"));
						 board.setBOARD_DATE(rs.getInt("BOARD_DATE"));
						 board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
						 board.setBOARD_NOTICE(rs.getString("BOARD_NOTICE"));
						 board.setBOARD_LIKECOUNT(rs.getInt("BOARD_LIKECOUNT"));
						 board.setCnt(rs.getInt("cnt"));
						 list.add(board);	//값을 담은 객체를 리스트에 저장합니다.
					 }
				 } catch (SQLException e) {
					 e.printStackTrace();
				 }
			 } catch (Exception ex) {
				 ex.printStackTrace();
				 System.out.println("getListCount() 에러: " + ex);
			 }
			return list;	
		} //getBoardList end
		
		/*
		 * //글 목록 보기 public List<BoardBean> getBoardList(int page, int limit){
		 * 
		 * // page : 페이지 // limit : 페이지 당 목록의 수 // board_re_ref desc, board_re_seq asc에
		 * 의해 정렬한 것을 // 조건절에 맞는 rnum의 범위 만큼 가져오는 쿼리문입니다.
		 * 
		 * String board_list_sql = "select * " + "from ( select rownum rnum, j.* " +
		 * "		from(select board.*, nvl(cnt,0) cnt " +
		 * "				from BOARD left outer join (select comment_board_num, count(*) cnt "
		 * + "							 				from comm " +
		 * "											group by comment_board_num) " +
		 * "				on board_num = comment_board_num " +
		 * "				order by board_re_ref desc, " +
		 * "				board_re_seq asc)j " + "			where rownum <= ? " +
		 * "		 ) " + "where rnum>=? and rnum<=? ";
		 * 
		 * List<BoardBean> list = new ArrayList<BoardBean>(); // 한 페이지당 10개씩 목록인 경우
		 * 1페이지, 2페이지, 3페이지, 4페이지 .... int startrow = (page - 1) * limit + 1; // 읽기 시작할
		 * row 번호(1 11 21 31 ... int endrow = startrow + limit - 1; // 읽을 마지막 row 번호(10
		 * 20 30 40 ... try(Connection con = ds.getConnection(); PreparedStatement pstmt
		 * = con.prepareStatement(board_list_sql);){ pstmt.setInt(1, endrow);
		 * pstmt.setInt(2, startrow); pstmt.setInt(3, endrow);
		 * 
		 * try(ResultSet rs = pstmt.executeQuery()){
		 * 
		 * //DB에서 가져온 데이터를 BoardBean에 담습니다. while(rs.next()) { BoardBean board = new
		 * BoardBean(); board.setBoard_num(rs.getInt("BOARD_NUM"));
		 * board.setBoard_name(rs.getString("BOARD_NAME"));
		 * board.setBoard_subject(rs.getString("BOARD_SUBJECT"));
		 * board.setBoard_content(rs.getString("BOARD_CONTENT"));
		 * board.setBoard_file(rs.getString("BOARD_FILE"));
		 * board.setBoard_re_ref(rs.getInt("BOARD_RE_REF"));
		 * board.setBoard_re_lev(rs.getInt("BOARD_RE_LEV"));
		 * board.setBoard_re_seq(rs.getInt("BOARD_RE_SEQ"));
		 * board.setBoard_readcount(rs.getInt("BOARD_READCOUNT"));
		 * board.setBoard_date(rs.getString("BOARD_DATE"));
		 * board.setCnt(rs.getInt("cnt")); list.add(board); //값을 담은 객체를 리스트에 저장합니다. } }
		 * catch (SQLException e) { e.printStackTrace(); } } catch (Exception ex) {
		 * ex.printStackTrace(); System.out.println("getListCount() 에러: " + ex); }
		 * return list; }
		 * 
		 * 
		 * public boolean boardInsert(BoardBean board) { int result = 0; String max_sql
		 * = "(select nvl(max(board_num),0)+1 from board)";
		 * 
		 * //원문글의 BOARD_RE_REF 필드는 자신의 글번호 입니다. String sql = "insert into board" +
		 * "(BOARD_NUM,	   BOARD_NAME,	 BOARD_PASS, BOARD_SUBJECT, " +
		 * " BOARD_CONTENT, BOARD_FILE, 	 BOARD_RE_REF," +
		 * " BOARD_RE_LEV,  BOARD_RE_SEQ, BOARD_READCOUNT) " + " values(" + max_sql
		 * +", ?, ?, ?, " + "			?,?, " + max_sql + "," + "			?,?,?)";
		 * 
		 * try(Connection con = ds.getConnection(); PreparedStatement pstmt =
		 * con.prepareStatement(sql);){
		 * 
		 * //새로운 글을 등록하는 부분입니다. pstmt.setString(1, board.getBoard_name());
		 * pstmt.setString(2, board.getBoard_pass()); pstmt.setString(3,
		 * board.getBoard_subject()); pstmt.setString(4, board.getBoard_content());
		 * pstmt.setString(5, board.getBoard_file());
		 * 
		 * //원문의 경우 BOARD_RE_LEV, BOARD_RE_SEQ 필드 값은 0입니다. pstmt.setInt(6, 0); //
		 * BOARD_RE_LEV 필드 pstmt.setInt(7, 0); // BOARD_RE_SEQ 필드 pstmt.setInt(8, 0); //
		 * BOARD_READCOUNT 필드
		 * 
		 * result = pstmt.executeUpdate(); if(result == 1) {
		 * System.out.println("데이터 삽입이 모두 완료되었습니다."); return true; } } catch (Exception
		 * ex) { ex.printStackTrace(); System.out.println("boardInsert() 에러: " + ex); }
		 * return false; }//boardinsert() end
		 */
}
