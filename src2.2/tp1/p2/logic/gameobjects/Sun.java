package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;


public class Sun extends GameObject{
	
	private static int cicloSun = 10;
		
	public Sun(GameWorld game, int col, int row) {
		super(game,col, row, cicloSun);
		//this.game = game;
	}
	
	@Override
	public boolean catchObject(int col, int row) {
		if(col == getCol()  && row == getRow()) {
			return true;
		}
		return false;
	}
	
	@Override
	public String getSymbol() {
		
		return Messages.SUN_SYMBOL;
	}
	@Override
	public String getDescription() {
		return null;
	}
	
	@Override
	public void onEnter() {
		// TODO Auto-generated method stub		
	}
	
	@Override
	public void onExit() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(GameWorld game) {
		
		this.hp-= 1;
		if(this.hp == 0) {
			this.hp-= this.hp;
		}
	}

	public boolean isInPosition(int col, int row) {
		return false;
	}
	
	public boolean isAllInPosition(int col, int row) {
		return this.getCol() == col && this.getRow() == row;
	}

	@Override
	public boolean receiveZombieAttack(int damage) {		
		return false;
	}
	
	@Override
	public boolean receivePlantAttack(int damage){
		return false;
	}
	
}
