package com.riskgame.common;

import java.util.*;
import java.util.Scanner;

import com.risk.model.Player;

import java.util.HashMap;
import java.util.Map;

//private Map map;

public class StartUp {
 private int NoOfPlayers = 0;
 private HashMap < String, Integer > PlayersList = new HashMap < > ();
 int j;

 public StartUp(Map map) {
  //this.map = map;
  @SuppressWarnings("resource")
  Scanner scan = new Scanner(System.in);
  try {
   do {
    System.out.println("Enter number of players:");
    this.NoOfPlayers = Integer.parseInt(scan.nextLine());
    if (this.NoOfPlayers < 2) {
     System.out.println("Number of players should be more than 2");
    }
    if (this.NoOfPlayers > 6) {
     System.out.println("Number of players should be less than 6");
    }
   }
   while (this.NoOfPlayers < 2 || this.NoOfPlayers > 6);
  } catch (NumberFormatException ex) {
   System.out.println("Please enter valid number format");
  }
  System.out.println("Names of the Players must be entered below");
  for (j = 0; j < this.NoOfPlayers; ++j) {
   Player player = new Player(); //player object will come from diff class
   String playername = null;
   if ((playername = scan.nextLine()) != null) {
    player.setName(playername);
   }
   this.PlayersList.put("players", j);
  }
  
  public void countryAllocation() {
	  
  }
  
  public void AllocateArmies() { 
			for(int i=0; i<this.NoOfPlayers ; i++)
			{
				while (this.NoOfPlayers == 2) {
					player.setNoOfArmy(40); // setNoOfArmy is in player class
				} while(this.NoOfPlayers == 3) {
					player.setNoOfArmy(35);
				} while (this.NoOfPlayers == 4) {
					player.setNoOfArmy(30);
				} while(this.NoOfPlayers == 5) {
					player.setNoOfArmy(25);
				} while (this.NoOfPlayers == 6) {
					player.setNoOfArmy(20);
				}
			
		}
			}
  

 }
}