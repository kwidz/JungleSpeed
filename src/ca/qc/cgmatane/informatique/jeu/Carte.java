package ca.qc.cgmatane.informatique.jeu;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import com.example.junglerapide.R;

public class Carte {


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

	public Carte(String position, Couleur cl, Forme f) {
		// a float has 4 bytes so we allocate for each coordinate 4 bytes
		positionnement(position);
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

	/**
	 * Affiche l'image de la carte
	 * @param gl
	 * @param context
	 */
	public void loadGLTexture(GL10 gl, Context context) {
		// loading texture
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.carte);

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
	public void positionnement(String position)
	{
		if (position.equals("haut"))
		{
		
	        float Vertices[] =  {
					-0.2f, 1f,  0.0f,		
					-0.2f, 0.8f,  0.0f,		// positionnement de la carte sur l'ecran
					 0.2f, 1f,  0.0f,		
					 0.2f,  0.8f,  0.0f			
			};
			vertices = Vertices;
		}
		else if (position.equals("bas"))
		{
	        float Vertices[] =  {
					-0.2f, 1f,  0.0f,		
					-0.2f, 0.8f,  0.0f,		// positionnement de la carte sur l'ecran
					 0.2f, 1f,  0.0f,		
					 0.2f,  0.8f,  0.0f			
			};
			vertices = Vertices;
		}
		else if (position.equals("droite"))
		{
	        float Vertices[] =  {
					-0.2f, 1f,  0.0f,		
					-0.2f, 0.8f,  0.0f,		// positionnement de la carte sur l'ecran
					 0.2f, 1f,  0.0f,		
					 0.2f,  0.8f,  0.0f			
			};
			vertices = Vertices;
		}
		else if (position.equals("gauche"))
		{
	        float Vertices[] =  {
					-0.2f, 1f,  0.0f,		
					-0.2f, 0.8f,  0.0f,		// positionnement de la carte sur l'ecran
					 0.2f, 1f,  0.0f,		
					 0.2f,  0.8f,  0.0f			
			};
			vertices = Vertices;
		}
	}
}