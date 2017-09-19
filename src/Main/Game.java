package Main;

import javax.swing.JFrame;

import Man.Hero;

public class Game {
	public static JFrame w=new JFrame("RUNNN!");
	public static void main(String[] args)
	{
		
		w.setContentPane(new GamePanel());
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setResizable(false);
		w.pack();
		w.setVisible(true);
		
	}

}
