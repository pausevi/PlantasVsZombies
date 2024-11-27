package tp1.p2.control.commands;

import static tp1.p2.view.Messages.error;

//import java.util.Locale;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.control.Level;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ResetCommand extends Command {

	private Level level;
	
	private boolean hacerResetNormal = true;

	private long seed;

	public ResetCommand() {
		
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_RESET_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_RESET_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_RESET_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_RESET_HELP;
	}

	@Override
	public ExecutionResult execute(GameWorld game){
		if(!hacerResetNormal)game.reset(this.level, this.seed);
		else game.reset();
		return new ExecutionResult(false);

	}

	@Override
	public Command create(String[] parameters) {
		if(parameters.length == 1) {
			hacerResetNormal = true;
		}
		else {
			String seedParam = "";
			try {
				if (parameters.length ==3) {
					seedParam = parameters[2];
					long seede = Long.parseLong(seedParam);
					hacerResetNormal = false;
					Level l = matchLevel(parameters[1]);
					if(l != null) {						
					   this.level = l;
					   this.seed = seede;					 
					   return this;
					}
				}
			} catch (NumberFormatException nfe) {
				System.out.println(String.format(Messages.SEED_NOT_A_NUMBER_ERROR, seedParam));
				return null;
			}				
			if (parameters.length ==2) {	
				System.out.println(error(Messages.COMMAND_PARAMETERS_MISSING));
				return null;
			}
			
		}
		return this;
	}
	private Level matchLevel(String name){
		name = name.toLowerCase();		
		for(Level p : Level.values()){
			if(p.name().toUpperCase().equals(name.toUpperCase())) {
				return p;
			}
		}
		return null;
	}
}
