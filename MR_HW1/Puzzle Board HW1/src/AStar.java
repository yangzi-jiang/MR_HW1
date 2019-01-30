import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.HashSet;

//not using static methods
public class AStar{
	
	//Memory
	public static int queueInsert(PuzzleBoard state, PriorityQueue frontier, Set visited, int nodesCounter) {
		List<Integer> children = state.isLegal();

		if(children.contains(1)){
			PuzzleBoard upBoard = new PuzzleBoard(state);
			upBoard.moveUp();
			upBoard.pathCost++;
			upBoard.heuristicCost = upBoard.heuristicManhattan();
			//			upBoard.heuristicCost = upBoard.heuristicMisplaced();
			upBoard.functionCost = upBoard.pathCost + upBoard.heuristicCost;
			if(!visited.contains(upBoard)){
				frontier.add(upBoard);
				nodesCounter++;
			}
		}

		if(children.contains(2)){
			PuzzleBoard rightBoard = new PuzzleBoard(state);
			rightBoard.moveRight();
			rightBoard.pathCost++;
			rightBoard.heuristicCost = rightBoard.heuristicManhattan();
			//			rightBoard.heuristicCost = upBoard.heuristicMisplaced();
			rightBoard.functionCost = rightBoard.pathCost + rightBoard.heuristicCost;
			if(!visited.contains(rightBoard)){
				frontier.add(rightBoard);
				nodesCounter++;
			}
		}

		if(children.contains(3)){
			PuzzleBoard downBoard = new PuzzleBoard(state);
			downBoard.moveDown();
			downBoard.pathCost++;
			downBoard.heuristicCost = downBoard.heuristicManhattan();
			//			downBoard.heuristicCost = upBoard.heuristicMisplaced();
			downBoard.functionCost = downBoard.pathCost + downBoard.heuristicCost;
			if(!visited.contains(downBoard)){
				frontier.add(downBoard);
				nodesCounter++;
			}
		}

		if(children.contains(4)){
			PuzzleBoard leftBoard = new PuzzleBoard(state);
			leftBoard.moveLeft();
			leftBoard.pathCost++;
			leftBoard.heuristicCost = leftBoard.heuristicManhattan();
			//			leftBoard.heuristicCost = upBoard.heuristicMisplaced();
			leftBoard.functionCost = leftBoard.pathCost + leftBoard.heuristicCost;
			if(!visited.contains(leftBoard)){
				frontier.add(leftBoard);
				nodesCounter++;
			}
		}

		return nodesCounter;
	}


	//	GRAPH-SEARCH(problem):
	//		initialize frontier using the initial state of problem
	//		loop:
	//			if frontier is empty, return failure
	//			pop node from frontier
	//			if node contains goal state, return solution
	//			for each successor s of node:
	//				add s to frontier
	public static int[] solve(PuzzleBoard start) throws Exception {
		int[] nodesAndPathcost = new int[2];
		int nodesCounter = 0;
		Set<PuzzleBoard> visited = new HashSet<PuzzleBoard>();
		PriorityQueue<PuzzleBoard> frontier = new PriorityQueue<PuzzleBoard>();

		frontier.add(start);
		nodesCounter++;

		while(!frontier.isEmpty()) {
			PuzzleBoard next = frontier.poll();
			visited.add(next);

			if(next.isGoal()) {
//				next.printTable();
//				System.out.println("The path cost is " + next.pathCost);
//				System.out.println("The heuristic cost is " + next.heuristicCost);
//				System.out.println("The function cost is " + next.functionCost);
				nodesAndPathcost[0] = nodesCounter;
				nodesAndPathcost[1] = next.pathCost;
				return nodesAndPathcost;
			}	
			nodesCounter = queueInsert(next, frontier, visited, nodesCounter); // no need to count nodes when inserting
		}

		System.out.println("Error");
		throw new Exception(); // is this how you throw?
	}


	public static void usingDataOutputStream(List<Integer> bucket, String filename) throws IOException {

		FileOutputStream outputStream = new FileOutputStream("/Users/yangzijiang/Desktop/" + filename);
		DataOutputStream dataOutStream = new DataOutputStream(new BufferedOutputStream(outputStream));

		for(int i=0; i < bucket.size();i++) {
			dataOutStream.writeUTF(Integer.toString(bucket.get(i)) +"\n");
		}

		dataOutStream.close();
	}


	public static void main(String[] args) throws Exception {
		List<Integer> bucket = new ArrayList<Integer>();
		int[] nodesAndDepth = new int[2];
		
		while(bucket.size() < 100) {
			PuzzleBoard board = new PuzzleBoard();
			board.randomizeBoard(26);
			nodesAndDepth = solve(board);
//			System.out.println("pathcostB is " + nodesAndDepth[1]);
			
			if(nodesAndDepth[1] == 12) { // If path == 2
				bucket.add(nodesAndDepth[0]);
//				System.out.println("bucket at 3 is " + bucket.get(3));	
			}
		}
		
		PrintWriter pw = new PrintWriter(new File("/Users/yangzijiang/Desktop/depth12.csv"));
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < bucket.size();i++) {
			sb.append(Integer.toString(bucket.get(i)));
			sb.append(',');
		}
		pw.write(sb.toString());
        pw.close();
		
//		usingDataOutputStream(bucket, "depth24.txt");
	}

}
