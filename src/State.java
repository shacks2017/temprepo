import java.util.*;

/**
 * @Author Rohan Krishna Ramkhumar
 * @ID rxr353
 * 
 * The problem states (FWGC and 8 puzzle) inherit from this interface. 
 * Implements the following methods in each class and helps to get to endgoal 
 */
public interface State {

	/**
	 * boolean goal(): checks if the state right now is the goal state
	 * ArrayList<State> nextVal(): Generates the children (successors) for
	 * current state double findCost(): Finds the cost from initial to current
	 * state void printState(): Prints the current state boolea equals(State s):
	 * boolea equals(): Compares 2 states(current and previous)
	 */
	boolean goal();

	ArrayList<State> nextVal();

	double findCost();

	public void printState();

	public boolean checkEqual(State s);
	
}
