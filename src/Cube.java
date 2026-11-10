public class Cube {
    public final Vec3[] corners;
    public final int[][] pairs = {
            {0,1}, {1,2}, {2,3}, {3,0},   // bottom
            {4,5}, {5,6}, {6,7}, {7,4},   // top
            {0,4}, {1,5}, {2,6}, {3,7}    // verticals
    };
    private Vec3 pos;

    public Cube(double size, Vec3 pos) {
        if (size == 0.0) {
            throw new IllegalArgumentException("size of cube must be positive, got " + size);
        }

        this.pos = pos;
        // Order is first bottom then top, anticlockwise
        corners = new Vec3[] {
                new Vec3(0, 0, 0),
                new Vec3(1, 0, 0),
                new Vec3(1, 1, 0),
                new Vec3(0, 1, 0),

                new Vec3(0, 0, 1),
                new Vec3(1, 0, 1),
                new Vec3(1, 1, 1),
                new Vec3(0, 1, 1)
        };
    }

    public Vec3 getWorldPos() {
        return pos;
    }

    public void update() {
        IO.println("Cube getting loaded");
    }
}
