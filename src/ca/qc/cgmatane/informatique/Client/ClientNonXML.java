package ca.qc.cgmatane.informatique.Client;

import org.jdom2.Document;
import org.jdom2.Element;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

/**
 * Created by kwidz on 01/11/14.
 */
public class ClientNonXML {
    public String demanderScore(){

        ThreadReception threadReception = new ThreadReception();
        threadReception.start();
        while( threadReception.isAlive() ) {
            // faire un traitement...

            try {
                // et faire une pause
                threadReception.sleep(800);
            }
            catch (InterruptedException ex) {}
        }
        return threadReception.getLeMessage();

    }

    public void envoyerScore(String pseudoStr, int scoreStr){
        // Creation de la socket
        Socket socket = null;
        try {
            socket = new Socket("kwidz.fr", 6789);
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


    public Document retournerLesScores(){

        String lesScores=demanderScore();
        StringTokenizer decouperChaqueScores= new StringTokenizer(lesScores,";");
        Element racine = new Element("lesScores");
        while(decouperChaqueScores.hasMoreTokens()) {
            StringTokenizer pseudoEtScore = new StringTokenizer(decouperChaqueScores.nextToken(),":");
            Element score = new Element("score");
            Element pseudo = new Element("pseudo");
            Element pointage = new Element("pointage");
            pseudo.addContent(pseudoEtScore.nextToken());
            pointage.addContent(pseudoEtScore.nextToken());
            score.addContent(pseudo);
            score.addContent(pointage);
            racine.addContent(score);
        }
        Document document = new Document(racine);



        return document;
    }
}
