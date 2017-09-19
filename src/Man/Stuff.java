package Man;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class Stuff {
	public BufferedImage image;
	public double x;
	public double y;
	private double dy;
	public double dx;
	public int width;
	public int height;
	public double moveScale;

	public Stuff(String s,int x,int y)
	{
		this.x=x;
		this.y=y;
		width=200;
		height=80;
		try{
			image=ImageIO.read(getClass().getResourceAsStream(s));
			
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
		x+=dx;
		y+=dy;

	}
	
	public void draw(Graphics2D g)
	{
		g.drawImage(image,(int)x,(int)y,width,height,null);
	}
	
}
