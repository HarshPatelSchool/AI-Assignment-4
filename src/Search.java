public class Search {
    double[][][] qValues;
    public Search(Board b){
        int[][] vals = b.getBoard();
        qValues = new double[vals.length][vals[0].length][1]; //Stores Q value at coordinate
    }
}