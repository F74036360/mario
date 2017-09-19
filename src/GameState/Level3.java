package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import Main.GamePanel;
import Man.Attack;
import Man.BadGuy;
import Man.BadGuy2;
import Man.Hero;
import Man.Stuff;
import Man.SuperBad;
import Man.coin;
import TileMap.BackGround;

import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Level3 extends GameState {
	
	private Hero hero;
	private BackGround bg;
	private double masktimestart;
	private double rainL;
	private double peeledtime;
	private double maskend;
	private Attack[] banana=new Attack[5];
	private Attack[] badL=new Attack[5];
	
	private int supercount=0;
	private boolean superbananaed;
	private boolean peeled;
	private boolean masked;
	private boolean hearted;
	private boolean setrain=false;
	private int life;
	private int templife;
	private int tempbad=0;
	private int badlife;
	private boolean bumpstone;
	private boolean jump;
	private SuperBad bad2;
	
	private Color color_title;
	private Font font;
	long endTime;
	long StartTime;
	public static long deltatime;
	public Level3(GameStateManager gsm)
	{
		this.gsm=gsm;
		init();
	}
	@Override
	public void init() {
		rainL=System.currentTimeMillis();
		deltatime=0;
		endTime = System.currentTimeMillis();
		StartTime = System.currentTimeMillis();
		System.out.println("Start:"+StartTime);
		badlife=100;
		life=100;
		hero=new Hero("/BackGround/mouse.gif",50,270);
		bg=new BackGround("/BackGround/grassbg1.png",1);
		hero.bound=350;
		
		
		bad2=new SuperBad("/BackGround/bigbad.gif",500,100);
		bad2.vertical=true;
		
		for(int i=0;i<5;i++)
		{
			banana[i]=new Attack("/BackGround/banana.gif",1500,0);
			badL[i]=new Attack("/BackGround/L.gif",-10+i*20,(int)bad2.y+20);
			badL[i].dx=-2;
			badL[i].y=10;
			badL[i].x=30+i*80;
		}
		
		
	
	
		color_title=new Color(128,0,0);
		int style = Font.BOLD | Font.ITALIC;  
		font=new Font("Jokerman", Font.PLAIN, 20);
		
	
	
	}

	public void collision_banana(Attack banana,SuperBad bad2)
	{
		if(banana.y<bad2.y+bad2.height&&banana.y>bad2.y&&bad2.x<600&&banana.x<bad2.x+bad2.width&&banana.x>bad2.x&&bad2.bumped==false)
		{
			tempbad++;
			if(tempbad>=20)
			{
				badlife-=5;
				tempbad=0;
			}
			
			
			
		}
		
	}
	
	public void collision_hero(Attack[] badL)
	{
		for(int i=0;i<5;i++)
		{
			if(badL[i].y+40>hero.y&&badL[i].y+40<hero.y+hero.height&&badL[i].x>hero.x&&badL[i].x<hero.x+hero.width)
			{
				templife++;
				if(templife>50)
				{
					life-=2;
					templife=0;
				}
				
			
						
			}
		}
		
		
		
		
	}
	
	@Override
	public void KeyPressed(int k) {
		
		
		if(k==KeyEvent.VK_UP)
		{
			hero.dy=-5;
			hero.jumping=true;
		}
		if(k==KeyEvent.VK_DOWN)
		{
			hero.dy=5;
		}
		if(k==KeyEvent.VK_RIGHT)
		{
			hero.dx=2;
	
		}
		
		if(k==KeyEvent.VK_LEFT)
		{
			hero.dx=-2;

			
		}
	
		if(k==KeyEvent.VK_SPACE)
		{
			if(superbananaed&&supercount<10)
			{
				
				banana[0].x=(int)hero.x+20;
				banana[0].y=(int)hero.y+20;
				banana[0].dx=2;
				banana[0].dy=0;
				
				System.out.println(banana[1].superbanana);
				banana[1].x=(int)hero.x+20;
				banana[1].y=(int)hero.y+20;
				banana[1].dx=2;
				banana[1].dy=-2;
				
				banana[2].x=(int)hero.x+10;
				banana[2].y=(int)hero.y-20;	
				banana[2].dx=1;
				banana[2].dy=-2;
				
				
				banana[3].x=(int)hero.x-20;
				banana[3].y=(int)hero.y-20;	
				banana[3].dx=3;
				banana[3].dy=0;
				
				
				banana[4].x=(int)hero.x-20;
				banana[4].y=(int)hero.y+20;
				banana[4].dx=3;
				banana[4].dy=1;
				
				supercount++;
				
					
			}
			
			else
			{
				banana[0].x=(int)hero.x+20;
				banana[0].y=(int)hero.y+20;	
				banana[0].dx=5;
			}
			
		}
		if(k==KeyEvent.VK_Q)
		{
			if(Store.banana_peel)
			{
				peeled=true;
				bad2.peel_attacked=true;
				Store.banana_peel=false;
				peeledtime=System.currentTimeMillis();
			}
			
		}
		if(k==KeyEvent.VK_W)
		{
			if(Store.super_banana)
			{
				superbananaed=true;
				for(int i=0;i<5;i++)
				{
					banana[i].superbanana=true;
				}
				
				Store.super_banana=false;
			}
		}
		if(k==KeyEvent.VK_E)
		{
			if(Store.mask)
			{
				masktimestart=System.currentTimeMillis();
				masked=true;
				hero.masked=true;
				java.net.URL imgURL = getClass().getResource("/BackGround/protect.gif");
				ImageIcon icon = new ImageIcon(imgURL);
				Image test=icon.getImage();
				hero.test=test;
				Store.mask=false;
			}
		}
		if(k==KeyEvent.VK_R)
		{
			if(Store.heart_life)
			{
				hearted=true;
				life+=50;
				Store.heart_life=false;
			}
		}
		
	}
	
	@Override
	public void KeyReleased(int k) 
	{
		hero.y=270;
		hero.dy=0;
		hero.dx=0;
		hero.jumping=false;
	
		
	}
	
	
	
	

	
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(!masked) collision_hero(badL);
		for(int i=0;i<5;i++)
		{
			if(badL[i].x<-30&&bad2.x>0&&bad2.x<600&&!peeled)
			{
				badL[i].x=(int)bad2.x;
				badL[i].y=(int)bad2.y+20;
				
			}
			
		}
		
		for(int i=0;i<5;i++)
		{
			collision_banana(banana[i],bad2);
			banana[i].update();
			badL[i].update();
		}
		
		
		hero.update();
		bg.update();
		bad2.update();
		double temp=deltatime;
		endTime = System.currentTimeMillis();
		deltatime=(endTime-StartTime)/1000;
		maskend=(endTime-masktimestart)/1000;
		if(((endTime-peeledtime)/1000)>5)
		{
			peeled=false;
			bad2.peel_attacked=false;
		}
		if(maskend>=5)
		{
			System.out.println("end mask");
			hero.masked=false;
			masked=false;
		}
		
		if(deltatime%2==0&&temp!=deltatime)
		{
			Random ran=new Random();
			
			bad2.x=ran.nextInt(5)*40+300;
			bad2.y=ran.nextInt(5)*30+100;
			temp=deltatime;
		}
		
		
		double rain=(endTime-rainL)/1000;
		if(rain>3&&rain<6)
		{
			
			for(int i=0;i<5;i++)
			{
				badL[i].bumped=false;
				System.out.println("into rain");
				badL[i].superbanana=true;
				badL[i].dy=2;
				
				badL[i].dx=0;
				if(!setrain)
				{
					badL[i].y=10;
					badL[i].x=30+i*80;
					
				}
			}
			if(rain==5)setrain=true;
		}
		for(int i=0;i<5;i++)
		{
			if(badL[i].y==400)
			{
				badL[i].x=-40;
				badL[i].dx=-2;
				badL[i].dy=0;
				badL[i].y=0;
			
			}
		}
		
		
		if(life==0)
		{
			Restart.reset=true;
			Restart.win=false;
			gsm.setState(GameStateManager.Restart);
		}
		if(badlife==0)
		{
			Restart.reset=true;
			Restart.win=true;
			gsm.setState(GameStateManager.Restart);
		}
	
	
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		bg.draw(g);
		java.net.URL imgURL = getClass().getResource("/BackGround/back.png");
		ImageIcon icon = new ImageIcon(imgURL);
		Image test=icon.getImage();
	
		g.drawImage(test, 10, 80, 50,250, null);
		g.setColor(color_title);
		g.setFont(font);
		
		g.drawString("Hero's Life "+life, 180, 50);
		g.drawString("Time "+deltatime, 50,50);
		g.drawString("Nono's Life "+badlife,400,50);
		bad2.draw(g);
		
	
		hero.draw(g);
		
		for(int i=0;i<5;i++)
		{
			badL[i].draw(g);
			banana[i].draw(g);
		}
	}

	
}