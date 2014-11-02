package test;

import ca.qc.cgmatane.informatique.jeu.Paquet;

/**
 * Created by kwidz on 26/10/14.
 */
public class TestPaquet {
    public static void main(String args[]){
        Paquet paquet = new Paquet();
        System.out.println(paquet.getPaquet());
        System.out.println(paquet.getCarteDessu());
        paquet.remplirPaquet();

        System.out.println(paquet.getPaquet());
        System.out.println(paquet.getCarteDessu());
        paquet.melangerPaquet();
        System.out.println(paquet.getPaquet());
        System.out.println(paquet.prendreCarteDessus());


    }
}
