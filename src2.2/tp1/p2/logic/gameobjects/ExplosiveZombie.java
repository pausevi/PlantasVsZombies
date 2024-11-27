package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;
import static tp1.p2.view.Messages.zombieDescription;

public class ExplosiveZombie extends Zombies{
	
	private GameWorld game;	
	public static final int damage=1;
	public static final int endurance=5;
	public static final int speed=2;
	private static final int dañoExplosión = 3;

	public ExplosiveZombie() {
			
	}
	public ExplosiveZombie(GameWorld game, int col, int row) {
			super(game, col, row, ExplosiveZombie.endurance);
			this.game = game;
	}
	
	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	protected Zombies copy(GameWorld game, int col, int row) {
		if(game.PosicionValidaZombie(col, row)) {
			ExplosiveZombie z = new ExplosiveZombie(game,col, row);		
			return z;
		}
		else return null;
	}
	
	@Override
	public String getName() {
		return Messages.EXPLOSIVE_ZOMBIE_NAME;
	}
	
	@Override
	public String getSymbol() {
		return Messages.EXPLOSIVE_ZOMBIE_SYMBOL;
	}
	
	
	@Override
	protected int getnextStepCycles() {		
		return ExplosiveZombie.speed;
	}
	
	@Override
	protected int getDamage() {
		return ExplosiveZombie.damage;
	}
	
	@Override
	public String getDescription() {		
		return zombieDescription(Messages.EXPLOSIVE_ZOMBIE_NAME, ExplosiveZombie.speed, ExplosiveZombie.damage, ExplosiveZombie.endurance);
	}
	
	
	public void hasReceivedDamage() {							
		game.Explosion(this, this.getCol(), this.getRow(), ExplosiveZombie.dañoExplosión);						
	}		
	
	@Override
	public boolean receivePlantAttack(int damage){
		if(this.isAlive()) {
			this.hp -= damage;
			if(this.hp <= 0) {
				hasReceivedDamage();
			}
			return true;
		}
		return false;
	}
}
