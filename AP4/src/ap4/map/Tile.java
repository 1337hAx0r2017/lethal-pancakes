package ap4.map;

public class Tile {
      
    public boolean exit = false;
    public boolean solid = false; //false = can walk through
    public int type = 0;
    /** types
     * 0: air
    */
    
    Tile()
    {
        solid = false;
        exit = false;
        type = 0;
    }
}
