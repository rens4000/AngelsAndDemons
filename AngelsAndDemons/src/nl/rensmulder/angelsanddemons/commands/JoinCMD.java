package nl.rensmulder.angelsanddemons.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import nl.rensmulder.angelsanddemons.Core;
import nl.rensmulder.angelsanddemons.objects.Arena;
import nl.rensmulder.angelsanddemons.utilities.GameState;

public class JoinCMD extends DefaultCommand {

	public JoinCMD(String name, String description, String usage) {
		super(name, description, usage);
	}

	@Override
	public void run(String[] args, CommandSender sender, AngelsAndDemonsCMD cmd) {
		if(args.length != 2) {
			sender.sendMessage(Core.PREFIX + ChatColor.RED + "/angelsanddemons " + getUsage());
			return;
		}
		if(cmd.getCore().getArenaManager().getArena(args[1]) == null) {
			sender.sendMessage(Core.PREFIX + ChatColor.RED + "That arena doesn't exist.");
			return;
		}
		Arena a = cmd.getCore().getArenaManager().getArena(args[1]);
		if(!a.isEnabled()) {
			sender.sendMessage(Core.PREFIX + ChatColor.RED + "That arena has not been enabled yet.");
			return;
		}
		if(a.getState() != GameState.WAITING && a.getState() != GameState.STARTING) {
			sender.sendMessage(Core.PREFIX + ChatColor.RED + "That arena's game has already started.");
			return;
		}
		if(a.hasTooManyPlayers()) {
			sender.sendMessage(Core.PREFIX + ChatColor.RED + "That arena is too full and is not joinable.");
			return;
		}
		a.join(sender.getName());
	}

}
