package tp1.p2.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.view.Messages;

public class GameObjectContainer {

	private List<GameObject> gameObjects;

	public GameObjectContainer() {
		gameObjects = new ArrayList<>();
	}

	public String positionToString(int col, int row) {
		StringBuilder buffer = new StringBuilder();
		boolean sunPainted = false;
		boolean sunAboutToPaint = false;
		for (GameObject object : gameObjects) {
			if(object.isAlive() && object.getCol() == col && object.getRow() == row) {
				String objectText = object.toString();
				sunAboutToPaint = objectText.indexOf(Messages.SUN_SYMBOL) >= 0;
				if (sunAboutToPaint) {
					if (!sunPainted) {
						buffer.append(objectText);
						sunPainted = true;
					}
				} else {
					buffer.append(objectText);
				}
			}
		}

		return buffer.toString();
	}

	public boolean tryToCatchObject(int col, int row) {
		for(GameObject object : gameObjects) {			
			if(object.catchObject(col, row)) {
				return true;
			}
		}
		return false;
	}
	
	public int RecogerSoles(int col, int row) {
		int soles = 0;
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i).catchObject(col, row)) {			
				gameObjects.remove(i);
				soles++;
			}
		}
		return soles;
	}

	public void addGameObject(GameObject object) {
			gameObjects.add(object);			
			object.onEnter();						
	}

	public boolean isPositionEmpty(int col, int row) {
		for(GameObject game : gameObjects) {			
			if(game.isInPosition(col , row)) {
				return false;
			}
		}
		return true;		
	}
	
	
	public GameObject getGameItemPosition(int col, int row) {//devolverá el objeto de la fila y la columna
		for(GameObject object: gameObjects) {
			if(object.isAllInPosition(col, row))
				return object;
		}
		return null;
	}
	
	public void update(GameWorld game) {		
		for(GameObject object: gameObjects) {
			if(object.isAlive()) object.update(game);			
		}		
	}
	
	public void Clear() {
		gameObjects.clear();
	}
	
	public boolean removeDead() {
		List<GameObject> objects = new ArrayList<>();	
		boolean res = false;
		for(int i = 0; i < gameObjects.size(); i++) {
			if(gameObjects.get(i).isAlive()) {	
				objects.add(gameObjects.get(i));
			}
			else {
				gameObjects.get(i).onExit();
				res = true;
			}
		}
		gameObjects = objects;
		return res;
	}
	
}
