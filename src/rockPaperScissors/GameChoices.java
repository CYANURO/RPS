/**
 * 
 */
package rockPaperScissors;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.ImageIcon;

/**
 * @author Aldo R Valdes
 *
 */


public enum GameChoices {
	
	ROCK(new ImageIcon("resources/rock.jpg")),
	PAPER(new ImageIcon("resources/paper.jpg")),
	SCISSORS(new ImageIcon("resources/scissors.jpg"));
	
	public ImageIcon icon;
	public static int userScore;
	public static int computerScore;
	
	
	private GameChoices(ImageIcon imageIcon) {
		
		icon = imageIcon;
	}
	
	public static GameChoices randomChoiceGenerator() {
		
		Random randomChoice = new Random();
		List<GameChoices> gameChoicesList = new ArrayList<>(Arrays.asList(GameChoices.values()));
		int randomNumberPick = randomChoice.nextInt(gameChoicesList.size());
		GameChoices randomGameChoice = gameChoicesList.get(randomNumberPick);
		
		return randomGameChoice;
		
	}
	
	public String gameChoicesEvaluation(GameChoices userPick, GameChoices computerPick) {
		
			if(userPick == ROCK && computerPick == PAPER || 
					userPick == SCISSORS && computerPick == ROCK ||
					userPick == PAPER && computerPick == SCISSORS) {
				
				computerScore++;
				return("You Lost!");
				
			}
			
			else if(userPick == ROCK && computerPick == SCISSORS ||
					userPick == SCISSORS && computerPick == PAPER ||
					userPick == PAPER && computerPick == ROCK) {
				
				userScore++;
				return("You Won!");
				
			}
			
			else {
				
				return("It's a Tie");
				
			}
	}
	
	
	@Override
	public String toString() {
		
		return(this.name() + " VS ");
		
	}
}

