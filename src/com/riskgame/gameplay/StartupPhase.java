package com.riskgame.gameplay;

public class StartupPhase {
	
	public void gamePlay() {
		FortificationPhase fortification = new FortificationPhase();
		fortification.startGameFortification();
	}

}
