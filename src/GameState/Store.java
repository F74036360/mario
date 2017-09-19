package GameState;

import java.awt.*;
import java.awt.event.KeyEvent;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import TileMap.BackGround;
import TileMap.Items;

public class Store extends GameState{
	private Items superbanana;
	private Items protect_mask;
	private Items heart;
	private Items peel;
	private BackGround bg;
	private boolean bought[]=new boolean[4];
	public static boolean super_banana=false;
	public static boolean mask=false;
	public static boolean heart_life=false;
	public static boolean banana_peel=false;
	Font font2 =new Font("Jokerman", Font.PLAIN, 24);
	
	private int currentoption=0;
	private String[] option={
			"super banana $200","protecting mask $400","life $200","Banana peel $200","OK"
	};

	
	public Store(GameStateManager gsm)
	{
		this.gsm=gsm;
		superbanana=new Items("/BackGround/superBanana.gif",120,70);
		protect_mask=new Items("/BackGround/protect.png",120,140);
		heart=new Items("/BackGround/life.gif",120,210);
		peel=new Items("/BackGround/peel.png",120,280);
		try{
			bg=new BackGround("/BackGround/menubg.gif",1);
			bg.Vec_XY(-0.05, 0);
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void init()
	{
		
	}
	
	public void select()
	{
		switch(currentoption)
		{
		case 0:
			if(bought[0]==false)
			{
				Level1State.money-=200;
				if(Level1State.money<0)
				{
					java.net.URL imgURL = getClass().getResource("/BackGround/grassbg1.gif");
					ImageIcon icon = new ImageIcon(imgURL);
					JFrame temp=new JFrame("caution");
					
					temp.setSize(600, 400);
					JLabel l=new JLabel();
					l.setSize(600,400);
					l.setIcon(icon);
					l.setFont(font2);
					temp.add(l);
					temp.setVisible(true);
					Level1State.money+=200;
					
				}
				else
				{
					super_banana=true;
					bought[0]=true;
				}
					
			}
			
			break;
		case 1:
			if(bought[1]==false)
			{
				Level1State.money-=400;
				if(Level1State.money<0)
				{
					java.net.URL imgURL = getClass().getResource("/BackGround/grassbg1.gif");
					ImageIcon icon = new ImageIcon(imgURL);
					JFrame temp=new JFrame("caution");
					
					temp.setSize(600, 400);
					JLabel l=new JLabel();
					l.setSize(600,400);
					l.setIcon(icon);
					l.setFont(font2);
					temp.add(l);
					temp.setVisible(true);
					Level1State.money+=400;
					
				}
				else 
					{
						bought[1]=true;
						mask=true;
					}
			}
			
			break;
		case 2:
			if(bought[2]==false)
			{
				Level1State.money-=200;
				if(Level1State.money<0)
				{
					java.net.URL imgURL = getClass().getResource("/BackGround/grassbg1.gif");
					ImageIcon icon = new ImageIcon(imgURL);
					JFrame temp=new JFrame("caution");
					
					temp.setSize(600, 400);
					JLabel l=new JLabel();
					l.setSize(600,400);
					l.setIcon(icon);
					l.setFont(font2);
					temp.add(l);
					temp.setVisible(true);
					Level1State.money+=200;
					
				}
				else 
					{
					bought[2]=true;
					heart_life=true;
					}
			}
			
			break;
		case 3:
			if(bought[3]==false)
			{
				Level1State.money-=200;
				if(Level1State.money<0)
				{
					java.net.URL imgURL = getClass().getResource("/BackGround/grassbg1.gif");
					ImageIcon icon = new ImageIcon(imgURL);
					JFrame temp=new JFrame("caution");
					
					temp.setSize(600, 400);
					JLabel l=new JLabel();
					l.setSize(600,400);
					l.setIcon(icon);
					l.setFont(font2);
					temp.add(l);
					temp.setVisible(true);
					Level1State.money+=200;
					
				}
				else 
				{
					banana_peel=true;
					bought[3]=true;
				}
			}
			
			
			break;
		case 4:
			gsm.setState(GameStateManager.LEVEL3);
			break;
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
			if(currentoption==5)currentoption=0;
		}
		
	}
	public void KeyReleased(int k)
	{
		
	}
	public void update()
	{
		bg.update();
		heart.update();
		peel.update();
		protect_mask.update();
		superbanana.update();
	}
	public void draw(Graphics2D g)
	{
		
		bg.draw(g);
		heart.draw(g);
		peel.draw(g);
		protect_mask.draw(g);
		superbanana.draw(g);
		g.setColor(Color.DARK_GRAY);
		g.drawString("STORE", 250, 50);
		g.drawString("$: "+Level1State.money, 450, 50);
		for(int i=0;i<option.length;i++)
		{
			if(i==currentoption)
			{
				g.setFont(font2);
				g.setColor(Color.BLACK);
				
				
			}
			else
			{
				g.setColor(Color.GRAY);
				
				g.setFont(font2);
			}
			g.drawString(option[i],180,100+i*70 );
			
		}
		
		
	}

	
}
