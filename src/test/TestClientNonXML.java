package test;

import ca.qc.cgmatane.informatique.Client.Client;
import ca.qc.cgmatane.informatique.Client.ClientNonXML;

/**
 * Created by kwidz on 01/11/14.
 */
public class TestClientNonXML {
    public static void main(String args[]) {

        ClientNonXML client = new ClientNonXML();
        client.demanderScore();
        ClientNonXML client2 = new ClientNonXML();
        client2.envoyerScore("toto", 122);


    }
}
