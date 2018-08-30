package nl.rensmulder.angelsanddemons.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AngelsAndDemonsCMD implements CommandExecutor {
	
	public List<DefaultCommand> commands = new ArrayList<DefaultCommand>();
	
	public AngelsAndDemonsCMD() {
		commands.add(new CreateCMD("create", "Create an arena"));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 0) {
			//TODO: CONNER MAAK LEUK WELKOM DINGETJE
			return false;
		}
		for(DefaultCommand c : commands) {
			if(args[0].equalsIgnoreCase(c.getName())) {
				c.run(args, sender);
				return false;
			}
		} 
		//Stuur help command
		
		return false;
	}
	
	public boolean hasPermission(CommandSender sender) {
		return sender.hasPermission("angelsanddemons.admin");
	}

}
