drop table if exists Person;
create table Person(
                       id SERIAL primary key,
                       name varchar unique not null,
                       year_of_birth int check (year_of_birth <= 2022 and year_of_birth >= 1923)
);

insert into Person (name, year_of_birth) values ('person#1', 1997);
insert into Person (name, year_of_birth) values ('person#2', 1995);
insert into Person (name, year_of_birth) values ('person#3', 1958);
insert into Person (name, year_of_birth) values ('person#4', 1982);
insert into Person (name, year_of_birth) values ('person#5', 1996);
insert into Person (name, year_of_birth) values ('person#6', 1946);
insert into Person (name, year_of_birth) values ('person#7', 1988);

select * from person;

drop table if exists Book;
create table Book(
                     id SERIAL primary key,
                     person_id int references Person(id) on delete set null,
                     name varchar unique not null,
                     author varchar not null,
                     year int check (year <= 2022)
    );

insert into Book (person_id, name, author, year) values (1, 'Book#1', 'Author#1' , 1997);
insert into Book (person_id, name, author, year) values (1, 'Book#2', 'Author#1' , 2000);
insert into Book (person_id, name, author, year) values (2, 'Book#3', 'Author#2' , 2001);
insert into Book (person_id, name, author, year) values (3, 'Book#4', 'Author#2' , 1986);
insert into Book (person_id, name, author, year) values (6, 'Book#5', 'Author#2' , 1855);
insert into Book (person_id, name, author, year) values (7, 'Book#6', 'Author#3' , 1967);
insert into Book (person_id, name, author, year) values (4, 'Book#7', 'Author#4' , 1999);
insert into Book (person_id, name, author, year) values (4, 'Book#8', 'Author#4' , 2011);

insert into Book (person_id, name, author, year) values (null, 'Book#9', 'Author#4' , 2011);

select * from Book;

UPDATE Book SET person_id = 2 WHERE id = 10;

select p.name, b.name from Person p left join book b on p.id = b.person_id;