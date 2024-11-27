package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;
import static tp1.p2.view.Messages.plantDescription;


public class Sunflower extends Plant{ 
		
	
	public static final int cost=20;
	public static final int damage=0;
	public static final int endurance=1;
	public static final int sunsGenera= 10;
	private static final int cooldown=3;
	private int cicloS = 1;
	
	Sunflower() {};
	
	public Sunflower(GameWorld game, int col, int row) {
		super(game, col, row, Sunflower.endurance);
		this.game = game;
	};

	public String getName() {
		return Messages.SUNFLOWER_NAME;
	}
	
	public int getCost() {
		return Sunflower.cost;
	}

	@Override
	protected Plant copy(GameWorld game, int col, int row) {
		Plant s = new Sunflower(game, col, row);
		return s;
	}


	@Override
	public String getSymbol() {
		return Messages.SUNFLOWER_SYMBOL;
	}

	@Override
	public String getDescription() {	
			return  plantDescription(Messages.SUNFLOWER_NAME_SHORTCUT, Sunflower.cost, Sunflower.damage, Sunflower.endurance );		
	}

	@Override
	public void update(GameWorld game){
		if(cicloS != 0 && cicloS % Sunflower.cooldown ==0) {
			new Sun(this.game, col, row);			
			game.NuevoSun(); 
			cicloS = 0;
		}
		cicloS++;
	}

	


	


	


}

