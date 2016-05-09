package ap4;

import java.util.ArrayList;
import javax.swing.JComponent;

public class Controller extends JComponent {
    
    public ArrayList<Key> keys = new ArrayList<>();
    
    public Controller()
    {
        requestFocusInWindow();
    }
    
    public void addKey(String keyName)
    {
        keys.add(new Key(keyName, this));
    }
    
    public void update()
    {
        for (Key k : keys)
        {
            k.update();
            System.out.println(k.keyName + " down: " + k.isDown);
        }
    }
}