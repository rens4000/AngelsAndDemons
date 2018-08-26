package nl.rensmulder.angelsanddemons.managers;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import nl.rensmulder.angelsanddemons.Core;

public class ConfigManager {
	
	private File configFile, dataFile, langFile;
	private FileConfiguration config, data, lang;
	

	public ConfigManager(Core core) {
		configFile = new File(core.getDataFolder(), "config.yml");
		dataFile = new File(core.getDataFolder(), "data.yml");
		langFile = new File(core.getDataFolder(), "language.yml");
		config = YamlConfiguration.loadConfiguration(configFile);
		data = YamlConfiguration.loadConfiguration(dataFile);
		lang = YamlConfiguration.loadConfiguration(langFile);
	}
	
	public void saveConfig() {
		try {
			config.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveData() {
		try {
			data.save(dataFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveLang() {
		try {
			lang.save(langFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadDefaultLang() {
		lang.addDefault("Prefix", "&7[&b&lAngels&f&lAnd&4&lDemons&7]");
		saveLang();
	}

	public FileConfiguration getConfig() {
		return config;
	}

	public FileConfiguration getData() {
		return data;
	}

	public FileConfiguration getLang() {
		return lang;
	}
	

}
