package Man;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import Main.GamePanel;

public class SuperBad {
	public boolean vertical;
	public Image image;
	public Image temp;
	public double x;
	public double y;
	public double dy;
	public double dx;
	public int width=80;
	public int height=100;
	public double moveScale=-0.5;
	public boolean peel_attacked;
	
	public boolean bumped;
	private String s;
	public ImageIcon icon ;
	public SuperBad(String s,int x,int y)
	{
		this.x=x;
		this.y=y;
		this.s=s;
		java.net.URL imgURL = getClass().getResource(s);
		icon = new ImageIcon(imgURL);
		image=icon.getImage();
		temp=image;
	}
	public boolean collision(double x_man,double y_man)
	{
		boolean collided=false;
		return collided;
	}
	
	public void setXY(double x,double y)
	{
		this.x=(x*moveScale)%GamePanel.WIDTH;
		this.y=(y*moveScale)%GamePanel.HEIGHT;
	}
	public void Vec_XY(double dx,double dy)
	{
		this.dx=dx;
		this.dy=dy;
	}
	
	public void update()
	{
		
		if(peel_attacked)
		{
			x+=0;
			y+=0;
		}
		else
		{
			x+=dx;
			y+=dy;
		}
	}
	
	public void draw(Graphics2D g)
	{
		if(peel_attacked)
		{
			java.net.URL imgURL = getClass().getResource("/BackGround/banana_bad.png");
			icon = new ImageIcon(imgURL);
			image=icon.getImage();
			g.drawImage(image,(int)x,(int)y,width,height,null);
			
		}
		else 
		{
			image=temp;
			g.drawImage(image,(int)x,(int)y,width,height,null);
		}
	}
	
}
