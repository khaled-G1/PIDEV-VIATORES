-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 24 fév. 2022 à 11:18
-- Version du serveur : 10.4.21-MariaDB
-- Version de PHP : 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `test3`
--

-- --------------------------------------------------------

--
-- Structure de la table `chambre`
--

CREATE TABLE `chambre` (
  `id` int(11) NOT NULL,
  `hotel_id` int(11) DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nb_lits` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `etage` int(11) NOT NULL,
  `prix` int(11) NOT NULL,
  `vue` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `chambre`
--

INSERT INTO `chambre` (`id`, `hotel_id`, `type`, `nb_lits`, `etage`, `prix`, `vue`) VALUES
(11, 12, 'Double', '3', 4, 220, 'Vue sur Mer'),
(16, 12, 'Double', '2', 1, 200, 'Vue sur Mer'),
(17, 15, 'Suite', '4', 4, 1500, 'Vue sur Mer'),
(18, 15, 'Double', '2', 3, 220, 'Vue sur Mer'),
(19, 17, 'Single', '1', 2, 150, 'Vue sur Mer');

-- --------------------------------------------------------

--
-- Structure de la table `doctrine_migration_versions`
--

CREATE TABLE `doctrine_migration_versions` (
  `version` varchar(191) COLLATE utf8_unicode_ci NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `execution_time` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `doctrine_migration_versions`
--

INSERT INTO `doctrine_migration_versions` (`version`, `executed_at`, `execution_time`) VALUES
('DoctrineMigrations\\Version20220214224517', '2022-02-15 12:45:29', 35),
('DoctrineMigrations\\Version20220215124604', '2022-02-15 12:46:13', 118);

-- --------------------------------------------------------

--
-- Structure de la table `hotel`
--

CREATE TABLE `hotel` (
  `id` int(11) NOT NULL,
  `ville` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nb_chambre` int(11) NOT NULL,
  `imghotel` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `hotel`
--

INSERT INTO `hotel` (`id`, `ville`, `nom`, `nb_chambre`, `imghotel`) VALUES
(10, 'Tunis', 'AAAAA', 500, 'vue_pano_nuit_hotel_iberostar_kantaoui_bay_tunisie-621545ae8f3f2.jpeg'),
(11, 'Sousse', 'Royal Victoria', 4000, 'LTI-Mahdia-Beach (1)-6215600c1b98f.jpeg'),
(12, 'Mahdia', 'Mövenpick Hotel du Lac Tunis', 500, 'EXT11-621560319fda1.jpeg'),
(14, 'Monastir', 'Marigold Hotel', 600, 'hotel-sousse-the-pearl-62155f96af63a.jpeg'),
(15, 'Djerba', 'Acropole Tunis, hôtel à Tunis Acropole Tunis', 500, 'Sousse_palace_11-62155fb497eb6.jpeg'),
(17, 'Hamamet', 'feryel', 30, 'LTI-Mahdia-Beach (1)-6215f70397065.jpeg');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `chambre`
--
ALTER TABLE `chambre`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_C509E4FF3243BB18` (`hotel_id`);

--
-- Index pour la table `doctrine_migration_versions`
--
ALTER TABLE `doctrine_migration_versions`
  ADD PRIMARY KEY (`version`);

--
-- Index pour la table `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `chambre`
--
ALTER TABLE `chambre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT pour la table `hotel`
--
ALTER TABLE `hotel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `chambre`
--
ALTER TABLE `chambre`
  ADD CONSTRAINT `FK_C509E4FF3243BB18` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
