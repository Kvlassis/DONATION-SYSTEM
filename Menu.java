import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Menu {
    Organization org = new Organization();

    // FIRST MENU - For authentication purposes

    public void loginMenu(Organization org1)
    {
        this.org = org1;
        String phNum;
        Boolean usersEmpty = true;
        List<String> donatorListPh = new ArrayList();
        List<String> beneficiaryListPh = new ArrayList();

        // Fetching the phone numbers of all beneficiaries/donators of the organization
        // Also checks if all lists are empty or not
        
        if(org.getDonators().isEmpty() && org.getAdmin() == null && org.getBeneficiaries().isEmpty())
        {
            usersEmpty = true;
        }
        else
        {
            usersEmpty = false;
            for(int i = 0; i < org.getDonators().size(); i++)
            {
                donatorListPh.add(org.getDonators().get(i).getPhoneNumber());
            }
            for(int i = 0; i < org.getBeneficiaries().size(); i++)
            {
                beneficiaryListPh.add(org.getBeneficiaries().get(i).getPhoneNumber());
            }
        }

        // AUTHENTICATION PROCESS

        System.out.println("\nEnter your phone number: ");
        phNum = readLine(); 

        if(!usersEmpty && org.getAdmin() != null && phNum.equals(org.getAdmin().getPhoneNumber()))
        {
            adminMenu(org.getAdmin());
        }
        else if(donatorListPh.contains(phNum))
        {
            for(int i = 0; i < org.getDonators().size(); i++)
            {
                if(org.getDonators().get(i).getPhoneNumber().equals(phNum))
                {
                    donatorMenu(org.getDonators().get(i));
                }
            }
        }
        else if(!usersEmpty && beneficiaryListPh.contains(phNum))
        {
            for(int i = 0; i < org.getBeneficiaries().size(); i++)
            {
                if(org.getBeneficiaries().get(i).getPhoneNumber().equals(phNum))
                {
                    beneficiaryMenu(org.getBeneficiaries().get(i));
                }
            }
        }
        else
        {
            System.out.println("\nNo user with the specificed phone number has be found." 
                            + "\nDo you want to register? (Y/N)\n");
            
            String regChoice = readLine();
            
            if(regChoice.equals("Y") || regChoice.equals("y"))
            {
                noUser();
            }
            else if(regChoice.equals("N") || regChoice.equals("n"))
            {
                System.exit(0);
            }
        }

    }

    // ADMIN MENU - Shows up after authentication of an ADMIN in the Organization

    public void adminMenu(Admin admin)
    {
        boolean neverEnd = false;
        do
        {
            neverEnd = true;
            // System.out.print("\033[H\033[2J");  
            // System.out.flush();
            // System.out.println(org.listEntities().get(0).getClass().getSimpleName());
            System.out.println("\n\n***********************************"
                            +    "\n*          ADMIN MENU             *"
                            +    "\n***********************************"
                            +    "\nName: " + admin.getName()
                            +    "\nPhone Number: " + admin.getPhoneNumber()
                            +    "\nAdmin Status: YES"
                            +    "\n***********************************"
                            +    "\n*       AVAILABLE OPTIONS         *"
                            +    "\n***********************************"
                            +    "\n1) View Offers available in Organization"
                            +    "\n2) Monitor Organization Information"
                            +    "\n3) Go back"
                            +    "\n4) Logout"
                            +    "\n5) Exit the application"
                            +    "\n\nYour choice: ");
            
            Short choice = Short.parseShort(readLine());

            switch(choice)
            {
                case 1:
                    int numOfMats = 0, numOfServices = 0;
                    List<Entity> matsList = new ArrayList();
                    List<Entity> servicesList = new ArrayList();

                    // System.out.print("\033[H\033[2J");  
                    // System.out.flush();
                    
                    for(int i = 0; i < org.listEntities().size(); i++)
                    {
                        if(org.listEntities().get(i).getClass().getSimpleName() == "Material")
                        {
                            matsList.add(org.listEntities().get(i));
                        }
                        else
                        {
                            servicesList.add(org.listEntities().get(i));
                        }
                    }
                    System.out.println("\n***********************************"
                                  +    "\n*       ENTITY CATEGORIES         *"
                                  +    "\n***********************************"
                                  +    "\n1) Materials - (" + matsList.size() + ") materials available");
                    for(int i = 0; i < matsList.size(); i++)
                    {
                        System.out.println("\n\t" + i + "\t| ID: "
                                         + matsList.get(i).getId() + "\t| Name: "
                                         + matsList.get(i).getName());
                    }
                    System.out.println("\n2) Services - (" + servicesList.size() + ") services available");
                    for(int i = 0; i < servicesList.size(); i++)
                    {
                        System.out.println("\n\t" + i + "\t| ID: "
                                         + servicesList.get(i).getId() + "\t| Name: "
                                         + servicesList.get(i).getName());
                    }
                    break;
                case 2:
                    adminMonitorOrg();
                    break;
                case 3:
                    loginMenu(org);
                    break;
                case 4:
                    loginMenu(org);
                    break;
                case 5:
                    System.exit(0);
                default:
                    // EXCEPTION (PROBABLY)
            }
        }while(neverEnd);

    }

    public void adminMonitorOrg()
    {
        System.out.println("\n***********************************"
                      +    "\n*      MONITOR ORGANIZATION       *"
                      +    "\n***********************************"
                      +    "\n1) List Beneficiaries of the Organization"
                      +    "\n2) List Donators of the Organization"
                      +    "\n3) Reset received donations of Beneficiaries"
                      +    "\n4) Go back");
        System.out.println("\n\nEnter your choice: ");
        
        switch(Integer.parseInt(readLine()))
        {
            case 1:
                listBeneficiaries();
                break;
            case 2:
                listDonators();
                break;
            case 3:
                org.resetAllBeneficiaries();
                System.out.println("\n-- ALL RECEIVED LISTS OF BENEFICIARIES HAVE BEEN RESET --\n\n");
                adminMonitorOrg();
                break;
            case 4:
                adminMenu(org.getAdmin());
                break;
            default:
        }
    }

    public void listBeneficiaries()
    {
        int choice;

        List<Beneficiary> beneficiaryList = org.getBeneficiaries();

        org.listBeneficiaries();

        System.out.println("\nChoose a Beneficiary to show more information/modify."
                        +  "\nType '-1' to go back."
                        +  "\n\nYour choice: ");

        choice = Integer.parseInt(readLine());
        
        if(choice == -1)
        {
            adminMonitorOrg();
        }
        else
        {
            showBeneficiary(beneficiaryList.get(choice));
        }

    }

    public void showBeneficiary(Beneficiary b)
    {
        for(int i = 0; i < b.getReceivedList().size(); i++)
        {
            System.out.println(b.getReceivedList().get(i));
        }

        System.out.println("\n\n1) Clean up the received donations of this beneficiary"
                         + "\n2) Remove this beneficiary from the organization"
                         + "\n3) Go back");

        short choice = Short.parseShort(readLine());

        switch(choice)
        {
            case 1:
                int temp = org.getBeneficiaries().indexOf(b);
                b.resetReceivedList();
                org.modifyBeneficiary(temp, b);
                listBeneficiaries();
            case 2:
                org.removeBeneficiary(b);
                listBeneficiaries();
            case 3:
                listBeneficiaries();
        }

    }



    public void listDonators()
    {

        int choice;

        List<Donator> donatorList = org.getDonators();

        org.listDonators();

        System.out.println("\nChoose a Donator to show more available offers or delete."
                        +  "\nType '-1' to go back."
                        +  "\n\nYour choice: ");

        choice = Integer.parseInt(readLine());
        
        if(choice == -1)
        {
            adminMonitorOrg();
        }
        else
        {
            showDonator(donatorList.get(choice));
        }
    }

    public void showDonator(Donator d)
    {
        d.showOffers();

        System.out.println("\n\nDo you want to delete this donator from the organization? (Y/n)");

        char choice = (readLine().charAt(0));

        if(choice == 'Y' || choice == 'y')
        {
            org.removeDonator(d);
            System.out.println("\n -- DONATOR " + d.getName() + " HAS BEEN REMOVED FROM THE ORGANIZATION --");
            listDonators();
        }
        else if(choice == 'N' || choice == 'n')
        {
            listDonators();
        }
        else
        {
            System.out.println("\nWrong option...");
        }
    }
    
    // DONATOR MENU - Shows up after authentication or registration of DONATOR in the Organization

    public void donatorMenu(Donator donator)
    {
        int temp = org.getDonators().indexOf(donator);
        boolean neverEnd = false;
        do
        {
            System.out.println("\n\n***********************************"
                            +    "\n*          DONATOR MENU           *"
                            +    "\n***********************************"
                            +    "\nName: " + donator.getName()
                            +    "\nPhone Number: " + donator.getPhoneNumber()
                            +    "\nOrganization Name: " + org.getName()
                            +    "\n***********************************"
                            +    "\n*       AVAILABLE OPTIONS         *"
                            +    "\n***********************************"
                            +    "\n1) Add Offer"
                            +    "\n2) Show Offers"
                            +    "\n3) Commit"
                            +    "\n4) Go Back"
                            +    "\n5) Logout"
                            +    "\n6) Exit the application"
                            +    "\n\nYour choice: ");


            Short choice = Short.parseShort(readLine());

            switch(choice)
            {
                case 1:
                    donorAddOffer(donator);
                    break;
                case 2:
                    donorShowOffers(donator);
                    break;
                case 3:
                    donator.commit();
                    org.updateRd(donator.getCommitOffers(), "offer");
                    donator.resetCommitOffers();
                    org.modifyDonator(temp, donator);
                    donatorMenu(donator);
                    break;
                case 4:
                    loginMenu(org);
                    break;
                case 5:
                    logoutMenu();
                    break;
                case 6:
                    System.exit(0);
                default:
                    // EXCEPTION (PROBABLY)
            }
        }while(neverEnd);

            
    }

    public void donorAddOffer(Donator donator)
    {
        int numOfMats = 0, numOfServices = 0;
        List<Material> matsList = new ArrayList();
        List<Service> servList = new ArrayList();

        // System.out.print("\033[H\033[2J");  
        // System.out.flush();
        
        for(int i = 0; i < org.listEntities().size(); i++)
        {
            if(org.listEntities().get(i).getClass().getSimpleName() == "Material")
            {
                matsList.add((Material) org.listEntities().get(i));
            }
            else
            {
                servList.add((Service) org.listEntities().get(i));
            }
        }
        System.out.println("\n***********************************"
                      +    "\n*       ENTITY CATEGORIES         *"
                      +    "\n***********************************"
                      +    "\n1) Materials - (" + matsList.size() + ") materials available");
        for(int i = 0; i < matsList.size(); i++)
        {
            System.out.println("\n\t" + i + "\t| ID: "
                             + matsList.get(i).getId() + "\t| Name: "
                             + matsList.get(i).getName());
        }
        System.out.println("\n2) Services - (" + servList.size() + ") services available");
        for(int i = 0; i < servList.size(); i++)
        {
            System.out.println("\n\t" + i + "\t| ID: "
                             + servList.get(i).getId() + "\t| Name: "
                             + servList.get(i).getName());
        };

        System.out.println("\n\nChoose either: "
                          +"\n1) Show Materials "
                          +"\n2) Show Services "
                          +"\n3) Go back");

        short choice = Short.parseShort(readLine());

        switch(choice)
        {
            case 1:
                showMaterials(matsList, donator);
                break;
            case 2:
                showServices(servList, donator);
                break;
            case 3:
                donatorMenu(donator);
                break;
            default:
        }


    }

    // Method to show Materials and select one

    public void showMaterials(List<Material> matsList, Donator donator)
    {
        int choice;

        System.out.println("\n\nMATERIALS:");
        
        for(int i = 0; i < matsList.size(); i++)
        {
            System.out.println("\n\t" + i + "\t| ID: "
                             + matsList.get(i).getId() + "\t| Name: "
                             + matsList.get(i).getName());
        }

        do
        {
            System.out.println("\n\nChoose a Material to show more information about or go back (-1): ");

            choice = Integer.parseInt(readLine());

            if(choice < matsList.size() && choice > -1)
            {
                showEnt(matsList.get(choice), donator);
            }
            else if(choice == -1)
            {
                donorAddOffer(donator);
            }
            else
            {
                System.out.println("\nIncorrect option. Please enter a correct option.");
            }

        }while(choice >= matsList.size() || choice < -1);

    }

    // Method to show Services and select one

    public void showServices(List<Service> servList, Donator donator)
    {
        int choice;

        System.out.println("\n\nSERVICES:");
        
        for(int i = 0; i < servList.size(); i++)
        {
            System.out.println("\n\t" + i + "\t| ID: "
                             + servList.get(i).getId() + "\t| Name: "
                             + servList.get(i).getName());
        }

        do
        {
            System.out.println("\n\nChoose a Service to show more information about or go back (-1): ");

            choice = Integer.parseInt(readLine());

            if(choice < servList.size() && choice > -1)
            {
                showEnt(servList.get(choice), donator);
            }
            else if(choice == -1)
            {
                donorAddOffer(donator);
            }
            else
            {
                System.out.println("\nIncorrect option. Please enter a correct option.");
            }

        }while(choice >= servList.size() || choice < -1);

    }

    public void showEnt(Entity ent, Donator donator)
    {
        ent.getEntityInfo();
        ent.getDetails();

        boolean neverEnd = true;

        System.out.println("\nDo you want to offer the selected entity? (Y/n)");

        do
        {
            char choice = readLine().charAt(0);

            if(choice == 'Y' || choice == 'y')
            {
                addOffer(ent, donator);
            }
            else if(choice == 'N' || choice == 'n')
            {
                donorAddOffer(donator);
            }
            else
            {
                System.out.println("\nIncorrect option. Please enter Y/y or N/n.");
            }
        }while(neverEnd);
    }

    public void addOffer(Entity entity, Donator donator)
    {
        RequestDonation rdEnt;

        if(entity.getClass().getSimpleName() == "Material")
        {
            System.out.println("\n\nPlease type how many units of the specified material you want to offer: ");
        }
        else if(entity.getClass().getSimpleName() == "Service")
        {
            System.out.println("\n\nPlease type how many hours of the specified service you want to offer: ");
        }

        rdEnt = new RequestDonation(entity, Double.parseDouble(readLine()));

        donator.add(rdEnt);

        donorAddOffer(donator);
    }

    public void donorShowOffers(Donator donator)
    {
        int temp = org.getDonators().indexOf(donator);

        boolean neverEnd = true;

        do
        {
            System.out.println("\n***********************************"
                          +    "\n*       CURRENTLY OFFERING        *"
                          +    "\n***********************************");


            donator.showOffers();

            System.out.println("\n\nChoose an offer ID to modify.");
            System.out.println("\n\t   or");

            System.out.println("\n\t   a) Reset all of your offers"
                          +    "\n\t   b) Commit all your changes"
                          +    "\n\t   c) Go back"
                          +    "\n\nYour choice: ");

            String choice = readLine();

            if(isNumeric(choice) == true && donator.getOffersList().rdEmpty() == false)
            {
                int offerChoice = Integer.parseInt(choice);

                if(offerChoice > 0 && offerChoice <= donator.getOffersList().size())
                {
                    donator.getOffersList().get(offerChoice);
                }
                else
                {
                    System.out.println("\nERROR: Specified offer does not exist! Please try another option.\n");
                }
            }
            else if(isNumeric(choice) == false)
            {
                char menuChoice = choice.charAt(0);

                switch(menuChoice)
                {
                    case 'a':
                        donator.resetOffersList();
                        org.modifyDonator(temp, donator);
                        System.out.println("\n-- Your offers list has been cleaned up --\n");
                        break;
                    case 'b':
                        System.out.println("\n-- COMMIT --\n");
                        donator.commit();
                        org.updateRd(donator.getCommitOffers(), "offer");
                        donator.resetCommitOffers();
                        org.modifyDonator(temp, donator);
                        break;
                    case 'c':
                        donatorMenu(donator);
                        break;
                    default:
                        System.out.println("Incorrect option. Please enter a valid option.");
                }
            }
        }while(neverEnd);
        
    }

    // BENEFICIARY MENU - Shows up after authentication or registration of BENEFICIARY in the Organization

    public void beneficiaryMenu(Beneficiary beneficiary)
    {
        boolean neverEnd = true;
        int temp = org.getBeneficiaries().indexOf(beneficiary);
        do
        {
            System.out.println("\n\n***********************************"
                            +    "\n*        BENEFICIARY MENU         *"
                            +    "\n***********************************"
                            +    "\nName: " + beneficiary.getName()
                            +    "\nPhone Number: " + beneficiary.getPhoneNumber()
                            +    "\nFamily Members: " + beneficiary.getNoPersons()
                            +    "\nOrganization Name: " + org.getName()
                            +    "\n***********************************"
                            +    "\n*       AVAILABLE OPTIONS         *"
                            +    "\n***********************************"
                            +    "\n1) Add Request"
                            +    "\n2) Show Requests"
                            +    "\n3) Commit"
                            +    "\n4) Go Back"
                            +    "\n5) Logout"
                            +    "\n6) Exit the application"
                            +    "\n\nYour choice: ");


            Short choice = Short.parseShort(readLine());

            switch(choice)
            {
                case 1:
                    benefAddRequest(beneficiary);
                    break;
                case 2:
                    benefShowRequests(beneficiary);
                    break;
                case 3:
                    beneficiary.commit();
                    org.updateRd(beneficiary.getCommitRequests(), "request");
                    beneficiary.resetCommitRequests();
                    org.modifyBeneficiary(temp, beneficiary);
                    beneficiaryMenu(beneficiary);
                    break;
                case 4:
                    loginMenu(org);
                    break;
                case 5:
                    logoutMenu();
                    break;
                case 6:
                    System.exit(0);
                default:
                    // EXCEPTION (PROBABLY)
            }
        }while(neverEnd);

            
    }

    public void benefAddRequest(Beneficiary beneficiary)
    {
        RequestDonation rdEnt;
        int choice;
        boolean neverEnd = true;

        System.out.println("\n***********************************"
                      +    "\n*       CURRENT DONATIONS         *"
                      +    "\n***********************************");
        
        for(int i=0; i < org.getCurrentDonations().size(); i++)
        {
            System.out.println("\n\tID: " + org.getCurrentDonations().getRd(i).getEntity().getId()
                             + "\t| Name: " + org.getCurrentDonations().getRd(i).getEntity().getName()
                             + " with " + org.getCurrentDonations().getRd(i).getQuantity() + " units available.");
        }

        do
        {
            
            System.out.println("\n\nPlease choose an ID of a Donation or type '0' to go back.");

            choice = Integer.parseInt(readLine());

            if(choice == 0)
            {
                beneficiaryMenu(beneficiary);
            }
            else if(choice > 0)
            {
                rdEnt = org.getCurrentDonations().get(choice);

                if(rdEnt == null)
                {
                    System.out.println("\nPlease enter a valid ID from the ones specified above.");
                }
                else
                {
                    showRD(rdEnt, beneficiary);
                }
            }
        }while(neverEnd);
    }

    public void showRD(RequestDonation rdEnt, Beneficiary beneficiary)
    {
        boolean neverEnd = true;

        if(rdEnt.getEntity().getClass().getSimpleName() == "Material")
        {
            Material mat = (Material) rdEnt.getEntity();
            mat.getEntityInfo();
            mat.getDetails();
        }
        else if(rdEnt.getEntity().getClass().getSimpleName() == "Service")
        {
            Service serv = (Service) rdEnt.getEntity();
            serv.getEntityInfo();
            serv.getDetails();
        }

        System.out.println("\nQuantity available: " + rdEnt.getQuantity());
        System.out.println("\nDo you want to request the selected entity? (Y/n)");

        do
        {
            char choice = readLine().charAt(0);

            if(choice == 'Y' || choice == 'y')
            {
                addRequest(rdEnt, beneficiary);
            }
            else if(choice == 'N' || choice == 'n')
            {
                benefAddRequest(beneficiary);
            }
            else
            {
                System.out.println("\nIncorrect option. Please enter Y/y or N/n.");
            }
        }while(neverEnd);
    }

    public void addRequest(RequestDonation entity, Beneficiary beneficiary)
    {
        RequestDonation rdEnt;

        if(entity.getEntity().getClass().getSimpleName() == "Material")
        {
            System.out.println("\n\nPlease type how many units of the specified material you want to request: ");
        }
        else if(entity.getEntity().getClass().getSimpleName() == "Service")
        {
            System.out.println("\n\nPlease type how many hours of the specified service you want to request: ");
        }

        rdEnt = new RequestDonation(entity.getEntity(), Double.parseDouble(readLine()));

        if(entity.getQuantity() < rdEnt.getQuantity())
        {
            System.out.println("\nSpecified quantity of the donation exceeds what's currently available in the organization.");
            System.out.println("\nCurrently the available quantity of the donation is " + entity.getQuantity());
        }
        else if(rdEnt.getQuantity() <= entity.getQuantity() && rdEnt.getQuantity() > 0)
        {
            beneficiary.add(rdEnt);
            System.out.println("\nAdded specified donation to the requests.");
        }
        else
        {
            System.out.println("\nERROR: Incorrent amount of quantity. Returning to CURRENT DONATIONS menu...\n");
        }

            benefAddRequest(beneficiary);
    }
        

    public void benefShowRequests(Beneficiary beneficiary)
    {
        boolean neverEnd = true;

        do
        {
            System.out.println("\n***********************************"
                          +    "\n*      CURRENTLY REQUESTING       *"
                          +    "\n***********************************");

            beneficiary.showRequests();

            System.out.println("\n\nChoose a request ID to modify.");
            System.out.println("\n\t   or");
            System.out.println("\n\t   a) Reset all of your requests"
                          +    "\n\t   b) Commit all your changes"
                          +    "\n\t   c) Go back"
                          +    "\n\nYour choice: ");

            String choice = readLine();

            if(isNumeric(choice) == true && beneficiary.getRequestsList().rdEmpty() == false)
            {
                int requestChoice = Integer.parseInt(choice);

                if(requestChoice >= 0 && requestChoice < beneficiary.getRequestsList().size())
                {
                    beneficiary.getRequestsList().get(requestChoice);
                }
                else
                {
                    System.out.println("\nERROR: Specified request does not exist! Please try another option.\nPress ENTER to continue...");
                    readLine();
                }
            }
            else if(isNumeric(choice) == false)
            {
                char menuChoice = choice.charAt(0);
                int temp = org.getBeneficiaries().indexOf(beneficiary);

                switch(menuChoice)
                {
                    case 'a':
                        beneficiary.resetRequestsList();
                        org.modifyBeneficiary(temp, beneficiary);

                        System.out.println("\n-- Your requests list has been cleaned up --\n");
                        break;
                    case 'b':
                        System.out.println("\n-- COMMIT --\n");
                        beneficiary.commit();
                        org.updateRd(beneficiary.getCommitRequests(), "request");
                        beneficiary.resetCommitRequests();
                        org.modifyBeneficiary(temp, beneficiary);
                        beneficiaryMenu(beneficiary);
                        break;
                    case 'c':
                        beneficiaryMenu(beneficiary);
                        break;
                    default:
                        System.out.println("Incorrect option. Please enter a valid option.");
                }
            }
        }while(neverEnd);
        
    }

    // REGISTRATION MENU - Shows up after the user's input does not match any of the phone numbers of any
    //                     users in the donatorList, beneficiaryList or admin attributes of Organization

    public void noUser()
    {
        String name, phone;
        Boolean validInput;

        // Will keep repeating until option 1, 2 or 3 are chosen.

        do
        {
            System.out.println("\nWhat do you want to register as?"
            + "\nYou can register as: 1. Donator | 2. Beneficiary"
            + "\nPlease choose one of the mentioned options, otherwise type 3 and press ENTER to quit."
            + "\nYour choice: ");

            short choice = (Short) Short.parseShort(readLine());

            if(choice == 1 || choice == 2 || choice == 3)
            {
                validInput = true;


                // Switch to handle inserts in donationList or beneficiaryList of Organization
                // Also logins to the menu of the newly created User
                // case 3 terminates the application
                
                switch(choice)
                {
                    case 1:
                        System.out.println("\n\nPlease enter your full name: ");
                        name = readLine();
                        System.out.println("\nPlease enter your phone number: ");
                        phone = readLine();

                        Donator newDonor = new Donator(name, phone);
                        org.insertDonator(newDonor);

                        donatorMenu(newDonor);
                        break;
                    case 2:
                        short noPersons;

                        System.out.println("\n\nPlease enter your full name: ");
                        name = readLine();
                        System.out.println("\nPlease enter your phone number: ");
                        phone = readLine();

                        System.out.println("\nPlease enter the number of people in your family: ");
                        noPersons = Short.parseShort(readLine());

                        Beneficiary newBenef = new Beneficiary(name, phone, noPersons);
                        org.insertBeneficiary(newBenef);
                        
                        beneficiaryMenu(newBenef);
                        break;
                    case 3:
                        System.out.print("\n\nQUITTING APPLICATION!");
                        System.exit(0);
                };
            }
            else
            {
                System.out.println("\n\nINVALID OPTION! Please enter a valid option...");
                validInput = false;
            }
        }while (!validInput);
    }

    // LOGOUT MENU - Shows up after the logged in user has decided to logout from the application

    public void logoutMenu()
    {
        String choice;
        Boolean neverEnd = true;

        System.out.println("\n\nLogged out!");
        
        // The code below will keep repeating until user enters Y/y or N/n

        do
        {
            System.out.println("\n\nDo you want to login as another user? (Y/N)"
                         + "\nYour choice: ");
                         
            choice = readLine();
        
            if(choice.equals("Y") || choice.equals("y"))
            {
                loginMenu(org);
            }
            else if(choice.equals("N") || choice.equals("n"))
            {
                System.out.println("\n\nExitting application...");
                System.exit(0);
            }
            else
            {
                System.out.println("\nIncorrect choice, please enter Y/y or N/n");
            }
        }while(neverEnd);

    }

    // Method to read user input so we don't have to create a new scanner for each method above

    public String readLine()
    {
        Scanner sc1 = new Scanner(System.in);
        return sc1.nextLine();
    }

    // Method for checking whether a string (such as user input) is numeric or not.

    public static boolean isNumeric(String string) {
        int intValue;
            
        System.out.println(String.format("Parsing string: \"%s\"", string));
            
        if(string == null || string.equals("")) {
            System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }
        
        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }

    public void test()
    {
        System.out.println("Test");
    }
}
