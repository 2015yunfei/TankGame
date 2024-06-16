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
    int direct = 0; //子弹方向
    int speed = 14; //子弹的速度
    boolean isLive = true; //子弹是否还存活

    //构造器
    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public Shot(int direct, Tank tank) {
        this.direct = direct;
        switch (direct) {
            //得到Hero对象方向
            case 0 -> // 向上
            {
                this.x = tank.getX() + 20;
                this.y = tank.getY();
            }
            case 1 -> // 向右
            {
                this.x = tank.getX() + 60;
                this.y = tank.getY() + 20;
            }
            case 2 -> // 向下
            {
                this.x = tank.getX() + 20;
                this.y = tank.getY() + 60;
            }
            case 3 -> // 向左
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
                case 0://上
                    y -= speed;
                    break;
                case 1://右
                    x += speed;
                    break;
                case 2://下
                    y += speed;
                    break;
                case 3://左
                    x -= speed;
                    break;
            }

            // System.out.println("子弹 x=" + x + " y=" + y);

            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isLive)) {
                System.out.println("子弹线程退出");
                isLive = false;
                break;
            }
        }
    }
}
