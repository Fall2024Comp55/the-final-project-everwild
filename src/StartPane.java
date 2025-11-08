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
        for(GObject item : contents) {
            mainScreen.remove(item);
        }
        contents.clear();
	}
	
	@Override
	public void showContent() {
		addBackground();
		addStartButton();
	}
	
	private void addBackground() {
		GImage background = new GImage("BackgroundwithoutStartButton.png",0,0);
//		assuming background is properly sized to our game window
		background.scale(0.5);
		mainScreen.add(background);
	}
	
	private void addStartButton() {
		startButton=new GImage("Start1Copy.png", 475,340);
		startButton.scale(0.5);
		mainScreen.add(startButton);
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
	    GObject clicked = mainScreen.getElementAtLocation(e.getX(), e.getY());
	    //System.out.println("Clicked: " + clicked);

	    if(clicked == null) return;

	    if(clicked == startButton) {
	        System.out.println("Start button clicked!");
	        mainScreen.switchToDifficultyScreen();
	    }
	}


}
