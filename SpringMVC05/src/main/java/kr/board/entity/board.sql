create table myboard (
	idx int not null auto_increment,
	memID varchar(20) not null,
	title varchar(100) not null,
	content varchar(2000) not null,
	writer varchar(30) not null,
	indate datetime default now(),
	count int default 0,
	primary key(idx)
);

create table mem_stbl (
	memIdx int not null,
	memID varchar(20) not null,
	memPassword varchar(68) not null,
	memName varchar(20) not null,
	memAge int,
	memGender varchar(20),
	memEmail varchar(50),
	memProfile varchar(50),
	primary key(memIdx)
);

create table mem_auth(
no int not null auto_increment,
memID varchar(50) not null,
auth varchar(50) not null,
primary key(no)

);

CREATE TABLE mem_auth (
  no INT NOT NULL AUTO_INCREMENT,
  memID VARCHAR(50) NOT NULL,
  auth VARCHAR(50) NOT NULL,
  PRIMARY KEY (no),
  CONSTRAINT fk_member_auth FOREIGN KEY (memID) REFERENCES mem_stbl(memID)
);


drop table myboard;

insert into myboard(title, content, writer)
values('게시판 연습','게시판 연습','관리자');

insert into myboard(title, content, writer)
values('게시판 연습','게시판 연습','박매일');

insert into myboard(title, content, writer)
values('게시판 연습','게시판 연습','선생님');

select * from myboard;

create table mem_tbl (
	memIdx int auto_increment,
	memID varchar(20) not null,
	memPassword varchar(20) not null,
	memName varchar(20) not null,
	memAge int,
	memGender varchar(20),
	memEmail varchar(50),
	memProfile varchar(50),
	primary key(memIdx)
);

select * from mem_tbl;

drop table mem_auth;
