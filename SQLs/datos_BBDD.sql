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

-- USUARIOS

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
	('Entrenador Personal', 3),
	('Entrenador Personal', 4),
	('Entrenador Personal', 5),
	('Entrenador Personal', 6),
	('Entrenador Personal', 7),
	('Entrenador Personal', 8);
