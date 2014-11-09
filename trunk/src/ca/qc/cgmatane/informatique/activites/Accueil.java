package ca.qc.cgmatane.informatique.activites;




import ca.qc.cgmatane.informatique.jeu.Secouer;

import com.example.junglerapide.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Accueil extends Activity {

	private Secouer secouer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accueil);
		

	    secouer = new Secouer(this);
	    secouer.setOnShakeListener(new Secouer.OnShakeListener () {
	      public void onShake()
	      {
	        System.out.println("je suis secoue");
	        finir();
	      } 
	      });
		
	}


	public void naviguerJouer(View view)
	{
		Intent intentionNavigation = new Intent(this, Jouer.class);
		startActivity(intentionNavigation);
	}
	
	public void naviguerScores(View view)
	{
		Intent intentionNavigation = new Intent(this, Scores.class);
		startActivity(intentionNavigation);
	}

	public void naviguerPhoto(View view)
	{
		Intent intentionNavigation = new Intent(this, Photo.class);
		startActivity(intentionNavigation);
	}
	public void finir()
	{
		this.finish();
	}
}