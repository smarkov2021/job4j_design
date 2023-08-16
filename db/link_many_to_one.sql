create table person(
    id serial primary key,
    firstName varchar(255),
    secondName varchar(255)
);

create table serviceCall(
    id serial primary key,
    title varchar(255),
	description text,
	author_id int references person(id)
);

insert into person(firstName, secondName) values ('Vladimir', 'Eremeev');
insert into serviceCall(title, description, author_id) values ('Не работает принтер.', 'Не включается принтер. Просьба заменить.', 1);

select * from person where id in (select author_id from serviceCall);
