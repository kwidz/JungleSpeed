package ca.qc.cgmatane.informatique.activites;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class Jouer extends Activity {

	/** The OpenGL view */
	private GLSurfaceView glSurfaceView;

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

  // set our renderer to be the main renderer with

  // the current activity context

  glSurfaceView.setRenderer(new Partie(this));

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
		System.out.println ("test");
		return true;
	}

}