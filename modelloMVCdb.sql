Drop Database if exists modelloMVCdb;
CREATE DATABASE modelloMVCdb;
use modelloMVCdb;

CREATE TABLE customer (
id int not null,
firstName varchar(20) not null,
lastName varchar(20) not null,
balance double not null
); 

INSERT INTO customer VALUES 
(1,'Harry','Hacker', '-3456.78'),
(2,'Codie', 'Coder', '234.56'),
(3,'Polly', 'Programmer', '987654.32');
