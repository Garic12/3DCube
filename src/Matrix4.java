public class Matrix4 {
    final double[][] m = new double[4][4];

    public static Matrix4 lookAt(Vec3 eye, Vec3 target, Vec3 up) {
        Vec3 f = eye.sub(target).normalize();
        Vec3 r = up.cross(f).normalize();
        Vec3 u = f.cross(r);

        Matrix4 v = new Matrix4();
        v.m[0] = new double[] {r.x, r.y, r.z, -r.dot(eye)};
        v.m[1] = new double[] {u.x, u.y, u.z, -u.dot(eye)};
        v.m[2] = new double[] {f.x, f.y, f.z, -f.dot(eye)};
        v.m[3] = new double[] {0, 0, 0, 1};

        return v;
    }

    public Matrix4 multiply(Matrix4 o) {
        Matrix4 result = new Matrix4();

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                for (int k = 0; k < 4; k++) {
                    result.m[x][y] += this.m[x][k] * o.m[k][y];
                }
            }
        }
        return result;
    }

    public Vec3 transform(Vec3 p) {
        double[] in = {p.x, p.y, p.z, 1};

        double[] out = new double[4];
        for (int i = 0; i < 4; i++) {
            for (int k = 0; k < 4; k++) {
                out[i] += m[i][k] * in[k];
            }
        }

        return new Vec3(out[0], out[1], out[2]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(i == 0 ? "[" : " ");
            sb.append("[");
            for (int j = 0; j < 4; j++) {
                sb.append(String.format("%9.4f", m[i][j]));
                if (j < 3) sb.append(", ");
            }
            sb.append("]");
            sb.append(i == 3 ? "]" : "\n");
        }
        return sb.toString();
    }
}
