package ca.qc.cgmatane.informatique.jeu;

import java.util.ArrayList;

public class Joueur {
    Paquet paquet = new Paquet();



    public Joueur(){

    }

    public void modifierPaquet(Paquet nouveauPaquet){
        this.paquet = nouveauPaquet;
    }

    public  Paquet getPaquet(){
        return this.paquet;
    }

    public Carte retournerCarte(){
        Carte carte = this.paquet.prendreCarteDessus();
        paquet.ajouterCarteDevant(carte);
        return carte;
    }

    public Carte getCarteDevant(){
       return paquet.getCarteDessus();
    }

    public ArrayList<Carte> getPaquetDevant(){

        return paquet.getPaquetDevant();

    }

    public boolean gagnantFinal(){
        if (paquet.getPaquet().isEmpty() ){
            return true;
        }
        else {
            return false;
        }
    }

}
