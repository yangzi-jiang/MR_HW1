import java.util.*;

//not using static methods
public class AStar 	{


	//needs to be fully redone since no longer using legalzzz
	private static void queueInsert(PuzzleBoard x, PriorityQueue frontier) {
		//		isLegal(x);
		//		
		//		if(x.legalz[0] == 1) {
		//			PuzzleBoard upBoard = new PuzzleBoard(x);
		//			moveUp(upBoard);
		//			Node up = new Node(upBoard, heuristic1(upBoard), 1);
		//			frontier.add(up);
		//		}
		//		
		//		if(x.legalz[1] == 1) {
		//			PuzzleBoard rightBoard = new PuzzleBoard(x);
		//			moveRight(rightBoard);
		//			Node right = new Node(rightBoard, heuristic1(rightBoard), 1);
		//			frontier.add(right);
		//		}
		//		
		//		if(x.legalz[2] == 1) {
		//			PuzzleBoard downBoard = new PuzzleBoard(x);
		//			moveDown(downBoard);
		//			Node down = new Node(downBoard, heuristic1(downBoard), 1);
		//			frontier.add(down);
		//		}
		//		
		//		if(x.legalz[3] == 1) {
		//			PuzzleBoard leftBoard = new PuzzleBoard(x);
		//			moveLeft(leftBoard);
		//			Node left = new Node(leftBoard, heuristic1(leftBoard), 1);
		//			frontier.add(left);
		//		}

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

		PriorityQueue<PuzzleBoard> frontier = new PriorityQueue<PuzzleBoard>();

		frontier.add(start);

		while(!frontier.isEmpty()) {
			PuzzleBoard next = frontier.poll();

			next.printTable();

			//counter++ somewhere
			if(next.isGoal()) {
				return 0; //returns counter....
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

		a.randomizeBoard(3);

		a.printTable();
		System.out.println(" ");

		System.out.println("heuristic1 is " + a.heuristicManhattan());

		System.out.println(" ");

		System.out.println(solve(a));


	}

}
