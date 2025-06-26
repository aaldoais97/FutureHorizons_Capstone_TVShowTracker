drop database if exists bingeboard_db;
create database bingeboard_db;
use bingeboard_db;

create table users (
	id int primary key auto_increment,
    username varchar(255) unique,
	password varchar(255),
	first_name varchar(255),
	last_name varchar(255)
);

create table progress_list (
	id int primary key,
    seasons_completed int,
    episodes_completed int,
    
    foreign key(id) references users(id)
);

create table shows (
	id int primary key auto_increment,
    name varchar(255),
    network_id int,
    director_id int,
    
    foreign key(network_id) references networks(id),
    foreign key(director_id) references directors(id)
);

create table progress_list_shows (
	id int primary key,
    progress_list_id int,
	show_id int,

    foreign key(progress_list_id) references progress_list(id),
	foreign key(show_id) references shows(id)
);