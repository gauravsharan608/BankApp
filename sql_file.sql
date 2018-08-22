use bank_db;
create table customers(
id int,
first_name varchar(12),
last_name varchar(13),
username varchar(15),
password varchar(30)
);

create table accounts(
account_no bigint primary key,
balance double,
customer_id int,
foreign key(customer_id) references customers(id)
);

create table transactions(
id int primary key auto_increment,
account_no bigint,
beneficiary_ac_no bigint,
transaction_amount double,
transaction_date date,
transaction_detail varchar(129),
foreign key(account_no) references accounts(account_no),
foreign key(beneficiary_ac_no) references accounts(account_no)
);

DELIMITER $$ 
DROP PROCEDURE IF EXISTS `insertCustomers`;
CREATE PROCEDURE insertCustomers (IN id int,IN first_name VARCHAR(18),IN last_name VARCHAR(20),IN username VARCHAR(25), IN password varchar(20))
BEGIN
       INSERT INTO customers VALUES (id, first_name,last_name,username,password);
END$$

DELIMITER ;

CALL insertCustomers(1,"Gaurav","Sharan", "gaurav",'gaurav' );
CALL insertCustomers(2,"Rocky","K", "rocky",'rocky' );
CALL insertCustomers(3,"Daisy","Rose", "daisy",'daisy' );



DELIMITER $$ 
DROP PROCEDURE IF EXISTS `insertAccounts`;
CREATE PROCEDURE insertAccounts (IN account_no bigint,IN balance double,IN customer_id INT)
BEGIN
       INSERT INTO accounts VALUES (account_no,balance,customer_id);
END$$

DELIMITER ;

CALL insertAccounts(123456789120,30000.0,1 );
CALL insertAccounts(123456789121,40000.0,2);
CALL insertAccounts(123456789122,32000.0,3 );
CALL insertAccounts(123456789123,20000.60,1);
CALL insertAccounts(123456789124,52000.48,1 );


create table beneficiaries(
id int primary key auto_increment,
beneficiary_name  varchar(40),
beneficiary_ac_no bigint,
customer_id int,
foreign key(customer_id) references customers(id),
foreign key(beneficiary_ac_no) references accounts(account_no)
);

DELIMITER $$ 
DROP PROCEDURE IF EXISTS `insertBeneficiaries`;
CREATE PROCEDURE insertBeneficiaries (IN beneficiary_name VARCHAR(40),IN ac_no bigint,IN customer_id INT)
BEGIN
       INSERT INTO beneficiaries VALUES (null,beneficiary_name,ac_no,customer_id);
END$$

DELIMITER ;

CALL insertBeneficiaries("Gaurav Sharan", 123456789120,2 );
CALL insertBeneficiaries("Gaurav Sharan", 123456789120,3 );
CALL insertBeneficiaries("Rocky K", 123456789121,1 );
CALL insertBeneficiaries("Daisy Rose", 123456789122,1 );


