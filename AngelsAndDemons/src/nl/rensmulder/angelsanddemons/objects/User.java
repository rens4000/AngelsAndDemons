package nl.rensmulder.angelsanddemons.objects;

import java.util.UUID;

import nl.rensmulder.angelsanddemons.utilities.Team;

public class User {
	
	private UUID uuid;
	private Team team;
	
	public User(UUID uuid, Team team) {
		this.uuid = uuid;
		this.team = team;
	}

	public UUID getUuid() {
		return uuid;
	}

	public Team getTeam() {
		return team;
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}

}
