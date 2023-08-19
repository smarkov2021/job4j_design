create table users (
    id serial primary key,
    name text
);

create table roles (
    id serial primary key,
    name text,
	users_id int references users(id)
);

create table rules (
    id serial primary key,
    name text
);

create table roles_rules (
    id serial primary key,
    roles_id int references roles(id),
	rules_id int references rules(id)
);


create table states (
    id serial primary key,
    name text
);

create table items (
    id serial primary key,
    name text,
	users_id int references users(id),
	states_id int references states(id)
);

create table comments (
    id serial primary key,
    name text,
	items_id int references items(id)
);

create table attachs (
    id serial primary key,
    name text,
	items_id int references users(id)
);

create table categories (
    id serial primary key,
    name text,
	items_id int references items(id)
);








