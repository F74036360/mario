package Man;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Main.Game;
import Main.GamePanel;

public class Hero extends JPanel{
	public BufferedImage image;
	public BufferedImage img1;
	public BufferedImage img2;
	public double x;
	public double y;
	public double dx;
	public double dy;
	public boolean masked;
	public boolean jumping;
	
	public int width;
	public int height;
	public int bound=225;
	public ImageIcon icon ;
    public Image test;
    public Image temp;
	public Hero(String s,int x,int y)
	{
		java.net.URL imgURL = getClass().getResource(s);
		icon = new ImageIcon(imgURL);
		test=icon.getImage();
		temp=test;
		width=80;
		height=100;
		this.x=x;
		this.y=y;

	}

	public void update()
	{
		x+=dx;
		y+=dy;
		
		if(x>bound)
		{	
			x=bound;
		}
		
		
		
	}
	
	public void draw(Graphics2D g)
	{
		
		if(masked)
		{
			java.net.URL imgURL = getClass().getResource("/BackGround/protect.gif");
			icon = new ImageIcon(imgURL);
			test=icon.getImage();
			g.drawImage(test, (int)x, (int)y , width,height,null);	
			test=temp;
		}
		else if(jumping)
		{
			java.net.URL imgURL = getClass().getResource("/BackGround/jump.png");
			icon = new ImageIcon(imgURL);
			test=icon.getImage();
			g.drawImage(test, (int)x, (int)y , width,height,null);	
			test=temp;
			
		}
		else g.drawImage(test, (int)x, (int)y , width,height,null);	
	}
	
	
	
}
