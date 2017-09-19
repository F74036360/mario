package GameState;
import java.util.ArrayList;
import GameState.GameState;
import GameState.MenuState;
import java.awt.*;

public class GameStateManager {
	public ArrayList<GameState> gameStates;
	private static final int MENUSTATE=0;
	public static final int LEVEL_1=1;
	public static final int Restart=2;
	public static final int Store=3;
	public static final int LEVEL3=4;
	public static final int HOME=5;
	
	private static int currentState;
	public GameStateManager()
	{
		gameStates=new ArrayList<GameState>();
		gameStates.add(new MenuState(this));
		gameStates.add(new Level1State(this));
		gameStates.add(new Restart(this));
		gameStates.add(new Store(this));
		gameStates.add(new Level3(this));
		
	}
	
	public void setState(int state)
	{
		currentState=state;
		gameStates.get(currentState).init();
	}
	
	public void init()
	{
		
	}
	
	public void draw(Graphics2D g)
	{
		gameStates.get(currentState).draw(g);
	}
	
	public void update()
	{
		gameStates.get(currentState).update();
		
	}
	
	public void KeyPressed(int k)
	{
		gameStates.get(currentState).KeyPressed(k);
	}
	
	public void KeyReleased(int k)
	{
		gameStates.get(currentState).KeyReleased(k);
	}
	
}
