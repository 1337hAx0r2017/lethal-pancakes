package gui;

import ap4.Controller;
import ap4.Game;
import javax.swing.JFrame;

/**
 *
 * @author ed.mason
 */
public class Window extends JFrame {
    
    GamePanel panel;
    
    public Window()
    {
        panel = new GamePanel();
        setSize(800, 600);
        setTitle("Lethal Pancakes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panel);
        panel.start();
        setVisible(true);
    }
}