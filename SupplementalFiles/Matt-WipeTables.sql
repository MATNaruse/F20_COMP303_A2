-- Wipe Orders --
delete from orders;
Alter table orders auto_increment = 1;

-- Wipe Customers --
delete from customer;
Alter table customer auto_increment = 1;


-- Check Tables --
Select * from Orders;
Select * from Customer;