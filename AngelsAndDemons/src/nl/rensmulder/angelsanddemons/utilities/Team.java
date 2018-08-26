package nl.rensmulder.angelsanddemons.utilities;

public enum Team {
	ANGELS("Angels"), DEMONS("Demons");
	
	private String teamname;
	
	Team(String teamname) {
		teamname = this.teamname;
	}
	
	public String getTeamName() {
		return teamname;
	}
}
