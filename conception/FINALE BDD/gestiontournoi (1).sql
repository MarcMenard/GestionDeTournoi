-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 25 Mai 2017 à 04:52
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gestiontournoi`
--

-- --------------------------------------------------------

--
-- Structure de la table `arbitre`
--

CREATE TABLE `arbitre` (
  `id` int(11) NOT NULL,
  `nom` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `motdepasse` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `arbitre`
--

INSERT INTO `arbitre` (`id`, `nom`, `motdepasse`) VALUES
(1, 'Jean Jacques', '123'),
(2, 'Théo', '561564'),
(3, 'Armel', '56848');

-- --------------------------------------------------------

--
-- Structure de la table `equipe`
--

CREATE TABLE `equipe` (
  `id` int(11) NOT NULL,
  `nom` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `numero` int(11) NOT NULL,
  `info` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '.'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `equipe`
--

INSERT INTO `equipe` (`id`, `nom`, `numero`, `info`) VALUES
(6, 'FC Bati Mod', 1, '.'),
(7, 'Atelier Decoupage', 2, '.'),
(8, 'La Juventus de Bourin', 3, '.'),
(9, 'Atelier Resinade', 4, '.'),
(10, 'The Best Family', 5, '.'),
(11, 'FCBB FC Brain Bouaine', 6, '.'),
(12, 'La Team a Francois', 7, '.'),
(13, 'Attendez nous on est au bar', 8, '.'),
(14, 'Les 2 Fermes', 9, '.'),
(15, 'Les Veterans du Dimanche', 10, '.'),
(16, 'Les Americains', 11, '.'),
(17, 'Bayern Leverkusec', 12, '.'),
(18, 'Cocktail Familial', 13, '.'),
(19, 'Brulots do Brazil', 14, '.'),
(20, 'FC Loisirs', 15, '.'),
(21, 'FC Roseaux', 16, '.'),
(22, 'Les 6 burnes en Concubinage', 17, '.'),
(23, 'J ai l air d un con au micro', 18, '.'),
(24, 'La Main au Panier', 19, '.'),
(25, 'Les 100 Alcools', 20, '.'),
(26, 'Les En Hervouet', 21, '.'),
(27, 'Les Oreos', 22, '.'),
(28, 'Les Ptits Verts', 23, '.'),
(29, 'Swim Beach Boy s', 24, '.'),
(30, 'FC Ruble', 25, '.'),
(31, 'Frollon Sauvage', 26, '.'),
(32, 'Les Amateurs', 27, '.'),
(33, 'Equipe 28', 28, '.'),
(34, 'Sud Loire', 29, '.'),
(35, 'La Neo Newteam', 30, '.'),
(36, 'PSV Heineken', 31, '.'),
(37, 'Equipe 32', 32, '.'),
(50, '4564', 64564564, '.'),
(51, 'fdsfdsfds', 54564, '.'),
(52, 'fgfdgdfs', 4564, '.');

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

CREATE TABLE `groupe` (
  `id` int(11) NOT NULL,
  `phase_id` int(11) DEFAULT NULL,
  `nom` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `numero` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `groupe`
--

INSERT INTO `groupe` (`id`, `phase_id`, `nom`, `numero`) VALUES
(1, 3, 'Groupe 1', 1),
(2, 3, 'Groupe 2', 2),
(3, 3, 'Groupe 3', 3),
(4, 3, 'Groupe 4', 4),
(5, 3, 'Groupe 5', 5),
(6, 3, 'Groupe 6', 6),
(7, 3, 'Groupe 7', 7),
(8, 3, 'Groupe 8', 8);

-- --------------------------------------------------------

--
-- Structure de la table `groupe_equipe`
--

CREATE TABLE `groupe_equipe` (
  `groupe_id` int(11) NOT NULL,
  `equipe_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `groupe_equipe`
--

INSERT INTO `groupe_equipe` (`groupe_id`, `equipe_id`) VALUES
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(2, 10),
(2, 11),
(2, 12),
(2, 13),
(3, 14),
(3, 15),
(3, 16),
(3, 17),
(4, 18),
(4, 19),
(4, 20),
(4, 21),
(5, 22),
(5, 23),
(5, 24),
(5, 25),
(6, 26),
(6, 27),
(6, 28),
(6, 29),
(7, 30),
(7, 31),
(7, 32),
(7, 33),
(8, 34),
(8, 35),
(8, 36),
(8, 37);

-- --------------------------------------------------------

--
-- Structure de la table `phase`
--

CREATE TABLE `phase` (
  `id` int(11) NOT NULL,
  `nom` varchar(45) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `phase`
--

INSERT INTO `phase` (`id`, `nom`) VALUES
(1, 'Phase principale'),
(2, 'Phase consolante'),
(3, 'Phase de groupe');

-- --------------------------------------------------------

--
-- Structure de la table `phase_groupe`
--

CREATE TABLE `phase_groupe` (
  `phase_id` int(11) NOT NULL,
  `groupe_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `rencontre`
--

CREATE TABLE `rencontre` (
  `id` int(11) NOT NULL,
  `terrain` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'A',
  `rang_planning` int(11) NOT NULL,
  `heure` time NOT NULL,
  `type_match` int(11) NOT NULL,
  `score1_id` int(11) DEFAULT NULL,
  `score2_id` int(11) DEFAULT NULL,
  `groupe_id` int(11) NOT NULL,
  `arbitre_id` int(11) NOT NULL,
  `jouee` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `rencontre`
--

INSERT INTO `rencontre` (`id`, `terrain`, `rang_planning`, `heure`, `type_match`, `score1_id`, `score2_id`, `groupe_id`, `arbitre_id`, `jouee`) VALUES
(1, 'Terrain A', 1, '08:45:00', 0, 1, 2, 1, 1, 0),
(2, 'Terrain B', 1, '08:45:00', 0, 3, 4, 1, 2, 0),
(3, 'Terrain A', 1, '08:55:00', 0, 5, 6, 2, 2, 0),
(4, 'Terrain B', 1, '08:55:00', 0, 7, 8, 2, 3, 0),
(5, 'Terrain A', 1, '09:05:00', 0, 9, 10, 3, 3, 0),
(6, 'Terrain B', 1, '09:05:00', 0, 11, 12, 3, 3, 0),
(7, 'Terrain A', 1, '09:15:00', 0, 13, 14, 4, 1, 0),
(8, 'Terrain B', 1, '09:15:00', 0, 15, 16, 4, 1, 0),
(9, 'Terrain A', 1, '10:05:00', 0, 17, 18, 1, 1, 0),
(10, 'Terrain B', 1, '10:05:00', 0, 19, 20, 1, 1, 0),
(11, 'Terrain A', 1, '10:15:00', 0, 21, 22, 2, 1, 0),
(12, 'Terrain B', 1, '10:15:00', 0, 23, 24, 2, 1, 0),
(13, 'Terrain A', 1, '10:25:00', 0, 25, 26, 3, 1, 0),
(14, 'Terrain B', 1, '10:25:00', 0, 27, 28, 3, 1, 0),
(15, 'Terrain A', 1, '10:35:00', 0, 29, 30, 4, 1, 0),
(16, 'Terrain B', 1, '10:35:00', 0, 31, 32, 4, 1, 0),
(17, 'Terrain A', 1, '11:25:00', 0, 33, 34, 1, 1, 0),
(18, 'Terrain A', 1, '11:15:00', 0, 86, 92, 8, 2, 0),
(19, 'Terrain A', 1, '11:35:00', 0, 37, 38, 2, 1, 0),
(20, 'Terrain B', 1, '11:35:00', 0, 39, 40, 2, 1, 0),
(21, 'Terrain A', 1, '11:45:00', 0, 41, 42, 3, 1, 0),
(22, 'Terrain B', 1, '11:45:00', 0, 43, 44, 3, 1, 0),
(23, 'Terrain A', 1, '11:55:00', 0, 45, 46, 4, 1, 0),
(24, 'Terrain B', 1, '11:55:00', 0, 47, 48, 4, 2, 0),
(25, 'Terrain B', 1, '11:25:00', 0, 35, 36, 1, 2, 0),
(26, 'Terrain A', 1, '09:25:00', 0, 49, 52, 5, 2, 0),
(27, 'Terrain B', 1, '09:25:00', 0, 55, 58, 5, 3, 0),
(28, 'Terrain A', 1, '09:35:00', 0, 61, 64, 6, 1, 0),
(29, 'Terrain B', 1, '09:35:00', 0, 67, 70, 6, 3, 0),
(30, 'Terrain A', 1, '09:45:00', 0, 73, 76, 7, 1, 0),
(31, 'Terrain B', 1, '09:45:00', 0, 79, 82, 7, 2, 0),
(32, 'Terrain A', 1, '09:55:00', 0, 85, 88, 8, 3, 0),
(33, 'Terrain B', 1, '09:55:00', 0, 91, 94, 8, 1, 0),
(34, 'Terrain A', 1, '10:45:00', 0, 50, 56, 5, 2, 0),
(35, 'Terrain B', 1, '10:45:00', 0, 53, 59, 5, 3, 0),
(36, 'Terrain A', 1, '10:55:00', 0, 65, 71, 6, 2, 0),
(37, 'Terrain B', 1, '10:55:00', 0, 62, 68, 6, 1, 0),
(38, 'Terrain A', 1, '11:05:00', 0, 74, 80, 7, 1, 0),
(39, 'Terrain B', 1, '11:05:00', 0, 77, 83, 7, 2, 0),
(40, 'Terrain A', 1, '12:05:00', 0, 51, 60, 5, 1, 0),
(41, 'Terrain B', 1, '12:05:00', 0, 54, 57, 5, 3, 0),
(42, 'Terrain A', 1, '12:15:00', 0, 63, 72, 6, 1, 0),
(43, 'Terrain B', 1, '12:15:00', 0, 66, 69, 6, 2, 0),
(44, 'Terrain B', 1, '11:15:00', 0, 89, 95, 8, 2, 0),
(45, 'Terrain A ', 1, '12:25:00', 0, 75, 84, 7, 3, 0),
(46, 'Terrain B', 1, '12:25:00', 0, 78, 81, 7, 1, 0),
(47, 'Terrain A', 1, '12:35:00', 0, 87, 96, 8, 2, 0),
(48, 'Terrain B', 1, '12:35:00', 0, 90, 93, 8, 1, 0);

-- --------------------------------------------------------

--
-- Structure de la table `score`
--

CREATE TABLE `score` (
  `id` int(11) NOT NULL,
  `equipe_id` int(11) NOT NULL,
  `nombre_buts` int(11) NOT NULL DEFAULT '0',
  `type_victoire` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT '?',
  `bonus` int(11) NOT NULL DEFAULT '0',
  `points` int(11) NOT NULL DEFAULT '0',
  `nombre_buts_contre` int(11) NOT NULL DEFAULT '0',
  `forfait` int(11) NOT NULL DEFAULT '0',
  `gagnant_penalty` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Contenu de la table `score`
--

INSERT INTO `score` (`id`, `equipe_id`, `nombre_buts`, `type_victoire`, `bonus`, `points`, `nombre_buts_contre`, `forfait`, `gagnant_penalty`) VALUES
(1, 6, 0, '0', 0, 0, 0, 0, 0),
(2, 7, 0, '0', 0, 0, 0, 0, 0),
(3, 8, 1, '0', 0, 1, 0, 0, 0),
(4, 9, 2, '0', 0, 4, 0, 0, 0),
(5, 10, 2, '0', 0, 2, 0, 0, 0),
(6, 11, 1, '0', 0, 2, 0, 0, 0),
(7, 12, 0, '0', 0, 0, 0, 0, 0),
(8, 13, 0, '0', 0, 0, 0, 0, 0),
(9, 14, 0, '0', 0, 0, 0, 0, 0),
(10, 15, 0, '0', 0, 0, 0, 0, 0),
(11, 16, 0, '0', 0, 0, 0, 0, 0),
(12, 17, 0, '0', 0, 0, 0, 0, 0),
(13, 18, 0, '0', 0, 0, 0, 0, 0),
(14, 19, 0, '0', 0, 0, 0, 0, 0),
(15, 20, 0, '0', 0, 0, 0, 0, 0),
(16, 21, 0, '0', 0, 0, 0, 0, 0),
(17, 6, 0, '0', 0, 0, 0, 0, 0),
(18, 9, 0, '0', 0, 0, 0, 0, 0),
(19, 7, 0, '0', 0, 0, 0, 0, 0),
(20, 8, 0, '0', 0, 0, 0, 0, 0),
(21, 10, 0, '0', 0, 0, 0, 0, 0),
(22, 13, 0, '0', 0, 0, 0, 0, 0),
(23, 12, 0, '0', 0, 0, 0, 0, 0),
(24, 11, 0, '0', 0, 0, 0, 0, 0),
(25, 14, 0, '0', 0, 0, 0, 0, 0),
(26, 17, 0, '0', 0, 0, 0, 0, 0),
(27, 15, 0, '0', 0, 0, 0, 0, 0),
(28, 16, 0, '0', 0, 0, 0, 0, 0),
(29, 18, 0, '0', 0, 0, 0, 0, 0),
(30, 21, 0, '0', 0, 0, 0, 0, 0),
(31, 19, 0, '0', 0, 0, 0, 0, 0),
(32, 20, 0, '0', 0, 0, 0, 0, 0),
(33, 6, 0, '0', 0, 0, 0, 0, 0),
(34, 8, 0, '0', 0, 0, 0, 0, 0),
(35, 7, 0, '0', 0, 0, 0, 0, 0),
(36, 9, 0, '0', 0, 0, 0, 0, 0),
(37, 10, 0, '0', 0, 0, 0, 0, 0),
(38, 12, 0, '0', 0, 0, 0, 0, 0),
(39, 11, 0, '0', 0, 0, 0, 0, 0),
(40, 13, 0, '0', 0, 0, 0, 0, 0),
(41, 14, 0, '0', 0, 0, 0, 0, 0),
(42, 16, 0, '0', 0, 0, 0, 0, 0),
(43, 15, 0, '0', 0, 0, 0, 0, 0),
(44, 17, 0, '0', 0, 0, 0, 0, 0),
(45, 18, 0, '0', 0, 0, 0, 0, 0),
(46, 20, 0, '0', 0, 0, 0, 0, 0),
(47, 19, 0, '0', 0, 0, 0, 0, 0),
(48, 21, 0, '0', 0, 0, 0, 0, 0),
(49, 22, 0, '?', 0, 0, 0, 0, 0),
(50, 22, 0, '?', 0, 0, 0, 0, 0),
(51, 22, 0, '?', 0, 0, 0, 0, 0),
(52, 23, 0, '?', 0, 0, 0, 0, 0),
(53, 23, 0, '?', 0, 0, 0, 0, 0),
(54, 23, 0, '?', 0, 0, 0, 0, 0),
(55, 24, 0, '?', 0, 0, 0, 0, 0),
(56, 24, 0, '?', 0, 0, 0, 0, 0),
(57, 24, 0, '?', 0, 0, 0, 0, 0),
(58, 25, 0, '?', 0, 0, 0, 0, 0),
(59, 25, 0, '?', 0, 0, 0, 0, 0),
(60, 25, 0, '?', 0, 0, 0, 0, 0),
(61, 26, 0, '?', 0, 0, 0, 0, 0),
(62, 26, 0, '?', 0, 0, 0, 0, 0),
(63, 26, 0, '?', 0, 0, 0, 0, 0),
(64, 27, 0, '?', 0, 0, 0, 0, 0),
(65, 27, 0, '?', 0, 0, 0, 0, 0),
(66, 27, 0, '?', 0, 0, 0, 0, 0),
(67, 28, 0, '?', 0, 0, 0, 0, 0),
(68, 28, 0, '?', 0, 0, 0, 0, 0),
(69, 28, 0, '?', 0, 0, 0, 0, 0),
(70, 29, 0, '?', 0, 0, 0, 0, 0),
(71, 29, 0, '?', 0, 0, 0, 0, 0),
(72, 29, 0, '?', 0, 0, 0, 0, 0),
(73, 30, 0, '?', 0, 0, 0, 0, 0),
(74, 30, 0, '?', 0, 0, 0, 0, 0),
(75, 30, 0, '?', 0, 0, 0, 0, 0),
(76, 31, 0, '?', 0, 0, 0, 0, 0),
(77, 31, 0, '?', 0, 0, 0, 0, 0),
(78, 31, 0, '?', 0, 0, 0, 0, 0),
(79, 32, 0, '?', 0, 0, 0, 0, 0),
(80, 32, 0, '?', 0, 0, 0, 0, 0),
(81, 32, 0, '?', 0, 0, 0, 0, 0),
(82, 33, 0, '?', 0, 0, 0, 0, 0),
(83, 33, 0, '?', 0, 0, 0, 0, 0),
(84, 33, 0, '?', 0, 0, 0, 0, 0),
(85, 34, 0, '?', 0, 0, 0, 0, 0),
(86, 34, 0, '?', 0, 0, 0, 0, 0),
(87, 34, 0, '?', 0, 0, 0, 0, 0),
(88, 35, 0, '?', 0, 0, 0, 0, 0),
(89, 35, 0, '?', 0, 0, 0, 0, 0),
(90, 35, 0, '?', 0, 0, 0, 0, 0),
(91, 36, 0, '?', 0, 0, 0, 0, 0),
(92, 36, 0, '?', 0, 0, 0, 0, 0),
(93, 36, 0, '?', 0, 0, 0, 0, 0),
(94, 37, 0, '?', 0, 0, 0, 0, 0),
(95, 37, 0, '?', 0, 0, 0, 0, 0),
(96, 37, 0, '?', 0, 0, 0, 0, 0);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `arbitre`
--
ALTER TABLE `arbitre`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `equipe`
--
ALTER TABLE `equipe`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `groupe`
--
ALTER TABLE `groupe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_4B98C2199091188` (`phase_id`);

--
-- Index pour la table `groupe_equipe`
--
ALTER TABLE `groupe_equipe`
  ADD PRIMARY KEY (`groupe_id`,`equipe_id`),
  ADD KEY `IDX_4F77462F7A45358C` (`groupe_id`),
  ADD KEY `IDX_4F77462F6D861B89` (`equipe_id`);

--
-- Index pour la table `phase`
--
ALTER TABLE `phase`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `phase_groupe`
--
ALTER TABLE `phase_groupe`
  ADD PRIMARY KEY (`phase_id`,`groupe_id`),
  ADD KEY `IDX_BA6EBABB99091188` (`phase_id`),
  ADD KEY `IDX_BA6EBABB7A45358C` (`groupe_id`);

--
-- Index pour la table `rencontre`
--
ALTER TABLE `rencontre`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_460C35EDCA12A7FB` (`score1_id`),
  ADD KEY `IDX_460C35EDD8A70815` (`score2_id`),
  ADD KEY `IDX_460C35ED7A45358C` (`groupe_id`),
  ADD KEY `IDX_460C35ED943A5F0` (`arbitre_id`);

--
-- Index pour la table `score`
--
ALTER TABLE `score`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_329937516D861B89` (`equipe_id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `arbitre`
--
ALTER TABLE `arbitre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `equipe`
--
ALTER TABLE `equipe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;
--
-- AUTO_INCREMENT pour la table `groupe`
--
ALTER TABLE `groupe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT pour la table `phase`
--
ALTER TABLE `phase`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `rencontre`
--
ALTER TABLE `rencontre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;
--
-- AUTO_INCREMENT pour la table `score`
--
ALTER TABLE `score`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=97;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `groupe`
--
ALTER TABLE `groupe`
  ADD CONSTRAINT `FK_4B98C2199091188` FOREIGN KEY (`phase_id`) REFERENCES `phase` (`id`);

--
-- Contraintes pour la table `groupe_equipe`
--
ALTER TABLE `groupe_equipe`
  ADD CONSTRAINT `FK_4F77462F6D861B89` FOREIGN KEY (`equipe_id`) REFERENCES `equipe` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_4F77462F7A45358C` FOREIGN KEY (`groupe_id`) REFERENCES `groupe` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `phase_groupe`
--
ALTER TABLE `phase_groupe`
  ADD CONSTRAINT `FK_BA6EBABB7A45358C` FOREIGN KEY (`groupe_id`) REFERENCES `groupe` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_BA6EBABB99091188` FOREIGN KEY (`phase_id`) REFERENCES `phase` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `rencontre`
--
ALTER TABLE `rencontre`
  ADD CONSTRAINT `FK_460C35ED7A45358C` FOREIGN KEY (`groupe_id`) REFERENCES `groupe` (`id`),
  ADD CONSTRAINT `FK_460C35ED943A5F0` FOREIGN KEY (`arbitre_id`) REFERENCES `arbitre` (`id`),
  ADD CONSTRAINT `FK_460C35EDCA12A7FB` FOREIGN KEY (`score1_id`) REFERENCES `score` (`id`),
  ADD CONSTRAINT `FK_460C35EDD8A70815` FOREIGN KEY (`score2_id`) REFERENCES `score` (`id`);

--
-- Contraintes pour la table `score`
--
ALTER TABLE `score`
  ADD CONSTRAINT `FK_329937516D861B89` FOREIGN KEY (`equipe_id`) REFERENCES `equipe` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;