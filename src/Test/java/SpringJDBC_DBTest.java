import Table.Customer;
import Table.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static tourSaleManagementSystemUtil.DisplayGUIUtil.manageableDatabase;

class SpringJDBC_DBTest {
    Employee emp = new Employee("EMP000004","5910406451","5910406451","sale","Miss","Wipawadee","Monkhut","wipawadee.mo@ku.th","0831653904");
    @Test
    public void testGetNameEmployeeFromDatabase(){
        assertEquals("Miss Thikamporn Simud",manageableDatabase.getNameEmployee("EMP000001"));
        assertEquals("Miss Piyawad Namtachan",manageableDatabase.getNameEmployee("EMP000002"));
        assertEquals("Miss Kewalee Boonyamarn",manageableDatabase.getNameEmployee("EMP000003"));
        assertEquals("Miss Wipawadee Monkhut",manageableDatabase.getNameEmployee("EMP000004"));
    }

    @Test
    public void testGetTourID(){
        assertEquals("GER-5D4N-000001",manageableDatabase.getTourID("ROMANTIC OF GERMANY-CZECH"));
        assertEquals("EUR-5D4N-000002",manageableDatabase.getTourID("EXCLUSIVE OF EAST EUROPE"));
        assertEquals("UAE-5D4N-000003",manageableDatabase.getTourID("DUBAI EXCLUSIVE"));
        assertEquals("JPN-4D3N-000004",manageableDatabase.getTourID("JAPAN NEW YEAR EXCLUSIVE"));
    }

    @Test
    public void testGetTourPrice(){
        assertEquals(175500,manageableDatabase.getTourPrice("GER-5D4N-000001"));
        assertEquals(198800,manageableDatabase.getTourPrice("EUR-5D4N-000002"));
        assertEquals(219900,manageableDatabase.getTourPrice("UAE-5D4N-000003"));
        assertEquals(115900,manageableDatabase.getTourPrice("JPN-4D3N-000004"));
    }

    @Test
    public void testGetAvailableSeatByTourID(){
        assertEquals(40,manageableDatabase.getAvailableByTourID("GER-5D4N-000001"));
        assertEquals(50,manageableDatabase.getAvailableByTourID("EUR-5D4N-000002"));
        assertEquals(0,manageableDatabase.getAvailableByTourID("UAE-5D4N-000003"));
        assertEquals(45,manageableDatabase.getAvailableByTourID("JPN-4D3N-000004"));
    }

    @Test
    public void testGetNameCustomer(){
        assertEquals("Miss Pilin Boonchom",manageableDatabase.getNameCustomer("CS00000013"));
        assertEquals("Mr. Khalid Robinson",manageableDatabase.getNameCustomer("CS00000001"));
        assertEquals("Mr. Shawn Mendes",manageableDatabase.getNameCustomer("CS00000002"));
        assertEquals("Mr. Charlie Puth",manageableDatabase.getNameCustomer("CS00000003"));
        assertEquals("Miss Mali Songlin",manageableDatabase.getNameCustomer("CS00000017"));
        assertEquals("Miss Sirirattana Monkhut",manageableDatabase.getNameCustomer("CS00000020"));
    }

    @Test
    public void testGetFirstNameCustomer(){
        assertEquals("Pilin",manageableDatabase.getFirstNameCustomer("CS00000013"));
        assertEquals("Khalid",manageableDatabase.getFirstNameCustomer("CS00000001"));
        assertEquals("Shawn",manageableDatabase.getFirstNameCustomer("CS00000002"));
        assertEquals("Charlie",manageableDatabase.getFirstNameCustomer("CS00000003"));
        assertEquals("Mali",manageableDatabase.getFirstNameCustomer("CS00000017"));
        assertEquals("Sirirattana",manageableDatabase.getFirstNameCustomer("CS00000020"));
    }

}