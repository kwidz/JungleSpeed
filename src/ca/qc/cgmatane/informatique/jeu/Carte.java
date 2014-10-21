package ca.qc.cgmatane.informatique.jeu;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import android.util.Log;

import com.example.junglerapide.R;

public class Carte {

	private int image;
	private FloatBuffer vertexBuffer;	// buffer holding the vertices
	private float vertices[];
	private FloatBuffer textureBuffer;	// buffer holding the texture coordinates
	private float texture[] = {    		
			
			0.0f, 1.0f,		
			0.0f, 0.0f,		
			1.0f, 1.0f,		// Affichage texture carte
			1.0f, 0.0f		
	};

	/** The texture pointer */
	private int[] textures = new int[1];
    private Position pos;

	public Carte(Position position, Couleur couleur, Forme forme) {
		// a float has 4 bytes so we allocate for each coordinate 4 bytes
		associerImage(position, couleur, forme);
		positionnement(position);
		gestionBuffer();
		
		
	}

    public void modifierPosition(Position p){
        this.pos = p;
    }
	/**
	 * Affiche l'image de la carte
	 * @param gl
	 * @param context
	 */
	public void loadGLTexture(GL10 gl, Context context) {
		// loading texture
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), image);

		gl.glGenTextures(1, textures, 0);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

		// Clean up
		bitmap.recycle();
	}

	/** La methode permet d'afficher la carte */
	public void draw(GL10 gl) {
		// bind the previously generated texture
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

		gl.glFrontFace(GL10.GL_CW);

		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);


		gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vertices.length / 3);


		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}
	
	/**
	 * positionne la carte sur l'ecran
	 * @param position
	 */
	public void positionnement(Position position)
	{
		if (position.equals(Position.haut))
		{
		
	        float Vertices[] =  {
					-0.15f, 1.3f,  0.0f,		
					-0.15f, 0.9f,  0.0f,		
					 0.25f, 1.3f,  0.0f,			
					 0.25f,  0.9f,  0.0f			
			};
			vertices = Vertices;
		}
		else if (position.equals(Position.bas))
		{
	        float Vertices[] =  {
					-0.15f, -1f,  0.0f,		
					-0.15f, -0.6f,  0.0f,		
					 0.25f, -1f,  0.0f,		
					 0.25f, -0.6f,  0.0f			
			};
			vertices = Vertices;
		}
		else if (position.equals(Position.droite))
		{
	        float Vertices[] =  {
					0.35f, 0.4f,  0.0f,		
					0.35f, 0f,  0.0f,		
					0.75f, 0.4f,  0.0f,		
					0.75f,  0f,  0.0f			
			};
			vertices = Vertices;
		}
		else if (position.equals(Position.gauche))
		{
	        float Vertices[] =  {
					-0.65f, 0f,  0.0f,		
					-0.65f, 0.4f,  0.0f,		
					-0.25f, 0f,  0.0f,		
					-0.25f,  0.4f,  0.0f			
			};
			vertices = Vertices;
		}
		else if (position.equals(Position.centre))
		{
	        float Vertices[] =  {
					-0.15f, 0.4f,  0.0f,		
					-0.15f, 0f,  0.0f,		
					0.25f, 0.4f,  0.0f,		
					0.25f,  0f,  0.0f			
			};
	        vertices = Vertices;
		}
		else if (position.equals(Position.pacHaut))
		{
			 float Vertices[] =  {
						-0.15f, 1.8f,  0.0f,		
						-0.15f, 1.4f,  0.0f,		
						 0.25f, 1.8f,  0.0f,			
						 0.25f, 1.4f,  0.0f			
				};
				vertices = Vertices;
			image = R.drawable.paquet;
		}
		else if (position.equals(Position.pacBas))
		{
	        float Vertices[] =  {
					-0.15f, -1.5f,  0.0f,		
					-0.15f, -1.1f,  0.0f,		
					 0.25f, -1.5f,  0.0f,		
					 0.25f, -1.1f,  0.0f			
			};
			vertices = Vertices;
			image = R.drawable.paquet;
		}
		else if (position.equals(Position.pacDroite))
		{
	        float Vertices[] =  {
					0.85f, 0.4f,  0.0f,		
					0.85f, 0f,  0.0f,		
					1.25f, 0.4f,  0.0f,		
					1.25f,  0f,  0.0f			
			};
			vertices = Vertices;
			image = R.drawable.paquet;
		}
		else if (position.equals(Position.pacGauche))
		{
	        float Vertices[] =  {
					-1.15f, 0f,  0.0f,		
					-1.15f, 0.4f,  0.0f,		
					-0.75f, 0f,  0.0f,		
					-0.75f,  0.4f,  0.0f			
			};
			vertices = Vertices;
			image = R.drawable.paquet;
		}
	}
	
	/**
	 * A completer
	 * Permet l'association d'une image a la carte qui lui correspond
	 * @param position
	 * @param couleur
	 * @param forme
	 */
	
	private void associerImage(Position position,Couleur couleur, Forme forme) // Argumant a modifier par couleur + forme pour selectionner la bonne carte
	{
		if(position.equals(Position.centre))
			image = R.drawable.totem;
		else if(couleur != null && forme != null)
		{
			if (couleur.equals(Couleur.jaune))
			{
				if (forme.equals(Forme.carrerondcarre))
					image = R.drawable.carrerondcarrejaune;
				else if (forme.equals(Forme.rondcarre))
					image = R.drawable.rondcarrejaune;
			}
			else if (couleur.equals(Couleur.orange))
			{
				if (forme.equals(Forme.carrerondcarre))
					image = R.drawable.carrerondcarreorange;
				else if (forme.equals(Forme.rondcarre))
					image = R.drawable.rondcarreorange;
			}
			else if (couleur.equals(Couleur.vert))
			{
				if (forme.equals(Forme.carrerondcarre))
					image = R.drawable.carrerondcarrevert;
				else if (forme.equals(Forme.rondcarre))
					image = R.drawable.rondcarrevert;
			}
			else if (couleur.equals(Couleur.violet))
			{
				if (forme.equals(Forme.carrerondcarre))
					image = R.drawable.carrerondcarreviolet;
				else if (forme.equals(Forme.rondcarre))
					image = R.drawable.rondcarreviolet;
			}
		}
	}
	
	/**
	 * gere les buffers.
	 */
	
	private void gestionBuffer()
	{
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());

		// allocates the memory from the byte buffer
		vertexBuffer = byteBuffer.asFloatBuffer();

		// fill the vertexBuffer with the vertices
		vertexBuffer.put(vertices);

		// set the cursor position to the beginning of the buffer
		vertexBuffer.position(0);

		byteBuffer = ByteBuffer.allocateDirect(texture.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		textureBuffer = byteBuffer.asFloatBuffer();
		textureBuffer.put(texture);
		textureBuffer.position(0);
	}
}