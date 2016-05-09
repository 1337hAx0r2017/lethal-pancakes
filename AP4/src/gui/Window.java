package gui;

import ap4.Game;
import javax.swing.JFrame;

/**
 *
 * @author ed.mason
 */
public class Window extends JFrame {
    
    Game game;
    GamePanel panel;
    
    public Window()
    {
        game = new Game();
        panel = new GamePanel(game);
        setSize(800, 600);
        setTitle("Lethal Pancakes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panel);
        panel.start();
        setVisible(true);
    }
}