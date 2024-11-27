package tp1.p2.logic.gameobjects;

import static tp1.p2.view.Messages.status;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;


/**
 * Base class for game non playable character in the game.
 *
 */
public abstract class GameObject implements GameItem {

	protected GameWorld game;

	protected int col;

	protected int row;
	
	protected int hp;

	

	GameObject() {
	}
	
	GameObject(GameWorld game, int col, int row, int health) {	
		this.game = game;
		this.col = col;
		this.row = row;
		this.hp = health;				
	}

	public int getCol() {
		return col;
	}
	
	public int getRow() {
		return row;
	}
	
	
	public boolean isAlive() {
		return this.hp>0;
	}

	public String toString() {		
		if (isAlive()) {			
			return status(getSymbol(), this.hp);
		} else {
			return " ";
		}				
	}
	
	
	public Plant spawnPlant(GameWorld game, int col, int row) {
		return null;
	}
	
	abstract public String getSymbol();

	abstract public String getDescription();

	abstract public void update(GameWorld game);
	
	abstract public void onEnter();
	
	abstract public void onExit();
	 
	abstract public boolean isInPosition(int col, int row);
	
	abstract public boolean isAllInPosition(int col, int row);
	
}
