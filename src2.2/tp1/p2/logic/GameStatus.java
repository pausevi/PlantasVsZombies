package tp1.p2.logic;

public interface GameStatus {

	abstract int getCycle();

	abstract int getSuncoins();
	
	abstract int getRemainingZombies();

	abstract boolean IsPlayerQuits();
	
	abstract boolean IsPlayerDead();
	
	abstract String positionToString(int col, int row);

	abstract boolean TodosLosZombiesMuertos();
	
	/**
	 * Get the number of generated suns.
	 * 
	 * @return the number of generated suns
	 */
	int getGeneratedSuns();

	/**
	 * Get the number of caught suns.
	 * 
	 * @return the number of caught suns
	 */
	int getCaughtSuns();

	
	// TODO add your code here
}
