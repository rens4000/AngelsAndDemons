package nl.rensmulder.angelsanddemons.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import nl.rensmulder.angelsanddemons.Core;

public class CreateCMD extends DefaultCommand {

	public CreateCMD(String name, String description, String usage) {
		super(name, description, usage);
	}

	@Override
	public void run(String[] args, CommandSender sender, AngelsAndDemonsCMD cmd) {
		if(!cmd.hasPermission(sender)) {
			sender.sendMessage(Core.PREFIX + ChatColor.RED + "You don't have permission to execute that command.");
			return;
		}
		if(args.length != 2) {
			sender.sendMessage(Core.PREFIX + ChatColor.RED + "/angelsanddemons " + getUsage());
			return;
		}
		if(cmd.getCore().getArenaManager().getArena(args[1]) != null) {
			sender.sendMessage(Core.PREFIX + ChatColor.RED + "That arena already exists. Try to make an arena with another name.");
			return;
		}
		cmd.getCore().getArenaManager().createNewArena(args[1]);
		sender.sendMessage(Core.PREFIX + ChatColor.GREEN + args[1] + " has successfully been created!");
	}

}
