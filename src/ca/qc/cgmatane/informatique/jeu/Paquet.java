package ca.qc.cgmatane.informatique.jeu;

import java.util.ArrayList;
import java.util.Collections;

public class Paquet {
    private ArrayList<Carte> cartes;

    public Paquet() {

    }

    public ArrayList getPaquet(){

        return this.cartes;
    }
    public void remplirPaquet(){
        this.cartes.add(new Carte(Position.centre, null, null));
        for(int i=0 ; i<4 ; i++ ){
            System.out.println(Couleur.values()[i]);
            for(int j=0; j< Forme.values().length ; j++){
                this.cartes.add(new Carte(Position.aModifier, Couleur.values()[i], Forme.values()[j]));
            }
        }
    }

    public  void ajouterCarte(Carte c){
        this.cartes.add(c);
    }

    public void melangerPaquet() {
        Collections.shuffle(this.cartes);
    }

    public void distribuerCarte() {
        this.remplirPaquet();
        this.melangerPaquet();
        /*JoueurHumain j1H = new JoueurHumain();
        JoueurOrdinateur j2O = new JoueurOrdinateur();
        JoueurOrdinateur j3O = new JoueurOrdinateur();
        JoueurOrdinateur j4O = new JoueurOrdinateur();*/
        Paquet p1h = new Paquet();
        Paquet p2o = new Paquet();
        Paquet p3o = new Paquet();
        Paquet p4o = new Paquet();
        for( int i = 0 ; i< cartes.size() ; i= i+4){
            p1h.ajouterCarte(cartes.get(i));
            p2o.ajouterCarte(cartes.get(i+1));
            p3o.ajouterCarte(cartes.get(i+2));
            p4o.ajouterCarte(cartes.get(i+3));
        }
        JoueurHumain j1H = new JoueurHumain(p1h);
        JoueurOrdinateur j2O = new JoueurOrdinateur( p2o);
        JoueurOrdinateur j3O = new JoueurOrdinateur( p3o);
        JoueurOrdinateur j4O = new JoueurOrdinateur( p4o);
    }

}