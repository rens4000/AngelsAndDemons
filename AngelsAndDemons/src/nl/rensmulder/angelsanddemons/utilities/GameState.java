package nl.rensmulder.angelsanddemons.utilities;

public enum GameState {
	WAITING("waiting"), STARTING("starting"), STARTED("started"), RESETTING("resetting");
	
	private String state;
	
	GameState(String state) {
		state = this.state;
	}
	
	public String getStateName() {
		return state;
	}
}
