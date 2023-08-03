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

private int    board_num;    //글번호
private String board_name;   //글 작성자
private String board_pass;   //글 비밀번호
private String board_subject;//글 제목
private String board_content;//글 내용
private String board_file;   //첨부될 파일의 이름
private int    board_re_ref; //답변 글 작성시 참조되는 글의 번호
private int    board_re_lev; //답변 글의 깊이
private int    board_re_seq; //답변 글의 순서
private int    board_readcount;//글의 조회수
private String board_date;
private int cnt;

//이곳에서 오른쪽 마우스 버튼 클릭 후 -> Source 
//-> Generate Getters and Setters(alt + shift + )

}


