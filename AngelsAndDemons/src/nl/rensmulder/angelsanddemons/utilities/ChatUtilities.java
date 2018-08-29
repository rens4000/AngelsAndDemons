package nl.rensmulder.angelsanddemons.utilities;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import nl.rensmulder.angelsanddemons.Core;
import nl.rensmulder.angelsanddemons.objects.User;

public class ChatUtilities {
	
	public static void broadcast(List<String> players, String message) {
		for(String s : players) {
			Player p = Bukkit.getPlayer(s);
			p.sendMessage(Core.PREFIX + message);
			p.playSound(p.getLocation(), Sound.BLOCK_NOTE_CHIME, 1,1);
		}
	}
	
	public static void broadcastUsers(List<User> users, String message) {
		for(User u : users) {
			Player p = Bukkit.getPlayer(u.getUuid());
			p.sendMessage(Core.PREFIX + message);
		}
	}
	
	public static void broadcastTitleUsers(List<User> users, String message1, String message2) {
		for(User u : users) {
			Player p = Bukkit.getPlayer(u.getUuid());
			p.sendTitle(message1, message2);
		}
	}

}
