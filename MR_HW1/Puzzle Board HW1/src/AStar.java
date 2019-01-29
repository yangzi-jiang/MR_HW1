import java.util.*;

//not using static methods
public class AStar 	{

	//needs to be fully redone since no longer using legalzzz
	private static void queueInsert(PuzzleBoard state, PriorityQueue frontier) {
		List<Integer> children = state.isLegal();

		if(children.contains(1)){
			PuzzleBoard upBoard = new PuzzleBoard(state);
			upBoard.moveUp();
			frontier.add(upBoard);
		}

		if(children.contains(2)){
			PuzzleBoard rightBoard = new PuzzleBoard(state);
			rightBoard.moveRight();
			frontier.add(rightBoard);
		}

		if(children.contains(3)){
			PuzzleBoard downBoard = new PuzzleBoard(state);
			downBoard.moveDown();
			frontier.add(downBoard);
		}

		if(children.contains(4)){
			PuzzleBoard leftBoard = new PuzzleBoard(state);
			leftBoard.moveLeft();
			frontier.add(leftBoard);
		}


	}



	//	GRAPH-SEARCH(problem):
	//		initialize frontier using the initial state of problem
	//		loop:
	//			if frontier is empty, return failure
	//			pop node from frontier
	//			if node contains goal state, return solution
	//			for each successor s of node:
	//				add s to frontier
	public static int solve(PuzzleBoard start) {

		int nodesCounter = 0;
		Set<String> visited = new HashSet<String>();
		PriorityQueue<PuzzleBoard> frontier = new PriorityQueue<PuzzleBoard>();

		frontier.add(start);

		while(!frontier.isEmpty()) {
			PuzzleBoard next = frontier.poll();
			//after popping a node, we increment the counter
			nodesCounter++;
			
			System.out.println(nodesCounter);
			
//			System.out.println("After node is popped ");
//			next.printTable();
			
			// visited
			visited.add(start.toString());
			

			next.printTable();

			if(next.isGoal()) {
				return nodesCounter; //returns counter....
			}

			queueInsert(next, frontier);
		}

		System.out.println("Error");
		return -1; // throw an exception

	}



	public static void main(String[] args) {

		// Create boards and solve them using A*

		PuzzleBoard a = new PuzzleBoard();

		a.printTable();

		System.out.println(" ");

		a.randomizeBoard(2);

		a.printTable();
		System.out.println(" ");

		System.out.println("heuristic1 is " + a.heuristicManhattan());

		System.out.println(" ");

		System.out.println(solve(a));


	}

}
