package tp1.p2.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import tp1.p2.logic.GameWorld;

public class ZombieFactory {

	
	private static final List<Zombies> ZOMBIE_NAME =Arrays.asList(
			new Zombie(),
			new BucketHead(),
			new Sporty(),
			new ExplosiveZombie()
	);
	public static boolean isValidZombie(int zombieIdx) {
		return zombieIdx >= 0 || zombieIdx < ZOMBIE_NAME.size();
	}


	public static List<Zombies> getAvailableZombies() {
		return Collections.unmodifiableList(ZOMBIE_NAME);
	}

	/*
	 * Avoid creating instances of this class
	 */
	public ZombieFactory() {
	}
	
	
	//////////////////////////
	public static boolean isValidZombie(String zombieName) {
		for(Zombies zomb : ZOMBIE_NAME) {
			if(zomb.getName().toLowerCase().equals(zombieName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
	public static Zombies spawnZombie(String zombieName, GameWorld game, int col, int row) {
		if(isValidZombie(zombieName.toLowerCase())) {
			for(Zombies zomb : ZOMBIE_NAME) {
				if(zomb.getName().toLowerCase().equals(zombieName.toLowerCase())) {
					return zomb.copy(game, col, row);				
				}
			}
		}
		return null;
	}
	
}
