package tp1.p2.control.commands;

import static tp1.p2.view.Messages.error;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.Game;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class AddPlantCommand extends Command implements Cloneable {

	private int col;
	private int row;
	private String plantName;
	private boolean consumeCoins;
	
	public AddPlantCommand() {		 
		super(true);
	}

	public AddPlantCommand(boolean consumeCoins) {
		this.consumeCoins = consumeCoins;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_ADD_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_ADD_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_ADD_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_ADD_HELP;
	}

	@Override
	public ExecutionResult execute(GameWorld game) {		
		Plant plant = PlantFactory.spawnPlant(plantName, game, col, row);	
		if(game.PosicionValidaPlanta(col, row)) {
			if(game.SePuedeComprar(plant.getCost())) {
				game.addGameObject(plant, col, row);	
				game.QuitaSoles(plant.getCost());
				game.update();
				consumeCoins = true;
				return new ExecutionResult(false);			
			}
			else System.out.println(error(Messages.NOT_ENOUGH_COINS));
		}
		else System.out.println(error(Messages.INVALID_POSITION));		
		return null;
	}

	@Override
	public Command create(String[] parameters) {
		if(parameters.length == 4) {
			try {				
				this.col = Integer.parseInt(parameters[2]);
				this.row = Integer.parseInt(parameters[3]);	
				plantName = parameters[1];
				if(col < Game.NUM_COLS && col >= 0 && row < Game.NUM_ROWS && row >= 0 ) {
					if(PlantFactory.isValidPlant(plantName) == true ) {
						return this;
					}
					else {
						System.out.println(error(Messages.INVALID_GAME_OBJECT));
					}
				}
				else {
					System.out.println(error(Messages.INVALID_POSITION));
				}				
			}
			catch (Exception e) {				
				System.out.println(error(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER));    
				System.out.println(" ");
			}
		}
		else if(parameters.length < 4){
			System.out.println(error(Messages.COMMAND_PARAMETERS_MISSING));
		}
		else {
			System.out.println(error(Messages.INVALID_COMMAND));
		}
		return null;
	}
}
