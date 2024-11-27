package tp1.p2.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p2.logic.GameWorld;

public class PlantFactory {
	public PlantFactory() {
	}
	
	/* @formatter:off */
	private static final List<Plant> AVAILABLE_PLANTS = Arrays.asList(
		new Sunflower(),
		new Peashooter(),
		new WallNut(),
		new CherryBomb()
	);
	/* @formatter:on */

	public static boolean isValidPlant(String plantName) { //ns si se puede hacer esto
		for (Plant p : AVAILABLE_PLANTS) {
			if (p.getName().toLowerCase().equals(plantName.toLowerCase())||p.getSymbol().toLowerCase().equals(plantName.toLowerCase())) {								
				 
				return true;
			}
		}
		return false;
	}

	public static Plant spawnPlant(String plantName, GameWorld game, int col, int row) {	
		for (Plant p : AVAILABLE_PLANTS) {
			if (p.getName().toLowerCase().equals(plantName.toLowerCase())||p.getSymbol().toLowerCase().equals(plantName.toLowerCase())) {								
		
				if(p != null) {												
					return p.copy(game, col, row);			
				}
				
			}
		}
		return null;
	}

	public static Iterable<Plant> getAvailablePlants() {
		return Collections.unmodifiableList(AVAILABLE_PLANTS);
	}	
	
}
