public enum Direction {
    UP(0),
    RIGHT(1),
    DOWN(2),
    LEFT(3);
    private final int value;

    Direction(int i) {
        value = i;
    }

    public int getValue() {
        return value;
    }
}
