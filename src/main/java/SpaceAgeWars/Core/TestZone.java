package SpaceAgeWars.Core;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.jogamp.opengl.GL.*;
import static com.jogamp.opengl.fixedfunc.GLLightingFunc.GL_SMOOTH;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static com.jogamp.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;

/**
 * Created by bodyi on 3/18/2017.
 */
@SuppressWarnings("serial")
public class TestZone extends GLCanvas implements GLEventListener {
	private GLU glu;
	private float x = 0;
	private float y = 0;
	private int texture;
	float aa = 0.01f;
	private int textureHeight = 0;
	private int textureWidth = 0;
	private String filename = "C:\\Users\\bodyi\\Desktop\\a.png";

	public TestZone() {
		this.addGLEventListener(this);
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		glu = new GLU();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glClearDepth(1.0f);
		// set clear depth value to farthest
		//gl.glEnable(GL_DEPTH_TEST); // enables depth testing
		//gl.glDepthFunc(GL_LEQUAL);  // the type of depth test to do
		//gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); // best perspective correction
		gl.glShadeModel(GL_SMOOTH);

		gl.glEnable(GL2.GL_TEXTURE_2D);
		try{

			File im = new File(filename);
			Texture t = TextureIO.newTexture(im, true);
			texture= t.getTextureObject(gl);

		}catch(IOException e){
			e.printStackTrace();
		}

		try {
			updateTextureDimentions();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void dispose(GLAutoDrawable glAutoDrawable) {

	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL2 gl = drawable.getGL().getGL2();
		if (height == 0) height = 1;
		float aspect = (float)width / height;
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL_PROJECTION);
		gl.glLoadIdentity();

		glu.gluPerspective(45.0, aspect, 0.1, 100.0);

		gl.glMatrixMode(GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glClearColor(0f, 0f, 0f, 0f);
		gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();

		gl.glTranslatef(-0.5f, -0.5f, -1.0f);
		System.out.println(aa);
		gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2f(0, 0);
		gl.glVertex2f(x , y );
		gl.glTexCoord2f(1, 0);
		gl.glVertex2f(x + 1 , y );
		gl.glTexCoord2f(1, 1);
		gl.glVertex2f(x + 1 , y + 1 );
		gl.glTexCoord2f(0, 1);
		gl.glVertex2f(x , y + 1 );
		gl.glEnd();
		gl.glFlush();

	}

	private void updateTextureDimentions() throws IOException
	{
		BufferedImage bimg = ImageIO.read(new File(filename));
		textureWidth = bimg.getWidth();
		textureHeight = bimg.getHeight();
	}

}

