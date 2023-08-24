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

create table list (
	id serial primary key,
	car_id int references cars(id),
	quantity int,
	price int
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

insert into list(car_id, quantity, price) values(1, 10, 700000);
insert into list(car_id, quantity, price) values(2, 7, 1700000);
insert into list(car_id, quantity, price) values(3, 15, 1200000);
insert into list(car_id, quantity, price) values(4, 2, 200000);
insert into list(car_id, quantity, price) values(5, 10, 500000);
insert into list(car_id, quantity, price) values(6, 8, 700000);


create view available_budgets_car 
as select c.id, c.name as Марка, bd.name as "Тип кузова", eng.name as "Тип двигателя", tn.name as "Тип коробки передач", l.quantity as "Количество в поставке", l.price as "Цена единицы" from cars c
join car_bodies bd
on bd.id = c.body_id
join car_engines eng
on eng.id = c.engine_id
join car_transmissions tn
on tn.id = c.transmission_id
join list l
on c.id = l.car_id
where quantity * price < 10000000

select * from available_budgets_car