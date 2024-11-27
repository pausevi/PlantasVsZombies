package p1.logic;

import java.util.Random;

import p1.control.Level;
import p1.logic.gameobjects.Zombie;
import p1.logic.gameobjects.ZombieList;

/**
 * Manage zombies in the game.
 *
 */
public class ZombiesManager {

	private Game game;

	private Level level;

	private Random rand;

	private int remainingZombies;

	private ZombieList zombies;

	public ZombiesManager(Game game, Level level, Random rand) {
		this.game = game;
		this.level = level;
		this.rand = rand;
		this.remainingZombies = level.getNumberOfZombies();
		this.zombies = new ZombieList(this.remainingZombies);
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
		return rand.nextInt(Game.NUM_ROWS);
		
	}
	
	public boolean addZombie() {//genera un rand para su row
		int row = randomZombieRow();
		return addZombie(row);
	}

	public boolean addZombie(int row) {//añade un zombie
		boolean canAdd = getRemainingZombies() > 0 && shouldAddZombie()
				&& isPositionEmpty(Game.NUM_COLS, row);

		if(canAdd) {
			zombies.Nuevo(game, row);
			this.remainingZombies-= 1;
		}
		return canAdd;
	}

	public boolean isPositionEmpty(int numCols, int row) { //comprueba si la posicion esta vacia
		if(zombies.isPositionEmpty(numCols, row)) {
			return true;
		}
		else {
			return false;
		}
	}

	public int getRemainingZombies() {//devuelve el valor de zombies restantes por salir
		return this.remainingZombies;
	}
	public String getPosicionZombie(int col , int row) {//devuelve el valor de zombies Z[hp]
		return zombies.getZombieInPosition(col, row);
	}
	
	public void Update() {//llama al update de zombieList
		zombies.Update();
	}
	public boolean zombieHaLlegado() {//comrpueba si un zombie ha llegado a la base
		return zombies.zombieHaLlegado();
	}
	public void AtaquePlanta(int col, int row) {//llama a zobieList para ejecutar el ataque planta
		zombies.AtaquePlanta(col, row);
	}
	public boolean HayZombiesEnTablero() {
		return zombies.HayZombiesEnTablero();
	}
}
