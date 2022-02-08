create table passport_car(
    id serial primary key,
    seria int,
    number int
);

create table car_auto(
    id serial primary key,
    name varchar(255)
);

create table passport_car_car(
    id serial primary key,
    passport_car_id int references passport_car(id) unique,
    car_id int references car_auto(id) unique
);

insert into passport_car(seria, number) values (111, 222);
insert into passport_car(seria, number) values (333, 444);


insert into car_auto(name) values ('BMW');
insert into car_auto(name) values ('Porsche');


insert into passport_car_car(passport_car_id, car_id) values (1, 1);
insert into passport_car_car(passport_car_id, car_id) values (2, 2);
 
select * from passport_car;
select * from car_auto;
select * from passport_car_car;

select c.name, p.seria, p.number
from passport_car_car pc, passport_car p, car_auto c
where c.id = pc.car_id
and p.id = pc.passport_car_id

drop table passport_car_car; 
drop table passport_car;
drop table car_auto;
