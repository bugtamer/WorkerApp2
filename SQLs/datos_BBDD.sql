USE WorkerApp2;


-- UBICACIONES

INSERT INTO `ubicacion` VALUES ('1', '41.39906491397483', '2.19027578830719');
INSERT INTO `ubicacion` VALUES ('2', '41.40784461738553', '2.1700572967529297');
INSERT INTO `ubicacion` VALUES ('3', '41.392199663301255', '2.1836185455322266');
INSERT INTO `ubicacion` VALUES ('4', '41.39831645175792', '2.1915149688720703');
INSERT INTO `ubicacion` VALUES ('5', '41.41344498164187', '2.2075653076171875');
INSERT INTO `ubicacion` VALUES ('6', '41.43403986879929', '2.159414291381836');
INSERT INTO `ubicacion` VALUES ('7', '41.40366012210204', '2.174348831176758');
INSERT INTO `ubicacion` VALUES ('8', '41.394903998658826', '2.180614471435547');
INSERT INTO `ubicacion` VALUES ('9', '41.38904446318836', '41.38904446318836');

-- USUARIO (id=1) + MANITAS (id > 1)

INSERT INTO usuario (usu_id, nombre, apellidos, `password`, email, url_avatar, fk_ubi) VALUES
 ('1', 'Manolo', 'del Bombo', '123456', 'manolo@workerapp.com', './imgs/Manolo-entrenador-personal.jpg', '1'),
 ('2', 'Alexandra', 'Lorem ipsum', '123456', 'alexandra@workerapp.com', './imgs/Alexandra-entrenadora-personal.jpg', '2'),
 ('3', 'Lorem', 'ipsum dolor', '123456', 'lorem@workerapp.com', './imgs/Manolo-entrenador-personal.jpg', '3'),
 ('4', 'Western', 'amet consectetur', '123456', 'western', './imgs/Western-entrenador-personal.jpg', '4'),
 ('5', 'Jose', 'adipiscing elit', '123456', 'jose@workerapp.com', './imgs/Jose-entrenador-personal.jpg', '5'),
 ('6', 'Carmina', 'Burana', '123456', 'carmina@workerapp.com', './imgs/Carmina-entrenadora-personal.jpg', '6'),
 ('7', 'Fina', 'eiusmod tempor', '123456', 'fina@workerapp.com', './imgs/Fina-entrenadora-personal.jpg', '7'),
 ('8', 'Loli', 'incididunt ut', '123456', 'loli@workerapp.com', './imgs/Loli-entrenadora-personal.jpg', '8'),
 ('9', 'Rubén', 'incididunt ut', '123456', 'ruben@workerapp.com', './imgs/Ruben-entrenadora-personal.jpg', '9');
 
 -- MANITAS
 
 INSERT INTO manitas (profesion, fk_usu) VALUES
	('Entrenador Personal', 2),
	('Coach personal', 3),
	('Fisioterapeuta deportivo', 4),
	('Entrenador deportivo y coach personal', 5),
	('Nutricionista deportivo', 6),
	('Dietista personal y deportivo', 7),
	('Consejero y coach personal y deportivo', 8);
	
-- MANITAS: educacion

INSERT INTO educacion (edu_id, educacion) VALUES
	(2, 'Educación 1'),
	(3, 'Educación 1'),
	(4, 'Educación 1'),
	(4, 'Educación 2'),
	(4, 'Educación 3'),
	(5, 'Educación 1'),
	(6, 'Educación 1'),
	(6, 'Educación 2'),
	(7, 'Educación 1'),
	(8, 'Educación 1'),
	(8, 'Educación 2');

-- MANITAS: expereincia

INSERT INTO experiencia (exp_id, experiencia) VALUES
	(2, 'Experiencia 1'),
	(2, 'Experiencia 2'),
	(2, 'Experiencia 3'),
	(3, 'Experiencia 1'),
	(4, 'Experiencia 1'),
	(4, 'Experiencia 2'),
	(5, 'Experiencia 1'),
	(6, 'Experiencia 1'),
	(6, 'Experiencia 2'),
	(7, 'Experiencia 1'),
	(8, 'Experiencia 1'),
	(8, 'Experiencia 2'),
	(8, 'Experiencia 3');

-- VALORACIONES

INSERT INTO valoracion (val_id, comentario, puntuacion, `timestamp`, autor_usu_id, receptor_fk_usu) VALUES
	(1, "Excepcional", 5, '2018-06-10 16:54:36', 1, 2),
	(2, "Excepcional", 4, '2018-06-11 15:54:36', 1, 2),
	(3, "Mediocre", 1, '2018-06-12 16:54:36', 1, 2),
	(4, "Mediocre", 1, '2018-06-13 16:54:36', 1, 3),
	(5, "Bueno", 3, '2018-06-14 16:54:36', 1, 4),
	(6, "Bueno", 3, '2018-06-15 16:54:36', 1, 4),
	(7, "Mediocre", 1, '2018-06-16 16:54:36', 1, 5),
	(8, "regular", 2, '2018-06-17 16:54:36', 1, 6),
	(9, "Muy bueno", 4, '2018-06-18 16:54:36', 1, 7),
	(10, "Muy bueno", 4, '2018-06-19 16:54:36', 1, 8),
	(11, "Excepcional", 5, '2018-06-20 16:54:36', 1, 8);

-- MENSAJES

INSERT INTO mensaje (men_id, texto, `timestamp`, urlImagen, emisor_usu_id, receptor_usu_id) VALUES
	(1, "Hola Fina!<br>¿Podrías entrenarme para hacer running los fines de semana?<br>También necesito asesoramiento para elegir zapatillas", '2018-06-20 20:18:36', null, 1, 2),
	(2, null, '2018-06-20 20:18:36', "./imgs/foto-chat-01.jpg", 2, 1),
	(3, "Lo siento, los tengo ya ocupados<br>Pero te puedo recomendar otro coach, si te parece bien?", '2018-06-20 20:18:36', null, 1, 2),
	(4, "Ok!", '2018-06-20 20:26:36', null, 2, 1),
	(5, "Fina me ha comentado tu caso, y puedo ayudarte a elegir equipamiento y entrenarte los fines de semana.<br>Te mantedria el precio de Fina. ¿Te parece bien?", '2018-06-20 20:41:36', null, 1, 2);