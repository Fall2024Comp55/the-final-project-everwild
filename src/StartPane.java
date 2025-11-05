import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.*;

public class StartPane extends GraphicsPane{
	private GImage startButton;

	public StartPane(MainApplication mainApplication) {
		this.mainScreen=mainApplication;
		
	}
	
	@Override
	public void hideContent() {
		
	}
	@Override
	public void showContent() {
		addBackground();
		addStartButton();
	}
	
	private void addBackground() {
//		GImage background = new GImage("background.png",0,0);
//		assuming background is properly sized to our game window
//		
//		contents.add(background);
//		mainScreen.add(background);
	}
	
	private void addStartButton() {
		GImage startButton=new GImage("startButton.png", 440,450);
		contents.add(startButton);
		mainScreen.add(startButton);
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	    GObject clicked = mainScreen.getElementAtLocation(e.getX(), e.getY());
	    System.out.println("Clicked: " + clicked);

	    if(clicked == null) return;

	    if(clicked == contents.get(0)) {
	        System.out.println("Start button clicked!");
	       // mainScreen.switchToDifficultyScreen();
	    }
	}


}
