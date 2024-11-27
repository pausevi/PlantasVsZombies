package tp1.p2.logic.gameobjects;
import static tp1.p2.view.Messages.zombieDescription;


import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Zombie extends Zombies{
	
	public static final int damage=1;
	public static final int endurance=5;
	public static final int speed=2;
	
	
	public Zombie(GameWorld game, int col, int row) {		
		super(game, col, row, Zombie.endurance);	
	}
	public Zombie() {//constructor
		this.col = 8;
	}
	
	public Zombies copy(GameWorld game, int col, int row) {
		if(game.PosicionValidaZombie(col, row)) {
			Zombie z = new Zombie(game,col, row);		
			return z;
		}
		else return null;
	}
	public String getName() {
		return Messages.ZOMBIE_NAME;
	}
	public String getSymbol() {
		return Messages.ZOMBIE_SYMBOL;
	}
	
	
	protected int getSpeed() {
		return Zombie.speed;
	}
	protected int getDamage() {
		return Zombie.damage;
	}
	
	@Override
	public int getCost() {
		return 0;
	}
	@Override
	protected int getnextStepCycles() {
		
		return Zombie.speed;
	}
	@Override
	public String getDescription() {
		
		return zombieDescription(Messages.ZOMBIE_NAME, Zombie.speed, Zombie.damage, Zombie.endurance);
	}	
	


	
}