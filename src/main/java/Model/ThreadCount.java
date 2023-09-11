package Model;

import presentation.MainWindow;

import javax.swing.*;
import java.util.Random;

public class ThreadCount implements Runnable {
    private JLabel jLabel;
    private ImageIcon[] images;
    private boolean state;
    private MainWindow mainWindow;
    private Random random = new Random();

    public ThreadCount(JLabel jLabel, ImageIcon[] images, boolean state) {
        this.images = images;
        this.jLabel = jLabel;
        this.state = state;
    }

    public void setMainWindow(MainWindow mainWindow) {

        this.mainWindow = mainWindow;
    }

    @Override
    public void run() {
        long sleepTime = 800;
        long initialSleep = 50;
        long elapsedTime = 0;
        long startTime = System.currentTimeMillis();

        while (state && elapsedTime < 14000) {
            int randomIndex = random.nextInt(images.length);
            jLabel.setIcon(images[randomIndex]);

            try {
                if (elapsedTime < 7000) {
                    sleepTime = initialSleep;
                } else if (elapsedTime < 12000) {
                    sleepTime = sleepTime + (elapsedTime - 6000) / 150;
                } else {
                    sleepTime = 3000;
                }

                Thread.sleep(sleepTime);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            elapsedTime = System.currentTimeMillis() - startTime;
        }
        stopThread();
    }

    public void stopThread() {
        state = false;
    }
}