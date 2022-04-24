create table usuarios (
    id int(11) not null auto_increment,
    nombre varchar(100) not null,
    clave varchar(45) not null,
    fecha_creacion datetime null,
    primary key (id)
);

CREATE TABLE CLIENTES (
    id int primary key auto_increment,
    nit varchar(20) unique not null,
    razon_social varchar(200) not null,
    nombre_representante varchar(200) not null,
    telefono varchar(20) not null,
    direccion varchar(50) not null,
    fecha_creacion datetime not null default CURRENT_TIMESTAMP,
    fecha_modificacion datetime null
);

CREATE TABLE CONTRATOS (
    id int primary key auto_increment,
    nit_cliente varchar(20) unique not null, foreign key (nit_cliente) references CLIENTES (nit),
    duracion_contrato_meses int not null,
    tipo_moneda varchar(10) not null,
    trm_aplicada decimal(17,2) not null,
    paquete varchar(100) not null,
    fecha_instalacion date not null,
    fecha_corte date not null,
    fecha_corte_anterior date null,
    fecha_creacion datetime not null default CURRENT_TIMESTAMP,
    fecha_modificacion datetime null
);