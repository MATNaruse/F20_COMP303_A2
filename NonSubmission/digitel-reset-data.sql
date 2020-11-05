-- NOTE: Remove this file before final submission
-- NOTE: You will need to remove safety mode to run Clause-less DELETE FROM

-- Empty & Reset Customer Table --
Delete from Customer;
Alter Table Customer auto_increment = 1;

-- Empty & Reset Product Table --
Delete from Product;
Alter Table Product auto_increment = 1;

-- Empty & Reset Orders Table --
Truncate Table Orders;
Alter Table Orders auto_increment = 1;

