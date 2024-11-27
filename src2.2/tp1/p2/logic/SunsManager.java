package tp1.p2.logic;

import java.util.Random;

import tp1.p2.logic.gameobjects.Sun;

public class SunsManager {

	private static final int COOLDOWN_RANDOM_SUN = 5;

	private GameWorld game;

	private Random rand;

	private int cooldown;
	
	private int CatchedSuns;
	
	private int GeneratedSuns;

	public SunsManager(GameWorld game, Random rand) {
		this.game = game;
		this.rand = rand;
		this.cooldown = COOLDOWN_RANDOM_SUN;
	}

	public int getCatchedSuns() {
		return CatchedSuns;		
	}
	
	public int getGeneratedSuns() {		
		return GeneratedSuns;
	}

	public void update(GameWorld game) {
		if (cooldown == 0) {
			addSun();
			cooldown = COOLDOWN_RANDOM_SUN;
		} else {
			cooldown--;
		}
	}

	
	public void NuevoSunsCatched(int suns) {
		CatchedSuns+=suns;
	}
	
	private int getRandomInt(int bound) {
		return this.rand.nextInt(bound);
	}

	public void addSun() {
			int col = getRandomInt(GameWorld.NUM_COLS);
			int row = getRandomInt(GameWorld.NUM_ROWS);
			game.ADD(new Sun(this.game, col, row), col, row);
			GeneratedSuns++;			
	}	
}