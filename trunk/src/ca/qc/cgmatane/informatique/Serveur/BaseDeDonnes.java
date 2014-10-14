package ca.qc.cgmatane.informatique.Serveur;

import java.sql.*;

public class BaseDeDonnes {

    Connection 	conn;

    /* Affiche les infos liés à la Bdd
    */
    private void printInfo(Connection c) throws Exception
    {
        // Récupération des infos sur la base de donnée
        DatabaseMetaData info = c.getMetaData();
        System.out.println("\nConnecté à :\t" + info.getURL());
        System.out.println("Driver :\t" + info.getDriverName());
        System.out.println("Version :\t" + info.getDriverVersion());
    }

    /* Affiche toutes les infos
     */
    private boolean checkForSQLWarnings(SQLWarning w)
            throws SQLException
    {
        boolean warning = false;
        if(w != null) {
            warning = true;
            System.out.println("\n**** Erreurs ****\n");

            while(w != null) {
                System.out.println("SQLState: " + w.getSQLState());
                System.out.println("Message:  " + w.getMessage());
                System.out.println("Vendor:   " + w.getErrorCode());
                System.out.println("");
                w = w.getNextWarning();
            }
        }
        return warning;
    }

    /* Affiche les erreurs sql sur la sortie standard d'erreurs
    */
    private void printSQLErrors(SQLException e)
    {
        while(e != null) {
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Message:  " + e.getMessage());
            System.err.println("Vendor:   " + e.getErrorCode());
            System.err.println("");
            e = e.getNextException();
        }
    }

    public void chargerDriver()
            throws Exception
    {
        try {
            // Chargement du driver
            Class.forName("com.mysql.jdbc.Driver");

            // Connection à la base de donnée mysql
            String url = "jdbc:mysql://localhost:3306/Jungle";
            conn = DriverManager.getConnection(url, "root", "toor");

            // Affiche les warnings sur la sortie standard
            checkForSQLWarnings(conn.getWarnings());

            // Affiche des infos sur la sortie standard
            printInfo(conn);

        }
        catch(SQLException e) {
            System.err.println("\n*** ExceptionSql dans  chargerDriver()");
            printSQLErrors(e);
            throw e;
        }
        catch(Exception e) {
            // Exception dans le cas ou le driver n'est pas trouvé
            System.err.println("\n*** Le driver JDBC n'a pas pu être chargé");
            throw e;
        }

    }

    /**
     *	Ferme la connection à la BDD
     */
    public void close() throws Exception
    {
        try {
            conn.close();
            System.out.println("Test : Disconnecting ...");
        }
        catch(Exception e) {
            System.err.println("\n*** Exception caught in close()");
            throw e;
        }
    }

}

class TestMain {
    public static void main(String[] args) {
        BaseDeDonnes t = new BaseDeDonnes();
        try{
            t.chargerDriver();
            // To do...
            t.close();
        }
        catch(Exception e) {
            System.err.println("\n*** SQLException caught in main()");
        }
    }
}