package tp1.p2.logic;

import java.util.Random;

import tp1.p2.control.Level;
import tp1.p2.logic.gameobjects.Zombies;
import tp1.p2.logic.gameobjects.ZombieFactory;
/**
 * Manage zombies in the game.
 *
 */
public class ZombiesManager {

	private GameWorld game; //he cambiado gameworld por game

	private Level level;

	private Random rand;

	private int remainingZombies;
	
	private int zombiesGenerados; // son aquellos generados con el addZombieCommand
	
	private int zombiesAlived;
	
	public ZombiesManager(Game game, Level level, Random rand) { //he cambiado gameworld por game
		this.game = game;
		this.level = level;
		this.rand = rand;
		this.remainingZombies = level.getNumberOfZombies();
		this.zombiesAlived = remainingZombies;
		
	}

	/**
	 * Checks if the game should add (if possible) a zombie to the game.
	 * 
	 * @return <code>true</code> if a zombie should be added to the game.
	 */
	private boolean shouldAddZombie() {
		return rand.nextDouble() < level.getZombieFrequency();
	}

	/**
	 * Return a random row within the board limits.
	 * 
	 * @return a random row.
	 */
	private int randomZombieRow() {
		return rand.nextInt(GameWorld.NUM_ROWS);
	}

	private int randomZombieType() {
		
		return rand.nextInt(ZombieFactory.getAvailableZombies().size());//no funciona si no le pones el +1 hace todo el rato que rand sea 0
	}

	public void update() {
		addZombie();
	}

	public boolean addZombie() {
		int row = randomZombieRow();
		return addZombie(row);
	}

	public boolean addZombie(int row) {		
		
		boolean canAdd = getRemainingZombies() > 0 && shouldAddZombie() && isPositionEmpty(GameWorld.NUM_COLS, row);
		int zombieType = randomZombieType();
		
		if (canAdd) {			
			String name=  ZombieFactory.getAvailableZombies().get(zombieType).getName();
			Zombies z = ZombieFactory.spawnZombie(name, game, GameWorld.NUM_COLS, row);
			if( z != null) {
				game.ADD(z, GameWorld.NUM_COLS, row);
				game.nuevoZombie();
				return true;
			}
			else{return false;}
		}
		return canAdd;
	}

	private boolean isPositionEmpty(int numCols, int row) {
		return game.IsPositionEmpty(numCols, row);	
	}
	
	public void ZombiesGenerados() {
		zombiesGenerados++;
	}
	
	public int getRemainingZombies() {
		return remainingZombies;
	}
	
	public void nuevoZombie() {
		remainingZombies--;
	}
	
	public void zombieMuerto() {		
		if(zombiesGenerados == 0) {
			zombiesAlived--;
		}
		else {
			zombiesGenerados--;
		}
	}
	
	 public boolean TodosLosZombiesMuertos() {	
		 if(zombiesAlived == 0 && getRemainingZombies() == 0) {
			 return true;
		 }
		 else return false;		 						 
	 }
}
	

	
