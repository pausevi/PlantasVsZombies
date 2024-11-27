package tp1.p2.logic.actions;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.GameObject;

public class Add implements GameAction {
	
	private int col;
	private int row;
	private GameObject object;
	public Add(GameObject object, int col, int row) {
		this.col = col;
		this.row = row;
		this.object = object;
	}
	

	@Override
	public void execute(GameWorld game) {
		game.addGameObject(object, this.col, this.row);		
	}	
}