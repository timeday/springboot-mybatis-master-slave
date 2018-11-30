DROP DATABASE IF EXISTS user_master;
CREATE DATABASE user_master;
CREATE TABLE user_master.user(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  age DOUBLE(10,2) NOT NULL DEFAULT 0
);
INSERT INTO user_master.user (name, age) VALUES('master', '1');


DROP DATABASE IF EXISTS user_slave_one;
CREATE DATABASE user_slave_one;
CREATE TABLE user_slave_one.user(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  age DOUBLE(10,2) NOT NULL DEFAULT 0
);
INSERT INTO user_slave_one.user(name, age) VALUES('slaveOne', '1');

DROP DATABASE IF EXISTS user_slave_two;
CREATE DATABASE user_slave_two;
CREATE TABLE user_slave_two.user(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  age DOUBLE(10,2) NOT NULL DEFAULT 0
);
INSERT INTO user_slave_two.user (name, age) VALUES('slaveTwo', '1');

DROP DATABASE IF EXISTS user_slave_three;
CREATE DATABASE user_slave_three;
CREATE TABLE user_slave_three.user(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  age DOUBLE(10,2) NOT NULL DEFAULT 0
);
INSERT INTO user_slave_three.user (name, age) VALUES('slaveThree', '1');