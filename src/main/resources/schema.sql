create table if not exists warehouses (
    warehouse_id serial primary key,
    name varchar(50) not null
);

create type category as enum ('FRESH','FROZEN_FOOD','REFRIGERATED');
create type status_order as Enum('CART', 'PROCESSING', 'CLOSED');

create table if not exists sections (
    section_id serial primary key,
    capacity numeric(10, 2) not null,
    current_temperature double precision not null,
    category category
);

create table if not exists representatives (
    representative_id serial primary key,
    full_name varchar(100) not null,
    section_id int not null,
    constraint fk_sections
        foreign key(section_id)
            references sections(section_id)
);

create table if not exists salesmans (
    salesman_id serial primary key,
    full_name varchar(100) not null
);

create table if not exists products (
    product_id serial primary key,
    product_name varchar(50) not null,
    volume numeric(10, 2) not null,
    minimum_Temperature double precision not null,
    max_temperature double precision not null,
    category category not null,
    salesman_id int not null,
    constraint fk_salesmans
        foreign key(salesman_id)
            references salesmans(salesman_id)
);

create table if not exists batchstocks (
    batch_number serial primary key,
    initial_quantity int not null,
    current_quantity int not null,
    price numeric(10, 2) not null,
    due_date Date not null,
    manufacturing_date Date not null,
    manufacturing_time Time without Time Zone not null,
    product_id int not null,
    constraint fk_products
        foreign key(product_id)
            references products(product_id)
);

create table if not exists in_bound_orders (
    in_bound_order_id serial primary key,
    in_bound_order_date date not null,
    representative_id int not null,
    section_id int not null,
    constraint fk_sections
        foreign key(section_id)
            references sections(section_id),
    constraint fk_representatives
        foreign key(representative_id)
            references representatives(representative_id)
);

create table if not exists buyers (
    buyer_id serial primary key,
    full_name varchar(100) not null
);

create table if not exists purchases_orders_Itens (
    pruchase_order_itens_id serial primary key,
    purchase_order_number bigint not null,
    quantity int not null,
    product_id int not null,
    constraint fk_products
        foreign key(product_id)
            references products(product_id)
);

create table if not exists purchases_orders (
    purchase_order_number bigint not null,
    status_order status_order not null,
    buyer_id int not null,
    purchase_order_date Date not null,
    constraint fk_buyers
        foreign key(buyer_id)
            references buyers(buyer_id)
);