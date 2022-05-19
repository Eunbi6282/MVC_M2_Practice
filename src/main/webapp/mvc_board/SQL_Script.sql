create table mvcboard(
    idx number PRIMARY key,
    name VARCHAR2(50) not null,
    title VARCHAR2(200) not null,
    content  VARCHAR2(2000) not null,
    postdate date DEFAULT sysdate not null,
    ofile VARCHAR2(200),
    sfile VARCHAR2(50),
    downcount number(5) DEFAULT 0 not null,
    pass VARCHAR2(50) not null,
    visitcount number DEFAULT 0 not null
);
select * from mvcboard;

alter table mvcboard
rename column vistitcount to visitcount;
create sequence seq_board_num
    increment by 1
    start with 1
    nocache;
    
-- 더미 데이터 입력 (5개로 값 입력)
insert into mvcboard (idx, name, title, content, pass)
values( seq_board_num.nextval, '김유신', '자료실 제목1입니다.', '내용1', '1234');
insert into mvcboard (idx, name, title, content, pass)
values( seq_board_num.nextval, '장보고', '자료실 제목2입니다.', '내용2', '1234');
insert into mvcboard (idx, name, title, content, pass)
values( seq_board_num.nextval, '이순신', '자료실 제목3입니다.', '내용3', '1234');
insert into mvcboard (idx, name, title, content, pass)
values( seq_board_num.nextval, '강감찬', '자료실 제목4입니다.', '내용4', '1234');
insert into mvcboard (idx, name, title, content, pass)
values( seq_board_num.nextval, '대조영', '자료실 제목5입니다.', '내용5', '1234');

commit;
