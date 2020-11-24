import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Atlantis extends JFrame {

    private JPanel inspector;
    private Viewport viewport;
    private Camera viewportCamera = new Camera();
    private FileImporter importer = new FileImporter();

    private JSlider xPos = new JSlider();
    private JSlider yPos = new JSlider();
    private JSlider zPos = new JSlider();

    private JMenuItem objItem;
    private JMenuItem sceneMenu;

    public Atlantis() {

        super("Atlantis");
        setSize(800, 600);

        JMenuBar menuBar = new JMenuBar();
        add(menuBar, BorderLayout.NORTH);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenu newMenu = new JMenu("New");
        fileMenu.add(newMenu);

        sceneMenu = new JMenuItem("Scene");
        sceneMenu.addActionListener(new NewSceneListener());
        newMenu.add(sceneMenu);

        fileMenu.addSeparator();

        JMenu importMenu = new JMenu("Import");
        fileMenu.add(importMenu);

        objItem = new JMenuItem(".obj");
        objItem.addActionListener(new OpenFileListener());
        importMenu.add(objItem);

        viewport = new Viewport(viewportCamera);
        add(viewport, BorderLayout.CENTER);

        inspector = new JPanel();
        inspector.setLayout(new BoxLayout(inspector, BoxLayout.Y_AXIS));
        add(inspector, BorderLayout.EAST);

        xPos.addChangeListener(new ValueChange());
        yPos.addChangeListener(new ValueChange());
        zPos.addChangeListener(new ValueChange());

        xPos.setMinimum(-1000);
        xPos.setMaximum(1000);
        xPos.setValue(0);

        yPos.setMinimum(-1000);
        yPos.setMaximum(1000);
        yPos.setValue(0);

        zPos.setMinimum(-100);
        zPos.setMaximum(100);
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

    protected class NewSceneListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            remove(viewport);
            viewport = new Viewport(viewportCamera);
            add(viewport, BorderLayout.CENTER);
            viewport.update();
        }
    }

    protected class OpenFileListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Wavefront Object", "obj");
            fileChooser.setFileFilter(filter);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fileChooser.setCurrentDirectory(fileChooser.getCurrentDirectory());
            int returnVal = fileChooser.showOpenDialog(fileChooser);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                Mesh mesh = importer.parseObj(fileChooser.getSelectedFile().getAbsolutePath());
                viewport.meshs.add(mesh);
                viewport.update();
            }
        }

    }

    public static void main(String[] args) {
        new Atlantis();
    }

}