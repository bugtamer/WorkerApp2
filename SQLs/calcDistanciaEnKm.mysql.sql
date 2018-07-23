USE workerapp2;

DELIMITER $$
DROP FUNCTION IF EXISTS calcDistanciaEnKm$$

CREATE FUNCTION calcDistanciaEnKm (lat1 DOUBLE, lon1 DOUBLE, lat2 DOUBLE, lon2 DOUBLE) RETURNS DOUBLE
    NO SQL
    DETERMINISTIC
BEGIN

	DECLARE radLat1 double DEFAULT lat1 * PI()/180;
	DECLARE radLat2 double DEFAULT lat2 * PI()/180;
	DECLARE theta double DEFAULT lon1 - lon2;
	DECLARE radTheta double DEFAULT theta * PI()/180;
	DECLARE dist double DEFAULT SIN(radLat1) * SIN(radLat2) + COS(radLat1) * COS(radLat2) * COS(radTheta);
    
	IF dist > 1 THEN
		SET dist = 1;
	END IF;
    
	SET dist = ACOS(dist);
	SET dist = dist * 180/PI();
	SET dist = dist * 60 * 1.1515;
	SET dist = dist * 1.609344;
    
	RETURN dist;
    
END$$
DELIMITER ;

-- SELECT calcDistanciaEnKm(43.5291675, -5.6391020, 43.5292752, -5.6390161) AS distancia FROM ubicacion 