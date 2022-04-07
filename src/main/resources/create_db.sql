create type role_type as enum ('first', 'second');
create type gender_type as enum ('male', 'female');
create type stage_director_type as enum ('production director', 'production designer', 'production conductor');

create table authors
(
    id          serial primary key,
    name        varchar(50) not null,
    life_period interval    not null,
    country     varchar(30) not null
);

create table concert_tours
(
    id    serial primary key,
    dates interval not null
);

create table performances
(
    id               serial primary key,
    author_id        integer references authors (id) on update cascade on delete cascade,
    concert_tour_id  integer      references concert_tours (id) on update cascade on delete set null,
    name             varchar(100) not null,
    description      varchar(300),
    genre            varchar(30)  not null,
    age_rating       varchar(30)  not null,
    date             timestamp    not null,
    is_premiere      bool         not null,
    number_of_places integer      not null
);

create table actors
(
    id              serial primary key,
    name            varchar(50),
    rank            varchar(30),
    prizes          varchar(100),
    height          integer     not null,
    age             integer     not null,
    vocals          varchar(30) not null,
    gender          gender_type not null,
    work_experience varchar(100),
    salary          integer     not null
);

create table roles
(
    id                   serial primary key,
    name                 varchar(50),
    performance_id       integer references performances (id) on update cascade on delete cascade,
    actor_id             integer   references actors (id) on update cascade on delete set null,
    vocal_requirements   varchar(30),
    height_requirements  varchar(30),
    age_requirements     varchar(30),
    gender_requirements  varchar(30),
    first_or_second_role role_type not null
);

create table actors_and_performances
(
    actor_id       integer references actors (id) on update cascade on delete cascade,
    performance_id integer references performances (id) on update cascade on delete cascade
);



create table stage_directors
(
    id                     serial primary key,
    name                   varchar(50)         not null,
    type_of_stage_director stage_director_type not null,
    age                    integer             not null,
    gender                 gender_type         not null,
    work_experience        varchar(100),
    salary                 integer             not null
);

create table performances_and_stage_directors
(
    performance_id    integer references performances (id) on update cascade on delete cascade,
    stage_director_id integer references stage_directors (id) on update cascade on delete cascade
);

create table musicians
(
    id              serial primary key,
    name            varchar(50) not null,
    gender          gender_type not null,
    age             integer     not null,
    work_experience varchar(100),
    salary          integer     not null
);

create table musicians_and_performances
(
    musician_id    integer references musicians (id) on update cascade on delete cascade,
    performance_id integer references performances (id) on update cascade on delete cascade

);

create table tickets
(
    id               serial primary key,
    performance_id   integer references performances (id) on update cascade on delete cascade,
    price            integer not null,
    row              integer not null,
    seat             integer not null,
    is_season_ticket boolean
);

-- drop table authors;
-- drop table concert_tours;
-- drop table actors;
-- drop table authors;
-- drop table performances;
-- drop table roles;