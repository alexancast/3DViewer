import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Viewport extends JPanel {

    private Camera camera;
    private int gizmoSize = 2;
    private int scale = 50;

    public ArrayList<Mesh> meshs = new ArrayList<>();

    public Viewport(Camera camera) {
        this.camera = camera;
    }

    public void update() {
        revalidate();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.scale(1, -1);
        graphics.translate(0, -getHeight());
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, getWidth(), getHeight());

        graphics.setColor(Color.WHITE);

        for (Mesh mesh : meshs) {
            if (mesh != null) {
                for (int i = 0; i < mesh.vertices.length; i++) {

                    double xPos = mesh.vertices[i].x * scale * camera.transform.position.z + getWidth() / 2 - camera.transform.position.x;
                    double yPos = mesh.vertices[i].y * scale * camera.transform.position.z + getHeight() / 2 - camera.transform.position.y;
                    double zPos = mesh.vertices[i].z * scale;

                    Vector3 point3d = new Vector3(xPos, yPos, zPos);
                    Vector2 point = project3DPoint(point3d);
                    int x = (int) point.x;
                    int y = (int) point.y;
                    
                    graphics.fillOval(x - gizmoSize / 2, y - gizmoSize / 2, gizmoSize, gizmoSize);
                }
            }
        }

    }

    public Vector2 project3DPoint(Vector3 point) {

        // double f = point.z - camera.transform.position.z;
        // double x = ((point.x - camera.transform.position.x) * (f / point.z)) + camera.transform.position.x;
        // x += getWidth() / 2;
        // double y = ((point.y - camera.transform.position.y) * (f / point.z)) + camera.transform.position.y;
        // y += getHeight() / 2;

        // return new Vector2(x, y);

        Vector3 projection = new Vector3();

        double[][] projectionMatrix = new double[2][3];
        double[][] rotationMatrix = new double[3][2];
        double[][] renderMatrix = new double[2][1];

        //ProjectionMatrix
        projectionMatrix[0][0]= 1;
        projectionMatrix[0][1]= 0;
        projectionMatrix[0][2]= 0;

        projectionMatrix[1][0]= 0;
        projectionMatrix[1][1]= 1;
        projectionMatrix[1][2]= 0;

        //Positional
        renderMatrix = MathF.matrixMultiplication(projectionMatrix, point);
        return new Vector2(renderMatrix[0][0], renderMatrix[1][0]);
    }

}
