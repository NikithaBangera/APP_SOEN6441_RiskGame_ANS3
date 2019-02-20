package com.riskgame.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FunctionServiceImpl {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void userMapCreation() throws IOException {
		System.out.println("Welcome to Risk Game! \n");
		System.out.println("Enter the following information \n");
		System.out.println("Enter the basic layout \n");
		
		String inputUser = br.readLine();
	}
}
