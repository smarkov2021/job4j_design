create table type(
    id serial primary key,
    name varchar(255)
);


create table product(
    id serial primary key,
    name varchar(255),
	type_id int references type(id),
	expired_date timestamp,
    price float
);

insert into type(name) values ('СЫР');
insert into type(name) values ('МОЛОКО');
insert into type(name) values ('ПИВО');
insert into type(name) values ('МОРОЖЕНОЕ');

insert into product(name, type_id, expired_date, price) values ('Брест-Литовский сыр', 1, '30-09-23', 1000.0 );
insert into product(name, type_id, expired_date, price) values ('Российский сыр', 1, '28-09-23', 750.0);
insert into product(name, type_id, expired_date, price) values ('Тагильская моцарелла', 1, '30-07-23', 200.0);
insert into product(name, type_id, expired_date, price) values ('Курганское молоко', 2, '21-09-23', 100.0);
insert into product(name, type_id, expired_date, price) values ('Козье молоко', 2, '30-10-23', 300.0);
insert into product(name, type_id, expired_date, price) values ('Курганское светлое', 3, '30-07-24', 100.0);
insert into product(name, type_id, expired_date, price) values ('Тагильский жигуль', 3, '30-06-24', 100.0);
insert into product(name, type_id, expired_date, price) values ('Брестское мороженое', 4, '30-07-24', 200.0);
insert into product(name, type_id, expired_date, price) values ('Эскимо', 4, '22-06-30', 100.0);



select p.name from product p
join type t 
on p.type_id = t.id
where t.name ='СЫР'


select * from product where name like '%мороженое%'


select * from product where expired_date < CURRENT_TIMESTAMP



select t.name, max(p.price)
from product p
join type t
on p.type_id = t.id
group by t.name


select t.name, count(p.name)
from product p
join type t
on p.type_id = t.id
group by t.name


select *
from product p
join type t
on p.type_id = t.id
where t.name = 'СЫР' or t.name = 'МОЛОКО'

select t.name
from product p
join type t
on p.type_id = t.id
group by t.id
having count(p.name) < 10


select p.name, t.name
from product p
join type t
on p.type_id = t.id