import java.util.ArrayList;

/**
 * This is a class that plays the GUI version of the Elevens game.
 * See accompanying documents for a description of how Elevens is played.
 */
public class ThirteensGUIRunner {
	/**
	 * Plays the GUI version of Elevens.
	 * @param args is not used.
	 */
	public static void main(String[] args) {
		Board board = new ThirteensBoard();
		CardGameGUI gui = new CardGameGUI(board);
		gui.displayGame();
		System.out.println(board.anotherPlayIsPossible());
		while(board.anotherPlayIsPossible())
		{
			ArrayList<Integer> values = board.boardCardValues();
			board.printPairs(values, 13);
			System.out.println("User: " + board.getUserScore());
			System.out.println("CPU: " + board.getCPUScore());
		}
	}
}
