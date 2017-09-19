package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import Man.Attack;
import Man.BadGuy;
import Man.Hero;
import Man.Stuff;
import Man.coin;
import TileMap.BackGround;
import java.util.Random;
import javax.imageio.ImageIO;


public class Level1State extends GameState {
	private BadGuy bad1;
	private Hero hero;
	private BackGround bg;
	private Stuff house;
	private Stuff stage;
	private Stuff stone;
	private Attack banana;
	private Attack badL;
	private Attack badL_Vertical;
	public static int money;
	private int life;
	private int templife;

	private boolean bumpstone;
	private boolean jump;
	private BadGuy bad2;
	private coin[] co=new coin[7];
	public BufferedImage image;
	private Color color_title;
	private Font font;
	long endTime;
	long StartTime;
	public static long deltatime;
	public Level1State(GameStateManager gsm)
	{
		this.gsm=gsm;
		init();
	}
	@Override
	public void init() {
		deltatime=0;
		endTime = System.currentTimeMillis();
		StartTime = System.currentTimeMillis();
		System.out.println("Start:"+StartTime);
		templife=0;
		money=1000;
		life=5;
		hero=new Hero("/BackGround/mouse.gif",50,270);
		bg=new BackGround("/BackGround/grassbg1.png",1);
		house=new Stuff("/BackGround/images.png",450,270);
		house.width=150;
		bad1=new BadGuy("/BackGround/E.gif",800,120);
		bad1.vertical=false;
		bad2=new BadGuy("/BackGround/GHOST.gif",1400,100);
		bad2.vertical=true;

		stone=new Stuff("/BackGround/stone.png",1350,200);
		stone.width=150;
		stone.height=200;
		stage=new Stuff("/BackGround/stuff2.png",850,210);
		stage.height=20;
		stage.width=150;
		banana=new Attack("/BackGround/banana.gif",1500,0);
		badL=new Attack("/BackGround/L.gif",(int)bad1.x-40,(int)bad1.y+20);
		badL.dx=-2;
		badL_Vertical=new Attack("/BackGround/L.gif",(int)bad2.x+30,(int)bad2.y-20);
		badL_Vertical.dx=-2;
		badL_Vertical.vertical=true;
		color_title=new Color(128,0,0);
		int style = Font.BOLD | Font.ITALIC;
		font = new Font("Jokerman", Font.PLAIN, 20);

		try{
			image=ImageIO.read(getClass().getResourceAsStream("/BackGround/money.png"));

		}catch(Exception e)
		{
			if(image==null)System.out.println("NULLINPUT");
			e.printStackTrace();
		}
		for(int i=0;i<7;i++)
		{
			co[i]=new coin("/BackGround/bad1.png",150+i*70,250);
			co[i].dy=-0.5;
		}

	}

	public void collision_banana(BadGuy bad,Stuff thing)
	{
		if(banana.y<bad.y+bad.height&&banana.y>bad.y&&bad.x<600&&banana.x<bad.x&&bad.bumped==false)
		{
			bad.x=-100;
			bad.y=-100;
			bad.bumped=true;
			if(bad==bad1)
			{
				for(int i=4;i<6;i++)
				{
					co[i].bump=false;
					co[i].x=thing.x+50*i-150;
					co[i].y=thing.y-co[3].height-20;
					co[i].dy=0;
				}
			}
			else
			{
				co[3].bump=false;
				co[3].x=thing.x+20;
				co[3].y=thing.y-co[3].height-20;
				co[3].dy=0;
			}

		}

	}

	public void collision_hero(Attack badL)
	{

		if(badL.y+40>hero.y&&badL.y+40<hero.y+hero.height&&badL.x>hero.x&&badL.x<hero.x+hero.width)
		{
			templife++;
			if(templife>=40)
			{
				life-=1;
				templife=0;
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
			if(bumpstone==true&&stone.x>0)
			{
				stone.dx=0;
				bad2.dx=0;
				hero.dx=0;
				house.dx=0;
				stage.dx=0;
				for(int i=0;i<7;i++)
				{
					co[i].dx=0;
				}
			}
			else
			{
				stone.dx=-2;
				bad2.dx=-2;
				hero.dx=2;
				house.dx=-2;
				stage.dx=-2;
				for(int i=0;i<7;i++)
				{
					co[i].dx=-2;
				}
			}

		}

		if(k==KeyEvent.VK_LEFT)
		{
			if(bumpstone==true&&stone.x>0)
			{

				stone.dx=0;
				bad2.dx=0;
				hero.dx=0;
				house.dx=0;
				stage.dx=0;
				for(int i=0;i<7;i++)
				{
					co[i].dx=0;
				}

			}
			else
			{
				stone.dx=2;
				bad2.dx=2;
				hero.dx=-2;
				house.dx=2;
				stage.dx=2;
				for(int i=0;i<7;i++)
				{
					co[i].dx=2;
				}

			}


		}

		if(k==KeyEvent.VK_SPACE)
		{
			banana.x=(int)hero.x+20;
			banana.y=(int)hero.y+20;
		}

	}

	@Override
	public void KeyReleased(int k)  {





		bad2.boundmax=200;
		bad2.boundmin=50;


		if(hero.x<20)
		{
			hero.x=20;
			hero.dx=0;
		}
		if(hero.dy!=0)
		{
			hero.jumping=false;
			if(hero.x>house.x-30&&hero.x<house.x+house.width)collision_stuff(house);
			else if(hero.x>stage.x-30&&hero.x<stage.x+stage.width)collision_stuff(stage);
			else if(hero.x>stone.x-50&&hero.x<stone.x+stone.width+50)
			{

				collision_stuff(stone);

			}
			else hero.y=270;


			hero.dy=0;

		}

		else if(hero.dx!=0)
		{

			if(hero.x>house.x-30&&hero.x<house.x+house.width)collision_stuffx(house);
			else if(hero.x>stage.x-30&&hero.x<stage.x+stage.width)collision_stuffx(stage);
			else if(hero.x>stone.x-50&&hero.x<stone.x+stone.width+50)
			{

				collision_stuffx(stone);

			}
			else hero.y=270;

			hero.dx=0;
		}



		house.dx=0;
		stage.dx=0;
		stone.dx=0;
		bad2.dx=0;
		hero.dy=0;
		hero.dx=0;
		for(int i=0;i<7;i++)
		{
			co[i].dx=0;
		}

	}



	public void collision_coin()
	{
		for(int i=0;i<7;i++)
		{
			if(hero.x+hero.width>co[i].x&&hero.y<co[i].y+co[i].height&&hero.x<co[i].x+co[i].width&&co[i].bump==false)
			{

				co[i].bump=true;
				money+=10;
				System.out.println(money);
			}
		}
	}



	public void collision_stuffx(Stuff thing)
	{

		if(hero.x+hero.width>thing.x&&hero.x<thing.x+thing.width&&hero.y+hero.height-30<thing.y)
		{
			hero.y=thing.y-hero.height;
			jump=true;
		}
		else
		{
			hero.y=270;

		}


	}
	public void collision_stuff(Stuff thing)
	{

		if(hero.dx!=0)
		{
			if(hero.x>thing.x)
			{

				if(hero.x+20<thing.x+thing.width)
				{
					if(hero.height-30<thing.y)
					{
						hero.y=thing.y-hero.height;
						if(thing==stone)jump=true;
					}
				}

			}
			else
			{
				hero.y=270;

			}
		}
		else
		{

			if(hero.x+hero.width>thing.x&&hero.x+20<thing.x+thing.width&&hero.height-30<thing.y)
			{
				hero.y=thing.y-hero.height;
			}
			else hero.y=270;

		}


	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

		if(hero.dx!=0)
		{

			if(hero.x>house.x-30&&hero.x<house.x+house.width)collision_stuffx(house);
			else if(hero.x>stage.x-30&&hero.x<stage.x+stage.width)collision_stuffx(stage);
			else if(hero.x>stone.x-50&&hero.x<stone.x+stone.width+50)
			{

				collision_stuffx(stone);

			}
			else hero.y=270;


		}




		if(banana.x>600)
		{
			banana.x=1200;
			banana.y=0;
		}
		if(badL.x<-30&&bad1.x>0&&bad1.x<600)
		{
			badL.x=(int)bad1.x;
			badL.y=(int)bad1.y+20;
		}
		if(badL_Vertical.y<-30&&bad2.x>0)
		{
			badL_Vertical.x=(int)bad2.x+20;
			badL_Vertical.y=(int)bad2.y-20;
		}

		collision_banana(bad1,stage);
		collision_banana(bad2,stone);
		collision_hero(badL);
		collision_hero(badL_Vertical);
		randomstuff() ;
		collision_coin();
		hero.update();
		banana.update();
		bg.update();
		bad1.update();
		bad2.update();
		badL.update();
		badL_Vertical.update();
		stage.update();
		stone.update();
		house.update();
		for(int i=0;i<7;i++)
		{
			co[i].update();
		}


		endTime = System.currentTimeMillis();
		deltatime=(endTime-StartTime)/1000;
		if(deltatime==15)
		{
			gsm.setState(GameStateManager.Store);

		}
		if(life==0)
		{
			gsm.setState(GameStateManager.Restart);
		}


	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		bg.draw(g);
		g.setColor(color_title);
		g.setFont(font);
		g.drawString("Money "+money, 400, 50);
		g.drawString("Life "+life, 250, 50);
		g.drawString("Time "+deltatime, 50,50);
		house.draw(g);
		bad1.draw(g);
		stage.draw(g);
		bad2.draw(g);
		badL.draw(g);
		badL_Vertical.draw(g);

		stone.draw(g);
		for(int i=0;i<7;i++)
		{
			co[i].draw(g);
		}
		hero.draw(g);
		banana.draw(g);
	}

	public void randomstuff()
	{
		if(house.x+house.width<0)
		{

			house.x=1000;
			house.y=270;
			System.out.println("into random");
			co[2].bump=false;
			co[2].x=house.x+20;
			co[2].y=house.y-co[2].height-20;
			co[2].dy=0;

			co[1].bump=false;
			co[1].x=house.x+80;
			co[1].y=house.y-co[1].height-20;
			co[1].dy=-0.01;


		}
		if(stage.x+stage.width<0)
		{
			Random ran=new Random();
			int r=ran.nextInt(2);

			switch(r)
			{
			case 0:
				stage.x=1000;
				stage.y=120;
				bad1.x=700;
				bad1.moveScale=-0.5;


				bad1.y=stage.y-bad1.height;
				bad1.bumped=false;
				break;

			case 1:
				stage.x=1000;
				stage.y=120;

				co[4].bump=false;
				co[4].x=stage.x+20;
				co[4].y=stage.y-co[1].height-20;
				co[4].dy=-0.01;
				co[5].bump=false;
				co[5].x=stage.x+70;
				co[5].y=stage.y-co[1].height-20;
				co[5].dy=-0.01;
				break;

			}


		}
		if(stone.x+stone.width<0)
		{
			stone.x=1000;
			stone.y=200;
			bad2.x=1050;
			bad2.y=200;
			bad2.bumped=false;

		}
	}
}
