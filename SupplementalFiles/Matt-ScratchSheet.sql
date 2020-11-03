use digitel;
SELECT EXISTS (select * from Customer where userName like "MNaruse3");
Select * from Customer;
Alter table Customer auto_increment = 1;
select * from Customer where userName like "MNaruse";
delete from Customer where username like "MNaruse3";

Insert into Orders (custId, productId, quantity, deliveryDate, orderStatus) Values (1, 1, 20, "2020-10-31", "On The Way");
Insert into Product (brandName, modelName, price) Values ("Samsung", "Galaxy S6", 120.75);
Rename table Order2 to Orders;

select * from Orders;
Delete from Orders where custId = 1;
Alter table Orders auto_increment = 1;

select * from product;

DROP TABLE orders;
delete from orders;



Insert into Orders (orderId, custId, productId, quantity, deliveryDate, orderStatus) Values (7, 1, 1, 25, "2020-10-31", "On The Way");
Update Orders set quantity = 25 where orderId = 1 and custId = 1 and productId = 1;

delete from Orders where orderId = 8;

update product set imgSrc = "css/iPhone11_Black_2Up.jpg" where productId = 1;

Insert into Orders (custId, productId, quantity, deliveryDate, orderStatus) values
(1, 1, 2, "2020-10-31", "On The Way");

-- Will return true if within a day --
Select case when count(*) > 0 then 'true' else 'false' end as bool from orders where creationDate >= now() - interval 1 day;
