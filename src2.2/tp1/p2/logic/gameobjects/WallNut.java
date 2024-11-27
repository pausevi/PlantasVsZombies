package tp1.p2.logic.gameobjects;

import static tp1.p2.view.Messages.plantDescription;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class WallNut extends Plant{

	public static final int cost=50;
	public static final int damage=0;
	public static final int endurance=10;
	public WallNut(GameWorld game, int col, int row) {
		super(game, col, row, WallNut.endurance);
		this.game = game;
	}

	public WallNut() {
		
	}

	@Override
	public int getCost() {
		return WallNut.cost;
	}

	@Override
	protected Plant copy(GameWorld game, int col, int row) {
		Plant s = new WallNut(game, col, row);
		return s;
	}

	@Override
	public String getName() {
		return Messages.WALL_NUT_NAME;
	}

	@Override
	public String getSymbol() {
		return Messages.WALL_NUT_SYMBOL;
	}

	@Override
	public String getDescription() {
		return  plantDescription(Messages.WALL_NUT_NAME_SHORTCUT, WallNut.cost, WallNut.damage, WallNut.endurance );
		
	}


}
