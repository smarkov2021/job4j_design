create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('laptop HP', 70000.0);
insert into devices(name, price) values ('Phone Samsung A10' , 12000.0);
insert into devices(name, price) values ('Phone Xiaomi 2' , 6000.0);
insert into devices(name, price) values ('Phone Phillips 43' , 22000.0);
insert into devices(name, price) values ('laptop Acer L2' , 43000.0);
insert into devices(name, price) values ('laptop Lenovo E43' , 22000.0);
insert into devices(name, price) values ('Monitor LG' , 12000.0);
insert into devices(name, price) values ('USB device' , 4000.0);

insert into people(name) values ('Alexander Gleb');
insert into people(name) values ('Pavel Nedved');
insert into people(name) values ('Nikolay Michailov');
insert into people(name) values ('Georgyi Livrev');
insert into people(name) values ('Georgyi Avel');


insert into devices_people(device_id, people_id) values (1,1);
insert into devices_people(device_id, people_id) values (2,1);
insert into devices_people(device_id, people_id) values (3,1);
insert into devices_people(device_id, people_id) values (4,1);
insert into devices_people(device_id, people_id) values (5,1);
insert into devices_people(device_id, people_id) values (4,2);
insert into devices_people(device_id, people_id) values (6,2);
insert into devices_people(device_id, people_id) values (7,2);
insert into devices_people(device_id, people_id) values (1,3);
insert into devices_people(device_id, people_id) values (3,3);
insert into devices_people(device_id, people_id) values (7,3);
insert into devices_people(device_id, people_id) values (1,4);
insert into devices_people(device_id, people_id) values (2,4);
insert into devices_people(device_id, people_id) values (5,4);
insert into devices_people(device_id, people_id) values (8,5);

select avg(price) from devices;

select  p.name, avg(d.price)
from devices_people as dp
join people as p 
on dp.people_id = p.id
join devices as d
on dp.device_id = d.id
group by p.name

select  p.name, avg(d.price)
from devices_people as dp
join people as p 
on dp.people_id = p.id
join devices as d
on dp.device_id = d.id
group by p.name
having avg(d.price) > 5000



