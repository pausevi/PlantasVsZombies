package tp1.p2.logic.gameobjects;

import tp1.p2.logic.Game;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;
import static tp1.p2.view.Messages.plantDescription;
public class Peashooter extends Plant{ 
	
	public static final int cost=50;
	public static final int damage=1;
	public static final int endurance=3;
	
	Peashooter() {}
	
	Peashooter(GameWorld game, int col, int row) {		
		super(game, col, row, Peashooter.endurance);
		this.game = game;
	}
	
	public String getName() {
		return Messages.PEASHOOTER_NAME;
	}
	
	public int getCost() {
		return Peashooter.cost;
	}
	
	@Override
	public String getDescription() {		
		return  plantDescription(Messages.PEASHOOTER_NAME_SHORTCUT, Peashooter.cost, Peashooter.damage, Peashooter.endurance );		
	}
	
	@Override
	protected Plant copy(GameWorld game, int col, int row) {
		Plant p = new Peashooter(game, col, row);
		return p;
	}

	@Override
	public String getSymbol() {
		return Messages.PEASHOOTER_SYMBOL;
		
	}
	
	@Override
	public void update(GameWorld game) {	
		int columna = this.getCol()+1; boolean haAtacado = false;
		while( columna < Game.NUM_COLS && haAtacado== false) {
			haAtacado = game.AttackPlant(columna, this.getRow(), Peashooter.damage);
			columna++;
		}
	}

	


	
}
