create table car_bodies (
	id serial primary key,
	name varchar(255)
);

create table car_engines (
	id serial primary key,
	name varchar(255)
);

create table car_transmissions (
	id serial primary key,
	name varchar(255)
);

create table cars (
	id serial primary key,
	name varchar(255),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values('Кроссовер');
insert into car_bodies(name) values('Внедорожник');
insert into car_bodies(name) values('Пикап');
insert into car_bodies(name) values('Седан');
insert into car_bodies(name) values('Минивен');
insert into car_bodies(name) values('Хэтчбек');

insert into car_engines(name) values('ДВС');
insert into car_engines(name) values('Электрический двигатель');
insert into car_engines(name) values('Криогенный двигатель');
insert into car_engines(name) values('ГТД');
insert into car_engines(name) values('Иной');


insert into car_transmissions(name) values('Механическая коробка');
insert into car_transmissions(name) values('Автомат');
insert into car_transmissions(name) values('Экспериментальная');

insert into cars(name, body_id, engine_id, transmission_id) values('Ваз 2110', 4, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) values('Mazda 9', 3, 2, 2);
insert into cars(name, body_id, engine_id, transmission_id) values('Volkswagen polo', 2, 3, 1);
insert into cars(name, body_id, engine_id, transmission_id) values('Skoda Rapid', 1, 4, 2);
insert into cars(name, body_id, engine_id) values('Gaz 8', 1, 2);
insert into cars(name, body_id) values('Kavz 4 model', 2);
insert into cars(name) values('Lada Kalina');

select c.id, c.name, bd.name, eng.name, tn.name
from cars c
join car_bodies bd
on bd.id = c.body_id
join car_engines eng
on eng.id = c.engine_id
join car_transmissions tn
on tn.id = c.transmission_id

select name 
from car_bodies
where id not in (select bd.id 
from car_bodies bd
join cars c
on bd.id = c.body_id)

select name 
from car_engines
where id not in (select eng.id 
from car_engines eng
join cars c
on eng.id = c.engine_id)

select name 
from car_transmissions
where id not in (select tn.id 
from car_transmissions tn
join cars c
on tn.id = c.transmission_id)

