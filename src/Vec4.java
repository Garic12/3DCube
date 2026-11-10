public class Vec4 {
    public final double x;
    public final double y;
    public final double z;
    public final double w;

    public Vec4(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vec4(Vec3 p) {
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
        this.w = 1;
    }

    public Vec3 xyz() {
        return new Vec3(x, y, z);
    }

    @Override
    public String toString() {
        return "Vec3(" + x + ", " + y + ", " + z + ", " + w + ")";
    }
}
