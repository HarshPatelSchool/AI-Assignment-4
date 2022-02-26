import java.util.Arrays;
import java.util.Random;

public class Agent {
    public int row, column;
    private int prevRow, prevCol;
    private Board b;

    /**
     * Creates an Agent at a row and column in a board
     * @param row Starting row
     * @param column Starting column
     * @param board Board to start on
     */
    public Agent(int row, int column, Board board){
        this.row = row;
        this.column = column;
        b = board;
    }

    /**
     * Calculates Q values of current state and taking an action
     * @param oldQs Old Q values
     * @param a Annealing value
     * @return Array of Q values from taking actions in order of {UP, RIGHT, DOWN, LEFT}
     */
    public double[] calculateQ(double[][][] oldQs, int a){
        double up,left,down,right, prev;
        up = getNewQDir(oldQs, row - 1, column, a);
        left = getNewQDir(oldQs, row, column-1,a);
        down = getNewQDir(oldQs, row + 1, column, a);
        right = getNewQDir(oldQs, row, column + 1, a);

        return new double[]{up, right, down, left};
    }

    private double getNewQDir(double[][][] oldQs, int row, int column, int a){
        double prevQ, newQ;
        double y = 0.9;
        try{
            prevQ = oldQs[row][column][0];
            newQ = prevQ
                    + a * (b.getVal(row, column)
                    + y * maxA(oldQs, row, column) - prevQ);
        }catch (Exception e){ //If out of bounds
            newQ = Double.MIN_VALUE;
        }
        return newQ;
    }

    private double maxA(double[][][] oldQs, int row, int column){
        double[] storage = new double[4];
        storage[0] = getQDir(oldQs, row - 1, column);
        storage[1] = getQDir(oldQs, row, column - 1);
        storage[2] = getQDir(oldQs, row + 1, column);
        storage[3] = getQDir(oldQs, row, column + 1);

        Arrays.sort(storage); //Sorts from least to greatest
        return storage[3]; //
    }

    private double getQDir(double[][][] oldQs, int row, int column){
        double r;
        try{
            r= oldQs[row][column][0];
        }catch (Exception e){  //If out of bounds
            r = Double.MIN_VALUE;
        }
        return r;
    }
    private Direction updateDir (Direction d, double prob){
        Random rng = new Random();
        int changeDir = 0;
        double chance = 1-prob;
        double ran = Math.round(rng.nextDouble() * 100.0) / 100.0;
        double tr = prob + chance/2;
        if(ran < prob){
        }
        else if (ran < tr){
            changeDir = 1;
        }
        else{
            changeDir = 2;
        }
        switch (changeDir){
            case 0:
                break;
            case 1:
                d = d.changeDirL(d);
                break;
            case 2:
                d = d.changeDirR(d);
                break;
        }
        return d;
    }
    public void move(Direction d, double prob){
        d = updateDir(d,prob);
        switch (d){
            case UP:
                System.out.println("Going up");
                prevCol = column;
                column++;
                break;
            case RIGHT:
                System.out.println("Going Right");
                prevRow = row;
                row++;
                break;
            case DOWN:
                System.out.println("Going Down");
                prevCol = column;
                column--;
                break;
            case LEFT:
                System.out.println("Going Left");
                prevRow = row;
                row--;
                break;
        }
    }
    public void revertmove (){
        row = prevRow;
        column = prevCol;
    }
}
