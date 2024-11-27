package tp1.p2.control.commands;

import static tp1.p2.view.Messages.error;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.Game;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class CatchCommand extends Command {

	private static boolean caughtSunThisCycle = false;

	private int col;

	private int row;

	public CatchCommand() {
		caughtSunThisCycle = false;
	}
	
	@Override
	protected void newCycleStarted() {
		caughtSunThisCycle = false;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_CATCH_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_CATCH_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_CATCH_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_CATCH_HELP;
	}

	
	
	@Override
	public ExecutionResult execute(GameWorld game) {
		if(caughtSunThisCycle == false) {	
			if(game.tryToCatchObject(col, row)) {
				game.RecogerSoles(col, row);
				caughtSunThisCycle = true;
				return new ExecutionResult(false);
			}
			else {
				System.out.println(error(Messages.NO_CATCHABLE_IN_POSITION.formatted(col, row)));
			}
		}
		else{
			System.out.println(error(Messages.SUN_ALREADY_CAUGHT));
		}
		return null;		
	}

	
	
	@Override
	public Command create(String[] parameters) {
		if(parameters.length == 3) {
			try {				
				this.col = Integer.parseInt(parameters[1]);
				this.row = Integer.parseInt(parameters[2]);	
				if(col < Game.NUM_COLS && col >= 0 && row < Game.NUM_ROWS && row >= 0 ) {
					return this;
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