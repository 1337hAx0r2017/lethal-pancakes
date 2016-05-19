package gui;
import javax.swing.JFrame;

public class Window extends JFrame {
    
    public GeneralGamePanel panel;
    public static int sx = 790;
    public static int sy = 620;
    
    public Window()
    {
        panel = new GamePanel();
        setSize(sx, sy);
        setTitle("Lethal Pancakes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(panel);
        panel.start();
        setVisible(true);
    }
}