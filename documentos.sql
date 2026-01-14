-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 27-Nov-2024 às 18:24
-- Versão do servidor: 10.4.24-MariaDB
-- versão do PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `documentos`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `documentos`
--

CREATE TABLE `documentos` (
  `doc_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `categ_id` int(11) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `file_path` varchar(255) NOT NULL,
  `data_criacao_doc` datetime DEFAULT current_timestamp(),
  `data_atualizacao_doc` datetime DEFAULT current_timestamp(),
  `status` enum('Ativo','Inativo','Arquivado') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `documentos`
--

INSERT INTO `documentos` (`doc_id`, `user_id`, `categ_id`, `titulo`, `file_path`, `data_criacao_doc`, `data_atualizacao_doc`, `status`) VALUES
(1, 1, 1, 'Relatario Financeiro 2024', '/docs/relatorio_financeiro_2024.pdf', '2024-11-27 13:40:17', '2024-11-27 13:40:17', 'Ativo'),
(2, 2, 2, 'Politica de Contrato', '/docs/politica_contratacao.pdf', '2024-11-27 13:40:17', '2024-11-27 13:40:17', 'Ativo'),
(3, 3, 3, 'Plano de Projeto X', '/docs/plano_projeto_x.pdf', '2024-11-27 13:40:17', '2024-11-27 13:40:17', 'Arquivado');

-- --------------------------------------------------------

--
-- Estrutura da tabela `doc_categoria`
--

CREATE TABLE `doc_categoria` (
  `categ_id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `descricao` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `doc_categoria`
--

INSERT INTO `doc_categoria` (`categ_id`, `nome`, `descricao`) VALUES
(1, 'Financeiro', 'Documentos relacionados a financas e contabilidade'),
(2, 'Recursos Humanos', 'Documentos relacionados a RH e funcionarios'),
(3, 'Projetos', 'Documentos sobre planejamento e execucao de projetos');

-- --------------------------------------------------------

--
-- Estrutura da tabela `permissoes_doc`
--

CREATE TABLE `permissoes_doc` (
  `perm_id` int(11) NOT NULL,
  `doc_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `permission_type` enum('Ver','Editar','Deletar') NOT NULL,
  `data_criacao_perm` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `permissoes_doc`
--

INSERT INTO `permissoes_doc` (`perm_id`, `doc_id`, `user_id`, `permission_type`, `data_criacao_perm`) VALUES
(1, 1, 1, 'Editar', '2024-11-27 13:43:15'),
(2, 2, 2, 'Ver', '2024-11-27 13:43:15'),
(3, 3, 3, 'Deletar', '2024-11-27 13:43:15');

-- --------------------------------------------------------

--
-- Estrutura da tabela `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `nome` varchar(150) NOT NULL,
  `email` varchar(320) NOT NULL,
  `senha` varchar(16) NOT NULL,
  `cargo` enum('Administrador','Editor','Espectador') NOT NULL,
  `data_criacao` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `users`
--

INSERT INTO `users` (`user_id`, `nome`, `email`, `senha`, `cargo`, `data_criacao`) VALUES
(1, 'Ana Souza', 'ana.souza@example.com', 'senha123', 'Administrador', '2024-11-27 13:40:17'),
(2, 'Carlos Lima', 'carlos.lima@example.com', 'senha456', 'Editor', '2024-11-27 13:40:17'),
(3, 'Mariana Torres', 'mariana.torres@example.com', 'senha789', 'Espectador', '2024-11-27 13:40:17');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `documentos`
--
ALTER TABLE `documentos`
  ADD PRIMARY KEY (`doc_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `categ_id` (`categ_id`);

--
-- Índices para tabela `doc_categoria`
--
ALTER TABLE `doc_categoria`
  ADD PRIMARY KEY (`categ_id`);

--
-- Índices para tabela `permissoes_doc`
--
ALTER TABLE `permissoes_doc`
  ADD PRIMARY KEY (`perm_id`),
  ADD KEY `doc_id` (`doc_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Índices para tabela `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `documentos`
--
ALTER TABLE `documentos`
  MODIFY `doc_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `doc_categoria`
--
ALTER TABLE `doc_categoria`
  MODIFY `categ_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `permissoes_doc`
--
ALTER TABLE `permissoes_doc`
  MODIFY `perm_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `documentos`
--
ALTER TABLE `documentos`
  ADD CONSTRAINT `documentos_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `documentos_ibfk_2` FOREIGN KEY (`categ_id`) REFERENCES `doc_categoria` (`categ_id`) ON DELETE CASCADE;

--
-- Limitadores para a tabela `permissoes_doc`
--
ALTER TABLE `permissoes_doc`
  ADD CONSTRAINT `permissoes_doc_ibfk_1` FOREIGN KEY (`doc_id`) REFERENCES `documentos` (`doc_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `permissoes_doc_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
