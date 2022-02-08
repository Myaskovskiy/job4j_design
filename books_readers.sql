create table books(
     id serial primary key,
     name varchar(255)
 );
 
 create table readers(
     id serial primary key,
     name varchar(255)
 );
 
 create table readers_books(
     id serial primary key,
     book_id int references books(id),
     reader_id int references readers(id)
 );
 
insert into readers(name) values ('Ivan');
insert into readers(name) values ('Kirill');
insert into readers(name) values ('Roman');

insert into books(name) values ('Java');
insert into books(name) values ('Postgress');
insert into books(name) values ('Pithon');

select * from readers;
select * from books;
select * from readers_books;

insert into readers_books(reader_id, book_id) values (1, 1);
insert into readers_books(reader_id, book_id) values (1, 3);
insert into readers_books(reader_id, book_id) values (2, 3);
insert into readers_books(reader_id, book_id) values (3, 1);
insert into readers_books(reader_id, book_id) values (3, 2);

select readers.name, books.name 
from readers, books, readers_books
where readers.id = readers_books.reader_id
and books.id = readers_books.book_id
and readers.name like 'Ivan';

delete from books;
delete from readers;

drop table readers_books; 
drop table readers;
drop table books;
