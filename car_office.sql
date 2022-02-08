create table office(
    id serial primary key,
    name varchar(255)
);

create table car(
    id serial primary key,
    model varchar(255),
    office_id int references office(id)
);

insert into office(name) values ('Moscow');
insert into car(model, office_id) VALUES ('BMW', 1);

select * from car;

select * from office where id in (select id from car);

select car.model, office.name 
from car, office
where car.id = office.id;