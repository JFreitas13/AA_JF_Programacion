DROP TRIGGER TRIGGER_ID_CLIENTES;
DROP TRIGGER TRIGGER_ID_PRODUCTOS;
DROP TRIGGER TRIGGER_ID_PEDIDOS;
DROP TRIGGER TRIGGER_ID_PROVEEDORES;

DROP SEQUENCE ID_CLIENTES;
DROP SEQUENCE ID_PEDIDOS;
DROP SEQUENCE ID_PRODUCTOS;

--CREAR SECUENCIA ID CLIENTE
CREATE SEQUENCE ID_CLIENTES
   START WITH 1 -- Por donde empieza a contar la secuencia
   INCREMENT BY 1 -- Incremento
   MINVALUE 1 -- Valor mínimo
   NOCACHE
   NOCYCLE
   NOORDER;
   
CREATE OR REPLACE TRIGGER TRIGGER_ID_CLIENTES -- Nombre del trigger
   BEFORE INSERT -- Cuando se lanzara el trigger. Aquí se a puesto antes de insertar
   ON CLIENTES -- Para asociar a la tabla QUE DESEAMOS
   REFERENCING NEW AS New OLD AS Old
   FOR EACH ROW
DECLARE
   tmpVar   NUMBER; -- Variable temporal para recoger el valor de la secuencia
BEGIN
   tmpVar := 0; --Inicializamos la variable a 0
  
   -- Obtenemos el valor de la secuencia
   SELECT ID_CLIENTES.NEXTVAL INTO tmpVar FROM DUAL;

   :NEW.ID_CLIENTE := tmpVar;

EXCEPTION
   WHEN OTHERS
   THEN
      -- Consider logging the error and then re-raise
      RAISE;
END;

--PEDIDOS
CREATE SEQUENCE ID_PEDIDOS
   START WITH 1 -- Por donde empieza a contar la secuencia
   INCREMENT BY 1 -- Incremento
   MINVALUE 1 -- Valor mínimo
   NOCACHE
   NOCYCLE
   NOORDER;
   
CREATE OR REPLACE TRIGGER TRIGGER_ID_PEDIDOS -- Nombre del trigger
   BEFORE INSERT -- Cuando se lanzara el trigger. Aquí se a puesto antes de insertar
   ON PEDIDOS -- Para asociar a la tabla QUE DESEAMOS
   REFERENCING NEW AS New OLD AS Old
   FOR EACH ROW
DECLARE
   tmpVar   NUMBER; -- Variable temporal para recoger el valor de la secuencia
BEGIN
   tmpVar := 0; --Inicializamos la variable a 0
  
   -- Obtenemos el valor de la secuencia
   SELECT ID_PEDIDOS.NEXTVAL INTO tmpVar FROM DUAL;

   :NEW.ID_PEDIDO := tmpVar;

EXCEPTION
   WHEN OTHERS
   THEN
      -- Consider logging the error and then re-raise
      RAISE;
END;

--PRODUCTOS
CREATE SEQUENCE ID_PRODUCTOS
   START WITH 1 -- Por donde empieza a contar la secuencia
   INCREMENT BY 1 -- Incremento
   MINVALUE 1 -- Valor mínimo
   NOCACHE
   NOCYCLE
   NOORDER;
   
CREATE OR REPLACE TRIGGER TRIGGER_ID_PRODUCTOS -- Nombre del trigger
   BEFORE INSERT -- Cuando se lanzara el trigger. Aquí se a puesto antes de insertar
   ON PRODUCTOS -- Para asociar a la tabla QUE DESEAMOS
   REFERENCING NEW AS New OLD AS Old
   FOR EACH ROW
DECLARE
   tmpVar   NUMBER; -- Variable temporal para recoger el valor de la secuencia
BEGIN
   tmpVar := 0; --Inicializamos la variable a 0
  
   -- Obtenemos el valor de la secuencia
   SELECT ID_PRODUCTOS.NEXTVAL INTO tmpVar FROM DUAL;

   :NEW.ID_PRODUCTO := tmpVar;

EXCEPTION
   WHEN OTHERS
   THEN
      -- Consider logging the error and then re-raise
      RAISE;
END;

--PROVEEDORES
CREATE SEQUENCE ID_PROVEEDORES
   START WITH 1 -- Por donde empieza a contar la secuencia
   INCREMENT BY 1 -- Incremento
   MINVALUE 1 -- Valor mínimo
   NOCACHE
   NOCYCLE
   NOORDER;
   
CREATE OR REPLACE TRIGGER TRIGGER_ID_PROVEEDORES -- Nombre del trigger
   BEFORE INSERT -- Cuando se lanzara el trigger. Aquí se a puesto antes de insertar
   ON PROVEEDORES -- Para asociar a la tabla QUE DESEAMOS
   REFERENCING NEW AS New OLD AS Old
   FOR EACH ROW
DECLARE
   tmpVar   NUMBER; -- Variable temporal para recoger el valor de la secuencia
BEGIN
   tmpVar := 0; --Inicializamos la variable a 0
  
   -- Obtenemos el valor de la secuencia
   SELECT ID_PROVEEDORES.NEXTVAL INTO tmpVar FROM DUAL;

   :NEW.ID_PROVEEDOR := tmpVar;

EXCEPTION
   WHEN OTHERS
   THEN
      -- Consider logging the error and then re-raise
      RAISE;
END;

