import java.awt.event.MouseEvent;
import java.lang.foreign.AddressLayout;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class TurnsPane extends GraphicsPane{
	public TurnsPane(MainApplication mainScreen) {
		this.mainScreen = mainScreen;
	}
	private GImage background;
	private GLabel turnCount;
	
	@Override
	public void showContent() {
		addBackground();
		addTurnCount();
	}

	@Override
	public void hideContent() {
		for(GObject item : contents) {
			mainScreen.remove(item);
		}
		contents.clear();
	}
	
	private void addBackground() {
		background=new GImage("Child.jpeg");
		mainScreen.add(background);
	}
	
	private void addTurnCount() {
		turnCount = new GLabel(""+mainScreen.getTurnsRemaining());
		turnCount.setFont("papyrus");
		mainScreen.add(turnCount);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		mainScreen.switchToTrainingScreen();
	}
	
}