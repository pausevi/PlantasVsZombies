package tp1.p2.logic.gameobjects;

import static tp1.p2.view.Messages.plantDescription;

import tp1.p2.logic.Game;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class CherryBomb extends Plant{

	public static final int cost=50;
	public static final int damage=10;
	public static final int endurance=2;
	private int cicloCherry = 1;
	
	public CherryBomb(GameWorld game, int col, int row) {
		super(game, col, row, CherryBomb.endurance);
	}

	public CherryBomb() {
		
	}

	@Override
	public int getCost() {		
		return CherryBomb.cost;
	}

	@Override
	protected Plant copy(GameWorld game, int col, int row) {
		Plant s = new CherryBomb(game, col, row);
		return s;
	}

	@Override
	public String getName() {
		return Messages.CHERRY_BOMB_NAME;
	}

	@Override
	public String getSymbol() {
		if(cicloCherry == 3) {
			return Messages.CHERRY_BOMB_SYMBOL.toUpperCase();
		}
		else {
			return Messages.CHERRY_BOMB_SYMBOL;
		}
	}

	@Override
	public String getDescription() {
		return  plantDescription(Messages.CHERRY_BOMB_NAME_SHORTCUT, CherryBomb.cost, CherryBomb.damage, CherryBomb.endurance );
		
	}

	@Override
	public void update(GameWorld game) {
		if(cicloCherry == 3) {			
			for(int i = getCol()-1; i < getCol()+2 && i < Game.NUM_COLS; i++) {
				for(int j = getRow()-1 ; j < getRow() +2 && j < Game.NUM_ROWS;j++) {
						game.AttackPlant(i, j, CherryBomb.damage);				
				}
			}
			this.hp-= CherryBomb.damage;		
		}		
		cicloCherry++;	
	}

}
