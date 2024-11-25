create table user(
    id bigint not null auto_increment,
    username varchar(100) not null,
    password varchar(200) not null,

    primary key (id)
)