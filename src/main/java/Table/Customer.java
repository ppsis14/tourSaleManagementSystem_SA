package Table;

import java.util.ArrayList;

public class Customer {
    private int customerID;
    private String titleNameTH;
    private String firstNameTH;
    private String lastNameTH;
    private String titleNameENG;
    private String firstNameENG;
    private String lastNameENG;
    private String titleNameOld;
    private String firstNameOld;
    private String lastNameOld;
    private String gender;
    private String age;
    private String dateOfBirth;
    private String passport_no;
    private String exp_passport;
    private String homeAddress;
    private String cell_phone;
    private String home_Tel;
    private String fax;
    private String email;
    private String career;
    private String companyAddress;
    private String work_Tel;
    private String memberStatus;
    private String disease;
    private String foodAllergy;
    private String eatBeef;
    private String moreDetail;
    private String channel;

    public Customer(){

    }
    public Customer(int customerID , String titleNameTH, String firstNameTH, String lastNameTH, String titleNameENG,
                    String firstNameENG, String lastNameENG, String titleNameOld, String firstNameOld, String lastNameOld,
                    String gender, String age, String dateOfBirth, String passport_no, String exp_passport, String homeAddress,
                    String cell_phone, String home_Tel, String fax, String email, String career, String companyAddress,
                    String work_Tel, String memberStatus, String disease, String foodAllergy, String eatBeef, String moreDetail,
                    String channel) {

        this.customerID = customerID;
        this.titleNameTH = titleNameTH;
        this.firstNameTH = firstNameTH;
        this.lastNameTH = lastNameTH;
        this.titleNameENG = titleNameENG;
        this.firstNameENG = firstNameENG;
        this.lastNameENG = lastNameENG;
        this.titleNameOld = titleNameOld;
        this.firstNameOld = firstNameOld;
        this.lastNameOld = lastNameOld;
        this.gender = gender;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.passport_no = passport_no;
        this.exp_passport = exp_passport;
        this.homeAddress = homeAddress;
        this.cell_phone = cell_phone;
        this.home_Tel = home_Tel;
        this.fax = fax;
        this.email = email;
        this.career = career;
        this.companyAddress = companyAddress;
        this.work_Tel = work_Tel;
        this.memberStatus = memberStatus;
        this.disease = disease;
        this.foodAllergy = foodAllergy;
        this.eatBeef = eatBeef;
        this.moreDetail = moreDetail;
        this.channel = channel;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
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

    public String getTitleNameOld() {
        return titleNameOld;
    }

    public void setTitleNameOld(String titleNameOld) {
        this.titleNameOld = titleNameOld;
    }

    public String getFirstNameOld() {
        return firstNameOld;
    }

    public void setFirstNameOld(String firstNameOld) {
        this.firstNameOld = firstNameOld;
    }

    public String getLastNameOld() {
        return lastNameOld;
    }

    public void setLastNameOld(String lastNameOld) {
        this.lastNameOld = lastNameOld;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
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

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getWork_Tel() {
        return work_Tel;
    }

    public void setWork_Tel(String work_Tel) {
        this.work_Tel = work_Tel;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }


}
