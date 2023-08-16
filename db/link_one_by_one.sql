create table student(
    id serial primary key,
    firstName varchar(255),
    secondName varchar(255)
);

create table research(
    id serial primary key,
    title varchar(255)
);

create table student_research(
	id serial primary key,
     student_id int references student(id) unique,
     research_id int references research(id) unique
);

insert into student(firstName, secondName) values ('Vasiliy', 'Ivanov');


insert into research(title) values ('Исследование проблем механики.');


insert into student_research(student_id, research_id) values (1, 1);


select * from student_research;
