import java.awt.*;
import javax.swing.*;

public class Viewport extends JPanel {

    private Camera camera;
    private int gizmoSize = 4;

    public Viewport(Camera camera) {
        this.camera = camera;
    }

    public void update() {
        revalidate();
        repaint();
    }

    private Vector3[] cube = { new Vector3(0, 0, 0), new Vector3(1, 0, 0), new Vector3(0, 1, 0), new Vector3(1, 1, 0),

            new Vector3(0, 0, -1), new Vector3(1, 0, -1), new Vector3(0, 1, -1), new Vector3(1, 1, -1) };

    @Override
    public void paintComponent(Graphics g) {

        System.out.println(
                camera.transform.position.x + ", " + camera.transform.position.y + ", " + camera.transform.position.z);

        Graphics2D graphics = (Graphics2D) g;
        graphics.scale(1, -1);
        graphics.translate(0, -getHeight());
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, getWidth(), getHeight());

        graphics.setColor(Color.WHITE);

        for (int i = 0; i < cube.length; i++) {

            Vector2 point = project3DPoint(cube[i]);
            int x = (int) point.x;
            int y = (int) point.y;

            graphics.drawOval(x - gizmoSize / 2, y - gizmoSize / 2, gizmoSize / 2, gizmoSize / 2);
        }
    }

    public Vector2 project3DPoint(Vector3 point) {

        double f = point.z - camera.transform.position.z;
        double x = ((point.x - camera.transform.position.x) * (f / point.z)) + camera.transform.position.x;
        x += getWidth() / 2;
        double y = ((point.y - camera.transform.position.y) * (f / point.z)) + camera.transform.position.y;
        y += getHeight() / 2;

        return new Vector2(x, y);
    }

}
