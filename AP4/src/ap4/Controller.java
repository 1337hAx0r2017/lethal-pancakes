package ap4;

import java.util.ArrayList;
import javax.swing.JComponent;

public class Controller extends JComponent {
    
    public ArrayList<Key> keys = new ArrayList<>();
    
    public Controller()
    {
        requestFocusInWindow();
        
        // Default keys
        addKey("UP");
        addKey("DOWN");
        addKey("LEFT");
        addKey("RIGHT");
    }
    
    public void addKey(String keyName)
    {
        keys.add(new Key(keyName, this));
    }
    
    public Key getKey(String name)
    {
        for (Key k : keys)
        {
            if (k.keyName.equals(name))
                return k;
        }
        return null;
    }
    
    public boolean getKeyState(String name)
    {
        for (Key k : keys)
        {
            if (k.keyName.equals(name))
                return k.isDown;
        }
        return false;
    }
    
    public boolean getKeyOldState(String name)
    {
        for (Key k : keys)
        {
            if (k.keyName.equals(name))
                return k.wasDown;
        }
        return false;
    }
    
    public void update()
    {
        for (Key k : keys)
            k.update();
    }
}