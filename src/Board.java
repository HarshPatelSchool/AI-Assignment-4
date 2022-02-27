import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Board {
    private File file; //Text file that contains the board
    private int[][] board; //Array containing the terrain complexity

    /**
     * Creates a new Board object from a text file
     *
     * @param file Source file of the board
     * @throws IOException
     */
    public Board(File file) throws IOException {
        this.file = file;
        board = makeBoard();
    }

    /**
     * Creates an array representing the board
     *
     * @return char array with the terrain complexity, start, and goal
     * @throws IOException
     */
    private int[][] makeBoard() throws IOException {
        List boardS = Files.readAllLines(file.toPath()); //Reads every line of the file and adds them to a list
        int[][] board = new int[0][0]; //Initializes the board
        int[] row; //Initializes a row of the board

        /*Adds the row values to the board array*/
        for (int i = 0; i < boardS.size(); i++) {
            String line = (String) boardS.get(i);
            String[] strings = line.split("\t");
            row = new int[strings.length];
            for (int j = 0; j < row.length; j++)
                row[j] = Integer.parseInt(strings[j]);
            if (i == 0) //Sets the size of the board after the first row
                board = new int[boardS.size()][row.length];
            board[i] = row; //Adds the row to the board
        }
        return board; //Returns the completed board
    }

    public int getVal(int row, int column) {
        try {
            return board[row][column]; //Gets val at coordinate.
        } catch (Exception e) {
            return Integer.MIN_VALUE;
        }
    }

    /**
     * Returns the board
     *
     * @return int[][] that contains the terrain complexity of the board
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * Returns the file of the board
     *
     * @return the .txt file
     */
    public File getFile() {
        return file;
    }

}
