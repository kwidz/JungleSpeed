package ca.qc.cgmatane.informatique.Client;


import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client
{
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

        Document document = creerDocumentdemanderScore();

        // Envoi du document XML
        try {
            XMLOutputter envoi = new XMLOutputter(Format.getCompactFormat());
            envoi.output(document, sortie);

        } catch(IOException e) {
            System.err.println("Erreur lors de l'envoi dans la socket : " + e);

        }
        System.out.println("envoyé");


        //lecture de la réponse au format xml

        SAXBuilder sxb = new SAXBuilder();
        Document document2 = null;
        try {
            System.out.println("tentative de lecture de réponse");
            document2 = sxb.build(entree);

        } catch (JDOMException e) {
            System.err.println("Erreur lors de la lecture : " + e);

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture : " + e);

        }

        // Affichage de tout le document
        try {
            XMLOutputter afficheur = new XMLOutputter(Format.getPrettyFormat());
            afficheur.output(document2, System.out);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'affichage : " + e);

        }


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

        Document document = creerDocumentEnvoyerScore(pseudoStr, scoreStr);

        // Envoi du document XML
        try {
            XMLOutputter envoi = new XMLOutputter(Format.getCompactFormat());
            envoi.output(document, sortie);
        } catch(IOException e) {
            System.err.println("Erreur lors de l'envoi dans la socket : " + e);

        }


        //fermetures

        try {
            entree.close();
            sortie.close();
            socket.close();
        } catch(IOException e) {
            System.err.println("Erreur lors de la fermeture des flux et de la socket : " + e);
        }
    }

    private Document creerDocumentEnvoyerScore(String pseudoStr, int scoreStr){

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
        Document document = new Document(racine);

        return document;

    }

    private Document creerDocumentdemanderScore(){

        Element racine = new Element("requete");
        Element type = new Element("type");
        type.addContent("demande");
        racine.addContent(type);
        Document document = new Document(racine);

        return document;

    }
}