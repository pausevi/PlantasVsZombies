package p1.logic.gameobjects;

import p1.logic.Game;
import  p1.view.Messages;

public class Sunflower {
	
	private Game game;
	
	private int col;
	private int row;
	private int hp;
	private int countS ;
	public static final int cost=20;
	public static final int damage=0;
	public static final int endurance=1;
	public static final int sunsGenera= 10;
	public static final int cooldown=3;
	
	
	public Sunflower(Game game,int col, int row) {//constructor
		this.game = game;
		this.hp = 1;
		this.col = col;
		this.row =row;
		this.countS = 0;
		
		
	}
	public boolean isInPosition(int col, int row) {// devuelve true si la posicion esta correspondida con el sunflower
		if(this.col == col && this.row == row) {
			return true;
		}
		else {
			return false;
		}
		
	}
	public boolean IsAlive() {//comprueba si esta vivo
		if(this.hp>0) {
			return true;
			
		}
		else {
			return false;
		}
	}
	public void Update() {	// cada vez que el ciclo sea divisible entre su cooldown se generan soles
		if(game.getCiclo() > 0 && this.countS > 0) {
			if((this.countS) % this.cooldown == 0) {
				
				this.countS = -1;
				game.SumaSoles(sunsGenera);
			}
		}
		this.countS++;
		
			
		
	}
	public String toString(int col, int row) {	// devuelve S[hp]
		String str = new String();
		
		if(this.col == col && this.row == row) {	
			
			str= str.format(String.format(Messages.SUNFLOWER_ICON, this.hp));
			
			
			return str;
		}
		
		
		return str;
	}
	public void PierdeVida(int hp) {
		this.hp -= hp;
	}
	public static Object getDescription() { //devuelve la descripcion de sunflower
		String str = new String();
		return str.format(String.format(Messages.SUNFLOWER_DESCRIPTION, cost, damage, endurance));
	}
	void AtaqueZombie(int col, int row) {// se activa si hay un ataque por parte del zombie a la planta
		if(this.col ==  col && this.row == row) {
			this.PierdeVida(Zombie.damage);
		}
	}

}
