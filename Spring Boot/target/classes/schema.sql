CREATE TABLE project (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(255) NOT NULL,
                         descricao TEXT,
                         categoria VARCHAR(255),
                         status VARCHAR(255)
);