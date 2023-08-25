create or replace function add_taxes()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
		where id = (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger taxes_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure add_taxes();





create or replace function tax()
    returns trigger as
$$
    BEGIN
        new.price = new.price*1.2;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    before insert
    on products
    for each row
    execute procedure tax();
	

	
create or replace function history()
    returns trigger as
$$
    BEGIN
		insert into history_of_price(name, price,date) 
        values(new.name, new.price, CURRENT_TIMESTAMP);
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger create_history
    after insert
    on products
    for each row
    execute procedure history();