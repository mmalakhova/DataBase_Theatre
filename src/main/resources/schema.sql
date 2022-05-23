create table authors
(
    id      serial primary key,
    name    varchar(50) not null,
    country varchar(30) not null,
    gender  varchar(30) not null
);

insert into authors(name, country, gender)
VALUES ('Василий Пупкин', 'Россия', 'мужчина'),
       ('Лариса Васильева', 'Россия', 'женщина');


create table stage_directors
(
    id                     serial primary key,
    name                   varchar(50) not null,
    type_of_stage_director varchar(30) not null,
    age                    integer     not null check (age > 0),
    gender                 varchar(30) not null
);

insert into stage_directors(name, type_of_stage_director,
                            age, gender)
VALUES ('Иван Сергеевич', 'Режиссер-постановщик', 36, 'мужчина'),
       ('Вероника Бахрушина', 'Художник-постановщик', 40, 'женщина');

create table concert_tours
(
    id         serial primary key,
    title      varchar(50)  not null,
    descr      varchar(300) not null,
    start_date date         not null,
    end_date   date         not null check (start_date < end_date)
);

insert into concert_tours(title, descr, start_date, end_date)
VALUES ('Весенние гастроли', 'Очень важный тур по золотому кольцу', '2022-09-12', '2022-09-30'),
       ('Зимние гастроли', 'Гастроли в честь юбилея театра', '2023-02-02', '2023-03-01');

create table musicians
(
    id         serial primary key,
    name       varchar(50) not null,
    gender     varchar(30) not null,
    age        integer     not null check (age > 0),
    instrument varchar(30) not null
);

insert into musicians(name, gender, age, instrument)
values ('Александр Рин', 'мужчина', 27, 'Фортепьяно'),
       ('Валентина Кулина', 'женщина', 30, 'Скрипка');

create table actors
(
    id     serial primary key,
    name   varchar(50) not null,
    height integer     not null check (height > 100),
    age    integer     not null check (age > 0),
    vocals varchar(30) not null,
    gender varchar(30) not null
);

insert into actors(name, height, age, vocals, gender)
values ('Карина Самойлова', 167, 25, 'Сопрано', 'женщина'),
       ('Полина Яковлева', 179, 37, 'Альт', 'женщина');


create table performances
(
    id              serial primary key,
    name            varchar(50)                                                         not null,
    genre           varchar(30)                                                         not null,
    age_rating      varchar(10)                                                         not null,
    date            date                                                                not null,
    descr           varchar(300)                                                        not null,
    author_id       integer references authors (id) on update cascade on delete cascade not null,
    concert_tour_id integer                                                             references concert_tours (id) on update cascade on delete set null
);

insert into performances(name, genre, age_rating, date, descr, author_id, concert_tour_id)
values ('Дядюшкин сон', 'Вещичка голубиного незлобия', '16+', '2022-05-23',
        'Анекдот Достоевского о нравах провинциального общества',
        1, 2);

create table tickets
(
    id             serial primary key,
    price          integer                                                                  not null check (price > 0),
    row            integer                                                                  not null,
    seat           integer                                                                  not null,
    performance_id integer references performances (id) on update cascade on delete cascade not null
);

insert into tickets(price, row, seat, performance_id)
values (1200, 4, 12, 1),
       (1000, 14, 24, 1);


create table roles
(
    id                   serial primary key,
    name                 varchar(50) not null,
    vocal_req            varchar(30),
    height_req           integer,
    age_req              integer,
    gender_req           varchar(30),
    first_or_second_role varchar(30) not null,
    actor_id             integer     references actors (id) on update cascade on delete set null,
    performance_id       integer references performances (id) on update cascade on delete cascade
);

insert into roles(name, vocal_req, height_req, age_req, gender_req, first_or_second_role, actor_id, performance_id)
values ('Марья Москалева', 'Альт', 160, 30, 'женщина', 'Роль второго плана', 2, 1);

create table actors_and_performances
(
    actor_id       integer references actors (id) on update cascade on delete cascade,
    performance_id integer references performances (id) on update cascade on delete cascade
);

insert into actors_and_performances(actor_id, performance_id)
values (2, 1);

create table musicians_and_performances
(
    musician_id    integer references musicians (id) on update cascade on delete cascade,
    performance_id integer references performances (id) on update cascade on delete cascade

);

insert into musicians_and_performances(musician_id, performance_id)
values (1, 1);

create table performances_and_stage_directors
(
    performance_id    integer references performances (id) on update cascade on delete cascade,
    stage_director_id integer references stage_directors (id) on update cascade on delete cascade
);

insert into performances_and_stage_directors(performance_id, stage_director_id)
values (1, 2);

create table scripts (
                         id serial primary key,
                         title varchar not null,
                         text varchar not null,
                         performance_id int default 0,
                         unique(performance_id),
                         foreign key(performance_id) references performances(id)
)

