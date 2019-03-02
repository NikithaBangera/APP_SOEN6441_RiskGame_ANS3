package com.riskgame.gameplay.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.riskgame.common.RiskPlayer;
import com.riskgame.gameplay.StartupPhase;

public class StartupPhaseTest {
    RiskPlayer player1, player2, player3;
    StartupPhase startUpPhase;
    
    public StartupPhaseTest() {
        startUpPhase = new StartupPhase();
        startUpPhase.setCountOfthePlayers(2);
        
        player1 = new RiskPlayer();
        startUpPhase.getPlayersList().add(player1);
        
    }
    
    @Test
    public void testAllocationArmy() {
        startUpPhase.allocationOfArmyToPlayers();
        assertEquals(40, player1.getArmyCount());
    }
}