--Запрос с фильтрацией и сортировкой
select * from stage_directors where age between 18 and 55 order by stage_directors.name;

--Запрос с GROUP BY/HAVING/INNER JOIN
select p.name as name, sum(tickets.price) as total from tickets
inner join performances p on tickets.performance_id = p.id
group by p.name having sum(tickets.price) > 500;

--Запрос с OUTER JOIN
select roles.name, actors.name from roles
left outer join actors on roles.actor_id = actors.id;

--Запрос с подзапросом(самый популярный жанр спектакля)
select genre from performances group by genre
having count(*) >= all (select count(*) from performances group by genre);




