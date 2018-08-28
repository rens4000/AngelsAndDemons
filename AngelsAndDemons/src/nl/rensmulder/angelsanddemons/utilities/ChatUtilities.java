package nl.rensmulder.angelsanddemons.utilities;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import nl.rensmulder.angelsanddemons.Core;

public class ChatUtilities {
	
	public static void broadcast(List<String> players, String message) {
		for(String s : players) {
			Player p = Bukkit.getPlayer(s);
			p.sendMessage(Core.PREFIX + message);
		}
	}

}
