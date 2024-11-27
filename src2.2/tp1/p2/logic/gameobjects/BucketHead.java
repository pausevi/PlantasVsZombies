package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;
import static tp1.p2.view.Messages.zombieDescription;

public class BucketHead extends Zombies{

	public static final int damage=1;
	public static final int endurance=8;
	public static final int speed=4;	
	
	
	public BucketHead(GameWorld game, int col, int row) {
		super(game, col, row, BucketHead.endurance);
		
	}
	
	public BucketHead() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getCost() {
		return 0;
	}
	
	@Override
	protected Zombies copy(GameWorld game, int col, int row) {
		if(game.PosicionValidaZombie(col, row)) {
			BucketHead z = new BucketHead(game,col, row);		
			return z;
		}
		else return null;
	}
	
	@Override
	public String getName() {
		return Messages.BUCKET_HEAD_ZOMBIE_NAME;
	}
	
	@Override
	public String getSymbol() {
		return Messages.BUCKET_HEAD_ZOMBIE_SYMBOL;
	}

	@Override
	protected int getnextStepCycles() {
		
		return BucketHead.speed;
	}
	
	@Override
	protected int getDamage() {
		// TODO Auto-generated method stub
		return damage;
	}
	
	@Override
	public String getDescription() {	
		return zombieDescription(Messages.BUCKET_HEAD_ZOMBIE_NAME, BucketHead.speed, BucketHead.damage, BucketHead.endurance);
	}
}
