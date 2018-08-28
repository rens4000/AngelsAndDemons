package nl.rensmulder.angelsanddemons.utilities;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import nl.rensmulder.angelsanddemons.Core;
import nl.rensmulder.angelsanddemons.objects.User;

public class ChatUtilities {
	
	public static void broadcast(List<String> players, String message) {
		for(String s : players) {
			Player p = Bukkit.getPlayer(s);
			p.sendMessage(Core.PREFIX + message);
		}
	}
	
	public static void broadcastUsers(List<User> users, String message) {
		for(User u : users) {
			Player p = Bukkit.getPlayer(u.getUuid());
			p.sendMessage(Core.PREFIX + message);
		}
	}

}
