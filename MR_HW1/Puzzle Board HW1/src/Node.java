
public class Node extends PuzzleBoard{

	public PuzzleBoard puzzle;
	int heuristicVal;
	int edgeCost;

	public Node(PuzzleBoard x, int heuristic, int edge) {
		puzzle = x;
		heuristicVal = heuristic;
		edgeCost = edge;
	}

	public static boolean nodeComp(Node a, Node b) {
		return a.heuristicVal+a.edgeCost < b.heuristicVal+b.edgeCost;
	}
	
	public static int[] child(PuzzleBoard x) {
		isLegal(x);
		return x.legalz;
	}

//	public static boolean nodeComp(Node a, Node b, Node c) {
//		return a.heuristicVal+a.edgeCost < b.heuristicVal+b.edgeCost;
//	}
//
//	public static Node findMin(Node a, Node b, Node c, Node d) {
//		Math.min(Math.min(Math.min(a.heuristicVal+a.edgeCost, b.heuristicVal+b.edgeCost), 
//				c.heuristicVal+c.edgeCost),d.heuristicVal+d.edgeCost);
//		
//		return a;
//	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
