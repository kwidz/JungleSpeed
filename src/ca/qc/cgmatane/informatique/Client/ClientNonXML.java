package ca.qc.cgmatane.informatique.Client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by kwidz on 01/11/14.
 */
public class ClientNonXML {
    public void demanderScore(){

        // Creation de la socket
        Socket socket = null;
        try {
            socket = new Socket("localhost", 6789);
        } catch(UnknownHostException e) {
            System.err.println("Erreur sur l'hôte : " + e);

        } catch(IOException e) {
            System.err.println("Creation de la socket impossible : " + e);

        }

        // Association d'un flux d'entree et de sortie
        BufferedReader entree = null;
        PrintWriter sortie = null;
        try {
            entree = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            sortie = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        } catch(IOException e) {
            System.err.println("Association des flux impossible : " + e);

        }



        // Envoi du document XML

       String message = creerChaineDemandeScore();
        System.out.println("Envoi: " + message);
        sortie.println(message);

        message = new String();
        try {
            message= entree.readLine();
        } catch(IOException e) {
            System.err.println("Erreur lors de la lecture : " + e);
            System.exit(-1);
        }
        System.out.println("Lu: " + message);
        //lecture de la réponse au format xml

        //fermetures

        try {
            entree.close();
            sortie.close();
            socket.close();
        } catch(IOException e) {
            System.err.println("Erreur lors de la fermeture des flux et de la socket : " + e);
        }

    }

    public void envoyerScore(String pseudoStr, int scoreStr){
        // Creation de la socket
        Socket socket = null;
        try {
            socket = new Socket("localhost", 6789);
        } catch(UnknownHostException e) {
            System.err.println("Erreur sur l'hôte : " + e);

        } catch(IOException e) {
            System.err.println("Creation de la socket impossible : " + e);

        }

        // Association d'un flux d'entree et de sortie
        BufferedReader entree = null;
        PrintWriter sortie = null;
        try {
            entree = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            sortie = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        } catch(IOException e) {
            System.err.println("Association des flux impossible : " + e);

        }

        String message = creerChaineEnvoiScore(pseudoStr, scoreStr);
        System.out.println("Envoi: " + message);
        sortie.println(message);

        //fermetures

        try {
            entree.close();
            sortie.close();
            socket.close();
        } catch(IOException e) {
            System.err.println("Erreur lors de la fermeture des flux et de la socket : " + e);
        }
    }

    private String creerChaineEnvoiScore(String pseudoStr, int scoreStr){

        String envoiScore=new String();
        envoiScore="Envoi:";
        envoiScore+=pseudoStr+";"+Integer.toString(scoreStr);

        return envoiScore;

    }

    private String creerChaineDemandeScore(){

        String demande="Demande:";
        return demande;

    }
}
