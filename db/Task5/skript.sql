create table account(
    id serial primary key,
    name varchar(255),
    number int,
	users_id int references account(id) unique
);

create table users(
    id serial primary key,
    name varchar(255),
	surname varchar(255)
);


insert into users(name, surname) values ('Alexander', 'Rest');
insert into users(name, surname) values ('Vasiliy', 'Ivanov');
insert into users(name, surname) values ('Vladimir', 'Istomin');
insert into users(name, surname) values ('Sergey', 'Marks');
insert into users(name, surname) values ('Alexander', 'Fomin');

insert into account(name, number, users_id) values ('Счет 1', 133333, 1);
insert into account(name, number, users_id) values ('Счет 2', 133333, 2);
insert into account(name, number, users_id) values ('Счет 3', 133333, 3);


select us.name, us.surname, a.name 
from users as us join account as a on a.users_id = us.id;

select us.name as Имя, us.surname as Фамилия, a.name as Счет 
from users as us join account as a on a.users_id = us.id;

select us.name Имя, us.surname as Фамилия, a.name as "Счет пользователя" 
from users us join account a on a.users_id = us.id;