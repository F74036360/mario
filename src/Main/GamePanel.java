package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
 
import javax.swing.*;

import GameState.GameStateManager;

public class GamePanel extends JPanel implements Runnable, KeyListener{
	//size
	public static int WIDTH=600;
	public static int HEIGHT=400;
	public static int SCALE=2;
	//thread
	private Thread thread;
	private boolean running;
	
	
	private GameStateManager gsm;
	
	//image
	private BufferedImage image;
	public Graphics2D g;
	
	public GamePanel()
	{
		super();
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		setFocusable(true);
		requestFocus();
		
	}
	
	public void addNotify()
	{
		super.addNotify();
		if(thread==null)
		{
			thread=new Thread(this);
			addKeyListener(this);
			
			thread.start();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	@Override
	public void keyPressed(KeyEvent key) {
		// TODO Auto-generated method stub
		gsm.KeyPressed(key.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent key) {
		// TODO Auto-generated method stub
		gsm.KeyReleased(key.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		init();
		while(running)
		{
			
			update();
			draw();
			drawToScreen();
		}
	}
	
	private void update()
	{
		gsm.update();
		
	}
	
	private void draw()
	{
		gsm.draw(g);
	}
	
	private void drawToScreen()
	{
		Graphics g2=getGraphics();
		g2.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE,null);
		g2.dispose();
	}
	private void init()
	{
		image=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		g=(Graphics2D) image.getGraphics();
		running=true;
		gsm=new GameStateManager();
	}

	
	
	
}
