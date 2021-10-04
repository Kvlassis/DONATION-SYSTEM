import java.util.Scanner;

public abstract class User {
    protected String name;
    protected String phoneNumber;

    public String getName() 
    {
        return name;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }

    public String readLine()
    {
        Scanner sc1 = new Scanner(System.in);
        return sc1.nextLine();
    }
}
