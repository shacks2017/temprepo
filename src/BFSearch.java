import java.util.*;

/**
 * @Author Rohan Krishna Ramkhumar
 * @ID rxr353
 * Defines and executes the Breadth First Search Algorithm 
 * Currently can  be called with 2 overloaded methods 
 * FWGC (no parameters) and 8puzzle (the problem state)
 */
public class BFSearch {

	/**
	 * Search function for 8Puzzle
	 * Array Size 9 represents the 3x3 grid in linear form
	 * 
	 * @param
	 * board: The starting problem state. 
	 */
	public static void search(int[] board) {
		Node root = new Node(new eightPuzzle(board));
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);

		doSearch(queue);
	}

	/**
	 * Search function for FWGC
	 * Calls  doSearch on the FWGC stack
	 */
	public static void search() {
		Node root = new Node(new FWGC());
		Queue<Node> queue = new LinkedList<Node>();

		queue.add(root);
		doSearch(queue);
	}

	/**
	 * Private Method checkRepeats
	 * Checks if the node has ever been checked to check if that move has already been made
	 * 
	 * @param 
	 * n: the node to be checked whether repeated
	 * 
	 * @return
	 * True/False: returns result depending on the node parsed
	 */
	private static boolean checker(Node n) {
		boolean result = false;
		Node check = n;

		/* While n's parent isn't null(root node)
		 * check to see if it's equal to the node
		 * we're parsing (i.e. checks if there are repeats
		 */

		while (n.getParent() != null && !result) {
			if (n.getParent().getCurState().checkEqual(check.getCurState())) {
				result = true;
			}
			n = n.getParent();
		}
		return result;
	}

	/**
	 * Private Method doSearch
	 * Performs Breadth First search on the puzzle parsed
	 * Uses the parsed stack as the search space
	 * 
	 * @param 
	 * s: The puzzle state to be searched
	 */
	private static void doSearch(Queue<Node> q) {
		int count = 1; //Counter to keep track of number of nodes
		while (!q.isEmpty()) //While the stack/queue is not empty
		{

			Node temp = (Node) q.remove();

			/*
			* BF search if statement
			* Checks first if the current state of node temp is the goal state
			* Moves on to the next branch to check
			* This loop keeps repeating (for loop) 
			* until the goal has been found
			* The checked nodes are added to the solution stack
			* If they are found to not be repeated
			*/
			if (!temp.getCurState().goal()) {
				//the successors for the temp node
				ArrayList<State> value = temp.getCurState().nextVal();
				int length = value.size();

				/*
				 * Checks all successors and wraps in a node
				 * If the value hasn't been repeated adds to the stack
				 */
				for (int i = 0; i <length; i++) {

					/*
					 * the third value(temp.getcost()) 
					 * adds the cost to the current Cost of node
					 */
					Node newNode = new Node(temp, value.get(i), temp.getCost() + value.get(i).findCost(), 0);

					if (!checker(newNode)) {
						q.add(newNode);
						count++;
					}
				}
			}

			/*
			 * Else statement for when the goal state has been found
			 * Prints the path by using a stack from starting to end states
			 */
			else {
				Stack<Node> path = new Stack<Node>();
				path.push(temp);
				temp = temp.getParent();

				//Only the root node has a null parent (i.e. the start point)
				while (temp.getParent() != null) {
					path.push(temp);
					temp = temp.getParent();
				}
				path.push(temp);

				// The size of the stack before looping through and emptying it.
				int loopSize = path.size();

				//Pops, prints the solution then prints out spaces
				for (int i = 0; i < loopSize; i++) {
					temp = path.pop();
					temp.getCurState().printState();
					System.out.println();
					System.out.println();
				}
				System.out.println("Number of Moves made: " + temp.getCost());
				System.out.println("Number of nodes examined: " + count);
				System.exit(0);
			}
		}

		/*
		 * Backup message
		 * Should not print with a valid problem
		 * All valid solutions can be found within move no. 31 (i.e. level 31)
		 * Invalid puzzles will cause an error or the program to continue to loop through itself
		 * After testing, I have noticed that puzzles that need over 26 moved tend to take up a lot of memory and time. Sorry!
		 */
		System.out.println("System Error");
	}

	public static void main(String args[]) {
		int[] y = new int[] { 4, 7, 3, 5, 2, 8, 0, 6, 1 };
		search(y);
	}
}
