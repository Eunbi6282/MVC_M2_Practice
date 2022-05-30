select * from member01;

create table member01 (
    id varchar2(50) not null primary key, 
    passwd varchar2(60) not null,
    name varchar2(20) not null,
    reg_date date default sysdate not null ,
    address varchar2(100) not null,
    tel varchar2 (20) not null 
    );
    
insert into member01 (id, passwd, name, reg_date, address, tel)
values ('admin@naver.com' , '1234', '������', default, '���� Ư����', '010-2055-6282');

insert into member01 (id, passwd, name, reg_date, address, tel)
values ('hong@google.com' , '1234', 'ȫ�浿', default, '��⵵', '010-1111-1111');
commit;