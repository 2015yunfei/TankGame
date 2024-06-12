import java.util.Vector;

/**
 * @author ：2015yunfei
 * @date ：Created in 2024/6/9 12:57
 * 敌人的坦克
 */
public class EnemyTank extends Tank implements Runnable {

    public EnemyTank(int x, int y) {
        super(x, y);
        this.setSpeed(4);
    }

    @Override
    public void run() {
        while (true) {

            //这里我们判断如果shots size() =0, 创建一颗子弹，放入到
            //shots集合，并启动
            if (isLive && shots.size() < 4) {
                Shot s = switch (getDirect()) {
                    case 0 -> new Shot(getX() + 20, getY(), 0);
                    case 1 -> new Shot(getX() + 60, getY() + 20, 1);
                    case 2 -> //向下
                            new Shot(getX() + 20, getY() + 60, 2);
                    case 3 ->//向左
                            new Shot(getX(), getY() + 20, 3);
                    default -> null;
                    //判断坦克的方向，创建对应的子弹
                };
                shots.add(s);
                //启动
                new Thread(s).start();

            }


            //根据坦克的方向来继续激动
            switch (getDirect()) {
                case 0:  //向上
                    //让坦克保持一个方向，走30步
                    for (int i = 0; i < 30; i++) {
                        if (getY() > 0) {
                            moveUp();
                        }
                        //休眠50毫秒
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:  //向右
                    for (int i = 0; i < 30; i++) {
                        if (getX() + 60 < 1000) {
                            moveRight();
                        }
                        //休眠50毫秒
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:  //向下
                    for (int i = 0; i < 30; i++) {
                        if (getY() + 60 < 750) {
                            moveDown();
                        }
                        //休眠50毫秒
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:  //向左
                    for (int i = 0; i < 30; i++) {
                        if (getX() > 0) {
                            moveLeft();
                        }
                        //休眠50毫秒
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }


            //然后随机的改变坦克方向 0-3
            setDirect((int) (Math.random() * 4));
            //听老韩说，写并发程序，一定要考虑清楚，该线程什么时候结束
            if (!isLive) {
                break; //退出线程.
            }
        }
    }
}
