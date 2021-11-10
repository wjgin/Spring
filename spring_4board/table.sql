insert into customer_1 (name, password , email, gender)
	values('홍길동', sha2('1111', 256), 'hong@gmail.com', 'male');
	
-- 기존의 패스워드 저장을 해시함수를 이용해서 해시코드 변환 하여 저장.
-- 해시함수: 단방향 함수, 메세지 - 해시함수 - 해시코드(해시코드로 원래 메세지로 변환 불가)
-- 암호(개인키, 공개키): 메세지 - 암호화 - 암호문자열 - 복호화 - 원래 메세지

-- 해시함수를 이용한 해시코드는 항상 일정한 길이
-- sha(secure hash algorithm) 해시함수 256비트 또는 512비트로 해시코드 생성
select sha2('1111', 256); -- 1111을 256비트 sha 해시함수 (16진수문자 256/4 글자)
select  idx,email,name,gender,hobby,age,address from customer_1 where email='hong@gmail.com' and password=sha2('1111',256);

alter table customer_1 change address addr varchar(30);

select * from customer_1 c ;
commit;

alter table gallery add title varchar(50);

create table gallery_1 (
	pno int primary key auto_increment,
	title varchar(50),
	filename varchar(50)
);

select * from gallery_1 g ;

drop table if exists board;
drop table if exists users;
create table users(
   mno int(12) auto_increment,
   email varchar(20) not null,
   password varchar(64) not null,
   name varchar(20) not null,
   reg_date timestamp,  -- 등록날짜
   mod_date timestamp,    -- 수정날짜
   primary key (mno)
);

create table board(
   bidx int(9) auto_increment,
   subject varchar(30) not null,
   content varchar(200) not null,
   mno int(9),
   reg_date timestamp,  -- 등록날짜
   mod_date timestamp,  -- 수정날짜
   primary key(bidx),
   foreign key(mno) references users(mno)
);