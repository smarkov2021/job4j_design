insert into users(name) values ('Alexey Abr');
insert into users(name) values ('Maxim Livs');

insert into roles(name, users_id) values ('Administrator', 1);
insert into roles(name, users_id) values ('Guard', 1);
insert into roles(name, users_id) values ('System Analyst', 2);

insert into rules(name) values ('Have a MedKit');
insert into rules(name) values ('Check documents');
insert into rules(name) values ('Keep silent');

insert into roles_rules(roles_id, rules_id) values (1, 1);
insert into roles_rules(roles_id, rules_id) values (1, 2);
insert into roles_rules(roles_id, rules_id) values (3, 3);

insert into states(name) values ('Active');
insert into states(name) values ('Closed');

insert into items(name, users_id, states_id) values ('Need a printer', 1, 1);
insert into items(name, users_id, states_id) values ('Need a scanner', 1, 2);
insert into items(name, users_id, states_id) values ('Need some food', 2, 2);

insert into comments(name, items_id) values ('Need a pizza', 3);
insert into comments(name, items_id) values ('Need a beer', 3);

insert into attachs(name, items_id) values ('Instruction', 1);

insert into categories(name, items_id) values ('Office', 1);
insert into categories(name, items_id) values ('Office', 2);
insert into categories(name, items_id) values ('Technical', 1);
insert into categories(name, items_id) values ('Technical', 2);
insert into categories(name, items_id) values ('First Aid', 3);