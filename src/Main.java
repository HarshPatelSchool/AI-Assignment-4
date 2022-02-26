import java.io.File;
import java.io.IOException;

public class Main {
    // read in file name and generate board map for that 
    // keep track of seconds - use nanoseconds instead of miliseconds
    // every part of the 3D array pick the biggest value for the output 
    // X, Y, AND MOVE for output 
    public static void main(String[] args) throws IOException {
        File inputFile = new File(args[0]);
        Double seconds = Double.parseDouble(args[1]);;
        Double probability = Double.parseDouble(args[2]);
        Double constantRewards = Double.parseDouble(args[3]);

        // File boardTxt = new File("boards/sample.txt");
        Board board = new Board(inputFile);
        int[][] b = board.getBoard();

        int[][][] map = new int[b.length][b[0].length][4];

        Double nanoseconds = seconds * 1000000000;

        long start = System.nanoTime();
        // run Q function 
        long finish = System.nanoTime();

        //printing output:
        for(int i = 0; i< map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                int maxNum=-1;
                int k = 0;
                for (int x : map[i][j]) {
                    if (x > maxNum){
                        maxNum = x;
                        k = k+1;
                    }
                }
                System.out.println(k+" "+ maxNum);
                if (k == 0){
                    System.out.print("UP ");
                }
                if (k == 1){
                    System.out.print("DOWN ");
                }
                if (k==2){
                    System.out.print("RIGHT ");
                }
                if (k==3){
                    System.out.print("LEFT ");
                }
                System.out.println("X:"+i+" Y:"+j+ " max value of move:"+maxNum);
            }
            System.out.println();
        }

        System.out.println("input values " + seconds + " "+ probability+ " "+constantRewards);
    }

}

