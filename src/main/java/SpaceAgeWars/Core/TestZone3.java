package SpaceAgeWars.Core;

import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by bodyi on 3/18/2017.
 */
public class TestZone3 implements GLEventListener {

	public static DisplayMode dm, dm_old;
	private GLU glu = new GLU();
	private float x,y;
	private int textureWidth;
	private int textureHeight;
	private int texture;
	private String filename = "C:\\Users\\bodyi\\Desktop\\a.png";

	@Override
	public void display(GLAutoDrawable drawable) {

		// TODO Auto-generated method stub
		final GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity(); // Reset The View
		gl.glTranslatef(0f, 0f, -5.0f);


		gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
		gl.glBegin(GL2.GL_QUADS);

		float width = (float)textureWidth/100;
		float height = (float)textureWidth/100;
		System.out.println(width+", "+height);
		gl.glTexCoord2f(0, 0);
		gl.glVertex2f(x , y );
		gl.glTexCoord2f(1,  0);
		gl.glVertex2f(x + width , y );
		gl.glTexCoord2f(1, 1);
		gl.glVertex2f(x + width, y + height);
		gl.glTexCoord2f(0, 1);
		gl.glVertex2f(x , y + height );

		gl.glEnd();
		gl.glFlush();
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
		// method body
	}

	@Override
	public void init(GLAutoDrawable drawable) {

		x=0f;
		y=0f;

		final GL2 gl = drawable.getGL().getGL2();

		gl.glShadeModel(GL2.GL_SMOOTH);
		gl.glClearColor(0f, 0f, 0f, 0f);
		gl.glClearDepth(1.0f);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glDepthFunc(GL2.GL_LEQUAL);
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);

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
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

		// TODO Auto-generated method stub
		final GL2 gl = drawable.getGL().getGL2();
		if(height== 0)
		height = 1;

		final float h = (float) width / (float) height;
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();

		glu.gluPerspective(45.0f, h, 1.0, 20.0);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);

		// The canvas
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		TestZone3 r = new TestZone3();

		glcanvas.addGLEventListener(r);
		glcanvas.setSize(400, 400);

		final JFrame frame = new JFrame (" .svg load test");
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		final FPSAnimator animator = new FPSAnimator(glcanvas, 300, true);

		animator.start();
	}

	private void updateTextureDimentions() throws IOException
	{
		BufferedImage bimg = ImageIO.read(new File(filename));
		textureWidth = bimg.getWidth();
		textureHeight = bimg.getHeight();
	}

}
