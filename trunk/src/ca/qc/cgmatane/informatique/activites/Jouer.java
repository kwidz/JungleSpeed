package ca.qc.cgmatane.informatique.activites;

import java.util.ArrayList;

import ca.qc.cgmatane.informatique.jeu.Carte;
import ca.qc.cgmatane.informatique.jeu.Couleur;
import ca.qc.cgmatane.informatique.jeu.Forme;
import ca.qc.cgmatane.informatique.jeu.Position;
import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class Jouer extends Activity {

	/** The OpenGL view */
	private GLSurfaceView glSurfaceView;
	private ArrayList<Carte> cartes;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

  super.onCreate(savedInstanceState);

  // requesting to turn the title OFF

  requestWindowFeature(Window.FEATURE_NO_TITLE);

  // making it full screen
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

  // Initiate the Open GL view and

  // create an instance with this activity

  glSurfaceView = new GLSurfaceView(this);
  cartes = new ArrayList<Carte>();
  cartes.add(new Carte(Position.pacHaut,null, null));
  cartes.add(new Carte(Position.pacDroite, null, null));
  cartes.add(new Carte(Position.pacGauche,null, null));
  cartes.add(new Carte(Position.pacBas,null, null));
  cartes.add(new Carte(Position.centre, null, null));
  // set our renderer to be the main renderer with

  // the current activity context

  glSurfaceView.setRenderer(new Partie(this, this));

  setContentView(glSurfaceView);
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
		glSurfaceView.onPause();
	}
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		cartes.set(3, new Carte(Position.bas, Couleur.orange, Forme.carrerondcarre)); // ligne test pour essayer de remplacer une carte : echecs ! retire toutes les textures
		//cartes.add(new Carte(Position.bas, Couleur.orange, Forme.carrerondcarre));
		 float x = e.getX();
         float y = e.getY();
         System.out.println("X" + x + "Y : "+y);
         if(x>210 && x<290 && y>320 && y<400)
         {
        	 System.out.println("je suis dans le carre");
        	 /*
        	  * Fonction pour le totem
        	  */
         }
         else
         {
        	 System.out.println("je ne suis pas dans le carre");
        	 /*
        	  * Fonction pour changer de carte
        	  */
         }
		return true;
	}
	public ArrayList<Carte> getCartes()
	{
		return cartes;
	}

}