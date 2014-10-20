package ca.qc.cgmatane.informatique.jeu;

public class Joueur {
    Paquet paquet = new Paquet();

    public Joueur(Paquet p){
        this.paquet = p;
    }

    public void modifierPaquet(Paquet nouveauP){
        this.paquet = nouveauP;
    }


}
