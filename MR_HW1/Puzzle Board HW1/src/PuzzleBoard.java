import java.util.Random;

public class PuzzleBoard implements Comparable<PuzzleBoard> {
	int[][] table;
	int zeroRow;
	int zeroCol;
	int[] legalz;
	
	//Puzzle Board constructor
	public PuzzleBoard() {
		this.table = new int[3][3];
		this.legalz = new int[4];
		this.zeroRow = 2;
		this.zeroCol = 2;

		int counter = 1;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				table[i][j] = counter;
				counter++;
			}
		}
		table[zeroRow][zeroCol] = 0;

	}
//edit to fill in from scratch
	public PuzzleBoard(PuzzleBoard x) {
		this.table = x.table;
		this.legalz = x.legalz;
		this.zeroRow = x.zeroRow;
		this.zeroCol = x.zeroCol;
	}
	
//create a error puzzle
	public PuzzleBoard(int zero) {
		this.table = new int[3][3];
		this.legalz = new int[4];
		this.zeroRow = 2;
		this.zeroCol = 2;
	}
	
	
	// Prints out the table
	public static void printTable(PuzzleBoard x) {
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {

				System.out.print(x.table[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void moveUp(PuzzleBoard x) {
		x.table[x.zeroRow][x.zeroCol]= x.table[x.zeroRow - 1][x.zeroCol];
		x.table[x.zeroRow - 1][x.zeroCol] = 0;
		x.zeroRow--;
	}

	public static void moveRight(PuzzleBoard x) {
		x.table[x.zeroRow][x.zeroCol]= x.table[x.zeroRow][x.zeroCol + 1];
		x.table[x.zeroRow][x.zeroCol + 1] = 0;
		x.zeroCol++;
	}

	public static void moveDown(PuzzleBoard x) {
		x.table[x.zeroRow][x.zeroCol]= x.table[x.zeroRow + 1][x.zeroCol];
		x.table[x.zeroRow + 1][x.zeroCol] = 0;
		x.zeroRow++;
	}

	public static void moveLeft(PuzzleBoard x) {
		x.table[x.zeroRow][x.zeroCol ]= x.table[x.zeroRow][x.zeroCol - 1];
		x.table[x.zeroRow][x.zeroCol - 1] = 0;
		x.zeroCol--;
	}

//redo to avoid static
	public static void isLegal(PuzzleBoard x) {

		//zero out all the legalz array
		for(int i=0; i<4; i++) {
			x.legalz[i] = 0;
		}

		//check for all legal moves and update the array
		if(x.zeroRow >= 1) {
			//LeagalMoves == up;
			x.legalz[0] = 1;
		}
		if(x.zeroCol <= 1) {
			//LeagalMoves == right;
			x.legalz[1] = 1;
		}	
		if(x.zeroRow <= 1) {
			//LeagalMoves == down;
			x.legalz[2] = 1;
		}
		if(x.zeroCol >= 1) {
			//LeagalMoves == left;
			x.legalz[3] = 1;
		}

	}



	public static boolean isGoal(PuzzleBoard x) {
		int counter = 1;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(x.table[i][j] == counter) {
					counter++;
				}
				else if(counter == 9) {
					if(x.table[2][2] == 0) {
						return true;
					}
					else {
						return false;
					}
				}
				else {
					return false;
				}
			}
		}
		return true;
	}



	//code from the slides. 
	//priority queue
	//f(n) = h(n)..heuristic..... + g(n)....path cost...

	private static void randomize(PuzzleBoard x) {
		isLegal(x); //return a one-time list vs. an atribute then pick based on the length of the list
		int check = 0;
		int randomMove;
		int temp = 0;

		//check how many legal moves are available
		for(int i = 0; i<4; i++) {
			check += x.legalz[i];
		}
//		System.out.println("Check is " + check);


		Random rand = new Random();
		randomMove = rand.nextInt((check - 1) + 1) + 1;
//		System.out.println("Random is " + randomMove);


		for(int i = 0; i<4; i++) {
			if(x.legalz[i] == 1) {
				temp++;
			}
			if(temp == randomMove) {
				if(i == 0) {
					moveUp(x);
//					printTable(x);

				}
				else if(i == 1) {
					moveRight(x);
//					printTable(x);

				}
				else if(i == 2) {
					moveDown(x);
//					printTable(x);

				}
				else {
					moveLeft(x);
//					printTable(x);
				}
				break;
			}
		}

	}

	public static void randomizeBoard(PuzzleBoard x, int iterations) {
		for(int i = 0; i < iterations; i++) {
			randomize(x);
		}
	}


	@Override
	public int compareTo(PuzzleBoard o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//Equals method (tiles are the same or not) boolean. 
	
	//hashCode method
	
	

	public static void main(String[] args) {

		PuzzleBoard a = new PuzzleBoard();

		printTable(a);

		System.out.println(isGoal(a));

		randomizeBoard(a, 10);
		
		printTable(a);


	}
	

}
