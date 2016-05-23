package ap4;

import java.util.ArrayList;
import javax.swing.JComponent;

public class Controller extends JComponent {
    
    public ArrayList<Key> keys = new ArrayList<>();
    public Key w;
    public Key a;
    public Key s;
    public Key d;
    public Key _q;
    public Key _e;
    public Key up;
    public Key down;
    public Key left;
    public Key right;
    public Key z;
    public Key x;
    
    public Controller()
    {
        requestFocusInWindow();
        
        // Default keys
        w = addKey("W");
        a = addKey("A");
        s = addKey("S");
        d = addKey("D");
        _q = addKey("Q");
        _e = addKey("E");
        up = addKey("UP");
        down = addKey("DOWN");
        left = addKey("LEFT");
        right = addKey("RIGHT");
        z = addKey("Z");
        x = addKey("X");
    }
    
    // Uses java keybinding names - A is A, up arrow is UP
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