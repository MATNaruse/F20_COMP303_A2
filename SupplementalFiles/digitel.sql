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
    primary key (orderId, custId, productId),
	foreign key (custId) REFERENCES Customer(custId) ON DELETE CASCADE,
    foreign key (productId) REFERENCES Product(productId) ON DELETE CASCADE
);

-- Seed Data --
-- Filling Product Table --
Insert into Product (brandName, modelName, price, imgSrc) values 
-- Apple--
("Apple", "iPhone 11", 1000.00, "css/iPhone11_Black_2Up.jpg"),
("Apple", "iPhone 11 Pro", 1400.00, "css/iphone-11-pro.jpg"),
("Apple", "iPhone 11 Pro Max", 1550.00, "css/iphone-11-pro_max.jpg"),
("Apple", "iPhone 11 XR", 1550.00, "css/iphone-xr.jpg"),
-- Google --
("Google", "Google Pixel 5", 900.00, "css/google-pixel-5.jpg"),
("Google", "Google Pixel 4a", 540.00, "css/google-pixel-4a.jpg"),
-- Samsung --
("Samsung", "Galaxy S20 FE", 1100.00, "css/samsung-galaxy-s20_fe.jpg"),
("Samsung", "Galaxy S20+", 1900.00, "css/samsung-galaxy-s20_p.jpg"),
("Samsung", "Galaxy S20", 1570.00, "css/samsung-galaxy-s20.jpg"),
("Samsung", "Galaxy S20 Ultra", 2200.00, "css/samsung-galaxy-s20_ultra.jpg"),
-- LG --
("LG", "LG Velvet 5G", 750.00, "css/lg-velvet-5g.jpg"),
("LG", "LG K61", 400.00, "css/lg-k61.jpg"),
("LG", "LG K41s", 250.00, "css/lg-k41s.jpg"),
("LG", "LG K31s", 160.00, "css/lg-k31s.jpg");


-- Generate Customers --
Insert into Customer (userName, password, firstname, lastname, address, city, postalCode, country) values
("APerson", "password", "Aaron", "Aaronson", "111 Art Street", "Arlen", "A1A1A1", "Canada"),
("BPerson", "password", "Barry", "Bluejeans", "222 Broadway Blvd", "Barrie", "B2B2B2", "Canada"),
("CPerson", "password", "Carl", "Carlson", "333 Cabot Cres.", "Chicago", "C3C3C3", "Canada");


-- Dummy Orders --
Insert into Orders (orderId, custId, productId, quantity, deliveryDate, creationDate, orderStatus) values
(1, 1, 1, 2, "2020-10-31", "2020-9-25", "On The Way"),
(2, 2, 3, 1, "2020-11-01", "2020-9-25","On The Way"),
(3, 3, 2, 4, "2020-11-28", "2020-9-25","On The Way"),
(4, 2, 5, 2, "2020-11-30", "2020-9-25","On The Way"),
(4, 2, 7, 3, "2020-12-25", "2020-9-25","On The Way"),
(5, 3, 3, 1, "2021-01-10", "2020-9-25", "On The Way"),
(6, 1, 2, 1, "2021-01-17", "2020-9-25", "On The Way");

