package ca.qc.cgmatane.informatique.activites;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import ca.qc.cgmatane.informatique.jeu.*;

import android.content.Context;
import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

public class Partie implements Renderer {

	private ArrayList<Carte> cartes;
    private Paquet paquet;
	private Context 	context;


    private Paquet p1h = new Paquet();
    private Paquet p2o = new Paquet();
    private Paquet p3o = new Paquet();
    private Paquet p4o = new Paquet();


    /** Constructor to set the handed over context */
	public Partie(Context context) {
		this.context = context;
		cartes = new ArrayList<Carte>();
		this.cartes.add(new Carte(Position.haut, Couleur.jaune, Forme.carrerondcarre));
		Log.i("test", Forme.carrerondcarre.name());
		Log.i("test2", Couleur.jaune.name());
		//this.cartes.add(new Carte(Position.bas, Couleur.orange, Forme.carrerondcarre));
		//this.cartes.add(new Carte(Position.droite, Couleur.vert, Forme.rondcarre));
		this.cartes.add(new Carte(Position.gauche, Couleur.violet, Forme.rondcarre));
		this.cartes.add(new Carte(Position.centre, null, null));
        p1h.ModifierCarteDevant((new Carte(Position.droite, Couleur.vert, Forme.rondcarre)));
        /*JoueurHumain jh = new JoueurHumain(new Paquet());
        JoueurOrdinateur jo1 = new JoueurOrdinateur(new Paquet());
        JoueurOrdinateur jo2 = new JoueurOrdinateur(new Paquet());
        JoueurOrdinateur jo3 = new JoueurOrdinateur(new Paquet());
        jouer(jh,jo1,jo2,jo3);*/



	}

	@Override
	public void onDrawFrame(GL10 gl) {
		// clear Screen and Depth Buffer
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		// Reset the Modelview Matrix
		gl.glLoadIdentity();

		// Drawing
		gl.glTranslatef(0.0f, 0.0f, -5.0f);		// move 5 units INTO the screen
												// is the same as moving the camera 5 units away
//		gl.glScalef(0.5f, 0.5f, 0.5f);			// scale the square to 50% 
												// otherwise it will be too large
		//cartes.get(0).draw(gl);						// Draw the triangle
		/*for (Carte carte : cartes) {
            carte.draw(gl);
        }*/
        if(p1h.getCarteDevant() != null){
            p1h.getCarteDevant().draw(gl);
        }
        if(p2o.getCarteDevant() != null){
            p2o.getCarteDevant().draw(gl);
        }
        if(p3o.getCarteDevant() != null){
            p3o.getCarteDevant().draw(gl);
        }

        if(p4o.getCarteDevant() != null){
            p4o.getCarteDevant().draw(gl);
        }




        /*(new Carte(Position.pacHaut, null, null) ).draw(gl);
        (new Carte(Position.pacBas, null, null)).draw(gl);
        (new Carte(Position.pacDroite, null, null)).draw(gl);
        (new Carte(Position.pacGauche, null, null)).draw(gl);*/
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		if(height == 0) { 						//Prevent A Divide By Zero By
			height = 1; 						//Making Height Equal One
		}

		gl.glViewport(0, 0, width, height); 	//Reset The Current Viewport
		gl.glMatrixMode(GL10.GL_PROJECTION); 	//Select The Projection Matrix
		gl.glLoadIdentity(); 					//Reset The Projection Matrix

		//Calculate The Aspect Ratio Of The Window
		GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);

		gl.glMatrixMode(GL10.GL_MODELVIEW); 	//Select The Modelview Matrix
		gl.glLoadIdentity(); 					//Reset The Modelview Matrix
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// Load the texture for the square
		//this.cartes.get(0).loadGLTexture(gl, this.context);
		for (Carte carte : cartes)
			carte.loadGLTexture(gl, context);



        if(p1h.getCarteDevant() != null){
            p1h.getCarteDevant().loadGLTexture(gl, context);
        }
        if(p2o.getCarteDevant() != null){
            p2o.getCarteDevant().loadGLTexture(gl, context);
        }
        if(p3o.getCarteDevant() != null){
            p3o.getCarteDevant().loadGLTexture(gl, context);
        }

        if(p4o.getCarteDevant() != null){
            p4o.getCarteDevant().loadGLTexture(gl, context);
        }



        gl.glEnable(GL10.GL_TEXTURE_2D);			//Enable Texture Mapping ( NEW )
		gl.glShadeModel(GL10.GL_SMOOTH); 			//Enable Smooth Shading
		gl.glClearColor(0.5f, 0.5f, 0.8f, 0.5f); 	//Black Background
		gl.glClearDepthf(1.0f); 					//Depth Buffer Setup
		gl.glEnable(GL10.GL_DEPTH_TEST); 			//Enables Depth Testing
		gl.glDepthFunc(GL10.GL_LEQUAL); 			//The Type Of Depth Testing To Do

		//Really Nice Perspective Calculations
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); 

	}

    public void jouer(Joueur j1Hum,Joueur j2Rb,Joueur j3Rb,Joueur j4Rb){  // pour l'instant un humain contre trois robot
        //paquet.distribuerCarte();
        paquet.remplirPaquet();
        paquet.melangerPaquet();


        /*for( int i = 0 ; i< paquet.getPaquet().size() ; i= i+4){
            p1h.ajouterCarte(cartes.get(i));
            p2o.ajouterCarte(cartes.get(i+1));
            p3o.ajouterCarte(cartes.get(i+2));
            p4o.ajouterCarte(cartes.get(i+3));
        }*/

        while(paquet.getPaquet().size() > 0 ){
            Carte carteInter = paquet.prendreCarteDessu();
            carteInter.modifierPosition(Position.haut);
            p1h.ajouterCarte(carteInter);

            carteInter = paquet.prendreCarteDessu();
            carteInter.modifierPosition(Position.gauche);
            p2o.ajouterCarte(carteInter);

            carteInter = paquet.prendreCarteDessu();
            carteInter.modifierPosition(Position.droite);
            p3o.ajouterCarte(carteInter);

            carteInter = paquet.prendreCarteDessu();
            carteInter.modifierPosition(Position.bas);
            p4o.ajouterCarte(carteInter);
        }

        j1Hum.modifierPaquet(p1h);
        j2Rb.modifierPaquet(p2o);
        j3Rb.modifierPaquet(p3o);
        j4Rb.modifierPaquet(p4o);



        // maintenant la methode doit afficher les quatres paquets de carte sur le terrain
        //ainsi que le totem

        // la methode doit aussi ajouter les listerners sur les cartes
    }

    //cette methode sera appeler lorsque l'utilisateur cliquera sur le totem, pour l'instant elle n'est appelé nulle part
    public void cliqueTotem(Joueur j){

    }

}