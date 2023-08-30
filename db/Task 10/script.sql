begin transaction;

insert into students (name, sum) VALUES ('Elena Reg', 200);

savepoint first_savepoint;

delete from students where sum = 20;

savepoint second_savepoint;

delete from students;

rollback to second_savepoint;

rollback to first_savepoint;

commit;