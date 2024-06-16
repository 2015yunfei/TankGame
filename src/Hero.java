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

        Shot s = new Shot(getDirect(),this);

        //把新创建的shot放入到shots
        shots.add(s);
        new Thread(s).start();
    }
}
