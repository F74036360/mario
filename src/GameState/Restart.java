package GameState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import javax.swing.JLabel;

import Main.Game;
import TileMap.BackGround;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class Restart extends GameState{
	BufferedImage img;
	private BackGround bg;
	
	private JLabel JJ;
	private int currentoption=0;
	private String[] option={
			"ReStart",
			"Home",
			"Exit"
	};
	public static boolean reset;
	private Color color_title;
	private Font font_title;
	private Font font;
	public static boolean win;
	public Restart(GameStateManager gsm)
	{
		this.gsm=gsm;
		
		try{
			img=ImageIO.read(getClass().getResourceAsStream("/BackGround/mouse.png"));
			bg=new BackGround("/BackGround/menubg.gif",1);
			bg.Vec_XY(-0.05, 0);
			color_title=new Color(128,0,0);
			font_title=new Font("Jokerman",Font.PLAIN,50);
			font=new Font("Jokerman", Font.PLAIN, 40);
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void init()
	{
		
	}
	
	public void select()
	{
		if(currentoption==0)
		{
			gsm.setState(GameStateManager.LEVEL_1);
			
		}
		else if(currentoption==1)
		{
			Game.w.dispose();
		}
		else
		{
			System.exit(0);
		}
	}
	
	public void KeyPressed(int k)
	{
		if(k==KeyEvent.VK_ENTER)
		{
			select();
		}
		if(k==KeyEvent.VK_UP)
		{
			currentoption--;
			if(currentoption<0)
				currentoption=option.length-1;
		}
		if(k==KeyEvent.VK_DOWN)
		{
			currentoption++;
			if(currentoption==2)currentoption=0;
		}
		
	}
	public void KeyReleased(int k)
	{
		
	}
	public void update()
	{
		bg.update();
		
	}
	public void draw(Graphics2D g)
	{
		bg.draw(g);
		if(!reset)
		{
			g.setColor(color_title);
			g.setFont(font_title);
			g.drawString("You'd Better Run!", 75, 100);
			g.drawString("Money:"+Level1State.money, 200, 180);
			g.setFont(font);
		}
		
		else
		{
			if(win)
			{
				g.setColor(color_title);
				g.setFont(font_title);
				g.drawString("You'd Better Run!", 75, 100);
				g.setColor(Color.GRAY);
				g.drawString("WINNER!", 200, 180);
				g.setFont(font);
			}
			else
			{
				g.setColor(color_title);
				g.setFont(font_title);
				g.drawString("You'd Better Run!", 75, 100);
				g.setColor(Color.GRAY);
				g.drawString("LOSER", 200, 180);
				g.setFont(font);
			}
			
		}
		for(int i=0;i<option.length;i++)
		{
			if(i==currentoption)
			{
				g.setColor(Color.BLACK);
				
			}
			else
			{
				g.setColor(Color.LIGHT_GRAY);
			}
			g.drawString(option[i],250,250+i*60 );
			
		}
		
	}

}
