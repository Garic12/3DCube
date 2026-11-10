import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class Screen {
    public int width;
    public int height;
    private double scale;

    private static final double NEAR = 0.1;

    private final Vec3 X = new Vec3(1, 0, 0);
    private final Vec3 Y = new Vec3(0, 1, 0);
    private final Vec3 Z = new Vec3(0,0,1);

    private static final int[] WHITE = {255, 255, 255, 255};
    private static final int[] RED   = {255, 0, 0, 255};
    private static final int[] GREEN = {0, 255, 0, 255};
    private static final int[] BLUE  = {0, 0, 255, 255};

    public final BufferedImage image;
    private final WritableRaster raster;

    public Screen(int width, int height, double fov) {
        this.width = width;
        this.height = height;
        this.scale = (height / 2.0) / Math.tan(Math.toRadians(fov) / 2);

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        raster = image.getRaster();
    }

    public Boolean InScreen(int x, int y) {
        return (x> 0 && x < width) && (y > 0 && y < height);
    }

    public Vec2 project(Vec3 p) {
        return new Vec2(
                p.x / p.z * scale + width  / 2f,
                p.y / p.z * scale + height / 2f
        );
    }

    public void DrawLine(Vec2 a, Vec2 b) {
        DrawLine(a, b, WHITE);
    }

    public void DrawLine(Vec2 a, Vec2 b, int[] color) {
        if (color == null || color.length != 4) {
            throw new IllegalArgumentException("color must have 4 components (RGBA)");
        }

        // Implements Bresenham’s algorithm
        int x1 = (int) Math.round(a.x), y1 = (int) Math.round(a.y);
        int x2 = (int) Math.round(b.x), y2 = (int) Math.round(b.y);

        int dx = Math.abs(x2 - x1), dy = -Math.abs(y2 - y1);
        int sx = a.x < b.x ? 1 : -1, sy = a.y < b.y ? 1 : -1;

        double err = (int) (dx + dy);

        while (true) {
            if (InScreen(x1, y1)) {
                raster.setPixel(x1, y1, color);
            }
            if (x1 == x2 && y1 == y2) break;
            double e2 = 2 * err;
            if (e2 >= dy) {err += dy; x1 += sx;}
            if (e2 <= dx) {err += dx; y1 += sy;}
        }
    }

    private void DrawAxis(Matrix4 view, Vec3 axis, double length, int[] color) {
        Vec3 a = view.transform(axis.scalarMult(-length));
        Vec3 b = view.transform(axis.scalarMult(length));
        DrawLine(project(a), project(b), color);
    }

    public void DrawXYZ(Camera3D cam) {
        Matrix4 view = cam.getViewMatrix();
        DrawAxis(view, X, 10, RED);
        DrawAxis(view, Y, 10, GREEN);
        DrawAxis(view, Z, 10, BLUE);
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

            DrawLine(screen_a, screen_b);
        }
    }
}
