import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // Screen Settings
    final int screenWidth = 500;
    final int screenHeight = 500;

    Thread game_thread;

    Cube cube = new Cube(this, 1);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    public void startGameThread() {
        game_thread = new Thread(this);
        game_thread.start();
    }

    @Override
    public void run() {
        while (game_thread != null) {
            update();
            repaint();

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void update() {
        cube.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(0, 100, 5, 400);
    }
}