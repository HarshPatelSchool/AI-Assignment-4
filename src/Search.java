import java.util.Random;

public class Search {
    double[][][] qValues;
    Board b;
    int row, col;
    double prob, cost;

    public Search(Board b, double prob, double cost) {
        this.b = b;
        this.prob = prob;
        this.cost = cost;
        int[][] vals = b.getBoard();
        row = vals.length;
        col = vals[0].length;
        qValues = new double[row][col][4]; //Stores Q value at coordinate
    }

    public void beginSearch(double time) {
        int nrow, ncol;
        Random r = new Random();
        long start = System.currentTimeMillis();
        double annealing;
        while (System.currentTimeMillis() - start < time * 1000) {
            //for(int i = 0; i< 100; i++){
            annealing = 0.9;
            do {
                nrow = r.nextInt(row);
                ncol = r.nextInt(col);
            } while (b.getVal(nrow, ncol) != 0);
            Agent agent = new Agent(nrow, ncol, b, cost);
            while (b.getVal(agent.getRow(), agent.getColumn()) == 0) {
                Direction d = agent.bestAction(qValues, annealing);
                agent.move(d, prob);
            }
        }
    }


    public double[][][] getqValues() {
        return qValues;
    }
}