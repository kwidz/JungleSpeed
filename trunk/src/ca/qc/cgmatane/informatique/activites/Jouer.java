package ca.qc.cgmatane.informatique.activites;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.*;
import android.widget.EditText;
import android.widget.Toast;
import ca.qc.cgmatane.informatique.Client.ClientNonXML;
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
import com.example.junglerapide.R;

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

	private GLSurfaceView glSurfaceVue;
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
    private int score=0;

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

		glSurfaceVue.onResume();


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
		if (e.getAction() == MotionEvent.ACTION_DOWN ) {
			Joueur gagnantFinal = new Joueur();

			gagnantFinal = this.partieFini();
			if(gagnantFinal != null){
				System.out.print("changement d'activité");
				LayoutInflater instancieurdelayout = LayoutInflater.from(this);
				final View alertDialogVue = instancieurdelayout.inflate(R.layout.boitededialogue, null);
				AlertDialog.Builder boiteDeDialogue = new AlertDialog.Builder(this);
				boiteDeDialogue.setView(alertDialogVue);
				boiteDeDialogue.setTitle("Vous avez gagné !!! Score : "+score);
				boiteDeDialogue.setIcon(android.R.drawable.ic_dialog_alert);
				boiteDeDialogue.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

						//Lorsque l'on cliquera sur le bouton "OK", on récupère l'EditText correspondant à notre vue personnalisée (cad à alertDialogView)
						EditText texte = (EditText)alertDialogVue.findViewById(R.id.EditText1);

						//On affiche dans un Toast le texte contenu dans l'EditText de notre AlertDialog
                        ClientNonXML client = new ClientNonXML();
                        client.envoyerScore(texte.getText().toString(),score);
						Toast.makeText(Jouer.this, "Le score a été enregistré en ligne", Toast.LENGTH_SHORT).show();
						Intent intentionNavigation = new Intent(Jouer.this, Accueil.class);
						startActivity(intentionNavigation);
					} });

					//On crée un bouton "Annuler" à notre AlertDialog et on lui affecte un évènement
					boiteDeDialogue.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							//Lorsque l'on cliquera sur annuler on quittera l'application
							Intent intentionNavigation = new Intent(Jouer.this, Accueil.class);
							startActivity(intentionNavigation);
						} });
						boiteDeDialogue.show();
						return true;
					}

					//cartes.remove(cartes.size()-1); // ligne test pour essayer de remplacer une carte : echecs ! retire toutes les textures
					float x = e.getX();
					float y = e.getY();
					System.out.println("X" + x + "Y : " + y);

					setContentView(glSurfaceVue);



					if (x > 310 && x < 450 && y > 520 && y < 600)
					{

						System.out.println("je suis dans le carre");
						boolean gagner = this.cliqueTotem();
						if(gagner == true) {
                            System.out.print(" le joueur humain a gagner");

                            Toast.makeText(Jouer.this, " Vous avez gagné cette manche ! ", Toast.LENGTH_SHORT).show();
                            score+=75;
                        }else if(joueurQuiperd != null){
							System.out.print(" le joueur ordinateur a gagner");
                            Toast.makeText(Jouer.this, "L'autre joueur a été plus rapide, Dommage! ", Toast.LENGTH_SHORT).show();
                            score-=10;
							/*//le joueur ordinateur a gagner
							cartes.remove(j1Hum.getPaquet().getCarteDessu());
							cartes.remove(joueurQuiperd.getPaquet().getCarteDessu());
							j1Hum.getPaquet().getPaquet().addAll(0, joueurQuiperd.getPaquet().viderPaquetDevant());
							j1Hum.getPaquet().getPaquet().addAll(0,j1Hum.getPaquet().viderPaquetDevant());*/
						}else{
							System.out.print(" joueur humain s'est trompé");
                            Toast.makeText(Jouer.this, "Vous vous êtes trompé! ", Toast.LENGTH_SHORT).show();
                            score-=50;
							/* cartes.remove(j1Hum.getPaquet().getCarteDessu());
							cartes.remove(j2Rb.getPaquet().getCarteDessu());
							cartes.remove(j3Rb.getPaquet().getCarteDessu());
							cartes.remove(j4Rb.getPaquet().getCarteDessu());

							j1Hum.getPaquet().getPaquet().addAll(0,j1Hum.getPaquet().viderPaquetDevant());
							j1Hum.getPaquet().getPaquet().addAll(0,j2Rb.getPaquet().viderPaquetDevant());
							j1Hum.getPaquet().getPaquet().addAll(0,j3Rb.getPaquet().viderPaquetDevant());
							j1Hum.getPaquet().getPaquet().addAll(0,j4Rb.getPaquet().viderPaquetDevant());*/
						}




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
						cartes.set(6,carte);
						System.out.print("j3 :"+carte);

						carte = j2Rb.retournerCarte();
						cartes.set(7,carte);
						System.out.print("j2 :"+carte);



						if(j3Rb.getCarteDevant().comparerForme(j2Rb.getCarteDevant()) == true){
							Random r = new Random();
							int random = r.nextInt(2);
							if(random == 1){
                                //j3rb perdant


								int j = cartes.indexOf(j3Rb.getCarteDevant());
								cartes.set(j,new Carte(Position.centre, null, null));

                                //le joueur 3 recupere les cartes du joueur 2 apres avoir changé leurs position:


                                /*for(int i=0 ; i < j2Rb.getPaquetDevant().size() ; i++){
                                    j2Rb.getPaquetDevant().get(i).modifierPosition(Position.droite);
                                    j3Rb.getPaquet().ajouterCarte( j2Rb.getPaquetDevant().get(i));
                                }
                                j2Rb.getPaquet().viderPaquetDevant();
                                for(int i=0 ; i < j3Rb.getPaquetDevant().size() ; i++){
                                    j3Rb.getPaquet().ajouterCarte( j3Rb.getPaquetDevant().get(i));
                                }
                                j3Rb.getPaquet().viderPaquetDevant();*/

							}else{
                                //j2rb perdant
								int j = cartes.indexOf(j2Rb.getCarteDevant());
								cartes.set(j, new Carte(Position.centre, null, null));
							}


						}


						if(j3Rb.getCarteDevant().comparerForme(j4Rb.getCarteDevant()) == true){
							Random r = new Random();
							int random = r.nextInt(2);
							if(random == 1){
								int j = cartes.indexOf(j3Rb.getCarteDevant());
								cartes.set(j,new Carte(Position.centre, null, null));
							}else{
								int j = cartes.indexOf(j4Rb.getCarteDevant());
								cartes.set(j, new Carte(Position.centre, null, null));
							}



						}

						if(j2Rb.getCarteDevant().comparerForme(j4Rb.getCarteDevant()) == true){
							Random r = new Random();
							int random = r.nextInt(2);
							if(random == 1){
								int j = cartes.indexOf(j4Rb.getCarteDevant());
								cartes.set(j, new Carte(Position.centre, null, null));
							}else{
								int j = cartes.indexOf(j2Rb.getCarteDevant());
								cartes.set(j,new Carte(Position.centre, null, null));
							}


						}

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
        joueurQuiperd = null;

		Carte carteJoueur = j1Hum.getPaquet().getCarteDessus();
		if(carteJoueur.comparerForme(j2Rb.getPaquet().getCarteDessus()) == true){
			joueurQuiperd = j2Rb;
			gagner = true;
		}

		if(carteJoueur.comparerForme(j3Rb.getPaquet().getCarteDessus()) == true){
			joueurQuiperd = j3Rb;
			gagner = true;
		}

		if(carteJoueur.comparerForme(j4Rb.getPaquet().getCarteDessus()) == true){
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
		glSurfaceVue = new GLSurfaceView(this);
		glSurfaceVue.setRenderer(new Partie(this, this));

		setContentView(glSurfaceVue);
	}


	//on appel cette fonction a chaque tour et si elle renvois un joueur non null alors on va a l'ecran des scores
	// et le joueur qui a été retourné est le gagnant.
	public Joueur partieFini(){

		Joueur finParti = null;
		if (j1Hum.gagnantFinal()){
			finParti = j1Hum;
		}

		if (j2Rb.gagnantFinal()){
			finParti = j2Rb;
		}

		if (j3Rb.gagnantFinal()){
			finParti = j3Rb;
		}

		if (j4Rb.gagnantFinal()){
			finParti = j4Rb;
		}

		return  finParti;
	}



}
