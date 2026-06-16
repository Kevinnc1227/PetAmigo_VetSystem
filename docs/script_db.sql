CREATE DATABASE IF NOT EXISTS PetAmigo_VetSystem;
USE PetAmigo_VetSystem;

-- 1. Tabela Cliente
CREATE TABLE IF NOT EXISTS cliente (
    cpf VARCHAR(14) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    endereco VARCHAR(255),
    telefone VARCHAR(20)
);

-- 2. Tabela Veterinário
CREATE TABLE IF NOT EXISTS veterinario (
    crmv VARCHAR(20) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    endereco VARCHAR(255),
    telefone VARCHAR(20),
    especialidade VARCHAR(100)
);

-- 3. Tabela Animal
CREATE TABLE IF NOT EXISTS Animal (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    especie VARCHAR(50) NOT NULL,
    cpfCliente VARCHAR(14),
    CONSTRAINT fk_animal_cliente FOREIGN KEY (cpfCliente) REFERENCES cliente(cpf) ON DELETE SET NULL ON UPDATE CASCADE
);

-- 4. Tabela Prontuário
CREATE TABLE IF NOT EXISTS Prontuario (
    idAnimal INT PRIMARY KEY,
    historico TEXT,
    observacoes TEXT,
    peso FLOAT,
    CONSTRAINT fk_prontuario_animal FOREIGN KEY (idAnimal) REFERENCES Animal(id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- 5. Tabela Pagamento
CREATE TABLE IF NOT EXISTS pagamento (
    valorTotal FLOAT,
    status VARCHAR(20) NOT NULL
);

-- 6. Tabela Consulta
CREATE TABLE IF NOT EXISTS consulta (
    data_consulta VARCHAR(20) NOT NULL,
    hora_consulta VARCHAR(20) NOT NULL,
    animal_id INT NOT NULL,
    veterinario_crmv VARCHAR(20) NOT NULL,
    valor FLOAT,
    PRIMARY KEY (data_consulta, hora_consulta, animal_id),
    CONSTRAINT fk_consulta_animal FOREIGN KEY (animal_id) REFERENCES Animal(id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_consulta_vet FOREIGN KEY (veterinario_crmv) REFERENCES veterinario(crmv) ON DELETE CASCADE ON UPDATE CASCADE
);
