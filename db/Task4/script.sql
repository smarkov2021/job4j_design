create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna (name, avg_age, discovery_date) values ('Belay fish', 10, '2000-09-01');
insert into fauna (name, avg_age, discovery_date) values ('Tiger', 20, '1920-12-01');
insert into fauna (name, avg_age, discovery_date) values ('Eagle', 14, '1990-12-01');
insert into fauna (name, avg_age, discovery_date) values ('Magic Turtle', 14000, '1990-12-01');
insert into fauna (name, avg_age) values ('Dragon', 9999);

select * from fauna where name like('%fish%');
select * from fauna where avg_age < 21000 and avg_age > 10000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date <  '1950-01-01';

