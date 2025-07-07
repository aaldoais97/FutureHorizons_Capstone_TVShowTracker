/*
	This script creates the BingeBoard database and all tables
    (WARNING: THIS DELETES AND REPLACES THE DATABASE WITH AN EMPTY ONE)
*/

drop database if exists bingeboard_db;
create database bingeboard_db;
use bingeboard_db;

create table users (
	id int primary key auto_increment,
    username varchar(255) unique,
	password varchar(255)
);

-- Each user must have one progress list and each list must have one user
create table progress_lists (
	id int primary key,
    
    foreign key(id) references users(id)
);

create table actors(
	id int primary key auto_increment,
    name varchar(255)
);

create table writers(
	id int primary key auto_increment,
    name varchar(255)
);

create table directors(
	id int primary key auto_increment,
    name varchar(255)
);

-- TV Networks must have unique names due to copyright
create table networks(
	id int primary key auto_increment,
    name varchar(255) unique
);

-- Genres are unique to ensure proper data groupings
create table genres(
	id int primary key auto_increment,
    name varchar(255) unique
);

create table shows (
	id int primary key auto_increment,
    name varchar(255),
    network_id int,
    director_id int,
    
    foreign key(network_id) references networks(id),
    foreign key(director_id) references directors(id)
);

create table seasons(
	id int primary key auto_increment,
    name varchar(255),
    summary varchar(255),
    show_id int,
    
    foreign key(show_id) references shows(id)
);

create table episodes(
	id int primary key auto_increment,
    name varchar(255),
    summary varchar(255),
    season_id int,
    
    foreign key(season_id) references seasons(id)
);

create table shows_progress_lists (
	id int primary key,
    progress_list_id int,
	show_id int,
    episodes_completed int,

    foreign key(progress_list_id) references progress_lists(id),
	foreign key(show_id) references shows(id)
);

create table shows_actors(
	id int primary key,
    show_id int,
    actor_id int,
    
    foreign key(show_id) references shows(id),
    foreign key(actor_id) references actors(id)
);

create table shows_writers(
	id int primary key,
    show_id int,
    writer_id int,
    
    foreign key(show_id) references shows(id),
    foreign key(writer_id) references writers(id)
);

create table shows_genres(
	id int primary key,
    show_id int,
    genre_id int,
    
    foreign key(show_id) references shows(id),
    foreign key(genre_id) references genres(id)
);