package TileMap;

import java.awt.Graphics2D;
import java.awt.Image;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;



public class Items {
	public int x;
	public int y;

	public int width=40;
	public int height=40;
	public boolean vertical;
	public ImageIcon icon ;
    public Image test;
    
	public Items(String s,int x,int y)
	{
		java.net.URL imgURL = getClass().getResource(s);
		icon = new ImageIcon(imgURL);
		test=icon.getImage();
	
		this.x=x;
		this.y=y;
	
		
	}

	
	public void update()
	{
		
	}
	
	public void draw(Graphics2D g)
	{
		
		g.drawImage(test, (int)x, (int)y , width,height,null);	

	}
	
	
	
}
