package ca.qc.cgmatane.informatique.jeu;

import java.util.ArrayList;
import java.util.Collections;

public class Paquet {
    private ArrayList<Carte> carteArrayList;
    //private Carte carteDevant = null;
    private  ArrayList<Carte> cartesDevant = new ArrayList<Carte>();

    public Paquet() {
    	carteArrayList = new ArrayList<Carte>();
    }

    public ArrayList getPaquet(){
        return this.carteArrayList;
    }

    /*public Carte getCarteDevant(){
        return this.carteDevant;
    }

    public void modifierCarteDevant(Carte c){
        this.carteDevant = c;
    }  */

    public ArrayList getPaquetDevant() {
        return  this.cartesDevant;
    }

    //modifie la carte au dessu du paquet de devant
    public void modifierCarteDessu(Carte c){
        this.cartesDevant.set( ((this.cartesDevant.size()) - 1), c);
    }

    public  void ajouterCarteDevant(Carte c){
        this.cartesDevant.add(c);
    }

    // retourne la carte au dessu du paquet de devant
    public Carte getCarteDessu(){
        Carte c = null;
               if(this.cartesDevant.isEmpty() != true){
                   c = this.cartesDevant.get(((this.cartesDevant.size()) - 1));
               }
        return c;
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
                this.carteArrayList.add(new Carte(Position.gauche, Couleur.values()[i], Forme.values()[j]));
            }
        }
    }

    //permet d'ajouter une carte au paquet
    public  void ajouterCarte(Carte c){
        this.carteArrayList.add(c);
    }


    //Mélange le paquet de carte principal
    public void melangerPaquet() {
        Collections.shuffle(this.carteArrayList);
    }


    //cette methode permet de supprimer un élément du paquet de en renvoyant cette élément,
    // elle est très utile pour distribuer le paquet de carte au départ
    public Carte prendreCarteDessus(){
        return(carteArrayList.remove(   ((carteArrayList.size()) -1 ) )  );
    }


}