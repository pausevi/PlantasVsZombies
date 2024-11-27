package tp1.p2.control.commands;

import static tp1.p2.view.Messages.error;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.Game;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Zombies;
import tp1.p2.logic.gameobjects.ZombieFactory;
import tp1.p2.view.Messages;

public class AddZombieCommand extends Command {

	private int zombieIdx;

	private int col;

	private int row;

	public AddZombieCommand() {

	}

	@Override
	protected String getName() {
		return Messages.COMMAND_ADD_ZOMBIE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_ADD_ZOMBIE_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_ADD_ZOMBIE_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_ADD_ZOMBIE_HELP;
	}

	
	@Override
	public ExecutionResult execute(GameWorld game) {
		String ZombieName = ZombieFactory.getAvailableZombies().get(zombieIdx).getName() ;
		Zombies zombie = ZombieFactory.spawnZombie(ZombieName, game, col, row);	
		if(game.PosicionValidaZombie(col, row)) {			
			game.addGameObject(zombie, col, row);	
			game.ZombiesGenerados();
			game.update();
			return new ExecutionResult(false);
				
				
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
				this.zombieIdx = Integer.parseInt(parameters[1]);				
				if(col < Game.NUM_COLS+1 && col >= 0 && row < Game.NUM_ROWS && row >= 0) {					
					if(zombieIdx >=0 && zombieIdx < ZombieFactory.getAvailableZombies().size()) {
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
				System.out.println(error(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER));    			// falta alguna coordenada
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