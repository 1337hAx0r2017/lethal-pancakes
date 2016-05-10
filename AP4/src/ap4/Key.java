package ap4;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

public class Key {
    
    private boolean isDown = false;
    private boolean wasDown = false;
    private String keyName = "";
    
    public Key(String keyName, Controller c)
    {
        this.keyName = keyName;
        c.getInputMap().put(KeyStroke.getKeyStroke(keyName), "hit" + keyName);
        c.getActionMap().put("hit" + keyName, new AbstractAction() {
        @Override
            public void actionPerformed(ActionEvent e) {
                isDown = true;
            }
        });
        c.getInputMap().put(KeyStroke.getKeyStroke("released " + keyName), "rel" + keyName);
        c.getActionMap().put("rel" + keyName, new AbstractAction() {
        @Override
            public void actionPerformed(ActionEvent e) {
                isDown = false;
            }
        });
    }
    
    public void update()
    {
        wasDown = isDown;
    }
    
    public String getName()
    { return keyName; }
    public boolean getDown()
    { return isDown; }
    public boolean getWasDown()
    { return wasDown; }
}