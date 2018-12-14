package Table;

public class Employee {
    private String employee_ID;
    private String username;
    private String password;
    private String position;
    private String titleName;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNum;

    public Employee(){}

    public Employee(String username, String password, String position, String titleName, String firstName, String lastName, String email, String mobileNum) {
        this.username = username;
        this.password = password;
        this.position = position;
        this.titleName = titleName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNum = mobileNum;
    }

    public Employee(String employee_ID, String username, String password, String position, String titleName, String firstName, String lastName, String email, String mobileNum) {
        this.employee_ID = employee_ID;
        this.username = username;
        this.password = password;
        this.position = position;
        this.titleName = titleName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNum = mobileNum;
    }

    public String getEmployee_ID() {
        return employee_ID;
    }

    public void setEmployee_ID(String employee_ID) {
        this.employee_ID = employee_ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }
}
