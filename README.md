# JungleRapide

JungleRapide est un jeu de carte réalisé par Morgane Cabrol, Geoffrey Glangine et Benjamin Heinen
Le principe du jeu est de retourner des cartes, si deux cartes ont des symboles identiques on doit attraper le totem pour gagner la manche et le but du jeu est de vider son paquet de cartes
### Spécifications :

* L'application permet de jouer de façon très basique car nous avons eut beaucoup de mal avec l'openGL ce qui nous a beaucoup retardé et nous a fait supprimer des règles au jeu.
* L'application utilise openGL
* L'application se ferme lorsque l'on est dans le menu et qu'on la secoue
* L'application permet de prendre des photos et de les enregistrer au format bitmap
* L'application stocke ses score sur un serveur externe éxécuté dans un screen d'un serveur dédié avec une base de données mysql
* La classe ClientnonXML de l'application communique avec le serveur et retourne a l'application un fichier XML contenant tout les scores qu'elle devra afficher dans un "listview"
* Toutes les socketes réseaux sont des socketes TCP(mode Connecté)

### Installation

* Pour installer l'application, il suffit d'installer l'apk généré par eclipse
* Pour installer un serveur, il faut creer la base de donnée mySql avec le code /Serveur/Jungle.sql
* Puis lancer classes/artifacts/jungleRapide.jar

    java -jar jungleRapide.jar > logServeur

pour ainsi garder un log des connections du serveur

