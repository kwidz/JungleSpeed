package ca.qc.cgmatane.informatique.jeu;

import java.util.ArrayList;
import java.util.Collections;

public class Paquet {
    private ArrayList<Carte> cartes;
    private Carte carteDevant = null;

    public Paquet() {
    	cartes = new ArrayList<Carte>();
    }

    public ArrayList getPaquet(){
        return this.cartes;
    }

    public Carte getCarteDevant(){
        return this.carteDevant;
    }

    public void modifierCarteDevant(Carte c){
        this.carteDevant = c;
    }

    public void remplirPaquet(){
    	System.out.print("testRemplirPaquet");
        for(int i=0 ; i<4 ; i++ ){
            System.out.println(Couleur.values()[i]);
            for(int j=0; j< Forme.values().length ; j++){
            	if(j%4==0)
            		ajouterCarte(new Carte(Position.droite, Couleur.values()[i], Forme.values()[j]));
            	if (j%4 == 1)
                    ajouterCarte(new Carte(Position.gauche, Couleur.values()[i], Forme.values()[j]));
            	if(j%4 == 2)
                    ajouterCarte(new Carte(Position.haut, Couleur.values()[i], Forme.values()[j]));
            	if(j%4 == 3)
                    ajouterCarte(new Carte(Position.bas, Couleur.values()[i], Forme.values()[j]));
            	System.out.println(Forme.values()[j]);
            }
        }
    }

    //permet d'ajouter une carte au paquet
    public  void ajouterCarte(Carte c){
        this.cartes.add(c);
    }


    //Mélange le paquet de carte principal
    public void melangerPaquet() {
        Collections.shuffle(this.cartes);
    }


    //cette methode permet de supprimer un élément du paquet de cartes en renvoyant cette élément,
    // elle est très utile pour distribuer le paquet de carte au départ
    public Carte prendreCarteDessus(){
        return(cartes.remove(   ((cartes.size()) -1 ) )  );
    }

  /*  public ArrayList<Paquet> distribuerCarte() {
        this.remplirPaquet();
        this.melangerPaquet();

        Paquet p1h = new Paquet();
        Paquet p2o = new Paquet();
        Paquet p3o = new Paquet();
        Paquet p4o = new Paquet();
        ArrayList<Paquet> paquetJoueurs;
        for( int i = 0 ; i< cartes.size() ; i= i+4){
            p1h.ajouterCarte(cartes.get(i));
            p2o.ajouterCarte(cartes.get(i+1));
            p3o.ajouterCarte(cartes.get(i+2));
            p4o.ajouterCarte(cartes.get(i+3));
        }
        paquetJoueurs = new ArrayList();
        paquetJoueurs.add(p1h);
        paquetJoueurs.add(p2o);
        paquetJoueurs.add(p3o);
        paquetJoueurs.add(p4o);

        return(paquetJoueurs);

    }*/

}