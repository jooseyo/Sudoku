import java.util.Scanner;

public class Sudoku {
    
    int[][] board; // 2x2 arrays for the sudoku board
    // add other instance variables
    
    /** 
     * default constructor. creates a Sudoku with an initially empty board
     */ 
    public Sudoku() {
        
    }
    
    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /** 
     * constructor that creates a Sudoku with an initial board that is a copy of board
     */
    public Sudoku(int[][] board) {
        
    }
    
    /** 
     * method that returns a copy of the current state of the board
     */
    public int[][] board() {
        //
    }
    
    /** 
     * method to figure out who the candidates are on the selected cell
     * 
     * @param the row and column of the selected cell
     * @return boolean array with the corresponding indexes of numbers 1-9. True if it is a candidate
     */
    public boolean[] candidates(int row, int column) {
        boolean[] canArray = new boolean[9];
        for (int i = 0; i < 9; i++)
            canArray[i] = !canArray[i];
        
        for (int i = 0; i < 9; i++) {
            int numberInCell = board[row - 1][i];
            if (numberInCell != 0)
                canArray[numberInCell - 1] = false; // is -1 correct or not
        }
        
        for (int j = 0; j < 9; j++) {
            int numberInCell = board[j][column - 1];
            if (numberInCell != 0)
                canArray[numberInCell - 1] = false;
        }
        
        int rRow = rowOfRepresentative(row);
        int rColumn = columnOfRepresentative(column);
        for (int i = rRow; i < rRow + 3; i++) {
            for (int j = rColumn; j < rColumn + 3; j++) {
                int numberInCell = board[i][j];
                if (numberInCell != 0)
                    canArray[numberInCell - 1] = false;
            }
        }
        return canArray;
    }
    
    /**
     * method1 to figure out which box the selected cell belongs to
     * 
     * @param row: the row of the selected cell
     * @return the row of the representative of the box that thetselected cell belongs to
     */
    public int rowOfRepresentative (int row) {
        int rowRepresentative = ((row - 1) / 3) * 3;
        return rowRepresentative;
    }
    
     /**
      * method2 to figure out which box the selected cell belongs to
      * 
      * @param column: the column of the selected cell
      * @return the column of the representative of the box that thetselected cell belongs to
      */
    public int columnOfRepresentative (int column) {
        int columnRepresentative = ((column - 1) / 3) * 3;
        return columnRepresentative;
    }
    
    /**
     * method to check the state of board
     * 
     * @return true if board is solved
     *  otherwise false
     */
    public boolean isSolved() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0)
                    return false;
            }
        }
        return true;
    }
    
    public void solve() {
        while (!isSolved() && (nakedSingles() || hiddenSingles()));
    }
    
    public boolean nakedSingles() {
        boolean result = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boolean[] candidates = candidates(i, j);
                int count = 0;
                int index = -1;
                for (int k = 0; k < candidates.length; k++) {
                    if (candidates[k]) {
                        count++;
                        index = k;
                    }
                }
                if (count == 1) {
                    board[i][j] = index + 1;
                    result = true;
                }
            }
        }
        return result;
    }
    
    public boolean hiddenSingles() {
        return true;
    }
}
