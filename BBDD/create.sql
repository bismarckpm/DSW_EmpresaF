CREATE TABLE pais(
	codigo_pais INT NOT NULL AUTO_INCREMENT,
	nombre_pais VARCHAR(64) NOT NULL,
	PRIMARY KEY(codigo_pais)
);

CREATE TABLE municipio(
	codigo_municipio INT NOT NULL,
	nombre_municipio VARCHAR(64) NOT NULL,
	fk_pais INT NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(codigo_municipio),
	FOREIGN KEY(fk_pais) REFERENCES pais(codigo_pais)
);

CREATE TABLE parroquia(
	codigo_parroquia INT NOT NULL AUTO_INCREMENT,
	nombre_parroquia VARCHAR(64) NOT NULL,
	fk_municipio INT NOT NULL,
	PRIMARY KEY(codigo_parroquia),
	FOREIGN KEY(fk_municipio) REFERENCES municipio(codigo_municipio)
);

CREATE TABLE categoria(
	codigo_categoria INT NOT NULL AUTO_INCREMENT,
	nombre_categoria VARCHAR(64) NOT NULL,
	PRIMARY KEY(codigo_categoria)
);

CREATE TABLE subcategoria(
	codigo_subcategoria INT NOT NULL AUTO_INCREMENT,
	nombre_categoria VARCHAR(64) NOT NULL,
	fk_categoria INT NOT NULL,
	PRIMARY KEY(codigo_subcategoria),
	FOREIGN KEY (fk_categoria) REFERENCES categoria (codigo_categoria)
);

CREATE TABLE marca(
	codigo_marca INT NOT NULL AUTO_INCREMENT,
	nombre_marca VARCHAR(64) NOT NULL,
	tipo_marca VARCHAR(64) NOT NULL,
	capacidad DECIMAL(8,2) NOT NULL,
	unidad VARCHAR(64) NOT NULL,
	fk_subcategoria INT NOT NULL,
	PRIMARY KEY(codigo_marca),
	FOREIGN KEY (fk_subcategoria) REFERENCES subcategoria (codigo_subcategoria)
);

CREATE TABLE encuesta(
	codigo_encuesta INT NOT NULL AUTO_INCREMENT,
	fk_subcategoria INT NOT NULL,
	PRIMARY KEY(codigo_encuesta),
	FOREIGN KEY(fk_subcategoria) REFERENCES subcategoria(codigo_subcategoria)
);

CREATE TABLE estudio(
	codigo_estudio INT NOT NULL AUTO_INCREMENT,
	estado ENUM('solicitado','procesado','ejecutando','culminado') NOT NULL,
	resultado VARCHAR(64),
	fecha_incio TIMESTAMP,
	fecha_fin TIMESTAMP,
	fk_encuesta INT NOT NULL,
	fk_subcategoria INT NOT NULL,
	PRIMARY KEY(codigo_estudio),
	FOREIGN KEY(fk_encuesta) REFERENCES encuesta(codigo_encuesta),
	FOREIGN KEY(fk_subcategoria) REFERENCES subcategoria(codigo_subcategoria)
);

CREATE TABLE caracteristica(
	codigo_caracteristica INT NOT NULL AUTO_INCREMENT,
	edad_inicial INT,
	edad_final INT,
	genero ENUM('femenino','masculino','otro'),
	nivel_socioeconomico ENUM('alto','medio','bajo'),
	fk_parroquia INT NOT NULL,
	PRIMARY KEY(codigo_caracteristica),
	FOREIGN KEY(fk_parroquia) REFERENCES parroquia(codigo_parroquia)
);

CREATE TABLE cliente(
	codigo_cliente INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(64),
	PRIMARY KEY(codigo_cliente)
);

CREATE TABLE solicitud_estudio(
	codigo_soli_estu INT NOT NULL AUTO_INCREMENT,
	fecha_incio TIMESTAMP,
	fecha_fin TIMESTAMP,
	estado ENUM('solicitado','procesado','ejecutando','culminado') NOT NULL,
	fk_estudio INT NOT NULL,
	fk_cliente INT NOT NULL,
	fk_caracteristica INT NOT NULL,
	PRIMARY KEY(codigo_soli_estu),
	FOREIGN KEY(fk_estudio) REFERENCES estudio(codigo_estudio),
	FOREIGN KEY(fk_cliente) REFERENCES cliente(codigo_cliente),
	FOREIGN KEY(fk_caracteristica) REFERENCES caracteristica(codigo_caracteristica)
);

CREATE TABLE nivel_estudio(
	codigo_nive_estu INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(64) NOT NULL,
	PRIMARY KEY(codigo_nive_estu)
);

CREATE TABLE encuestado(
	codigo_encuestado INT NOT NULL AUTO_INCREMENT,
	numero_identificacion VARCHAR(16),
	primer_nombre VARCHAR(64) NOT NULL,
	segundo_nombre VARCHAR(64),
	primer_apellido VARCHAR(64) NOT NULL,
	segundo_apellido VARCHAR(64),
	direccion_complemento VARCHAR(255) NOT NULL,
	fecha_nacimiento DATE NOT NULL,
	genero ENUM('femenino','masculino','otro') NOT NULL,
	estado_civil ENUM('soltero','casado','divorciado','viudo') NOT NULL,
	ocupacion VARCHAR(64) NOT NULL,
	nombre VARCHAR(64),
	fk_parroquia INT NOT NULL,
	fk_nivel_estudio INT NOT NULL,
	PRIMARY KEY(codigo_encuestado),
	FOREIGN KEY(fk_parroquia) REFERENCES parroquia(codigo_parroquia),
	FOREIGN KEY(fk_nivel_estudio) REFERENCES nivel_estudio(codigo_nive_estu)
);

CREATE TABLE usuario(
	codigo_usuario INT NOT NULL AUTO_INCREMENT,
	nombre VARCHAR(64),
	estado ENUM('activo','inactivo','bloqueado') NOT NULL,
	fk_cliente INT,
	fk_encuestado INT,
	PRIMARY KEY(codigo_usuario),
	FOREIGN KEY(fk_cliente) REFERENCES cliente(codigo_cliente),
	FOREIGN KEY(fk_encuestado) REFERENCES encuestado(codigo_encuestado)
);

CREATE TABLE hijo(
	codigo_hijo INT NOT NULL AUTO_INCREMENT,
	fecha_nacimiento DATE NOT NULL,
	genero ENUM('femenino','masculino','otro') NOT NULL,
	fk_encuestado INT NOT NULL,
	PRIMARY KEY(codigo_hijo),
	FOREIGN KEY(fk_encuestado) REFERENCES encuestado(codigo_encuestado)
);

CREATE TABLE telefono(
	codigo_telefono INT NOT NULL AUTO_INCREMENT,
	codigo_area VARCHAR(4) NOT NULL,
	numero_telefono VARCHAR(7) NOT NULL,
	fk_encuestado INT NOT NULL,
	PRIMARY KEY(codigo_telefono),
	FOREIGN KEY(fk_encuestado) REFERENCES encuestado(codigo_encuestado)
);

CREATE TABLE dispositivo(
	codigo_dispositivo INT NOT NULL AUTO_INCREMENT,
	tipo ENUM('tablet','telefono','laptop', 'desktop') NOT NULL,
	fk_encuestado INT NOT NULL,
	PRIMARY KEY(codigo_dispositivo),
	FOREIGN KEY(fk_encuestado) REFERENCES encuestado(codigo_encuestado)
);

CREATE TABLE pregunta(
	codigo_pregunta INT NOT NULL AUTO_INCREMENT,
	descripcion_pregunta VARCHAR(255) NOT NULL,
	tipo ENUM('rango','multiple','simple', 'desarrollo') NOT NULL,
	max INT,
	min INT,
	PRIMARY KEY(codigo_pregunta)
);

CREATE TABLE pregunta_encuesta(
	codigo_preg_encu INT NOT NULL AUTO_INCREMENT,
	fk_pregunta INT NOT NULL,
	fk_encuesta INT NOT NULL,
	PRIMARY KEY(codigo_preg_encu),
	FOREIGN KEY (fk_pregunta) REFERENCES pregunta(codigo_pregunta),
	FOREIGN KEY (fk_encuesta) REFERENCES encuesta(codigo_encuesta)
);


CREATE TABLE opcion(
	codigo_opcion INT NOT NULL AUTO_INCREMENT,
	descripcion VARCHAR(255) NOT NULL,
	PRIMARY KEY(codigo_opcion)
);

CREATE TABLE pregunta_opcion(
	codigo_pregunta_opcion INT NOT NULL AUTO_INCREMENT,
	fk_pregunta INT NOT NULL,
	fk_opcion INT NOT NULL,
	PRIMARY KEY(codigo_pregunta_opcion),
	FOREIGN KEY (fk_pregunta) REFERENCES pregunta(codigo_pregunta),
	FOREIGN KEY (fk_opcion) REFERENCES opcion(codigo_opcion)
);

CREATE TABLE respuesta(
	codigo_respuesta INT NOT NULL AUTO_INCREMENT,
	fecha TIMESTAMP,
	descripcion VARCHAR(255) NOT NULL,
	rango INT,
	fk_encuestado INT NOT NULL,
	fk_pregunta_encuesta INT NOT NULL,
	fk_opcion INT,
	PRIMARY KEY(codigo_respuesta),
	FOREIGN KEY(fk_encuestado) REFERENCES encuestado(codigo_encuestado),
	FOREIGN KEY(fk_pregunta_encuesta) REFERENCES pregunta_encuesta(codigo_preg_encu),
	FOREIGN KEY (fk_opcion) REFERENCES opcion(codigo_opcion)
);