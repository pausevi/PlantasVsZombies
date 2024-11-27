package tp1.p2.logic.gameobjects;


import tp1.p2.logic.GameWorld;



public abstract class Plant extends GameObject  {
	
	
	protected Sunflower sun;
	
	protected Peashooter peash;
	
	protected GameWorld game;
	
	public Plant(){
		//this.sun = new Sunflower(); si se quedase con new sunflower seria creacion doble de sunflower y peashooter
		//this.peash = new Peashooter();
	}
	
	public Plant(GameWorld game, int col, int row, int health){
		super(game, col, row, health);
		this.game = game; 
	}
	
	public Plant spawnPlant(GameWorld game, int col, int row) {		
	
		Plant plant = PlantFactory.spawnPlant(getName(), game , col, row);

		return plant;
	}
		
	@Override
	public void onEnter() {
		
	}
	@Override
	public void onExit() {
		
	}
	abstract public int getCost();
	
	abstract protected Plant copy(GameWorld game, int col, int row);
	
	abstract public String getName();
	
	@Override
	public boolean receiveZombieAttack(int damage) {		
		if(isAlive()) {	
			this.hp -= damage;
			return true;
		}
		return false;
	}
	
	@Override
	public boolean receivePlantAttack(int damage){
		return false;
	}
	
	@Override
	public boolean catchObject(int col, int row) {
		return false;
	}
	
	public boolean isInPosition(int col, int row) {
		return this.getCol() == col && this.getRow() == row;
	}
	
	public boolean isAllInPosition(int col, int row) {
		return this.getCol() == col && this.getRow() == row;
	}
	
	public void update(GameWorld game) {
		
	}
}



