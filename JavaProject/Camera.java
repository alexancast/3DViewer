public class Camera {

    public Transform transform = new Transform();

    public Camera() {
    }

    public Camera(Vector3 position) {
        transform.position = position;
    }

}
