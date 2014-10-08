package ca.qc.cgmatane.informatique.jeu;

import java.util.ArrayList;

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

    public void melangerPaquet() {
    }

    public void distribuerCarte() {
    }

}
