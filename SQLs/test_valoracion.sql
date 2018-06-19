-- probar restricciones:
-- suponinedo una BBDD vacia, para funcionar una sentencia requiere la anterior para no fallar

USE WorkerApp2;

-- INSERT INTO ubicacion (latitud, longitud) VALUES (41.1, 2.45);
-- INSERT INTO ubicacion (latitud, longitud) VALUES (41.2, 2.47); -- no deja asignar una ubicacion 2 veces

-- INSERT INTO usuario (nombre, apellidos, email, `password`, url_avatar, fk_ubi) VALUES ("nombre1", "apellidos1", "usuario1@domain.com", "123456", "url_avatar1", 1);
-- INSERT INTO usuario (nombre, apellidos, email, `password`, url_avatar, fk_ubi) VALUES ("nombre2", "apellidos2", "usuario2@domain.com", "123456", "url_avatar2", 2); -- no deja asignar una ubicacion 2 veces

-- INSERT INTO manitas (profesion, fk_usu) VALUES ("Entrenador personal", 2);

INSERT INTO valoracion (comentario, puntuacion, `timestamp`, autor_usu_id, receptor_fk_usu) VALUES ("comentario", 5, now(), 1, 2);
