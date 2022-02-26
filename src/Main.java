import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File boardTxt = new File("src/boards/sample.txt");
        Board board = new Board(boardTxt);
        Search s = new Search(board, 1, 0.9, -0.05);
        s.beginSearch();
        System.out.println();
        double[][][] b = s.getqValues();
        for(int i = 0; i< b.length; i++){
            for(int j = 0; j < b[i].length; j++)
                System.out.print(b[i][j][0] + "\t\t");
            System.out.println();
        }

    }
}
