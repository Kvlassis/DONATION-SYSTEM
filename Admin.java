public class Admin extends User {
    private boolean isAdmin = true;    

    public Admin(String name, String phoneNumber)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
