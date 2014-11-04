package ca.qc.cgmatane.informatique.jeu;

public class Joueur {
    Paquet paquet = new Paquet();

    public Joueur(){

    }

    public void modifierPaquet(Paquet nouveauP){
        this.paquet = nouveauP;
    }
    public  Paquet getPaquet(){
        return this.paquet;
    }

    public Carte retournerCarte(){
        Carte c = this.paquet.prendreCarteDessus();
        paquet.ajouterCarteDevant(c);
        return c;
    }

}
