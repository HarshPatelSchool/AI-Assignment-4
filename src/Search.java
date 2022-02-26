import java.util.Random;

public class Search {
    double[][][] qValues;
    Board b;
    int row, col;
    double prob, cost;
    public Search(Board b, int time, double prob, double cost){
        this.b = b;
        this.prob = prob;
        int[][] vals = b.getBoard();
        this.cost = cost;
        row = vals.length;
        col = vals[0].length;
        qValues = new double[row][col][1]; //Stores Q value at coordinate
    }

    public void beginSearch(){
        int nrow, ncol;
        Random r = new Random();
        long start = System.currentTimeMillis();
        //while(System.currentTimeMillis() - start < time * 1000){
        do{
            nrow = r.nextInt(row);
            ncol = r.nextInt(col);
        } while(b.getVal(nrow, ncol) != 0);
        Agent agent = new Agent(nrow, ncol, b, cost);
        nextMove(agent, 0.9);
        //}
    }
    private void nextMove(Agent agent, double a){
        System.out.print("Row: "+agent.getRow() +"\tColumn: "+agent.getColumn());
        if(b.getVal(agent.getRow(), agent.getColumn()) == 0){
            Direction d = agent.bestAction(qValues, a);
            System.out.println("\tBest Move: " + d.name());
            agent.move(d, prob);
            nextMove(agent, a);
        }
    }

    public double[][][] getqValues() {
        return qValues;
    }
}