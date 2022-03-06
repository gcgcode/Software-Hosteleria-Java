create or replace PACKAGE GEST_PERSONA IS -- CABECERA >> CRUD CLIENTE
-- list 
    FUNCTION LISTAR_PERSONA RETURN SYS_REFCURSOR;
    FUNCTION LISTAR_EMPLEADO RETURN SYS_REFCURSOR;
    FUNCTION LISTAR_CLIENTE RETURN SYS_REFCURSOR;
END GEST_PERSONA;
/
create or replace PACKAGE BODY GEST_PERSONA
IS
     FUNCTION LISTAR_PERSONA RETURN SYS_REFCURSOR
        IS
            Q_CURSOR SYS_REFCURSOR;
        BEGIN
            OPEN Q_CURSOR FOR SELECT * FROM VIEW_PERSONA;  
            RETURN Q_CURSOR;        
     END LISTAR_PERSONA;
--
     FUNCTION LISTAR_EMPLEADO RETURN SYS_REFCURSOR
        IS
            Q_CURSOR SYS_REFCURSOR;
        BEGIN
            OPEN Q_CURSOR FOR SELECT * FROM VIEW_EMPLEADO_TABLE;  
            RETURN Q_CURSOR;        
     END LISTAR_EMPLEADO;
--     
      FUNCTION LISTAR_CLIENTE RETURN SYS_REFCURSOR
    IS
        Q_CURSOR SYS_REFCURSOR;
    BEGIN
        OPEN Q_CURSOR FOR SELECT * FROM VIEW_CLIENTE_TABLE;  
        RETURN Q_CURSOR;        
    END LISTAR_CLIENTE;
END GEST_PERSONA;
/
create or replace PACKAGE GEST_CLIENTE IS -- CABECERA >> CRUD CLIENTE
-- list 
    FUNCTION LISTAR RETURN SYS_REFCURSOR;
-- list EXTRA  
    FUNCTION LISTAR_NOMBRE(P_NOMBRE PERSONA.NOMBRE%TYPE) RETURN SYS_REFCURSOR;
-- insert
    PROCEDURE CREAR
         (P_DNI PERSONA.DNI%TYPE,
         P_NOMBRE PERSONA.NOMBRE%TYPE,
         P_APELLIDO_1 PERSONA.APELLIDO_1%TYPE,
         P_APELLIDO_2 PERSONA.APELLIDO_2%TYPE,
         P_DOMICILIO PERSONA.DOMICILIO%TYPE,
         P_TELEFONO PERSONA.TELEFONO%TYPE,
         P_EMAIL PERSONA.EMAIL%TYPE,
         P_ID_PROVINCIA PERSONA.ID_PROVINCIA%TYPE,
         P_NUM_TARJETA CLIENTE.NUM_TARJETA%TYPE);
-- update
    PROCEDURE ACTUALIZAR 
        (P_DNI PERSONA.DNI%TYPE,
         P_NOMBRE PERSONA.NOMBRE%TYPE,
         P_APELLIDO_1 PERSONA.APELLIDO_1%TYPE,
         P_APELLIDO_2 PERSONA.APELLIDO_2%TYPE,
         P_DOMICILIO PERSONA.DOMICILIO%TYPE,
         P_TELEFONO PERSONA.TELEFONO%TYPE,
         P_EMAIL PERSONA.EMAIL%TYPE,
         P_ID_PROVINCIA PERSONA.ID_PROVINCIA%TYPE,
         P_NUM_TARJETA CLIENTE.NUM_TARJETA%TYPE,
         P_ID_PERSONA PERSONA.ID_PERSONA%TYPE);
-- delete        
      PROCEDURE ELIMINAR 
        (P_ID_PERSONA PERSONA.ID_PERSONA%TYPE);      
END GEST_CLIENTE;
/
create or replace PACKAGE BODY GEST_CLIENTE IS -- PACKAGE BODY >> CRUD CLIENTE
-- list
    FUNCTION LISTAR RETURN SYS_REFCURSOR
        IS
            Q_CURSOR SYS_REFCURSOR;
        BEGIN
            OPEN Q_CURSOR FOR SELECT * FROM VIEW_CLIENTE;  
            RETURN Q_CURSOR;        
     END LISTAR;
-- list EXTRA     
     FUNCTION LISTAR_NOMBRE(P_NOMBRE PERSONA.NOMBRE%TYPE) RETURN SYS_REFCURSOR
        IS 
         Q_CURSOR SYS_REFCURSOR;
        BEGIN
            OPEN Q_CURSOR FOR SELECT * FROM VIEW_CLIENTE WHERE NOMBRE = P_NOMBRE;
            RETURN Q_CURSOR;
        END LISTAR_NOMBRE;
-- insert
    PROCEDURE CREAR
         (
         P_DNI PERSONA.DNI%TYPE,
         P_NOMBRE PERSONA.NOMBRE%TYPE,
         P_APELLIDO_1 PERSONA.APELLIDO_1%TYPE,
         P_APELLIDO_2 PERSONA.APELLIDO_2%TYPE,
         P_DOMICILIO PERSONA.DOMICILIO%TYPE,
         P_TELEFONO PERSONA.TELEFONO%TYPE,
         P_EMAIL PERSONA.EMAIL%TYPE,
         P_ID_PROVINCIA PERSONA.ID_PROVINCIA%TYPE,
         P_NUM_TARJETA CLIENTE.NUM_TARJETA%TYPE)
        IS
            SEQ1 NUMBER := SEQ_PERSONA.NEXTVAL;
            SEQ2 NUMBER := SEQ_CLIENTE.NEXTVAL;
        BEGIN
            INSERT INTO PERSONA (ID_PERSONA, DNI, NOMBRE, APELLIDO_1, APELLIDO_2, DOMICILIO, TELEFONO, EMAIL, ID_PROVINCIA)
            VALUES (SEQ1, P_DNI, P_NOMBRE, P_APELLIDO_1, P_APELLIDO_2, P_DOMICILIO, P_TELEFONO, P_EMAIL, P_ID_PROVINCIA);
 --           
            INSERT INTO CLIENTE (ID_CLIENTE, NUM_TARJETA, ID_PERSONA)
            VALUES (SEQ2, P_NUM_TARJETA, SEQ1);
            COMMIT;
        EXCEPTION
            WHEN OTHERS THEN
                RAISE_APPLICATION_ERROR(-2003,'NO SE HA PODIDO REALIZAR LA OPERATIVA');
    END CREAR;
-- update
    PROCEDURE ACTUALIZAR (P_DNI PERSONA.DNI%TYPE,
         P_NOMBRE PERSONA.NOMBRE%TYPE,
         P_APELLIDO_1 PERSONA.APELLIDO_1%TYPE,
         P_APELLIDO_2 PERSONA.APELLIDO_2%TYPE,
         P_DOMICILIO PERSONA.DOMICILIO%TYPE,
         P_TELEFONO PERSONA.TELEFONO%TYPE,
         P_EMAIL PERSONA.EMAIL%TYPE,
         P_ID_PROVINCIA PERSONA.ID_PROVINCIA%TYPE,
         P_NUM_TARJETA CLIENTE.NUM_TARJETA%TYPE,
         P_ID_PERSONA PERSONA.ID_PERSONA%TYPE)
        IS 
          V_NUM NUMBER;
        BEGIN
            SELECT COUNT (*) INTO V_NUM FROM VIEW_CLIENTE
            WHERE ID = P_ID_PERSONA;
            --
            IF V_NUM = 0 THEN
                RAISE_APPLICATION_ERROR(-2002, 'ERROR: EL REGISTRO SELECCIONADO NO EXISTE');
            ELSE
                UPDATE PERSONA SET
                    DNI = P_DNI,
                    NOMBRE = P_NOMBRE,
                    APELLIDO_1 = P_APELLIDO_1,
                    APELLIDO_2 = P_APELLIDO_2,
                    DOMICILIO = P_DOMICILIO,
                    TELEFONO = P_TELEFONO,
                    EMAIL = P_EMAIL,
                    ID_PROVINCIA = P_ID_PROVINCIA
                WHERE ID_PERSONA = P_ID_PERSONA;
        --    
                UPDATE CLIENTE SET
                    NUM_TARJETA = P_NUM_TARJETA
                WHERE ID_PERSONA = P_ID_PERSONA;      
                COMMIT;
            END IF;
    END ACTUALIZAR;   
-- delete 
    PROCEDURE ELIMINAR (P_ID_PERSONA PERSONA.ID_PERSONA%TYPE)
        IS
        V_NUM NUMBER;
        BEGIN 
            SELECT COUNT (*) INTO V_NUM FROM VIEW_CLIENTE
            WHERE ID = P_ID_PERSONA;
    --
        IF V_NUM = 0 THEN
            RAISE_APPLICATION_ERROR(-2002, 'ERROR: EL REGISTRO SELECCIONADO NO EXISTE');
        ELSE
            DELETE FROM COMANDA 
            WHERE ID_CLIENTE = (SELECT ID_CLIENTE FROM CLIENTE WHERE ID_PERSONA = P_ID_PERSONA);
            DELETE FROM CLIENTE 
            WHERE ID_PERSONA = P_ID_PERSONA;
            DELETE FROM PERSONA
            WHERE ID_PERSONA = P_ID_PERSONA;
            COMMIT;
        END IF;
    END ELIMINAR;
END GEST_CLIENTE;
/
create or replace PACKAGE GEST_EMPLEADO IS -- CABECERA >> CRUD EMPLEADO
-- list 
    FUNCTION LISTAR RETURN SYS_REFCURSOR;
    FUNCTION LISTAR_NOMBRE(P_NOMBRE PERSONA.NOMBRE%TYPE) RETURN SYS_REFCURSOR;
-- insert
    PROCEDURE CREAR
         (P_DNI PERSONA.DNI%TYPE,
         P_NOMBRE PERSONA.NOMBRE%TYPE,
         P_APELLIDO_1 PERSONA.APELLIDO_1%TYPE,
         P_APELLIDO_2 PERSONA.APELLIDO_2%TYPE,
         P_DOMICILIO PERSONA.DOMICILIO%TYPE,
         P_TELEFONO PERSONA.TELEFONO%TYPE,
         P_EMAIL PERSONA.EMAIL%TYPE,
         P_ID_PROVINCIA PERSONA.ID_PROVINCIA%TYPE,
         P_IBAN EMPLEADO.IBAN%TYPE,
         P_NSS EMPLEADO.NSS%TYPE,
         P_TIPO EMPLEADO.ID_TIPO%TYPE);
-- update
    PROCEDURE ACTUALIZAR 
        (P_DNI PERSONA.DNI%TYPE,
         P_NOMBRE PERSONA.NOMBRE%TYPE,
         P_APELLIDO_1 PERSONA.APELLIDO_1%TYPE,
         P_APELLIDO_2 PERSONA.APELLIDO_2%TYPE,
         P_DOMICILIO PERSONA.DOMICILIO%TYPE,
         P_TELEFONO PERSONA.TELEFONO%TYPE,
         P_EMAIL PERSONA.EMAIL%TYPE,
         P_ID_PROVINCIA PERSONA.ID_PROVINCIA%TYPE,
         P_ID_PERSONA PERSONA.ID_PERSONA%TYPE,
         P_IBAN EMPLEADO.IBAN%TYPE,
         P_NSS EMPLEADO.NSS%TYPE,
         P_TIPO EMPLEADO.ID_TIPO%TYPE);
-- delete        
      PROCEDURE ELIMINAR 
        (P_ID_PERSONA PERSONA.ID_PERSONA%TYPE);      
END GEST_EMPLEADO;
/
create or replace PACKAGE BODY GEST_EMPLEADO IS -- PACKAGE BODY >> CRUD EMPLEADO
-- list
    FUNCTION LISTAR RETURN SYS_REFCURSOR
        IS
            Q_CURSOR SYS_REFCURSOR;
        BEGIN
            OPEN Q_CURSOR FOR SELECT * FROM VIEW_EMPLEADO;
    RETURN Q_CURSOR;
    END LISTAR;
-- list EXTRA     
     FUNCTION LISTAR_NOMBRE(P_NOMBRE PERSONA.NOMBRE%TYPE) RETURN SYS_REFCURSOR
        IS 
         Q_CURSOR SYS_REFCURSOR;
        BEGIN
            OPEN Q_CURSOR FOR SELECT * FROM VIEW_EMPLEADO WHERE NOMBRE = P_NOMBRE;
            RETURN Q_CURSOR;
        END LISTAR_NOMBRE;
-- insert
    PROCEDURE CREAR
         (
         P_DNI PERSONA.DNI%TYPE,
         P_NOMBRE PERSONA.NOMBRE%TYPE,
         P_APELLIDO_1 PERSONA.APELLIDO_1%TYPE,
         P_APELLIDO_2 PERSONA.APELLIDO_2%TYPE,
         P_DOMICILIO PERSONA.DOMICILIO%TYPE,
         P_TELEFONO PERSONA.TELEFONO%TYPE,
         P_EMAIL PERSONA.EMAIL%TYPE,
         P_ID_PROVINCIA PERSONA.ID_PROVINCIA%TYPE,
         P_IBAN EMPLEADO.IBAN%TYPE,
         P_NSS EMPLEADO.NSS%TYPE,
         P_TIPO EMPLEADO.ID_TIPO%TYPE)
        IS
         SEQ1 NUMBER := SEQ_PERSONA.NEXTVAL;
         SEQ2 NUMBER := SEQ_EMPLEADO.NEXTVAL;
        BEGIN
                INSERT INTO PERSONA (ID_PERSONA, DNI, NOMBRE, APELLIDO_1, APELLIDO_2, DOMICILIO, TELEFONO, EMAIL, ID_PROVINCIA)
                VALUES (SEQ1, P_DNI, P_NOMBRE, P_APELLIDO_1, P_APELLIDO_2, P_DOMICILIO, P_TELEFONO, P_EMAIL, P_ID_PROVINCIA);
     --           
                INSERT INTO EMPLEADO (ID_EMPLEADO, IBAN, NSS, ID_PERSONA, ID_TIPO)
                VALUES (SEQ2, P_IBAN , P_NSS , SEQ1, P_TIPO);
                COMMIT;
                EXCEPTION
                    WHEN OTHERS THEN
                        RAISE_APPLICATION_ERROR(-2003,'NO SE HA PODIDO REALIZAR LA OPERATIVA');
    END CREAR;
-- update
    PROCEDURE ACTUALIZAR (P_DNI PERSONA.DNI%TYPE,
         P_NOMBRE PERSONA.NOMBRE%TYPE,
         P_APELLIDO_1 PERSONA.APELLIDO_1%TYPE,
         P_APELLIDO_2 PERSONA.APELLIDO_2%TYPE,
         P_DOMICILIO PERSONA.DOMICILIO%TYPE,
         P_TELEFONO PERSONA.TELEFONO%TYPE,
         P_EMAIL PERSONA.EMAIL%TYPE,
         P_ID_PROVINCIA PERSONA.ID_PROVINCIA%TYPE,
         P_ID_PERSONA PERSONA.ID_PERSONA%TYPE,
         P_IBAN EMPLEADO.IBAN%TYPE,
         P_NSS EMPLEADO.NSS%TYPE,
         P_TIPO EMPLEADO.ID_TIPO%TYPE)
        IS 
            V_NUM NUMBER;
        BEGIN
            SELECT COUNT (*) INTO V_NUM FROM VIEW_EMPLEADO
            WHERE ID = P_ID_PERSONA;
    --
            IF V_NUM = 0 THEN
                RAISE_APPLICATION_ERROR(-2002, 'ERROR: EL REGISTRO SELECCIONADO NO EXISTE');
            ELSE
                UPDATE PERSONA SET
                    DNI = P_DNI,
                    NOMBRE = P_NOMBRE,
                    APELLIDO_1 = P_APELLIDO_1,
                    APELLIDO_2 = P_APELLIDO_2,
                    DOMICILIO = P_DOMICILIO,
                    TELEFONO = P_TELEFONO,
                    EMAIL = P_EMAIL,
                    ID_PROVINCIA = P_ID_PROVINCIA
                WHERE ID_PERSONA = P_ID_PERSONA;
--        
                UPDATE EMPLEADO SET
                    IBAN = P_IBAN,
                    NSS = P_NSS,
                    ID_TIPO = P_TIPO
                WHERE ID_PERSONA = P_ID_PERSONA; 
                COMMIT;
            END IF;
    END ACTUALIZAR;   
-- delete 
    PROCEDURE ELIMINAR (P_ID_PERSONA PERSONA.ID_PERSONA%TYPE)
        IS
            V_NUM NUMBER;
        BEGIN 
            SELECT COUNT (*) INTO V_NUM FROM VIEW_EMPLEADO
            WHERE ID = P_ID_PERSONA;
    --
            IF V_NUM = 0 THEN
                RAISE_APPLICATION_ERROR(-2002, 'ERROR: EL REGISTRO SELECCIONADO NO EXISTE');
            ELSE
                DELETE FROM EMPLEADO
                WHERE ID_PERSONA = P_ID_PERSONA;
        --        
                DELETE FROM PERSONA 
                WHERE ID_PERSONA = P_ID_PERSONA;
                COMMIT;
            END IF;
    END ELIMINAR;
END GEST_EMPLEADO;
/
CREATE OR REPLACE PACKAGE GEST_PROVINCIA IS -- CABECERA >> (LISTAR) PROVINCIA
    FUNCTION LISTAR RETURN SYS_REFCURSOR;
END GEST_PROVINCIA;
/
create or replace PACKAGE BODY GEST_PROVINCIA IS -- PACKAGE BODY >> (LISTAR) PROVINCIA
-- list
    FUNCTION LISTAR RETURN SYS_REFCURSOR
        IS
            Q_CURSOR SYS_REFCURSOR;
        BEGIN
                OPEN Q_CURSOR FOR
                SELECT *
                FROM VIEW_PROVINCIA;  
        RETURN Q_CURSOR;
    END LISTAR;
END GEST_PROVINCIA;
/
CREATE OR REPLACE PACKAGE GEST_TIPO_PRODUCTO -- CABECERA >> CRUD TIPO_PRODUCTO
IS
-- list
    FUNCTION LISTAR RETURN SYS_REFCURSOR;
-- insert
    PROCEDURE CREAR (
    P_NOMBRE TIPO_PRODUCTO.NOMBRE%TYPE);
-- update
    PROCEDURE ACTUALIZAR (
    P_ID_TIPO TIPO_PRODUCTO.ID_TIPO%TYPE,
    P_NOMBRE TIPO_PRODUCTO.NOMBRE%TYPE);
-- delete
    PROCEDURE BORRAR (
    P_ID_TIPO TIPO_PRODUCTO.ID_TIPO%TYPE);
    END GEST_TIPO_PRODUCTO;
/
CREATE OR REPLACE PACKAGE BODY GEST_TIPO_PRODUCTO -- PACKAGE BODY >> CRUD TIPO_PRODUCTO
IS
-- list
    FUNCTION LISTAR RETURN SYS_REFCURSOR
        IS
            Q_CURSOR SYS_REFCURSOR;
        BEGIN
                OPEN Q_CURSOR FOR
                SELECT *
                FROM VIEW_TIPO_PRODUCTO;  
         RETURN Q_CURSOR;
    END LISTAR;
-- insert
    PROCEDURE CREAR (
    P_NOMBRE TIPO_PRODUCTO.NOMBRE%TYPE)
        IS
        BEGIN
            INSERT INTO TIPO_PRODUCTO(ID_TIPO, NOMBRE) VALUES (SEQ_TIPO_PRODUCTO.NEXTVAL, P_NOMBRE);
            COMMIT;
            EXCEPTION
                WHEN OTHERS THEN
                    RAISE_APPLICATION_ERROR(-2003,'NO SE HA PODIDO REALIZAR LA OPERATIVA');
    END CREAR;   
-- update
    PROCEDURE ACTUALIZAR (
    P_ID_TIPO TIPO_PRODUCTO.ID_TIPO%TYPE,
    P_NOMBRE TIPO_PRODUCTO.NOMBRE%TYPE) 
        IS
        V_NUM NUMBER;
        BEGIN
            SELECT COUNT (*) INTO V_NUM FROM VIEW_TIPO_PRODUCTO
            WHERE ID_TIPO = P_ID_TIPO;
    --
            IF V_NUM = 0 THEN
                RAISE_APPLICATION_ERROR(-2002, 'ERROR: EL REGISTRO SELECCIONADO NO EXISTE');
            ELSE
                UPDATE TIPO_PRODUCTO set
                NOMBRE = P_NOMBRE
                WHERE ID_TIPO = P_ID_TIPO;
                COMMIT;
            END IF;
    END ACTUALIZAR;
-- del
    PROCEDURE BORRAR (
    P_ID_TIPO TIPO_PRODUCTO.ID_TIPO%TYPE) 
        IS
            V_NUM NUMBER;
        BEGIN
            SELECT COUNT (*) INTO V_NUM FROM VIEW_TIPO_PRODUCTO
            WHERE ID_TIPO = P_ID_TIPO;
    --
            IF V_NUM = 0 THEN
                RAISE_APPLICATION_ERROR(-2002, 'ERROR: EL REGISTRO SELECCIONADO NO EXISTE');
            ELSE
                DELETE FROM PRODUCTO
                WHERE ID_TIPO = P_ID_TIPO;
                DELETE FROM TIPO_PRODUCTO
                WHERE ID_TIPO = P_ID_TIPO;
                COMMIT;
            END IF;
        END BORRAR;
    END GEST_TIPO_PRODUCTO;
/
CREATE OR REPLACE PACKAGE GEST_TIPO_EMPLEADO -- CABECERA >> CRUD TIPO_EMPLEADO
IS
-- list
    FUNCTION LISTAR RETURN SYS_REFCURSOR;
-- insert
    PROCEDURE CREAR (
    P_NOMBRE TIPO_EMPLEADO.NOMBRE%TYPE,
    P_SAL TIPO_EMPLEADO.SAL%TYPE);
-- update
    PROCEDURE ACTUALIZAR (
    P_ID_TIPO TIPO_EMPLEADO.ID_TIPO%TYPE,
    P_NOMBRE TIPO_EMPLEADO.NOMBRE%TYPE,
    P_SAL TIPO_EMPLEADO.SAL%TYPE);
-- delete
    PROCEDURE ELIMINAR (
    P_ID_TIPO TIPO_EMPLEADO.ID_TIPO%TYPE);
    END GEST_TIPO_EMPLEADO;
/
create or replace package body GEST_TIPO_EMPLEADO -- PACKAGE BODY >> CRUD TIPO_EMPLEADO
IS
-- list
    FUNCTION LISTAR RETURN SYS_REFCURSOR
        IS
            Q_CURSOR SYS_REFCURSOR;
        BEGIN
                OPEN Q_CURSOR FOR
                SELECT *
                FROM VIEW_TIPO_EMPLEADO;  
         RETURN Q_CURSOR;
     END LISTAR;
-- insert
    PROCEDURE CREAR (
    P_NOMBRE TIPO_EMPLEADO.NOMBRE%TYPE,
    P_SAL TIPO_EMPLEADO.SAL%TYPE) 
        IS
        BEGIN
            INSERT INTO TIPO_EMPLEADO(ID_TIPO, NOMBRE, SAL) 
            VALUES (SEQ_TIPO_EMPLEADO.NEXTVAL, P_NOMBRE, P_SAL);
            COMMIT;
            EXCEPTION
                WHEN OTHERS THEN
                    RAISE_APPLICATION_ERROR(-2003,'NO SE HA PODIDO REALIZAR LA OPERATIVA');
    END CREAR;
-- update
    PROCEDURE ACTUALIZAR (
    P_ID_TIPO TIPO_EMPLEADO.ID_TIPO%TYPE,
    P_NOMBRE TIPO_EMPLEADO.NOMBRE%TYPE,
    P_SAL TIPO_EMPLEADO.SAL%TYPE) 
        IS
            V_NUM NUMBER;
        BEGIN
            SELECT COUNT (*) INTO V_NUM FROM VIEW_TIPO_EMPLEADO
        WHERE ID_TIPO = P_ID_TIPO;
--
        IF V_NUM = 0 THEN
            RAISE_APPLICATION_ERROR(-2002, 'ERROR: EL REGISTRO SELECCIONADO NO EXISTE');
        ELSE
            UPDATE TIPO_EMPLEADO SET
            NOMBRE = p_NOMBRE,
            SAL = P_SAL
            WHERE ID_TIPO = P_ID_TIPO;
            COMMIT;
        END IF;
    END ACTUALIZAR;
-- delete
    PROCEDURE ELIMINAR (
    P_ID_TIPO TIPO_EMPLEADO.ID_TIPO%TYPE)
        IS
            V_NUM NUMBER;
        BEGIN
            SELECT COUNT (*) INTO V_NUM FROM VIEW_TIPO_EMPLEADO
            WHERE ID_TIPO = P_ID_TIPO;
--
        IF V_NUM = 0 THEN
            RAISE_APPLICATION_ERROR(-2002, 'ERROR: EL REGISTRO SELECCIONADO NO EXISTE');
        ELSE
            DELETE FROM EMPLEADO
            WHERE ID_TIPO = P_ID_TIPO;
            DELETE FROM TIPO_EMPLEADO
            WHERE ID_TIPO = P_ID_TIPO;
            COMMIT;
        END IF;
    END ELIMINAR;
END GEST_TIPO_EMPLEADO;
/
create or replace package GEST_RESTAURANTE -- CABECERA >> CRUD RESTAURANTE
IS
-- list
    FUNCTION LISTAR RETURN SYS_REFCURSOR;
    FUNCTION LISTAR_NOMBRE(P_NOMBRE RESTAURANTE.NOMBRE%TYPE) RETURN SYS_REFCURSOR;
-- insert
    PROCEDURE CREAR (
    P_DIRECCION RESTAURANTE.DIRECCION%TYPE,
    P_NIF RESTAURANTE.NIF%TYPE,
    P_TELEFONO RESTAURANTE.TELEFONO%TYPE,
    P_NOMBRE RESTAURANTE.NOMBRE%TYPE);
-- update
    PROCEDURE ACTUALIZAR (
    P_DIRECCION RESTAURANTE.DIRECCION%TYPE,
    P_NIF RESTAURANTE.NIF%TYPE,
    P_TELEFONO RESTAURANTE.TELEFONO%TYPE,
    P_NOMBRE RESTAURANTE.NOMBRE%TYPE,
    P_ID_RESTAURANTE RESTAURANTE.ID_RESTAURANTE%TYPE);
-- delete
    PROCEDURE ELIMINAR (
    P_ID_RESTAURANTE RESTAURANTE.ID_RESTAURANTE%TYPE);

END GEST_RESTAURANTE;
/
create or replace package body GEST_RESTAURANTE -- PACKAGE BODY >> CRUD RESTAURANTE
IS
-- list
    FUNCTION LISTAR RETURN SYS_REFCURSOR
        IS
            Q_CURSOR SYS_REFCURSOR;
        BEGIN
                OPEN Q_CURSOR FOR
                SELECT *
                FROM VIEW_RESTAURANTE;  
        RETURN Q_CURSOR;
    END LISTAR;
-- list EXTRA     
     FUNCTION LISTAR_NOMBRE(P_NOMBRE RESTAURANTE.NOMBRE%TYPE) RETURN SYS_REFCURSOR
        IS 
         Q_CURSOR SYS_REFCURSOR;
        BEGIN
            OPEN Q_CURSOR FOR SELECT * FROM VIEW_RESTAURANTE WHERE NOMBRE = P_NOMBRE;
            RETURN Q_CURSOR;
        END LISTAR_NOMBRE;
-- insert
    PROCEDURE CREAR (P_DIRECCION RESTAURANTE.DIRECCION%TYPE,
    P_NIF RESTAURANTE.NIF%TYPE,
    P_TELEFONO RESTAURANTE.TELEFONO%TYPE,
    P_NOMBRE RESTAURANTE.NOMBRE%TYPE) 
        IS
        BEGIN
            INSERT INTO RESTAURANTE (ID_RESTAURANTE, DIRECCION, NIF, TELEFONO, NOMBRE)
            VALUES (SEQ_RESTAURANTE.NEXTVAL, P_DIRECCION, P_NIF, P_TELEFONO, P_NOMBRE);
            COMMIT;
            EXCEPTION
                WHEN OTHERS THEN
                    RAISE_APPLICATION_ERROR(-2003,'NO SE HA PODIDO REALIZAR LA OPERATIVA');
    END CREAR;
-- update
    PROCEDURE ACTUALIZAR (
    P_DIRECCION RESTAURANTE.DIRECCION%TYPE,
    P_NIF RESTAURANTE.NIF%TYPE,
    P_TELEFONO RESTAURANTE.TELEFONO%TYPE,
    P_NOMBRE RESTAURANTE.NOMBRE%TYPE,
    P_ID_RESTAURANTE RESTAURANTE.ID_RESTAURANTE%TYPE) 
        IS
            V_NUM NUMBER;
        BEGIN
            SELECT COUNT (*) INTO V_NUM FROM VIEW_RESTAURANTE
            WHERE ID_RESTAURANTE = P_ID_RESTAURANTE;
        --
            IF V_NUM = 0 THEN
                RAISE_APPLICATION_ERROR(-2002, 'ERROR: EL REGISTRO SELECCIONADO NO EXISTE');
            ELSE
                UPDATE RESTAURANTE SET
                DIRECCION = P_DIRECCION,
                NIF = P_NIF,
                TELEFONO = P_TELEFONO,
                NOMBRE = P_NOMBRE
                WHERE ID_RESTAURANTE = P_ID_RESTAURANTE;
                COMMIT;
            END IF;
    END ACTUALIZAR;
-- del
    PROCEDURE ELIMINAR (
    P_ID_RESTAURANTE RESTAURANTE.ID_RESTAURANTE%TYPE) 
        IS
            V_NUM NUMBER;
        BEGIN
            SELECT COUNT (*) INTO V_NUM FROM VIEW_RESTAURANTE
            WHERE ID_RESTAURANTE = P_ID_RESTAURANTE;
        --
            IF V_NUM = 0 THEN
                RAISE_APPLICATION_ERROR(-2002, 'ERROR: EL REGISTRO SELECCIONADO NO EXISTE');
            ELSE
                DELETE FROM CARTA
                WHERE ID_RESTAURANTE = P_ID_RESTAURANTE;
                DELETE FROM RESTAURANTE
                WHERE ID_RESTAURANTE = P_ID_RESTAURANTE;
                COMMIT;
            END IF;
    END ELIMINAR;
END GEST_RESTAURANTE;
/
create or replace package GEST_PRODUCTO -- CABECERA >> CRUD PRODUCTO
IS
-- list
    FUNCTION LISTAR RETURN SYS_REFCURSOR;
-- insert
    PROCEDURE CREAR (
    P_NOMBRE PRODUCTO.NOMBRE%TYPE,
    P_ID_TIPO PRODUCTO.ID_TIPO%TYPE);
-- update
    PROCEDURE ACTUALIZAR (
    P_ID_TIPO PRODUCTO.ID_TIPO%TYPE,
    P_ID_PRODUCTO PRODUCTO.ID_PRODUCTO%TYPE,
    P_NOMBRE PRODUCTO.NOMBRE%TYPE);
-- delete
    PROCEDURE ELIMINAR (
    P_ID_PRODUCTO  PRODUCTO.ID_PRODUCTO%TYPE);
END GEST_PRODUCTO;
/
create or replace package body GEST_PRODUCTO -- PACKAGE BODY >> CRUD PRODUCTO
IS
-- list
    FUNCTION LISTAR RETURN SYS_REFCURSOR
        IS
            Q_CURSOR SYS_REFCURSOR;
        BEGIN
                OPEN Q_CURSOR FOR
                SELECT *
                FROM VIEW_PRODUCTO;  
        RETURN Q_CURSOR;
    END LISTAR;
-- insert
    PROCEDURE CREAR (
    P_NOMBRE PRODUCTO.NOMBRE%TYPE,
    P_ID_TIPO PRODUCTO.ID_TIPO%TYPE)
        IS
        BEGIN
            INSERT INTO PRODUCTO(ID_PRODUCTO, NOMBRE, ID_TIPO)
            VALUES (SEQ_PRODUCTO.NEXTVAL, P_NOMBRE, P_ID_TIPO);
            COMMIT;
            EXCEPTION
                WHEN OTHERS THEN
                    RAISE_APPLICATION_ERROR(-2003,'NO SE HA PODIDO REALIZAR LA OPERATIVA');
    END CREAR;
-- update
    PROCEDURE ACTUALIZAR (
    P_ID_TIPO PRODUCTO.ID_TIPO%TYPE,
    P_ID_PRODUCTO PRODUCTO.ID_PRODUCTO%TYPE,
    P_NOMBRE PRODUCTO.NOMBRE%TYPE) 
        IS
            V_NUM NUMBER;
        BEGIN
            SELECT COUNT (*) INTO V_NUM FROM VIEW_PRODUCTO
            WHERE ID = P_ID_PRODUCTO;
        --
            IF V_NUM = 0 THEN
                RAISE_APPLICATION_ERROR(-2002, 'ERROR: EL REGISTRO SELECCIONADO NO EXISTE');
            ELSE
                UPDATE PRODUCTO SET
                NOMBRE = P_NOMBRE,
                ID_TIPO = P_ID_TIPO
                WHERE ID_PRODUCTO = P_ID_PRODUCTO;
                COMMIT;
            END IF;
    END ACTUALIZAR;
-- del
    PROCEDURE ELIMINAR (
    P_ID_PRODUCTO  PRODUCTO.ID_PRODUCTO%TYPE)
        IS
            V_NUM NUMBER;
        BEGIN
            SELECT COUNT (*) INTO V_NUM FROM VIEW_PRODUCTO
            WHERE ID = P_ID_PRODUCTO;
        --
            IF V_NUM = 0 THEN
                RAISE_APPLICATION_ERROR(-2002, 'ERROR: EL REGISTRO SELECCIONADO NO EXISTE');
            ELSE
                DELETE FROM COMANDA
                WHERE ID_CARTA = (SELECT ID_CARTA FROM CARTA WHERE ID_PRODUCTO = P_ID_PRODUCTO);
                DELETE FROM CARTA
                WHERE ID_PRODUCTO = P_ID_PRODUCTO;
                DELETE FROM PRODUCTO
                WHERE ID_PRODUCTO = P_ID_PRODUCTO;
                COMMIT;
            END IF;
    END ELIMINAR;
END GEST_PRODUCTO;
/
create or replace package GEST_CARTA -- CABECERA >> CRUD CARTA
IS
-- list
    FUNCTION LISTAR RETURN SYS_REFCURSOR;
    FUNCTION LISTAR_DISPONIBLE(P_DISPONIBLE CARTA.DISPONIBLE%TYPE) RETURN SYS_REFCURSOR;
-- insert
    PROCEDURE CREAR (
    P_DISPONIBLE CARTA.DISPONIBLE%TYPE,
    P_PVP CARTA.PVP%TYPE,
    P_ID_PRODUCTO CARTA.ID_PRODUCTO%TYPE,
    P_ID_RESTAURANTE CARTA.ID_RESTAURANTE%TYPE);
-- update
    PROCEDURE ACTUALIZAR (
    P_ID_CARTA CARTA.ID_CARTA%TYPE,
    P_DISPONIBLE CARTA.DISPONIBLE%TYPE,
    P_PVP CARTA.PVP%TYPE,
    P_ID_PRODUCTO CARTA.ID_PRODUCTO%TYPE,
    P_ID_RESTAURANTE CARTA.ID_RESTAURANTE%TYPE);
-- delete
    PROCEDURE ELIMINAR (
    P_ID_CARTA CARTA.ID_CARTA%TYPE);
END GEST_CARTA;
/
create or replace package body GEST_CARTA -- PACKAGE BODY >> CRUD CARTA
IS
-- list
    FUNCTION LISTAR RETURN SYS_REFCURSOR
        IS
            Q_CURSOR SYS_REFCURSOR;
        BEGIN
                OPEN Q_CURSOR FOR
                SELECT *
                FROM VIEW_CARTA; 
        RETURN Q_CURSOR;
    END LISTAR;
-- list EXTRA     
     FUNCTION LISTAR_DISPONIBLE(P_DISPONIBLE CARTA.DISPONIBLE%TYPE) RETURN SYS_REFCURSOR
        IS 
         Q_CURSOR SYS_REFCURSOR;
        BEGIN
            OPEN Q_CURSOR FOR SELECT * FROM VIEW_CARTA WHERE DISPONIBILIDAD = P_DISPONIBLE;
            RETURN Q_CURSOR;
        END LISTAR_DISPONIBLE;
-- insert
    PROCEDURE CREAR (
    P_DISPONIBLE CARTA.DISPONIBLE%TYPE,
    P_PVP CARTA.PVP%TYPE,
    P_ID_PRODUCTO CARTA.ID_PRODUCTO%TYPE,
    P_ID_RESTAURANTE CARTA.ID_RESTAURANTE%TYPE) 
        IS
        BEGIN
            INSERT INTO CARTA(ID_CARTA, DISPONIBLE, PVP,ID_PRODUCTO, ID_RESTAURANTE)
            VALUES (SEQ_CARTA.NEXTVAL, P_DISPONIBLE, P_PVP, P_ID_PRODUCTO, P_ID_RESTAURANTE);
            COMMIT;
            EXCEPTION
                WHEN OTHERS THEN
                    RAISE_APPLICATION_ERROR(-2003,'NO SE HA PODIDO REALIZAR LA OPERATIVA');
    END CREAR;
-- update
    PROCEDURE ACTUALIZAR (
    P_ID_CARTA CARTA.ID_CARTA%TYPE,
    P_DISPONIBLE CARTA.DISPONIBLE%TYPE,
    P_PVP CARTA.PVP%TYPE,
    P_ID_PRODUCTO CARTA.ID_PRODUCTO%TYPE,
    P_ID_RESTAURANTE CARTA.ID_RESTAURANTE%TYPE) 
        IS
            V_NUM NUMBER;
        BEGIN
            SELECT COUNT (*) INTO V_NUM FROM VIEW_CARTA
            WHERE ID = P_ID_CARTA;
        --
            IF V_NUM = 0 THEN
                RAISE_APPLICATION_ERROR(-2002, 'ERROR: EL REGISTRO SELECCIONADO NO EXISTE');
            ELSE
                UPDATE CARTA SET
                DISPONIBLE = P_DISPONIBLE,
                PVP = P_PVP,
                ID_PRODUCTO = P_ID_PRODUCTO,
                ID_RESTAURANTE = P_ID_RESTAURANTE
                WHERE ID_CARTA = P_ID_CARTA;
                COMMIT;
            END IF;
    END ACTUALIZAR;
-- delete
    PROCEDURE ELIMINAR (
    P_ID_CARTA CARTA.ID_CARTA%TYPE) 
        IS
            V_NUM NUMBER;
        BEGIN
            SELECT COUNT (*) INTO V_NUM FROM VIEW_CARTA
            WHERE ID = P_ID_CARTA;
    --
            IF V_NUM = 0 THEN
                RAISE_APPLICATION_ERROR(-2002, 'ERROR: EL REGISTRO SELECCIONADO NO EXISTE');
            ELSE
                DELETE FROM CARTA
                WHERE ID_CARTA = P_ID_CARTA;
                COMMIT;
            END IF;
    END ELIMINAR;
END GEST_CARTA;
/
create or replace package GEST_COMANDA -- CABECERA >> CRUD COMANDA
IS
-- list
    FUNCTION LISTAR RETURN SYS_REFCURSOR;
-- insert
    PROCEDURE CREAR (
    P_ID_CLIENTE COMANDA.ID_CLIENTE%TYPE,
    P_ID_CARTA COMANDA.ID_CARTA%TYPE,
    P_ID_EMPLEADO COMANDA.ID_EMPLEADO%TYPE);
-- update
    PROCEDURE ACTUALIZAR (
    P_ID_COMANDA COMANDA.ID_COMANDA%TYPE,
    P_ID_CLIENTE COMANDA.ID_CLIENTE%TYPE,
    P_ID_CARTA COMANDA.ID_CARTA%TYPE,
    P_ID_EMPLEADO COMANDA.ID_EMPLEADO%TYPE);
-- delete
    PROCEDURE ELIMINAR (
    P_ID_COMANDA COMANDA.ID_COMANDA%TYPE);
END GEST_COMANDA;
/
create or replace package body GEST_COMANDA -- PACKAGE BODY >> CRUD COMANDA
IS
-- list
    FUNCTION LISTAR RETURN SYS_REFCURSOR
        IS
            Q_CURSOR SYS_REFCURSOR;
        BEGIN
                OPEN Q_CURSOR FOR
                SELECT *
                FROM VIEW_COMANDA;  
        RETURN Q_CURSOR;
    END LISTAR;
-- insert
    PROCEDURE CREAR (
    P_ID_CLIENTE COMANDA.ID_CLIENTE%TYPE,
    P_ID_CARTA COMANDA.ID_CARTA%TYPE,
    P_ID_EMPLEADO COMANDA.ID_EMPLEADO%TYPE)
        IS
        BEGIN
            INSERT INTO COMANDA(ID_COMANDA, PRECIO, ID_CLIENTE, ID_CARTA, FECHA, ID_EMPLEADO)
            VALUES (SEQ_COMANDA.NEXTVAL, (SELECT PVP FROM CARTA WHERE ID_CARTA = P_ID_CARTA), P_ID_CLIENTE, P_ID_CARTA, SYSDATE, P_ID_EMPLEADO);
            COMMIT;
            EXCEPTION
                WHEN OTHERS THEN
                    RAISE_APPLICATION_ERROR(-2003,'NO SE HA PODIDO REALIZAR LA OPERATIVA');
    END CREAR;
-- update
    PROCEDURE ACTUALIZAR (
    P_ID_COMANDA COMANDA.ID_COMANDA%TYPE,
    P_ID_CLIENTE COMANDA.ID_CLIENTE%TYPE,
    P_ID_CARTA COMANDA.ID_CARTA%TYPE,
    P_ID_EMPLEADO COMANDA.ID_EMPLEADO%TYPE)
        IS
            V_NUM NUMBER;
        BEGIN
            SELECT COUNT (*) INTO V_NUM FROM VIEW_COMANDA
            WHERE ID = P_ID_COMANDA;
    --
            IF V_NUM = 0 THEN
                RAISE_APPLICATION_ERROR(-2002, 'ERROR: EL REGISTRO SELECCIONADO NO EXISTE');
            ELSE
                UPDATE COMANDA SET
                ID_CLIENTE = P_ID_CLIENTE,
                ID_CARTA = P_ID_CARTA,
                ID_EMPLEADO = P_ID_EMPLEADO
                WHERE ID_COMANDA = P_ID_COMANDA;
                COMMIT;
            END IF;
    END ACTUALIZAR;
-- delete
    PROCEDURE ELIMINAR (
    P_ID_COMANDA COMANDA.ID_COMANDA%TYPE)
        IS
            V_NUM NUMBER;
        BEGIN
            SELECT COUNT (*) INTO V_NUM FROM VIEW_COMANDA
            WHERE ID = P_ID_COMANDA;
    --
            IF V_NUM = 0 THEN
                RAISE_APPLICATION_ERROR(-2002, 'ERROR: EL REGISTRO SELECCIONADO NO EXISTE');
            ELSE
                DELETE FROM COMANDA
                WHERE ID_COMANDA = P_ID_COMANDA;
                COMMIT;
            END IF;
    END ELIMINAR;
END GEST_COMANDA;
