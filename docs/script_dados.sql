USE PetAmigo_VetSystem;

-- Clientes
INSERT INTO cliente (cpf, nome, email, endereco, telefone) VALUES
('111.111.111-11', 'Carlos Silva', 'carlos.silva@email.com', 'Rua das Flores, 123', '(47) 99999-1111'),
('222.222.222-22', 'Mariana Santos', 'mariana.santos@email.com', 'Av. Central, 456', '(47) 98888-2222'),
('333.333.333-33', 'Roberto Oliveira', 'roberto.o@email.com', 'Rua São Paulo, 789', '(47) 97777-3333');

-- Veterinários
INSERT INTO veterinario (crmv, nome, email, endereco, telefone, especialidade) VALUES
('CRMV-SC 12345', 'Dra. Ana Paula', 'ana.paula@petamigo.com', 'Rua XV de Novembro, 100', '(47) 3322-1111', 'Pequenos Animais'),
('CRMV-SC 67890', 'Dr. Bruno Costa', 'bruno.costa@petamigo.com', 'Rua Joinville, 250', '(47) 3322-2222', 'Cirurgia');

-- Animais
INSERT INTO Animal (nome, especie, cpfCliente) VALUES
('Mel', 'CACHORRO', '111.111.111-11'),
('Felix', 'GATO', '222.222.222-22'),
('Thor', 'CACHORRO', '333.333.333-33'),
('Luna', 'GATO', '111.111.111-11');

-- Prontuários
INSERT INTO Prontuario (idAnimal, historico, observacoes, peso) VALUES
(1, 'Primeira consulta de rotina.', 'Animal saudável, peso adequado.', 8.5),
(2, 'Tratamento de otite externa.', 'Otite curada, retornar se houver coceira.', 4.2),
(3, 'Check-up anual.', 'Apresenta tártaro leve nos dentes.', 25.0);

-- Pagamentos
INSERT INTO pagamento (valorTotal, status) VALUES
(150.00, 'PAGO'),
(120.00, 'PENDENTE'),
(200.00, 'PAGO');

-- Consultas
INSERT INTO consulta (data_consulta, hora_consulta, animal_id, veterinario_crmv, valor) VALUES
('15/06/2026', '14:00', 1, 'CRMV-SC 12345', 150.00),
('16/06/2026', '09:30', 2, 'CRMV-SC 67890', 120.00),
('17/06/2026', '16:15', 3, 'CRMV-SC 12345', 200.00);
