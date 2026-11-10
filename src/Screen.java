import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class Screen {
    public int width;
    public int height;
    private double scale;

    public final BufferedImage image;
    private final WritableRaster raster;
    private final int[] WHITE = {255, 255, 255, 255};

    public Screen(int width, int height, double fov) {
        this.width = width;
        this.height = height;
        this.scale = (height / 2.0) / Math.tan(Math.toRadians(fov) / 2);

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        raster = image.getRaster();
    }

    public Boolean in_screen(Vec2 p) {
        return (p.x > 0 && p.x < width) && (p.y > 0 && p.y < height);
    }

    public Vec2 project(Vec3 p) {
        return new Vec2(
                p.x / p.z * scale + width  / 2f,
                p.y / p.z * scale + height / 2f
        );
    }

    public void DrawPixel(int x, int y) {
        raster.setPixel(x, y, WHITE);
    }

    public void DrawLine(Vec2 a, Vec2 b) {
        // Implements Bresenham’s algorithm
        int x1 = (int) Math.round(a.x), y1 = (int) Math.round(a.y);
        int x2 = (int) Math.round(b.x), y2 = (int) Math.round(b.y);

        int dx = Math.abs(x2 - x1), dy = -Math.abs(y2 - y1);
        int sx = a.x < b.x ? 1 : -1, sy = a.y < b.y ? 1 : -1;

        double err = (int) (dx + dy);

        while (true) {
            raster.setPixel(x1, y1, WHITE);
            if (x1 == x2 && y1 == y2) break;
            double e2 = 2 * err;
            if (e2 >= dy) {err += dy; x1 += sx;}
            if (e2 <= dx) {err += dx; y1 += sy;}
        }
    }

    public void DrawCube(Cube cube, Camera3D cam) {
        Matrix4 view_matrix = cam.getViewMatrix();
        for (int[] pair : cube.pairs) {
            Vec3 a = cube.getWorldPos().add(cube.corners[pair[0]]);
            Vec3 b = cube.getWorldPos().add(cube.corners[pair[1]]);

            Vec3 cam_a = view_matrix.transform(a);
            Vec3 cam_b = view_matrix.transform(b);

            Vec2 screen_a = project(cam_a);
            Vec2 screen_b = project(cam_b);


            // TODO: Implement clamp to screen function
            if (in_screen(screen_a) && in_screen(screen_b)) DrawLine(screen_a, screen_b);
        }
    }
}
