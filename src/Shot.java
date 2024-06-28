import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ：2015yunfei
 * @date ：Created in 2024/6/11 15:18
 */
public class Shot implements Runnable {
    Logger logger = Logger.getLogger("Shot");  // 类名或应用名作为日志记录器的名称
    int x; //子弹x坐标
    int y; //子弹y坐标
    Directions direct; //子弹方向
    int speed = 14; //子弹的速度
    boolean isLive = true; //子弹是否还存活

    //构造器
    public Shot(int x, int y, Directions direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public Shot(Directions direct, Tank tank) {
        this.direct = direct;
        switch (direct) {
            //得到Hero对象方向
            case UP -> // 向上
            {
                this.x = tank.getX() + 20;
                this.y = tank.getY();
            }
            case RIGHT -> // 向右
            {
                this.x = tank.getX() + 60;
                this.y = tank.getY() + 20;
            }
            case DOWN -> // 向下
            {
                this.x = tank.getX() + 20;
                this.y = tank.getY() + 60;
            }
            case LEFT -> // 向左
            {
                this.x = tank.getX();
                this.y = tank.getY() + 20;
            }
        }
    }

    @Override
    public void run() {//射击
        while (true) {
            //休眠 50毫秒
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                // 使用logger来记录错误信息
                logger.log(Level.SEVERE, "An interrupted exception occurred", e);
            }

            switch (direct) {
                case UP://上
                    y -= speed;
                    break;
                case RIGHT://右
                    x += speed;
                    break;
                case DOWN://下
                    y += speed;
                    break;
                case LEFT://左
                    x -= speed;
                    break;
            }

            // System.out.println("子弹 x=" + x + " y=" + y);

            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isLive)) {
                String threadName = Thread.currentThread().getName();
                System.out.println("子弹线程退出" + "_当前线程名称：" + threadName);
                isLive = false;
                break;
            }
        }
    }
}
