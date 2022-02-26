public enum Direction {
    UP(0),
    RIGHT(1),
    DOWN(2),
    LEFT(3);
    private final int value;
    Direction(int i) {
        value = i;
    }
    public Direction changeDirL(Direction dir){
        return switch (dir) {
            case UP -> LEFT;
            case RIGHT -> UP;
            case DOWN -> RIGHT;
            case LEFT -> DOWN;
        };
    }
    public Direction changeDirR(Direction dir){
        return switch (dir) {
            case UP -> RIGHT;
            case RIGHT -> DOWN;
            case DOWN -> LEFT;
            case LEFT -> UP;
        };
    }
}
