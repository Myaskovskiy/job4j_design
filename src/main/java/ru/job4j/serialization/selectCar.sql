insert into car(name, description, TotalWeight, Cost, oldCar)
values('BMW', 'пушка гонка', 100.2, 100500, true);

insert into car(name, description, TotalWeight, Cost, oldCar)
values('Porsche', 'супер стар', 200.5, 200500, false);

update car set name = 'BMW-OLD', oldCar = false;

delete from car;

select * from car;
select name from car where oldCar = true;

create table car(
    id serial primary key,
	name varchar(255),
	description text,
	TotalWeight NUMERIC(9,2),
	Cost INTEGER,
	oldCar boolean
);
