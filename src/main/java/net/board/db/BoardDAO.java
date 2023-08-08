package net.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
				+ "VALUES("+ max_sql +", ?,?,?,?,?,?,?,?,?)";
				  // insert into(컬럼1,컬럼2)VALUES(값1, 값2) 컬럼과 값의 1대1 교환 ?와 개수가 맞아야함
				
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);) {  //con에 sql문을 실행하고 그 값을 pstmt 에 저장
			//새로운 글을 등록하는 부분입니다.
			pstmt.setString(1,  board.getBOARD_TYPE());
			pstmt.setString(2,  board.getBOARD_SUBJECT());
			pstmt.setString(3,  board.getBOARD_CONTENT());
			pstmt.setString(4,  board.getBOARD_FILE());
			pstmt.setString(5,  board.getBOARD_WRITER());
		    pstmt.setInt(6,     board.getBOARD_DATE());
		    pstmt.setInt(7,     board.getBOARD_READCOUNT());
		    pstmt.setInt(8,  board.getBOARD_DATE());
		    pstmt.setInt(9,  board.getBOARD_DATE());
		    
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
	
	/*   글의 갯수 구하기 , 글 목록보기, 글 등록하기, 조회수 업데이트, 글 삭제
	 * // 글의 갯수 구하기 public int getListCount() { String sql =
	 * "select count(*) from board"; int x = 0; try (Connection con =
	 * ds.getConnection(); // 데이터베이스 연결 PreparedStatement pstmt =
	 * con.prepareStatement(sql);) {
	 * 
	 * try (ResultSet rs = pstmt.executeQuery()){ 쿼리실행 후 그 값을 rs에 저장) if (rs.next())
	 * { //값이 있으면 true 없으면 x = rs.getInt(1); }
	 * 
	 * } catch (Exception ex) { ex.printStackTrace();
	 * System.out.println("getListCount() 에러 : " + ex); }
	 * 
	 * } catch (Exception ex) { ex.printStackTrace();// getListCount() end } return
	 * x; }
	 * 
	 * // 글 목록보기 public List<BoardBean> getBoardList(int page, int limit) {
	 * 
	 * // page : 페이지 // limit :페이지당 목록의 수 // board_re_ref desc, board_re_seq asc 에
	 * 의해 정렬한 것을 // 조건절에 맞는 rnum의 범위만큼 가져오는 쿼ㅣ문입니다.
	 * 
	 * String sql = "SELECT * " + " FROM ( " + "     SELECT rownum rnum, j.* " +
	 * "     FROM ( " + "         SELECT BOARD.*, NVL(cnt, 0) AS cnt " +
	 * "         FROM board " + "         LEFT OUTER JOIN ( " +
	 * "             SELECT comment_board_num, COUNT(*) AS cnt " +
	 * "             FROM comm " + "             GROUP BY comment_board_num " +
	 * "         ) comm_count " +
	 * "         ON board.BOARD_NUM = comm_count.comment_board_num " +
	 * "         ORDER BY BOARD_RE_REF DESC, BOARD_RE_SEQ ASC " + "     ) j " +
	 * "     where rownum <= ? " + "	   ) " + " WHERE rnum >= ? AND rnum <= ?";
	 * ArrayList<BoardBean> list = new ArrayList<BoardBean>(); // 한페이지당 10개씩 목록인 경우
	 * 1페이지, 2페이지, 3페이지, 4페이지--- int startrow = (page - 1) * limit + 1;// 읽기 시작할 row
	 * 번호 (1. 11. 21. 31,,,, int endrow = startrow + limit - 1; // 읽을 마지막 페이지 row번호
	 * (10,20,30 ... try (Connection con = ds.getConnection(); PreparedStatement
	 * pstmt = con.prepareStatement(sql);) { pstmt.setInt(1, endrow);
	 * pstmt.setInt(2, startrow); pstmt.setInt(3, endrow);
	 * 
	 * try (ResultSet rs = pstmt.executeQuery()) { // DB에서 가져온 데이터를 BoardBean에 담습니다.
	 * while (rs.next()) { BoardBean board = new BoardBean();
	 * board.setBoard_num(rs.getInt("BOARD_NUM"));
	 * board.setBoard_name(rs.getString("BOARD_NAME"));
	 * board.setBoard_subject(rs.getString("BOARD_SUBJECT"));
	 * board.setBoard_content(rs.getString("BOARD_CONTENT"));
	 * board.setBoard_file(rs.getString("BOARD_FILE"));
	 * board.setBoard_re_ref(rs.getInt("BOARD_RE_REF"));
	 * board.setBoard_re_lev(rs.getInt("BOARD_RE_LEV"));
	 * board.setBoard_re_seq(rs.getInt("BOARD_RE_SEQ"));
	 * board.setBoard_readcount(rs.getInt("BOARD_READCOUNT"));
	 * board.setBoard_date(rs.getString("BOARD_DATE")); list.add(board); // 값을 담은
	 * 객체를 리스트에 저장합니다. } } catch (SQLException e) { e.printStackTrace(); } } catch
	 * (Exception ex) { ex.printStackTrace();
	 * System.out.println("getBoardList() 에러 :" + ex); } return list; }
	 * 
	 * 
	 * // 글 등록하기 public boolean boardInsert(BoardBean board) { int result=0; String
	 * max_sql = "(select nvl(max(board_num),0)+1 from board)";
	 * 
	 * //원문글의 BOARD_RE_REF 필드는 자신의 글번호입니다. String sql = "insert into board " +
	 * "(BOARD_NUM,        BOARD_NAME, BOARD_PASS, BOARD_SUBJECT," +
	 * " BOARD_CONTENT,   BOARD_FILE, BOARD_RE_REF," +
	 * "BOARD_RE_LEV,     BOARD_RE_SEQ, BOARD_READCOUNT)" + "values(" + max_sql +
	 * ",?,?,?," + "         ?,?," + max_sql + "," + "       ?,?,?)"; try
	 * (Connection con = ds.getConnection(); PreparedStatement pstmt =
	 * con.prepareStatement(sql);) { //새로운 글을 등록하는 부분입니다. pstmt.setString(1,
	 * board.getBoard_name()); pstmt.setString(2, board.getBoard_pass());
	 * pstmt.setString(3, board.getBoard_subject()); pstmt.setString(4,
	 * board.getBoard_content()); pstmt.setString(5, board.getBoard_file());
	 * 
	 * 
	 * //원문의 경우 BOARD_RE_REV, BOARD_RE_SEQ필드 값은 0 입니다. pstmt.setInt(6,0);
	 * //BOARD_RE_LEV 필드 pstmt.setInt(7,0); //BOARD_RE_SEQ 필드 pstmt.setInt(8,0);
	 * //BOARD_READCOUNT 필드
	 * 
	 * result = pstmt.executeUpdate(); if(result==1) {
	 * System.out.println("데이터 삽입이 모두 완료되었습니다."); return true; } } catch (Exception
	 * ex) { System.out.println("boardInsert()에러 : " + ex); ex.printStackTrace(); }
	 * return false; }//boardInsert()메서드 end
	 * 
	 * 
	 * public int insert(Member m) { int result = -1; // DB 삽입 실패
	 * 
	 * String sql = "INSERT INTO member (id, password, name, age, gender, email) " +
	 * "VALUES (?, ?, ?, ?, ?, ?)";
	 * 
	 * try (Connection con = ds.getConnection(); PreparedStatement pstmt =
	 * con.prepareStatement(sql);) { pstmt.setString(1, m.getId());
	 * pstmt.setString(2, m.getPassword()); pstmt.setString(3, m.getName());
	 * pstmt.setInt(4, m.getAge()); pstmt.setString(5, m.getGender());
	 * pstmt.setString(6, m.getEmail());
	 * 
	 * result = pstmt.executeUpdate(); // 삽입 성공 시 1, 실패 시 0 반환
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } catch (Exception e) {
	 * e.printStackTrace(); }
	 * 
	 * return result; }// insert end
	 * 
	 * 
	 * public int isId(String id, String pass) { int result = -1;// 아이디가 존재하지 않습니다.
	 * String sql = "select id, password from member where id = ?"; try (Connection
	 * con = ds.getConnection(); PreparedStatement pstmt =
	 * con.prepareStatement(sql);) {
	 * 
	 * pstmt.setString(1, id); try (ResultSet rs = pstmt.executeQuery()) { if
	 * (rs.next()) { if (rs.getString(2).equals(pass)) { result = 1; // 아이디와 비번 일치
	 * 경우 } else { result = 0; // 비밀번호 일치하지 않는경우 } } } catch (SQLException e) {
	 * e.printStackTrace(); } } catch (Exception e) { e.printStackTrace(); } return
	 * result; }// isId end
	 * 
	 * // 조회수 업데이트 - 글번호에 해당하는 조회수를 1 증가합니다. public void setReadCountUpdate(int num)
	 * {
	 * 
	 * String sql = "update board " + "set BOARD_READCOUNT=BOARD_READCOUNT+1 " +
	 * "where BOARD_NUM = ?"; try (Connection con = ds.getConnection();
	 * PreparedStatement pstmt = con.prepareStatement(sql);) { pstmt.setInt(1, num);
	 * pstmt.executeUpdate(); } catch (SQLException ex) {
	 * System.out.println("setReadCountUpdate() 에러: " + ex); } }//
	 * setReadCountUpdate()메서드 end // 글 내용 보기 public BoardBean getDetail(int num) {
	 * BoardBean board = null; String sql =
	 * "select * from board where BOARD_NUM = ?"; try (Connection con =
	 * ds.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
	 * pstmt.setInt(1, num); try (ResultSet rs = pstmt.executeQuery()) { if
	 * (rs.next()) { board = new BoardBean();
	 * board.setBoard_num(rs.getInt("BOARD_NUM"));
	 * board.setBoard_name(rs.getString("BOARD_NAME"));
	 * board.setBoard_subject(rs.getString("BOARD_SUBJECT"));
	 * board.setBoard_content(rs.getString("BOARD_CONTENT"));
	 * board.setBoard_file(rs.getString("BOARD_FILE"));
	 * board.setBoard_re_ref(rs.getInt("BOARD_RE_REF"));
	 * board.setBoard_re_lev(rs.getInt("BOARD_RE_LEV"));
	 * board.setBoard_re_seq(rs.getInt("BOARD_RE_SEQ"));
	 * board.setBoard_readcount(rs.getInt("BOARD_READCOUNT"));
	 * board.setBoard_date(rs.getString("BOARD_DATE")); } }catch (SQLException e) {
	 * e.printStackTrace(); } } catch (Exception ex) {
	 * System.out.println("getDetail() 에러: " + ex); } return board; }//
	 * getDetail()메서드 end //글쓴이인지 확인 - 비밀번호로 확인합니다. public boolean isBoardWriter(int
	 * num , String pass) { boolean result = false; String board_sql =
	 * "select BOARD_PASS from board where BOARD_NUM=?"; try (Connection con =
	 * ds.getConnection(); PreparedStatement pstmt =
	 * con.prepareStatement(board_sql);) { pstmt.setInt(1, num); try (ResultSet rs =
	 * pstmt.executeQuery()) { if (rs.next()) { if
	 * (pass.equals(rs.getString("BOARD_PASS"))) { result = true; } } } catch
	 * (SQLException e) { e.printStackTrace(); } } catch (SQLException ex) {
	 * System.out.println("isBoardWriter() 에러 :" + ex); } return result;
	 * }//isBoardWriter end //글 수정 public boolean boardModify(BoardBean modifyboard)
	 * { String sql = "update board " +
	 * "set BOARD_SUBJECT=?, BOARD_CONTENT=?, BOARD_FILE=? " +"where BOARD_NUM=?";
	 * try(Connection con = ds.getConnection(); PreparedStatement pstmt=
	 * con.prepareStatement(sql);){ pstmt.setString(1,
	 * modifyboard.getBoard_subject()); pstmt.setString(2,
	 * modifyboard.getBoard_content()); pstmt.setString(3,
	 * modifyboard.getBoard_file()); pstmt.setInt(4, modifyboard.getBoard_num());
	 * int result = pstmt.executeUpdate(); if(result == 1) {
	 * System.out.println("성공 업데이트"); return true; } } catch (Exception ex) {
	 * System.out.println("boardModify()에러 : " + ex); } return false; }
	 * //boardModify()메서드 end //글 답변 public int boardReply(BoardBean board) { int
	 * num = 0;
	 * 
	 * 
	 * 답변을 달 원문 글 그룹번호입니다. 답변을 달게 되면 답변 글은 이 번호와 같은 관련글 번호를 갖게 되면서 같은 그룹에 속하게됩니다. 글
	 * 목록에서 보여줄 때 하나의 그룹으로 묶여서 출력됩니다.
	 * 
	 * 
	 * int re_ref = board.getBoard_re_ref();
	 * 
	 * 
	 * 답글의 깊이를 의미합니다. 원문에 대한 답글이 출력될 때 한 번 들여쓰기가 처리가 되고 답글에 대한 답글은 들여쓰기가 두번 처리되게
	 * 합니다. 원문인 경우에는 이 값이 0이고 원문의 답글은 1, 답글의 답글은 2가 됩니다.
	 * 
	 * int re_lev = board.getBoard_re_lev();
	 * 
	 * //같은 관련 글 중에서 해당 글이 출력되는 순서입니다. int re_seq = board.getBoard_re_seq(); try
	 * (Connection con = ds.getConnection();){
	 * 
	 * //트랜잭션을 이용하기 위해서 setAutoComit을 false 로 설정합니다. con.setAutoCommit(false);
	 * 
	 * try { reply_update(con, re_ref, re_seq);
	 * 
	 * //등록할 답변 글의 BOARD_RE_REV, BOARD_RE_SEQ 값을 원문 글보다 1씩 증가시킵니다.
	 * num=reply_insert(con, board, re_ref, re_lev+1, re_seq+1); con.commit();
	 * con.setAutoCommit(true); } catch(Exception e) { e.printStackTrace(); if (con
	 * != null) { try { con.rollback(); //롤백합니다. } catch (Exception ex) {
	 * 
	 * ex.printStackTrace(); } } } } catch(Exception ex) { ex.printStackTrace();
	 * System.out.println("boardReply() 에러 : " + ex); } return num; }//
	 * boardReply()메서드 end
	 * 
	 * public void reply_update(Connection con, int re_ref, int re_seq)throws
	 * Exception { //BOARD_RE_REF, BOARD_RE_SEQ 값을 확인하여 원문 글에 답글이 달려있다면 //달린 댓글들의
	 * BOARD_RE_SEQ 값을 1씩 증가시킵니다. // 현재 글을 이미 달린 답글보다 앞에 출력되게 하기 위해서입니다.
	 * 
	 * String sql = "update board " + "set BOARD_RE_SEQ = BOARD_RE_SEQ + 1 " +
	 * "where BOARD_RE_REF = ? " + "and BOARD_RE_SEQ > ?";
	 * 
	 * try(PreparedStatement pstmt = con.prepareStatement(sql);){ pstmt.setInt(1,
	 * re_ref); pstmt.setInt(2, re_seq); pstmt.executeUpdate(); } } // reply_update
	 * end
	 * 
	 * public int reply_insert(Connection con, BoardBean board, int re_ref, int
	 * re_lev, int re_seq) throws Exception { int num = 0; String board_max_sql =
	 * "(select max(board_num) + 1 from board)"; try(PreparedStatement pstmt =
	 * con.prepareStatement(board_max_sql);){ try (ResultSet re =
	 * pstmt.executeQuery()) { if(re.next()) { num=re.getInt(1); } } } String sql =
	 * "insert into board" + "(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT," +
	 * " BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF," +
	 * " BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT)" + " values(? ,?,?,?," +
	 * "     ?,?,?," + "     ?,?,?)"; try(PreparedStatement pstmt =
	 * con.prepareStatement(sql);){ pstmt.setInt(1, num); pstmt.setString(2,
	 * board.getBoard_name()); pstmt.setString(3, board.getBoard_pass());
	 * pstmt.setString(4, board.getBoard_subject()); pstmt.setString(5,
	 * board.getBoard_content()); pstmt.setString(6, ""); //답변에는 파일을 업로드하지 않습니다.
	 * pstmt.setInt(7, re_ref); // 원문의 글번호 pstmt.setInt(8, re_lev); pstmt.setInt(9,
	 * re_seq); pstmt.setInt(10, 0); //BOARD_READCOUNT(조회수)는 0
	 * pstmt.executeUpdate(); } return num; }//class end
	 * 
	 * //글 삭제 public boolean boardDelete(int num) { String select_sql =
	 * "select BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ" + " from board" +
	 * " where BOARD_NUM=?";
	 * 
	 * String board_delete_sql = "delete from board" +
	 * "	              where BOARD_RE_REF = ?" +
	 * "	              and   BOARD_RE_LEV >=?" +
	 * "	              and   BOARD_RE_SEQ >=?" +
	 * "	              and   BOARD_RE_SEQ <=(" +
	 * "                                      nvl((SELECT min(board_Re_seq)-1" +
	 * "                                          FROM  BOARD   " +
	 * "                                          WHERE BOARD_RE_REF=?" +
	 * "                                          AND   BOARD_RE_LEV=?" +
	 * "                                          AND   BOARD_RE_SEQ>?) , " +
	 * "                                          (SELECT max(board_re_seq)  " +
	 * "                                          FROM  BOARD   " +
	 * "                                          WHERE BOARD_RE_REF=? ))" +
	 * "                                      )"; boolean result_check = false;
	 * 
	 * try(Connection con = ds.getConnection(); PreparedStatement pstmt =
	 * con.prepareStatement(select_sql); ) {//1
	 * 
	 * pstmt.setInt(1, num); try (ResultSet rs = pstmt.executeQuery();){ //2
	 * if(rs.next()) { try(PreparedStatement pstmt2 =
	 * con.prepareStatement(board_delete_sql)){ //3 pstmt2.setInt(1,
	 * rs.getInt("BOARD_RE_REF")); pstmt2.setInt(2, rs.getInt("BOARD_RE_LEV"));
	 * pstmt2.setInt(3, rs.getInt("BOARD_RE_SEQ")); pstmt2.setInt(4,
	 * rs.getInt("BOARD_RE_REF")); pstmt2.setInt(5, rs.getInt("BOARD_RE_LEV"));
	 * pstmt2.setInt(6, rs.getInt("BOARD_RE_SEQ")); pstmt2.setInt(7,
	 * rs.getInt("BOARD_RE_REF")); int count=pstmt2.executeUpdate(); if(count>=1)
	 * result_check = true;// 삭제가 안되는 경우에는 false 를 반환합니다. }//try 3 }// if
	 * (rs.next()){ }//try 2 } catch (Exception ex) {
	 * System.out.println("boardDelecte() 에러 : " + ex); ex.printStackTrace(); }
	 * return result_check; }//boardDelete() 메서드 end
	 */
}
