psql --username=postgres

create database task3_db;

\c task4_db;

create table students (id serial primary key, name varchar(50),sum integer);

insert into students (name, sum) VALUES ('Alexander Iv', 20);
insert into students (name, sum) VALUES ('Julia Rus', 50);
insert into students (name, sum) VALUES ('Sergey M', 120);

begin transaction isolation level serializable;

select avg(sum) from students;

update students set sum = 0 where name = 'Alexander Iv';

update students set sum = 100 where name =’Julia Rus’;