package test;

import ca.qc.cgmatane.informatique.Client.Client;

/**
 * Created by kwidz on 25/10/14.
 */
public class TestClient {
    public static void main(String args[]) {

        Client client = new Client();
        //client.envoyerScore("geoffrey", 12);
        client.demanderScore();
    }
}
