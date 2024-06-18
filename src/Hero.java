/**
 * @author ：2015yunfei
 * @date ：Created in 2024/6/9 10:15
 * 主角的坦克
 */
public class Hero extends Tank {
    public Hero(int x, int y) {
        super(x, y);
    }

    // 射击方法
    public void shotEnemyTank() {
        // 控制主角子弹的最大数量为5
        if (shots.size() == 5) {
            return;
        }

        Shot s = new Shot(getDirect(), this);

        //把新创建的shot放入到shots
        shots.add(s);
        new Thread(s).start();
    }
}
