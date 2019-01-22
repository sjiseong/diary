create table diary(
    id number primary key,
    users_id varchar2(20),
    title varchar2(50),
    content clob not null,
    regdate date not null
);

create sequence seq_diary_id;

insert into diary values(seq_diary_id.nextval, 'flynn', '첫 번째 일기',
                         '오늘은 수요일이다. 아직도 수요일이다.', sysdate);
insert into diary values(seq_diary_id.nextval, 'flynn', '두 번째 일기',
                         '다음주부터 프로젝트를 시작한다고 한다. 두렵다.', sysdate);                         
    

create table tag(
    id number primary key,
    dairy_id number constraint FK_TAG_DIARY_ID references diary(id),
    name varchar2(20) not null,
    color varchar2(10) default 'default' not null 
);




    
                         
                         