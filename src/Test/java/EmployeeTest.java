import Table.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    Employee manager;
    Employee sale;

    @BeforeEach
    public void setUp(){
        //public Employee(String employee_ID, String username, String password, String position,
        // String titleName, String firstName, String lastName, String email, String mobileNum)

        manager = new Employee("EMP000001","5910401033","1123456","manager","Miss","Thikamporn","Simud","Thikamporn@hotmail.com","0875546387");
        sale = new Employee("5910406451","5910406451","sale","Miss","Wipawadee","Monkut","wipa.m@gmail.com","0899769532");
    }

    @Test
    public void testEmployeeID(){
        assertEquals("EMP000001",manager.getEmployee_ID());
        assertEquals(null,sale.getEmployee_ID());
        //set sale ID
        sale.setEmployee_ID("EMP000004");
        assertEquals("EMP000004",sale.getEmployee_ID());
    }

    @Test
    public void testEmployeeName(){
        assertEquals("Miss Thikamporn Simud",manager.getTitleName()+" "+manager.getFirstName()+" "+manager.getLastName());
        assertEquals("Miss Wipawadee Monkut",sale.getTitleName()+" "+sale.getFirstName()+" "+sale.getLastName());
    }

    @Test
    public void testUsernameAndPassword(){
        assertEquals("5910401033",manager.getUsername());
        assertEquals("1123456",manager.getPassword());
        assertEquals("5910406451",sale.getUsername());
        assertEquals("5910406451",sale.getPassword());
    }

    @Test
    public void testGetContactEmployee(){
        assertEquals("Thikamporn@hotmail.com",manager.getEmail());
        assertEquals("0875546387",manager.getMobileNum());
        assertEquals("wipa.m@gmail.com",sale.getEmail());
        assertEquals("0899769532",sale.getMobileNum());
    }


}