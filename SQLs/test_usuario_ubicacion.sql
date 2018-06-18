USE WorkerApp2;
-- INSERT INTO ubicacion (latitud, longitud) VALUES (41.1, 2.45); -- sin esta linea fallara
INSERT INTO usuario (nombre, apellidos, fk_ubi) VALUES ("nombre", "apellidos", 1);