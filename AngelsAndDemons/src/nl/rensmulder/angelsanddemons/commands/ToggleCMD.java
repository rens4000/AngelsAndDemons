package nl.rensmulder.angelsanddemons.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import nl.rensmulder.angelsanddemons.Core;
import nl.rensmulder.angelsanddemons.objects.Arena;

public class ToggleCMD extends DefaultCommand {

	public ToggleCMD(String name, String description, String usage) {
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
		if(cmd.getCore().getArenaManager().getArena(args[1]) == null) {
			sender.sendMessage(Core.PREFIX + ChatColor.RED + "That arena doesn't exist.");
			return;
		}
		Arena a = cmd.getCore().getArenaManager().getArena(args[1]);
		if(!a.isEnabled()) {
			if(!a.canBeEnabled()) {
				sender.sendMessage(Core.PREFIX + ChatColor.RED + "Not all spawnlocations were set or the lobby location wasn't set.");
				return;
			}
			a.setEnabled(true);
			sender.sendMessage(Core.PREFIX + ChatColor.GREEN + "You enabled the arena!");
		} else {
			a.close();
			a.setEnabled(false);
			sender.sendMessage(Core.PREFIX + ChatColor.GREEN + "You disabled the arena!");
		}
	}
	
}
