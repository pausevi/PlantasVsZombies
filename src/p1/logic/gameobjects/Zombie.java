package p1.logic.gameobjects;

import p1.logic.Game;
import p1.view.Messages;

public class Zombie {
	
	private Game game;
	
	private int col;
	private int row;
	private int hp;
	private int cicloZombie = 1; // se trata del ciclo individual de cada zombie
	public static final int damage=1;
	public static final int endurance=5;
	public static final int speed=1;
	private int nextStepCycles = 2; //cada dos ciclos da un paso
	
	public Zombie(Game game, int row) {//constructor
		this.hp = endurance;
		this.col = 8;
		this.row = row;
		this.game = game;
		
		
	}
	public boolean isInPosition(int col , int row) { // retorna true si esta en la posicion
		if(this.col == col && this.row == row) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean IsAlive() { // comprueba cuando el zombie esta vivo
		if(hp> 0) {
			return true;
		}
		else {
			return false;
		}
	}
	public void Update() {// comprueba si tiene una planta delante, llama a game que llama a peashlist y comprueba
		if(game.HayPlantaPosicion(this.col-1, this.row) == true) {
			game.AtaqueZombie(this.col-1, this.row);
		}
		
		if(this.cicloZombie != 0) {
			if((this.cicloZombie%nextStepCycles == 0) && !game.HayPlantaPosicion(this.col-1, this.row)) {//faltaria poner que no hubiese una planta delante es decir no hay ataque
				this.col = this.col -1;
			}
		}
		this.cicloZombie++;
		
	}
	public String toString(int col, int row) { // devuelve Z[hp] si su col y su row coinciden
		String str = new String();
		
		if(this.col == col && this.row == row) {
			
			str= str.format(String.format(Messages.ZOMBIE_ICON, this.hp));return str;
		}
		return str;
	}
	public void PierdeVida(int hp) { //pierde la vida que se pasa por referencia
		this.hp = this.hp - hp;
	}
	public boolean HaLlegado() {
		if(this.col == -1) {
			return true;
		}
		else {
			return false;
		}
	}
	public void AtaquePlanta(int col, int row) { //se activa si la planta ataca al zombie
		if(this.col == col && this.row == row) {
			this.PierdeVida(Peashooter.damage);
		}
	}
	

}
