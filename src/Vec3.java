public class Vec3 {
    public final double x;
    public final double y;
    public final double z;

    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3 sub(Vec3 o) {
        return new Vec3(x - o.x, y - o.y, z - o.z);
    }

    public Vec3 add(Vec3 o) {
        return new Vec3(x + o.x, y + o.y, z + o.z);
    }

    public double dot(Vec3 o) {
        return x * o.x + y * o.y + z * o.z;
    }

    public Vec3 normalize() {
        double len = Math.sqrt(dot(this));
        return new Vec3(x / len, y / len, z / len);
    }

    @Override
    public String toString() {
        return "Vec3(" + x + ", " + y + ", " + z + ")";
    }
}
