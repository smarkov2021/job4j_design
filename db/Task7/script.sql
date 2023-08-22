create table departments  (
    id serial primary key,
    name text
);

create table employees (
    id serial primary key,
    name text,
	departments_id int references departments(id)
);

insert into departments(name) values ('IT Department');
insert into departments(name) values ('Science Department');
insert into departments(name) values ('Flight Department');
insert into departments(name) values ('HR Department');


insert into employees(name, departments_id) values ('Alexey Abr', 1);
insert into employees(name, departments_id) values ('Maxim Livs', 2);
insert into employees(name, departments_id) values ('Vladimir Meltz', 1);
insert into employees(name, departments_id) values ('Vadim Kelm', 2);
insert into employees(name, departments_id) values ('Yuri Findov', 3);
insert into employees(name) values ('Sergey Frolov');


select * from departments d
left join employees e
on e.departments_id = d.id


select * from departments d
right join employees e
on e.departments_id = d.id


select * from departments d
full join employees e
on e.departments_id = d.id


select * from departments d
cross join employees e


select * from departments d
left join employees e
on e.departments_id = d.id
where e.departments_id is null


select from departments d
left join employees e
on e.departments_id = d.id

select d.*, e.* from employees e
right join departments d
on e.departments_id = d.id

create table teens  (
    id serial primary key,
    name text,
	gender boolean
);

insert into teens(name, gender) values ('Anastasia', true);
insert into teens(name, gender) values ('Maxim', false);
insert into teens(name, gender) values ('Vladimir', false);
insert into teens(name, gender) values ('Vadim', false);
insert into teens(name, gender) values ('Olga', true);
insert into teens(name, gender) values ('Kate', true);


select t1.name, t2.name
from teens t1
cross join teens t2
where t1.gender is true and t2.gender is false
