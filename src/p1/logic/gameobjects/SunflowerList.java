package p1.logic.gameobjects;

import p1.logic.gameobjects.Sunflower;
import p1.logic.Game;
public class SunflowerList {
	
	private Sunflower sunflowers[];
	private int count;
	
	public SunflowerList() {
		this.count = 0;
		this.sunflowers = new Sunflower[32];
	}
	
	public void Nuevo(Game game, int col, int row) {
		this.sunflowers[count] = new Sunflower(game, col, row);
		this.count+= 1;
	}
	public boolean isPositionEmpty(int col, int row) { //comprueba si la posicion esta vacia
		if(this.count > 0) {
			for(int i = 0; i < count; i++) {
				if(this.sunflowers[i].isInPosition(col, row)) {
					
					return false;
				}
			}
		}
		return true;
	}
	public String getSunflowerInPosition(int col, int row) {
		int i = 0; 
		String str = new String();
		String str2 = new String();
		while((i < count) && str.equalsIgnoreCase(str2)) {
			str = sunflowers[i].toString(col, row);
			i++;			
		}
		
		return str;
	}
	
	public void Update() { // recorre todo el array llevando a cabo el método update en cada sunflower de la lista
		if(count > 0) {
			for(int i = 0; i < count; i++) {
				sunflowers[i].Update();				
			}			
		}		
	}
	
	public void AtaqueZombie(int col, int row) {//recorre el array, comprueba que no haya ningún muerto, y si lo hay lo elimina del array
		for(int i = 0; i < count; i++) {
			sunflowers[i].AtaqueZombie(col, row);
		}
		for(int i = 0; i < count; i++) {
			if(sunflowers[i].IsAlive() == false) {
				for(int j = i; j < count;j++) {
					sunflowers[j] = sunflowers[j+1];
				}
				count = count -1;
			}
		
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
