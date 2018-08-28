package nl.rensmulder.angelsanddemons.managers;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class UserManager {
	
	private Map<String, ItemStack[]> inventories = new HashMap<>();
	private Map<String, ItemStack[]> armor = new HashMap<>();
	private Map<String, Location> locations = new HashMap<>();
	
	public Map<String, ItemStack[]> getInventories() {
		return inventories;
	}
	
	public Map<String, ItemStack[]> getArmor() {
		return armor;
	}
	
	public Map<String, Location> getLocations() {
		return locations;
	}

}
