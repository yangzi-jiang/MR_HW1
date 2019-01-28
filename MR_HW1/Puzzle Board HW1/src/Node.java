//import java.util.Comparator;

public class Node implements Comparable<Node>{

	public PuzzleBoard puzzle;
	int heuristicVal;
	int edgeCost;
	int functionCost = edgeCost + heuristicVal;
	boolean isVisited;

	public Node(PuzzleBoard x, int heuristic, int edge) {
		this.puzzle = x;
		this.heuristicVal = heuristic;
		this.edgeCost = edge;
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
		if (a.heuristicVal+a.edgeCost < this.functionCost) {
			return 1;
		}
		
		if (a.heuristicVal+a.edgeCost >= this.functionCost) {
			return 0;
		}
		
		return -1;
	}
}
