package ap4;

import java.util.ArrayList;
import javax.swing.JComponent;

public class Controller extends JComponent {
    
    public ArrayList<Key> keys = new ArrayList<>();
    public Key up;
    public Key down;
    public Key left;
    public Key right;
    
    public Controller()
    {
        requestFocusInWindow();
        
        // Default keys
        up = addKey("UP");
        down = addKey("DOWN");
        left = addKey("LEFT");
        right = addKey("RIGHT");
    }
    
    public Key addKey(String keyName)
    {
        Key k = new Key(keyName, this);
        keys.add(k);
        return k;
    }
    
    public Key getKey(String name)
    {
        for (Key k : keys)
        {
            if (k.getName().equals(name))
                return k;
        }
        return null;
    }
    
    public boolean getKeyState(String name)
    {
        for (Key k : keys)
        {
            if (k.getName().equals(name))
                return k.getDown();
        }
        return false;
    }
    
    public boolean getKeyOldState(String name)
    {
        for (Key k : keys)
        {
            if (k.getName().equals(name))
                return k.getDown();
        }
        return false;
    }
    
    public void update()
    {
        for (Key k : keys)
            k.update();
    }
}