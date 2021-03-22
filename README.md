DATABASE used For this project:

```sql
CREATE DATABASE ubase;
create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user springuser
grant all on ubase.* to 'springuser'@'%'; -- Gives all privileges to the new user on the newly created database
```

```sql
CREATE TABLE ubase.users (
id  int(11) AUTO_INCREMENT, 
name VARCHAR(256) NOT NULL,
email VARCHAR(256)NOT NULL UNIQUE, 
password VARCHAR(256)NOT NULL, 
PRIMARY KEY (`id`)
);



insert into ubase.users (name,email,password) VALUES ("Kyle West", "kwst@fakemail.com", "1234Ideclareathumbwar");
insert into ubase.users (name,email,password) VALUES ("Kenny East", "kest@bogusmail.com", "56PickupSticks");
insert into ubase.users (name,email,password) VALUES ("Stan North", "Stnrth@phonymail.com", "78StayupLate");
insert into ubase.users (name,email,password) VALUES ("Eric South", "ErcSth@impostermail.com", "910TosleepAgain");
```