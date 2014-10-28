import junit.framework.TestCase;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class SudokuTests extends TestCase {
    Sudoku s1;
    Sudoku s2;
    Sudoku s3;
    Sudoku s4;
    Sudoku s5;
    Sudoku s6;
    
    String testNums1 = "002193000000007000700040019803000600045000230007000504370080006000600000000534100";
    String testNums2 = "028007000016083070000020851137290000000730000000046307290070000000860140000300700";
    String testNums3 = "534678912672195348198342567859761423426853791713924856961537284287419635345286179";

    String testNums4 = "000000000672195348198342567859761423426853791713924856961537284287419635345286179";
    String testNums5 = "000000000000000000000000000000000000000000000000000000000000000000000000000000000";
    String testNums6 = "100000000000001000000000000000000010000000000000000000000000000000000000000000001";
    
    protected void setUp() throws Exception {
        int[][] testArray1 = new int[9][9];
        for (int i = 0; i < testArray1.length; i++) {
            for (int j = 0; j < testArray1[i].length; j++) {
                int position = (i * testArray1.length) + j;
                testArray1[i][j] = Integer.parseInt("" + testNums1.charAt(position));
            }
        }
        s1 = new Sudoku(testArray1);

        int [][] testArray2 = new int[9][9];
        for (int i = 0; i < testArray2.length; i++) {
            for (int j = 0; j < testArray2[i].length; j++) {
                int position = (i * testArray2.length) + j;
                testArray2[i][j] = Integer.parseInt("" + testNums2.charAt(position));
            }
        }
        s2 = new Sudoku(testArray2);

        int [][] testArray3 = new int[9][9];
        for (int i = 0; i < testArray3.length; i++) {
            for (int j = 0; j < testArray3[i].length; j++) {
                int position = (i * testArray3.length) + j;
                testArray3[i][j] = Integer.parseInt("" + testNums3.charAt(position));
            }
        }
        s3 = new Sudoku(testArray3);

        int [][] testArray4 = new int[9][9];
        for (int i = 0; i < testArray4.length; i++) {
            for (int j = 0; j < testArray4[i].length; j++) {
                int position = (i * testArray4.length) + j;
                testArray4[i][j] = Integer.parseInt("" + testNums4.charAt(position));
            }
        }
        s4 = new Sudoku(testArray4);

        int [][] testArray5 = new int[9][9];
        for (int i = 0; i < testArray5.length; i++) {
            for (int j = 0; j < testArray5[i].length; j++) {
                int position = (i * testArray5.length) + j;
                testArray5[i][j] = Integer.parseInt("" + testNums5.charAt(position));
            }
        }
        s5 = new Sudoku(testArray5);

        int [][] testArray6 = new int[9][9];
        for (int i = 0; i < testArray6.length; i++) {
            for (int j = 0; j < testArray6[i].length; j++) {
                int position = (i * testArray6.length) + j;
                testArray6[i][j] = Integer.parseInt("" + testNums6.charAt(position));
            }
        }
        s6 = new Sudoku(testArray6);

    }
    
    /**
     * A test method.
     * (Replace "X" with a name describing the test.  You may write as
     * many "testSomething" methods in this class as you wish, and each
     * one will be called when running JUnit over this class.)
     */
    
    public void testBoard() {
        for (int i = 0; i < s1.board.length; i++) {
            for (int j = 0; j < s1.board[i].length; j++) {
                assertEquals(s1.board[i][j], s1.board()[i][j]);
            }
        }
    }
    
    public void testCandidates() {
        boolean[] testArray = {false, false, false, false, true, true, true, false, false, false};
        for (int i = 0; i < 4; i++) {
            assertEquals(s1.candidates(0, 0)[i], testArray[i]);
        }
    }

    public void testRowOfRepresentative() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                assertEquals(s1.rowOfRepresentative(0), 0);
        }
    }

    public void testColumnOfRepresentative() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                assertEquals(s1.columnOfRepresentative(0), 0);
        }
    }

    public void testIsSolved() {
        assertEquals(s1.isSolved(), false);
        assertEquals(s2.isSolved(), false);
        assertEquals(s3.isSolved(), true);
    }

    public void testNakedSingles() {
        assertEquals(s4.nakedSingles(), true);
        assertEquals(s5.nakedSingles(), false);
    }

    public void testHiddenSingles() {
        assertEquals(s1.hiddenSingles(), true);
        assertEquals(s2.hiddenSingles(), true);
        assertEquals(s3.hiddenSingles(), false);
        assertEquals(s5.hiddenSingles(), false);
    }

    public void testHiddenSinglesHelp() {
        assertEquals(s1.hiddenSinglesHelp(5, 3), 3);
        assertEquals(s2.hiddenSinglesHelp(2, 3), 6);
        assertEquals(s1.hiddenSinglesHelp(0, 0), -1);
        assertEquals(s6.hiddenSinglesHelp(2, 6), 1);
    }


}
