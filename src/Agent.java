import java.util.Arrays;
import java.util.Random;

public class Agent {
    private int row, column;
    private double cost;
    private Board b;

    /**
     * Creates an Agent at a row and column in a board
     *
     * @param row    Starting row
     * @param column Starting column
     * @param board  Board to start on
     * @param cost   Cost per action
     */
    public Agent(int row, int column, Board board, double cost) {
        this.row = row;
        this.column = column;
        this.cost = cost;
        b = board;
    }

    /**
     * Calculates Q values of current state and taking an action
     *
     * @param oldQs Old Q values
     * @param a     Annealing value
     * @return Best Q Direction
     */
    public Direction bestAction(double[][][] oldQs, double a) {
        double up, left, down, right;
        /* Gets Q values from current state and making a move */
        up = getNewQDir(oldQs, row - 1, column, a);
        left = getNewQDir(oldQs, row, column - 1, a);
        down = getNewQDir(oldQs, row + 1, column, a);
        right = getNewQDir(oldQs, row, column + 1, a);

        /* Gets the best Q(s,a) value and direction action for that*/
        double[] actions = new double[]{up, right, down, left};
        int index = 0; //Index of best Q
        double max = Integer.MIN_VALUE; //Best Q
        for (int i = 0; i < actions.length; i++) {
            if (max < actions[i]) {
                max = actions[i];
                index = i;
            }
        }

        /* Returns direction of best Q(s,a) */
        switch (index) { //Only update on best Q, not the action that is taken
            case 0: //UP
                oldQs[row - 1][column][0] = up;
                return Direction.UP;
            case 1: //RIGHT
                oldQs[row][column + 1][0] = right;
                return Direction.RIGHT;
            case 2: //DOWN
                oldQs[row + 1][column][0] = down;
                return Direction.DOWN;
            case 3: //LEFT
                oldQs[row][column - 1][0] = left;
                return Direction.LEFT;
            default: //Default case. Mainly one here for Java to be happy
                return Direction.UP;
        }
    }

    /**
     * Calculates Q value based on action from state
     *
     * @param oldQs  Old Q values
     * @param row    Row after action
     * @param column Column after action
     * @param a      Annealing value
     * @return Q after action
     */
    private double getNewQDir(double[][][] oldQs, int row, int column, double a) {
        double prevQ, newQ;
        double y = 0.9; //Weight of how valuable next step after action is
        try {
            prevQ = oldQs[row][column][0];
            newQ = prevQ //Old Utility
                    + a * (b.getVal(row, column) + cost //Action Reward
                    + y * maxA(oldQs, row, column) - prevQ); //Potential Future reward
        } catch (Exception e) { //If out of bounds return smallest value instead so it is ignored
            newQ = Integer.MIN_VALUE;
        }
        return newQ;
    }

    /**
     * Gets best Q from surrounding actions
     *
     * @param oldQs  Old Q values
     * @param row    Row to check actions from
     * @param column Column to check actions from
     * @return Best Q from all possible actions
     */
    private double maxA(double[][][] oldQs, int row, int column) {
        double[] storage = new double[4];
        storage[0] = getQDir(oldQs, row - 1, column); //UP
        storage[1] = getQDir(oldQs, row, column - 1); //LEFT
        storage[2] = getQDir(oldQs, row + 1, column); //DOWN
        storage[3] = getQDir(oldQs, row, column + 1);//RIGHT

        Arrays.sort(storage); //Sorts from least to greatest
        return storage[3]; //Greatest Q value amongst the actions
    }

    /**
     * Gets Q value from row and column or smallest number if out of bounds
     *
     * @param oldQs  Old Qs
     * @param row    row to check
     * @param column column to check
     * @return Q value of row column on the board or smallest double if out of bounds
     */
    private double getQDir(double[][][] oldQs, int row, int column) {
        double r;
        try {
            r = oldQs[row][column][0]; //Get Q at that row and column
        } catch (Exception e) {  //If out of bounds return smallest value so it is ignored
            r = Integer.MIN_VALUE;
        }
        return r;
    }

    /**
     * Moves Agent in a direction with a probability to turn 90 degrees from that direction
     *
     * @param d    Desired direction
     * @param prob Chance to go in desired direction
     */
    public void move(Direction d, double prob) {
        int nrow = row, ncolumn = column;
        int dir = d.getValue();
        Random random = new Random();
        double r = random.nextDouble();

        if (r > prob) { //Turns randomly based on a specified probability chance
            if (random.nextBoolean()) //50% chance counterclockwise turn, 50% clockwise
                dir = (dir + 1) % 4; //Clockwise
            else
                dir = (dir - 1) % 4; //Counterclockwise
        }

        switch (dir) { //New row or column based on movement direction
            case 0: //UP
                nrow -= 1;
                break;
            case 1: //RIGHT
                ncolumn += 1;
                break;
            case 2: //DOWN
                nrow += 1;
                break;
            case 3: //LEFT
                ncolumn -= 1;
                break;
        }

        if (b.getVal(nrow, ncolumn) != Integer.MIN_VALUE) { //Only updates if new row and column are still in board size
            row = nrow;
            column = ncolumn;
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
