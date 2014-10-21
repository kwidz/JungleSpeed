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
        creerDocumentdemanderScore();
        outToServer.writeBytes(sentence + '\n');
        modifiedSentence = inFromServer.readLine();
        System.out.println("FROM SERVER: " + modifiedSentence);
        clientSocket.close();
    }

    private static Document creerDocumentEnvoyerScore(String pseudoStr, int scoreStr){

        Element racine = new Element("requete");
        Element type = new Element("type");
        type.addContent("envoi");
        Element score = new Element("score");
        Element pseudo=new Element("pseudo");
        Element pointage=new Element("pointage");
        pointage.addContent(Integer.toString((scoreStr)));
        pseudo.addContent(pseudoStr);
        score.addContent(pseudo);
        score.addContent(pointage);
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

    private static Document creerDocumentdemanderScore(){

        Element racine = new Element("requete");
        Element type = new Element("type");
        type.addContent("envoi");
        racine.addContent(type);
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