-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 24-Jan-2018 às 17:37
-- Versão do servidor: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `estacionamento`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes`
--

CREATE TABLE IF NOT EXISTS `clientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `telefone` varchar(15) DEFAULT NULL,
  `endereco` varchar(100) DEFAULT NULL,
  `nome` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `clientes_tem_veiculos`
--

CREATE TABLE IF NOT EXISTS `clientes_tem_veiculos` (
  `cliente_fk` int(11) NOT NULL,
  `veiculo_fk` int(11) NOT NULL,
  PRIMARY KEY (`cliente_fk`,`veiculo_fk`),
  KEY `veiculo_fk` (`veiculo_fk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente_fisico`
--

CREATE TABLE IF NOT EXISTS `cliente_fisico` (
  `cliente_fk` int(11) NOT NULL,
  `cpf` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`cliente_fk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente_juridico`
--

CREATE TABLE IF NOT EXISTS `cliente_juridico` (
  `cliente_fk` int(11) NOT NULL,
  `cnpj` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`cliente_fk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `estacionados`
--

CREATE TABLE IF NOT EXISTS `estacionados` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `entrada` datetime DEFAULT NULL,
  `saida` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `estacionado_gera_transacao`
--

CREATE TABLE IF NOT EXISTS `estacionado_gera_transacao` (
  `transacao_fk` int(11) NOT NULL,
  `estacionado_fk` int(11) NOT NULL,
  PRIMARY KEY (`estacionado_fk`,`transacao_fk`),
  KEY `transacao_fk` (`transacao_fk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `hora`
--

CREATE TABLE IF NOT EXISTS `hora` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `valor` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `transacoes`
--

CREATE TABLE IF NOT EXISTS `transacoes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` int(11) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) DEFAULT NULL,
  `senha` varchar(20) DEFAULT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `nivel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

--
-- Extraindo dados da tabela `usuarios`
--

INSERT INTO `usuarios` (`id`, `login`, `senha`, `nome`, `nivel`) VALUES
(19, 'admin', 'admin123', 'Administrador', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `veiculos`
--

CREATE TABLE IF NOT EXISTS `veiculos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `placa` varchar(7) NOT NULL,
  `ano` date DEFAULT NULL,
  `modelo` varchar(30) DEFAULT NULL,
  `marca` varchar(30) DEFAULT NULL,
  `cor` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `veiculo_esta_estacionado`
--

CREATE TABLE IF NOT EXISTS `veiculo_esta_estacionado` (
  `estacionado_fk` int(11) NOT NULL,
  `veiculo_fk` int(11) NOT NULL,
  PRIMARY KEY (`estacionado_fk`,`veiculo_fk`),
  KEY `veiculo_fk` (`veiculo_fk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `clientes_tem_veiculos`
--
ALTER TABLE `clientes_tem_veiculos`
  ADD CONSTRAINT `Clientes_tem_veiculos_ibfk_1` FOREIGN KEY (`cliente_fk`) REFERENCES `clientes` (`id`),
  ADD CONSTRAINT `Clientes_tem_veiculos_ibfk_2` FOREIGN KEY (`veiculo_fk`) REFERENCES `veiculos` (`id`);

--
-- Limitadores para a tabela `cliente_fisico`
--
ALTER TABLE `cliente_fisico`
  ADD CONSTRAINT `Cliente_fisico_ibfk_1` FOREIGN KEY (`cliente_fk`) REFERENCES `clientes` (`id`);

--
-- Limitadores para a tabela `cliente_juridico`
--
ALTER TABLE `cliente_juridico`
  ADD CONSTRAINT `Cliente_juridico_ibfk_1` FOREIGN KEY (`cliente_fk`) REFERENCES `clientes` (`id`);

--
-- Limitadores para a tabela `estacionado_gera_transacao`
--
ALTER TABLE `estacionado_gera_transacao`
  ADD CONSTRAINT `Estacionado_gera_transacao_ibfk_1` FOREIGN KEY (`estacionado_fk`) REFERENCES `estacionados` (`id`),
  ADD CONSTRAINT `Estacionado_gera_transacao_ibfk_2` FOREIGN KEY (`transacao_fk`) REFERENCES `transacoes` (`id`);

--
-- Limitadores para a tabela `veiculo_esta_estacionado`
--
ALTER TABLE `veiculo_esta_estacionado`
  ADD CONSTRAINT `Veiculo_esta_estacionado_ibfk_1` FOREIGN KEY (`estacionado_fk`) REFERENCES `estacionados` (`id`),
  ADD CONSTRAINT `Veiculo_esta_estacionado_ibfk_2` FOREIGN KEY (`veiculo_fk`) REFERENCES `veiculos` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
