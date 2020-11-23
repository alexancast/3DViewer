import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Atlantis extends JFrame {

    private JPanel inspector;
    private Viewport viewport;
    private Camera viewportCamera = new Camera();

    private JSlider xPos = new JSlider();
    private JSlider yPos = new JSlider();
    private JSlider zPos = new JSlider();

    public Atlantis() {

        super("Atlantis");
        setSize(800, 600);

        JMenuBar menuBar = new JMenuBar();
        add(menuBar, BorderLayout.NORTH);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenu newMenu = new JMenu("New");
        fileMenu.add(newMenu);

        JMenuItem sceneMenu = new JMenuItem("Scene");
        newMenu.add(sceneMenu);

        fileMenu.addSeparator();

        JMenuItem importFile = new JMenuItem("Import");
        fileMenu.add(importFile);

        viewport = new Viewport(viewportCamera);
        add(viewport, BorderLayout.CENTER);

        inspector = new JPanel();
        inspector.setLayout(new BoxLayout(inspector, BoxLayout.Y_AXIS));
        add(inspector, BorderLayout.EAST);

        xPos.addChangeListener(new ValueChange());
        yPos.addChangeListener(new ValueChange());
        zPos.addChangeListener(new ValueChange());

        xPos.setMinimum(-10);
        xPos.setMaximum(10);
        xPos.setValue(0);

        yPos.setMinimum(-10);
        yPos.setMaximum(10);
        yPos.setValue(0);

        zPos.setMinimum(-10);
        zPos.setMaximum(10);
        zPos.setValue(0);

        inspector.add(xPos);
        inspector.add(yPos);
        inspector.add(zPos);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    protected class ValueChange implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            viewportCamera.transform.position = new Vector3(xPos.getValue(), yPos.getValue(), zPos.getValue());
            viewport.update();
        }

    }

    public static void main(String[] args) {
        new Atlantis();
    }

}