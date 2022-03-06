CREATE OR REPLACE VIEW VIEW_CARTA AS
SELECT C.ID_CARTA AS ID
	 , R.NOMBRE AS RESTAURANTE
     , R.ID_RESTAURANTE AS ID_RESTAURANTE
	 , P.NOMBRE AS PRODUCTO
     , P.ID_PRODUCTO AS ID_PRODUCTO
	 , C.DISPONIBLE AS DISPONIBILIDAD
	 , C.PVP AS PVP
FROM CARTA C, PRODUCTO P, RESTAURANTE R
WHERE C.ID_PRODUCTO = P.ID_PRODUCTO AND C.ID_RESTAURANTE = R.ID_RESTAURANTE;
/
CREATE OR REPLACE VIEW VIEW_CLIENTE AS
SELECT P.ID_PERSONA AS ID
     , P.DNI AS DNI
     , P.NOMBRE AS NOMBRE
     , P.APELLIDO_1 AS APELLIDO1
     , P.APELLIDO_2 AS APELLIDO2
     , P.DOMICILIO AS DOMICILIO
     , P.TELEFONO AS TELEFONO
     , P.EMAIL AS EMAIL
     , X.NOMBRE AS PROVINCIA
     , X.ID_PROVINCIA AS ID_PROVINCIA
     , C.NUM_TARJETA AS TARJETA
     , C.ID_CLIENTE AS ID_CLIENTE
FROM PERSONA P, CLIENTE C, PROVINCIA X
WHERE P.ID_PERSONA = C.ID_PERSONA AND P.ID_PROVINCIA = X.ID_PROVINCIA;
/
CREATE OR REPLACE VIEW VIEW_COMANDA AS
SELECT C.ID_COMANDA AS ID
     , C.FECHA AS FECHA
     , (SELECT NOMBRE FROM PRODUCTO WHERE ID_PRODUCTO = K.ID_PRODUCTO) AS PRODUCTO
     , (SELECT ID_PRODUCTO FROM PRODUCTO WHERE ID_PRODUCTO = K.ID_PRODUCTO) AS ID_PRODUCTO
     , c.ID_CARTA AS ID_CARTA
     , (SELECT T.NOMBRE FROM TIPO_PRODUCTO T, PRODUCTO P WHERE K.ID_PRODUCTO = P.ID_PRODUCTO AND P.ID_TIPO = T.ID_TIPO) AS TIPO
     , (SELECT T.ID_TIPO FROM TIPO_PRODUCTO T, PRODUCTO P WHERE K.ID_PRODUCTO = P.ID_PRODUCTO AND P.ID_TIPO = T.ID_TIPO) AS ID_TIPO
     , C.PRECIO AS PRECIO
     , (SELECT NOMBRE FROM PERSONA  WHERE ID_PERSONA = Q.ID_PERSONA) AS CLIENTE
     , Q.ID_CLIENTE AS ID_CLIENTE
     , (SELECT NOMBRE FROM PERSONA  WHERE ID_PERSONA = E.ID_PERSONA) AS EMPLEADO
     , E.ID_EMPLEADO AS ID_EMPLEADO
  FROM COMANDA C, CLIENTE Q, EMPLEADO E, CARTA K
 WHERE C.ID_CLIENTE = Q.ID_CLIENTE
   AND C.ID_EMPLEADO = E.ID_EMPLEADO
   AND C.ID_CARTA = K.ID_CARTA;
/
CREATE OR REPLACE VIEW VIEW_EMPLEADO AS
SELECT P.ID_PERSONA AS ID
     , P.DNI AS DNI
     , P.NOMBRE AS NOMBRE
     , P.APELLIDO_1 AS APELLIDO1
     , P.APELLIDO_2 AS APELLIDO2
     , P.DOMICILIO AS DOMICILIO
     , P.TELEFONO AS TELEFONO
     , P.EMAIL AS EMAIL
     , X.NOMBRE AS PROVINCIA
     , X.ID_PROVINCIA AS ID_PROVINCIA
     , E.IBAN AS IBAN
     , E.NSS AS NSS
     , (SELECT NOMBRE FROM TIPO_EMPLEADO WHERE ID_TIPO = E.ID_TIPO) AS EMPLEADO
     , (SELECT ID_TIPO FROM TIPO_EMPLEADO WHERE ID_TIPO = E.ID_TIPO) AS ID_TIPO_EMPLEADO
     , (SELECT SAL FROM TIPO_EMPLEADO WHERE ID_TIPO = E.ID_TIPO) AS SALARIO
     , E.ID_EMPLEADO AS ID_EMPLEADO
FROM PERSONA P, EMPLEADO E, PROVINCIA X
WHERE P.ID_PERSONA = E.ID_PERSONA AND P.ID_PROVINCIA = X.ID_PROVINCIA;
/
CREATE OR REPLACE VIEW VIEW_PRODUCTO AS
SELECT ID_PRODUCTO AS ID
     , P.NOMBRE AS PRODUCTO
     , T.NOMBRE AS TIPO
     , T.ID_TIPO AS ID_TIPO
FROM PRODUCTO P, TIPO_PRODUCTO T
WHERE P.ID_TIPO = T.ID_TIPO;
/
CREATE OR REPLACE VIEW VIEW_PROVINCIA  AS SELECT * FROM PROVINCIA;
/
CREATE OR REPLACE VIEW VIEW_PERSONA AS SELECT * FROM PERSONA;
/
CREATE OR REPLACE VIEW VIEW_RESTAURANTE AS SELECT * FROM RESTAURANTE;
/
CREATE OR REPLACE VIEW VIEW_TIPO_EMPLEADO AS SELECT * FROM TIPO_EMPLEADO;
/
CREATE OR REPLACE VIEW VIEW_TIPO_PRODUCTO AS SELECT * FROM TIPO_PRODUCTO;
/
CREATE OR REPLACE VIEW VIEW_LOG_CLIENTE AS SELECT * FROM LOG_CLIENTE;
/
CREATE OR REPLACE VIEW VIEW_LOG_PRODUCTO AS SELECT * FROM LOG_PRODUCTO;
/
CREATE OR REPLACE VIEW VIEW_LOG_EMPLEADO AS SELECT * FROM LOG_EMPLEADO;
/
CREATE OR REPLACE VIEW VIEW_LOG_TIPO_EMPLEADO AS SELECT * FROM LOG_TIPO_EMPLEADO;
/
CREATE OR REPLACE VIEW VIEW_LOG_TIPO_PRODUCTO AS SELECT * FROM LOG_TIPO_PRODUCTO;