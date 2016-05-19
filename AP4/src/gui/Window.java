package gui;
import javax.swing.JFrame;

public class Window extends JFrame {
    
    GeneralGamePanel panel;
    
    public Window()
    {
        panel = new GamePanel();
        setSize(790, 620);
        setTitle("Lethal Pancakes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panel);
        panel.start();
        setVisible(true);
    }
}