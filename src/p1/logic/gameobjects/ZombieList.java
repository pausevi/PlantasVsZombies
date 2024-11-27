package p1.logic.gameobjects;
import p1.logic.Game;
public class ZombieList {
	
	private int count=0;
	private Zombie zombies[];
	
	private int remainingZombies;

	public ZombieList(int remainingZombies) { //constructor
		this.remainingZombies=remainingZombies;
		this.zombies = new Zombie[this.remainingZombies];
	}
	public void Nuevo(Game game, int row) {//crea un nuevo zombie
		this.zombies[count] = new Zombie(game, row);
		this.count+=1;
	}
	public String getZombieInPosition(int col, int row) {//devuelve los Z[hp] que hay en cada posicion
		int i = 0; 
		String str = new String();
		String str2 = new String();
		while((i < count) && str.equalsIgnoreCase(str2)) {
			str = zombies[i].toString(col, row);
			i++;			
		}
		
		return str;
	}
	public boolean isPositionEmpty(int col, int row) {//devuelve true si la posicion esta vacia
		if(count> 0) {
			for(int i = 0; i < count; i++ ) {
				if(zombies[i].isInPosition(col, row)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void Update() {// recorre todo el array llevando a cabo el método update en cada zombie de la lista
		if(count > 0) {
			for(int i = 0; i < count; i++) {
				zombies[i].Update();				
			}						
		}
	}
	public boolean zombieHaLlegado() { // si un zombie han llegado devuelve true
		
		for(int i = 0; i < count; i++ ) {
			if(zombies[i].HaLlegado() == true) {
				return true;
			}
		}
		return false;
	}
	public boolean HayZombiesEnTablero() { // para saber si un zombie esta vivo
		for(int i = 0; i < count; i++ ) {
			if(zombies[i].IsAlive() == true) {
				return true;
			}
		}
		return false;
	}
	public void AtaquePlanta(int col, int row) {//recorre el array, comprueba que no haya ningún muerto, y si lo hay lo elimina del array
		for(int i = 0; i < count; i++) {
			zombies[i].AtaquePlanta(col, row);
		}
		for(int i = 0; i < count; i++) {//si tras recibir daño ha perdido vida se recorre el array y se desplazan los zombies a la izquierda
			if(zombies[i].IsAlive() == false) {
				for(int j = i; j < count;j++) {
					zombies[j] = zombies[j+1];
				}
				count = count -1;
			}
		
		}
	}

}
