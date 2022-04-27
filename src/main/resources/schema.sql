create table if not exists warehouse (
    warehouse_id serial primary key,
    name varchar(50) not null
);

create type category as enum ('FRESH','FROZEN_FOOD','REFRIGERATED');

create table if not exists sections (
    section_id serial primary key,
    capacity numeric(money),
    current_temperature float,
    category category
);

create table if not exists representative (
    representative_id serial primary key,
    full_name varchar(100) not null,
    section_id int,
    constraint fk_sections
        foreign key(section_id)
            references sections(section_id)
);