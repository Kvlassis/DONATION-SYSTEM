import java.util.ArrayList;
import java.util.List;

public class Donator extends User {
    private Offers offersList = new Offers();
    private Offers commitOffers = new Offers();
    private List<RequestDonation> tempRd = new ArrayList();

    public Donator(String name, String phoneNumber)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Donator(String name, String phoneNumber, Offers offersList)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.offersList = offersList;
    }

    public void showOffers()
    {
        if(offersList.rdEmpty())
        {
            System.out.println("\nDonator " + name + " currently has no offers available.");
        }
        else
        {
            for(int i = 0; i < offersList.getRdList().size(); i++)
            {
                System.out.println("\n\tID: " + offersList.getRd(i).getEntity().getId()
                                 + "\t| Name: " + offersList.getRd(i).getEntity().getName()
                                 + " with " + offersList.getRd(i).getQuantity() + " offers.");
            }
        }
    }

    public Offers getOffersList()
    {
        return offersList;
    }

    public void resetOffersList()
    {
        offersList.reset();
    }

    public Offers getCommitOffers()
    {
        return commitOffers;
    }

    public void resetCommitOffers()
    {
        commitOffers.reset();
    }

    public void add(RequestDonation rdEnt)
    {
        tempRd.add(rdEnt);
    }

    public void commit()
    {
        for(int i=0; i < tempRd.size(); i++)
        {
            offersList.add(tempRd.get(i));
            commitOffers.add(tempRd.get(i));
            System.out.println("\n-- COMMITED OFFER NO. " + i + " --");
            System.out.println("\nPlease press ENTER to continue!");
        }

        tempRd.clear();
    }
}
