import java.awt.*;
import javax.swing.*;
import javax.xml.crypto.dsig.CanonicalizationMethod;

public class Viewport extends JPanel {

    private Camera camera;
    private int gizmoSize = 4;
    private int scale = 50;

    public Viewport(Camera camera) {
        this.camera = camera;
    }

    public void update() {
        revalidate();
        repaint();
    }

    private Vector3[] cube = { new Vector3(-50, 0, -50), new Vector3(50, 0, -50), new Vector3(-50, 100, -50), new Vector3(50, 100, -50),

            new Vector3(-50, 0, 50), new Vector3(50, 0, 50), new Vector3(-50, 100, 50), new Vector3(50, 100, 50) };

    private Vector2[] cubeEdges = {
        new Vector2(0,1),
        new Vector2(2,3),
        new Vector2(0,2),
        new Vector2(3,1),
        new Vector2(0,4),
        new Vector2(2,6),
        new Vector2(1,5),
        new Vector2(3,7),
        new Vector2(4,5),
        new Vector2(4,6),
        new Vector2(6,7),
        new Vector2(5,7),
    };

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.scale(1, -1);
        graphics.translate(0, -getHeight());
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, getWidth(), getHeight());

        graphics.setColor(Color.WHITE);

        //Points
        // for (int i = 0; i < cube.length; i++) {

        //     Vector2 point = project3DPoint(cube[i]);
        //     int x = (int) point.x;
        //     int y = (int) point.y;

        //     graphics.drawOval(x, y, 1, 1);
        // }

        for (int i = 0; i < cubeEdges.length; i++) {

            Vector2 startPoint = project3DPoint(cube[(int)cubeEdges[i].x]);
            Vector2 endPoint = project3DPoint(cube[(int)cubeEdges[i].y]);

            graphics.drawLine((int)startPoint.x, (int)startPoint.y, (int)endPoint.x, (int)endPoint.y);
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
