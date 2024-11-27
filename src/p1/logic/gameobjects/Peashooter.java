package p1.logic.gameobjects;

import p1.logic.Game;
import  p1.view.Messages;

public class Peashooter {
	
	private Game game;
	
	private int col;
	private int row;
	private int hp;
	
	public static final int cost=50;
	public static final int damage=1;
	public static final int endurance=3;
	
	
	public Peashooter(Game game, int col, int row) {
		this.game = game;
		this.col = col;
		this.row = row;
		this.hp = endurance;
	
		
	}
	
	
	public boolean isInPosition(int col, int row) {			// retorna true si esta en la posicion
		if(this.col == col && this.row == row) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	public boolean IsAlive() {					// comprueba cuando el Peashooter esta vivo
		if(hp> 0) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	public void Update() {					// manda ell disparo en un bucle y llama a  game que comprueba si en esa posicion hay zombie, si hay le hace daño y el disparo muere		
		int pos = this.col;
		while(pos < game.NUM_COLS-1 && game.HayZombiePosicion(pos, this.row) == false) {			
			pos++;			
		}
		if(game.HayZombiePosicion(pos, this.row) == true) {
			game.AtaquePlanta(pos, this.row);			
		}
		
		
	}
	
	
	public static Object getDescription() { //devuleve la descripcion
		String str = new String();
		return str.format(String.format(Messages.PEASHOOTER_DESCRIPTION, cost, damage, endurance));
	}
	
	
	public void PierdeVida(int hp) { //pierde vida que se pasa por referencia
		this.hp = this.hp - hp;
	}
	
	
	public String toString(int col , int row) {	//devuelve P[vida]	
		
		String str = new String();
		
		if(this.col == col && this.row == row) {
			
			return str.format(String.format(Messages.PEASHOOTER_ICON, this.hp));
		}
		return str;
		
	} 
	
	
	void AtaqueZombie(int col, int row) {// se activa si hay un ataque por parte del zombie a la planta
		if(this.col ==  col && this.row == row) {
			this.PierdeVida(Zombie.damage);
		}
	}
	
}
