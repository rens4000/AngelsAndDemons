package nl.rensmulder.angelsanddemons.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import nl.rensmulder.angelsanddemons.Core;

public class AngelsAndDemonsCMD implements CommandExecutor {
	
	public List<DefaultCommand> commands = new ArrayList<DefaultCommand>();
	
	private Core core;
	
	public AngelsAndDemonsCMD(Core core) {
		this.core = core;
		commands.add(new CreateCMD("create", "Create an arena", "create <name>"));
		commands.add(new SetSpawn("setspawn", "Set the spawn of an arena spawn", "setspawn <arena> <angels/demons/lobby>"));
		commands.add(new JoinCMD("join", "Join an arena", "join <arena>"));
		commands.add(new LeaveCMD("leave", "Leave the arena you're in", "leave"));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 0) {
			//TODO: CONNER MAAK LEUK WELKOM DINGETJE
			return false;
		}
		for(DefaultCommand c : commands) {
			if(args[0].equalsIgnoreCase(c.getName())) {
				c.run(args, sender, this);
				return false;
			}
		} 
		if(hasPermission(sender)) {
			help(sender);
		} else {
			sender.sendMessage(Core.PREFIX + ChatColor.RED + "That was not a valid sub-command");
		}
		
		return false;
	}
	
	public boolean hasPermission(CommandSender sender) {
		return sender.hasPermission("angelsanddemons.admin");
	}
	
	public void help(CommandSender sender) {
		sender.sendMessage(ChatColor.AQUA + "               Help command               ");
		sender.sendMessage(ChatColor.AQUA + "=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=");
		for(DefaultCommand c : commands) {
			sender.sendMessage(ChatColor.WHITE + "/angelsanddemons " + c.getUsage() + ChatColor.YELLOW + " - " + ChatColor.AQUA + c.getDescription() + ".");
		}
		sender.sendMessage(ChatColor.AQUA + "=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=");
	}
	
	public Core getCore() {
		return core;
	}

}
