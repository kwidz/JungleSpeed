package ca.qc.cgmatane.informatique.jeu;

public class Joueur {
    Paquet paquet = new Paquet();

    public Joueur(){

    }

    public void modifierPaquet(Paquet nouveauP){
        this.paquet = nouveauP;
    }


}
