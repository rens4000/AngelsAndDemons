package nl.rensmulder.angelsanddemons.commands;

import org.bukkit.command.CommandSender;

public abstract class DefaultCommand {
	
	private String name, description;
	
	public DefaultCommand(String name, String description, String usage) {
		this.name = name;
		this.description = description;
	}
	
	public abstract void run(String[] args, CommandSender sender, AngelsAndDemonsCMD cmd);

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getUsage() {
		return usage;
	}
}
