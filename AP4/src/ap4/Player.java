package ap4;

import java.util.ArrayList;

public class Player extends Character {
    
    ArrayList<InvItem> items;
    
    Player(float x, float y, float moveSpeed){
        super(x, y, moveSpeed);
        
        items = new ArrayList<InvItem>();
    }
    
    @Override
    void update(Game game, float time){
        // Movement
        move(game, game.control);
    }
}
