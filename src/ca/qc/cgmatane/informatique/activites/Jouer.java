package ca.qc.cgmatane.informatique.activites;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import ca.qc.cgmatane.informatique.jeu.Carte;
import ca.qc.cgmatane.informatique.jeu.Couleur;
import ca.qc.cgmatane.informatique.jeu.Forme;
import ca.qc.cgmatane.informatique.jeu.Joueur;
import ca.qc.cgmatane.informatique.jeu.JoueurHumain;
import ca.qc.cgmatane.informatique.jeu.JoueurOrdinateur;
import ca.qc.cgmatane.informatique.jeu.Paquet;
import ca.qc.cgmatane.informatique.jeu.Position;
import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class Jouer extends Activity {

	private final int PAQUETHAUT = 0;
	private final int PAQUETBAS = 1;
	private final int PAQUETGAUCHE = 2;
	private final int PAQUETDROITE = 3;
	private final int TOTEM = 4;
	private final int CARTEDEVANTHAUT = 7;
	private final int CARTEDEVANTBAS = 8;
	private final int CARTEDEVANTGAUCHE = 5;
	private final int CARTEDEVANTDROITE = 6;
	
	private GLSurfaceView glSurfaceView;
	private ArrayList<Carte> cartes;
    public Paquet paquet = new Paquet();
    public Paquet p1h = new Paquet();
    public Paquet p2o = new Paquet();
    public Paquet p3o = new Paquet();
    public Paquet p4o = new Paquet();


    private JoueurHumain j1Hum ;
    private JoueurOrdinateur j2Rb;
    private JoueurOrdinateur j3Rb;
    private JoueurOrdinateur j4Rb;

    private JoueurOrdinateur joueurQuiperd = new JoueurOrdinateur() ;



    @Override
    public void onCreate(Bundle savedInstanceState) {

  super.onCreate(savedInstanceState);
  System.out.println("on joue");
  paquet.remplirPaquet();
  System.out.println("le paquet est rempli");
 // paquet.melangerPaquet();
  System.out.println("le paquet est melange");
  cartes = new ArrayList<Carte>();
  cartes.add(new Carte(Position.pacHaut,null, null));
  cartes.add(new Carte(Position.pacBas,null, null));
  cartes.add(new Carte(Position.pacGauche,null, null));
  cartes.add(new Carte(Position.pacDroite, null, null));
  cartes.add(new Carte(Position.centre, null, null));
        joueurQuiperd = null;
  /*cartes.add(new Carte(Position.gauche,null, null));
  cartes.add(new Carte(Position.gauche,null, null));
  cartes.add(new Carte(Position.gauche,null, null));
  cartes.add(new Carte(Position.gauche,null, null));*/
       this.jouer();



  gestionOpenGl();
 // p1h.modifierCarteDevant((new Carte(Position.droite, Couleur.vert, Forme.rondcarre)));
  /*JoueurHumain jh = new JoueurHumain(new Paquet());
  JoueurOrdinateur jo1 = new JoueurOrdinateur(new Paquet());
  JoueurOrdinateur jo2 = new JoueurOrdinateur(new Paquet());
  JoueurOrdinateur jo3 = new JoueurOrdinateur(new Paquet());
  jouer(jh,jo1,jo2,jo3);*/  
 
    }

	/**
	 * Remember to resume the glSurface
	 */
	@Override
	protected void onResume() {

        super.onResume();

		glSurfaceView.onResume();


	}

	/**
	 * Also pause the glSurface
	 */
	@Override
	protected void onPause() {



		super.onPause();
		//glSurfaceView.onPause();
	}
	@Override
	public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {



            //cartes.remove(cartes.size()-1); // ligne test pour essayer de remplacer une carte : echecs ! retire toutes les textures
            float x = e.getX();
            float y = e.getY();
            System.out.println("X" + x + "Y : " + y);

            setContentView(glSurfaceView);
            if (x > 210 && x < 290 && y > 320 && y < 400) {
                System.out.println("je suis dans le carre");
                /*boolean gagner = this.cliqueTotem();
                if(gagner == true){
                    //le joueur humain a gagner
                    cartes.remove(joueurQuiperd.getPaquet().getCarteDessu());
                    cartes.remove(j1Hum.getPaquet().getCarteDessu());
                    joueurQuiperd.getPaquet().getPaquet().addAll(0, j1Hum.getPaquet().viderPaquetDevant());
                    joueurQuiperd.getPaquet().getPaquet().addAll(0,joueurQuiperd.getPaquet().viderPaquetDevant());
                }else if(joueurQuiperd != null){
                    //le joueur ordinateur a gagner
                    cartes.remove(j1Hum.getPaquet().getCarteDessu());
                    cartes.remove(joueurQuiperd.getPaquet().getCarteDessu());
                    j1Hum.getPaquet().getPaquet().addAll(0, joueurQuiperd.getPaquet().viderPaquetDevant());
                    j1Hum.getPaquet().getPaquet().addAll(0,j1Hum.getPaquet().viderPaquetDevant());
                }else{
                    //le joueur humain s'est trompé
                    cartes.remove(j1Hum.getPaquet().getCarteDessu());
                    cartes.remove(j2Rb.getPaquet().getCarteDessu());
                    cartes.remove(j3Rb.getPaquet().getCarteDessu());
                    cartes.remove(j4Rb.getPaquet().getCarteDessu());

                    j1Hum.getPaquet().getPaquet().addAll(0,j1Hum.getPaquet().viderPaquetDevant());
                    j1Hum.getPaquet().getPaquet().addAll(0,j2Rb.getPaquet().viderPaquetDevant());
                    j1Hum.getPaquet().getPaquet().addAll(0,j3Rb.getPaquet().viderPaquetDevant());
                    j1Hum.getPaquet().getPaquet().addAll(0,j4Rb.getPaquet().viderPaquetDevant());
                }*/


	        	 /*
	        	  * Fonction pour le totem
	        	  */
            } else {
                System.out.println("je ne suis pas dans le carre");

                // on recupere la carte du paquet du joueur
                Carte carte = j1Hum.retournerCarte();

                  //on rempli le tableau d'affichage
                if(cartes.size()  < 9){

                    cartes.add(carte);

                }else {
                    cartes.set(8, carte);

                }
                System.out.print("j1 :"+carte);


                carte = j4Rb.retournerCarte();
                cartes.set(5,carte);
                System.out.print("j4 :"+carte);

                carte = j3Rb.retournerCarte();
                cartes.add(6,carte);
                System.out.print("j3 :"+carte);

                carte = j2Rb.retournerCarte();
                cartes.add(7,carte);
                System.out.print("j2 :"+carte);



                /* if(j3Rb.getCarteDevant().comparCarteForm(j2Rb.getCarteDevant()) == true){
                   Random r = new Random();
                    int random = r.nextInt(2);
                    if(random == 1){
                        int j = cartes.indexOf(j3Rb.getCarteDevant());
                        cartes.set(j, null);
                    }else{
                        int j = cartes.indexOf(j2Rb.getCarteDevant());
                        cartes.set(j, null);
                    }

                    int j = cartes.indexOf(j3Rb.getCarteDevant());
                    cartes.set(j,new Carte(Position.centre, null, null));
                }


                if(j3Rb.getCarteDevant().comparCarteForm(j4Rb.getCarteDevant()) == true){
                   Random r = new Random();
                    int random = r.nextInt(2);
                    if(random == 1){
                            int j = cartes.indexOf(j3Rb.getCarteDevant());
                        cartes.set(j, null);
                    }else{
                        int j = cartes.indexOf(j4Rb.getCarteDevant());
                        cartes.set(j, null);
                    }

                    int j = cartes.indexOf(j3Rb.getCarteDevant());
                    cartes.set(j, new Carte(Position.centre, null, null));

                }

                if(j2Rb.getCarteDevant().comparCarteForm(j4Rb.getCarteDevant()) == true){
                    Random r = new Random();
                    int random = r.nextInt(2);
                    if(random == 1){
                        int j = cartes.indexOf(j4Rb.getCarteDevant());
                        cartes.set(j, null);
                    }else{
                        int j = cartes.indexOf(j2Rb.getCarteDevant());
                        cartes.set(j, null);
                    }

                    int j = cartes.indexOf(j4Rb.getCarteDevant());
                    cartes.set(j,new Carte(Position.centre, null, null));
                } */










            }
            return true;
        }else {return false; }

    }
	public ArrayList<Carte> getCartes()
	{
		return cartes;
	}
	
	public void jouer(){  // pour l'instant un humain contre trois robot
        //paquet.distribuerCarte();
        paquet.remplirPaquet();
        paquet.melangerPaquet();

        j1Hum = new JoueurHumain();
        j2Rb = new JoueurOrdinateur();
        j3Rb = new JoueurOrdinateur();
        j4Rb = new JoueurOrdinateur();


        /*for( int i = 0 ; i< paquet.getPaquet().size() ; i= i+4){
            p1h.ajouterCarte(cartes.get(i));
            p2o.ajouterCarte(cartes.get(i+1));
            p3o.ajouterCarte(cartes.get(i+2));
            p4o.ajouterCarte(cartes.get(i+3));
        }*/

        while(paquet.getPaquet().size() > 0 ){
            Carte carteInter = paquet.prendreCarteDessus();
            carteInter.modifierPosition(Position.bas);
            p1h.ajouterCarte(carteInter);

            carteInter = paquet.prendreCarteDessus();
            carteInter.modifierPosition(Position.gauche);
            p2o.ajouterCarte(carteInter);

            carteInter = paquet.prendreCarteDessus();
            carteInter.modifierPosition(Position.droite);
            p3o.ajouterCarte(carteInter);

            carteInter = paquet.prendreCarteDessus();
            carteInter.modifierPosition(Position.haut);
            p4o.ajouterCarte(carteInter);
        }

        j1Hum.modifierPaquet(p1h);
        j2Rb.modifierPaquet(p2o);
        j3Rb.modifierPaquet(p3o);
        j4Rb.modifierPaquet(p4o);

       /*cartes.add(j1Hum.getPaquet().prendreCarteDessus());
        cartes.add(j2Rb.getPaquet().prendreCarteDessus());
        cartes.add(j3Rb.getPaquet().prendreCarteDessus());
        cartes.add(j4Rb.getPaquet().prendreCarteDessus());*/



        Carte carte;
        carte = j4Rb.retournerCarte();
        cartes.add(carte);

        carte = j3Rb.retournerCarte();
        cartes.add(carte);

        carte = j2Rb.retournerCarte();
        cartes.add(carte);


    }

	
    //cette methode sera appeler lorsque l'utilisateur cliquera sur le totem
    public boolean cliqueTotem(){
        Boolean gagner = false;

        Carte carteJoueur = j1Hum.getPaquet().getCarteDessu();
        if(carteJoueur.comparCarteForm(j2Rb.getPaquet().getCarteDessu()) == true){
           joueurQuiperd = j2Rb;
           gagner = true;
        }

        if(carteJoueur.comparCarteForm(j3Rb.getPaquet().getCarteDessu()) == true){
            joueurQuiperd = j3Rb;
            gagner = true;
        }

        if(carteJoueur.comparCarteForm(j4Rb.getPaquet().getCarteDessu()) == true){
            joueurQuiperd = j4Rb;
            gagner = true;
        }

        // si il y a deux forme pareil on creer un chiffre random qui permettra de choisir si
        // c'est le robot ou l'humain qui sera compter comme gagnant
        if(gagner){
            Random r = new Random();
            int random = r.nextInt(5);
            if(random == 4){
                gagner = false;
            }
        }


        return gagner;

    }

    // cette fonction modifie la carte du paquet sur lequel on a cliquez
    // (pour l'instant cette fonction prend un paquet mais bientot on fera un e.target )

    public void gestionOpenGl()
    {
    	 requestWindowFeature(Window.FEATURE_NO_TITLE);
 		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
 				WindowManager.LayoutParams.FLAG_FULLSCREEN);
   glSurfaceView = new GLSurfaceView(this);
   glSurfaceView.setRenderer(new Partie(this, this));

   setContentView(glSurfaceView);
    }



}