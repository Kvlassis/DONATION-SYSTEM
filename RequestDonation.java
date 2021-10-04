public class RequestDonation {
    private Entity entity;
    private double quantity;

    public RequestDonation(Entity ent, double quantity)
    {
        this.setEntity(ent);
        this.setQuantity(quantity);
    }

    public RequestDonation() 
    {
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    
    
}
