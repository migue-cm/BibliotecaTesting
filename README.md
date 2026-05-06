# SISTEMA DE BIBLIOTECA

SCRIPTS PARA LA BASE DE DATOS

CREATE TABLE libro(
    id SERIAL PRIMARY KEY,
    isbn VARCHAR(20) UNIQUE NOT NULL,
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(100),
    editorial VARCHAR(100),
    anio INT,
    cantidad INT NOT NULL
);


CREATE TABLE lector(
    id SERIAL PRIMARY KEY,
    dni VARCHAR(8) UNIQUE,
    nombre VARCHAR(100)
);


CREATE TABLE prestamo(
    id SERIAL PRIMARY KEY,
    id_libro INT REFERENCES libro(id),
    id_lector INT REFERENCES lector(id),
    fecha_prestamo DATE,
    fecha_devolucion DATE,
    estado VARCHAR(20)
);
