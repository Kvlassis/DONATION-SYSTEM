public class Main {

    public static void main(String[] args) 
    {
        Menu menu1 = new Menu();
        Organization org1 = new Organization("Blizzard");

        Material mat1 = new Material("Cookies", "Chip-chocolate Cookies", 1, 25, 100, 300);
        Material mat2 = new Material("Rice", "White rice", 2, 200, 400, 750);
        Material mat3 = new Material("Milk", "Chocolate-milk", 3, 2, 4, 6);
        Service serv1 = new Service("Medical Support", "Provide Medical Support", 4);
        Service serv2 = new Service("Nursery Support", "Provide Nursery Support", 5);
        Service serv3 = new Service("Baby sitting", "Provide baby sitting", 6);


        Donator don1 = new Donator("Manuel Dagalidis", "6945608206");
        Beneficiary ben1 = new Beneficiary("John Statham", "6931153757", 3);
        Beneficiary ben2 = new Beneficiary("Mark Humming", "6974214156", 2);
        Admin admin1 = new Admin("Mike Tompkins", "6932239861");

        org1.addEntity(mat1);
        org1.addEntity(mat2);
        org1.addEntity(mat3);
        org1.addEntity(serv1);
        org1.addEntity(serv2);
        org1.addEntity(serv3);
        

        org1.insertDonator(don1);
        org1.insertBeneficiary(ben1);
        org1.insertBeneficiary(ben2);
        org1.setAdmin(admin1);

        menu1.loginMenu(org1);
    }
    
}