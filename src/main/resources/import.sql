DROP TABLE IF EXISTS debts;
DROP TABLE IF EXISTS banks;
DROP TABLE IF EXISTS users;
-- create users table
CREATE TABLE users (
                       id INTEGER PRIMARY KEY,
                       username TEXT NOT NULL
);

-- create banks table
CREATE TABLE banks (
                       id INTEGER PRIMARY KEY,
                       name TEXT NOT NULL
);

-- create banks debts
CREATE TABLE debts (
                       id INTEGER PRIMARY KEY,
                       description TEXT NOT NULL,
                       total INTEGER,
                       total_payed INTEGER,
                       fees INTEGER,
                       remaining_fees INTEGER,
                       total_remaining INTEGER,
                       currency TEXT,
                       is_canceled boolean,
                       user_id integer,
                       foreign key(user_id) references users(id),
                       bank_id integer,
                       foreign key(bank_id) references banks(id)
);

-- insert into users table
INSERT INTO users(id, username)
VALUES(1, 'Daniel');

INSERT INTO users(id, username)
VALUES(2, 'Laura');

-- insert into banks table
INSERT INTO banks(id, name)
VALUES(1, 'BBVA');

INSERT INTO banks(id, name)
VALUES(2, 'Davivienda');

INSERT INTO banks(id, name)
VALUES(3, 'Nequi');


-- insert into Debts table
-- BBVA
INSERT INTO debts(id, description, total, total_payed, fees, remaining_fees, total_remaining, currency, is_canceled, user_id, bank_id)
VALUES(1, 'Furniture', 10000, 2000, 5, 4, 8000, 'COP', false, 1, 1);

INSERT INTO debts(id, description, total, total_payed, fees, remaining_fees, total_remaining, currency, is_canceled, user_id, bank_id)
VALUES(2, 'Floor', 20000, 2858, 7, 6, 17142, 'COP', false, 1, 1);

-- Davivienda
INSERT INTO debts(id, description, total, total_payed, fees, remaining_fees, total_remaining, currency, is_canceled, user_id, bank_id)
VALUES(3, 'Phone', 1000000, 500000, 2, 1, 500000, 'COP', false, 1, 2);

INSERT INTO debts(id, description, total, total_payed, fees, remaining_fees, total_remaining, currency, is_canceled, user_id, bank_id)
VALUES(4, 'Monitor', 2000000, 1000000, 4, 2, 1000000, 'COP', false, 2, 2);

-- Nequi
INSERT INTO debts(id, description, total, total_payed, fees, remaining_fees, total_remaining, currency, is_canceled, user_id, bank_id)
VALUES(5, 'IPhone', 1000000, 500000, 2, 1, 500000, 'COP', false, 2, 3);


-- fetch data from users
SELECT d.*
FROM debts d
WHERE d.user_id = 2;
