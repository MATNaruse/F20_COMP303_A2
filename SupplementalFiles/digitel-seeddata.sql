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


-- Dummy Orders --
Insert into Orders (custId, productId, quantity, deliveryDate, creationDate, orderStatus) values
(1, 1, 2, "2020-10-31", "2020-9-25", "On The Way"),
(1, 3, 1, "2020-11-01", "2020-9-25","On The Way"),
(1, 2, 4, "2020-11-28", "2020-9-25","On The Way"),
(1, 5, 2, "2020-11-30", "2020-9-25","On The Way"),
(1, 7, 3, "2020-12-25", "2020-9-25","On The Way"),
(1, 3, 1, "2021-01-10", "2020-9-25", "On The Way"),
(1, 2, 1, "2021-01-17", "2020-9-25", "On The Way");