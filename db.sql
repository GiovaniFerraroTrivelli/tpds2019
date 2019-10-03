CREATE TABLE marcas(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(20)
);

CREATE TABLE modelos(
    id INT NOT NULL AUTO_INCREMENT,
    id_marca INT,
    nombre VARCHAR(30),
    CONSTRAINT modelos_pk FOREIGN KEY(id_marca) REFERENCES marcas(id),
    PRIMARY KEY(id, id_marca)
);
