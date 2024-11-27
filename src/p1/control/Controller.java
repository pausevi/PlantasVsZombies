package p1.control;

import static p1.view.Messages.*;

import java.util.Scanner;
import java.util.logging.Level;

import p1.logic.Game;
import p1.view.GamePrinter;
import p1.view.Messages;

/**
 * Accepts user input and coordinates the game execution logic.
 *
 */
public class Controller {

	private Game game;

	private Scanner scanner;

	private GamePrinter gamePrinter;
	
	

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		this.gamePrinter = new GamePrinter(game);
	}

	/**
	 * Draw / Paint the game.
	 */
	private void printGame() {
		System.out.println(gamePrinter);
	}

	/**
	 * Prints the final message once the match is finished.
	 */
	public void printEndMessage() {
		System.out.println(gamePrinter.endMessage());
	}

	/**
	 * Show prompt and request command.
	 *
	 * @return the player command as words
	 */
	private String[] prompt() { //ESTA FUNCION RECOGE LAS ENTRADAS DEL USUARIO
		System.out.print(Messages.PROMPT);
		String line = scanner.nextLine();
		String[] words = line.toLowerCase().trim().split("\\s+");

		System.out.println(debug(line));

		return words;
		
	}

	/**
	 * Runs the game logic.
	 */
	public void run() {
		
		printGame();
		while(game.PlayerQuits() == false) {
			
			String[] op = prompt();
			int col = 0, row=0;
			switch (op[0].toLowerCase()){
				
				case "a":
				case "add" :
					
					try {
					
						col = Integer.parseInt(op[2]);
						row = Integer.parseInt(op[3]);
						switch(op[1].toLowerCase()) {
						
						case "peashooter":   			//desea añadir peashooter
						case "p":
							
								if(game.IsPositionEmpty(col, row) && (col >= 0 && col<=game.NUM_COLS) && (row >= 0 && row<= game.NUM_ROWS)) { // hay que comprobar que la posicion esta vacia y que las coord pasadas son posibles
									if(game.SolesSuficientes(op[1])==true) { 				// comprobar si tenemos soles suficientes
										game.NuevoPeashooter(col, row);  					// llama game para crear un peashooter
										game.Update();										// actauliza el juego
										System.out.println();
										printGame();										// imprime el juego
									}
									else {
										System.out.println(Messages.NOT_ENOUGH_COINS);      // salta la excepcion NO HAY SUFICIENTES MONEDAS :(
										System.out.println();							// como no se considera una accion no llama a update
									}
								}
								else {
									System.out.println(Messages.INVALID_POSITION);			 // salta la excepcion POSICION INVALIDA :(
									System.out.println();								// como no se considera una accion no llama a update
								}
								break;
							
								
							case "sunflower" : 					//desea añadir sunflower
							case "s" : 
								
								if(game.IsPositionEmpty(col, row)&& (col >= 0 && col<=game.NUM_COLS) && (row >= 0 && row<= game.NUM_ROWS)) {   // hay que comprobar que la posicion esta vacia y que las coord pasadas son posibles
									if(game.SolesSuficientes(op[1]) ==true) {					// comprobar si tenemos soles suficientes
										game.NuevoSunflower(col, row);							// llama game para crear un sunflower				
										game.Update();											// actauliza el juego
										System.out.println();
										printGame();											// imprime el juego
									}
									else {
										System.out.println(Messages.NOT_ENOUGH_COINS);			// salta la excepcion NO HAY SUFICIENTES MONEDAS :(
										System.out.println(" ");								// como no se considera una accion no llama a update
									}
								}
								else{
									System.out.println(Messages.INVALID_POSITION);				// salta la excepcion POSICION INVALIDA :(
									System.out.println(" ");								  	// como no se considera una accion no llama a update
								}
								break;
							
							
							default: 
								System.out.println(Messages.INVALID_GAME_OBJECT);			    // salta la excepcion OBJECT INVALIDO :(
								break;
						}
						break;
					}
					catch (Exception e) {
						
						System.out.println(Messages.COMMAND_PARAMETERS_MISSING);    			// falta alguna coordenada
						System.out.println(" ");
					}
					
					break;
					
					
				case "l":				//"[l]ist: print the list of available plants"	
				case "list":
					System.out.println(Messages.LIST);											
					System.out.println(" ");
					printGame();
					
					
					break;
					
					
				case "r":				//"[r]eset: start a new game"
				case "reset":
					
					game.Reset();
					printGame();
					
					break;
					
					
				case "h":				//"[h]elp: print this help message"
				case "help":
					
					System.out.println(Messages.HELP);
					System.out.println(" ");
					
					break;
					
					
				case "e":				//"[e]xit: terminate the program"
				case "exit":
					game.playerQuitsf(true);  												//llama a game para que active playerQuits
										
					break;
					
				case "n":
				case "none": 
				case "":
					game.Update();
					System.out.println(" ");
					printGame();
					
					break;	
					
				default: 
					System.out.println();
					System.out.println(Messages.UNKNOWN_COMMAND);//poner message
					printGame();
					break;					
			}
			game.PlayerQuits();
		}
		printEndMessage();
		if(game.TodosLosZombiesMuertos() == true) {
			System.out.println(Messages.PLAYER_WINS);
		}
		else if(game.zombieHaLlegado()) {
			System.out.println(Messages.ZOMBIES_WIN);
		}
		else {
			System.out.println(Messages.PLAYER_QUITS);
		}
	}
}
	
		
	
	
