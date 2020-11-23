import java.awt.*;
import javax.swing.*;

public class Atlantis extends JFrame{

    public Atlantis(){

        super("Atlantis");
        setSize(800,600);
        
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
        
        Viewport viewport = new Viewport();
        add(viewport, BorderLayout.CENTER);
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Atlantis();
    }

}