import java.util.Vector;

/**
 * @author ：2015yunfei
 * @date ：Created in 2024/6/9 10:15
 * 主角的坦克
 */
public class Hero extends Tank {
    //定义一个Shot对象, 表示一个射击(线程)
//    Shot shot = null;

    public Hero(int x, int y) {
        super(x, y);
    }

    //射击
    public void shotEnemyTank() {

        //发多颗子弹怎么办, 控制在我们的面板上，最多只有5颗
        if (shots.size() == 5) {
            return;
        }

        Shot s = switch (getDirect()) {
            //得到Hero对象方向
            case 0 -> //向上
                    new Shot(getX() + 20, getY(), 0);
            case 1 -> //向右
                    new Shot(getX() + 60, getY() + 20, 1);
            case 2 -> //向下
                    new Shot(getX() + 20, getY() + 60, 2);
            case 3 -> //向左
                    new Shot(getX(), getY() + 20, 3);
            default -> null;
            //创建 Shot 对象, 根据当前Hero对象的位置和方向来创建Shot
        };

        //把新创建的shot放入到shots
        shots.add(s);
        //启动我们的Shot线程
        new Thread(s).start();
    }
}
