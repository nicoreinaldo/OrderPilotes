DROP TABLE orders;
DROP TABLE client;
DROP TABLE address;
DROP TABLE users;

CREATE TABLE users
(
    id       VARCHAR(150) PRIMARY KEY,
    password VARCHAR(250) NOT NULL,
    email    VARCHAR(250) NOT NULL
);

CREATE TABLE client
(
    id         INTEGER PRIMARY KEY,
    id_user    VARCHAR(250) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name  VARCHAR(100) NOT NULL,
    telephone  VARCHAR(25)  NOT NULL
);


CREATE TABLE address
(
    street    VARCHAR(100) PRIMARY KEY,
    post_code INTEGER      NOT NULL,
    city      VARCHAR(100) NOT NULL,
    country   VARCHAR(100) NOT NULL
);

CREATE TABLE orders
(
    id         INTEGER PRIMARY KEY,
    address_id INTEGER        NOT NULL,
    count      INTEGER        NOT NULL,
    total      DECIMAL(10, 2) NOT NULL,
    time       TIMESTAMP,
    client_id  INTEGER        NOT NULL
);

ALTER TABLE orders
    ADD FOREIGN KEY (client_id)
        REFERENCES client (id);

ALTER TABLE orders
    ADD FOREIGN KEY (address_id)
        REFERENCES address (street);

ALTER TABLE client
    ADD FOREIGN KEY (id_user)
        REFERENCES users (id);