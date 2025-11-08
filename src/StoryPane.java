import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class StoryPane extends GraphicsPane{
	public StoryPane(MainApplication mainScreen) {
		this.mainScreen = mainScreen;
	}
	
	@Override
	public void showContent() {
		addBackground();
	}

	@Override
	public void hideContent() {
		for(GObject item : contents) {
			mainScreen.remove(item);
		}
		contents.clear();
	}

	private void addBackground() {
	    GImage background = new GImage("tempstorybackground.png", 0, 0);
	    contents.add(background);    
	    mainScreen.add(background); 
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		mainScreen.switchToMonsterSelectScreen();
	}
}
	