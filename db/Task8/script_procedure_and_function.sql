create or replace procedure delete_product_by_name(i_name varchar)
language 'plpgsql'
as $$
    BEGIN
    delete from products where name like i_name ;
    END
$$;


create or replace function delete_elem(u_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
		if (u_id) = 0 then
		delete from products where count = 0;
		end if;
		if (u_id) > 0 then
		delete from products where id = u_id;
		end if;
		return result;
    end;
$$;