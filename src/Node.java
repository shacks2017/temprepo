/**
 * @author Rohan Krishna Ramkhumar
 * @ID rxr353
 * 
 * Node class implements each individual node in the problem state
 * Implements methods from state
 * Tracks the cost to get to the current state and it's parent state
 */
public class Node {

	/**
	 * State currentState: the current state of the puzzle 
	 * Node parent: the parent Node (has the parent state) 
	 * double cost: The cost (number of moves) to get to current state 
	 * double hCost: heuristic cost 
	 */
	private State currentState;
	private Node parent;
	private double cost;

	/**
	 * Constructor and initializer for the root node 
	 * 
	 * @param
	 * State s: The state that is passed in 
	 */
	public Node(State s) {
		currentState = s;
		parent = null;
		cost = 0;
	}

	/**
	 * Constructor/Initializer for all other Nodes 
	 * 
	 * @param 
	 * Node prev: The parent node 
	 * State s: The state passed in to create the node 
	 * double c: the g(n) cost of the node 
	 * double h: the h(n) cost of thenode
	 */
	public Node(Node prev, State s, double c, double h) {
		parent = prev;
		currentState = s;
		cost = c;
	}

	/**
	 * Method curState 
	 * 
	 * @return 
	 * currentState: the current State
	 */
	public State getCurState() {
		return currentState;
	}

	/**
	 * Method getParent() 
	 * 
	 * @return 
	 * parent: The parent node
	 */
	public Node getParent() {
		return parent;
	}

	/**
	 * Method getCost 
	 * 
	 * @return 
	 * cost: the cost to get to the root node
	 */
	public double getCost() {
		return cost;
	}

}
