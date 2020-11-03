CREATE DATABASE digitel;

USE digitel;
CREATE TABLE Customer(
	custId SMALLINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    userName varchar(30) UNIQUE NOT NULL,
    password varchar(30) NOT NULL,
    firstname varchar(30) NOT NULL,
    lastname varchar(30) NOT NULL,
    address varchar(30) NOT NULL,
    city varchar(30) NOT NULL,
    postalCode varchar(30) NOT NULL,
    country varchar(30) NOT NULL
);

CREATE TABLE Product(
	productId SMALLINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    brandName varchar(30) NOT NULL,
    modelName varchar(30) NOT NULL,
    price double NOT NULL,
    imgSrc varchar(50)
);

-- Changed name from "Order" to "Orders" due to keyword conflict
CREATE TABLE Orders(
	orderId SMALLINT NOT NULL AUTO_INCREMENT,
    custId SMALLINT,
    productId SMALLINT,
    quantity INT NOT NULL,
    deliveryDate DATE NOT NULL,
    orderStatus varchar(30),
	creationDate DATETIME NOT NULL,
    primary key (orderId, custId, productId, deliveryDate),
	foreign key (custId) REFERENCES Customer(custId) ON DELETE CASCADE,
    foreign key (productId) REFERENCES Product(productId) ON DELETE CASCADE
);