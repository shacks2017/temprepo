import java.util.*;

/**
 * @Author Rohan Krishna Ramkhumar
 * @ID rxr353
 * Defines and executes the Depth First Search Algorithm 
 * Currently can  be called with 2 overloaded methods 
 * FWGC (no parameters) and 8puzzle (the problem state)
 */
public class DFSearch {

	/**
	 * Search function for FWGC
	 * Calls  doSearch on the FWGC stack
	 */
	public static void search() {
		Node root = new Node(new FWGC());
		Stack<Node> stack = new Stack<Node>();

		stack.add(root);
		doSearch(stack);
	}

	/**
	 * Search function for the 8Puzzle
	 * Array Size 9 represents the 3x3 grid in linear form	
	 * 
	 * @param
	 * board: The starting problem state. 
	 */
	public static void search(int[] board) {
		Node root = new Node(new eightPuzzle(board));
		Stack<Node> stack = new Stack<Node>();
		stack.add(root);
		doSearch(stack);
	}

	/**
	 * Private Method c
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

		/* 
		 * While n's parent isn't null(a.k.a the root node)
		 * check to see if it's equal to the node
		 * we're parsing (i.e. checks if there are repeats
		 */
		while (n.getParent() != null) {
			if (n.getParent().getCurState().checkEqual(check.getCurState()))
				result = true;
			n = n.getParent();
		}
		return result;
	}

	/**
	 * Method doSearch
	 * Performs Depth First search on the puzzle parsed
	 * Uses the parsed stack as the search space
	 * 
	 * @param 
	 * s: The puzzle state to be searched
	 */
	public static void doSearch(Stack<Node> s) {
		int count = 1;//Counter to keep track of number of nodes
		while (!s.isEmpty()) {//While the stack/queue is not empty

			Node temp = (Node) s.pop();

			/*
			 * DF search if statement
			 * Checks first if the current state of node temp is the goal state
			 * Then checks if the Cost(number of moves) is less than 31. 
			 * 31 is the max number of moves for solvable puzzles
			 * if this limit is reached it's not an efficient tree/an infinite tree
			 * Moves on to the next branch to check
			 * This loop keeps repeating (for loop) 
			 * until the goal has been found
			 * The checked nodes are added to the solution stack
			 * If they are found to not be repeated
			 */
			if (!temp.getCurState().goal()) {
				if (temp.getCost() < 31) {
					//the successors for the temp node
					ArrayList<State> nextVal = temp.getCurState().nextVal();

					/*
					 * Checks all successors and wraps in a node
					 * If the value hasn't been repeated adds to the stack
					 */
					for (int i = 0; i < nextVal.size(); i++) {

						/*
						 * the third value(temp.getcost()) 
						 * adds the cost to the current Cost of node
						 */
						Node newVal = new Node(temp, nextVal.get(i), temp.getCost() + nextVal.get(i).findCost(), 0);
						if (!checker(newVal)){
							s.add(newVal);
							count++;
						}
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

				//The size of the stack before emptying through the loop
				int loop = path.size();

				//Pops, prints the solution then prints out spaces
				for (int i = 0; i < loop; i++) {
					temp = path.pop();
					temp.getCurState().printState();
					System.out.println();
					System.out.println();
				}

				System.out.println("Number of Moves made: " + temp.getCost());
				System.out.println("Number of Nodes Examined:" + count);
				System.exit(0);
			}

		}
		/*
		 * Backup message
		 * Should not print with a valid problem
		 * All valid solutions can be found within move no. 31
		 * This only applies to valid puzzles
		 */
		System.out.println("Error! I went over move 31! (max number of moves for a solvable puzzle) ");
		System.out.println("Possible Reasons:");
		System.out.println("1) The puzzle might be invalid");
		System.out.println("2) I went down an infinite tree");
		
	}
	public static void main(String args[]) {
		int[] y = new int[] {6, 7, 5, 4, 8, 2, 3, 1, 0};
		search(y);
	}
}
