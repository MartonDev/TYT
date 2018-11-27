package me.marton.troll;

public class Main {
	
	public static void main(String args[]) {
		
		System.out.println("Starting troll by Marton...");

		ActionManager actionManager = new ActionManager();
		Utils utils = new Utils();
		
		System.out.println("Loaded classes...");
		System.out.println("Starting checks for new actions...");
		
		utils.disableMouseTroll();
		actionManager.checkForActions();
				
	}

}
