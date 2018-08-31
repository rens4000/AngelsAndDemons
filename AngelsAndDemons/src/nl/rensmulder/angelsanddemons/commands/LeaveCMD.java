package nl.rensmulder.angelsanddemons.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import nl.rensmulder.angelsanddemons.Core;
import nl.rensmulder.angelsanddemons.objects.Arena;


public class LeaveCMD extends DefaultCommand{

	public LeaveCMD(String name, String description, String usage) {
		super(name, description, usage);
	}

	@Override
	public void run(String[] args, CommandSender sender, AngelsAndDemonsCMD cmd) {
		if(args.length != 1) {
			sender.sendMessage(Core.PREFIX + ChatColor.RED + "/angelsanddemons " + getUsage());
			return;
		}
		if(cmd.getCore().getArenaManager().getArenaByPlayer(sender.getName()) == null) {
			sender.sendMessage(Core.PREFIX + ChatColor.RED + "You are not in an arena!");
			return;
		}
		Arena a = cmd.getCore().getArenaManager().getArenaByPlayer(sender.getName());
		a.leave(sender.getName());	
	}

}
