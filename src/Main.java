import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        final int FPS = 60;
        double[] time = {0};
        final int CANVAS_WIDTH = 500, CANVAS_HEIGHT = 500;
        final int WINDOW_WIDTH = 700, WINDOW_HEIGHT = 700;

        Screen screen = new Screen(CANVAS_WIDTH, CANVAS_HEIGHT, 90);
        Camera3D cam = new Camera3D(new Vec3(8, 6, 8), new Vec3(0, 0, 0));

        JFrame window = new JFrame("Cube");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                        RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
                g.drawImage(screen.image, 0, 0, getWidth(), getHeight(), null);
            }
        };
        panel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

        window.add(panel);
        window.pack();
        window.setLocationRelativeTo(null); // center on screen
        window.setVisible(true);

        Set<Integer> keys = new HashSet<>();

        panel.setFocusable(true);
        panel.addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e)  { keys.add(e.getKeyCode()); }
            @Override public void keyReleased(KeyEvent e) { keys.remove(e.getKeyCode()); }
        });

        panel.requestFocusInWindow();

        Timer loop = new Timer(1000 / FPS, e -> {
            // 1. input
            if (keys.contains(KeyEvent.VK_W)) {
                cam.ChangePosition(new Vec3(0,0,0.2));
            } else if (keys.contains(KeyEvent.VK_S)) {
                cam.ChangePosition(new Vec3(0,0,-0.2));
            } else if (keys.contains(KeyEvent.VK_A)) {
                cam.ChangeAngle(0.1);
            } else if (keys.contains(KeyEvent.VK_D)) {
                cam.ChangeAngle(-0.1);
            }

            // 2. clear
            Graphics2D g = screen.image.createGraphics();
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, screen.width, screen.height);
            g.dispose();

            screen.DrawXYZ(cam);

            time[0] += 0.05;

            int half = 4;
            double spacing = 1;

            for (int x = -half; x <= half; x++) {
                for (int z = -half; z <= half; z++) {
                    double dist = Math.sqrt(x * x + z * z);
                    double y = Math.sin(dist * 0.8 - time[0]) * 1.5;

                    Vec3 pos = new Vec3(x * spacing, y, z * spacing);
                    screen.DrawCube(new Cube(0.5, pos), cam);
                }
            }

            panel.repaint();
        });
        loop.start();
    }
}