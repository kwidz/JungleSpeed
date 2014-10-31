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
	private Boolean isPreview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.print("rentre dans l'appli photo");
		   // On met l'application en plein écran et sans barre de titre
	    getWindow().setFormat(PixelFormat.TRANSLUCENT);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	            WindowManager.LayoutParams.FLAG_FULLSCREEN);
	 
	    isPreview = false;
	 
	    // On applique notre layout
	    setContentView(R.layout.activity_photo);	 
	    // On récupère notre surface pour le preview
	    surfaceCamera = (SurfaceView) findViewById(R.id.surfaceViewCamera);
	 
	    // Méthode d'initialisation de la caméra
	    InitializeCamera();
	    
	    surfaceCamera.setOnClickListener(new OnClickListener() {
	    	 
	        public void onClick(View v) {
	            // On prend une photo
	            if (camera != null) {
	            	System.out.println("detection clic");
	                SavePicture();
			        long t1 = System.currentTimeMillis(); 
	                while(System.currentTimeMillis()<t1+5000); 
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
		if (isPreview) {
	        camera.stopPreview();
	    }
	    // On récupère les parametres de la camera
		 Camera.Parameters parameters = camera.getParameters();  
		   List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();  
		   Camera.Size cs = sizes.get(0);  
		 //On récupére le display afin de connaitre l'orientation du téléphone.
		    Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
		         
		    // On change la taille selon l'orientation du téléphone
		    if(display.getRotation() == Surface.ROTATION_0)
		        {
		            parameters.setPreviewSize(height, width);                          
		            camera.setDisplayOrientation(90);
		             
		        }
		        if(display.getRotation() == Surface.ROTATION_90)
		        {
		            parameters.setPreviewSize(width, height);                          
		        }
		 
		        if(display.getRotation() == Surface.ROTATION_180)
		        {
		            parameters.setPreviewSize(height, width);              
		        }
		 
		        if(display.getRotation() == Surface.ROTATION_270)
		        {
		            parameters.setPreviewSize(width, height);
		            camera.setDisplayOrientation(180);
		        }

		   parameters.setPreviewSize(cs.width, cs.height);  
		   camera.setParameters(parameters);
	 
	    try {
	        // On attache notre previsualisation de la camera au holder de la
	        // surface
	        camera.setPreviewDisplay(surfaceCamera.getHolder());
	    } catch (IOException e) {
	    }
	 
	    // On lance la previeuw
	    camera.startPreview();
	 
	    isPreview = true;
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		   if (camera != null) {
		        camera.stopPreview();
		        isPreview = false;
		        camera.release();
		    }
	}
	@SuppressWarnings("deprecation")
	public void InitializeCamera() {
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
	private void SavePicture() {
	    try {
	    /*	System.out.println("on rentre dans la sauvegarde du fichier");
	        String fileName = "photoProfil" ;
	 
	        // Metadata pour la photo
	        ContentValues values = new ContentValues();
	        values.put(Media.TITLE, fileName);
	        values.put(Media.DISPLAY_NAME, fileName);
	        values.put(Media.DESCRIPTION, "photo de profil");
	        values.put(Media.DATE_TAKEN, new Date().getTime());
	        values.put(Media.MIME_TYPE, "image/jpeg");
	 
	        // Support de stockage
	        System.out.println("a");
	        Uri taken = getContentResolver().insert(Uri.parse("../res/drawable-hdpi"),
	                values);
	        System.out.println("b");

	        System.out.println("taken : " + taken);
	        if(taken == null)
	        {
	        	System.out.println("solution magique marche pas");
	        }
	        else
	        {
		        System.out.println("test : " + Media.EXTERNAL_CONTENT_URI);
		        System.out.println("value : "+values);
		        System.out.println("taken : " + taken);
		        // Ouverture du flux pour la sauvegarde
		        final FileOutputStream stream = (FileOutputStream) getContentResolver().openOutputStream(
		                taken);
		        System.out.println("stream : " + stream);
	*/
		        Camera.PictureCallback pictureCallback = new Camera.PictureCallback() {
		        	 
		            public void onPictureTaken(byte[] data, Camera camera) {
		            //	File sdCard = Environment.getExternalStorageDirectory();
				        SimpleDateFormat timeStampFormat = new SimpleDateFormat(
				                "yyyy-MM-dd-HH.mm.ss");
				        String fileName = "photo_" + timeStampFormat.format(new Date())
				                + ".jpg";
		            	System.out.println("erreur1 : "+Environment.getExternalStorageDirectory());
		    			File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
				    	        "/DCIM/", fileName);
		     
		    //Convertion 
		    			InputStream is = new ByteArrayInputStream(data);
		    			//BitmapFactory.Options opt = new BitmapFactory.Options();
		    			//System.out.println("taill" +data.length);
		    			Bitmap bMap = BitmapFactory.decodeStream(is);
		    			FileOutputStream fileOutputStream;
		            	System.out.println("erreur1:bonjour");

		    			try {
		    				if (!file.exists()) {
		    					System.out.println("erreur1 : test");
		    					file.createNewFile();
		    					System.out.println("erreur1 : sauf ");
		    				}
		    				System.out.println("erreur1 : " +file);
		    				fileOutputStream = new FileOutputStream(file);
		    				System.out.println("erreur1:bonjour2");
		    				BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
		    				bMap.compress(CompressFormat.JPEG, 100, bos);
		    				bos.flush();
		    				bos.close();
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

		        camera.takePicture(null, null, pictureCallback);
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
