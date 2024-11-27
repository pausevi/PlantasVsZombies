package p1.logic.gameobjects;

import p1.logic.gameobjects.Peashooter;
import p1.logic.Game;


public class PeashooterList {
	
	
	private Peashooter peashooters[];
	private int count=0;
	
	
	public PeashooterList( ) {//constructor
		this.peashooters = new Peashooter[32];
	}
	
	public void Nuevo(Game game,int col, int row) { // añade nuevo peashooter
		this.peashooters[count] = new Peashooter(game, col, row);
		this.count+= 1;
		
	}
	
	
	public boolean isPositionEmpty(int col, int row) { //comprueba si la posicion esta vacia
		if(this.count> 0) {
			for(int i = 0; i < count; i++) {
				if(this.peashooters[i].isInPosition(col, row) == true) {
					
					return false;
				}
			}
		}
		return true;
	}
	
	
	public String getPeashooterInPosition(int col, int row) { // devuelve P[vida] 
		int i = 0; 
		String str = new String();
		String str2 = new String();
		while((i < count) && str.equalsIgnoreCase(str2)) {
			str = peashooters[i].toString(col, row);
			i++;			
		}
		
		return str;
	}
	
	public void Update() { // recorre todo el array llevando a cabo el método update en cada peashooter de la lista
		if(count > 0) {						
			for(int i = 0; i < count; i++) {
				peashooters[i].Update();				
			}					
		}
	}
	
	
	
	//recorre el array, comprueba que no haya ningún muerto, y si lo hay lo elimina del array
    //dependiendo del ciclo en el que fue generado efectúa disparo si tienen zombie delante
	
	public void AtaqueZombie(int col, int row) {
		for(int i = 0; i < count; i++) {
			peashooters[i].AtaqueZombie(col, row);
		}
		for(int i = 0; i < count; i++) {
			if(peashooters[i].IsAlive() == false) {
				for(int j = i; j < count;j++) {
					peashooters[j] = peashooters[j+1];
				}
				count = count -1;
			}
			
		}
	}
	
}
