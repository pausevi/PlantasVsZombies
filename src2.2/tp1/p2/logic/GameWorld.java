package tp1.p2.logic;


import tp1.p2.control.Level;
import tp1.p2.logic.gameobjects.GameObject; 

public interface GameWorld {

	public static final int NUM_ROWS = 4;

	public static final int NUM_COLS = 8;

	abstract public void playerQuits();

	abstract public boolean addGameObject(GameObject gameOb, int col, int row);
	
	abstract public boolean PosicionValidaPlanta(int col, int row);
	
	abstract public boolean PosicionValidaZombie(int col, int row);

	abstract public void reset(Level level, long seed);
	
	abstract public void reset();
	
	abstract public void update();	
	
	abstract public boolean AttackPlant(int col, int row, int damage);	
	
	abstract public boolean AttackZombie(int col, int row, int damage);	
	
	abstract public boolean TodosLosZombiesMuertos();
	
	abstract public boolean SePuedeComprar(int sun) ;
	
	abstract public boolean IsPositionEmpty(int col, int row);

	abstract public void QuitaSoles(int sun);
	
	abstract public void zombieMuerto();
	
	abstract public void zombieHasArrived();

	public abstract void nuevoZombie();
	
	public abstract int getCiclo();

	public boolean tryToCatchObject(int col, int row);

	abstract public void ADD(GameObject object, int col, int row);

	abstract public void Explosion(GameObject object, int col, int row, int damage);
	
	public void RecogerSoles(int col, int row);
	
	public void ZombiesGenerados();
	
	public void NuevoSun();
}
