package test;

import Serveur.BaseDeDonnes;
import Serveur.ScoreDAO;

/**
 * Created by kwidz on 11/11/14.
 */
class TestBasedeDonnee {
    public static void main(String[] args) {
        BaseDeDonnes bdd = new BaseDeDonnes();
        try{
            bdd.chargerDriver();

            //execution de la requette de récupération des scores.
            ScoreDAO scoreDAO = new ScoreDAO(bdd);

            scoreDAO.selectionnerLesSocres();
            scoreDAO.insererScore("momo", 586);
            scoreDAO.selectionnerLesSocres();
            bdd.close();
        }
        catch(Exception e) {
            System.err.println(e);
        }
    }
}