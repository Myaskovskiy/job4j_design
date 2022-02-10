create table type (
     id serial primary key,   
     name varchar(2000)
);

insert into type(name) values ('сыр'), ('молоко'), ('мясо'), ('мороженное');

create table product (
     id serial primary key,   
     name varchar(2000),    
	 expired_date date,
	 price integer,
	 type_id int references type(id)
);

insert into product(name, expired_date, price, type_id)
values 
('сыр российский', '2022-02-18', 200, 1),
('сыр агдам', '2022-03-08', 300, 1),
('молоко простоквашино', '2022-02-10', 250, 2),
('мороженное чистая линия', '2022-04-11', 50, 4),
('мясо грудинка', '2022-03-28', 400, 3),
('мороженное пломбир', '2022-03-07', 60, 4),
('молоко домик в деревне', '2022-04-01', 173, 2),
('chees', '2022-04-08', 400, 1);

--1. Написать запрос получение всех продуктов с типом "СЫР"
select p.name
from product as p
join type as t on p.type_id = t.id
where t.name like 'сыр';
--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
select * from product where name like 'мороженное%';
--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select * from product where 
expired_date > '2022-01-31' and expired_date < '2022-03-30';

--4. Написать запрос, который выводит самый дорогой продукт.
select * from product where price = (
select max(price) from product);
--5. Написать запрос, который выводит количество всех продуктов определенного типа.
select count(*) from product as pr
join type as tp on pr.type_id = tp.id    
where tp.name like 'сыр';
--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select pr.name , pr.price from product as pr
join type as tp on pr.type_id = tp.id    
where (tp.name like 'сыр'
or tp.name like 'молоко');
--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук. 
--у меня продуктов с типом мясо в таблице 1 шт, из за этого для наглядности , я сделал условие меньше 2 шт.
--результат 1 ед мяса осталась. 
select count(pr.type_id), tp.name from product as pr
join type as tp on pr.type_id = tp.id    
group by tp.name 
having count(pr.type_id) < 2;
--8. Вывести все продукты и их тип.
select pr.name as название,  tp.name as тип_продукта
from product as pr
join type as tp on pr.type_id = tp.id
order by tp.name;


drop table product;



