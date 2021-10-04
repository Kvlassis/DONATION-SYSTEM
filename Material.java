public class Material extends Entity {
    private final double level1, level2, level3;
    
    public Material(String name, String description, int id, double level1, double level2, double level3)
    {
        super(name, description, id);
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
    }

    public double getLevel1() 
    {
        return level1;
    }
    
    public double getLevel2() 
    {
        return level2;
    }

    public double getLevel3() 
    {
        return level3;
    }

    public String getDetails()
    {
        return "\nLevel 1 (1 Person in Family) can have " + getLevel1() + " of this material." +
               "\nLevel 2 (2-4 People in Family) can have " + getLevel2() + " of this material." +
               "\nLevel 3 (More than 5 People in Family) can have " + getLevel3() + " of this material." +
               "\n\n" + this.name + " is a material.";
    }

    /*

    @Override
    String toString()
    {
        String s1 = this.getEntityInfo();
        String s2 = this.getDetails();
        System.out.println(s1 + s2);
    }

    */
}
