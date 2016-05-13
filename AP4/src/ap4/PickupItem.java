package ap4;

public abstract class PickupItem {
    
    public int type = 0;
    
    public PickupItem() { }
    
    public PickupItem(int type) { }
    
    public abstract void onPickup();
}