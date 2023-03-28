CREATE DATABASE Series;

USE Series;
/* --------------------------------------------------------------------- USUARIO --------------------------------------------------------------------- */
CREATE TABLE Usuario(
    IdUsuario INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(45),
    Paterno VARCHAR(45),
    Materno VARCHAR(45),
    FechaNacimiento DATE,
    Celular BIGINT,
    Sexo CHAR,
    Correo VARCHAR(45),
    Username VARCHAR(45),
    Password VARCHAR(45)
);

CREATE table Auditoria_Usuario(
    id int primary key auto_increment,

    Nombre VARCHAR(45),
    Paterno VARCHAR(45),
    Materno VARCHAR(45),
    FechaNacimiento DATE,
    Celular BIGINT,
    Sexo CHAR,
    Correo VARCHAR(45),
    Username VARCHAR(45),
    Password VARCHAR(45),

    usuario VARCHAR(45),
    modificado DATETIME,
    proceso VARCHAR(45),
    idUsuario INT
);

/* ------------------------------------------------------- TRIGGERS ------------------------------------------------------- */
/* Insertar */
CREATE TRIGGER Agregar_Auditoria_Usuario
AFTER INSERT ON Usuario
FOR EACH ROW
INSERT INTO Auditoria_Usuario(
    Nombre, Paterno, Materno, FechaNacimiento,
    Celular, Sexo, Correo, Username, Password,
    usuario, modificado, proceso, idUsuario)
VALUES(
    NEW.Nombre, NEW.Paterno, NEW.Materno, NEW.FechaNacimiento,
    NEW.Celular, NEW.Sexo, NEW.Correo, NEW.Username, NEW.Password,
    USER(), NOW(), 'Dato Insertado', NEW.idUsuario);

/* Actualizar */
CREATE TRIGGER Actualiza_Auditoria_Usuario
AFTER UPDATE ON Usuario
FOR EACH ROW
INSERT INTO Auditoria_Usuario(
    Nombre, Paterno, Materno, FechaNacimiento,
    Celular, Sexo, Correo, Username, Password,
    usuario, modificado, proceso, idUsuario)
VALUES(
    NEW.Nombre, NEW.Paterno, NEW.Materno, NEW.FechaNacimiento,
    NEW.Celular, NEW.Sexo, NEW.Correo, NEW.Username, NEW.Password,
    USER(), NOW(), 'Dato Actualizado', NEW.idUsuario);

/* Eliminar */
CREATE TRIGGER Elimina_Auditoria_Usuario
AFTER DELETE ON Usuario
FOR EACH ROW
INSERT INTO Auditoria_Usuario(
    Nombre, Paterno, Materno, FechaNacimiento,
    Celular, Sexo, Correo, Username, Password,
    usuario, modificado, proceso, idUsuario)
VALUES(
    OLD.Nombre, OLD.Paterno, OLD.Materno, OLD.FechaNacimiento,
    OLD.Celular, OLD.Sexo, OLD.Correo, OLD.Username, OLD.Password,
    USER(), NOW(), 'Dato Eliminado', OLD.idUsuario);

INSERT INTO Usuario VALUES
    (null, 'Raul Gabriel', 'Geronimo', 'Herrera', '2000-09-07', 5528973869, 'H', 'raul090700@gmail.com', 'Raul', md5('1829301'));

/* ----------------------------------------------------------------- TABLAS EXTERNAS ----------------------------------------------------------------- */
CREATE TABLE Pais(
    idPais INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(60),
    Nacionalidad VARCHAR(60),
    Continente VARCHAR(60),
    Bandera VARCHAR(200)
);

CREATE TABLE Genero(
    idGenero INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(70),
    Descripcion VARCHAR(200)
);

CREATE TABLE EstudioAnimacion(
    idEstudio INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(45),
    Fundacion DATE,
    Imagen VARCHAR(200)
);

CREATE TABLE Clasificacion(
    idClasificacion INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(45),
    Descripcion VARCHAR(200)
);

CREATE TABLE Productora(
    idProductora INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(60),
    Genero INT,
    SitioWeb VARCHAR(60),
    Imagen VARCHAR(200),
    FOREIGN KEY (Genero) REFERENCES Genero(idGenero) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Distribuidora(
    idDistribuidora INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(60),
    Genero INT,
    SitioWeb VARCHAR(60),
    Imagen VARCHAR(200),
    FOREIGN KEY (Genero) REFERENCES Genero(idGenero) ON UPDATE CASCADE ON DELETE CASCADE
);

/* ------------------------------------------------------- TRIGGERS ------------------------------------------------------- */
/* Insertar */
CREATE TRIGGER Agregar_Distribuidora
AFTER INSERT ON Productora
FOR EACH ROW
INSERT INTO Distribuidora (
    Nombre, Genero, SitioWeb, Imagen
    )
VALUES(
    NEW.Nombre, NEW.Genero, NEW.SitioWeb, NEW.Imagen
);

/* Actualizar */
CREATE TRIGGER Actualiza_Distribuidora
AFTER UPDATE ON Productora
FOR EACH ROW
UPDATE Distribuidora SET Nombre = NEW.Nombre, Genero = NEW.Genero, SitioWeb = NEW.SitioWeb, Imagen = NEW.Imagen WHERE idDistribuidora = NEW.idProductora;

/* Eliminar */
CREATE TRIGGER Elimina_Distribuidora
AFTER DELETE ON Productora
FOR EACH ROW
DELETE FROM Distribuidora WHERE idDistribuidora = OLD.idProductora;

/* --------------------------------------------------------------------- DIRECTOR --------------------------------------------------------------------- */
CREATE TABLE Director(
    idDirector INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(60),
    Apellidos VARCHAR(60),
    NombreArtistico VARCHAR(60),
    FechaNacimiento DATE,
    FechaDefuncion DATE,
    Sexo CHAR,
    Estatura DOUBLE,
    Nacionalidad INT,
    FOREIGN KEY (Nacionalidad) REFERENCES Pais(idPais) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Auditoria_Director(
    id INT PRIMARY KEY AUTO_INCREMENT,

    Nombre VARCHAR(60),
    Apellidos VARCHAR(60),
    NombreArtistico VARCHAR(60),
    FechaNacimiento DATE,
    FechaDefuncion DATE,
    Sexo CHAR,
    Estatura DOUBLE,
    Nacionalidad INT,

    Usuario VARCHAR(45),
    Modificado DATETIME,
    Proceso VARCHAR(45),
    idDirector INT
);
/* ------------------------------------------------------- TRIGGERS ------------------------------------------------------- */
/* Insertar */
CREATE TRIGGER Agregar_Auditoria_Director
AFTER INSERT ON Director
FOR EACH ROW
INSERT INTO Auditoria_Director(
    Nombre, Apellidos, NombreArtistico,
    FechaNacimiento, FechaDefuncion, Sexo, Estatura, Nacionalidad,
    Usuario, Modificado, Proceso, idDirector)
VALUES(
    NEW.Nombre, NEW.Apellidos, NEW.NombreArtistico,
    NEW.FechaNacimiento, NEW.FechaDefuncion, NEW.Sexo, NEW.Estatura, NEW.Nacionalidad,
    USER(), NOW(), 'Dato Insertado', NEW.idDirector);

/* Actualizar */
CREATE TRIGGER Actualiza_Auditoria_Director
AFTER UPDATE ON Director
FOR EACH ROW
INSERT INTO Auditoria_Director(
    Nombre, Apellidos, NombreArtistico,
    FechaNacimiento, FechaDefuncion, Sexo, Estatura, Nacionalidad,
    Usuario, Modificado, Proceso, idDirector)
VALUES(
    NEW.Nombre, NEW.Apellidos, NEW.NombreArtistico,
    NEW.FechaNacimiento, NEW.FechaDefuncion, NEW.Sexo, NEW.Estatura, NEW.Nacionalidad,
    USER(), NOW(), 'Dato Actualizado', NEW.idDirector);

/* Eliminar */
CREATE TRIGGER Elimina_Auditoria_Director
AFTER DELETE ON Director
FOR EACH ROW
INSERT INTO Auditoria_Director(
    Nombre, Apellidos, NombreArtistico,
    FechaNacimiento, FechaDefuncion, Sexo, Estatura, Nacionalidad,
    Usuario, Modificado, Proceso, idDirector)
VALUES(
    OLD.Nombre, OLD.Apellidos, OLD.NombreArtistico,
    OLD.FechaNacimiento, OLD.FechaDefuncion, OLD.Sexo, OLD.Estatura, OLD.Nacionalidad,
    USER(), NOW(), 'Dato Eliminado', OLD.idDirector);

/* --------------------------------------------------------------------- PELICULAS --------------------------------------------------------------------- */
CREATE TABLE Peliculas(
    idPelicula INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(60),
    OtrosNombres VARCHAR(60),
    idProductora INT,
    idDistribuidora INT,
    Duracion TIME,
    Genero INT,
    Tipo VARCHAR(45), /* Animada, Stop Motion o LiveAction */
    Clasificacion INT,
    Estreno DATE,
    EstrenoMexico DATE,
    Calificacion DOUBLE,
    idDirector INT,
    Portada TEXT,
    FOREIGN KEY(idProductora) REFERENCES Productora(idProductora) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(idDistribuidora) REFERENCES Distribuidora(idDistribuidora) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (Genero) REFERENCES Genero (idGenero) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (Clasificacion) REFERENCES Clasificacion (idClasificacion) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(idDirector) REFERENCES Director(idDirector) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Auditoria_Peliculas(
    id INT PRIMARY KEY AUTO_INCREMENT,

    Nombre VARCHAR(60),
    OtrosNombres VARCHAR(60),
    idProductora INT,
    idDistribuidora INT,
    Duracion TIME,
    Genero INT,
    Tipo VARCHAR(45),
    Clasificacion INT,
    Estreno DATE,
    EstrenoMexico DATE,
    Calificacion DOUBLE,
    idDirector INT,
    Portada TEXT,

    usuario VARCHAR(45),
    modificado DATETIME,
    proceso VARCHAR(45),
    idPelicula INT
);

/* ------------------------------------------------------- TRIGGERS ------------------------------------------------------- */
/* Insertar */
CREATE TRIGGER Agregar_Auditoria_Pelicula
AFTER INSERT ON Peliculas
FOR EACH ROW
INSERT INTO Auditoria_Peliculas(
    Nombre, OtrosNombres, idProductora, idDistribuidora, Duracion,
    Genero, Tipo, Clasificacion, Estreno, EstrenoMexico, Calificacion, idDirector, Portada,
    usuario, modificado, proceso, idPelicula)
VALUES(
    NEW.Nombre, NEW.OtrosNombres, NEW.idProductora, NEW.idDistribuidora, NEW.Duracion,
    NEW.Genero, NEW.Tipo, NEW.Clasificacion, NEW.Estreno, NEW.EstrenoMexico, NEW.Calificacion, NEW.idDirector, NEW.Portada,
    USER(), NOW(), 'Dato Insertado', NEW.idPelicula);

/* Actualizar */
CREATE TRIGGER Actualiza_Auditoria_Pelicula
AFTER UPDATE ON Peliculas
FOR EACH ROW
INSERT INTO Auditoria_Peliculas(
    Nombre, OtrosNombres, idProductora, idDistribuidora, Duracion,
    Genero, Tipo, Clasificacion, Estreno, EstrenoMexico, Calificacion, idDirector, Portada,
    usuario, modificado, proceso, idPelicula)
VALUES(
    NEW.Nombre, NEW.OtrosNombres, NEW.idProductora, NEW.idDistribuidora, NEW.Duracion,
    NEW.Genero, NEW.Tipo, NEW.Clasificacion, NEW.Estreno, NEW.EstrenoMexico, NEW.Calificacion, NEW.idDirector, NEW.Portada,
    USER(), NOW(), 'Dato Actualizado', NEW.idPelicula);

/* Eliminar */
CREATE TRIGGER Elimina_Auditoria_Pelicula
AFTER DELETE ON Peliculas
FOR EACH ROW
INSERT INTO Auditoria_Peliculas(
    Nombre, OtrosNombres, idProductora, idDistribuidora, Duracion,
    Genero, Tipo, Clasificacion, Estreno, EstrenoMexico, Calificacion, idDirector, Portada,
    usuario, modificado, proceso, idPelicula)
VALUES(
    OLD.Nombre, OLD.OtrosNombres, OLD.idProductora, OLD.idDistribuidora, OLD.Duracion,
    OLD.Genero, OLD.Tipo, OLD.Clasificacion, OLD.Estreno, OLD.EstrenoMexico, OLD.Calificacion, OLD.idDirector, OLD.Portada,
    USER(), NOW(), 'Dato Eliminado', OLD.idPelicula);

/* --------------------------------------------------------------------- SERIES --------------------------------------------------------------------- */
CREATE TABLE Series(
    idSerie INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(60),
    OtrosNombres VARCHAR(60),
    Genero INT,
    idProductora INT,
    idDistribuidora INT,
    idDirector INT,
    Portada TEXT,
    FOREIGN KEY(Genero) REFERENCES Genero(idGenero) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(idProductora) REFERENCES Productora(idProductora) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(idDistribuidora) REFERENCES Distribuidora(idDistribuidora) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(idDirector) REFERENCES Director(idDirector) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Auditoria_Series(
    id INT PRIMARY KEY AUTO_INCREMENT,

    Nombre VARCHAR(60),
    OtrosNombres VARCHAR(60),
    Genero VARCHAR(60),
    idProductora INT,
    idDistribuidora INT,
    idDirector INT,
    Portada TEXT,

    usuario VARCHAR(45),
    modificado DATETIME,
    proceso VARCHAR(45),
    idSerie INT
);

/* ------------------------------------------------------- TRIGGERS ------------------------------------------------------- */
/* Insertar */
CREATE TRIGGER Agregar_Auditoria_Series
AFTER INSERT ON Series
FOR EACH ROW
INSERT INTO Auditoria_Series(
    Nombre, OtrosNombres, Genero,
    idProductora, idDistribuidora, idDirector, Portada,
    usuario, modificado, proceso, idSerie)
VALUES(
    NEW.Nombre, NEW.OtrosNombres, NEW.Genero,
    NEW.idProductora, NEW.idDistribuidora, NEW.idDirector, NEW.Portada,
    USER(), NOW(), 'Dato Insertado', NEW.idSerie);

/* Actualizar */
CREATE TRIGGER Actualiza_Auditoria_Series
AFTER UPDATE ON Series
FOR EACH ROW
INSERT INTO Auditoria_Series(
    Nombre, OtrosNombres, Genero,
    idProductora, idDistribuidora, idDirector, Portada,
    usuario, modificado, proceso, idSerie)
VALUES(
    NEW.Nombre, NEW.OtrosNombres, NEW.Genero,
    NEW.idProductora, NEW.idDistribuidora, NEW.idDirector, NEW.Portada,
    USER(), NOW(), 'Dato Actualizado', NEW.idSerie);

/* Eliminar */
CREATE TRIGGER Elimina_Auditoria_Series
AFTER DELETE ON Series
FOR EACH ROW
INSERT INTO Auditoria_Series(
    Nombre, OtrosNombres, Genero,
    idProductora, idDistribuidora, idDirector, Portada,
    usuario, modificado, proceso, idSerie)
VALUES(
    OLD.Nombre, OLD.OtrosNombres, OLD.Genero,
    OLD.idProductora, OLD.idDistribuidora, OLD.idDirector, OLD.Portada,
    USER(), NOW(), 'Dato Eliminado', OLD.idSerie);

/* --------------------------------------------------------------------- TEMPORADAS SERIES --------------------------------------------------------------------- */
CREATE TABLE Temporadas_Series(
    idTemporada INT PRIMARY KEY AUTO_INCREMENT,
    idSerie INT,
    Nombre VARCHAR(60),
    Capitulos INT,
    Duracion TIME,
    Calificacion DOUBLE,
    FechaInicio DATE,
    FechaFin DATE,
    FOREIGN KEY(idSerie) REFERENCES Series(idSerie) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Auditoria_Temporadas_Series(
    id INT PRIMARY KEY AUTO_INCREMENT,

    idSerie INT,
    Nombre VARCHAR(60),
    Capitulos INT,
    Duracion TIME,
    Calificacion DOUBLE,
    FechaInicio DATE,
    FechaFin DATE,

    usuario VARCHAR(45),
    modificado DATETIME,
    proceso VARCHAR(45),
    idTemporada INT
);

/* ------------------------------------------------------- TRIGGERS ------------------------------------------------------- */
/* Insertar */
CREATE TRIGGER Agregar_Auditoria_Temporadas_Series
AFTER INSERT ON Temporadas_Series
FOR EACH ROW
INSERT INTO Auditoria_Temporadas_Series(
    Nombre, idSerie, Capitulos, Duracion,
    Calificacion, FechaInicio, FechaFin,
    usuario, modificado, proceso, idTemporada)
VALUES(
    NEW.Nombre, NEW.idSerie, NEW.Capitulos, NEW.Duracion,
    NEW.Calificacion, NEW.FechaInicio, NEW.FechaFin,
    USER(), NOW(), 'Dato Insertado', NEW.idTemporada);

/* Actualizar */
CREATE TRIGGER Actualiza_Auditoria_Temporadas_Series
AFTER UPDATE ON Temporadas_Series
FOR EACH ROW
INSERT INTO Auditoria_Temporadas_Series(
    Nombre, idSerie, Capitulos, Duracion,
    Calificacion, FechaInicio, FechaFin,
    usuario, modificado, proceso, idTemporada)
VALUES(
    NEW.Nombre, NEW.idSerie, NEW.Capitulos, NEW.Duracion,
    NEW.Calificacion, NEW.FechaInicio, NEW.FechaFin,
    USER(), NOW(), 'Dato Actualizado', NEW.idTemporada);

/* Eliminar */
CREATE TRIGGER Elimina_Auditoria_Temporadas_Series
AFTER DELETE ON Temporadas_Series
FOR EACH ROW
INSERT INTO Auditoria_Temporadas_Series(
    Nombre, idSerie, Capitulos, Duracion,
    Calificacion, FechaInicio, FechaFin,
    usuario, modificado, proceso, idTemporada)
VALUES(
    OLD.Nombre, OLD.idSerie, OLD.Capitulos, OLD.Duracion,
    OLD.Calificacion, OLD.FechaInicio, OLD.FechaFin,
    USER(), NOW(), 'Dato Eliminado', OLD.idTemporada);

/* --------------------------------------------------------------------- CARICATURAS --------------------------------------------------------------------- */
CREATE TABLE Caricatura(
    idCaricatura INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(60),
    OtrosNombres VARCHAR(60),
    Genero INT,
    idProductora INT,
    idDistribuidora INT,
    idDirector INT,
    Portada TEXT,
    FOREIGN KEY(Genero) REFERENCES Genero(idGenero) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(idProductora) REFERENCES Productora(idProductora) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(idDistribuidora) REFERENCES Distribuidora(idDistribuidora) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(idDirector) REFERENCES Director(idDirector) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Auditoria_Caricatura(
    id INT PRIMARY KEY AUTO_INCREMENT,

    Nombre VARCHAR(60),
    OtrosNombres VARCHAR(60),
    Genero INT,
    idProductora INT,
    idDistribuidora INT,
    idDirector INT,
    Portada TEXT,

    usuario VARCHAR(45),
    modificado DATETIME,
    proceso VARCHAR(45),
    idCaricatura INT
);

/* ------------------------------------------------------- TRIGGERS ------------------------------------------------------- */
/* Insertar */
CREATE TRIGGER Agregar_Auditoria_Caricatura
AFTER INSERT ON Caricatura
FOR EACH ROW
INSERT INTO Auditoria_Caricatura(
    Nombre, OtrosNombres, Genero,
    idProductora, idDistribuidora, idDirector, Portada,
    usuario, modificado, proceso, idCaricatura)
VALUES(
    NEW.Nombre, NEW.OtrosNombres, NEW.Genero,
    NEW.idProductora, NEW.idDistribuidora, NEW.idDirector, NEW.Portada,
    USER(), NOW(), 'Dato Insertado', NEW.idCaricatura);

/* Actualizar */
CREATE TRIGGER Actualiza_Auditoria_Caricatura
AFTER UPDATE ON Caricatura
FOR EACH ROW
INSERT INTO Auditoria_Caricatura(
    Nombre, OtrosNombres, Genero,
    idProductora, idDistribuidora, idDirector, Portada,
    usuario, modificado, proceso, idCaricatura)
VALUES(
    NEW.Nombre, NEW.OtrosNombres, NEW.Genero,
    NEW.idProductora, NEW.idDistribuidora, NEW.idDirector, NEW.Portada,
    USER(), NOW(), 'Dato Actualizado', NEW.idCaricatura);

/* Eliminar */
CREATE TRIGGER Elimina_Auditoria_Caricatura
AFTER DELETE ON Caricatura
FOR EACH ROW
INSERT INTO Auditoria_Caricatura(
    Nombre, OtrosNombres, Genero,
    idProductora, idDistribuidora, idDirector, Portada,
    usuario, modificado, proceso, idCaricatura)
VALUES(
    OLD.Nombre, OLD.OtrosNombres, OLD.Genero,
    OLD.idProductora, OLD.idDistribuidora, OLD.idDirector, OLD.Portada,
    USER(), NOW(), 'Dato Eliminado', OLD.idCaricatura);

/* --------------------------------------------------------------------- TEMPORADAS CARICATURAS --------------------------------------------------------------------- */
CREATE TABLE Temporadas_Caricatura(
    idTemporada INT PRIMARY KEY AUTO_INCREMENT,
    idCaricatura INT,
    Nombre VARCHAR(60),
    Capitulos INT,
    Duracion TIME,
    Calificacion DOUBLE,
    FechaInicio DATE,
    FechaFin DATE,
    FOREIGN KEY(idCaricatura) REFERENCES Caricatura(idCaricatura) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Auditoria_Temporadas_Caricatura(
    id INT PRIMARY KEY AUTO_INCREMENT,

    idCaricatura INT,
    Nombre VARCHAR(60),
    Capitulos INT,
    Duracion TIME,
    Calificacion DOUBLE,
    FechaInicio DATE,
    FechaFin DATE,

    usuario VARCHAR(45),
    modificado DATETIME,
    proceso VARCHAR(45),
    idTemporada INT
);

/* ------------------------------------------------------- TRIGGERS ------------------------------------------------------- */
/* Insertar */
CREATE TRIGGER Agregar_Auditoria_Temporadas_Caricatura
AFTER INSERT ON Temporadas_Caricatura
FOR EACH ROW
INSERT INTO Auditoria_Temporadas_Caricatura(
    Nombre, idCaricatura, Capitulos, Duracion,
    Calificacion, FechaInicio, FechaFin,
    usuario, modificado, proceso, idTemporada)
VALUES(
    NEW.Nombre, NEW.idCaricatura, NEW.Capitulos, NEW.Duracion,
    NEW.Calificacion, NEW.FechaInicio, NEW.FechaFin,
    USER(), NOW(), 'Dato Insertado', NEW.idTemporada);

/* Actualizar */
CREATE TRIGGER Actualiza_Auditoria_Temporadas_Caricatura
AFTER UPDATE ON Temporadas_Caricatura
FOR EACH ROW
INSERT INTO Auditoria_Temporadas_Caricatura(
    Nombre, idCaricatura, Capitulos, Duracion,
    Calificacion, FechaInicio, FechaFin,
    usuario, modificado, proceso, idTemporada)
VALUES(
    NEW.Nombre, NEW.idCaricatura, NEW.Capitulos, NEW.Duracion,
    NEW.Calificacion, NEW.FechaInicio, NEW.FechaFin,
    USER(), NOW(), 'Dato Actualizado', NEW.idTemporada);

/* Eliminar */
CREATE TRIGGER Elimina_Auditoria_Temporadas_Caricatura
AFTER DELETE ON Temporadas_Caricatura
FOR EACH ROW
INSERT INTO Auditoria_Temporadas_Caricatura(
    Nombre, idCaricatura, Capitulos, Duracion,
    Calificacion, FechaInicio, FechaFin,
    usuario, modificado, proceso, idTemporada)
VALUES(
    OLD.Nombre, OLD.idCaricatura, OLD.Capitulos, OLD.Duracion,
    OLD.Calificacion, OLD.FechaInicio, OLD.FechaFin,
    USER(), NOW(), 'Dato Eliminado', OLD.idTemporada);

/* --------------------------------------------------------------------- ANIMES - TEMPORADAS EMISION --------------------------------------------------------------------- */
CREATE TABLE Temporadas_Emision(
    idTemporada INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(45),
    FechaInicio DATE,
    FechaFin DATE
);

CREATE TABLE Auditoria_Temporadas_Emision(
    id INT PRIMARY KEY AUTO_INCREMENT,

    Nombre VARCHAR(45),
    FechaInicio DATE,
    FechaFin DATE,

    usuario VARCHAR(45),
    modificado DATETIME,
    proceso VARCHAR(45),
    idTemporada INT
);
/* ------------------------------------------------------- TRIGGERS ------------------------------------------------------- */
/* Insertar */
CREATE TRIGGER Agregar_Auditoria_Temporadas_Emision
AFTER INSERT ON Temporadas_Emision
FOR EACH ROW
INSERT INTO Auditoria_Temporadas_Emision(
    Nombre, FechaInicio, FechaFin,
    usuario, modificado, proceso, idTemporada)
VALUES(
    NEW.Nombre, NEW.FechaInicio, NEW.FechaFin,
    USER(), NOW(), 'Dato Insertado', NEW.idTemporada);

/* Actualizar */
CREATE TRIGGER Actualiza_Auditoria_Temporadas_Emision
AFTER UPDATE ON Temporadas_Emision
FOR EACH ROW
INSERT INTO Auditoria_Temporadas_Emision(
    Nombre, FechaInicio, FechaFin,
    usuario, modificado, proceso, idTemporada)
VALUES(
    NEW.Nombre, NEW.FechaInicio, NEW.FechaFin,
    USER(), NOW(), 'Dato Actualizado', NEW.idTemporada);

/* Eliminar */
CREATE TRIGGER Elimina_Auditoria_Temporadas_Emision
AFTER DELETE ON Temporadas_Emision
FOR EACH ROW
INSERT INTO Auditoria_Temporadas_Emision(
    Nombre, FechaInicio, FechaFin,
    usuario, modificado, proceso, idTemporada)
VALUES(
    OLD.Nombre, OLD.FechaInicio, OLD.FechaFin,
    USER(), NOW(), 'Dato Eliminado', OLD.idTemporada);

/* --------------------------------------------------------------------- ANIMES --------------------------------------------------------------------- */
CREATE TABLE Anime(
    idAnime INT PRIMARY KEY AUTO_INCREMENT,
    Nombre VARCHAR(100),
    OtrosNombres VARCHAR(300),
    Genero VARCHAR(100)
);

CREATE TABLE Auditoria_Anime(
    id INT PRIMARY KEY AUTO_INCREMENT,

    Nombre VARCHAR(100),
    OtrosNombres VARCHAR(300),
    Genero VARCHAR(100),

    usuario VARCHAR(45),
    modificado DATETIME,
    proceso VARCHAR(45),
    idAnime INT
);
/* ------------------------------------------------------- TRIGGERS ------------------------------------------------------- */
/* Insertar */
CREATE TRIGGER Agregar_Auditoria_Anime
AFTER INSERT ON Anime
FOR EACH ROW
INSERT INTO Auditoria_Anime(
    Nombre, OtrosNombres, Genero,
    usuario, modificado, proceso, idAnime)
VALUES(
    NEW.Nombre, NEW.OtrosNombres, NEW.Genero,
    USER(), NOW(), 'Dato Insertado', NEW.idAnime);

/* Actualizar */
CREATE TRIGGER Actualiza_Auditoria_Anime
AFTER UPDATE ON Anime
FOR EACH ROW
INSERT INTO Auditoria_Anime(
    Nombre, OtrosNombres, Genero,
    usuario, modificado, proceso, idAnime)
VALUES(
    NEW.Nombre, NEW.OtrosNombres, NEW.Genero,
    USER(), NOW(), 'Dato Actualizado', NEW.idAnime);

/* Eliminar */
CREATE TRIGGER Elimina_Auditoria_Anime
AFTER DELETE ON Anime
FOR EACH ROW
INSERT INTO Auditoria_Anime(
    Nombre, OtrosNombres, Genero,
    usuario, modificado, proceso, idAnime)
VALUES(
    OLD.Nombre, OLD.OtrosNombres, OLD.Genero,
    USER(), NOW(), 'Dato Eliminado', OLD.idAnime);

/* --------------------------------------------------------------------- TEMPORADAS ANIMES --------------------------------------------------------------------- */
CREATE TABLE Temporadas_Anime(
    idTemporada INT PRIMARY KEY AUTO_INCREMENT,
    idAnime INT,
    Nombre VARCHAR(100),
    OtrosNombres VARCHAR(300),
    Capitulos INT,
    Duracion TIME,
    Idioma VARCHAR(50),
    idTemporadaEmision INT,
    FechaInicio DATE,
    FechaFin DATE,
    idEstudio INT,
    Calificacion DOUBLE,
    Portada TEXT,
    FOREIGN KEY(idAnime) REFERENCES Anime(idAnime) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(idTemporadaEmision) REFERENCES Temporadas_Emision(idTemporada) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(idEstudio) REFERENCES EstudioAnimacion(idEstudio) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Auditoria_Temporadas_Anime(
    id INT PRIMARY KEY AUTO_INCREMENT,

    idAnime INT,
    Nombre VARCHAR(100),
    OtrosNombres VARCHAR(300),
    Capitulos INT,
    Duracion TIME,
    Idioma VARCHAR(50),
    idTemporadaEmision INT,
    FechaInicio DATE,
    FechaFin DATE,
    idEstudio INT,
    Calificacion DOUBLE,
    Portada TEXT,

    usuario VARCHAR(45),
    modificado DATETIME,
    proceso VARCHAR(45),
    idTemporada INT
);
/* ------------------------------------------------------- TRIGGERS ------------------------------------------------------- */
/* Insertar */
CREATE TRIGGER Agregar_Auditoria_Temporadas_Anime
AFTER INSERT ON Temporadas_Anime
FOR EACH ROW
INSERT INTO Auditoria_Temporadas_Anime(
    Nombre, idAnime, OtrosNombres, Capitulos, Duracion, Idioma,
    idTemporadaEmision, FechaInicio, FechaFin, idEstudio, Calificacion, Portada, 
    usuario, modificado, proceso, idTemporada)
VALUES(
    NEW.Nombre, NEW.idAnime, NEW.OtrosNombres, NEW.Capitulos, NEW.Duracion, NEW.Idioma,
    NEW.idTemporadaEmision, NEW.FechaInicio, NEW.FechaFin, NEW.idEstudio, NEW.Calificacion, NEW.Portada,
    USER(), NOW(), 'Dato Insertado', NEW.idTemporada);

/* Actualizar */
CREATE TRIGGER Actualiza_Auditoria_Temporadas_Anime
AFTER UPDATE ON Temporadas_Anime
FOR EACH ROW
INSERT INTO Auditoria_Temporadas_Anime(
    Nombre, idAnime, OtrosNombres, Capitulos, Duracion, Idioma,
    idTemporadaEmision, FechaInicio, FechaFin, idEstudio, Calificacion, Portada, 
    usuario, modificado, proceso, idTemporada)
VALUES(
    NEW.Nombre, NEW.idAnime, NEW.OtrosNombres, NEW.Capitulos, NEW.Duracion, NEW.Idioma,
    NEW.idTemporadaEmision, NEW.FechaInicio, NEW.FechaFin, NEW.idEstudio, NEW.Calificacion, NEW.Portada,
    USER(), NOW(), 'Dato Actualizado', NEW.idTemporada);

/* Eliminar */
CREATE TRIGGER Elimina_Auditoria_Temporadas_Anime
AFTER DELETE ON Temporadas_Anime
FOR EACH ROW
INSERT INTO Auditoria_Temporadas_Anime(
    Nombre, idAnime, OtrosNombres, Capitulos, Duracion, Idioma,
    idTemporadaEmision, FechaInicio, FechaFin, idEstudio, Calificacion, Portada, 
    usuario, modificado, proceso, idTemporada)
VALUES(
    OLD.Nombre, OLD.idAnime, OLD.OtrosNombres, OLD.Capitulos, OLD.Duracion, OLD.Idioma,
    OLD.idTemporadaEmision, OLD.FechaInicio, OLD.FechaFin, OLD.idEstudio, OLD.Calificacion, OLD.Portada,
    USER(), NOW(), 'Dato Eliminado', OLD.idTemporada);

/* ----------------------------------------------------------------------------- VISTAS ----------------------------------------------------------------------------- */
/********************************************** Lista Anime **********************************************/
CREATE OR REPLACE VIEW
Vista_Animes AS
SELECT
Anime.idAnime,
TRIM(Anime.Nombre) AS Nombre,
TRIM(Anime.OtrosNombres) AS OtrosNombres,
Anime.Genero, 
Temporadas_Anime.Portada,
COUNT((Temporadas_Anime.idTemporada)) AS Temporadas,
SUM(Temporadas_Anime.Capitulos) AS Capitulos,
DATE_FORMAT((MIN(Temporadas_Anime.FechaInicio)), "%d / %M / %Y") As FechaInicio,
MIN(Temporadas_Anime.FechaInicio) AS Inicio,
IF(Temporadas_Anime.FechaFin IS NULL AND Temporadas_Anime.FechaInicio IS NULL,' ' ,IF(MAX(Temporadas_Anime.FechaInicio > NOW()), MAX(Temporadas_Anime.FechaFin), (IF (MIN(Temporadas_Anime.FechaFin IS NOT NULL), ((MAX(Temporadas_Anime.FechaFin))), (IF(CURDATE() < Temporadas_Anime.FechaInicio, " ", CURDATE() )))))) As Fin,
IF(Temporadas_Anime.FechaFin IS NULL AND Temporadas_Anime.FechaInicio IS NULL,' ' ,IF(MAX(Temporadas_Anime.FechaInicio > NOW()), DATE_FORMAT(MAX(Temporadas_Anime.FechaFin), "%d / %M / %Y"), (IF (MIN(Temporadas_Anime.FechaFin IS NOT NULL), (DATE_FORMAT((MAX(Temporadas_Anime.FechaFin)), "%d / %M / %Y")), (IF(NOW() < Temporadas_Anime.FechaInicio, " ", DATE_FORMAT(NOW(), "%d / %M / %Y") )))))) As FechaFin,
IF(Temporadas_Anime.FechaFin IS NULL AND Temporadas_Anime.FechaInicio IS NULL, ' ', IF(MAX(Temporadas_Anime.FechaInicio > NOW()), "Estreno", IF (MIN(Temporadas_Anime.FechaFin IS NOT NULL), "Finalizado", "Emision"))) AS Estado,
IFNULL((IF((AVG(Temporadas_Anime.Calificacion)) = 10, FORMAT((AVG(Temporadas_Anime.Calificacion)), 0),FORMAT((AVG(Temporadas_Anime.Calificacion)), 2))), " ") AS Promedio
FROM Anime 
LEFT JOIN Temporadas_Anime
ON Temporadas_Anime.idAnime = Anime.idAnime
AND Temporadas_Anime.Duracion <= '00:59:00'
GROUP BY (Anime.idAnime)
ORDER BY Anime.Nombre;

/********************************************** Temporadas Anime **********************************************/
CREATE OR REPLACE VIEW
Vista_TemporadasAnime AS
SELECT
Temporadas_Anime.idTemporada,
Anime.idAnime,
EstudioAnimacion.idEstudio,
Temporadas_Emision.idTemporada AS idTemporadaEmision,
TRIM(Anime.Nombre) AS Anime,
TRIM(Temporadas_Anime.Nombre) AS Nombre,
TRIM(Temporadas_Anime.OtrosNombres) AS OtrosNombres,
Temporadas_Anime.Capitulos,
Temporadas_Anime.Duracion AS Duration,
IF(DATE_FORMAT(Temporadas_Anime.Duracion, "%H") = '00', DATE_FORMAT(Temporadas_Anime.Duracion, "%i min"), DATE_FORMAT(Temporadas_Anime.Duracion, "%Hh %im")) AS Duracion,
Temporadas_Anime.Idioma,
REPLACE(Temporadas_Emision.Nombre, "Otonio", "Otoño") AS Emision,
DAYNAME(Temporadas_Anime.FechaInicio) AS DiaEmision,
DATE_FORMAT(Temporadas_Anime.FechaInicio, "%d / %M / %Y") AS FechaInicio,
Temporadas_Anime.FechaInicio AS Inicio,

IFNULL((DATE_FORMAT(Temporadas_Anime.FechaFin, "%d / %M / %Y")), (DATE_FORMAT(NOW(), "%d / %M / %Y"))) AS FechaFin,
IFNULL((Temporadas_Anime.FechaFin), (CURDATE())) AS Fin,

IFNULL((TIMESTAMPDIFF(WEEK,Temporadas_Anime.FechaInicio, Temporadas_Anime.FechaFin)), TIMESTAMPDIFF(WEEK,Temporadas_Anime.FechaInicio, NOW())) AS Semanas,
TIMESTAMPDIFF(YEAR, Temporadas_Anime.FechaInicio, NOW()) AS Años,
EstudioAnimacion.Nombre AS Estudio,
IF(Calificacion = 10, FORMAT(Calificacion, 0),FORMAT(Calificacion, 2)) AS Calificacion,
Temporadas_Anime.Portada
FROM Temporadas_Anime
INNER JOIN Anime
ON Temporadas_Anime.idAnime = Anime.idAnime
INNER JOIN Temporadas_Emision
ON Temporadas_Anime.idTemporadaEmision = Temporadas_Emision.idTemporada
INNER JOIN EstudioAnimacion
ON Temporadas_Anime.idEstudio = EstudioAnimacion.idEstudio
ORDER BY Anime.Nombre;

/********************************************** Estudio Anime **********************************************/
CREATE OR REPLACE VIEW
Vista_Estudio AS
SELECT
EstudioAnimacion.idEstudio,
TRIM(EstudioAnimacion.Nombre) AS Nombre,
COUNT((Temporadas_Anime.idTemporada)) AS Animes,
DATE_FORMAT(Fundacion, "%M / %Y") AS Fundacion,
Imagen
FROM EstudioAnimacion
LEFT JOIN Temporadas_Anime
ON Temporadas_Anime.idEstudio = EstudioAnimacion.idEstudio
GROUP BY (EstudioAnimacion.idEstudio)
ORDER BY EstudioAnimacion.Nombre;

/********************************************** Temporadas Emision **********************************************/
CREATE OR REPLACE VIEW
Vista_TemporadasEmision AS
SELECT
Temporadas_Emision.idTemporada,
REPLACE(Temporadas_Emision.Nombre, "Otonio", "Otoño") AS Nombre,
DATE_FORMAT(Temporadas_Emision.FechaInicio, "%d / %M / %Y") AS FechaInicio,
DATE_FORMAT(Temporadas_Emision.FechaFin, "%d / %M / %Y") AS FechaFin,
COUNT((Temporadas_Anime.idTemporadaEmision)) AS Animes,
IFNULL((IF((AVG(Temporadas_Anime.Calificacion)) = 10, FORMAT((AVG(Temporadas_Anime.Calificacion)), 0),FORMAT((AVG(Temporadas_Anime.Calificacion)), 2))), " ") AS Promedio
FROM Temporadas_Emision
LEFT JOIN Temporadas_Anime
ON Temporadas_Anime.idTemporadaEmision = Temporadas_Emision.idTemporada
GROUP BY (Temporadas_Emision.idTemporada)
ORDER BY (Temporadas_Emision.Nombre);

/********************************************************************************************* LISTA CARICATURA *********************************************************************************************/
CREATE OR REPLACE VIEW
Vista_Caricaturas AS
SELECT
Caricatura.idCaricatura,
Caricatura.Nombre,
REPLACE(Caricatura.OtrosNombres, 'AraÃ±a', 'Araña') AS NombreSecundario,
COUNT((Temporadas_Caricatura.idTemporada)) AS Temporadas,
SUM(Temporadas_Caricatura.Capitulos) AS Capitulos,
DATE_FORMAT((MIN(Temporadas_Caricatura.FechaInicio)), "%d / %M / %Y") As FechaInicio,
IF(Temporadas_Caricatura.FechaFin IS NULL AND Temporadas_Caricatura.FechaInicio IS NULL,' ' ,IF(MAX(Temporadas_Caricatura.FechaInicio > NOW()), DATE_FORMAT(MAX(Temporadas_Caricatura.FechaFin), "%d / %M / %Y"), (IF (MIN(Temporadas_Caricatura.FechaFin IS NOT NULL), (DATE_FORMAT((MAX(Temporadas_Caricatura.FechaFin)), "%d / %M / %Y")), (IF(NOW() < Temporadas_Caricatura.FechaInicio, " ", DATE_FORMAT(NOW(), "%d / %M / %Y") )))))) As FechaFin,
IF((AVG(Temporadas_Caricatura.Calificacion)) = 10, FORMAT((AVG(Temporadas_Caricatura.Calificacion)), 0),FORMAT((AVG(Temporadas_Caricatura.Calificacion)), 2)) AS Promedio,
Genero.Nombre AS Genero,
Productora.Nombre AS Productora,
Distribuidora.Nombre AS Distribuidora,
Director.NombreArtistico AS Director,
Caricatura.Portada
FROM Caricatura
LEFT JOIN Genero
ON Caricatura.Genero = Genero.idGenero
LEFT JOIN Productora
ON Caricatura.idProductora = Productora.idProductora
LEFT JOIN Productora AS Distribuidora
ON Caricatura.idDistribuidora = Distribuidora.idProductora
LEFT JOIN Director
ON Caricatura.idDirector = Director.idDirector
LEFT JOIN Temporadas_Caricatura
ON Temporadas_Caricatura.idCaricatura = Caricatura.idCaricatura
GROUP BY (Caricatura.idCaricatura)
ORDER BY Nombre;

/********************************************* TEMPORADAS *********************************************/
CREATE OR REPLACE VIEW
Vista_TemporadasCaricatura AS
SELECT
Temporadas_Caricatura.idTemporada,
Caricatura.idCaricatura,
temporadas_caricatura.FechaInicio AS Inicio,
Caricatura.Nombre AS Caricatura,
Temporadas_Caricatura.Nombre,
Temporadas_Caricatura.Capitulos,
IF(Calificacion = 10, FORMAT(Calificacion, 0),FORMAT(Calificacion, 2)) AS Calificacion,
IF(DATE_FORMAT(Duracion, "%H") = '00', DATE_FORMAT(Duracion, "%i min"), DATE_FORMAT(Duracion, "%Hh %im")) AS Duracion,
DATE_FORMAT(FechaInicio, "%d / %M / %Y") AS FechaInicio,
IFNULL((DATE_FORMAT(FechaFin, "%d / %M / %Y")), (DATE_FORMAT(NOW(), "%d / %M / %Y"))) AS FechaFin
FROM Temporadas_Caricatura
INNER JOIN Caricatura
ON Temporadas_Caricatura.idCaricatura = Caricatura.idCaricatura
ORDER BY Caricatura.Nombre;

/********************************************** DIRECTOR **********************************************/
CREATE OR REPLACE VIEW
Vista_Directores AS
SELECT
Director.idDirector,
Director.NombreArtistico,
Director.Nombre,
Director.Apellidos,
DATE_FORMAT(Director.FechaNacimiento, "%d / %M / %Y") AS FechaNacimiento,
DATE_FORMAT(Director.FechaDefuncion, "%d / %M / %Y") AS FechaDefuncion,
IF (Director.Sexo = 'H', 'Hombre', 'Mujer') AS Sexo,
IF (Director.FechaNacimiento >= Director.FechaDefuncion, "Fecha Invalida", TIMESTAMPDIFF(Year, Director.FechaNacimiento, (IFNULL(Director.FechaDefuncion, NOW())))) AS Edad,
FORMAT(Director.Estatura, 2) AS Estatura,
IF(IFNULL(Director.FechaDefuncion, 'ACTIVO') = 'ACTIVO', 'Activo', 'Inactivo') AS Estado,
CONCAT_WS(' - ', Pais.Nombre, Pais.Nacionalidad) AS Pais
FROM Director
INNER JOIN Pais
ON Director.Nacionalidad = Pais.idPais
ORDER BY Director.NombreArtistico;

/********************************************** USUARIO **********************************************/
CREATE OR REPLACE VIEW
Vista_Usuarios AS
SELECT
idUsuario,
Nombre,
Paterno,
Materno,
DATE_FORMAT(FechaNacimiento, "%d / %M / %Y") AS FechaNacimiento,
TIMESTAMPDIFF(Year, FechaNacimiento, NOW()) AS Edad,
CONCAT("+ (", LEFT(Celular, 3), ") ", MID(Celular, 4, 3), "-", MID(Celular, 7, 4)) AS Celular,
IF(Sexo = 'H', 'Hombre', 'Mujer') AS Sexo,
Correo,
Username,
IF((CONCAT_WS('-', YEAR(NOW()), MONTH(FechaNacimiento), DAY(FechaNacimiento))) > NOW(),
(DATEDIFF((CONVERT((CONCAT_WS('-', YEAR(NOW()), MONTH(FechaNacimiento), DAY(FechaNacimiento))), DATE)), NOW())),
(DATEDIFF((CONVERT((CONCAT_WS('-', YEAR(ADDDATE(CURDATE(), INTERVAL 1 YEAR)), MONTH(FechaNacimiento), DAY(FechaNacimiento))), DATE)), NOW()))) AS Cumple
FROM Usuario
ORDER BY Nombre;

/********************************************** Lista Serie **********************************************/
CREATE OR REPLACE VIEW
Vista_Series AS
SELECT
Series.idSerie,
Series.Nombre,
Series.OtrosNombres,
COUNT(Temporadas_Series.idTemporada) AS Temporadas,
SUM(Temporadas_Series.Capitulos) AS Capitulos,

DATE_FORMAT((MIN(Temporadas_Series.FechaInicio)), "%d / %M / %Y") As FechaInicio,
IF(Temporadas_Series.FechaFin IS NULL AND Temporadas_Series.FechaInicio IS NULL,' ' ,IF(MAX(Temporadas_Series.FechaInicio > NOW()), DATE_FORMAT(MAX(Temporadas_Series.FechaFin), "%d / %M / %Y"), (IF (MIN(Temporadas_Series.FechaFin IS NOT NULL), (DATE_FORMAT((MAX(Temporadas_Series.FechaFin)), "%d / %M / %Y")), (IF(NOW() < Temporadas_Series.FechaInicio, " ", DATE_FORMAT(NOW(), "%d / %M / %Y") )))))) As FechaFin,
IF(AVG(Temporadas_Series.Calificacion) = 10, FORMAT(AVG(Temporadas_Series.Calificacion), 0),FORMAT(AVG(Temporadas_Series.Calificacion), 2)) AS Promedio,
Genero.Nombre AS Genero,
Productora.Nombre AS Productora,
Distribuidora.Nombre AS Distribuidora,
Director.NombreArtistico AS Director,
Series.Portada
FROM Series
LEFT JOIN Genero
ON Genero.idGenero = Series.Genero
LEFT JOIN Productora
ON Series.idProductora = Productora.idProductora
LEFT JOIN Distribuidora
ON Series.idDistribuidora = Distribuidora.idDistribuidora
LEFT JOIN Director
ON Series.idDirector = Director.idDirector
LEFT JOIN Temporadas_Series
ON Temporadas_Series.idSerie = Series.idSerie
GROUP BY (Series.idSerie)
ORDER BY Series.Nombre;

/********************************************** Temporadas Serie **********************************************/
CREATE OR REPLACE VIEW
Vista_TemporadasSerie AS
SELECT
Temporadas_Series.idTemporada,
Series.idSerie,
Temporadas_Series.FechaInicio AS Inicio,
Series.Nombre AS Serie,
Temporadas_Series.Nombre,
Temporadas_Series.Capitulos,
IF(DATE_FORMAT(Duracion, "%H") = '00', DATE_FORMAT(Duracion, "%i min"), DATE_FORMAT(Duracion, "%Hh %im")) AS Duracion,
IF(Calificacion = 10, FORMAT(Calificacion, 0),FORMAT(Calificacion, 2)) AS Calificacion,
DATE_FORMAT(FechaInicio, "%d / %M / %Y") AS FechaInicio,
IFNULL((DATE_FORMAT(FechaFin, "%d / %M / %Y")), (DATE_FORMAT(NOW(), "%d / %M / %Y"))) AS FechaFin
FROM Temporadas_Series
INNER JOIN Series
ON Temporadas_Series.idSerie = Series.idSerie
ORDER BY Series.Nombre;

/********************************************** PELICULAS **********************************************/
CREATE OR REPLACE VIEW
Vista_Peliculas AS
SELECT
Peliculas.idPelicula,
Peliculas.Nombre,
REPLACE(Peliculas.OtrosNombres, 'Ã±', 'ñ') AS NombreSecundario,
Productora.Nombre AS Productora,
Distribuidora.Nombre AS Distribuidora,
DATE_FORMAT(Peliculas.Duracion, "%Hh %im") AS Duracion,
Genero.Nombre AS Genero,
Peliculas.Tipo,
Clasificacion.Nombre AS Clasificacion,
DATE_FORMAT(Estreno, "%d / %M / %Y") AS Estreno,
DATE_FORMAT(EstrenoMexico, "%d / %M / %Y") AS EstrenoMexico,
IF(Peliculas.Calificacion = 10, FORMAT(Peliculas.Calificacion, 0),FORMAT(Peliculas.Calificacion, 1)) AS Calificacion,
IF(Peliculas.Estreno IS NULL, 'Próximamente', TIMESTAMPDIFF(Year, Peliculas.Estreno, NOW())) AS Anios,
Director.NombreArtistico AS Director,
Peliculas.Portada
FROM Peliculas
INNER JOIN Productora
ON Productora.idProductora = Peliculas.idProductora
INNER JOIN Distribuidora
ON Distribuidora.idDistribuidora = Peliculas.idDistribuidora
INNER JOIN Genero
ON Genero.idGenero = Peliculas.Genero
INNER JOIN Clasificacion
ON Clasificacion.idClasificacion = Peliculas.Clasificacion
INNER JOIN Director
ON Peliculas.idDirector = Director.idDirector
ORDER BY Nombre;

/* ------------------------------------------------------------------------------------- TRIGGERS ------------------------------------------------------------------------------------- */
/********************************************** Usuario **********************************************/
CREATE OR REPLACE VIEW
Vista_Cambios_Usuario AS
SELECT
id,
Nombre,
Paterno,
Materno,
DATE_FORMAT(FechaNacimiento, "%d / %M / %Y") AS FechaNacimiento,
Celular,
IF(Sexo = 'H', 'Hombre', 'Mujer') AS Sexo,
Correo,
Username,
Password,
DATE_FORMAT(Modificado, "%d / %M / %Y") AS Modificado,
Proceso,
Usuario,
idUsuario
FROM Auditoria_Usuario;

/********************************************** Anime **********************************************/
CREATE OR REPLACE VIEW
Vista_Cambios_Anime AS
SELECT
id,
Nombre,
OtrosNombres,
Genero,
Usuario,
DATE_FORMAT(Modificado, "%d / %M / %Y") AS Modificado,
Proceso,
idAnime
FROM Auditoria_Anime;

/********************************************** Temporada Anime **********************************************/
CREATE OR REPLACE VIEW
Vista_Cambios_Temporadas_Anime AS
SELECT
Auditoria_Temporadas_Anime.id,
Anime.Nombre AS Anime,
Auditoria_Temporadas_Anime.Nombre,
Auditoria_Temporadas_Anime.OtrosNombres,
Auditoria_Temporadas_Anime.Capitulos,
IF(DATE_FORMAT(Auditoria_Temporadas_Anime.Duracion, "%H") = '00', DATE_FORMAT(Auditoria_Temporadas_Anime.Duracion, "%i min"), DATE_FORMAT(Auditoria_Temporadas_Anime.Duracion, "%Hh %im")) AS Duracion,
Auditoria_Temporadas_Anime.Idioma,
Temporadas_Emision.Nombre AS Emision,
DATE_FORMAT(Auditoria_Temporadas_Anime.FechaInicio, "%d / %M / %Y") AS FechaInicio,
DATE_FORMAT(Auditoria_Temporadas_Anime.FechaFin, "%d / %M / %Y") AS FechaFin,
EstudioAnimacion.Nombre AS Estudio,
IF(Calificacion = 10, FORMAT(Calificacion, 0),FORMAT(Calificacion, 2)) AS Calificacion,
Auditoria_Temporadas_Anime.Portada,
Auditoria_Temporadas_Anime.Usuario,
DATE_FORMAT(Modificado, "%d / %M / %Y") AS Modificado,
Auditoria_Temporadas_Anime.Proceso,
Auditoria_Temporadas_Anime.idTemporada
FROM Auditoria_Temporadas_Anime
INNER JOIN Anime
ON Auditoria_Temporadas_Anime.idAnime = Anime.idAnime
INNER JOIN Temporadas_Emision
ON Auditoria_Temporadas_Anime.idTemporadaEmision = Temporadas_Emision.idTemporada
INNER JOIN EstudioAnimacion
ON Auditoria_Temporadas_Anime.idEstudio = EstudioAnimacion.idEstudio;

/********************************************** Temporada Emision **********************************************/
CREATE OR REPLACE VIEW
Vista_Cambios_Temporadas_Emision AS
SELECT
id,
Nombre,
DATE_FORMAT(FechaInicio, "%d / %M / %Y") AS FechaInicio,
DATE_FORMAT(FechaFin, "%d / %M / %Y") AS FechaFin,
Usuario,
DATE_FORMAT(Modificado, "%d / %M / %Y") AS Modificado,
Proceso,
idTemporada
FROM Auditoria_Temporadas_Emision;


/********************************************** Caricatura **********************************************/
CREATE OR REPLACE VIEW
Vista_Cambios_Caricatura AS
SELECT
Auditoria_Caricatura.id,
Auditoria_Caricatura.Nombre,
Auditoria_Caricatura.OtrosNombres AS NombreSecundario,
Genero.Nombre AS Genero,
Productora.Nombre AS Productora,
Distribuidora.Nombre AS Distribuidora,
Director.NombreArtistico AS Director,
Auditoria_Caricatura.Portada,
Auditoria_Caricatura.Usuario,
DATE_FORMAT(Modificado, "%d / %M / %Y") AS Modificado,
Auditoria_Caricatura.Proceso,
Auditoria_Caricatura.idCaricatura
FROM Auditoria_Caricatura
INNER JOIN Genero
ON Auditoria_Caricatura.Genero = Genero.idGenero
INNER JOIN Productora
ON Auditoria_Caricatura.idProductora = Productora.idProductora
INNER JOIN Productora AS Distribuidora
ON Auditoria_Caricatura.idDistribuidora = Distribuidora.idProductora
INNER JOIN Director
ON Auditoria_Caricatura.idDirector = Director.idDirector;

/********************************************** Temporadas Caricatura **********************************************/
CREATE OR REPLACE VIEW
Vista_Cambios_Temporadas_Caricatura AS
SELECT 
Auditoria_Temporadas_Caricatura.id,
Caricatura.Nombre AS Caricatura,
Auditoria_Temporadas_Caricatura.Nombre,
Auditoria_Temporadas_Caricatura.Capitulos,
IF(DATE_FORMAT(Duracion, "%H") = '00', DATE_FORMAT(Duracion, "%i min"), DATE_FORMAT(Duracion, "%Hh %im")) AS Duracion,
IF(Calificacion = 10, FORMAT(Calificacion, 0),FORMAT(Calificacion, 2)) AS Calificacion,
DATE_FORMAT(FechaInicio, "%d / %M / %Y") AS FechaInicio,
DATE_FORMAT(FechaFin, "%d / %M / %Y") AS FechaFin,
Auditoria_Temporadas_Caricatura.Usuario,
DATE_FORMAT(Modificado, "%d / %M / %Y") AS Modificado,
Auditoria_Temporadas_Caricatura.Proceso,
Auditoria_Temporadas_Caricatura.idTemporada
FROM Auditoria_Temporadas_Caricatura
INNER JOIN Caricatura
ON Auditoria_Temporadas_Caricatura.idCaricatura = Caricatura.idCaricatura;

/********************************************** Director **********************************************/
CREATE OR REPLACE VIEW
Vista_Cambios_Director AS
SELECT
Auditoria_Director.id,
Auditoria_Director.Nombre,
Auditoria_Director.Apellidos,
Auditoria_Director.NombreArtistico,
DATE_FORMAT(FechaNacimiento, "%d / %M / %Y") AS FechaNacimiento,
DATE_FORMAT(FechaDefuncion, "%d / %M / %Y") AS FechaDefuncion,
IF (Auditoria_Director.Sexo = 'H', 'Hombre', 'Mujer') AS Sexo,
FORMAT(Auditoria_Director.Estatura, 2) AS Estatura,
Pais.Nombre AS Pais,
Auditoria_Director.Usuario,
DATE_FORMAT(Modificado, "%d / %M / %Y") AS Modificado,
Auditoria_Director.Proceso,
Auditoria_Director.idDirector
FROM Auditoria_Director
INNER JOIN Pais
ON Auditoria_Director.Nacionalidad = Pais.idPais;

/********************************************** Pelicula **********************************************/
CREATE OR REPLACE VIEW
Vista_Cambios_Pelicula AS
SELECT 
Auditoria_Peliculas.id,
Auditoria_Peliculas.Nombre,
REPLACE(Auditoria_Peliculas.OtrosNombres, 'Ã±', 'ñ') AS NombreSecundario,
Productora.Nombre as Productora,
Distribuidora.Nombre as Distribuidora,
DATE_FORMAT(Auditoria_Peliculas.Duracion, "%Hh %im") AS Duracion,
Genero.Nombre AS Genero,
Auditoria_Peliculas.Tipo,
Clasificacion.Nombre AS Clasificacion,
DATE_FORMAT(Estreno, "%d / %M / %Y") AS Estreno,
DATE_FORMAT(EstrenoMexico, "%d / %M / %Y") AS EstrenoMexico,
IF(Auditoria_Peliculas.Calificacion = 10, FORMAT(Auditoria_Peliculas.Calificacion, 0),FORMAT(Auditoria_Peliculas.Calificacion, 1)) AS Calificacion,
Director.NombreArtistico AS Director,
Auditoria_Peliculas.Portada,
Auditoria_Peliculas.Usuario,
DATE_FORMAT(Modificado, "%d / %M / %Y") AS Modificado,
Auditoria_Peliculas.Proceso,
Auditoria_Peliculas.idPelicula
FROM Auditoria_Peliculas
INNER JOIN Productora
ON Productora.idProductora = Auditoria_Peliculas.idProductora
INNER JOIN Distribuidora
ON Distribuidora.idDistribuidora = Auditoria_Peliculas.idDistribuidora
INNER JOIN Genero
ON Genero.idGenero = Auditoria_Peliculas.Genero
INNER JOIN Clasificacion
ON Clasificacion.idClasificacion = Auditoria_Peliculas.Clasificacion
INNER JOIN Director
ON Auditoria_Peliculas.idDirector = Director.idDirector;

/********************************************** Serie **********************************************/
CREATE OR REPLACE VIEW
Vista_Cambios_Serie AS
SELECT 
Auditoria_Series.id,
Auditoria_Series.Nombre,
Auditoria_Series.OtrosNombres,
Genero.Nombre AS Genero,
Productora.Nombre AS Productora,
Distribuidora.Nombre AS Distribuidora,
Director.NombreArtistico AS Director,
Auditoria_Series.Portada,
Auditoria_Series.Usuario,
DATE_FORMAT(Modificado, "%d / %M / %Y") AS Modificado,
Auditoria_Series.Proceso,
Auditoria_Series.idSerie
FROM Auditoria_Series
INNER JOIN Genero
ON Genero.idGenero = Auditoria_Series.Genero
INNER JOIN Productora
ON Auditoria_Series.idProductora = Productora.idProductora
INNER JOIN Productora AS Distribuidora
ON Auditoria_Series.idDistribuidora = Distribuidora.idProductora
INNER JOIN Director
ON Auditoria_Series.idDirector = Director.idDirector;

/********************************************** Temporada Serie **********************************************/
CREATE OR REPLACE VIEW
Vista_Cambios_Temporadas_Serie AS
SELECT 
Auditoria_Temporadas_Series.id,
Series.Nombre AS Serie,
Auditoria_Temporadas_Series.Nombre,
Auditoria_Temporadas_Series.Capitulos,
TIME_FORMAT(Duracion, "%i min") AS Duracion,
IF(Calificacion = 10, FORMAT(Calificacion, 0),FORMAT(Calificacion, 2)) AS Calificacion,
DATE_FORMAT(FechaInicio, "%d / %M / %Y") AS FechaInicio,
DATE_FORMAT(FechaFin, "%d / %M / %Y") AS FechaFin,
Auditoria_Temporadas_Series.Usuario,
DATE_FORMAT(Modificado, "%d / %M / %Y") AS Modificado,
Auditoria_Temporadas_Series.Proceso,
Auditoria_Temporadas_Series.idTemporada
FROM Auditoria_Temporadas_Series
INNER JOIN Series
ON Auditoria_Temporadas_Series.idSerie = Series.idSerie;