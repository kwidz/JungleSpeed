package ca.qc.cgmatane.informatique.Serveur;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by kwidz on 16/10/14.
 */
public class ScoreDAO {
    BaseDeDonnes bdd;

    public ScoreDAO(BaseDeDonnes bdd){
        this.bdd=bdd;
    }

    /**
     * Récupère les scores dans la base de donnees par ordre décroissant
     * @throws SQLException
     */
    public void selectionnerLesSocres() throws SQLException {

        Statement declaration = bdd.conn.createStatement();
        //L'objet ResultSet contient le résultat de la requête SQL
        ResultSet resultat = declaration.executeQuery("SELECT * FROM Score order by ScoreJoueur desc");
        //On récupère les MetaData
        ResultSetMetaData resultatMeta = resultat.getMetaData();
        System.out.println("\n**********************************");
        //On affiche le nom des colonnes
        for(int i = 1; i <= resultatMeta.getColumnCount(); i++)
            System.out.print("\t" + resultatMeta.getColumnName(i).toUpperCase() + "\t *");

        System.out.println("\n**********************************");

        while(resultat.next()){
            for(int i = 1; i <= resultatMeta.getColumnCount(); i++)
                System.out.print("\t" + resultat.getObject(i).toString() + "\t |");

            System.out.println("\n---------------------------------");
        }
        resultat.close();
        declaration.close();
    }

    /**
     * Insère un nouveau score dans la base de données
     * @param pseudo
     * @param score
     */
    public void insererScore(String pseudo, int score) throws SQLException {

        Statement declaration = bdd.conn.createStatement();
        //L'objet ResultSet contient le résultat de la requête SQL
        String sql = "INSERT INTO `Jungle`.`Score` (`idScore`, `PseudoJoueur`, `ScoreJoueur`) VALUES (NULL, '"+pseudo+"', '"+score+"');";
        //exécution de la requette.
        declaration.executeUpdate(sql);
        //On "désaloue" la mémoire
        declaration.close();

    }
}
