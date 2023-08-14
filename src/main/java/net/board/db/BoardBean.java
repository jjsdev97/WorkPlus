package net.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.member.db.Member;
public class BoardBean {

private int    BOARD_NUM;    //글번호
private String BOARD_TYPE;   //게시판 종류
private String BOARD_SUBJECT;//게시판 제목
private String BOARD_CONTENT;//게시판 내용
private String BOARD_FILE;   //파일첨부/다운
private String BOARD_WRITER; //작성자
private String    BOARD_DATE;   //작성일자
private int    BOARD_READCOUNT; //조회수 카운팅
private String BOARD_NOTICE; // 공지글 확인
private int    BOARD_LIKECOUNT;// 좋아요 개수
private int cnt; // 댓글카운팅
public int getBOARD_NUM() {
	return BOARD_NUM;
}
public void setBOARD_NUM(int bOARD_NUM) {
	BOARD_NUM = bOARD_NUM;
}
public String getBOARD_TYPE() {
	return BOARD_TYPE;
}
public void setBOARD_TYPE(String bOARD_TYPE) {
	BOARD_TYPE = bOARD_TYPE;
}
public String getBOARD_SUBJECT() {
	return BOARD_SUBJECT;
}
public void setBOARD_SUBJECT(String bOARD_SUBJECT) {
	BOARD_SUBJECT = bOARD_SUBJECT;
}
public String getBOARD_CONTENT() {
	return BOARD_CONTENT;
}
public void setBOARD_CONTENT(String bOARD_CONTENT) {
	BOARD_CONTENT = bOARD_CONTENT;
}
public String getBOARD_FILE() {
	return BOARD_FILE;
}
public void setBOARD_FILE(String bOARD_FILE) {
	BOARD_FILE = bOARD_FILE;
}
public String getBOARD_WRITER() {
	return BOARD_WRITER;
}
public void setBOARD_WRITER(String bOARD_WRITER) {
	BOARD_WRITER = bOARD_WRITER;
}
public String getBOARD_DATE() {
	return BOARD_DATE;
}
public void setBOARD_DATE(String bOARD_DATE) {
	BOARD_DATE = bOARD_DATE;
}
public int getBOARD_READCOUNT() {
	return BOARD_READCOUNT;
}
public void setBOARD_READCOUNT(int bOARD_READCOUNT) {
	BOARD_READCOUNT = bOARD_READCOUNT;
}
public String getBOARD_NOTICE() {
	return BOARD_NOTICE;
}
public void setBOARD_NOTICE(String bOARD_NOTICE) {
	BOARD_NOTICE = bOARD_NOTICE;
}
public int getBOARD_LIKECOUNT() {
	return BOARD_LIKECOUNT;
}
public void setBOARD_LIKECOUNT(int bOARD_LIKECOUNT) {
	BOARD_LIKECOUNT = bOARD_LIKECOUNT;
}
public int getCnt() {
	return cnt;
}
public void setCnt(int cnt) {
	this.cnt = cnt;
}



/*
 * private String board_name; //글 작성자 private String board_pass; //글 비밀번호
 * private String board_subject;//글 제목 private String board_content;//글 내용
 * private String board_file; //첨부될 파일의 이름 private int board_re_ref; //답변 글 작성시
 * 참조되는 글의 번호 private int board_re_lev; //답변 글의 깊이 private int board_re_seq;
 * //답변 글의 순서 private int board_readcount;//글의 조회수 private String board_date;
 * private int cnt;
 */

//이곳에서 오른쪽 마우스 버튼 클릭 후 -> Source 
//-> Generate Getters and Setters(alt + shift + )

}


// 수정필요
