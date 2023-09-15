CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

insert into customers(first_name, last_name, age, country) values ('Dmitriy', 'Serb', 43, 'Russia');
insert into customers(first_name, last_name, age, country) values ('Alex', 'Erf', 23, 'Finland');
insert into customers(first_name, last_name, age, country) values ('Sergey', 'Herz', 33, 'Russia');
insert into customers(first_name, last_name, age, country) values ('Alina', 'Vart', 27, 'Germany');
insert into customers(first_name, last_name, age, country) values ('Georg', 'Resw', 43, 'Russia');

select * from customers where age = (select min(age) from customers);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders(amount, customer_id) values (3, 2);
insert into orders(amount, customer_id) values (21, 2);
insert into orders(amount, customer_id) values (3, 1);
insert into orders(amount, customer_id) values (34, 1);
insert into orders(amount, customer_id) values (87, 2);


select * from customers 
where id not in (select distinct customer_id from orders)
