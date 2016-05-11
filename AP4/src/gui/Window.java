package gui;

import ap4.Controller;
import ap4.Game;
import javax.swing.JFrame;

/**
 *
 * @author ed.mason
 */
public class Window extends JFrame {
    
    GeneralGamePanel panel;
    
    public Window()
    {
        panel = new ModelTestGamePanel();
        setSize(800, 600);
        setTitle("Lethal Pancakes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panel);
        panel.start();
        setVisible(true);
    }
}