create table office(
    id serial primary key,
    name varchar(255)
);

create table car(
    id serial primary key,
    model varchar(255),
    office_id int references office(id)
);

create table customer(
    id serial primary key,
    name varchar(255),
    model_id int references car(id)
);

insert into office(name) values ('Moscow');
insert into office(name) values ('Tula');
insert into office(name) values ('Omsk');
insert into car(model, office_id) VALUES ('BMW', 1);
insert into car(model, office_id) VALUES ('BMW', 3);
insert into car(model, office_id) VALUES ('Porsche', 2);
insert into car(model, office_id) VALUES ('Volga', null);
insert into customer(name, model_id) VALUES ('Petr', 1);
insert into customer(name, model_id) VALUES ('Slava', 2);
insert into customer(name, model_id) VALUES ('Slava', 3);
insert into customer(name, model_id) VALUES ('Sasha', 4);


select c.model as Модель_машины, o.name as Название_офиса, cus.name as Покупатель
from car as c
FULL join office as o on c.id = o.id
FULL join customer as cus on c.id = cus.id;

select c.model, o.name
from car as c join office as o
on c.id = o.id
where c.model like 'BMW';

select c.model as Модель_машины, o.name as Название_офиса
from car as c join office as o
on c.id = o.id;

select c.model as Модель_машины, o.name as Название_офиса
from office as o  join car as c
on o.id = c.id;

drop table customer;
drop table car;
drop table office; 

