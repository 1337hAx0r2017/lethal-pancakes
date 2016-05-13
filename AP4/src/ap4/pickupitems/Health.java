package ap4.pickupitems;

import ap4.PickupItem;

public class Health extends PickupItem {

    public Health()
    {
        type = 1;
    }   
    
    @Override
    public void onPickup() {
        // Grant player health
    }
}