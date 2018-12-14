import Table.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    //public Customer(String customerID, String titleNameTH, String firstNameTH, String lastNameTH, String titleNameENG, String firstNameENG, String lastNameENG,
    // String gender, String age, String occupation, String dateOfBirth, String passport_no, String exp_passport, String contactAddress, String cell_phone,
    // String home_Tel, String fax, String email, String disease, String foodAllergy, String eatBeef, String moreDetail, String hearAboutUs) {

    Customer customer1;
    Customer customer2;
    @BeforeEach
    public void setUp(){
        customer1 = new Customer();
        customer2 = new Customer("CS000004","นางสาว","กมลชนก","อาจสมบัติ","Miss","Kamonchanok","Archsombat","Female","25","Flight attendant","23-01-1993","AA340098760","28-05-2020",
                "Bangkok, Thailand","0954600040","-","-","kamolchanok@gmail.com","-","chicken","yes","-","facebook");
    }

    @Test
    public void testGetAndSetCustomerID(){
        assertEquals(null,customer1.getCustomerID());
        assertEquals("CS000004",customer2.getCustomerID());
        // set new ID for the customers.
        customer1.setCustomerID("CS000005");
        customer2.setCustomerID("CS000001");
        assertEquals("CS000005",customer1.getCustomerID());
        assertEquals("CS000001",customer2.getCustomerID());
    }
    @Test
    public void testGetCustomerName(){
        assertEquals("null null",customer1.getFirstNameTH()+" "+ customer1.getLastNameTH());
        assertEquals("กมลชนก อาจสมบัติ",customer2.getFirstNameTH()+" "+ customer2.getLastNameTH());
        assertEquals("null null",customer1.getFirstNameENG()+" "+ customer1.getLastNameENG());
        assertEquals("Kamonchanok Archsombat",customer2.getFirstNameENG()+" "+ customer2.getLastNameENG());
        //set the customer name.
        customer1.setFirstNameTH("ธาดา");
        customer1.setLastNameTH("มารารอง");
        customer1.setFirstNameENG("Thada");
        customer1.setLastNameENG("Mararong");
        assertEquals("ธาดา มารารอง",customer1.getFirstNameTH()+" "+customer1.getLastNameTH());
        assertEquals("Thada Mararong",customer1.getFirstNameENG()+" "+customer1.getLastNameENG());
    }
    @Test
    public void testCustomerAge(){
        assertEquals(null,customer1.getAge());
        assertEquals("25",customer2.getAge());
        //set the new age for the customer1
        customer1.setAge("20");
        assertEquals("20",customer1.getAge());
    }
    @Test
    public void testCustomerDateOfBirth(){
        assertEquals("dd-mm-yyyy",customer1.getDateOfBirth());
        assertEquals("23-01-1993",customer2.getDateOfBirth());
        //set the date of birth for customer1.
        customer1.setDateOfBirth("01-01-1998");
        assertEquals("01-01-1998",customer1.getDateOfBirth());
    }
    @Test
    public void testOccupationOfCustomer(){
        assertEquals("Flight attendant",customer2.getOccupation());
    }

    @Test
    public void testCustomerPassport(){
        assertEquals("AA340098760",customer2.getPassport_no());
        assertEquals("28-05-2020",customer2.getExp_passport());
    }

    @Test void testContactAddress(){
        assertEquals(null, customer1.getContactAddress());
        assertEquals("Bangkok, Thailand",customer2.getContactAddress());
        //set address for the customer1.
        customer1.setContactAddress("Ladprao, Bangkok, Thailand");
        assertEquals("Ladprao, Bangkok, Thailand", customer1.getContactAddress());
    }

    @Test
    public void testCustomerContact(){
        //","-","-","kamolchanok@gmail.com
        assertEquals("0954600040", customer2.getCell_phone());
        assertEquals("-",customer2.getHome_Tel());
        assertEquals("-", customer2.getFax());
        assertEquals("kamolchanok@gmail.com",customer2.getEmail());
    }

    @Test
    public void testCustomerDetail(){
        assertEquals("-",customer2.getDisease());
        assertEquals("chicken",customer2.getFoodAllergy());
        assertEquals("-", customer2.getMoreDetail());
        assertEquals("facebook",customer2.getHearAboutUs());
    }

}