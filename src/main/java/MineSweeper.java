import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MineSweeper {
    int rowCount;
    int colCount;
    String[][] mineFields;
    String[][] board;

    public MineSweeper(int row, int col) {
        rowCount = row;
        colCount = col;
        mineFields = new String[row][col];
        board = new String[row][col];
        for (String[] r : mineFields)
            Arrays.fill(r, "-");
        for (String[] r : board)
            Arrays.fill(r, "-");

        mineInclude();
        //System.out.println(Arrays.deepToString(mineFields));
    }

    int[] numberToRowCol(int number) {
        int[] rowCol = {number / colCount, number % colCount};
        return rowCol;
    }

    public void mineInclude() {
        Random rnd = new Random();
        int mineSize = rowCount * colCount / 4;
        int mineCount = mineSize;
        while (mineCount > 0) {
            int rand = rnd.nextInt(rowCount * colCount);
            int[] xy = numberToRowCol(rand);
            if (mineFields[xy[0]][xy[1]] != "*") {
                mineFields[xy[0]][xy[1]] = "*";
                mineCount--;
            }
        }
    }


    public void printBoard(String title, String[][] arr) {
        System.out.println(title + ":");
        System.out.println(String.format("%" + colCount * 2 + "s", "").replaceAll(" ", "="));
        for (String[] r : arr) {
            for (String c : r)
                System.out.print(c + " ");
            System.out.println();
        }


    }

    public void controlBoard(int[] xy) {
        int r = xy[0];
        int c = xy[1];
        int sum = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if ((r + i < 0 || c + j < 0 || r + i >= rowCount || c + j >= colCount) || (i == 0 && j == 0)) continue;
                if (mineFields[r + i][c + j] == "*") sum++;
            }
        }

        board[r][c] = String.valueOf(sum);

    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        printBoard("Mine Fields", mineFields);
        printBoard("Board", board);

        while (1 > 0) {
            System.out.print("Parsel No Girin( 0 ile " + (colCount * rowCount - 1) + " arasında): ");
            int[] xy = numberToRowCol(sc.nextInt()); // ararlık kontrol et

            if (mineFields[xy[0]][xy[1]] == "*") {
                System.out.println("GAME OVER!!!!!");
                break;
            }
            controlBoard(xy);
            printBoard("Board", board);

        }
    }
}
