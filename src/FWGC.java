import java.util.*;

/**
 * @Author Rohan Krishna Ramkhumar
 * @ID rxr353
 * 
 * FWGC creates the Farmer Wolf Goat cabbage problem state
 * All invalid states are stored. 
 * Any generated states are checked against invalidStates
 * Invalid states are removed from nextNode if true
 * A state is represented by one 4 bit string
 * Represents which side of the river the entity (F/W/G/C) is on
 */
public class FWGC implements State {

	/**
	 * pos[] goal: the final goal state
	 * pos[] currentState: Current 4 bit state representation
	 */
	private final FWGC.pos[] goal = new FWGC.pos[] { pos.east, pos.east, pos.east, pos.east };
	public pos[] currentState;

	/**
	 * enum pos
	 * all the members of the problem are defined to be either on the east or west side of the river
	 * Implemented in the order: Farmer, Wolf, Goat, Cabbage
	 * pos is primitive
	 */
	public enum pos {
		east, west;
	}

	/**
	 * Default standard constructor
	 */
	public FWGC() {
		currentState = new pos[] { pos.west, pos.west, pos.west, pos.west };
	}

	/**
	 * Constructor 1 (Polymorphic)
	 * 
	 * @param
	 * fpos: Farmer's Position
	 * wpos: Wolf's Position
	 * gpos: Goat's Position
	 * cpos: Cabbage's Position
	 */
	public FWGC(pos fpos, pos wpos, pos gpos, pos cpos) {
		currentState = new pos[] { fpos, wpos, gpos, cpos };
	}

	/**
	 * Constructor 2 (Polymorphic)
	 * 
	 * @param
	 * state: The pos[] array containing all the current positions of members
	 */
	public FWGC(FWGC.pos[] state) {
		currentState = new pos[] { state[0], state[1], state[2], state[3] };
	}

	/**
	 * Method findCost()
	 * @return 
	 * 1: states cost to reach the current state
	 */
	@Override
	public double findCost() {
		return 1;
	}

	/**
	 * Method nextVal
	 * Generates all possible values in the next level for the current state
	 * Will ignore any states that match the invalid states array
	 * 
	 * @return 
	 * ArrayList<State> value: One possible valid successor to the current state
	 */
	@Override
	public ArrayList<State> nextVal() {

		/*
		 * ArrayList<State> value: the successor statee
		 * pos[] temp: the current State is assigned to a temp array for if statements
		 */
		ArrayList<State> value = new ArrayList<State>();
		FWGC.pos[] temp = Arrays.copyOf(currentState, currentState.length);

		/*
		 * temp[0] is the farmer
		 * temp[1] is the wolf
		 * temp[2] is the goat
		 * temp[3] is the cabbage
		 */

		//If statement for if the farmer is on the west bank
		if (temp[0] == pos.west) {

			// Takes wolf east but only if the goat isn't alone there
			if (temp[1] == pos.west) {
				temp[0] = pos.east;
				temp[1] = pos.east;
				value.add(new FWGC(temp));
				temp = Arrays.copyOf(currentState, currentState.length);// reset
			}

			//takes the goat east
			if (temp[2] == pos.west) {
				temp[0] = pos.east;
				temp[2] = pos.east;
				value.add(new FWGC(temp));
				temp = Arrays.copyOf(currentState, currentState.length);
			}

			//takes the cabbage east, if the goat isn't alone there
			if (temp[3] == pos.west) {
				temp[0] = pos.east;
				temp[3] = pos.east;
				value.add(new FWGC(temp));
				temp = Arrays.copyOf(currentState, currentState.length);
			}

			// farmer goes alone
			temp[0] = pos.east;
			value.add(new FWGC(temp));
			temp = Arrays.copyOf(currentState, currentState.length);

		}

		//Else if the farmer is on the east bank
		else {

			// Takes wolf west
			if (temp[1] == pos.east) {
				temp[0] = pos.west;
				temp[1] = pos.west;
				value.add(new FWGC(temp));
				temp = Arrays.copyOf(currentState, currentState.length);
			}
			// Takes the goat west
			if (temp[2] == pos.east) {
				temp[0] = pos.west;
				temp[2] = pos.west;
				value.add(new FWGC(temp));
				temp = Arrays.copyOf(currentState, currentState.length);
			}
			// Takes the cabbage west
			if (temp[3] == pos.east) {
				temp[0] = pos.west;
				temp[3] = pos.west;
				value.add(new FWGC(temp));
				temp = Arrays.copyOf(currentState, currentState.length);
			}

			// Farmer goes alone
			temp[0] = pos.west;
			value.add(new FWGC(temp));
			temp = Arrays.copyOf(currentState, currentState.length);
		}

		for (int i = 0; i < value.size(); i++) {
			FWGC s = (FWGC) value.get(i);
			temp = s.currentState;

			/* check for conflicts minus the original state (makes sure it down't return to the starting state
			 * For reference:
			 * [0]: Farmer
			 * [1]: Wolf
			 * [2]: Goat
			 * [3]: Cabbage
			 */
			if (Arrays.equals(temp, new FWGC.pos[] { pos.east, pos.east, pos.west, pos.west })
					|| Arrays.equals(temp, new FWGC.pos[] { pos.east, pos.west, pos.west, pos.west })
					|| Arrays.equals(temp, new FWGC.pos[] { pos.east, pos.west, pos.west, pos.east })
					|| Arrays.equals(temp, new FWGC.pos[] { pos.west, pos.east, pos.east, pos.west })
					|| Arrays.equals(temp, new FWGC.pos[] { pos.west, pos.west, pos.east, pos.east })
					|| Arrays.equals(temp, new FWGC.pos[] { pos.west, pos.east, pos.east, pos.east })
					|| Arrays.equals(temp, new FWGC.pos[] { pos.west, pos.west, pos.west, pos.west })) {
				value.remove(i);
				i = 0; //restarts search to verify if all nodes are checked
			}
		}
		return value;
	}

	/**
	 * Method goal
	 * Checks if the current state is the goal state
	 * 
	 * @return 
	 * true or false: Checks whether the goal and current state are equal
	 */
	@Override
	public boolean goal() {
		if (Arrays.equals(currentState, goal))
			return true;
		return false;
	}

	/**
	 * Method printState
	 * prints out the current location of each member
	 */
	@Override
	public void printState() {
		System.out.println("Farmer: " + currentState[0]);
		System.out.println("Wolf: " + currentState[1]);
		System.out.println("Goat: " + currentState[2]);
		System.out.println("Cabbages: " + currentState[3]);
	}

	/**
	 * Method checkEqual
	 * checks if the current state and the input state are equal
	 * The state is type converted to FWGC to do this
	 * Eclipse generated
	 * 
	 * @param
	 * s: the state to be checked against the current State
	 * @return 
	 * true or false: whether they are equal or not
	 */
	@Override
	public boolean checkEqual(State s) {
		if (Arrays.equals(((FWGC) s).getCurState(), currentState))
			return true;
		else
			return false;
	}

	/**
	 * Method getCurState
	 * 
	 * @return 
	 * currentState: The current State
	 */
	public pos[] getCurState() {
		return currentState;
	}
}
