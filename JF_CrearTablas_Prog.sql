select * from clientes;
select * from PRODUCTOS;
select * from proveedores;
select * from usuarios;

DELETE FROM USUARIOS WHERE ID_USUARIO = 3;
DELETE FROM PRODUCTOS WHERE ID_PRODUCTO = 18;

INSERT INTO USUARIOS (NOMBRE, USERNAME, PASS, ROLE) VALUES ('Admin', 'Sfaci', '1234', 'admin');
INSERT INTO USUARIOS (NOMBRE, USERNAME, PASS, ROLE) VALUES ('Usuario', 'Usuario', '1234', 'usuario');

COMMIT;

ALTER TABLE PRODUCTOS DROP CONSTRAINT FK_ID_PROVEEDOR;
ALTER TABLE PRODUCTOS ADD CONSTRAINT FK_ID_PROVEEDOR FOREIGN KEY(ID_PROVEEDOR) REFERENCES PROVEEDORES(ID_PROVEEDOR) ON DELETE CASCADE;
