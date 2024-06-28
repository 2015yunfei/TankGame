import java.io.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ：2015yunfei
 * @date ：Created in 2024/6/12 15:53
 */
public class Recorder {
    static Logger logger = Logger.getLogger(Recorder.class.getName());  // 类名或应用名作为日志记录器的名称

    //定义变量，记录我方击毁敌人坦克数
    private static int hasDestroyedTanks = 0;
    //定义IO对象, 准备写数据到文件中
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static final String recordFile = "src\\myRecord.txt";
    //定义Vector ,指向 MyPanel 对象的 敌人坦克Vector
    private static Vector<EnemyTank> enemyTanks = null;
    //定义一个Node 的Vector ,用于保存敌人的信息node
    private static Vector<Node> nodes = new Vector<>();

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    public static String getRecordFile() {
        return recordFile;
    }

    //增加一个方法，用于读取recordFile, 恢复相关信息
    //该方法，在继续上局的时候调用即可
    public static Vector<Node> getNodesAndEnemyTankRec() {
        try {
            br = new BufferedReader(new FileReader(recordFile));
            hasDestroyedTanks = Integer.parseInt(br.readLine());
            //循环读取文件，生成nodes 集合
            String line = "";//255 40 UP
            while ((line = br.readLine()) != null) {
                String[] xyd = line.split(" ");

                Node node = null;
                try {
                    node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), xyd[2]);
                } catch (IllegalStateException | NumberFormatException e) {
                    System.out.println("保存失败" + e.toString());
                }
                nodes.add(node);//放入nodes Vector
            }
        } catch (IOException e) {
            // 使用logger来记录错误信息
            logger.log(Level.SEVERE, "An IO exception occurred", e);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                // 使用logger来记录错误信息
                logger.log(Level.SEVERE, "An IO exception occurred", e);
            }
        }
        return nodes;
    }


    //增加一个方法，当游戏退出时，将 allEnemyTankNum 保存到 recordFile
    //对 keepRecord 进行升级, 保存敌人坦克的坐标和方向
    public static void keepRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(hasDestroyedTanks + "\r\n");
            //遍历敌人坦克的Vector ,然后根据情况保存即可
            //OOP, 定义一个属性 ，然后通过setXxx得到敌人坦克的Vector
            for (int i = 0; i < enemyTanks.size(); i++) {
                //取出敌人坦克
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive) { //并发场景下建议判断
                    //保存该enemyTank信息
                    String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirect();
                    //写入到文件
                    bw.write(record + "\r\n");
                }
            }
        } catch (IOException e) {
            // 使用logger来记录错误信息
            logger.log(Level.SEVERE, "An IO exception occurred", e);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                // 使用logger来记录错误信息
                logger.log(Level.SEVERE, "An IO exception occurred", e);
            }
        }
    }

    public static int getHasDestroyedTanks() {
        return hasDestroyedTanks;
    }

    public static void setHasDestroyedTanks(int hasDestroyedTanks) {
        Recorder.hasDestroyedTanks = hasDestroyedTanks;
    }

    //当我方坦克击毁一个敌人坦克，就应当 allEnemyTankNum++
    public static void addHasDestroyedTanks() {
        Recorder.hasDestroyedTanks++;
    }
}
