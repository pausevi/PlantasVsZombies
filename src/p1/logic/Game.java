package p1.logic;
import java.util.Random;

import p1.control.Level;
import p1.logic.gameobjects.Peashooter;
import p1.logic.gameobjects.PeashooterList;
import p1.logic.gameobjects.Sunflower;
import p1.logic.gameobjects.SunflowerList;
import p1.logic.ZombiesManager;
import p1.view.Messages;

public class Game {
	
	public static final int NUM_COLS = 8;

	public static final int NUM_ROWS = 4;
	
	public static final int SUNSINICIALES = 50;
	
	private boolean FIN = false;
	
	private int suns=50; 
	
	private int ciclo=0;

	private long seed;
	
	private Random rand;
	
	private Level level;
	
	private ZombiesManager zombieM;
	
	private SunflowerList sunList;
	
	private PeashooterList peaList;
	
	public Game(long seed, Level level){//contructor
		this.seed = seed;
		this.level=level;
		this.rand = new Random(seed);
		this.zombieM = new ZombiesManager(this, this.level, this.rand);
		this.sunList = new SunflowerList();
		this.peaList = new PeashooterList();
		
	}
	private String getPosicionSunflower(int col, int row) {//devuelve S[hp]
		return sunList.getSunflowerInPosition(col, row);
	}
	private String getPosicionPeashooter(int col, int row) {//devuelve P[hp]
		return peaList.getPeashooterInPosition(col, row);
	}
	private String getPosicionZombie(int col, int row) {//devuelve Z[hp]
		return zombieM.getPosicionZombie(col, row);
	}
	
	public boolean PlayerQuits() {//si zombie ha llegado o no quedan zombies o el jugador decide salir el juego se acaba
		if((this.FIN == true || TodosLosZombiesMuertos() == true)|| zombieM.zombieHaLlegado() == true) {
			return true;
		}
		return false;
	}
	public void playerQuitsf(boolean f) {// para el exit tenga valor y salga del bucle del controller
		this.FIN = f;
	}
	public boolean zombieHaLlegado() {
		if(zombieM.zombieHaLlegado() == true) {
			return true;
		}
		else return false;
	}
	 public boolean IsPositionEmpty(int col, int row) {//comprueba si la posicion esta vacia
			if(!sunList.isPositionEmpty(col, row)) {
				return false;
			}
			else if(!peaList.isPositionEmpty(col, row)) {
				return false;
			}
			else if(!zombieM.isPositionEmpty(col, row)){
				return false;
				
			}
			return true;
		}
	 public String positionToString(int col, int row) { //devuelve P[hp] | S[hp] | Z[hp]
		String str = new String();
		
			if(IsPositionEmpty(col, row)) {
				str= " ";								
			}
			else if (!sunList.isPositionEmpty(col, row)){		
				str = getPosicionSunflower(col, row);
				
			}
			else if(!peaList.isPositionEmpty(col, row)) {
				str = getPosicionPeashooter( col, row);
				
			}
			else if(!zombieM.isPositionEmpty(col, row)) {
				 str= getPosicionZombie(col, row);
				
			}
			
		return str;
		
	}
	 
	 
	public boolean SolesSuficientes(String str) {//comrpueba si los soles son suficiente para el coste de las pantas
		boolean soles = false;
		if(str.equals("s") || str.equals("sunflower")) {
			if(Sunflower.cost <= getSuns()) {
				this.SumaSoles(-Sunflower.cost);
				soles = true;
			}
		}
		else if(str.equals("p") || str.equals("peashooter")) {
			if(Peashooter.cost <= getSuns()) {
				this.SumaSoles(-Peashooter.cost);
				soles = true;
			}
		}
		return soles;
	}
	
	
	public void SumaSoles( int sun) {
		this.suns += sun;
	}
	
	
	public void Reset() {// hace new de todas las listas para resetearlas al igual q los soles y el ciclo
		
		this.zombieM = new ZombiesManager(this, this.level, new Random(seed));
		this.sunList = new SunflowerList();
		this.peaList = new PeashooterList();
		this.suns = this.SUNSINICIALES;
		this.ciclo = 0;
		
	}
	
	
	public int getCiclo() {
		return this.ciclo;
	}
	
	public int getSuns() {
		return this.suns;
	}

	public int remainZombies() {
		return zombieM.getRemainingZombies();
	}
	 public void NuevoSunflower(int col, int row) {
		 
		 sunList.Nuevo(this, col, row);
	 }
	 public void NuevoPeashooter(int col, int row) {
		 peaList.Nuevo(this, col, row);
	 }
	 public void NuevoZombie() {
		 if(level.getNumberOfZombies() > 0) {
			// int row = rand.nextInt();//he cambiado y puse entre parentesis las rows pq si no daba num muy altos
			 zombieM.addZombie();
			
		 }
	 }
	 
	 public void AtaquePlanta(int col, int row) { //tras comrpobar en peashooter si hay que atacar, llama a zombieM para hacer ataque
		 zombieM.AtaquePlanta(col, row);
		 
	 }
	 public boolean HayZombiePosicion(int col, int row) {
		 if(!zombieM.isPositionEmpty(col, row)) {
			 return true;
		 }
		 else {
			 return false;
		 }
	 }
	 public boolean HayPlantaPosicion(int col, int row) {
		 if(!peaList.isPositionEmpty(col, row)) {
			 return true;
		 }
		 else if(!sunList.isPositionEmpty(col, row)){
			 return true;
		 }
		 else return false;
	 }
	
	 
	 public void AtaqueZombie(int col, int row) { //preguntar como recibe daño como saber de que zombie se trata etc
		peaList.AtaqueZombie(col, row);
		sunList.AtaqueZombie(col, row);
		 
		 
		 
		 
	 }
	 public boolean TodosLosZombiesMuertos() {
		 boolean todosMuertos = true;
		
		if(zombieM.getRemainingZombies()> 0 || zombieM.HayZombiesEnTablero() == true) {
			todosMuertos = false;
		}
		 return todosMuertos;
	 }
	
	 public void Update() {
		if(this.PlayerQuits() == false) {
			
			peaList.Update(); 			
			zombieM.Update();
			sunList.Update();
			NuevoZombie();
			
		}
		
		
		this.ciclo = ciclo +1;
		
	 }

}
