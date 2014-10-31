package Serveur;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kwidz on 25/10/14.
 */

public class Serveur {

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

            // On crée une instance de SAXBuilder pour recuperer le XML

            SAXBuilder sxb = new SAXBuilder();
            Document document = null;
            try {
                System.out.println("récupération du document");
                document = sxb.build(entree);
                System.out.println("document recupéré");
            } catch (JDOMException e) {
                System.err.println("Erreur lors de la lecture : " + e);

            } catch (IOException e) {
                System.err.println("Erreur lors de la lecture : " + e);

            }

            // Affichage de tout le document
            try {
                XMLOutputter afficheur = new XMLOutputter(Format.getPrettyFormat());
                afficheur.output(document, System.out);
            } catch (IOException e) {
                System.err.println("Erreur lors de l'affichage : " + e);

            }

            // Envoi de la réponse au format xml
            System.out.print("envoi de :\n");

            try {
                XMLOutputter afficheur = new XMLOutputter(Format.getPrettyFormat());
                afficheur.output(document, System.out);
            } catch (IOException e) {
                System.err.println("Erreur lors de l'affichage : " + e);

            }

            try {

                XMLOutputter envoi = new XMLOutputter(Format.getCompactFormat());
                envoi.output(document, sortie);
                System.out.println("envoyé !");
            } catch(IOException e) {
                System.err.println("Erreur lors de l'envoi dans la socket : " + e);

            }/*
            String message = new String();
            try {
                message= entree.readLine();
            } catch(IOException e) {
                System.err.println("Erreur lors de la lecture : " + e);
                System.exit(-1);
            }
            System.out.println("Lu: " + message);

            message = "Bonjour";
            System.out.println("Envoi: " + message);
            sortie.println(message);
*/





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
}
