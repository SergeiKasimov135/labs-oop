create schema if not exists labs;

create table labs.t_function(
    id serial primary key,
    c_function_type varchar(255),
    c_count integer check ( c_count >= 2 ),
    c_x_from double,
    c_x_to double
);

create table labs.t_point(
    id serial primary key,
    function_id int,
    c_x_value double,
    c_y_value double,
    foreign key (function_id) references labs.t_math_function(id) on delete cascade
);
