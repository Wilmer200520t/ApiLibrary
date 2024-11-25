create table medico(){
    id bigint not null auto_increment,
    city VARCHAR(100),
    complement VARCHAR(100),
    district VARCHAR(100),
    number INT,
    street VARCHAR(100),
    document VARCHAR(20),
    email VARCHAR(100),
    name VARCHAR(100),
    speciality VARCHAR(50)
    primary key(id);
}