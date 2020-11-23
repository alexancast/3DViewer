import java.awt.*;
import javax.swing.*;

public class Viewport extends JPanel{
    
    private Vector3 cameraPosition = new Vector3(-getWidth()/2, 0, -200);
    private int gizmoSize = 4;

    private Vector3[] cube = {
        new Vector3(0, 0, 0),
        new Vector3(1, 0, 0),
        new Vector3(0, 1, 0),
        new Vector3(1, 1, 0),

        new Vector3(0, 0, 1),
        new Vector3(1, 0, 1),
        new Vector3(0, 1, 1),
        new Vector3(1, 1, 1)
    };

    @Override
    public void paintComponent(Graphics g){
     
        Graphics2D graphics = (Graphics2D) g;
        graphics.scale(1, -1);
        graphics.translate(0, -getHeight());
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        
        graphics.setColor(Color.WHITE);
        for (int i = 0; i < cube.length; i++) {
            int x = (int)(((cube[i].x - cameraPosition.x) * ((cube[i].z - cameraPosition.z)/cube[i].z)) + cameraPosition.x);
            int y = (int)(((cube[i].y - cameraPosition.y) * ((cube[i].z - cameraPosition.z)/cube[i].z)) + cameraPosition.y);
            
            System.out.println("Cube vertex: " + x + ", " + y);
            graphics.drawOval(x - gizmoSize/2, y - gizmoSize/2, gizmoSize/2, gizmoSize/2);
        }

    }

}
