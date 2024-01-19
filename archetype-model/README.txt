delete from father where id > 0;
delete from children where id > 0;
delete from father_children where id_father > 0;

ALTER TABLE father AUTO_INCREMENT = 1;
ALTER TABLE children AUTO_INCREMENT = 1;

select * from father where id > 218;
select * from children;
select * from father_children;
