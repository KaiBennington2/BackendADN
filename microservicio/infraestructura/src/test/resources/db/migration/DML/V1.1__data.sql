--USUARIOS
insert into usuarios(id, nombre,clave,fecha_creacion) values(1,'test','1234',now());

--CLIENTES
INSERT INTO clientes(id, nit, razon_social, nombre_representante, telefono, direccion)
VALUES (1,'1234567','Pruebas S.A.S.','Kai Bennington','3013101550','Cll 000 # 01 - 02');

INSERT INTO clientes(id, nit, razon_social, nombre_representante, telefono, direccion)
VALUES (2,'0987654','Tests S.A.S.','Alonso Bennington','3023111660','Cll 111 # 03 - 04');

INSERT INTO clientes(id, nit, razon_social, nombre_representante, telefono, direccion)
VALUES (3,'1029384','Empresa S.A.S.','Cliente Prueba','3023013065','Cll 222 # 13 - 24');

INSERT INTO clientes(id, nit, razon_social, nombre_representante, telefono, direccion)
VALUES (4,'1234','cliente base S.A.S.','Cliente Base','1234567890','Cll 000 # 00 - 00');

--CONTRATOS
INSERT INTO contratos(id, nit_cliente, duracion_contrato_meses, tipo_moneda, trm_aplicada, paquete, fecha_instalacion, fecha_corte)
VALUES (1,'1234567',24,'USD','3702.86','PREMIUM',now(),'2022-05-12');

INSERT INTO contratos(id, nit_cliente, duracion_contrato_meses, tipo_moneda, trm_aplicada, paquete, fecha_instalacion, fecha_corte)
VALUES (2,'0987654',12,'COP','4020.52','COMPACT',now(),'2022-05-05');

INSERT INTO contratos(id, nit_cliente, duracion_contrato_meses, tipo_moneda, trm_aplicada, paquete, fecha_instalacion, fecha_corte)
VALUES (3,'1029384',12,'USD','3065.43','BASIC',now(),'2022-06-09');