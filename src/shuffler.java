import java.util.Arrays;
import java.util.Random;

/**
 * 
 * @author Rohan Krishna Ramkhumar
 * @ID rxr353
 * The shuffler class to help the user get a valid shuffled puzzle
 * Uses the fisher-yates algorithm for simplicity then checks ifSolvable before displaying
 */
public class shuffler {
	private static final int[] goal = new int[]{ 0, 1, 2, 3, 4, 5, 6, 7, 8 };

	/**
	 * Private Method isSolvable
	 * Checks if parsed puzzle is solvable
	 * ignores blank tile and takes the inversion of the rest ( by checking is any numbers are before the preceding number
	 * An Odd number of inversions means that the puzzle is unsolvable
	 * This method can technically be used with any NxN puzzle
	 * 
	 * @param 
	 * puzzle: The puzzle to be checked
	 * 
	 * @return
	 * True/False: Whether the puzzle is solvable or not
	 */
	private static boolean isSolvable(int[] puzzle) {
		int parity = 0;//the inversion number
		int gridWidth = (int) Math.sqrt(puzzle.length);//i.e. How it works for NxN it gets the N value
		int row = 0; // the current row we are on
		int blankRow = 0; // the row with the blank tile

		for (int i = 0; i < puzzle.length; i++) {
			if (i % gridWidth == 0) { // advance to next row
				row++;
			}
			if (puzzle[i] == 0) { // the blank tile
				blankRow = row; // save the row on which encountered
				continue;
			}
			for (int j = i + 1; j < puzzle.length; j++) {
				if (puzzle[i] > puzzle[j] && puzzle[j] != 0) {
					parity++;
				}
			}
		}

		/* If statements for blank tile to check whether inversions are odd or even
		 * Also checks if parity is odd or even and returns the modulo 
		 * which outputs true or false depending on whether it's of or even
		 */
		if (gridWidth % 2 == 0) { // even grid
			if (blankRow % 2 == 0) { // blank on odd row; counting from bottom
				return parity % 2 == 0;
			} else { // blank on even row; counting from bottom
				return parity % 2 != 0;
			}
		} else { // odd grid
			return parity % 2 == 0;
		}
	}
/**
 * Private Method removechar
 * Simple method to remove characters such as '[', ']', ','
 * Uses this to output a copy paste version of the puzzle
 * @param 
 * input: The input array to be removed
 * 
 * @return
 * result.toString(): the resulting copy paste version of the array
 */
	private static String removechar(int[] input) {
		String temp = Arrays.toString(input);//converts the int array to string
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < temp.length(); i++) {
			if (temp.charAt(i) != '[' && temp.charAt(i) != ']' && temp.charAt(i) != ',')
				result.append(temp.charAt(i));
		}
		return result.toString();
	}

	/**
	 * Method Shuffle
	 * Uses the Fisher-Yates shuffle instead of collections.shuffle 
	 * Easier method that creates valid puzzles more often than the shuffle package
	 * Checks if the provided array isSolvable
	 * if Not, Shuffles it the saame number of times again
	 * if Yes, prints out the before, after and copy paste version to make it easier for the user
	 * 
	 * @param 
	 * num: The number of times the puzzle is to be shuffled
	 */
	public static void shuffle(int num) {
		int[] result = new int[9];
		int[] start = new int[]{ 0, 1, 2, 3, 4, 5, 6, 7, 8 };
		Random random = new Random();

		//assigns the start state to the goal state before shuffling
		result = start;

		/*
		 * Fisher Yates shuffle
		 * The array is looped through 
		 * then a random value is picked and swapped after iteration
		 */
		for (int i = 0; i < num; i++) {
			for (int j = 8; j >= 1; j--) {
				int x = random.nextInt(j);
				int y = result[x];
				result[x] = result[j];
				result[j] = y;
			}
		}

		//If Statements to check if solvable and print out valid puzzle
		if (isSolvable(result) == false) {
			shuffle(num);
		}
		if (isSolvable(result) == true) {
			System.out.println("Before:" + Arrays.toString(goal));
			System.out.println("After" + Arrays.toString(result));
			System.out.println("Copy Paste version: " + removechar(result));
		}
	}
	public static void main(String []args){
		shuffle (4);
	}
}
