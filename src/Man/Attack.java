package Man;

import java.awt.Graphics2D;
import java.awt.Image;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;



public class Attack {
	public int x;
	public int y;
	public double dx=3;
	public double dy;
	public int width=60;
	public int height=40;
	public boolean vertical;
	public ImageIcon icon ;
    public Image test;
    public boolean superbanana;
    public boolean bumped;
	public Attack(String s,int x,int y)
	{
		java.net.URL imgURL = getClass().getResource(s);
		icon = new ImageIcon(imgURL);
		test=icon.getImage();
	
		this.x=x;
		this.y=y;
	
		
	}

	public void update()
	{
		if(superbanana)
		{
			x+=dx;
			y+=dy;
			if(x>700)x=700;
			if(x<-60)x=-60;
			if(y>400)y=400;
			if(y<-50)y=-50;
			
		}
		else if(!vertical)
		{
			x+=dx;
			if(x>700)x=700;
			if(x<-60)x=-60;
		}
		
		else
		{
			y+=dx;
			if(y<-50)y=-50;
		}
	}
	
	public void draw(Graphics2D g)
	{
		
		g.drawImage(test, (int)x, (int)y , width,height,null);	

	}
	
	
	
}
