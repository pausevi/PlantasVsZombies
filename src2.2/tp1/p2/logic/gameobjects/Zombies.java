package tp1.p2.logic.gameobjects;
import tp1.p2.logic.GameWorld;

public abstract class Zombies extends GameObject{
	private int cicloZ = 0;
	private GameWorld game;
	public Zombies() {}
	
	public Zombies(GameWorld game, int col, int row, int endurance) {
		super(game, col, row, endurance);	
		this.game = game;
	}
		
	@Override
	public void onEnter() {
			
		
	}
	
	@Override
	public void onExit() {
		game.zombieMuerto();			
	}
	
	@Override
	public boolean receiveZombieAttack(int damage) {		
		return false;
	}
	
	@Override
	public boolean receivePlantAttack(int damage){
		if(isAlive()) {	
			this.hp -= damage;
			return true;
		}
		return false;
	}
	
	@Override
	public boolean catchObject(int col, int row) {
		return false;
	}
	
	public int getCost() {
		return 0;
	}
	
	public void update(GameWorld game) {
		game.AttackZombie(getCol()-1, getRow(), this.getDamage());
		if(this.isAlive() && game.IsPositionEmpty(this.getCol()-1, this.getRow())) {
			if(cicloZ >0  && cicloZ % getnextStepCycles() == 0 ) {
				this.col--;
			}
		}
		if(getCol() < 0) {
			game.zombieHasArrived();
		}		
		cicloZ++;
	}
	
	abstract protected Zombies copy(GameWorld game, int col, int row);
	
	abstract public String getName();
	
	abstract public String getSymbol();
	
	protected abstract int getnextStepCycles();

	protected abstract int getDamage();
	
	public boolean isInPosition(int col, int row) {
		return this.getCol() == col && this.getRow() == row;
	}
	public boolean isAllInPosition(int col, int row) {
		return this.getCol() == col && this.getRow() == row;
	}
}
