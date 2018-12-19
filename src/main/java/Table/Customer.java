package Table;

public class Customer {
    private String customerID;
    private String titleNameTH;
    private String firstNameTH;
    private String lastNameTH;
    private String titleNameENG;
    private String firstNameENG;
    private String lastNameENG;
    private String gender;
    private String occupation;
    private String dateOfBirth;
    private String passport_no;
    private String exp_passport;

    private String contactAddress;
    private String cell_phone;
    private String home_Tel;
    private String fax;
    private String email;

    private String disease;
    private String foodAllergy;
    private String eatBeef;
    private String moreDetail;
    private String hearAboutUs;

    public Customer() {
        this.customerID = null;
        this.titleNameTH = "นางสาว";
        this.firstNameTH = null;
        this.lastNameTH = null;
        this.titleNameENG = "Miss";
        this.firstNameENG = null;
        this.lastNameENG = null;
        this.gender = "Female";
        this.occupation = null;
        this.dateOfBirth = "dd-mm-yyyy";
        this.passport_no = null;
        this.exp_passport = "dd-mm-yyyy";
        this.contactAddress = null;
        this.cell_phone = null;
        this.home_Tel = null;
        this.fax = null;
        this.email = null;
        this.disease = null;
        this.foodAllergy = null;
        this.eatBeef = null;
        this.moreDetail = null;
        this.hearAboutUs = "Bangkokbizs News";
    }

    public Customer(String customerID, String titleNameTH, String firstNameTH, String lastNameTH, String titleNameENG, String firstNameENG, String lastNameENG, String gender, String occupation, String dateOfBirth, String passport_no, String exp_passport, String contactAddress, String cell_phone, String home_Tel, String fax, String email, String disease, String foodAllergy, String eatBeef, String moreDetail, String hearAboutUs) {
        this.customerID = customerID;
        this.titleNameTH = titleNameTH;
        this.firstNameTH = firstNameTH;
        this.lastNameTH = lastNameTH;
        this.titleNameENG = titleNameENG;
        this.firstNameENG = firstNameENG;
        this.lastNameENG = lastNameENG;
        this.gender = gender;
        this.occupation = occupation;
        this.dateOfBirth = dateOfBirth;
        this.passport_no = passport_no;
        this.exp_passport = exp_passport;
        this.contactAddress = contactAddress;
        this.cell_phone = cell_phone;
        this.home_Tel = home_Tel;
        this.fax = fax;
        this.email = email;
        this.disease = disease;
        this.foodAllergy = foodAllergy;
        this.eatBeef = eatBeef;
        this.moreDetail = moreDetail;
        this.hearAboutUs = hearAboutUs;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getTitleNameTH() {
        return titleNameTH;
    }

    public void setTitleNameTH(String titleNameTH) {
        this.titleNameTH = titleNameTH;
    }

    public String getFirstNameTH() {
        return firstNameTH;
    }

    public void setFirstNameTH(String firstNameTH) {
        this.firstNameTH = firstNameTH;
    }

    public String getLastNameTH() {
        return lastNameTH;
    }

    public void setLastNameTH(String lastNameTH) {
        this.lastNameTH = lastNameTH;
    }

    public String getTitleNameENG() {
        return titleNameENG;
    }

    public void setTitleNameENG(String titleNameENG) {
        this.titleNameENG = titleNameENG;
    }

    public String getFirstNameENG() {
        return firstNameENG;
    }

    public void setFirstNameENG(String firstNameENG) {
        this.firstNameENG = firstNameENG;
    }

    public String getLastNameENG() {
        return lastNameENG;
    }

    public void setLastNameENG(String lastNameENG) {
        this.lastNameENG = lastNameENG;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassport_no() {
        return passport_no;
    }

    public void setPassport_no(String passport_no) {
        this.passport_no = passport_no;
    }

    public String getExp_passport() {
        return exp_passport;
    }

    public void setExp_passport(String exp_passport) {
        this.exp_passport = exp_passport;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getCell_phone() {
        return cell_phone;
    }

    public void setCell_phone(String cell_phone) {
        this.cell_phone = cell_phone;
    }

    public String getHome_Tel() {
        return home_Tel;
    }

    public void setHome_Tel(String home_Tel) {
        this.home_Tel = home_Tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getFoodAllergy() {
        return foodAllergy;
    }

    public void setFoodAllergy(String foodAllergy) {
        this.foodAllergy = foodAllergy;
    }

    public String getEatBeef() {
        return eatBeef;
    }

    public void setEatBeef(String eatBeef) {
        this.eatBeef = eatBeef;
    }

    public String getMoreDetail() {
        return moreDetail;
    }

    public void setMoreDetail(String moreDetail) {
        this.moreDetail = moreDetail;
    }

    public String getHearAboutUs() {
        return hearAboutUs;
    }

    public void setHearAboutUs(String hearAboutUs) {
        this.hearAboutUs = hearAboutUs;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID='" + customerID + '\'' +
                ", titleNameTH='" + titleNameTH + '\'' +
                ", firstNameTH='" + firstNameTH + '\'' +
                ", lastNameTH='" + lastNameTH + '\'' +
                ", titleNameENG='" + titleNameENG + '\'' +
                ", firstNameENG='" + firstNameENG + '\'' +
                ", lastNameENG='" + lastNameENG + '\'' +
                ", gender='" + gender + '\'' +
                ", occupation='" + occupation + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", passport_no='" + passport_no + '\'' +
                ", exp_passport='" + exp_passport + '\'' +
                ", contactAddress='" + contactAddress + '\'' +
                ", cell_phone='" + cell_phone + '\'' +
                ", home_Tel='" + home_Tel + '\'' +
                ", fax='" + fax + '\'' +
                ", email='" + email + '\'' +
                ", disease='" + disease + '\'' +
                ", foodAllergy='" + foodAllergy + '\'' +
                ", eatBeef='" + eatBeef + '\'' +
                ", moreDetail='" + moreDetail + '\'' +
                ", hearAboutUs='" + hearAboutUs + '\'' +
                '}';
    }
}
