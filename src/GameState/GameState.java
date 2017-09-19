package GameState;

import java.awt.*;
public abstract class GameState {
	protected GameStateManager gsm;
	
	public abstract void init();
	public abstract void KeyPressed(int k);
	public abstract void KeyReleased(int k);
	public abstract void update();
	public abstract void draw(Graphics2D g);
}
