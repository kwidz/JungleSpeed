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
	private Context context;
	private Jouer jeu;
	private final int PAQUETHAUT = 0;
	private final int PAQUETBAS = 1;
	private final int PAQUETGAUCHE = 2;
	private final int PAQUETDROITE = 3;
	private final int TOTEM = 4;
	private final int CARTEDEVANTHAUT = 7;
	private final int CARTEDEVANTBAS = 8;
	private final int CARTEDEVANTGAUCHE = 5;
	private final int CARTEDEVANTDROITE = 6;


    /** Constructor to set the handed over context */
	public Partie(Context context, Jouer jouer) {
		this.context = context;
		cartes = new ArrayList<Carte>();
		jeu = jouer;
		cartes = jeu.getCartes();
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
		
		for (int i = 0; i < cartes.size(); i++) {
			if(cartes.get(i) != null)
				cartes.get(i).dessiner(gl);
        }

	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		if(height == 0) { 						//Prevent A Divide By Zero By
			height = 1; 						//Making Height Equal One
		}

		
		System.out.println("on change de surface");
		for (int i = 0; i < cartes.size(); i++) {
			if(cartes.get(i) != null)
				cartes.get(i).loadGLTexture(gl, context);
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
		System.out.println("on cree la surface");

		for (int i = 0; i < cartes.size(); i++) {
			if(cartes.get(i) != null)
				cartes.get(i).loadGLTexture(gl, context);
        }





        gl.glEnable(GL10.GL_TEXTURE_2D);			
		gl.glShadeModel(GL10.GL_SMOOTH); 			
		gl.glClearColor(0.5f, 0.5f, 0.8f, 0.5f); 	
		gl.glClearDepthf(1.0f); 					
		gl.glEnable(GL10.GL_DEPTH_TEST); 			
		gl.glDepthFunc(GL10.GL_LEQUAL); 			

		//Really Nice Perspective Calculations
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST); 

	}


    
}