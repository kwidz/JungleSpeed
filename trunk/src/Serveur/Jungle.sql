SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Structure de la table Score
--

CREATE TABLE IF NOT EXISTS `Score` (
`idScore` int(50) NOT NULL,
  `PseudoJoueur` varchar(50) NOT NULL,
  `ScoreJoueur` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;

ALTER TABLE `Score`
 ADD PRIMARY KEY (`idScore`);

--
-- AUTO_INCREMENT pour la table `Score`
--
ALTER TABLE `Score`
MODIFY `idScore` int(50) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=20;
