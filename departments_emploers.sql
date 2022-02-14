create table departments(
    id serial primary key,
    name varchar(255)
);

create table emploers(
    id serial primary key,
    name varchar(255),
	emploers_id int references departments(id)
);


insert into departments(name) values
('departments 1'),
('departments 2'),
('departments 3'),
('departments 4');

insert into emploers(name, emploers_id) values
('Emploers 1', 1),
('Emploers 2', 2),
('Emploers 3', 3),
('Emploers 4', null),
('Emploers 5', null),
('Emploers 6', 1);


select * from   emploers e left join departments d on d.id = e.emploers_id;
select * from   departments d left join emploers e on d.id = e.emploers_id
where e.emploers_id is null;
select * from   emploers e right join departments d on d.id = e.emploers_id;
select * from   emploers e full join departments d on d.id = e.emploers_id;
select * from emploers e cross join departments d;
select * from   emploers e left join departments d on d.id = e.emploers_id
where e.emploers_id is null;


select d.name, e.name from   departments d left join emploers e on  d.id = e.emploers_id;
select d.name, e.name from  emploers e right join departments d on d.id = e.emploers_id;

select d.name, e.name from   departments d right join emploers e on  d.id = e.emploers_id;
select d.name, e.name from  emploers e left join departments d on d.id = e.emploers_id;

create table teens(
    id serial primary key,
    name varchar(255),
	gender varchar(255)
);
 
insert into teens(name, gender) values
('Petr', 'man'),
('Sveta', 'woman'),
('Slava', 'man'),
('Sasha', 'man'),
('Olya', 'woman');

select e.name, d.name from teens e cross join teens d
where e.gender <> d.gender
order by e.name;

drop table emploers;
drop table departments;
drop table teens;