public class Requests extends RequestDonationList {
    
    // validRequestDonation() method firstly checks whether the RequestDonation is a Material or a Service.
    //      If the RequestDonation is a Material, it checks if the Beneficiary is allowed to request the specified quantity of the specified material.
    //      If the Beneficiary is allowed to request such quantity, then it checks depending on the number of family members
    //      whether he is allowed to request such quantity based on his already received donations.
    //      Should all the checks succeed, the Request is valid and returns a "true" value back to where the method was called from.
    //      If not all the checks succeed, it prints a message to the terminal about what went wrong and returns "false" value back to where the method was called from.
    //
    //      If the RequestDonation is a Service, then the Request is valid and it returns a "true" value back to where the method was called from.

    public boolean validRequestDonation(int index, Beneficiary beneficiary)
    {
        RequestDonation rqDonation = getRd(index);

        System.out.println("\n-- ATTEMPTING TO COMMIT REQUEST NO. " + index + " --");

        if(rqDonation.getEntity().getClass().getSimpleName() == "Material")
        {
            int benefNoPersons = beneficiary.getNoPersons();
            Material mat = (Material) rqDonation.getEntity();

            // Checks quantity for beneficiary with 1 family member
            if(benefNoPersons == 1)
            {
                if(rqDonation.getQuantity() > mat.getLevel1())
                {
                    System.out.println("\nThe quantity you specified is higher than the amount you are allowed to request.");
                    System.out.println("\nYou are allowed to request only " + mat.getLevel1() + " of the specified material.");
                    return false;
                }
                else if(rqDonation.getQuantity() <= mat.getLevel1() && rqDonation.getQuantity() > 0 && beneficiary.getReceivedList().rdEmpty() == false)
                {
                    for(int i=0; i < beneficiary.getReceivedList().size(); i++)
                    {
                        if(beneficiary.getReceivedList().getRd(i).getEntity().equals(mat))
                        {
                            double requestedQuantity = beneficiary.getReceivedList().getRd(i).getQuantity() + rqDonation.getQuantity();

                            if(requestedQuantity > mat.getLevel1())
                            {
                                System.out.println("\nThe quantity you specified is higher than the amount you are allowed to request.");

                                if((mat.getLevel1() - beneficiary.getReceivedList().getRd(i).getQuantity()) <= 0)
                                {
                                    System.out.println("\nYou cannot request any more from this material.");
                                }
                                else
                                {
                                    System.out.println("\nYou can request " + (mat.getLevel1() - beneficiary.getReceivedList().getRd(i).getQuantity()) + " more units from this material.");
                                }
                                return false;
                            }
                            else
                            {
                                System.out.println("\nRequest is valid. \n");
                                return true;
                            }
                        }
                    }
                }
                else if(rqDonation.getQuantity() <= mat.getLevel1() && rqDonation.getQuantity() > 0 && beneficiary.getReceivedList().rdEmpty() == true)
                {
                    System.out.println("\nRequest is valid. \n");
                    return true;
                }
            }
            // Checks quantity for beneficiary with 2-4 family members
            else if(benefNoPersons >= 2 && benefNoPersons <= 4)
            {
                if(rqDonation.getQuantity() > mat.getLevel2())
                {
                    System.out.println("\nThe quantity you specified is higher than the amount you are allowed to request.");
                    System.out.println("\nYou are allowed to request only " + mat.getLevel2() + " of the specified material.");
                    return false;
                }
                else if(rqDonation.getQuantity() <= mat.getLevel2() && rqDonation.getQuantity() > 0 && beneficiary.getReceivedList().rdEmpty() == false)
                {
                    for(int i=0; i < beneficiary.getReceivedList().size(); i++)
                    {
                        if(beneficiary.getReceivedList().getRd(i).getEntity().equals(mat))
                        {
                            double requestedQuantity = beneficiary.getReceivedList().getRd(i).getQuantity() + rqDonation.getQuantity();

                            if(requestedQuantity > mat.getLevel2())
                            {
                                System.out.println("\nThe quantity you specified is higher than the amount you are allowed to request.");

                                if((mat.getLevel2() - beneficiary.getReceivedList().getRd(i).getQuantity()) <= 0)
                                {
                                    System.out.println("\nYou cannot request any more from this material.");
                                }
                                else
                                {
                                    System.out.println("\nYou can request " + (mat.getLevel2() - beneficiary.getReceivedList().getRd(i).getQuantity()) + " more units from this material.");
                                }
                                return false;
                            }
                            else
                            {
                                System.out.println("\nRequest is valid. \n");
                                return true;
                            }
                        }
                    }
                }
                else if(rqDonation.getQuantity() <= mat.getLevel2() && rqDonation.getQuantity() > 0 && beneficiary.getReceivedList().rdEmpty() == true)
                {
                    System.out.println("\nRequest is valid. \n");
                    return true;
                }
            }
            // Checks quantity for beneficiary with more than 4 family members
            else if(benefNoPersons > 4)
            {
                if(rqDonation.getQuantity() > mat.getLevel3())
                {
                    System.out.println("\nThe quantity you specified is higher than the amount you are allowed to request.");
                    System.out.println("\nYou are allowed to request only " + mat.getLevel3() + " of the specified material.");
                    return false;
                }
                else if(rqDonation.getQuantity() <= mat.getLevel3() && rqDonation.getQuantity() > 0 && beneficiary.getReceivedList().rdEmpty() == false)
                {
                    for(int i=0; i < beneficiary.getReceivedList().size(); i++)
                    {
                        if(beneficiary.getReceivedList().getRd(i).getEntity().equals(mat))
                        {
                            double requestedQuantity = beneficiary.getReceivedList().getRd(i).getQuantity() + rqDonation.getQuantity();

                            if(requestedQuantity > mat.getLevel3())
                            {
                                System.out.println("\nThe quantity you specified is higher than the amount you are allowed to request.");

                                if((mat.getLevel3() - beneficiary.getReceivedList().getRd(i).getQuantity()) <= 0)
                                {
                                    System.out.println("\nYou cannot request any more from this material.");
                                }
                                else
                                {
                                    System.out.println("\nYou can request " + (mat.getLevel3() - beneficiary.getReceivedList().getRd(i).getQuantity()) + " more units from this material.");
                                }
                                return false;
                            }
                            else
                            {
                                System.out.println("\nRequest is valid. \n");
                                return true;
                            }
                        }
                    }
                }
                else if(rqDonation.getQuantity() <= mat.getLevel3() && rqDonation.getQuantity() > 0 && beneficiary.getReceivedList().rdEmpty() == true)
                {
                    System.out.println("\nRequest is valid. \n");
                    return true;
                }
            }
        }
        else
        {
            System.out.println("\nRequest is valid. \n");
            return true;
        }
        return false;
    }
}
