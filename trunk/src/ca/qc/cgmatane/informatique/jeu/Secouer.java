package ca.qc.cgmatane.informatique.jeu;

import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.content.Context;
import java.lang.UnsupportedOperationException;

public class Secouer implements SensorListener 
{
  private static final int FORCE = 350;
  private static final int TEMPSLIMITE = 100;
  private static final int TEMPS = 500;
  private static final int DUREE = 1000;
  private static final int COMPTER = 3;

  private SensorManager sensibiliteManageur;
  private float dernierX=-1.0f, dernierY=-1.0f, dernierZ=-1.0f;
  private long finDuTemps;
  private OnShakeListener ecouteurSecouer;
  private Context contexte;
  private int compterSecouement = 0;
  private long dernierSecouement;
  private long forceFin;

  public interface OnShakeListener
  {
    public void onShake();
  }

  public Secouer(Context context) 
  { 
    contexte = context;
    resume();
  }

  public void setEcouteurSecouer(OnShakeListener ecouteur)
  {
    ecouteurSecouer = ecouteur;
  }

  public void resume() {
    sensibiliteManageur = (SensorManager)contexte.getSystemService(Context.SENSOR_SERVICE);
    if (sensibiliteManageur == null) {
      throw new UnsupportedOperationException("Sensibilite non supporté");
    }
    boolean supporte = sensibiliteManageur.registerListener(this, SensorManager.SENSOR_ACCELEROMETER, SensorManager.SENSOR_DELAY_GAME);
    if (!supporte) {
      sensibiliteManageur.unregisterListener(this, SensorManager.SENSOR_ACCELEROMETER);
      throw new UnsupportedOperationException("Accelerometre n'est pas supporte");
    }
  }

  public void pause() {
    if (sensibiliteManageur != null) {
      sensibiliteManageur.unregisterListener(this, SensorManager.SENSOR_ACCELEROMETER);
      sensibiliteManageur = null;
    }
  }


  public void onSensorChanged(int sensibilite, float[] valeur) 
  {
    if (sensibilite != SensorManager.SENSOR_ACCELEROMETER) return;
    long maintenant = System.currentTimeMillis();

    if ((maintenant - forceFin) > TEMPS) {
      compterSecouement = 0;
    }

    if ((maintenant - finDuTemps) > TEMPSLIMITE) {
      long difference = maintenant - finDuTemps;
      float vitesse = Math.abs(valeur[SensorManager.DATA_X] + valeur[SensorManager.DATA_Y] + valeur[SensorManager.DATA_Z] - dernierX - dernierY - dernierZ) / difference * 10000;
      if (vitesse > FORCE) {
        if ((++compterSecouement >= COMPTER) && (maintenant - dernierSecouement > DUREE)) {
          dernierSecouement = maintenant;
          compterSecouement = 0;
          if (ecouteurSecouer != null) { 
            ecouteurSecouer.onShake(); 
          }
        }
        forceFin = maintenant;
      }
      finDuTemps = maintenant;
      dernierX = valeur[SensorManager.DATA_X];
      dernierY = valeur[SensorManager.DATA_Y];
      dernierZ = valeur[SensorManager.DATA_Z];
    }
  }

@Override
public void onAccuracyChanged(int sensor, int accuracy) {
	// TODO Auto-generated method stub
	
}

}