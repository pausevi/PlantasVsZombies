package tp1.p2.logic.actions;

import tp1.p2.logic.Game;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.GameObject;

public class ExplosionAction implements GameAction {

	private int col;

	private int row;

	private int damage;
	
	public ExplosionAction() {}
	public ExplosionAction(GameObject object, int col, int row, int damage) {
		this.col = col;
		this.row = row;
		this.damage = damage;
	}

	@Override
	public void execute(GameWorld game) {		
		int columna = this.col -1;  
		int fila = this.row -1;	 
		for(int i = columna; i < col+2 && i < Game.NUM_COLS; i++) {
			for(int j = fila; j < row +2 && j < Game.NUM_ROWS;j++) {
				//System.out.println(i +" "+ j + " soy zombie con daño " + damage);
				game.AttackZombie(i, j, damage);
			}
		}
	}
		

}