DROP TABLE client;
DROP TABLE accounts;


CREATE TABLE client (
  client_id INT AUTO_INCREMENT PRIMARY KEY,
  name      CHAR(50) NOT NULL,
  gender    CHAR(10) NOT NULL,
  email     CHAR(30) NULL,
  phone     CHAR(15) NULL,
  city      CHAR(30) NULL,
  activeAcc_id LONG NULL

);


CREATE TABLE accounts (
  account_id   INT AUTO_INCREMENT PRIMARY KEY,
  balance      NUMERIC(15, 2) NULL,
  overdraft    NUMERIC(15, 2) NULL,
  account_type CHAR(15)       NOT NULL,
  client_id    INT,
  FOREIGN KEY (client_id) REFERENCES client (client_id)
);

INSERT INTO client (name, gender, email, phone, city, activeAcc_id)
VALUES ('Vasya', 'MALE', 'vasya@mail.ua', '(097)123-32-32', 'Dnepr', 1);

INSERT INTO accounts (balance, overdraft, account_type, client_id)
VALUES (23.1, NULL, 'SAVING', 1);

INSERT INTO accounts (balance, overdraft, account_type, client_id)
VALUES (33.1, 2, 'CHECKING', 1);

INSERT INTO client (name, gender, email, phone, city, activeAcc_id)
VALUES ('John Silver', 'MALE', 'john@mail.ua', '(092)123-32-32', 'Dnepr', 3);


INSERT INTO accounts (balance, overdraft, account_type, client_id)
VALUES (323.1, NULL, 'SAVING', 2);

INSERT INTO accounts (balance, overdraft, account_type, client_id)
VALUES (133.1, 2, 'CHECKING', 2);

INSERT INTO client (name, gender, email, phone, city, activeAcc_id)
VALUES ('Masha', 'FAMALE', 'masha@mail.ua', '(093)123-32-32', 'Lvov', 5);


INSERT INTO accounts (balance, overdraft, account_type, client_id)
VALUES (2323.13, NULL, 'SAVING', 3);

INSERT INTO accounts (balance, overdraft, account_type, client_id)
VALUES (236.15, 25, 'CHECKING', 3);


INSERT INTO client (name, gender, email, phone, city, activeAcc_id)
VALUES ('Petya', 'MALE', 'petya@mail.ua', '(098)123-32-32', 'Lvov', 7);


INSERT INTO accounts (balance, overdraft, account_type, client_id)
VALUES (223.18, NULL, 'SAVING', 4);

INSERT INTO accounts (balance, overdraft, account_type, client_id)
VALUES (3336.16, 12, 'CHECKING', 4);


INSERT INTO client (name, gender, email, phone, city, activeAcc_id)
VALUES ('Sofia Baba', 'FAMALE', 'sofia@mail.ua', '(099)123-32-32', 'Dnepr', 9);

INSERT INTO accounts (balance, overdraft, account_type, client_id)
VALUES (2563.1, NULL, 'SAVING', 5);

INSERT INTO accounts (balance, overdraft, account_type, client_id)
VALUES (3763.19, 62, 'CHECKING', 5);


SELECT *
  FROM client AS t1
  JOIN accounts AS t2 ON t2.account_id = t1.activeAcc_id;