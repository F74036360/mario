package Man;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class BadGuy2 {
	public BufferedImage image;
	public BufferedImage temp;
	public double x;
	public double y;
	public double dy;
	public double dx;
	public int width=80;
	public int height=100;
	private double moveScale=-0.5;
	public double boundmin=150;
	public double boundmax=300;
	public boolean bumped;
	private String s;
	public BadGuy2(String s,int x,int y)
	{
		this.x=x;
		this.y=y;
		this.s=s;
		try{
			image=ImageIO.read(getClass().getResourceAsStream(s));
			temp=image;
		}catch(Exception e)
		{
			if(image==null)System.out.println("NULLINPUT");
			e.printStackTrace();
		}
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
		
		y+=moveScale;
		if(bumped)moveScale=0;
		else if(x<boundmin)moveScale=0.5;
		else if(x>=boundmax)moveScale=-0.5;
	}
	
	public void draw(Graphics2D g)
	{
		if(bumped)
		{
			image=null;
			g.drawImage(image,(int)x,(int)y,width,height,null);
			image=temp;
		}
		else g.drawImage(image,(int)x,(int)y,width,height,null);
	}
	
}
