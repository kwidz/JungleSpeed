package ca.qc.cgmatane.informatique.Client;

/**
 * Created by kwidz on 25/10/14.
 */
public class TestClient {
    public static void main(String args[]){
        Client client = new Client();
        client.demanderScore();
        client.envoyerScore("geoffrey", 1024);
    }
}
