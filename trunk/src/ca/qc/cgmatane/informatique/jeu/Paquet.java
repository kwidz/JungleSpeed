package ca.qc.cgmatane.informatique.jeu;

import java.util.ArrayList;
import java.util.Collections;

public class Paquet {
    private ArrayList<Carte> paquet;
    //private Carte carteDevant = null;
    private  ArrayList<Carte> cartesDevant = new ArrayList<Carte>();

    public Paquet() {
    	paquet = new ArrayList<Carte>();
    }

    public ArrayList getPaquet(){
        return this.paquet;
    }


    public ArrayList getPaquetDevant() {
        return  this.cartesDevant;
    }

    /**
     *  modifie la carte au dessu du paquet de devant
     */

    public void modifierCarteDessu(Carte carte){
        this.cartesDevant.set( ((this.cartesDevant.size()) - 1), carte);
    }

    public  void ajouterCarteDevant(Carte carte){
        this.cartesDevant.add(carte);
    }

    // retourne la carte au dessu du paquet de devant
    public Carte getCarteDessus(){
        Carte carte = null;
               if(this.cartesDevant.isEmpty() != true){
                   carte = this.cartesDevant.get(((this.cartesDevant.size()) - 1));
               }
        return carte;
    }

    //vide le paquet de devant
    public  ArrayList viderPaquetDevant(){
        ArrayList paquet = this.cartesDevant;
        this.cartesDevant = new ArrayList<Carte>();
        return paquet;
    }


    public void remplirPaquet(){

        for(int i=0 ; i<4 ; i++ ){

            for(int j=0; j< Forme.values().length; j++){
                this.paquet.add(new Carte(Position.gauche, Couleur.values()[i], Forme.values()[j]));
            }
        }
    }

    //permet d'ajouter une carte au paquet
    public  void ajouterCarte(Carte carte){
        this.paquet.add(0,carte);
    }


    //Mélange le paquet de carte principal
    public void melangerPaquet() {
        Collections.shuffle(this.paquet);
    }


    //cette methode permet de supprimer un élément du paquet de en renvoyant cette élément,
    // elle est très utile pour distribuer le paquet de carte au départ
    public Carte prendreCarteDessus(){
        return(paquet.remove(   ((paquet.size()) -1 ) )  );
    }


}