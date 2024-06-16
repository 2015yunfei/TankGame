import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ：2015yunfei
 * @date ：Created in 2024/6/12 18:45
 */
public class AePlayWave extends Thread {
    Logger logger = Logger.getLogger("AePlayWave");  // 类名或应用名作为日志记录器的名称
    private final String filename;

    public AePlayWave(String wavfile) { //构造器 , 指定文件
        filename = wavfile;

    }

    public void run() {

        File soundFile = new File(filename);

        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e) {
            // 使用logger来记录错误信息
            logger.log(Level.SEVERE, "An exception occurred", e);
            return;
        }

        AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auline = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        try {
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (Exception e) {
            // 使用logger来记录错误信息
            logger.log(Level.SEVERE, "An exception occurred", e);
            return;
        }

        auline.start();
        int nBytesRead = 0;
        //这是缓冲
        byte[] abData = new byte[512];

        try {
            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0)
                    auline.write(abData, 0, nBytesRead);
            }
        } catch (IOException e) {
            // 使用logger来记录错误信息
            logger.log(Level.SEVERE, "An IO exception occurred", e);
        } finally {
            auline.drain();
            auline.close();
        }
    }
}
