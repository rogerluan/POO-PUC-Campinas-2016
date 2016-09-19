

public class TicTacToe {

	 private char[][] board; 
     private char currentPlayer;
     
     public TicTacToe() {
         board = new char[3][3];
         currentPlayer = 'X';
         clearBoard();
     }

	/*
	 * Game Logic
	 */
	
	private static boolean isBoardFull(char[][] board) {
	    boolean isFull = true;
	    for (int i = 0; i < 3; i++) {
	        for (int j = 0; j < 3; j++) {
	            if (board[i][j] == ' ') {
	                isFull = false;
	            }
	        }
	    }
	    return isFull;
	}
	
	public boolean checkForWin() {
	    return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
	}
	
	private boolean checkRowsForWin() {
	    for (int i = 0; i < 3; i++) {
	        if (checkRowCol(board[i][0], board[i][1], board[i][2]) == true) {
	            return true;
	        }
	    }
	    return false;
	}
	
	private boolean checkColumnsForWin() {
	    for (int i = 0; i < 3; i++) {
	        if (checkRowCol(board[0][i], board[1][i], board[2][i]) == true) {
	            return true;
	        }
	    }
	    return false;
	}
	
	private static boolean checkDiagonalsForWin() {
	    return ((checkRowCol(board[0][0], board[1][1], board[2][2]) == true) || (checkRowCol(board[0][2], board[1][1], board[2][0]) == true));
	}
	
	private static boolean checkRowCol(char c1, char c2, char c3) {
	    return ((c1 != '-') && (c1 == c2) && (c2 == c3));
	}
	
	private static void switchPlayers() {
	    if (currentPlayer == 'X') {
	        currentPlayer = 'O';
	    } else {
	        currentPlayer = 'X';
	    }
	}
	
	
	
	/*
	 * Board management
	 */
	
	private void printBoard() {
		System.out.println("Board: \n\n―――――――――――――");
		for (int i = 0 ; i < 3 ; i++) {
			System.out.print("| ");
			for (int j = 0 ; j < 3 ; j++) {
				System.out.println(board[i][j] + " | ");
			}
			System.out.print("\n\n");
			System.out.println("―――――――――――――");
		}
	}
	
	private void clearBoard() {
		for (int i = 0 ; i < 9 ; i++) {
			for (int j = 0 ; j < 9 ; j++) {
				board[i][j] = ' ';
			}
		}	
	}

}
