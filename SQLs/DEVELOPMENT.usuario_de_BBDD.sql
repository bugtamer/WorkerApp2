-- DROP USER WorkerApp2@localhost;

CREATE USER WorkerApp2@localhost IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON WorkerApp2.* TO WorkerApp2@localhost;
FLUSH PRIVILEGES;
