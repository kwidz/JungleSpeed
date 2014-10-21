package ca.qc.cgmatane.informatique.Client;


import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class Client
{
    public static void main(String args[]) throws Exception
    {
        String sentence;
        String modifiedSentence;
        //BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
        Socket clientSocket = new Socket("localhost", 6789);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        sentence = new String("Test");
        creerDocumentEnvoyerScore("geoffrey",8000);
        outToServer.writeBytes(sentence + '\n');
        modifiedSentence = inFromServer.readLine();
        System.out.println("FROM SERVER: " + modifiedSentence);
        clientSocket.close();
    }

    private static Document creerDocumentEnvoyerScore(String pseudoStr, int scoreStr){

        Element racine = new Element("requete");
        Element type = new Element("type");
        Attribute nom;
        nom = new Attribute("nom", "envoi");
        type.setAttribute(nom);
        Element score = new Element("score");
        Attribute pseudo, valeur;
        pseudo = new Attribute("pseudo", pseudoStr);
        valeur = new Attribute("valeur",Integer.toString(scoreStr));
        score.setAttribute(pseudo);
        score.setAttribute(valeur);
        racine.addContent(type);
        racine.addContent(score);
        XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
        Document document = new Document(racine);
        try {
            sortie.output(document, System.out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;

    }
}