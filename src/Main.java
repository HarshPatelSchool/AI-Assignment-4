import java.io.File;
import java.io.IOException;

public class Main {
    // read in file name and generate board map for that
    // keep track of seconds - use nanoseconds instead of miliseconds
    // every part of the 3D array pick the biggest value for the output
    // X, Y, AND MOVE for output
    public static void main(String[] args) throws IOException {
        File inputFile = new File(args[0]);
        Double seconds = Double.parseDouble(args[1]);
        Double probability = Double.parseDouble(args[2]);
        Double constantRewards = Double.parseDouble(args[3]);

        // File boardTxt = new File("boards/sample.txt");
        Board board = new Board(inputFile);

        Search s = new Search(board, probability, constantRewards);
        s.beginSearch(seconds);

        double[][][] map = s.getqValues();
        boolean[][][] checked = s.getqUpdated();
        output(board, map, checked);
    }

    private static void output(Board b, double[][][] map, boolean[][][] checked) {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[0].length; col++) {
                if (b.getVal(row, col) != 0)
                    System.out.print("TERMINATE\t");
                else {
                    double maxNum = Integer.MIN_VALUE;
                    int k = 0;
                    for (int i = 0; i < map[row][col].length; i++) {
                        if (maxNum < map[row][col][i] && checked[row][col][i]) {
                            maxNum = map[row][col][i];
                            k = i;
                        }
                    }
                    if (k == 0) {
                        System.out.print("UP       \t");
                    }
                    if (k == 1) {
                        System.out.print("RIGHT    \t");
                    }
                    if (k == 2) {
                        System.out.print("DOWN     \t");
                    }
                    if (k == 3) {
                        System.out.print("LEFT     \t");
                    }
                }
            }
            System.out.println();
        }
    }
}

