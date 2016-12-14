import java.util.*;

/**
 * 
 * @author Rohan Krishna Ramkhumar
 * @id rxr353
 * eightPuzzle defines the puzzle state for the 8 puzzle problem.
 * The board will always be represented by a single dimensional array
 * But for display purposes the matrix style makes it seem 2 dimensional
 * 0 represents the blank tile  
 * if the tile is blank, special cases are used when generating values
 * It (0) is not treated as a tile its thought about as a hole
 */
public class eightPuzzle implements State {

	private final int size = 9;
	private int[] currentBoard;
	private final int[] goal = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };

	/**
	 * Standard Constructor for the eight Puzzle
	 * 
	 * @param 
	 * board: assigns the current board state to the variable currentBoard
	 */
	public eightPuzzle(int[] board) {
		currentBoard = board;
	}

	/**
	 * Method findCost()
	 * 
	 * @return 
	 * 1: states cost to reach the current state
	 */
	@Override
	public double findCost() {
		return 1;
	}

	/**
	 * Private Method getZero
	 * goes through all the values in the array to find the 0 ('blank tile')
	 * 
	 * @return
	 * index: the location of the blank tile
	 */
	private int getZero() {
		int index = -1;

		for (int i = 0; i < size; i++) {
			if (currentBoard[i] == 0)
				index = i;
		}
		return index;
	}

	/**
	 * Private Method Copy
	 * Copes the array parsed to it
	 * For some reason does not work with directly equating the arrays
	 * 
	 * @param 
	 * state: The current state/ the array to be copied
	 * @return
	 * result: the copied array
	 */
	private int[] copy(int[] state) {
		int[] result = new int[size];

		for (int i = 0; i < size; i++) {
			result[i] = state[i];
		}
		return result;
	}

	/**
	 * Method nextVal
	 * Can essentially only do 4 operations at the most (up down left right)
	 * If a corner is met, that direction is invalid
	 * 
	 * @return
	 * Value: the Arraylist of all possible successor values for that state
	 */
	@Override
	public ArrayList<State> nextVal() {
		ArrayList<State> value = new ArrayList<State>();
		int hole = getZero();

		/*
		 * Each of the hole if statements represent the edges of the tiles
		 *where the moves will be invalid
		 */
		// Generate a new state by sliding the blank tile left (If constraints are met)
		if (hole != 0 && hole != 3 && hole != 6) {
			swap(hole - 1, hole, value);
		}

		// Generate a new state by sliding the blank tile right (If constraints are met)
		if (hole != 2 && hole != 5 && hole != 8) {
			swap(hole + 1, hole, value);
		}
		// Generate a new state by sliding the blank tile downward (If constraints are met)
		if (hole < 6) {
			swap(hole + 3, hole, value);
		}

		// Generate a new state by sliding the blank tile upward (If constraints are met)
		if (hole > 2) {
			swap(hole - 3, hole, value);
		}

		return value;
	}

	/**
	 * Private Method Swap
	 * switches the data at indexes index1 and index2 
	 * pushes the new board to state s
	 * 
	 * @param 
	 * index1: the first index to be swapped
	 * index2: the second index to be swapped
	 * s: The location where the new state (board) needs to be pushed
	 */
	private void swap(int index1, int index2, ArrayList<State> s) {
		int[] copy = copy(currentBoard);
		int temp = copy[index1];
		copy[index1] = currentBoard[index2];
		copy[index2] = temp;
		s.add((new eightPuzzle(copy)));
	}

	/**
	 * Method goal
	 * Checks if current state is the goal state
	 * 
	 * @return
	 * True/False: Returns the boolean value 
	 */
	@Override
	public boolean goal() {
		if (Arrays.equals(currentBoard, goal))
			return true;
		return false;
	}

	/**
	 * Method printState
	 * prints out the current board state in a 3x3 grid form
	 */
	@Override
	public void printState() {
		System.out.println(currentBoard[0] + " | " + currentBoard[1] + " | " + currentBoard[2]);
		System.out.println("---------");
		System.out.println(currentBoard[3] + " | " + currentBoard[4] + " | " + currentBoard[5]);
		System.out.println("---------");
		System.out.println(currentBoard[6] + " | " + currentBoard[7] + " | " + currentBoard[8]);
	}

	/**
	 * method checkEqual
	 * Compares two states to see if they are equal/ 
	 * S is converted to eightPuzzle to get the board version
	 * eclipse generated
	 * 
	 * @param
	 * s: the state to be checked against the current Board
	 * @return
	 * True/False: Returns the answer depending on equality
	 */
	@Override
	public boolean checkEqual(State check) {
		if (Arrays.equals(((eightPuzzle) check).getCurBoard(), getCurBoard()))
			return true;
		return false;
	}

	/**
	 * Method getCurBoard
	 * 
	 * @return 
	 * currentState: The current Board
	 */
	public int[] getCurBoard() {
		return currentBoard;
	}
}
