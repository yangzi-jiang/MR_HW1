import java.util.Map;
import java.util.PriorityQueue;

public class AStar extends PuzzleBoard {

	//Heuristic to calculate the Manhattan distance 
	public static int heuristic1(PuzzleBoard x) {
		int h = 0;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(x.table[i][j] != 0) {
					h += mDistance(i, j, x);
				}
			}
		}

		return h;
	}

	//helper for heuristic1
	private static int mDistance(int row, int col, PuzzleBoard x) {
		int element = x.table[row][col];

		// Calculate element's goal row & col
		int temp = element - 1;
		int goalRow = temp / 3;
		int goalCol = temp % 3;

		int mDistance = Math.abs(goalRow - row) + Math.abs(goalCol - col);	

//		System.out.println("mDistance is " + mDistance);
		return mDistance;
	}



	//Heuristic to calculate the amount of tiles misplaced
	public static int heuristic2(PuzzleBoard x) {
		int h = 0;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(x.table[i][j] != 0) {
					h += misplaced(i, j, x);
				}
			}
		}
//		System.out.println("Heuristic 2 is " + h);
		return h;
	}

	//helper for heuristic2
	private static int misplaced(int row, int col, PuzzleBoard x) {
		int element = x.table[row][col];


		// Calculate element's goal row & col

		int temp = element-1;
		if(row == temp/3 && col == temp%3) {
			return 0;
		}
		else {
			return 1;
		}
	}


	private static void queueInsert(PuzzleBoard x, PriorityQueue frontier) {
		isLegal(x);
		
		if(x.legalz[0] == 1) {
			PuzzleBoard upBoard = new PuzzleBoard(x);
			moveUp(upBoard);
			Node up = new Node(upBoard, heuristic1(upBoard), 1);
			frontier.add(up);
		}
		
		if(x.legalz[1] == 1) {
			PuzzleBoard rightBoard = new PuzzleBoard(x);
			moveUp(rightBoard);
			Node right = new Node(rightBoard, heuristic1(rightBoard), 1);
			frontier.add(right);
		}
		
		if(x.legalz[2] == 1) {
			PuzzleBoard downBoard = new PuzzleBoard(x);
			moveUp(downBoard);
			Node down = new Node(downBoard, heuristic1(downBoard), 1);
			frontier.add(down);
		}
		
		if(x.legalz[3] == 1) {
			PuzzleBoard leftBoard = new PuzzleBoard(x);
			moveUp(leftBoard);
			Node left = new Node(leftBoard, heuristic1(leftBoard), 1);
			frontier.add(left);
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
	public static PuzzleBoard aStar(PuzzleBoard x) {
		
		PriorityQueue<Node> frontier = new PriorityQueue<Node>();
		
		Node start = new Node(x, heuristic1(x), 1);
		
		frontier.add(start);
		
		while(!frontier.isEmpty()) {
			Node next = frontier.poll();
			
			printTable(next.puzzle);
			
			if(isGoal(next.puzzle)) {
				return next.puzzle;
			}
			queueInsert(next.puzzle,frontier);
		}
		
		PuzzleBoard error = new PuzzleBoard(0);
		return error;

	}



	public static void main(String[] args) {

		PuzzleBoard a = new PuzzleBoard();

		printTable(a);
		
		System.out.println(" ");

		randomizeBoard(a, 3);

		printTable(a);
		System.out.println(" ");

		System.out.println("heuristic1 is " + heuristic1(a));
		
		System.out.println(" ");
		
		PuzzleBoard solution = new PuzzleBoard(aStar(a));
		
		printTable(solution);
		

	}

}
