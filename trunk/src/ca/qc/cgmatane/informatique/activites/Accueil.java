package ca.qc.cgmatane.informatique.activites;




import com.example.junglerapide.R;
import android.app.Activity;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Accueil extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accueil);
		
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

}