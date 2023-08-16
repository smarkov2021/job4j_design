create table citizen(
    id serial primary key,
    firstName varchar(255),
    secondName varchar(255)
);

create table violations(
    id serial primary key,
    title varchar(255)
);

create table citizen_violations(
	id serial primary key,
     citizen_id int references citizen(id),
     violations_id int references violations(id)
);

insert into citizen(firstName, secondName) values ('Sergey', 'Bystrov');
insert into citizen(firstName, secondName) values ('Ivan', 'Ivanov');
insert into citizen(firstName, secondName) values ('Alexander', 'Herz');

insert into violations(title) values ('Переход дороги на запрещающий цвет светофора.');
insert into violations(title) values ('Переход дороги в неположенном месте.');

insert into citizen_violations(citizen_id, violations_id) values (1, 1);
insert into citizen_violations(citizen_id, violations_id) values (1, 2);
insert into citizen_violations(citizen_id, violations_id) values (2, 2);
insert into citizen_violations(citizen_id, violations_id) values (3, 1);

select * from citizen
