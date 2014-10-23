public class Sudoku {
    
    int[][] board;
    // add other instance variables
    
    /** default constructor. creates a Sudoku with an initially empty board
     */ 
    public Sudoku() {
        
    }
    
    /** constructor that creates a Sudoku with an initial board that is a copy of board
     */
    public Sudoku(int[][] board) {
        
    }
    
    /** method that returns a copy of the current state of the board
      * 
      * 
      */
    public int[][] board() {
        
    }
    
    /** method to figure out who the candidates are on the selected cell
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
    
    public int rowOfRepresentative (int row) {
        int rowRepresentative = ((row - 1) / 3) * 3;
        return rowRepresentative;
    }
    
    public int columnOfRepresentative (int column) {
        int columnRepresentative = ((column - 1) / 3) * 3;
        return columnRepresentative;
    }
    
    public boolean isSolved() {
        return false;
    }
    
    public void solve() {
        while (!isSolved() && (nakedSingles() || hiddenSingles()));
    }
    
    public boolean nakedSingles() {
        return true;
    }
    
    public boolean hiddenSingles() {
        return true;
    }
}
