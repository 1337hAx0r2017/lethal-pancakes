package ap4.map;

import ap4.Game;

public class Wall extends Tile {
    
    Wall()
    {
        solid = true;
        type = 1;
    }
    
    static {
    }
    
    
    
    public void draw(Game game, Room r)
    {
        cap.draw(game.camera, r.x + x, r.y + y, r.z + z, 1, game.theLight);
    }

    @Override
    public void setupWalls(Tile[][] t) {
    }
}
