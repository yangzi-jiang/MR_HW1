import java.util.*;

//not using static methods
public class AStar 	{
	
// thoughts on using constructor instead????
//	private int nodesCounter;
//
//	public AStar() {
//		this.nodesCounter = 0;
//	}
//	
//	public int getNodesCounter() {
//		return this.nodesCounter;
//	}
//		
	
	//needs to be fully redone since no longer using legalzzz
	//Memory
	public static void queueInsert(PuzzleBoard state, PriorityQueue frontier, Set visited, int nodesCounter) {
		List<Integer> children = state.isLegal();

		if(children.contains(1)){
			PuzzleBoard upBoard = new PuzzleBoard(state);
			upBoard.moveUp();
			if(!visited.contains(upBoard.boardToString())){
				nodesCounter++;
				frontier.add(upBoard);
			}
		}

		if(children.contains(2)){
			PuzzleBoard rightBoard = new PuzzleBoard(state);
			rightBoard.moveRight();
			frontier.add(rightBoard);
			if(!visited.contains(rightBoard.boardToString())){
				nodesCounter++;
				frontier.add(rightBoard);
			}
		}

		if(children.contains(3)){
			PuzzleBoard downBoard = new PuzzleBoard(state);
			downBoard.moveDown();
			frontier.add(downBoard);
			if(!visited.contains(downBoard.boardToString())){
				nodesCounter++;
				frontier.add(downBoard);
			}
		}

		if(children.contains(4)){
			PuzzleBoard leftBoard = new PuzzleBoard(state);
			leftBoard.moveLeft();
			frontier.add(leftBoard);
			if(!visited.contains(leftBoard.boardToString())){
				nodesCounter++;
				frontier.add(leftBoard);
			}
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
	public static int solve(PuzzleBoard start) throws Exception {
		
		int nodesCounter = 0;

		Set<String> visited = new HashSet<String>();
		PriorityQueue<PuzzleBoard> frontier = new PriorityQueue<PuzzleBoard>();

		frontier.add(start);
		
		while(!frontier.isEmpty()) {
			PuzzleBoard next = frontier.poll();
			//after popping a node, we increment the counter
			nodesCounter++;
			
//			System.out.println("After node is popped ");
//			next.printTable();
			
			// visited
			visited.add(next.boardToString());
			
			next.printTable();

			if(next.isGoal()) {
				
				System.out.println(nodesCounter);
				return start.pathCost; //returns counter....
				
				// YJ: we need to return a counter for how many nodes we created, and the # steps to solution
			}

			queueInsert(next, frontier, visited, nodesCounter);
			
			// YJ: need to re-check recently added nodes against the visited ones,
				// if a recent node's pathCost is less than that one in visited node, we need to update it?
				// update visited
				// calculate new pathcost
		}

		System.out.println("Error");
		throw new Exception();
		// is this how you throw?
	}


	public static void main(String[] args) throws Exception {

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
