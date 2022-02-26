import java.util.Random;

public class Search {
    int score = 0;
    double[][][] qValues;
    int x = 0;
    int y = 0;
    int [][] vals;
    int constantReward = 0;
    Random rng = new Random();
    Board brd;
    public Search(Board b){
        brd = b;
        vals = b.getBoard();
        qValues = new double[vals.length][vals[0].length][4]; //Stores Q value at coordinate
    }
    public int expBoard (){
        x = rng.nextInt(vals[0].length);
        y = rng.nextInt(vals.length);
        Agent A = new Agent(y, x, brd);
        int moveCount = 0;
        while(vals[y][x] == 0){
            double[] arr = A.calculateQ(qValues,1);
            double max = 0;
            int dir = 0;
            for(int i = 0; i < 4; i++){
                qValues[y][x][i] = arr[i];
                if(qValues[y][x][i] > max){
                    dir = i;
                    max = qValues[y][x][i];
                }
            }
            switch(dir){
                case 0:
                    A.move(Direction.UP, 0.9);
                    y = A.row;
                    x = A.column;
                    break;
                case 1:
                    A.move(Direction.RIGHT, 0.9);
                    y = A.row;
                    x = A.column;
                    break;
                case 2:
                    A.move(Direction.DOWN, 0.9);
                    y = A.row;
                    x = A.column;
                    break;
                case 3:
                    A.move(Direction.LEFT, 0.9);
                    y = A.row;
                    x = A.column;
                    break;
            }
            if(checkValidMove(y,x)){
                A.revertmove();
                y = A.row;
                x = A.column;
            }
            System.out.println(y + ", " + x);
            moveCount++;
        }
        return vals[y][x];
    }
    public boolean checkValidMove(int fRow, int fCol){
        if(fRow >= vals.length || fRow < 0){
            return false;
        }
        if(fCol >= vals[0].length || fCol < 0){
            return false;
        }
        return true;
    }
}