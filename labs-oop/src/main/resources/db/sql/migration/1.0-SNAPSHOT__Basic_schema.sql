create schema if not exists labs;

create table labs.t_function(
    id serial primary key,
    c_function_type varchar(255),
    c_count integer check ( c_count >= 2 ),
    c_x_from double precision,
    c_x_to double precision
);

create table labs.t_point(
    id serial primary key,
    function_id integer,
    c_x_value double precision,
    c_y_value double precision,
    foreign key (function_id) references labs.t_math_function(id) on delete cascade
);
