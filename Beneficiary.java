import java.util.ArrayList;
import java.util.List;

public class Beneficiary extends User {
    private int noPersons = 1;
    private RequestDonationList receivedList = new RequestDonationList();
    private Requests requestsList = new Requests();
    private Requests commitRequests = new Requests();
    private Requests tempRq = new Requests();
    
    public Beneficiary(String name, String phoneNumber, int noPersons)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.setNoPersons(noPersons);
    }

    public int getNoPersons() {
        return noPersons;
    }

    public void setNoPersons(int noPersons) {
        this.noPersons = noPersons;
    }

    public Requests getRequestsList() {
        return requestsList;
    }

    public void setRequestsList(Requests requestsList) {
        this.requestsList = requestsList;
    }

    public RequestDonationList getReceivedList() {
        return receivedList;
    }

    public void setReceivedList(RequestDonationList receivedList) {
        this.receivedList = receivedList;
    }

    public void resetReceivedList()
    {
        this.receivedList.reset();
    }

    public void resetRequestsList()
    {
        this.requestsList.reset();
    }

    public void showRequests()
    {
        if(requestsList.rdEmpty())
        {
            System.out.println("\nBeneficiary " + name + " currently has no requests available.");
        }
        else
        {
            for(int i = 0; i < requestsList.size(); i++)
            {
                System.out.println("\n\tID: " + requestsList.getRd(i).getEntity().getId()
                                 + "\t| Name: " + requestsList.getRd(i).getEntity().getName()
                                 + " with " + requestsList.getRd(i).getQuantity() + " amount requested.");
            }
        }
    }

    public void add(RequestDonation rdEnt)
    {
        tempRq.add(rdEnt);
    }

    public Requests getCommitRequests()
    {
        return commitRequests;
    }

    public void resetCommitRequests()
    {
        commitRequests.reset();
    }

    public void commit()
    {
        boolean valid = false;

        for(int i=0; i < tempRq.size(); i++)
        {
            valid = tempRq.validRequestDonation(i, this);

            if(valid == true)
            {
                requestsList.add(tempRq.getRd(i));
                receivedList.add(tempRq.getRd(i));
                commitRequests.add(tempRq.getRd(i));
            }
            else
            {
                System.out.println("\nRequest No. " + i + " for " + tempRq.getRd(i).getEntity().getName() + " with " + tempRq.getRd(i).getQuantity() + " units requested has been denied.");
            }
            System.out.println("\nPlease press ENTER to continue!");
            readLine();
        }

        tempRq.reset();
    }
}


