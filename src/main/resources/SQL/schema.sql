-- Tabla: customer
CREATE TABLE customer (
  id_customer VARCHAR(15) PRIMARY KEY,
  name VARCHAR(60) NOT NULL,
  address VARCHAR(100),
  email VARCHAR(50),
  phone_number VARCHAR(20)
);

-- Tabla: pizza
CREATE TABLE pizza (
   id_pizza SERIAL PRIMARY KEY,
   name VARCHAR(30) NOT NULL,
   description VARCHAR(150),
   price NUMERIC(5,2) NOT NULL,
   vegetarian BOOLEAN DEFAULT FALSE,
   vegan BOOLEAN DEFAULT FALSE,
   available BOOLEAN DEFAULT TRUE
);

-- Tabla: pizza_order
CREATE TABLE pizza_order (
     id_order SERIAL PRIMARY KEY,
     id_customer VARCHAR(15) REFERENCES customer(id_customer) ON DELETE CASCADE,
     date TIMESTAMP NOT NULL,
     total NUMERIC(6,2) NOT NULL,
     method CHAR(1), -- E.g. 'C' for cash, 'D' for debit, etc.
     additional_notes VARCHAR(200)
);

-- Tabla: order_item
CREATE TABLE order_item (
    id_item SERIAL PRIMARY KEY,
    id_order INT REFERENCES pizza_order(id_order) ON DELETE CASCADE,
    id_pizza INT REFERENCES pizza(id_pizza) ON DELETE CASCADE,
    quantity NUMERIC(2,1) NOT NULL,
    price NUMERIC(5,2) NOT NULL
);


CREATE TABLE users (
    username VARCHAR(20) NOT NULL,
    password VARCHAR(200) NOT NULL,
    email VARCHAR(50) NULL,
    locked BOOLEAN NOT NULL,
    disabled BOOLEAN NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE user_role (
   username VARCHAR(20) NOT NULL,
   role VARCHAR(20) NOT NULL,
   granted_date TIMESTAMP NOT NULL,
   PRIMARY KEY (username, role),
   CONSTRAINT fk_user_role_user1
       FOREIGN KEY (username)
           REFERENCES "user" (username)
           ON DELETE RESTRICT
           ON UPDATE CASCADE
);

COMMENT ON COLUMN user_role.role IS 'CUSTOMER\nADMIN';
