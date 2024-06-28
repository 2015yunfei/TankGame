import java.util.Random;
import java.util.Vector;

public class Tank {
    private int x;
    private int y;

    private Directions direct;
    private int speed = 8;
    boolean isLive = true;

    //可以发射多颗子弹
    Vector<Shot> shots = new Vector<>();

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    //上右下左移动方法
    public void moveUp() {
        y -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public void moveDown() {
        y += speed;
    }

    public void moveLeft() {
        x -= speed;
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
        getRandomDirection();
    }

    public Tank() {
        // 创建 Random 对象用于生成随机数
        Random random = new Random();

        this.x = random.nextInt(1001); // 1001 是因为 nextInt(n) 会生成 0 到 n-1 之间的随机数
        this.y = random.nextInt(751); // 751 同理
        getRandomDirection();
    }

    public void getRandomDirection(){
        // 创建Random对象
        Random random = new Random();
        // 生成随机索引
        int randomIndex = random.nextInt(Directions.values().length);
        // 使用随机索引获取枚举值
        this.direct = Directions.values()[randomIndex];
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