package ca.qc.cgmatane.informatique.activites;




import ca.qc.cgmatane.informatique.jeu.CheckSensor;

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

	private CheckSensor mShaker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accueil);
		

	    mShaker = new CheckSensor(this);
	    mShaker.setOnShakeListener(new CheckSensor.OnShakeListener () {
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