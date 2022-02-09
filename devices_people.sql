create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('Phone', 1500.2), ('Nout', 16000.1), ('Printer', 5500);
insert into devices(name, price) values ('Phone', 5600), ('Nout', 120000.1), ('Printer', 17000);
insert into devices(name, price) values ('Mouse', 600), ('USB', 100);
insert into people(name) values ('Any'), ('Ivan'), ('Boris');
insert into people(name) values ('Slava');
insert into devices_people(device_id, people_id) values (1, 1), (4, 2), (1, 3);
insert into devices_people(device_id, people_id) values (2, 1), (5, 2);
insert into devices_people(device_id, people_id) values (6, 3);
insert into devices_people(device_id, people_id) values (7, 4), (8, 4);

select d.name, d.price, p.name
from devices as d
join devices_people as dp on d.id = dp.device_id
join people as p on p.id = dp.people_id;

select d.name,  avg(d.price)
from devices as d
group by d.name;

select p.name, avg(d.price) 
from devices as d
join devices_people as dp on d.id = dp.device_id
join people as p on p.id = dp.people_id
group by p.name;

select p.name, avg(d.price) 
from devices as d
join devices_people as dp on d.id = dp.device_id
join people as p on p.id = dp.people_id
group by p.name
having avg(d.price) >= 5000; 

drop table devices_people; 
drop table devices;
drop table people;
