public class AStar extends PuzzleBoard {
	
	public static int heuristic(PuzzleBoard x) {
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
	
	private static int mDistance(int row, int col, PuzzleBoard x) {
		int element = x.table[row][col];
		
		System.out.println("element is " + element);
		// Calculate element's goal row & col
		int temp = element - 1;
		int goalRow = temp / 3;
		int goalCol = temp % 3;
		
		int mDistance = Math.abs(goalRow - row) + Math.abs(goalCol - col);	
		
		System.out.println("mDistance is " + mDistance);
		return mDistance;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PuzzleBoard a = new PuzzleBoard();

		printTable(a);
		
		System.out.println(isGoal(a));
		
		randomizeBoard(a, 500);
		
		printTable(a);
		
		System.out.println("h is " + heuristic(a));
		
	}

}
