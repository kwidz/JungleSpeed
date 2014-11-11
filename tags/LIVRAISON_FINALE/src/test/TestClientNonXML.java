package test;

import ca.qc.cgmatane.informatique.Client.Client;
import ca.qc.cgmatane.informatique.Client.ClientNonXML;
import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.IOException;

/**
 * Created by kwidz on 01/11/14.
 */
public class TestClientNonXML {
    public static void main(String args[]) {

        ClientNonXML client = new ClientNonXML();
        Document document = client.retournerLesScores();
        ClientNonXML client2 = new ClientNonXML();
        client2.envoyerScore("toto", 122);
        try {
            XMLOutputter afficheur = new XMLOutputter(Format.getPrettyFormat());
            afficheur.output(document, System.out);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'affichage : " + e);

        }

    }
}
