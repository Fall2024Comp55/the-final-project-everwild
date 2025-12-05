import java.awt.Color;
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
	private Monster monster; 

	
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
	    String imgPath = mainScreen.getDifficulty().getBackgroundImage();
	    background = new GImage(imgPath);

	    double screenW = mainScreen.getWidth();
	    double screenH = mainScreen.getHeight();

	    double imgW = background.getWidth();
	    double imgH = background.getHeight();

	    double scale = Math.max(screenW / imgW, screenH / imgH);

	    double newW = imgW * scale;
	    double newH = imgH * scale;

	    background.setSize(newW, newH);

	    double x = (screenW - newW) / 2.0;
	    double y = (screenH - newH) / 2.0;
	    background.setLocation(x, y);

	    mainScreen.add(background);
	    contents.add(background);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(mainScreen.getTurnsRemaining()>0)
			mainScreen.switchToTrainingScreen(mainScreen.getMonster());
		//else
			//mainScreen.switchToFinalBattleScreen;
	}
	public void setMonster(Monster monster) {
	    this.monster = monster;
	}

	public Monster getMonster() {
	    return this.monster;
	}
	
}