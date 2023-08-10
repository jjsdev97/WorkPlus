drop table board cascade constraints purge;
CREATE TABLE BOARD(
     BOARD_NUM          NUMBER,           -- 글번호
     BOARD_TYPE         VARCHAR2(20),     -- 게시판 종류
     BOARD_SUBJECT      VARCHAR2(300),    -- 게시판 제목 
     BOARD_CONTENT      VARCHAR2(4000),   -- 게시판 내용
     BOARD_FILE         VARCHAR2(50),     -- 파일첨부/다운
     BOARD_WRITER       VARCHAR2(30),     -- 작성자
     BOARD_DATE         DATE,             -- 작성일자
     BOARD_READCOUNT    NUMBER,           -- 조회수 카운팅
     BOARD_NOTICE       VARCHAR2(1) CHECK(BOARD_NOTICE = '0' OR BOARD_NOTICE ='1'),             -- 공지사항
     BOARD_LIKECOUNT    NUMBER,           -- 좋아요 개수
     PRIMARY KEY(BOARD_NUM)
);

게시판 종류
1    공통게시판  공지사항
2    공통게시판  자유게시판
3    팀별게시판
4
5


-- 게시글이 달린 댓글의 갯수를 구하기 위한 과정입니다.
select*from board;
delete from board;
select * from comm;

insert into board (BOARD_NUM, BOARD_SUBJECT, BOARD_NAME, BOARD_RE_REF)
values(1,'처음','admin', 1);
insert into board (BOARD_NUM, BOARD_SUBJECT, BOARD_NAME, BOARD_RE_REF)
value(2, '둘째', 'admin', 2);
insert into board (BOARD_NUM, BOARD_SUBJECT, BOARD_NAME, BOARD_RE_REF)
value(3, '셋째', 'admin', 3);

insert into comm (num, id, comment_board_num) values(1,'admin',1);
insert into comm (num, id, comment_board_num) values(2,'admin',1);
insert into comm (num, id, comment_board_num) values(3,'admin',2);
insert into comm (num, id, comment_board_num) values(4,'admin',2);

update board set board_subject = '오늘도 행복하요' where board_num = 1;

--1. comm테이블에서comment_board_num 별 갯수를 구합니다.
COMMENT_BOARD_NUM    CNT
1                    2
2                    2

select comment _board_num, count(*) cnt
from comm
group by comment_board_num;

--2. board와 조인을 합니다.
BOARD_NUM     BOARD_SUBJECT   CNT
1             오늘도 행복하세요    2
2             둘째              2

select board_num, board_subject, cnt
from board join (select comment_board_num, count(*) cnt
                from comm
                group by comment_board_num)
on board_num = comment_board_num;

-- 문제점) 만약 board 테이블에는 글이 잇지만 댓글에는 없는 경우 조회가 되지 않습니다.

--3. outer join 을 이용해서 board 의 글이 모두 표시되고 cnt가 null인 경우 0으로 표시되도록 합니다.
BOARD_NUM    BOARD_SUBJECT   CNT
3            세째             0
2            둘째             2
1            오늘도 행복하세요    2

-- 1단계) 게시판글에 댓글이 없는 경우 cnt가 null 입니다.
select board_num, board_subject, cnt
from board left outer join (select comment_board_num, coun(*) cnt
     from comm
     group by comment_board_num)
on board_num=comment_board_num;

BOARD_NUM    BOARD_SUBJECT  CNT
1            오늘도 행복하세요   2
2            둘째             2
3            세째             NULL

--) 2단계 cnt 가 null 인경우 0으로 만들어요
select board_num, board_subject, nvl(cnt,0) as cnt
from board left outer join (select comment_board_num, count(*) cnt
     from comm
     group by comment_board_num)
on board_num=comment_board_num
order by BOARD_RE_REF desc,
BOARD_RE_SEQ asc;

--4. 인라인 뷰를 이용한 쿼리문 작성
select * 
from (select fownum rnum, j.*
     from (
      3번
      ) j
      where rewnum<= 20)
where rnum>=11 and rnum<=10;

--5.

select * 
from (select rownum rnum, j.*
      from (
            select board_num, board_subject, nvl(cnt,0) as cnt
            from board left outer join (select comment_board_num, count(*) cnt
     from comm
     group by comment_board_num)
on board_num=comment_board_num
order by BOARD_RE_REF desc,
BOARD_RE_SEQ asc;
)j
      where rownum<=10
)
where rnum>=1 and rnum<=10;