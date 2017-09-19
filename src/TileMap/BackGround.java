package TileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.*;

import Main.GamePanel;
public class BackGround {
	private BufferedImage image;
	private double x , y , dx , dy;
	private double moveScale;
	public BackGround(String s, double ms)
	{
		try{
			image=ImageIO.read(getClass().getResourceAsStream(s));
			moveScale=ms;
			
			
		}catch(Exception e){
			if(image==null)System.out.println("NULLINPUT");
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
		
		g.drawImage(image,(int)x,(int)y,null);
		if(x<0)
		{
			
			g.drawImage(image,(int)x +GamePanel.WIDTH, (int)y, null);
		}
		if(x>0)
		{
			g.drawImage(image,(int)x -GamePanel.WIDTH ,(int)y,null);
		}
	}
	
}
