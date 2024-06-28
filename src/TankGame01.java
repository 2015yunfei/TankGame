import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

/**
 * @author ：2015yunfei
 * @date ：Created in 2024/6/9 10:21
 */
public class TankGame01 extends JFrame {
    MyPanel mp;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        TankGame01 tankGame01 = new TankGame01();
    }

    public TankGame01() {
        String key;

        while (true) {
            System.out.println("请输入选择 1: 新游戏 2: 继续上局");
            key = scanner.next();

            if ("1".equals(key) || "2".equals(key)) {
                // 如果输入是 "1" 或 "2"，则跳出循环
                break;
            } else {
                // 如果输入不是 "1" 或 "2"，则打印错误信息并继续循环
                System.out.println("无效的输入，请输入 1 或 2。");
            }
        }

        mp = new MyPanel(key);
        //将mp 放入到Thread ,并启动
        Thread thread = new Thread(mp);
        thread.start();
        //把面板(就是游戏的绘图区域)
        this.add(mp);
        this.setSize(1300, 750);
        this.setVisible(true);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //在JFrame 中增加相应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }
}
