package databaseConnection;

import Table.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static tourSaleManagementSystemUtil.SetTourSaleSystemDataUtil.*;

public class SpringJDBC_DB implements ManageableDatabase {

    private JdbcTemplate jdbcTemplate;

    public SpringJDBC_DB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Employee getEmployeeLogin(String username , String password) {
        Employee employee;

        String query = "Select * From employee Where Username" + " = '" + username + "' and Password = '" + password +"'";
        try {
            employee =  jdbcTemplate.queryForObject(query , new EmployeeRowMapper());
        }
        catch (final EmptyResultDataAccessException e){
            employee = null;
        }
        return employee;
    }

    @Override
    public boolean checkLogin(String username ,String password){
        /*String query = "Select * From employee Where Username = " + username + " and Password = " + password ;*/
        Employee employee = getEmployeeLogin(username, password);
        if (employee == null){
            System.out.println("employee is null");
            return false;
        }
        else
            System.out.println("employee isn't null");
        return true;
    }

    @Override
    public String getNameEmployee(String employeeID) {
        String employeeName;
        String query = "Select * From employee Where Employee_ID = " +"'"+ employeeID+"'";

        Employee employee = jdbcTemplate.queryForObject(query,new EmployeeRowMapper());
        employeeName = employee.getTitleName()+" "+employee.getFirstName()+" "+employee.getLastName();

        return employeeName;
    }


    @Override
    public TourPackage getOneTourPackage(String tourID) {
        String query = "Select * From tour_package Where Tour_ID = "+"'"+tourID+"'";
        TourPackage tourPackage = jdbcTemplate.queryForObject(query,new TourPackageRowMapper());

        return tourPackage;
    }

    @Override
    public String getTourID(String tourName) {
        String tourID = null;
        String query = "Select * From tour_package Where Tour_name = "+"'"+tourName+"'";
        try {
            TourPackage tourPackage = jdbcTemplate.queryForObject(query,new TourPackageRowMapper());
            tourID = tourPackage.getTourID();
            return tourID;
        }
        catch (EmptyResultDataAccessException e){
            return tourID;
        }

    }

    @Override
    public int getTourPrice(String tourID) {

        String query = "Select * From tour_package Where Tour_ID = "+"'"+tourID+"'";
        TourPackage tourPackage = jdbcTemplate.queryForObject(query,new TourPackageRowMapper());

        return tourPackage.getPrice();
    }

    @Override
    public int getAvailableByTourID(String tourID) {

        String query = "Select * From tour_package Where Tour_ID = " +"'"+tourID+"'";
        TourPackage tourPackage = jdbcTemplate.queryForObject(query,new TourPackageRowMapper());
        return tourPackage.getAvailable();
    }

    @Override
    public String getLastTourID() {
        String  query  = "SELECT * FROM tour_package ";
        List<TourPackage> tourPackageList = jdbcTemplate.query(query , new TourPackageRowMapper());

        if(tourPackageList.isEmpty())
            return "0";
        else{
            String lastTourID = tourPackageList.get(tourPackageList.size()-1).getTourID();
            return lastTourID;
        }
    }

    @Override
    public List<TourPackage> getAllTourPackage() {

        String query = "Select * From tour_package";
        List<TourPackage> tourPackageList = jdbcTemplate.query(query , new TourPackageRowMapper());

        return tourPackageList;
    }

    @Override
    public void updateAvailableData(String tourID ,int availableSeat) {
        String updateQuery = "update tour_package set Available = " + availableSeat + " where Tour_ID ="+"'"+tourID+"'";

        jdbcTemplate.update(updateQuery);
    }

    @Override
    public void insertData(Customer customer) {

        String query = "insert into customer (Customer_ID,TitleNameTH, FirstNameTH, LastNameTH, TitleNameENG, FirstNameENG, LastNameENG ," +
                "Gender, Age, Occupation ,Date_of_birth, Passport_no, Expire_passport_date," +
                "Contact_address, Cell_phone, Home_Tel, Fax, Email_address, Disease, Food_allergy, Eat_beef, More_detail, HearAboutUs ) " +
                "values( ?, ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] data = new Object[]{
                customer.getCustomerID(), customer.getTitleNameTH(), customer.getFirstNameTH(), customer.getLastNameTH(),
                customer.getTitleNameENG(), customer.getFirstNameENG(), customer.getLastNameENG(),
                customer.getGender(), customer.getAge(), customer.getOccupation(),
                customer.getDateOfBirth(), customer.getPassport_no(),customer.getExp_passport(),
                customer.getContactAddress(), customer.getCell_phone(),customer.getHome_Tel(),
                customer.getFax(), customer.getEmail(), customer.getDisease(),
                customer.getFoodAllergy(), customer.getEatBeef(), customer.getMoreDetail(),
                customer.getHearAboutUs()};
        jdbcTemplate.update(query, data);
    }

    @Override
    public void updateData(Customer customer) {
        String updateQuery = "update customer set TitleNameTH = ? , FirstNameTH = ?, LastNameTH = ?, TitleNameENG = ?, FirstNameENG = ?, LastNameENG = ?," +
                "Gender = ?, Age = ?, Occupation = ? , Date_of_birth = ?, Passport_no = ?, Expire_passport_date = ?, Contact_address = ?,Cell_phone = ?, Home_Tel = ?, Fax = ?, " +
                "Email_address = ?, Disease = ?, Food_allergy = ?,Eat_beef = ?,More_detail = ?, HearAboutUs = ? where Customer_ID = ?";
        Object[] data = new Object[]{
                customer.getTitleNameTH(), customer.getFirstNameTH(), customer.getLastNameTH(),
                customer.getTitleNameENG(), customer.getFirstNameENG(), customer.getLastNameENG(),
                customer.getGender(), customer.getAge(), customer.getOccupation(),
                customer.getDateOfBirth(), customer.getPassport_no(),customer.getExp_passport(),
                customer.getContactAddress(), customer.getCell_phone(),
                customer.getHome_Tel(), customer.getFax(), customer.getEmail(),
                customer.getDisease(), customer.getFoodAllergy(),
                customer.getEatBeef(), customer.getMoreDetail(), customer.getHearAboutUs(),
                customer.getCustomerID()};
        jdbcTemplate.update(updateQuery, data);
    }

    @Override
    public void deleteData(Customer customer) {
        String deleteQuery = "Delete From customer Where Customer_ID = ?";
        jdbcTemplate.update(deleteQuery, customer.getCustomerID());
    }

    @Override
    public List<Customer> getAllCustomer() {
        String query = "select * from customer";

        List<Customer> customerList = jdbcTemplate.query(query, new CustomerRowMapper());
        return customerList;
    }

    @Override
    public Customer getOneCustomer(String customerID) {

        String query = "select * from customer Where Customer_ID = " +"'"+ customerID+"'";
        Customer customer = jdbcTemplate.queryForObject(query,new CustomerRowMapper());
        return customer;
    }

    @Override
    public String getNameCustomer(String customerID) {

        String customerName;
        String query = "select * from customer Where Customer_ID = " +"'"+ customerID+"'";
        Customer customer = jdbcTemplate.queryForObject(query,new CustomerRowMapper());
        customerName = customer.getTitleNameENG()+" "+customer.getFirstNameENG()+" "+customer.getLastNameENG();
        return customerName;
    }

    @Override
    public String getFirstNameCustomer(String customerID) {
        String customerName;
        String query = "select * from customer Where Customer_ID = " +"'"+ customerID+"'";
        Customer customer = jdbcTemplate.queryForObject(query,new CustomerRowMapper());
        customerName = customer.getFirstNameENG();
        return customerName;
    }

    @Override
    public String getLastCustomerID() {
        List<Customer> listCustomer = getAllCustomer();

        String lastCustomerID;
        if(listCustomer.isEmpty())
            lastCustomerID = "CS00000000";
        else
            lastCustomerID =  listCustomer.get(listCustomer.size()-1).getCustomerID();

        return lastCustomerID;
    }


    @Override
    public void insertData(Reservation reservation) {
        String query = "insert into reservation_customer (Reservation_code,Tour_ID,Tour_name,Customer_ID,Customer_name, Employee_ID,Employee_name)" +
                "values(?, ?, ?, ?, ?, ?, ?)";
        Object[] data = new Object[]{
                reservation.getReservationCode(),
                reservation.getTourID(),
                reservation.getTourName(),
                reservation.getCustomerID(),
                reservation.getCustomerName(),
                reservation.getEmployeeID(),
                reservation.getEmployeeName()};
        jdbcTemplate.update(query, data);

    }

    @Override
    public void updateData(Reservation reservation) {

    }

    @Override
    public void deleteData(Reservation reservation) {
        String deleteQuery = "Delete * From reservation_customer Where Reservation_code = ?";
        jdbcTemplate.update(deleteQuery, reservation.getReservationCode());


    }

    @Override
    public void deleteDataByReserveCode(String reservationCode) {
        String deleteQuery = "Delete From reservation_customer Where Reservation_code = ?";
        jdbcTemplate.update(deleteQuery, reservationCode);
    }

    @Override
    public List<Reservation> getAllReservationByTourID(String tourID) {
        String query = "Select * From reservation_customer Where Tour_ID = " +"'"+ tourID +"'";
        List<Reservation> reservationList = jdbcTemplate.query(query , new ReservationRowMapper());

        return reservationList;
    }

    @Override
    public void insertData(ReservationPayment reservationPayment) {
        String query = "insert into reservation_payment (Reservation_code,Tour_ID,Tour_name,Customer_ID,Customer_name, Employee_ID,Employee_name,Amount_customer,Total_price,Deposit_status,Arrears_status)" +
                "values(?, ?, ?, ? ,? ,?, ?, ?, ?, ?, ?)";
        Object[] data = new Object[]{
                reservationPayment.getReservationCode(),
                reservationPayment.getTourID(),
                reservationPayment.getTourName(),
                reservationPayment.getCustomerID(),
                reservationPayment.getCustomerName(),
                reservationPayment.getEmployeeID(),
                reservationPayment.getEmployeeName(),
                reservationPayment.getAmountCustomer(),
                reservationPayment.getTotal_price(),
                reservationPayment.getDepositStatus(),
                reservationPayment.getArrearsStatus()};
        jdbcTemplate.update(query, data);

    }

    @Override
    public void updateData(ReservationPayment reservationPayment) {
        String updateQuery = "Update reservation_payment Set Reservation_code = ? ,Tour_ID = ?,Tour_name = ?,Customer_ID = ?,Customer_name = ?, Employee_ID = ? ,Employee_name = ?,Amount_customer = ?,Total_price = ?,Deposit_status = ?,Arrears_status = ? Where Reservation_code = ?";

        Object[] data = new Object[]{
                reservationPayment.getReservationCode(),
                reservationPayment.getTourID(),
                reservationPayment.getTourName(),
                reservationPayment.getCustomerID(),
                reservationPayment.getCustomerName(),
                reservationPayment.getEmployeeID(),
                reservationPayment.getEmployeeName(),
                reservationPayment.getAmountCustomer(),
                reservationPayment.getTotal_price(),
                reservationPayment.getDepositStatus(),
                reservationPayment.getArrearsStatus(),
                reservationPayment.getReservationCode()};
        jdbcTemplate.update(updateQuery, data);
    }

    @Override
    public void updateStatusPayment(ReservationPayment reservationPayment) {

        String updateQuery = "Update reservation_payment Set Deposit_status = ?,Arrears_status = ? Where Reservation_code = ?";
        Object[] data = new Object[]{
                reservationPayment.getDepositStatus(),
                reservationPayment.getArrearsStatus(),
                reservationPayment.getReservationCode()};
        jdbcTemplate.update(updateQuery, data);
    }

    @Override
    public void deleteData(ReservationPayment reservationPayment) {

        String deleteQuery = "Delete From reservation_payment Where Reservation_code = ?";
        jdbcTemplate.update(deleteQuery, reservationPayment.getReservationCode());
    }

    @Override
    public ReservationPayment getOneReservationPayment(String reservationCode) {

        String query = "Select * From reservation_payment Where Reservation_code = "+"'"+reservationCode+"'";
        ReservationPayment reservationPayment = new ReservationPayment();
        reservationPayment = jdbcTemplate.queryForObject(query,new ReservationPaymentRowMapper());

        return reservationPayment;
    }

    @Override
    public List<ReservationPayment> getAllReservationPaymentByTourID(String tourID) {
        String query = "Select * From reservation_payment Where Tour_ID = " +"'"+ tourID +"'";
        List<ReservationPayment> reservationPaymentsList = jdbcTemplate.query(query , new ReservationPaymentRowMapper());

        return reservationPaymentsList;
    }

    @Override
    public String getLastReservationPaymentCode() {

        String  query  = "SELECT * FROM reservation_payment ";
        List<Reservation> reservationList = jdbcTemplate.query(query , new ReservationRowMapper());

        if(reservationList.isEmpty())
            return "AAA-AAA-AAA-0000";
        else{
            String lastReservCode = reservationList.get(reservationList.size()-1).getReservationCode();
            return lastReservCode;
        }
    }

    @Override
    public void insertData(Invoice invoice ,String invoiceType) {
        String query = "insert into " + invoiceType + " (Reservation_code, Invoice_no, Tour_ID ,Tour_name,Customer_ID,Customer_name,Employee_ID,Employee_name ,Amount_customer,Total_price ,Invoice_status)" +
                "values(?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?)";

        Object[] data = new Object[]{
                invoice.getReservationCode(),
                invoice.getInvoiceNo(),
                invoice.getTourID(),
                invoice.getTourName(),
                invoice.getCustomerID(),
                invoice.getCustomerName(),
                invoice.getEmployeeID(),
                invoice.getEmployeeName(),
                invoice.getAmountCustomer(),
                invoice.getTotalPrice(),
                invoice.getInvoiceStatus()
        };
        jdbcTemplate.update(query, data);
    }

    @Override
    public void updateData(Invoice invoice ,String invoiceType) {

        String query = "update "+invoiceType+ " set Reservation_code = ? , Invoice_no = ?, Tour_ID = ?,Tour_name = ?,Customer_ID = ?,Customer_name = ?,Employee_ID = ?,Employee_name = ?,Amount_customer = ?,Total_price = ?,Invoice_status = ? Where Reservation_code = ?)";

        Object[] data = new Object[]{
                invoice.getReservationCode(),
                invoice.getInvoiceNo(),
                invoice.getTourID(),
                invoice.getTourName(),
                invoice.getCustomerID(),
                invoice.getCustomerName(),
                invoice.getEmployeeID(),
                invoice.getEmployeeName(),
                invoice.getAmountCustomer(),
                invoice.getTotalPrice(),
                invoice.getInvoiceStatus(),
                invoice.getReservationCode()
        };
        jdbcTemplate.update(query, data);
    }

    @Override
    public void updateCreateInvoiceStatus(Invoice invoice, String invoiceType) {
        String query = "update "+invoiceType+ " set Invoice_status = ? Where Invoice_no = ?";

        Object[] data = new Object[]{
                invoice.getInvoiceStatus(),
                invoice.getInvoiceNo()};
        jdbcTemplate.update(query, data);
    }


    @Override
    public void deleteData(Invoice invoice, String invoiceType) {

        String deleteQuery = "Delete From " + "'"+ invoiceType + "'" + " Where Reservation_code = ?";
        jdbcTemplate.update(deleteQuery, invoice.getReservationCode());
    }

    @Override
    public List<Invoice> getAllInvoice(String invoiceType) {

        String query = "Select * From " +"'"+ invoiceType+"'" ;
        List<Invoice> invoiceList = jdbcTemplate.query(query , new InvoiceRowMapper());

        return invoiceList;
    }

    @Override
    public List<Invoice> getAllInvoiceInTourName(String invoiceType, String tourName) {

        String tourID = getTourID(tourName);
        String query = "Select * From " + invoiceType +" Where Tour_ID = "+"'" + tourID+"'";
        List<Invoice> invoiceList = jdbcTemplate.query(query , new InvoiceRowMapper());
        return invoiceList;
    }

    @Override
    public Invoice getOneInvoice(String invoiceType, String reservationCode) {
        Invoice invoice = null;
        String query = "select * from " + invoiceType +" Where Reservation_code = "+"'"+reservationCode+"'";
        try {
             invoice = jdbcTemplate.queryForObject(query,new InvoiceRowMapper());
            return invoice;
        }
        catch (EmptyResultDataAccessException e){
            return invoice;
        }

    }

    @Override
    public String getLastInvoiceNo(String invoiceType) {
        String lastInvoiceNo = null;
        List<Invoice> invoiceList = getAllInvoice(invoiceType);

        if (invoiceList.isEmpty())
            if(invoiceType.equalsIgnoreCase(DEPOSIT_INVOICE)){
                lastInvoiceNo =  "DI-XXX-XXXX-000000-000000";
            }
            else if (invoiceType.equalsIgnoreCase(ARREARS_INVOICE)){
                lastInvoiceNo = "AI-XXX-XXXX-000000-000000";
            }
            else {

                lastInvoiceNo = invoiceList.get(invoiceList.size() - 1).getInvoiceNo();
            }
        return lastInvoiceNo;
    }

    @Override
    public void insertData(Receipt receipt, String receiptType) {

        String query = "insert into " + receiptType + " (Reservation_code, Receipt_no, Tour_ID ,Tour_name,Customer_ID,Customer_name,Employee_ID,Employee_name ,Amount_customer,Total_price ,Receipt_status)" +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Object[] data = new Object[]{
                receipt.getReservationCode(),
                receipt.getReceiptNo(),
                receipt.getTourID(),
                receipt.getTourName(),
                receipt.getCustomerID(),
                receipt.getCustomerName(),
                receipt.getEmployeeID(),
                receipt.getEmployeeName(),
                receipt.getAmountCustomer(),
                receipt.getTotalPrice(),
                receipt.getReceiptStatus()
        };
        jdbcTemplate.update(query, data);

    }

    @Override
    public void updateData(Receipt receipt, String receiptType) {

    }

    @Override
    public void updateCreateReceiptStatus(Receipt receipt, String receiptType) {
        String query = "update "+receiptType+ " set Receipt_status = ? Where Receipt_no = ?";

        Object[] data = new Object[]{
                receipt.getReceiptStatus(),
                receipt.getReceiptNo()};
        jdbcTemplate.update(query, data);
    }

    @Override
    public void deleteData(Receipt receipt, String receiptType) {
        String deleteQuery = "Delete From " + "'" + receiptType +"'" + " Where Reservation_code = ?";
        jdbcTemplate.update(deleteQuery, receipt.getReservationCode());
    }

    @Override
    public List<Receipt> getAllReceipt(String receiptType) {
        return null;
    }

    @Override
    public List<Receipt> getAllReceiptInTourName(String receiptType, String tourName) {
        String tourID = getTourID(tourName);
        String query = "Select * From " + receiptType +" Where Tour_ID = " +"'"+ tourID+"'";
        List<Receipt> receiptList = jdbcTemplate.query(query , new ReceiptRowMapper());
        return receiptList;
    }

    @Override
    public Receipt getOneReceipt(String receiptType, String reservationCode) {
        Receipt receipt = null;
        String query = "select * from " + receiptType +" Where Reservation_code = "+"'"+reservationCode+"'";
        try {
            receipt = jdbcTemplate.queryForObject(query, new ReceiptRowMapper());
            return receipt;
        }
        catch (EmptyResultDataAccessException e){
            return receipt;
        }
    }

    @Override
    public String getLastReceiptNo(String receiptType) {
        String lastReceiptNo;
        List<Receipt> receiptList = getAllReceipt(receiptType);
        if (receiptList == null)
            return "0";
        else {

            lastReceiptNo = receiptList.get(receiptList.size() - 1).getReceiptNo();
            return lastReceiptNo;
        }
    }

    // class row mapper

    class EmployeeRowMapper implements RowMapper<Employee>{

        public Employee mapRow(ResultSet resultSet, int rowNum) throws SQLException {

            Employee employee = new Employee(
                    resultSet.getString("Employee_ID"),
                    resultSet.getString("Username"),
                    resultSet.getString("Password"),
                    resultSet.getString("Position"),
                    resultSet.getString("TitleName"),
                    resultSet.getString("FirstName"),
                    resultSet.getString("LastName"),
                    resultSet.getString("Email"),
                    resultSet.getString("Mobile_num"));
            return employee;
        }
    }

    class TourPackageRowMapper implements RowMapper<TourPackage>{

        public TourPackage mapRow(ResultSet rs, int i) throws SQLException {
            TourPackage tourPackage = new TourPackage(
                    rs.getString("Tour_ID"),
                    rs.getString("Tour_name"),
                    rs.getInt("Price"),
                    rs.getString("Departure_date"),
                    rs.getString("Return_date"),
                    rs.getString("Deposit_date"),
                    rs.getString("Arrears_date"),
                    rs.getInt("Amount"),
                    rs.getInt("Available"),
                    rs.getString("Status"));

            return tourPackage;
        }
    }

    class CustomerRowMapper implements RowMapper<Customer> {
        public Customer mapRow(ResultSet rs, int rowNum)
                throws SQLException {
            Customer customer = new Customer(
                    rs.getString("Customer_ID"),
                    rs.getString("TitleNameTH"),
                    rs.getString("FirstNameTH"),
                    rs.getString("LastNameTH"),
                    rs.getString("TitleNameENG"),
                    rs.getString("FirstNameENG"),
                    rs.getString("lastNameENG"),
                    rs.getString("Gender"),
                    rs.getString("Age"),
                    rs.getString("Occupation"),
                    rs.getString("Date_of_birth"),
                    rs.getString("Passport_no"),
                    rs.getString("Expire_passport_date"),
                    rs.getString("Contact_Address"),
                    rs.getString("Cell_phone"),
                    rs.getString("Home_Tel"),
                    rs.getString("Fax"),
                    rs.getString("Email_address"),
                    rs.getString("Disease"),
                    rs.getString("Food_allergy"),
                    rs.getString("Eat_beef"),
                    rs.getString("More_detail"),
                    rs.getString("HearAboutUs"));

            return customer;
        }
    }

    class ReservationRowMapper implements RowMapper<Reservation>{

        public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
            Reservation reservation = new Reservation(
                    rs.getString("Reservation_code"),
                    rs.getString("Tour_ID"),
                    rs.getString("Tour_name"),
                    rs.getString("Customer_ID"),
                    rs.getString("Customer_name"),
                    rs.getString("Employee_ID"),
                    rs.getString("Employee_name"));

            return  reservation;
        }
    }
    class ReservationPaymentRowMapper implements RowMapper<ReservationPayment>{

        public ReservationPayment mapRow(ResultSet rs, int rowNum) throws SQLException {
            ReservationPayment reservationPayment = new ReservationPayment(
                    rs.getString("Reservation_code"),
                    rs.getString("Tour_ID"),
                    rs.getString("Tour_name"),
                    rs.getString("Customer_ID"),
                    rs.getString("Customer_name"),
                    rs.getString("Employee_ID"),
                    rs.getString("Employee_name"),
                    rs.getInt("Amount_customer"),
                    rs.getInt("Total_price"),
                    rs.getString("Deposit_status"),
                    rs.getString("Arrears_status")
            );

            return  reservationPayment;
        }
    }

    class InvoiceRowMapper implements RowMapper<Invoice>{
        public Invoice mapRow(ResultSet rs, int i) throws SQLException {
            Invoice invoice = new Invoice(
                    rs.getString("Reservation_code"),
                    rs.getString("Invoice_no"),
                    rs.getString("Tour_ID"),
                    rs.getString("Tour_name"),
                    rs.getString("Customer_ID"),
                    rs.getString("Customer_name"),
                    rs.getString("Employee_ID"),
                    rs.getString("Employee_name"),
                    rs.getInt("Amount_customer"),
                    rs.getInt("Total_price"),
                    rs.getString("Invoice_status"));
            return invoice;
        }
    }
    class ReceiptRowMapper implements RowMapper<Receipt>{
        public Receipt mapRow(ResultSet rs, int i) throws SQLException {
            Receipt receipt = new Receipt(
                    rs.getString("Reservation_code"),
                    rs.getString("Receipt_no"),
                    rs.getString("Tour_ID"),
                    rs.getString("Tour_name"),
                    rs.getString("Customer_ID"),
                    rs.getString("Customer_name"),
                    rs.getString("Employee_ID"),
                    rs.getString("Employee_name"),
                    rs.getInt("Amount_customer"),
                    rs.getInt("Total_price"),
                    rs.getString("Receipt_status"));
            return receipt;
        }
    }
}

