import javax.swing.*;

/**
 * @author ：2015yunfei
 * @date ：Created in 2024/6/9 10:21
 */
public class TankGame01 extends JFrame {
    MyPanel mp = null;

    public static void main(String[] args) {
        TankGame01 tankGame01 = new TankGame01();
    }

    public TankGame01() {
        mp = new MyPanel();
        //将mp放入到Thread,并启动
        Thread thread = new Thread(mp);
        thread.start();
        //把面板(就是游戏的绘图区域)
        this.add(mp);
        this.setSize(1000, 750);
        this.setVisible(true);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
