public class Cube {
    private Vec3[] corners;

    GamePanel gp;

    public Cube(GamePanel gp, double size) {
        if (size == 0.0) {
            throw new IllegalArgumentException("size of cube must be positive, got " + size);
        }

        this.gp = gp;
        // Order is first bottom then top, anticlockwise
        corners = new Vec3[] {
                new Vec3(-1, 1, -1),
                new Vec3(1, 1, -1),
                new Vec3(1, -1, -1),
                new Vec3(-1, -1, -1),

                new Vec3(-1, 1, 1),
                new Vec3(1, 1, 1),
                new Vec3(1, -1, 1),
                new Vec3(-1, -1, 1)
        };
    }

    public void update() {
        IO.println("Cube getting loaded");
    }
}
