CREATE TABLE city (
    code INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    about VARCHAR(255) NOT NULL,
    PRIMARY KEY (code)
);

INSERT INTO city (name, about)
VALUES ('Vinhedo', 'Vinhedo é um municipio situado no estado de são paulo e tem um dos maiores IDHs do Brasil: https://pt.wikipedia.org/wiki/Vinhedo');

CREATE TABLE city_avaliation (
    code INT NOT NULL AUTO_INCREMENT,
    school_avaliation VARCHAR(100) NOT NULL,
    security_avaliation VARCHAR(100) NOT NULL,
    health_avaliation VARCHAR(100) NOT NULL,
    city_id INT,
    PRIMARY KEY (code),
    FOREIGN KEY (city_id) REFERENCES city (code)
);

INSERT INTO city_avaliation (school_avaliation, security_avaliation, health_avaliation, city_id)
VALUES ('As escolas são ótimas, os professores são eficientes e bem organizados',
        'Me sinto bem segura, posso andar sozinha e com meus pertences sem me preocupar',
        'O sistema de saúde pode melhorar um pouco mais, mas já nos oferece bastante coisa',
        1);

CREATE TABLE users (
    code INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(25) NOT NULL,
    PRIMARY KEY (code)
);

INSERT INTO users (name) VALUES ('admin');
INSERT INTO users (name) VALUES ('user');
