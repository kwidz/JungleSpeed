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

    //demande le score au serveur
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
        ThreadEnvoiScore threadEnvoiScore = new ThreadEnvoiScore(pseudoStr, scoreStr);
        threadEnvoiScore.start();
        while( threadEnvoiScore.isAlive() ) {
            // faire un traitement...

            try {
                // et faire une pause
                threadEnvoiScore.sleep(800);
            }
            catch (InterruptedException ex) {}
        }
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
