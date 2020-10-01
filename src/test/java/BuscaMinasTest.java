import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.IOException;
import java.io.InputStream;

public class BuscaMinasTest {
    @Test
    public void test_check_win0() throws IOException {
        int ancho = 3, alto = 3;
        int [][]board = generate_test_board (ancho, alto);
        generic (board, ancho, alto, true);
    }
    @Test
    public void test_check_win1() throws IOException {
        int ancho = 4, alto = 6;
        int [][]board = generate_test_board (ancho, alto);
        board[2][1] = -1;
        board[3][3] = -1;
        generic (board, ancho, alto, true);
    }
    @Test
    public void test_check_win2() throws IOException {
        int ancho = 2, alto = 2;
        int [][]board = generate_test_board (ancho, alto);
        board[1][1] = -1;
        board[0][0] = 0;
        generic (board, ancho, alto, false);
    }

    private void generic (int [][]test_board, int ancho, int alto, boolean expected_result) throws IOException {
        boolean result = BuscaMinas.check_if_win (test_board, ancho, alto);
        Assert.assertEquals(expected_result, result);
    }

    private int[][] generate_test_board (int ancho, int alto) {
        int [][]board = new int[alto][ancho];
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                board[i][j] = 2;
            }
        }
        return board;
    }

    private static final int MINE = -1;
    private static final int SQUARE = 0;
    private static final int OPEN_SQUARE = 2;

    int [][] boardTest1 ={{MINE, SQUARE, OPEN_SQUARE},
            {MINE, MINE, SQUARE},
            {OPEN_SQUARE, SQUARE, MINE}};

    @Test
    public void testSelectSpaceMINE(){
        Assert.assertTrue(BuscaMinas.select_space(0, 0, boardTest1));
        Assert.assertTrue(BuscaMinas.select_space(1, 0, boardTest1));
        Assert.assertTrue(BuscaMinas.select_space(1, 1, boardTest1));
    }
    @Test
    public void testSelectSpaceSquare(){
        Assert.assertFalse(BuscaMinas.select_space(0, 1, boardTest1));
        Assert.assertEquals(boardTest1[0][1], OPEN_SQUARE);
        Assert.assertFalse(BuscaMinas.select_space(1, 2, boardTest1));
        Assert.assertEquals(boardTest1[1][2], OPEN_SQUARE);
        Assert.assertFalse(BuscaMinas.select_space(2, 1, boardTest1));
        Assert.assertEquals(boardTest1[2][1], OPEN_SQUARE);
    }
    @Test
    public void testSelectSpaceOPEN_SQUARE(){
        Assert.assertFalse(BuscaMinas.select_space(0, 2, boardTest1));
        Assert.assertEquals(boardTest1[0][2], OPEN_SQUARE);
        Assert.assertFalse(BuscaMinas.select_space(2, 0, boardTest1));
        Assert.assertEquals(boardTest1[2][0], OPEN_SQUARE);
    }
    int [][] boardTest2 ={{SQUARE, MINE, SQUARE,MINE},
            {OPEN_SQUARE,MINE,SQUARE,SQUARE},
            {OPEN_SQUARE, SQUARE, MINE, SQUARE},
            {SQUARE, MINE, MINE, OPEN_SQUARE}};
    @Test
    public void testSelectSpaceGAME(){
        Assert.assertFalse(BuscaMinas.select_space(0, 0, boardTest2));
        Assert.assertEquals(boardTest2[0][0], OPEN_SQUARE);

        Assert.assertFalse(BuscaMinas.select_space(0, 0, boardTest2));
        Assert.assertEquals(boardTest2[0][0], OPEN_SQUARE);

        Assert.assertTrue(BuscaMinas.select_space(0,1,boardTest2));

        Assert.assertFalse(BuscaMinas.select_space(0, 2, boardTest2));
        Assert.assertEquals(boardTest2[0][2], OPEN_SQUARE);

        Assert.assertFalse(BuscaMinas.select_space(1, 0, boardTest2));
        Assert.assertEquals(boardTest2[1][0], OPEN_SQUARE);

        Assert.assertFalse(BuscaMinas.select_space(1, 0, boardTest2));
        Assert.assertEquals(boardTest2[1][0], OPEN_SQUARE);

        Assert.assertFalse(BuscaMinas.select_space(1, 2, boardTest2));
        Assert.assertEquals(boardTest2[1][2], OPEN_SQUARE);

        Assert.assertFalse(BuscaMinas.select_space(1, 2, boardTest2));
        Assert.assertEquals(boardTest2[1][2], OPEN_SQUARE);

        Assert.assertFalse(BuscaMinas.select_space(2, 1, boardTest2));
        Assert.assertEquals(boardTest2[2][1], OPEN_SQUARE);

        Assert.assertFalse(BuscaMinas.select_space(1, 2, boardTest2));
        Assert.assertEquals(boardTest2[1][2], OPEN_SQUARE);

        Assert.assertTrue(BuscaMinas.select_space(3,1,boardTest2));

        Assert.assertFalse(BuscaMinas.select_space(3, 3, boardTest2));
        Assert.assertEquals(boardTest2[3][3], OPEN_SQUARE);

    }
}
