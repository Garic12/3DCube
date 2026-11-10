import javax.swing.*;
import java.awt.*;

public static void main(String[] args) {
    final int CANVAS_WIDTH = 500, CANVAS_HEIGHT = 500;
    final int WINDOW_WIDTH = 700, WINDOW_HEIGHT = 700;
    Screen screen = new Screen(CANVAS_WIDTH, CANVAS_HEIGHT, 90);

    Vec3 cube_pos1 = new Vec3(-4, 1, 1);
    Cube cube1 = new Cube(1, cube_pos1);

    Vec3 cube_pos2 = new Vec3(-3, 3, 1.2);
    Cube cube2 = new Cube(1, cube_pos2);

    Camera3D cam = new Camera3D(new Vec3(0, 0, 3), new Vec3(-5, 3, 2));
    Matrix4 view_mat = cam.getViewMatrix();

    screen.DrawCube(cube1, cam);
    screen.DrawCube(cube2, cam);

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

    final int FPS = 60;
    Timer loop = new Timer(1000 / FPS, e -> {
        panel.repaint();
    });
    loop.start();
}