//import java.util.Comparator;

public class Node implements Comparable<Node>{

	public PuzzleBoard puzzle;
	int heuristicVal;
	int edgeCost; //path cost
	int functionCost;
	boolean isVisited;

	public Node(PuzzleBoard x, int heuristic, int edge) {
		puzzle = x;
		heuristicVal = heuristic;
		edgeCost = edge;
		isVisited = false;
		functionCost = edgeCost + heuristicVal;
	}

//	public static boolean nodeComp(Node a, Node b) {
//		return a.heuristicVal+a.edgeCost < b.heuristicVal+b.edgeCost;
//	}
	
	public static int[] child(PuzzleBoard x) {
		PuzzleBoard.isLegal(x);
		return x.legalz;
	}


	@Override
	public int compareTo(Node a) {
		if (this.functionCost < a.functionCost) {
			return 1;
		}
		
		if (this.functionCost == a.functionCost) {
			return 0;
		}
		
		return -1;
	}
}
