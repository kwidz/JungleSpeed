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
            } catch (IOException e) {
                System.err.println("Erreur lors de l'attente d'une connexion : " + e);

            }

            // Association d'un flux d'entree et de sortie
            BufferedReader input = null;
            PrintWriter output = null;
            try {
                input = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                output = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream())), true);
            } catch (IOException e) {
                System.err.println("Association des flux impossible : " + e);
                System.exit(-1);
            }

            // On crée une instance de SAXBuilder pour recuperer le XML
            SAXBuilder sxb = new SAXBuilder();
            Document document = null;
            try {
                document = sxb.build(input);
            } catch (JDOMException e) {
                System.err.println("Erreur lors de la lecture : " + e);
                System.exit(-1);
            } catch (IOException e) {
                System.err.println("Erreur lors de la lecture : " + e);
                System.exit(-1);
            }

            // Affichage de tout le document
            try {
                XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
                sortie.output(document, System.out);
            } catch (IOException e) {
                System.err.println("Erreur lors de l'affichage : " + e);
                System.exit(-1);
            }

            // Fermeture des flux et des sockets
            try {
                input.close();
                output.close();
                socketClient.close();
                socketServeur.close();
            } catch(IOException e) {
                System.err.println("Erreur lors de la fermeture des flux et des sockets : " + e);
                System.exit(-1);
            }


        }
    }
}
