insert into roles(name) values ('Administrator');
insert into roles(name) values ('Guard');
insert into roles(name) values ('System Analyst');


insert into users(name, roles_id) values ('Alexey Abr', 1);
insert into users(name, roles_id) values ('Maxim Livs', 1);
insert into users(name, roles_id) values ('Ivan Ter', 3);

insert into categories(name) values ('Office');
insert into categories(name) values ('Technical');
insert into categories(name) values ('First Aid');

insert into rules(name) values ('Have a MedKit');
insert into rules(name) values ('Check documents');
insert into rules(name) values ('Keep silent');

insert into roles_rules(roles_id, rules_id) values (1, 1);
insert into roles_rules(roles_id, rules_id) values (1, 2);
insert into roles_rules(roles_id, rules_id) values (3, 3);

insert into states(name) values ('Active');
insert into states(name) values ('Closed');

insert into items(name, users_id, states_id, categories_id) values ('Need a printer', 1, 1, 2);
insert into items(name, users_id, states_id, categories_id) values ('Need a scanner', 1, 2, 2);
insert into items(name, users_id, states_id, categories_id) values ('Need some food', 2, 2, 1);

insert into comments(name, items_id) values ('Need a pizza', 3);
insert into comments(name, items_id) values ('Need a beer', 3);

insert into attachs(name, items_id) values ('Instruction', 1);

