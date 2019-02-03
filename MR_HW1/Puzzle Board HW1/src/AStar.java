import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.HashSet;

//not using static methods
public class AStar {

	// Memory
	public static int queueInsert(PuzzleBoard state, PriorityQueue frontier, Set visited, int nodesCounter,
			int heuristicsOption) {
		List<Integer> children = state.isLegal();

		if (children.contains(1)) {
			PuzzleBoard upBoard = new PuzzleBoard(state);
			upBoard.moveUp();
			upBoard.pathCost++;
			if (heuristicsOption == 2) {
				upBoard.heuristicCost = upBoard.heuristicManhattan();
			}
			if (heuristicsOption == 1) {
				upBoard.heuristicCost = upBoard.heuristicMisplaced();
			}
			if (heuristicsOption == 3) {
				upBoard.heuristicCost = upBoard.heuristicLinearConflict();
			}

			upBoard.functionCost = upBoard.pathCost + upBoard.heuristicCost;
			if (!visited.contains(upBoard)) {
				frontier.add(upBoard);
				nodesCounter++;
			}
		}

		if (children.contains(2)) {
			PuzzleBoard rightBoard = new PuzzleBoard(state);
			rightBoard.moveRight();
			rightBoard.pathCost++;
			if (heuristicsOption == 2) {
				rightBoard.heuristicCost = rightBoard.heuristicManhattan();
			}
			if (heuristicsOption == 1) {
				rightBoard.heuristicCost = rightBoard.heuristicMisplaced();
			}
			if (heuristicsOption == 3) {
				rightBoard.heuristicCost = rightBoard.heuristicLinearConflict();
			}
			rightBoard.functionCost = rightBoard.pathCost + rightBoard.heuristicCost;
			if (!visited.contains(rightBoard)) {
				frontier.add(rightBoard);
				nodesCounter++;
			}
		}

		if (children.contains(3)) {
			PuzzleBoard downBoard = new PuzzleBoard(state);
			downBoard.moveDown();
			downBoard.pathCost++;
			if (heuristicsOption == 2) {
				downBoard.heuristicCost = downBoard.heuristicManhattan();
			}
			if (heuristicsOption == 1) {
				downBoard.heuristicCost = downBoard.heuristicMisplaced();
			}
			if (heuristicsOption == 3) {
				downBoard.heuristicCost = downBoard.heuristicLinearConflict();
			}
			downBoard.functionCost = downBoard.pathCost + downBoard.heuristicCost;
			if (!visited.contains(downBoard)) {
				frontier.add(downBoard);
				nodesCounter++;
			}
		}

		if (children.contains(4)) {
			PuzzleBoard leftBoard = new PuzzleBoard(state);
			leftBoard.moveLeft();
			leftBoard.pathCost++;
			if (heuristicsOption == 2) {
				leftBoard.heuristicCost = leftBoard.heuristicManhattan();
			}
			if (heuristicsOption == 1) {
				leftBoard.heuristicCost = leftBoard.heuristicMisplaced();
			}
			if (heuristicsOption == 3) {
				leftBoard.heuristicCost = leftBoard.heuristicLinearConflict();
			}
			leftBoard.functionCost = leftBoard.pathCost + leftBoard.heuristicCost;
			if (!visited.contains(leftBoard)) {
				frontier.add(leftBoard);
				nodesCounter++;
			}
		}

		return nodesCounter;
	}

	// Using A* to solve the board
	public static int[] solve(PuzzleBoard start, int heuristicsOption) throws Exception {
		int[] nodesAndPathcost = new int[2];
		int nodesCounter = 0;
		Set<PuzzleBoard> visited = new HashSet<PuzzleBoard>();
		PriorityQueue<PuzzleBoard> frontier = new PriorityQueue<PuzzleBoard>();

		frontier.add(start);
		nodesCounter++;

		while (!frontier.isEmpty()) {
			PuzzleBoard next = frontier.poll();
			visited.add(next);

			if (next.isGoal()) {
//				next.printTable();
//				System.out.println("The path cost is " + next.pathCost);
//				System.out.println("The heuristic cost is " + next.heuristicCost);
//				System.out.println("The function cost is " + next.functionCost);
				nodesAndPathcost[1] = nodesCounter;
				nodesAndPathcost[0] = next.pathCost;
				return nodesAndPathcost;
			}
			nodesCounter = queueInsert(next, frontier, visited, nodesCounter, heuristicsOption); // no need to count
																									// nodes when
																									// inserting
		}

		System.out.println("Error");
		throw new Exception(); // is this how you throw?
	}

	public static void usingDataOutputStream(List<Integer> bucket, String filename) throws IOException {

		FileOutputStream outputStream = new FileOutputStream("/Users/yangzijiang/Desktop/" + filename);
		DataOutputStream dataOutStream = new DataOutputStream(new BufferedOutputStream(outputStream));

		for (int i = 0; i < bucket.size(); i++) {
			dataOutStream.writeUTF(Integer.toString(bucket.get(i)) + "\n");
		}

		dataOutStream.close();
	}

	public static void main(String[] args) throws Exception {
		List<Integer> bucket = new ArrayList<Integer>();
		int[] nodesAndDepth1 = new int[2]; // Store results from h1
		int[] nodesAndDepth2 = new int[2]; // Store results from h2
		int[] nodesAndDepth3 = new int[2]; // Store results from h3

		// Set up
//		int heuristicsOption = 3;

		// Generate data
		for (int depth = 2; depth < 25; depth = depth + 2) {
			while (bucket.size() < 300 * (depth / 2)) {
				PuzzleBoard board = new PuzzleBoard();
				board.randomizeBoard(depth * 4);
				nodesAndDepth1 = solve(board, 1);
				nodesAndDepth2 = solve(board, 2);
				nodesAndDepth3 = solve(board, 3);
//				System.out.println("pathcostB is " + nodesAndDepth[1]);

				if (nodesAndDepth1[0] == depth) { // If pathCost == 2
					bucket.add(nodesAndDepth1[1]);
					bucket.add(nodesAndDepth2[1]);
					bucket.add(nodesAndDepth3[1]);
//					System.out.println("bucket at 3 is " + bucket.get(3));	
				}
			}
		}

		PrintWriter pw = new PrintWriter(new File("/Users/yangzijiang/Desktop/allHeuristicsData.csv"));
//		PrintWriter pw = new PrintWriter(new File("/Users/yangzijiang/Desktop/h" + heuristicsOption + "depth" + depth + ".csv"));
		StringBuilder sb = new StringBuilder();
		
		for (int depth = 2; depth <= 24; depth = depth + 2) {
			sb.append("Depth " + depth + '\n');
			for (int data = 0; data < 100; data++) {
				for (int heuristic = 0; heuristic < 3; heuristic++) {
					sb.append(Integer.toString(bucket.get(heuristic + data * 3 + (depth - 2) / 2 * 300)));
					sb.append(',');
				}
				sb.append('\n');
			}
			sb.append('\n');
		}
		pw.write(sb.toString());
		pw.close();
	}

}
