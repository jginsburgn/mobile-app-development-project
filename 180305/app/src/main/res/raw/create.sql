create table agency (
    id integer primary key,
    name text
);

create table person (
    id integer auto_increment primary key,
    name text,
    first_last_name text,
    second_last_name text,
    email text,
    telephone text
);

create table customer (
    id integer primary key,
    id_seller integer not null,
    foreign key(id) references person(id),
    foreign key(id_seller) references seller(id)
);

create table seller (
    id integer primary key,
    id_agency integer,
    foreign key(id) references person(id)
    foreign key(id_agency) references agency(id)
);

create table address (
    id integer auto_increment primary key,
    street text,
    number text,
    unit text,
    city text,
    state text,
    country text,
    id_seller integer,
    id_customer integer,
    id_agency integer,
    foreign key(id_seller) references seller(id),
    foreign key(id_customer) references customer(id),
    foreign key(id_agency) references agency(id)
);

create table brand (
    id integer auto_increment primary key,
    name text
);

create table car (
    id integer auto_increment primary key,
    name text not null,
    year text not null,
    version text not null,
    price real not null,
    discount real,
    id_brand integer not null,
    id_customer integer,
    id_agency integer not null,
    foreign key(id_brand) references brand(id),
    foreign key(id_customer) references customer(id),
    foreign key(id_agency) references agency(id)
);