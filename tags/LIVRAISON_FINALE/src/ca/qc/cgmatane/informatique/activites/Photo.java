package ca.qc.cgmatane.informatique.activites;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Video.Media;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;

import com.example.junglerapide.R;

public class Photo extends Activity implements SurfaceHolder.Callback{

	private Camera camera;
	private SurfaceView surfaceCamera;
	private Boolean estPrevisualise;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.print("rentre dans l'appli photo");
		   // On met l'application en plein écran et sans barre de titre
	    getWindow().setFormat(PixelFormat.TRANSLUCENT);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	            WindowManager.LayoutParams.FLAG_FULLSCREEN);
	 
	    estPrevisualise = false;
	 
	    // On applique notre layout
	    setContentView(R.layout.activity_photo);	 
	    // On récupère notre surface pour le preview
	    surfaceCamera = (SurfaceView) findViewById(R.id.surfaceViewCamera);
	 
	    // Méthode d'initialisation de la caméra
	    initialiserCamera();
	    
	    surfaceCamera.setOnClickListener(new OnClickListener() {
	    	 
	        public void onClick(View v) {
	            // On prend une photo
	            if (camera != null) {
	            	System.out.println("detection clic");
	                sauvegarderImage();
			        long temps = System.currentTimeMillis(); 
	                while(System.currentTimeMillis()<temps+5000); 
			        finir();
	              //  finir();
	            }
	     
	        }
	    });
	    
	}

	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		  if (camera == null)
		        camera = Camera.open();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		if (estPrevisualise) {
	        camera.stopPreview();
	    }
	    // On récupère les parametres de la camera
		 Camera.Parameters parametres = camera.getParameters();  
		   List<Camera.Size> taille = parametres.getSupportedPreviewSizes();  
		   Camera.Size tailleCamera = taille.get(0);  
		 //On récupére le display afin de connaitre l'orientation du téléphone.
		    Display affichage = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
		         
		    // On change la taille selon l'orientation du téléphone
		    if(affichage.getRotation() == Surface.ROTATION_0)
		        {
		            parametres.setPreviewSize(height, width);                          
		            camera.setDisplayOrientation(90);
		             
		        }
		        if(affichage.getRotation() == Surface.ROTATION_90)
		        {
		            parametres.setPreviewSize(width, height);                          
		        }
		 
		        if(affichage.getRotation() == Surface.ROTATION_180)
		        {
		            parametres.setPreviewSize(height, width);              
		        }
		 
		        if(affichage.getRotation() == Surface.ROTATION_270)
		        {
		            parametres.setPreviewSize(width, height);
		            camera.setDisplayOrientation(180);
		        }

		   parametres.setPreviewSize(tailleCamera.width, tailleCamera.height);  
		   camera.setParameters(parametres);
	 
	    try {
	        // On attache notre previsualisation de la camera au holder de la
	        // surface
	        camera.setPreviewDisplay(surfaceCamera.getHolder());
	    } catch (IOException e) {
	    }
	 
	    // On lance la previeuw
	    camera.startPreview();
	 
	    estPrevisualise = true;
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		   if (camera != null) {
		        camera.stopPreview();
		        estPrevisualise = false;
		        camera.release();
		    }
	}
	@SuppressWarnings("deprecation")
	public void initialiserCamera() {
	// On attache nos retour du holder à notre activite
	surfaceCamera.getHolder().addCallback(this);

	// On spécifie le type du holder en mode SURFACE_TYPE_PUSH_BUFFERS
	surfaceCamera.getHolder().setType(
			SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	    camera = Camera.open();
	}
	 
	// Mise en pause de l'application
	@Override
	public void onPause() {
	    super.onPause();
	 
	    if (camera != null) {
	        camera.release();
	        camera = null;
	    }
	}
	private void sauvegarderImage() {
	    try {
		        Camera.PictureCallback memoireImage = new Camera.PictureCallback() {
		        	 
		            public void onPictureTaken(byte[] donnees, Camera camera) {
		            //	File sdCard = Environment.getExternalStorageDirectory();
				        SimpleDateFormat formatDate = new SimpleDateFormat(
				                "yyyy-MM-dd-HH.mm.ss");
				        String nomFichier = "photo_" + formatDate.format(new Date())
				                + ".jpg";
		            	System.out.println("erreur1 : "+Environment.getExternalStorageDirectory());
		    			File fichier = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
				    	        "/DCIM/", nomFichier);
		     
		    //Convertion 
		    			InputStream donneesStream = new ByteArrayInputStream(donnees);
		    			//BitmapFactory.Options opt = new BitmapFactory.Options();
		    			//System.out.println("taill" +data.length);
		    			Bitmap imageBitmap = BitmapFactory.decodeStream(donneesStream);
		    			FileOutputStream sortieDuFichier;
		            	System.out.println("erreur1:bonjour");

		    			try {
		    				if (!fichier.exists()) {
		    					System.out.println("erreur1 : test");
		    					fichier.createNewFile();
		    					System.out.println("erreur1 : sauf ");
		    				}
		    				System.out.println("erreur1 : " +fichier);
		    				sortieDuFichier = new FileOutputStream(fichier);
		    				System.out.println("erreur1:bonjour2");
		    				BufferedOutputStream sortieStream = new BufferedOutputStream(sortieDuFichier);
		    				imageBitmap.compress(CompressFormat.JPEG, 100, sortieStream);
		    				sortieStream.flush();
		    				sortieStream.close();
		    			} catch (FileNotFoundException e) {
		    				// TODO Auto-generated catch block
		    				System.out.println("erreur1");
		    				e.printStackTrace();
		    			} catch (IOException e) {
		    				System.out.println("erreur2");

		    				// TODO Auto-generated catch block
		    				e.printStackTrace();
		    			}
		    		}
		    	};

		        camera.takePicture(null, null, memoireImage);
	    } catch (Exception e) {
	        // TODO: handle exception
	        System.out.println("MARRE");
	    }
	
	}
	private void finir()
	{
		this.finish();
	}
	
}
