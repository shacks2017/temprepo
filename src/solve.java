/**
 * @Author Rohan Krishna Ramkhumar 
 * @ID rxr353
 * 
 * The main solver program that calls the relevant class
 * BFS or DFS is called to solve either the FWGC or 8 puzzle problem
 * Uses the main method and args in order to be excecutable
 * from command line
 */	
public class solve {
	
	/**
	 * Private method eightPuzzleStart
	 * Takes in each character separated by space and converts into string
	 * Before parsing in to an array
	 * 
	 * @param 
	 * String[] input: Takes in args
	 * @return
	 * initial: The start state of the board
	 */
	private static int[] eightPuzzleStart(String [] input){
		int [] initial = new int[9];
		
		for (int i = 2; i<input.length;i++)
			initial[i-2] = Integer.parseInt(input[i]);//i - 2 accounts for the 2 string values before the array (the puzzle type and search algorithm)
		
		return initial;
	}
	
	
	public static void main(String[] args) {
		
		/**
		 * String Puzzle: The puzzle type: 8 puzzle or FWGC
		 * String searchType: Depth First Search (dfs) or Breadth First Search(bfs)
		 */						
		String problem = args[0].toLowerCase();
		String searchType = args[1].toLowerCase();
		
		//shuffler program to print out a solvable 8puzzle
		if (problem.equals("shuffle")){
			int num = Integer.parseInt(searchType);
			shuffler.shuffle( num);
		}
		
		//8 puzzle if statements to either parse Depth First Search or Breadth First Search algorithms
		if (problem.equals("8puzzle")) {
			int [] startBoard = eightPuzzleStart(args);
			if (searchType.equals("dfs")) {
				DFSearch.search(startBoard);
			}
			if (searchType.equals("bfs")) {
				BFSearch.search(startBoard);
			}
		}
		
		//FWGC if statements to either parse Depth First Search or Breadth First Search algorithms
		if (problem.equals("fwgc")) {
			if (searchType.equals("dfs")) {
				DFSearch.search();
			}
			if (searchType.equals("bfs")) {
				BFSearch.search();
			}
		}
	}
}
