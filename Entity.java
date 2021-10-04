public abstract class Entity {
    protected String name, description;
    protected int id;

    public Entity(String name, String description, int id)
    {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public String getName() 
    {
        return name;
    }

    public String getDescription() 
    {
        return description;
    }

    public int getId() 
    {
        return id;
    }
    
    public String getEntityInfo()
    {
        return "\nName: " + this.name + "\nDescription: " + this.description + "\nID: " + this.id;
    }

 
    @Override
    public String toString()
    {
        String s1 = this.getEntityInfo();
        String s2 = this.getDetails();
        return s1 + s2;
    }

    abstract public String getDetails();
}
