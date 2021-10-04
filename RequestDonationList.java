import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RequestDonationList {
    private List<RequestDonation> rdEntities = new ArrayList();
    
    public RequestDonation get(int entId)
    {
        for(int i=0; i < rdEntities.size(); i++)
        {
            if(rdEntities.get(i).getEntity().getId() == entId)
            {
                return rdEntities.get(i);
            }
        }

        System.out.println("\nERROR: Specified RequestDonation with ID: " + entId + " does not exist.");
        return null;
    }

    public List<RequestDonation> getRdList()
    {
        return rdEntities;
    }

    public RequestDonation getRd(int i)
    {
        return rdEntities.get(i);
    }

    public int size()
    {
        return rdEntities.size();
    }
    
    public void add(RequestDonation rdEnt)
    {
        Boolean foundRD = false; 

        for(int i=0; i < rdEntities.size(); i++)
        {
            if(rdEnt.getEntity().equals(rdEntities.get(i).getEntity()))
            {
                rdEntities.set(i, rdEnt);
                System.out.println("\nUpdated quantity of specificed entity.");
                foundRD = true;
                break;
            }
        }

        if(foundRD == false)
        {
            rdEntities.add(rdEnt);
            System.out.println("\nAdded entity to the list.");
        }
    }
    
    public void remove(RequestDonation rdEnt)
    {
        rdEntities.remove(rdEnt);
    }
    
    public void modify(RequestDonation rdEnt)
    {
        Entity ent = rdEnt.getEntity();
        Boolean foundEnt = false;

        for(int i=0; i < rdEntities.size(); i++)
        {
            if(ent.getId() == rdEntities.get(i).getEntity().getId())
            {
                rdEntities.set(i, rdEnt);
                foundEnt = true;
                break;
            }
        }

        if(foundEnt == true)
        {
            System.out.println("\nSpecificed RequestDonation has had it's quantity updated!");
        }
        else
        {
            System.out.println("\nERROR: Specificed RequestDonation does not exist.");
        }
    }
    
    public void monitor()
    {
        System.out.println("\n\nEntities List contains:");

        for(int i=0; i < rdEntities.size(); i++)
        {
            System.out.println("\n\t"+ i + "\t" + rdEntities.get(i).getEntity().getName() + " with "
                             + rdEntities.get(i).getQuantity() + " quantity available.");
        }

        if(rdEntities.isEmpty())
        {
            System.out.println("\n\tNothing. The list is empty.");
        }
    }

    public boolean rdEmpty()
    {
        if(rdEntities.isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void reset()
    {
        rdEntities.clear();
    }

    public String readLine()
    {
        Scanner sc1 = new Scanner(System.in);
        return sc1.nextLine();
    }
}
