Script base de datos

/*Version de BBDD postgreslql 9,6*/

/*Creacion de base de datos y esquemas*/
CREATE DATABASE tienda WITH OWNER = postgres ENCODING = 'UTF8';

-----------------------------------------------------------------------------------

GRANT ALL ON DATABASE tienda TO postgres;

/*En la base de datos tienda ejecutar*/
/*Creacion de esquema seguridades*/
CREATE SCHEMA  seguridades;

create table seguridades.usuario (
   id		            serial                                not null,
   cedula               character varying(15)                 not null,
   nombre               character varying(20)                 not null,
   apellido             character varying(20)                 not null,
   usuario              character varying(20)                 not null,
   password             character varying(40)                 not null,
   constraint pk_agrupacion primary key (id));
ALTER TABLE seguridades.usuario OWNER to postgres;


/*Creacion de esquema inventario kardex*/
CREATE SCHEMA   kardex;

create table kardex.lista_productos (
   id		            serial                                not null,
   codigo               character varying(15)                 not null,
   nombre_item          character varying(20)                 not null,
   ubicacion            character varying(40)                 not null,
   cantidad             int4								  not null,
   estado               int4                                  not null,
   id_tipo_producto     int4                                  not null,     
   UNIQUE (codigo),
         
   constraint pk_id_lista_productos primary key (id));
ALTER TABLE kardex.lista_productos OWNER to postgres;


create table kardex.tipo_producto ( 
   id		            serial                                not null,
   codigo               character varying(15)                 not null,
   nombre_item          character varying(20)                 not null,
   estado               int4                                  not null,
   UNIQUE (codigo),      
   constraint pk_id_tipo_producto primary key (id));
ALTER TABLE seguridades.usuario OWNER to postgres;


alter table kardex.lista_productos 
   add constraint fk_id_tipo_producto foreign key (id_tipo_producto)
      references kardex.tipo_Producto (id)
      on delete restrict on update restrict;

------------------------------------------------------------------------------------
 insert into seguridades.usuario values (1,'1714564489','Galo','Ortega','galo','1234');
 
 
 insert into kardex.tipo_producto values (1,'P001','Ropa',1);
 insert into kardex.tipo_producto values (2,'P002','Accesorio',1);
 insert into kardex.lista_productos values (1,'I001','Camiseta Avengers','Sector 1',4,1,1);
 insert into kardex.lista_productos values (2,'I002','Camiseta DC','Sector 2',5,1,1);
 insert into kardex.lista_productos values (3,'I003','Pulsera DC','Sector 3',6,1,2);
 
 
 
 
 
 
 