import java.awt.List;
import java.awt.event.MouseEvent;
import java.lang.foreign.AddressLayout;
import java.util.ArrayList;

import acm.graphics.GDimension;
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
	private GDimension selectButtonSize = new GDimension(120, 50); 
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
		leftButton = new GImage("leftarrow.jpeg");
		leftButton.setSize(50,50);
		rightButton = new GImage("rightarrow.jpeg");
		rightButton.setSize(50,50);
		selectButton = new GImage(button.toString());
		selectButton.setLocation(330, 370);
		selectButton.setSize(selectButtonSize);
		leftButton.setLocation(selectButton.getX()-leftButton.getWidth()-10, selectButton.getY());
		rightButton.setLocation(selectButton.getX()+selectButton.getWidth()+10, selectButton.getY());
		contents.add(leftButton);
		mainScreen.add(leftButton);
		contents.add(rightButton);
		mainScreen.add(rightButton);
		contents.add(selectButton);
		mainScreen.add(selectButton);
		
	}
	private void addBackground() {
		background = new GImage("Train.jpeg");
        background.setSize(mainScreen.getWidth()-15, mainScreen.getHeight()-30);
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

				selectButton.setSize(selectButtonSize);
				//System.out.println(button.toString());
				break;
			}
			case AGILITY:{
				button=TrainingButton.DEFENSE;
				selectButton.setImage(button.toString());

				selectButton.setSize(selectButtonSize);
				//System.out.println(button.toString());
				break;
			}
			case DEFENSE:{
				button=TrainingButton.BACK;
				selectButton.setImage(button.toString());

				selectButton.setSize(selectButtonSize);
				//System.out.println(button.toString());
				break;
			}
			case BACK:{
				button=TrainingButton.STRENGTH;
				selectButton.setImage(button.toString());

				selectButton.setSize(selectButtonSize);
				//System.out.println(button.toString());
				break;
			}
			case TRAIN:{
				button=TrainingButton.REST;
				selectButton.setImage(button.toString());

				selectButton.setSize(selectButtonSize);
				//System.out.println(button.toString());
				break;
			}
			case REST:{
				button=TrainingButton.BATTLE;
				selectButton.setImage(button.toString());

				selectButton.setSize(selectButtonSize);
				//System.out.println(button.toString());
				break;
			}
			case BATTLE:{
				button=TrainingButton.TRAIN;
				selectButton.setImage(button.toString());

				selectButton.setSize(selectButtonSize);
				//System.out.println(button.toString());
				break;
			}
		}
		
	}
	private void changeButtonOptionLeft() {
		switch (button){
		case STRENGTH: {
			button=TrainingButton.BACK;
			selectButton.setImage(button.toString());
			//System.out.println(button.toString());
			selectButton.setSize(selectButtonSize);
			break;
		}
		case AGILITY:{
			button=TrainingButton.STRENGTH;
			selectButton.setImage(button.toString());
			//System.out.println(button.toString());
			selectButton.setSize(selectButtonSize);
			break;
		}
		case DEFENSE:{
			button=TrainingButton.AGILITY;
			selectButton.setImage(button.toString());
			//System.out.println(button.toString());
			selectButton.setSize(selectButtonSize);
			break;
		}
		case BACK:{
			button=TrainingButton.DEFENSE;
			selectButton.setImage(button.toString());
			//System.out.println(button.toString());
			selectButton.setSize(selectButtonSize);
			break;
		}
		case TRAIN:{
			button=TrainingButton.BATTLE;
			selectButton.setImage(button.toString());
			//System.out.println(button.toString());
			selectButton.setSize(selectButtonSize);
			break;
		}
		case REST:{
			button=TrainingButton.TRAIN;
			selectButton.setImage(button.toString());
			//System.out.println(button.toString());
			selectButton.setSize(selectButtonSize);
			break;
		}
		case BATTLE:{
			button=TrainingButton.REST;
			selectButton.setImage(button.toString());
			//System.out.println(button.toString());
			selectButton.setSize(selectButtonSize);
			break;
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
		if (clicked==selectButton) {
			System.out.println("SELECT BUTTON CLICKED");
			switch (button) {
				case BATTLE: {
				BattleDifficultySelect();
				
				}
				case REST:{
					RestMonster();
					break;
				
				}
				case TRAIN:{
					button=TrainingButton.STRENGTH;
					selectButton.setImage(button.toString());
					selectButton.setSize(selectButtonSize);
					break;
				}
				case STRENGTH:{
					trainStrength();
					break;
				}
				case AGILITY:{
					trainAgility();
					break;
				}
				case DEFENSE:{
					trainDefense();
					break;
				}
				case BACK:{
					button=TrainingButton.TRAIN;
					selectButton.setImage(button.toString());
					selectButton.setSize(selectButtonSize);
					break;
				}
				
			}
		}
				
			
	}
	
}
	