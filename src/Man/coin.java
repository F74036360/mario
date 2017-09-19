package Man;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class coin {
	public BufferedImage image;
	public BufferedImage temp;
	public double x;
	public double y;
	public double dy;
	public double dx;
	public int width;
	public int height;
	public double moveScale;
	public boolean bump;
	private String s;
	public coin(String s,int x,int y)
	{
		this.s=s;
		this.x=x;
		this.y=y;
		width=40;
		height=40;
		try{
			image=ImageIO.read(getClass().getResourceAsStream(s));
			temp=image;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
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
		if(bump==false)
		{
			
			g.drawImage(image,(int)x,(int)y, width, height, null);
		}
		if(bump==true)
		{
			image=null;
			g.drawImage(image,(int)x,(int)y, width, height, null);
			image=temp;
			
		
		}
		
		
	}
	
}