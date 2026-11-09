import javax.swing.*;
import java.awt.*;

public static void main(String[] args) {
    Camera3D cam = new Camera3D(new Vec3(0, 0, 0), new Vec3(-1, 0, 0));

    Matrix4 view_mat = cam.getViewMatrix();

    Vec3 point = new Vec3(-2, 1, 0);
    IO.println(view_mat.transform(point).toString());
//    JFrame window = new JFrame("Cube");
//    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//    window.add(new JPanel() {
//        @Override
//        protected void paintComponent(Graphics g) {
//            super.paintComponent(g);
//            // project your cube's vertices and g.drawLine(...) here
//        }
//    });
//
//    window.setSize(800, 600);
//    window.setVisible(true);
}