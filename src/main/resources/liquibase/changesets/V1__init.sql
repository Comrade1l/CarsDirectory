create table if not exists Car
(
    id                  bigserial primary key,
    registration_number varchar(15)  not null unique,
    car_model           varchar(255) not null,
    colour              varchar(255) not null,
    production_year     bigint       not null
);