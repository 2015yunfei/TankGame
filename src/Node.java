/**
 * @author ：2015yunfei
 * @date ：Created in 2024/6/12 15:55
 */
public class Node {
    private int x;
    private int y;
    private Directions direct;

    public Node(int x, int y, String direct) throws IllegalStateException {
        this.x = x;
        this.y = y;
        this.direct = switch (direct){
            case "UP"->Directions.UP;
            case "DOWN"->Directions.DOWN;
            case "RIGHT"->Directions.RIGHT;
            case "LEFT"->Directions.LEFT;
            default -> throw new IllegalStateException("Unexpected value: " + direct);
        };
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Directions getDirect() {
        return direct;
    }

    public void setDirect(Directions direct) {
        this.direct = direct;
    }
}
