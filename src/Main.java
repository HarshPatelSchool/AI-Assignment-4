import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        File boardTxt = new File("src/boards/sample.txt");
        Board board = new Board(boardTxt);
        Random rng = new Random();
        int[][] b = board.getBoard();
        for(int i = 0; i< b.length; i++){
            for(int j = 0; j < b[i].length; j++)
                System.out.print(b[i][j] + "\t");
            System.out.println();
        }
        Search test = new Search(board);
        for(int i = 0; i < 3; i++) {
            System.out.println(test.expBoard());
        }
    }
}
