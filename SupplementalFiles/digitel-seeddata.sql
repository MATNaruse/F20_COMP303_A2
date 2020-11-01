-- Filling Product Table --
Insert into Product (brandName, modelName, price) values 
-- Apple--
("Apple", "iPhone 11", 1000.00),
("Apple", "iPhone 11 Pro", 1400.00),
("Apple", "iPhone 11 Pro Max", 1550.00),
("Apple", "iPhone 11 XR", 1550.00),
-- Google --
("Google", "Google Pixel 5", 900.00),
("Google", "Google Pixel 4a", 540.00),
-- Samsung --
("Samsung", "Galaxy S20 FE", 1100.00),
("Samsung", "Galaxy S20+", 1900.00),
("Samsung", "Galaxy S20", 1570.00),
("Samsung", "Galaxy S20 Ultra", 2200.00),
-- LG --
("LG", "LG Velvet 5G", 750.00),
("LG", "LG K61", 400.00),
("LG", "LG K41s", 250.00),
("LG", "LG K31s", 160.00);

-- Dummy Orders --
Insert into Orders (custId, productId, quantity, deliveryDate, orderStatus) values
(1, 1, 2, "2020-10-31", "On The Way"),
(1, 3, 1, "2020-11-01", "On The Way"),
(1, 2, 4, "2020-11-28", "On The Way"),
(1, 5, 2, "2020-11-30", "On The Way"),
(1, 7, 3, "2020-12-25", "On The Way"),
(1, 3, 1, "2021-01-10", "On The Way"),
(1, 2, 1, "2021-01-17", "On The Way");