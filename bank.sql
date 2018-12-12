
//테이블 생성
create table bankMember(
id varchar(20) primary key,
name varchar(20) not null,
age int not null,
tel varchar(30) not null
);


"insert into member values(?,?,?,?)";

"select * from bankmember where id = "+id;

"delete from bankmember where id "+id;

"select * from bankmember";

"update bankmember set tel = '"+tel+"' where id = "+id;