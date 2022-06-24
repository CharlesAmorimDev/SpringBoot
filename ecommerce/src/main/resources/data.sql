CREATE TABLE CLIENT (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(50),
    AGE INTEGER
);

CREATE TABLE PRODUCT (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(50),
    PRICE_UNITARY NUMERIC(20,2)
);

CREATE TABLE CLIENT_ORDER (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    CLIENT_ID INTEGER REFERENCES CLIENT (ID),
    DATE_ORDER TIMESTAMP,
    TOTAL NUMERIC(20.2)
);

CREATE TABLE ORDER_ITEMS (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    CLIENT_ORDER_ID INTEGER REFERENCES CLIENT_ORDER (ID),
    PRODUCT_ID INTEGER REFERENCES PRODUCT (ID),
    AMOUNT INTEGER
);