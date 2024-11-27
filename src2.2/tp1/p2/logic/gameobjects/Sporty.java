package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import static tp1.p2.view.Messages.zombieDescription;
import tp1.p2.view.Messages;

public class Sporty extends Zombies{

	public static final int damage=1;
	public static final int endurance=2;
	public static final int speed=1;

	
	public Sporty() {}
	public Sporty(GameWorld game, int col, int row) {
		super(game, col, row, Sporty.endurance);
	}
	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	protected Zombies copy(GameWorld game, int col, int row) {
		if(game.PosicionValidaZombie(col, row)) {
			Sporty z = new Sporty(game,col, row);		
			return z;
		}
		else return null;
	}
	@Override
	public String getName() {
		
		return Messages.SPORTY_ZOMBIE_NAME;
	}
	@Override
	public String getSymbol() {
		return Messages.SPORTY_ZOMBIE_SYMBOL;
	}
	
	@Override
	protected int getnextStepCycles() {		
		return Sporty.speed;
	}
	@Override
	protected int getDamage() {		
		return Sporty.damage;
	}
	@Override
	public String getDescription() {
		
		return zombieDescription(Messages.SPORTY_ZOMBIE_NAME, Sporty.speed, Sporty.damage, Sporty.endurance);
	}
	
	
	
}
