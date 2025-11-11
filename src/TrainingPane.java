import java.awt.List;
import java.awt.event.MouseEvent;
import java.lang.foreign.AddressLayout;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class TrainingPane extends GraphicsPane{
	public TrainingPane(MainApplication mainScreen) {
		this.mainScreen = mainScreen;
		button=TrainingButton.TRAIN;
	}
	
	private GImage leftButton;
	private GImage rightButton;
	private GImage selectButton;
	private GImage background;
	private GImage statwindow;
	private TrainingButton button;
	
	
	@Override
	public void showContent() {
		addBackground();
		addButtons();
		addStatWindow();
	}

	@Override
	public void hideContent() {
		for(GObject item : contents) {
			mainScreen.remove(item);
		}
		contents.clear();
	}
	
	private void addButtons() {
		leftButton = new GImage("tempTrainingButtons.png");
		rightButton = new GImage("tempTrainingButtons.png");
		selectButton = new GImage(button.toString());
		selectButton.setLocation(450, 400);
		leftButton.setLocation(selectButton.getX()-110, selectButton.getY());
		rightButton.setLocation(selectButton.getX()+110, selectButton.getY());
		contents.add(leftButton);
		mainScreen.add(leftButton);
		contents.add(rightButton);
		mainScreen.add(rightButton);
		contents.add(selectButton);
		mainScreen.add(selectButton);
		
	}
	private void addBackground() {
		background = new GImage("tempTrainingScreen.png");
		contents.add(background);
		mainScreen.add(background);
	}
	private void addStatWindow() {
		
	}
	private void changeButtonOptionRight() {
		switch (button){
			case STRENGTH: {
				button=TrainingButton.AGILITY;
				selectButton.setImage(button.toString());
			}
			case AGILITY:{
				button=TrainingButton.DEFENSE;
				selectButton.setImage(button.toString());
			}
			case DEFENSE:{
				button=TrainingButton.BACK;
				selectButton.setImage(button.toString());
			}
			case BACK:{
				button=TrainingButton.STRENGTH;
				selectButton.setImage(button.toString());
			}
			case TRAIN:{
				button=TrainingButton.REST;
				selectButton.setImage(button.toString());
			}
			case REST:{
				button=TrainingButton.BATTLE;
				selectButton.setImage(button.toString());
			}
			case BATTLE:{
				button=TrainingButton.TRAIN;
				selectButton.setImage(button.toString());
			}
		}
		
	}
	private void changeButtonOptionLeft() {
		switch (button){
		case STRENGTH: {
			button=TrainingButton.BACK;
			selectButton.setImage(button.toString());
		}
		case AGILITY:{
			button=TrainingButton.STRENGTH;
			selectButton.setImage(button.toString());
		}
		case DEFENSE:{
			button=TrainingButton.AGILITY;
			selectButton.setImage(button.toString());
		}
		case BACK:{
			button=TrainingButton.DEFENSE;
			selectButton.setImage(button.toString());
		}
		case TRAIN:{
			button=TrainingButton.BATTLE;
			selectButton.setImage(button.toString());
		}
		case REST:{
			button=TrainingButton.TRAIN;
			selectButton.setImage(button.toString());
		}
		case BATTLE:{
			button=TrainingButton.REST;
			selectButton.setImage(button.toString());
		}
	}
	}
	private void BattleDifficultySelect() {
		
	}
	private void RestMonster() {
		
	}
	private void trainAgility(){
		
	}
	private void trainStrength() {
		
	}
	private void trainDefense() {
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		GObject clicked = mainScreen.getElementAtLocation(e.getX(), e.getY());
		
		if (clicked == null) return;
		
		if (clicked == leftButton)
			changeButtonOptionLeft();
		if (clicked == rightButton)
			changeButtonOptionRight();
		if (clicked==selectButton)
			switch (button) {
				case BATTLE: {
				BattleDifficultySelect();
				
				}
				case REST:{
					RestMonster();
				
				}
				case TRAIN:{
					button=TrainingButton.STRENGTH;
					selectButton.setImage(button.toString());
				}
				case STRENGTH:{
					trainStrength();
				}
				case AGILITY:{
					trainAgility();
				}
				case DEFENSE:{
					trainDefense();
				}
				case BACK:{
					button=TrainingButton.TRAIN;
					selectButton.setImage(button.toString());
				}
			}
				
			
	}
	
}
	