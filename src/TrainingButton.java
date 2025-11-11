

public enum TrainingButton {
	REST, BATTLE, TRAIN, STRENGTH, AGILITY, DEFENSE, BACK;
	
	public String toString() {
		switch (this) {
		default:
		case REST: return "Rest Button.jpeg";
		case BATTLE: return "Battle Button.jpeg";
		case TRAIN: return "Train Button.jpeg";
		case BACK: return "Back Button.jpeg";
		case STRENGTH: return "Strength Button.jpeg";
		case AGILITY: return "Agility Button.jpeg";
		case DEFENSE: return "Defense Button.jpeg";
		}
		
	}
}

