public class Camera3D {
    private Vec3 pos;
    private Vec3 target;
    private final Vec3 up = new Vec3(0, 0, 1);

    public Camera3D(Vec3 pos, Vec3 target) {
        this.pos = pos;
        this.target = target;
    }

    public Matrix4 getViewMatrix() {
        return Matrix4.lookAt(pos, target, up);
    }
}
