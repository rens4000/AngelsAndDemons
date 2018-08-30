package nl.rensmulder.angelsanddemons.commands;

import org.bukkit.command.CommandSender;

public abstract class DefaultCommand {
	
	private String name, description;
	
	public DefaultCommand(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public abstract void run(String[] args, CommandSender sender);

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
}
