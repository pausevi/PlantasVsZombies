package tp1.p2.logic;

import java.util.Random;

import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.logic.gameobjects.Sunflower;

import java.util.ArrayDeque;
import java.util.Deque;
import tp1.p2.view.Messages;
import tp1.p2.control.Command;
import tp1.p2.control.Level;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.logic.actions.Add;

public class Game implements GameStatus, GameWorld {	
		
		
		public static final int SUNSINICIALES = 50;
		
		private int suns;
		
		private boolean playerQuits = false ;	
		
		private boolean playerDead = false;

		private long seed;
		
		private Random rand;
		
		private Level level;
		
		private ZombiesManager zombieM;
		
		private SunsManager sunsM;
		
		private int ciclo;
		
		private Deque<GameAction> actions;
		
		private GameObjectContainer gamec;
		
		public Game() {
			
		}
		
		public Game(Level level, long seed){//contructor
			reset(level, seed);	
		}
		
		@Override
		public int getCycle() {
			
			return this.ciclo;
		}

		@Override
		public int getSuncoins() {
			
			return this.suns;
		}
		public boolean IsPlayerDead() {
			return playerDead;
		}		
		
		@Override
		public void playerQuits() {
			this.playerQuits = true;
		}
		public boolean IsPlayerQuits() {
			return playerQuits;
		}
		
		public boolean IsFinished() {
			if(IsPlayerDead() || IsPlayerQuits() || TodosLosZombiesMuertos())return true;
			else return false;
		}
		
		 public boolean IsPositionEmpty(int col, int row) {//comprueba si la posicion esta vacia
			 
			 return gamec.isPositionEmpty(col, row);			
				
		}
		 @Override
		public void update() {

			// 1. Execute pending actions
			executePendingActions();

			// 2. Execute game Actions
			// TODO add your code here

			// 3. Game object updates
			// TODO add your code here
			zombieM.addZombie();
			sunsM.update(this);
			gamec.update(this);	

			// 4. & 5. Remove dead and execute pending actions
			boolean deadRemoved = true;
			while (deadRemoved || areTherePendingActions()) {
				// 4. Remove dead
				deadRemoved = this.gamec.removeDead();
				// 5. execute pending actions
				executePendingActions();
			}

			// 6. Notify commands that a new cycle started
			
			this.ciclo++;
			Command.newCycle();	

		}

		private void executePendingActions() { // ejecuta las acciones pendientes
			while(!this.actions.isEmpty()) {
				GameAction action = this.actions.removeLast();
				action.execute(this);
				
			}
		}

		private boolean areTherePendingActions() {
			return this.actions.size() > 0;
		}

		 	 
		public String positionToString(int col, int row) { 	
				String s = " ";
				s = gamec.positionToString(col, row);
				return s;
					
		}				
		public int getCiclo() {
			return this.ciclo;
		}
		
		public int getRemainingZombies() {
			return zombieM.getRemainingZombies();
		}
				
		public void zombieMuerto() { //cada vez que un zombie muere se descuenta un zombie--
			zombieM.zombieMuerto();	
		}
		
		public void nuevoZombie() { //cada vez que un zombie es creado un la cantidad aumenta
			zombieM.nuevoZombie();
		}
		
		public void zombieHasArrived() { //si zombie llega a la casilla -1 el juego acaba	
			playerDead = true;
		}
		
		public boolean TodosLosZombiesMuertos() {	//todos los zombies generados aleatoriamente por el juego y no por el jugador mueren 	
			return zombieM.TodosLosZombiesMuertos();		 						 
		}
		
		public void ZombiesGenerados() { //cada vez que el jugador genera un zombie predefinido en una col y row elegidas por el zombiesGenerados++
			zombieM.ZombiesGenerados();
		}
		 
		 public boolean execute(Command command) {
			if(command.execute(this) != null) return true;			 
			else return false;		 
		 }

		@Override
		public boolean SePuedeComprar(int sun) {			
			return sun <= suns;			
		}

		
		@Override
	    public boolean PosicionValidaPlanta(int col, int row) {
			 return(gamec.isPositionEmpty(col, row)) 	 	;			 
		}
		 
		 
		@Override
		public boolean PosicionValidaZombie(int col, int row) {
			 if( col < Game.NUM_COLS+1 && col >= 0 && row < Game.NUM_ROWS && row >= 0) {
				 return(gamec.isPositionEmpty(col, row));								
			 }
			return false;
		}
		 	   	 
		@Override		
		public boolean addGameObject(GameObject object, int col, int row) {	
			gamec.addGameObject(object);		
			return true;		
		}
		
		public void QuitaSoles(int sun) {
			this.suns -=sun;
		}


		public boolean AttackPlant( int col, int row, int damage) { 
			if(!this.IsPositionEmpty(col, row)) { 
				GameObject object = gamec.getGameItemPosition(col, row);
				return object.receivePlantAttack(damage);
			}
			return false;
		}
		
		public boolean AttackZombie( int col, int row, int damage) {			
			if(!this.IsPositionEmpty(col, row)) {
				GameObject object = gamec.getGameItemPosition(col, row);
				return object.receiveZombieAttack(damage);
			}
			return false;
		}
		
		
		@Override
		public void reset(Level level, long seed) {
			this.seed = seed;
			this.level = level;
			this.rand = new Random(seed);
			this.zombieM = new ZombiesManager(this, this.level, this.rand);	
			this.sunsM= new SunsManager(this, rand); 
			this.gamec = new GameObjectContainer();
			this.ciclo = 0;
			this.actions = new ArrayDeque<GameAction>(); 
			this.suns = SUNSINICIALES;
			System.out.println(String.format(Messages.CONFIGURED_LEVEL, level.name()));
			System.out.println(String.format(Messages.CONFIGURED_SEED, seed));
		}

		@Override
		public void reset() {
			reset(this.level, this.seed);
		}

		@Override
		public int getGeneratedSuns() {
			return sunsM.getGeneratedSuns();
			
		}

		@Override
		public int getCaughtSuns() {
			return sunsM.getCatchedSuns();
		}
		@Override
		public void ADD(GameObject object, int col, int row) {
			actions.addLast( new Add(object, col, row));			
		}
		
		public void Explosion(GameObject object, int col, int row, int damage) {			
			actions.addLast(new ExplosionAction(object, col, row, damage));	
		}


		@Override
		public boolean tryToCatchObject(int col, int row) {
			 return gamec.tryToCatchObject(col, row); //comrpueba que es posible hacer el catch			
		}


		@Override
		public void RecogerSoles(int col, int row) {
			int soles = 0;
			soles =  gamec.RecogerSoles(col, row);	
			sunsM.NuevoSunsCatched(soles); //cuenta los soles catcheds
			this.suns += soles*Sunflower.sunsGenera;
		}
		
		public void NuevoSun() { //cada vez que un sol es añadido por sunflower; sun++
			sunsM.addSun();			
		}
	
}
