import java.util.ArrayList;
import java.util.List;

public class Organization {
    private String name;
    private Admin admin;
    private List<Entity> entityList = new ArrayList();
    private List<Donator> donatorList = new ArrayList();
    private List<Beneficiary> beneficiaryList = new ArrayList();
    private RequestDonationList currentDonations = new RequestDonationList();

    
    
    public Organization() {
    }

    public Organization(String name) 
    {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Admin getAdmin() 
    {
        return admin;
    }
    public List<Donator> getDonators()
    {
        return donatorList;
    }

    public List<Beneficiary> getBeneficiaries()
    {
        return beneficiaryList;
    }
    public void setAdmin(Admin admin) 
    {
        this.admin = admin;
    }    
    public void addEntity(Entity entity)
    {
        entityList.add(entity);
    }    
    public void removeEntity(Entity entity)
    {
        entityList.remove(entity);
    }    
    public void insertDonator(Donator donator)
    {
        donatorList.add(donator);
    }    
    public void removeDonator(Donator donator)
    {
        donatorList.remove(donator);
    }    
    public void insertBeneficiary(Beneficiary beneficiary)
    {
        beneficiaryList.add(beneficiary);
    }    
    public void removeBeneficiary(Beneficiary beneficiary)
    {
        beneficiaryList.remove(beneficiary);
    }    
    public List<Entity> listEntities()
    {
        return entityList;
        /*

        switch(choice) {
            case 1:
                for(int i=0; i < entityList.size(); i++)
                {
                    // if(entityList.get(i).equals(Material))
                }
                break;
            case 2:
                break;
            default:
                System.out.println("\nWrong Choice, please enter 1 (Materials) or 2 (Services)");
        }

        */
    }
    
    public void listBeneficiaries()
    {
        for(int i=0; i < beneficiaryList.size(); i++)
        {
            System.out.println("\n" + i+1 + " | Name: " + beneficiaryList.get(i).getName());
        }
    }

    public void listDonators()
    {
        for(int i=0; i < donatorList.size(); i++)
        {
            System.out.println("\n" + i+1 + " | Name: " + donatorList.get(i).getName());
        }
    }

    public void modifyBeneficiary(int index, Beneficiary b)
    {
        beneficiaryList.set(index, b);
    }
    public void modifyDonator(int index, Donator d)
    {
        donatorList.set(index, d);
    }

    // Reset receivedList of ALL Beneficiaries in the organization

    public void resetAllBeneficiaries()
    {
        Beneficiary b;

        for(int i=0; i < beneficiaryList.size(); i++)
        {
            b = beneficiaryList.get(i);
            b.resetReceivedList();
            beneficiaryList.set(i,b);
        }
    }

    public void updateRd(RequestDonationList rdEntList, String type)
    {
        RequestDonation rdEnt;
        boolean found = false;

        if(type == "request")
        {
            for(int i=0; i < rdEntList.size(); i++)
            {
                for(int j=0; j < currentDonations.size(); j++)
                {
                    if(rdEntList.getRd(i).getEntity().equals(currentDonations.getRd(j).getEntity()))
                    {
                        rdEnt = new RequestDonation(rdEntList.getRd(i).getEntity(), 
                            (currentDonations.getRd(j).getQuantity() - rdEntList.getRd(i).getQuantity()));

                        currentDonations.modify(rdEnt);
                    }
                }
            }
        }
        else if(type == "offer")
        {
            if(currentDonations.rdEmpty())
            {
                for(int i=0; i < rdEntList.size(); i++)
                {
                    currentDonations.add(rdEntList.getRd(i));
                }
            }
            else
            {
                for(int i=0; i < rdEntList.size(); i++)
                {
                    found = false;
                    for(int j=0; j < currentDonations.size(); j++)
                    {
                        if(rdEntList.getRd(i).getEntity().equals(currentDonations.getRd(j).getEntity()))
                        {
                            found = true;
                            rdEnt = new RequestDonation(rdEntList.getRd(i).getEntity(),
                            (currentDonations.getRd(j).getQuantity() + rdEntList.getRd(i).getQuantity()));
                            
                            currentDonations.modify(rdEnt);
                            break;
                        }
                    }
                    if(found == false)
                    {
                        currentDonations.add(rdEntList.getRd(i));
                    }
                }
            }
        }

    }

    public RequestDonationList getCurrentDonations()
    {
        return currentDonations;
    }
}
