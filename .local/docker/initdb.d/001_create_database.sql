CREATE USER IF NOT EXISTS 'webapp'@'%' IDENTIFIED BY 'webapp';
CREATE USER IF NOT EXISTS 'junit'@'%' IDENTIFIED BY 'junit';
CREATE USER IF NOT EXISTS 'phpmyadmin'@'%' IDENTIFIED BY 'phpmyadmin';
CREATE DATABASE IF NOT EXISTS maindb;
CREATE DATABASE IF NOT EXISTS testdb;
GRANT ALL ON maindb.* TO 'webapp'@'%';
GRANT ALL ON testdb.* TO 'junit'@'%';
