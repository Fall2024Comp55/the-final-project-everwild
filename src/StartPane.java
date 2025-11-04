import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.*;

public class StartPane extends GraphicsPane{
	
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
		GImage startButton=new GImage("startButton.png", 300,300);
		contents.add(startButton);
		mainScreen.add(startButton);
		
	}
}
