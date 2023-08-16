create table country(
	id serial primary key,
	name varchar(255),
	population bigint,
	isIsland boolean
);

insert into country(name, population, isIsland) values('Russia', '146000000', FALSE);

update country set population = '146000001';

delete from country;



