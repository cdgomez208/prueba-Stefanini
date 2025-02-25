
create database postgres;

CREATE TABLE public.productos (
    id_producto SERIAL PRIMARY KEY,
    nombre VARCHAR NOT NULL,
    precio INTEGER NOT NULL,
    cantidad INTEGER NOT NULL,
    id_categoria INTEGER NOT NULL
);