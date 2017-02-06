drop table client;
drop table accounts;

create table client (id long auto_increment primary key, 
                              name char(50) not null,
                              gender char(1) not null,
                              email char(30) null,
                              phone char(15) null,
                              city char(30) null);


create table accounts(balance numeric(15,2) null,
                                   overdraft numeric(15,2) null,
                                   account_type char(15) not null,
                                   client_id long,
                                   foreign key(client_id) REFERENCES client(id));

insert into client(name,gender,email,phone,city)
values('Vasya','M','vasya@mail.ua','(097)123-32-32','Dnepr'); 

insert into accounts
values(23.1,null,'SAVING',1);

insert into accounts
values(33.1,2,'CHECKING',1);

insert into client(name,gender,email,phone,city)
values('John Silver','M','john@mail.ua','(092)123-32-32','Dnepr'); 


insert into accounts
values(323.1,null,'SAVING',2);

insert into accounts
values(133.1,2,'CHECKING',2);

insert into client(name,gender,email,phone,city)
values('Masha','F','masha@mail.ua','(093)123-32-32','Lvov'); 


insert into accounts
values(2323.13,null,'SAVING',3);

insert into accounts
values(236.15,25,'CHECKING',3);


insert into client(name,gender,email,phone,city)
values('Petya','M','petya@mail.ua','(098)123-32-32','Lvov');


insert into accounts
values(223.18,null,'SAVING',4);

insert into accounts
values(3336.16,12,'CHECKING',4);


insert into client(name,gender,email,phone,city)
values('Sofia Baba','F','sofia@mail.ua','(099)123-32-32','Dnepr');

insert into accounts
values(2563.1,null,'SAVING',5);

insert into accounts
values(3763.19,62,'CHECKING',5);

select * 
  from client as t1
  join accounts as t2 on t2.client_id = t1.id;

 

