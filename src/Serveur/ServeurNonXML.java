package Serveur;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 * Created by kwidz on 01/11/14.
 * Serveur de l'application, il est éxécuté dans un Screen d'un serveur dédié sur le port 6789
 * de ce fait il est bloqué par le wifi du Cégep, j'ai du utiliser un VPN pour la démo
 */
public class ServeurNonXML {
    public static void main(String args[]) {
    while(true) {
        //creation de la socket serveur
        ServerSocket socketServeur = null;
        try {
            socketServeur = new ServerSocket(6789);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Attente d'une connexion d'un client
        Socket socketClient = null;
        try {
            socketClient = socketServeur.accept();
            System.out.println("socket client créée");
        } catch (IOException e) {
            System.err.println("Erreur lors de l'attente d'une connexion : " + e);

        }

        // Association d'un flux d'entree et de sortie
        BufferedReader entree = null;
        PrintWriter sortie = null;
        try {
            entree = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            sortie = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream())), true);
        } catch (IOException e) {
            System.err.println("Association des flux impossible : " + e);

        }


            String message = new String();
            try {
                message= entree.readLine();
            } catch(IOException e) {
                System.err.println("Erreur lors de la lecture : " + e);
                System.exit(-1);
            }
            System.out.println("Lu: " + message);

        StringTokenizer decoupeur = new StringTokenizer(message, ":");
        if(decoupeur.nextToken().toString().equals("Demande")){
            System.out.print("test");
            message = demanderScoreALaBDD();
            System.out.println("Envoi: " + message);
            sortie.println(message);
        }
        else{
            StringTokenizer decoupeur2 = new StringTokenizer(decoupeur.nextToken(),";");
            AjouterScoreaLaBDD(decoupeur2.nextToken(),decoupeur2.nextToken());
        }



        // Fermeture des flux et des sockets
        try {
            entree.close();
            sortie.close();
            socketClient.close();
            socketServeur.close();
        } catch(IOException e) {
            System.err.println("Erreur lors de la fermeture des flux et des sockets : " + e);

        }


    }
}
/*
 * ajoute un score dans la bdd grace au DAO
 */
    private static void AjouterScoreaLaBDD(String pseudo, String score) {
        int scoreInt=Integer.parseInt(score);
        BaseDeDonnes bdd = new BaseDeDonnes();
        try{
            bdd.chargerDriver();

            //execution de la requette de récupération des scores.
            ScoreDAO scoreDAO = new ScoreDAO(bdd);


            scoreDAO.insererScore(pseudo, scoreInt);

            bdd.close();
        }
        catch(Exception e) {
            System.err.println(e);
        }
    }


    /*
     * Récupère les scores dans la bdd via le DAO
     */

    private static String demanderScoreALaBDD() {
        String message=new String();
        BaseDeDonnes bdd = new BaseDeDonnes();
        try{
            bdd.chargerDriver();

            //execution de la requette de récupération des scores.
            ScoreDAO scoreDAO = new ScoreDAO(bdd);

            message = scoreDAO.selectionnerLesSocres();


            bdd.close();
        }
        catch(Exception e) {
            System.err.println(e);
        }

        return message;

    }
}

