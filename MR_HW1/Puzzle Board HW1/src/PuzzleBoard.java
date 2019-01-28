import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.*;


public class PuzzleBoard implements Comparable<PuzzleBoard> {
	private int[][] table;
	private int zeroRow;
	private int zeroCol;

	int pathCost;
	int functionCost;


	//new, ordered Puzzle Board constructor
	public PuzzleBoard() {
		this.table = new int[3][3];
		this.zeroRow = 2;
		this.zeroCol = 2;
		
		pathCost = 0;
		
		//can we call the heuristic like this? 
		this.functionCost = pathCost + this.heuristicManhattan();

		int counter = 1;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				table[i][j] = counter;
				counter++;
			}
		}
		table[zeroRow][zeroCol] = 0;

	}

	//new PuzzleBoard based on a template of a board
	public PuzzleBoard(PuzzleBoard template) {
		this.table = new int[3][3];
		this.zeroRow = 2;
		this.zeroCol = 2;
		
		this.pathCost = template.pathCost;
		this.functionCost = template.functionCost;

		int counter = 1;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				this.table[i][j] = template.table[i][j];
				counter++;
			}
		}

	} 

	// Prints out the table
	public void printTable() {
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {

				System.out.print(this.table[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	//Upon request, changes the board with an UP move
	public void moveUp() {
		this.table[this.zeroRow][this.zeroCol]= this.table[this.zeroRow - 1][this.zeroCol];
		this.table[this.zeroRow - 1][this.zeroCol] = 0;
		this.zeroRow--;
	}

	//Upon request, changes the board with an RIGHT move
	public void moveRight() {
		this.table[this.zeroRow][this.zeroCol]= this.table[this.zeroRow][this.zeroCol + 1];
		this.table[this.zeroRow][this.zeroCol + 1] = 0;
		this.zeroCol++;
	}

	//Upon request, changes the board with an DOWN move
	public void moveDown() {
		this.table[this.zeroRow][this.zeroCol]= this.table[this.zeroRow + 1][this.zeroCol];
		this.table[this.zeroRow + 1][this.zeroCol] = 0;
		this.zeroRow++;
	}

	//Upon request, changes the board with an LEFT move
	public void moveLeft() {
		this.table[this.zeroRow][this.zeroCol ]= this.table[this.zeroRow][this.zeroCol - 1];
		this.table[this.zeroRow][this.zeroCol - 1] = 0;
		this.zeroCol--;
	}



	//redo to avoid static
	public List<Integer> isLegal() {
		List<Integer> legalMoves = new ArrayList<Integer>(); 

		//check for all legal moves and update the array
		if(this.zeroRow >= 1) {
			//LeagalMoves == up;
			legalMoves.add(1);
		}
		if(this.zeroCol <= 1) {
			//LeagalMoves == right;
			legalMoves.add(2);
		}	
		if(this.zeroRow <= 1) {
			//LeagalMoves == down;
			legalMoves.add(3);
		}
		if(this.zeroCol >= 1) {
			//LeagalMoves == left;
			legalMoves.add(4);
		}

		return legalMoves;

	}


	//Checks if the board matches the initial 123456780 state
	public boolean isGoal() {
		int counter = 1;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(this.table[i][j] == counter) {
					counter++;
				}
				else if(counter == 9) {
					if(this.table[2][2] == 0) {
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
	//return a one-time list vs. an atribute then pick based on the length of the list

	private void randomize() {
		List<Integer> legalMoves = this.isLegal(); 
		int randomMove;

		//create a random value to choose a random move direction
		Random rand = new Random();
		//		System.out.println("Size of the set " + legalMoves.size());

		randomMove = rand.nextInt(legalMoves.size());
		//		System.out.println("Random index is " + randomMove);

		//		System.out.println("Random direction is " + legalMoves.get(randomMove));

		if(legalMoves.get(randomMove) == 1) {
			this.moveUp();
			//			this.printTable();		
		}
		else if(legalMoves.get(randomMove) == 2) {
			this.moveRight();
			//			this.printTable();
		}
		else if(legalMoves.get(randomMove) == 3) {
			this.moveDown();
			//			this.printTable();
		}
		else if(legalMoves.get(randomMove) == 4) {
			this.moveLeft();
			//			this.printTable();
		}

	}



	public void randomizeBoard(int iterations) {
		for(int i = 0; i < iterations; i++) {
			this.randomize();
		}
	}


	//helper for heuristicManhattan
	private int mDistance(int row, int col) {
		int element = this.table[row][col];

		// Calculate element's goal row & col
		int temp = element - 1;
		int goalRow = temp / 3;
		int goalCol = temp % 3;

		int mDistance = Math.abs(goalRow - row) + Math.abs(goalCol - col);	

		//			System.out.println("mDistance is " + mDistance);
		return mDistance;
	}


	//Heuristic to calculate the Manhattan distance 
	public  int heuristicManhattan() {
		int h = 0;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(this.table[i][j] != 0) {
					h += this.mDistance(i, j);
				}
			}
		}

		return h;
	}

	//helper for heuristicMisplaced
	private int misplaced(int row, int col) {
		int element = this.table[row][col];


		// Calculate element's goal row & col

		int temp = element-1;
		if(row == temp/3 && col == temp%3) {
			return 0;
		}
		else {
			return 1;
		}
	}

	//Heuristic to calculate the amount of tiles misplaced
	public int heuristicMisplaced() {
		int h = 0;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(this.table[i][j] != 0) {
					h += this.misplaced(i, j);
				}
			}
		}
		//			System.out.println("Heuristic 2 is " + h);
		return h;
	}





	//Equals method (tiles are the same or not) boolean. 

	//hashCode method



	public static void main(String[] args) {

		PuzzleBoard a = new PuzzleBoard();

		a.printTable();

		//		System.out.println(a.isGoal());

		a.randomizeBoard(3);

		a.printTable();


	}

	@Override
	public int compareTo(PuzzleBoard o) {
		// TODO Auto-generated method stub
		return 0;
	}


}
