package SpaceAgeWars.Core;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
/**
 * Created by bodyi on 3/18/2017.
 */
public class TestZone2 extends JFrame{
	private static final String TITLE = "TestZone ^-^";
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 640;
	private static final int FPS = 60; // animator's target frames per second


	public TestZone2()
	{
		GLCanvas canvas = new TestZone();
		canvas.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

		final FPSAnimator animator = new FPSAnimator(canvas, FPS, true);

		this.getContentPane().add(canvas);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				new Thread() {
					@Override
					public void run() {
						if (animator.isStarted()) animator.stop();
						System.exit(0);
					}
				}.start();
			}
		});
		this.setTitle(TITLE);
		this.pack();
		this.setVisible(true);
		animator.start();
	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new TestZone2();
			}
		});
	}


}

