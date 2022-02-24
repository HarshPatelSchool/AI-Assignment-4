public class Agent {
    private int row, column, prob;
    private Board b;

    public Agent(int row, int column, int prob, Board board){
        this.row = row;
        this.column = column;
        this.prob = prob;
        b = board;
    }

    public int calculateNewQ(int oldQ, Direction d){
        switch(d){
            case UP:

                break;
            case DOWN:
                break;
            case LEFT:
                break;
            case RIGHT:
                break;


        }
        return -1; //TODO
    }
}
