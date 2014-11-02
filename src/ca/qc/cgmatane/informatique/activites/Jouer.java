package ca.qc.cgmatane.informatique.activites;

import java.sql.Time;
import java.util.ArrayList;
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
		if(e.getAction() == MotionEvent.ACTION_DOWN)
		{

            int nbPacHum = j1Hum.getPaquet().getPaquet().size();

            //cartes.remove(cartes.size()-1); // ligne test pour essayer de remplacer une carte : echecs ! retire toutes les textures
            float x = e.getX();
            float y = e.getY();
            System.out.println("X" + x + "Y : "+y);
            long t1 = System.currentTimeMillis();
            while(System.currentTimeMillis()<t1+3000);
            setContentView(glSurfaceView);
            if(x>210 && x<290 && y>320 && y<400)
            {
                System.out.println("je suis dans le carre");
                /*boolean gagner = this.cliqueTotem();
                if(gagner == true){
                    cartes.remove(joueurQuiperd.getPaquet().getCarteDevant());
                }else{
                    cartes.remove(j1Hum.getPaquet().getCarteDevant());
                }*/


	        	 /*
	        	  * Fonction pour le totem
	        	  */
            }
            else
            {
                System.out.println("je ne suis pas dans le carre");
                Carte carte;
                carte = j1Hum.getPaquet().prendreCarteDessus();
                j1Hum.getPaquet().modifierCarteDevant(carte);

                // this.cliquePaquet();
                if(cartes.size()  < 9){

                    cartes.add(carte);

                }else {
                    cartes.set(8, carte);

                }

            }

            /*if(nbPacHum !=  j1Hum.getPaquet().getPaquet().size()){

                long tp = System.currentTimeMillis();
                while(System.currentTimeMillis()<tp+3000);
                setContentView(glSurfaceView);
                Carte carte;
                carte = j4Rb.getPaquet().prendreCarteDessus();
                j4Rb.getPaquet().modifierCarteDevant(carte);
                cartes.set(7, carte);

                carte = j3Rb.getPaquet().prendreCarteDessus();
                j3Rb.getPaquet().modifierCarteDevant(carte);
                cartes.set(6, carte);

                carte = j2Rb.getPaquet().prendreCarteDessus();
                j2Rb.getPaquet().modifierCarteDevant(carte);
                cartes.set(5, carte);
            }*/

        }
		return true;
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

       //cartes.add(j1Hum.getPaquet().prendreCarteDessus());
        cartes.add(j2Rb.getPaquet().prendreCarteDessus());
        cartes.add(j3Rb.getPaquet().prendreCarteDessus());
        cartes.add(j4Rb.getPaquet().prendreCarteDessus());


    }

	
    //cette methode sera appeler lorsque l'utilisateur cliquera sur le totem, pour l'instant elle n'est appelé nulle part
    public boolean cliqueTotem(){
        Boolean gagner = false;

        Carte carteJoueur = j1Hum.getPaquet().getCarteDevant();
        if(carteJoueur.comparCarteForm(j2Rb.getPaquet().getCarteDevant()) == true){
           joueurQuiperd = j2Rb;
           gagner = true;
        }

        if(carteJoueur.comparCarteForm(j3Rb.getPaquet().getCarteDevant()) == true){
            joueurQuiperd = j3Rb;
            gagner = true;
        }

        if(carteJoueur.comparCarteForm(j4Rb.getPaquet().getCarteDevant()) == true){
            joueurQuiperd = j4Rb;
            gagner = true;
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