public class Cube {
    private Vec3 pos;
    private Vec3[] corners;

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
        return corners[0];
    }

    public void update() {
        IO.println("Cube getting loaded");
    }
}
