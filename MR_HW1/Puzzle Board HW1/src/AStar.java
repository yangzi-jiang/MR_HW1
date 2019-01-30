import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
		System.out.println("nodecounter is : " + nodesCounter);

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
	public static int solve(PuzzleBoard start) throws Exception {

		int nodesCounter = 0;
		Set<PuzzleBoard> visited = new HashSet<PuzzleBoard>();
		PriorityQueue<PuzzleBoard> frontier = new PriorityQueue<PuzzleBoard>();

		frontier.add(start);
		nodesCounter++;

		while(!frontier.isEmpty()) {
			PuzzleBoard next = frontier.poll();
			//			System.out.println("After node is popped ");
			visited.add(next);
			next.printTable();

			System.out.println("The path cost is " + next.pathCost);
			System.out.println("The heuristic cost is " + next.heuristicCost);
			System.out.println("The function cost is " + next.functionCost);

			if(next.isGoal()) {
				next.printTable();
				System.out.println("The path cost is " + next.pathCost);
				System.out.println("The heuristic cost is " + next.heuristicCost);
				System.out.println("The function cost is " + next.functionCost);
				return nodesCounter;
			}	
			nodesCounter = queueInsert(next, frontier, visited, nodesCounter); // no need to count nodes when inserting
		}

		System.out.println("Error");
		throw new Exception(); // is this how you throw?
	}


	public static void usingDataOutputStream(List<Integer> bucket) throws IOException {
		String fileContent = "Bucket 2 \n";


		FileOutputStream outputStream = new FileOutputStream("/Users/andriymolchanov/Desktop/trial.txt");
		DataOutputStream dataOutStream = new DataOutputStream(new BufferedOutputStream(outputStream));
		dataOutStream.writeUTF(fileContent);

		for(int i=0; i < bucket.size();i++) {
			dataOutStream.writeUTF(Integer.toString(bucket.get(i)) +"\n");
		}



		dataOutStream.close();
	}


	public static void main(String[] args) throws Exception {

		PuzzleBoard board = new PuzzleBoard();
		board.printTable();

		board.randomizeBoard(50);

		System.out.println("nodecounter is : " + solve(board));	

		List<Integer> test = new ArrayList<Integer>();
		test.add(5);
		test.add(2);
		test.add(1);
		test.add(5);
		test.add(7);
		test.add(2);
		test.add(8);
		test.add(2);
		test.add(3);
		test.add(2);
		
		usingDataOutputStream(test);
	}

}
