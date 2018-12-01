package me.marton.troll;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Utils {
	
	private boolean mouseTroll;
	
	public void openChrome(String url) {
		
		try {
			
			Runtime.getRuntime().exec(new String[]{"cmd", "/c","start chrome " + url});
			
		} catch (IOException e) {

			e.printStackTrace();
			
		}
		
	}
	
	public void mouseClick() {
		
		try {
			
			Robot robot = new Robot();
			
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			
		} catch (AWTException e) {

			e.printStackTrace();
			
		}
			
	}
	
	public void mouseTroll(Long delay) {
		
		mouseTroll = true;
		
		TimerTask repeatedTask = new TimerTask() {
			
			public void run() {
				
				if(mouseTroll) {
					
					Random random = new Random();
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					
					try {
									
						Robot robot = new Robot();
						
						robot.mouseMove(random.nextInt(screenSize.width), random.nextInt(screenSize.height));
						
					} catch (AWTException e) {
						
						e.printStackTrace();
						
					}
					
				}
				
			}
			
		};
		
		Timer timer2 = new Timer("Timer2");
	    timer2.scheduleAtFixedRate(repeatedTask, delay, delay);
		
	}
	
	public void disableMouseTroll() {
		
		mouseTroll = false;
		
	}
	
	public static String get(String url) throws IOException {

        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
        	
            response.append(inputLine);
            
        }

        in.close();

        return response.toString();
        
    }

}
