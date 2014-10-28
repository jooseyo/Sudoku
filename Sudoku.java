import java.util.Scanner;
import java.util.Arrays;

public class Sudoku {
    
    int[][] board; // 2x2 arrays for the sudoku board
    
    // add other instance variables
    
    /**
     * default constructor. creates a Sudoku with an initially empty board
     */
    public Sudoku() {
        board = new int[9][9];
    }
    
    /**
     * constructor that creates a Sudoku with an initial board that is a copy of
     * board
     */
    public Sudoku(int[][] board) {
        this();
        // this.board = board;
        // this.board = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) { 
            for (int j = 0; j < board[i].length; j++) {
                this.board[i][j] = board[i][j];
            }
        }
    }
    
    /**
     * Main method for Sudoku
     */
    public static void main (String[] args) {
        System.out.println("Welcome to Sudoku.");
        System.out.println("To begin, please insert a string (numbers) of the sudoku board.");
        String stringNums = "028007000016083070000020851137290000000730000000046307290070000000860140000300700";
        int[][] numArray = new int[9][9];
        for (int i = 0; i < numArray.length; i++) {
            for (int j = 0; j < numArray[i].length; j++) {
                int position = (i * numArray.length) + j;
                numArray[i][j] = Integer.parseInt("" + stringNums.charAt(position));
            }
        }
        Sudoku sudoku = new Sudoku(numArray);
        sudoku.solve();
        int[][] thisBoard = sudoku.board();
        for (int i = 0; i < thisBoard.length; i++) {
            for (int j = 0; j < thisBoard[i].length; j++) {
                System.out.printf("% 2d", thisBoard[i][j]);
            }
            System.out.println();
        }
    }
    
    /**
     * method to verify whether it is int or not
     * 
     * @param s
     *            the input
     * @return true if it is int else false
     */
    //public boolean isInteger(String s) {
    //    try {
    //        Integer.parseInt(s);
    //       return true;
    //    } catch (NumberFormatException e) {
    //        return false;
    //    }
    //}
    
    /**
     * method that returns a copy of the current state of the board
     */
    public int[][] board() {
        int[][] boardCopy = Arrays.copyOf(board, board.length);
        return boardCopy;
    }
    
    /**
     * method to figure out who the candidates are on the selected cell
     * 
     * @param the
     *            row and column of the selected cell
     * @return boolean array with the corresponding indexes of numbers 1-9. True
     *         if it is a candidate
     */
    public boolean[] candidates(int row, int column) {
        boolean[] canArray = new boolean[10];
        canArray[0] = false;
        for (int i = 1; i < 10; i++)
            canArray[i] = true;
        
        for (int i = 0; i < 9; i++) {
            int numberInCell = board[row][i];
            if (numberInCell != 0)
                canArray[numberInCell] = false;
        }
        
        for (int j = 0; j < 9; j++) {
            int numberInCell = board[j][column];
            if (numberInCell != 0)
                canArray[numberInCell] = false;
        }
        
        int rRow = rowOfRepresentative(row);
        int rColumn = columnOfRepresentative(column);
        for (int i = rRow; i < rRow + 3; i++) {
            for (int j = rColumn; j < rColumn + 3; j++) {
                int numberInCell = board[i][j];
                if (numberInCell != 0)
                    canArray[numberInCell] = false;
            }
        }
        return canArray;
    }
    
    /**
     * method1 to figure out which box the selected cell belongs to
     * 
     * @param row
     *            : the row of the selected cell
     * @return the row of the representative of the box that thetselected cell
     *         belongs to
     */
    public int rowOfRepresentative(int row) {
        int rowRepresentative = (row / 3) * 3;
        return rowRepresentative;
    }
    
    /**
     * method2 to figure out which box the selected cell belongs to
     * 
     * @param column
     *            : the column of the selected cell
     * @return the column of the representative of the box that thetselected
     *         cell belongs to
     */
    public int columnOfRepresentative(int column) {
        int columnRepresentative = (column / 3) * 3;
        return columnRepresentative;
    }
    
    /**
     * method to check the state of board
     * 
     * @return true if board is solved otherwise false
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
    
    /**
     * This method checks every cell on the field to determine if there are cells that can only have one possible value
     * and assigns them that value.
     * 
     * @return true if the board has changed
     *         false otherwise
     */
    public boolean nakedSingles() {
        boolean result = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0)
                    continue;
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
                    board[i][j] = index;
                    result = true;
                }
            }
        }
        System.out.println("Naked singles: " + result);
        return result;
    }
    
    /**
     * This method checks every cell to determine whether or not it is a hidden single by invoking the helper method.
     * If the cell is not a hidden single, its value does not change. 
     * However, if it is a hidden single, the value that makes it one will be assigned to the cell.
     * 
     * @return true if the board has changed
     *         false otherwise
     */
    public boolean hiddenSingles() {
        boolean changed = false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0)
                    continue;
                else {
                    int number = hiddenSinglesHelp(i, j);
                    if (number != -1) {
                        board[i][j] = number;
                        changed = true;
                    }
                }
            }
        }
        return changed;
    }
    
    /**
     * This is the helper method for hiddenSingles(), which checks if each individual cell is a hidden single or not.
     * This method compares the candidates of the cells in the same row, column, and box using XORs and AND logic.
     * 
     * @param row the row number of the possible hidden single
     * @param column the column number of the possible hidden single
     * 
     * @return the integer value that would be assigned to the hidden single or 0 if the cell is nota hidden single
     */
    public int hiddenSinglesHelp(int row, int column) {
        boolean[]  candidatesOfTheSelectedCell = candidates(row, column); // store the candidates of the selected cell
        
        for (int j = 0; j < board.length; j++) { // go throught the same row
            if (board[row][j] != 0)
                continue;
            boolean[] compare = candidates(row, j); // store the cell's candidates on the same row to compare
            for (int i = 1; i < compare.length; i++) { // to compare the candiates in selected and comparing cell
                if (candidatesOfTheSelectedCell[i]) {    // if it is candidate for selected cell, because we only need to compare the candidates
                    candidatesOfTheSelectedCell[i] = candidatesOfTheSelectedCell[i] ^ compare[i]; // compare and true if only candidates in selected is true;
                }
            }
        }

        for (int i = 0; i < board.length; i++) { // go throught the same column
            if (board[i][column] != 0)
                continue;
            boolean[] compare = candidates(i, column); // store the cell's candidates on the same row to compare
            for (int j = 1; j < compare.length; j++) { // to compare the candiates in selected and comparing cell
                if (candidatesOfTheSelectedCell[j]) {    // if it is candidate for selected cell, because we only need to compare the candidates
                    candidatesOfTheSelectedCell[j] = candidatesOfTheSelectedCell[j] ^ compare[j]; // compare and true if only candidates in selected is true;
                }
            }
        }

        int rRow = rowOfRepresentative(row);      // Finds the row of the representative cell
        int rColumn = columnOfRepresentative(column);
        for (int i = rRow; i < rRow + 3; i++) {
            for (int j = rColumn; j < rColumn + 3; j++) {
                if (board[i][j] != 0)
                    continue;
                boolean[] compare = candidates(i, j);
                for (int k = 1; k < compare.length; k++) { // to compare the candiates in selected and comparing cell
                    if (candidatesOfTheSelectedCell[k]) {    // if it is candidate for selected cell, because we only need to compare the candidates
                        candidatesOfTheSelectedCell[k] = candidatesOfTheSelectedCell[k] ^ compare[k]; // compare and true if only candidates in selected is true;
                    }
                }
            }
        }

        int count = 0;
        int index = -1;
        for (int i = 1; i < candidatesOfTheSelectedCell.length; i++) {
            if (candidatesOfTheSelectedCell[i]) {
                count++;
                index = i;
            }
        }
        if (count == 1)
            return index;
        else
            return -1;
    
    }
}
