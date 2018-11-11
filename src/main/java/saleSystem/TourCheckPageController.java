package saleSystem;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import databaseConnection.DbConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static databaseConnection.MongoDBConnect.reserve_card;


public class TourCheckPageController implements Initializable {
    @FXML
    private JFXHamburger menu;
    @FXML
    private JFXDrawer drawerMenu;
    @FXML
    private ChoiceBox<?> findTourID;
    @FXML
    private JFXButton searchBtnTour;
    @FXML
    private Label showTourDetail;
    @FXML
    private TableView<TourChecking> tableTourCheck;
    @FXML private TableColumn<TourChecking, String> reservCodeColumn;
    @FXML private TableColumn<TourChecking, String> firstnameColumn;
    @FXML private TableColumn<TourChecking, String> lastnameColumn;
    @FXML private TableColumn<TourChecking, String> memberStatusColumn;
    @FXML private TableColumn<TourChecking, String> depositStatusColumn;
    @FXML private TableColumn<TourChecking, String> arrearsStatusColumn;
    @FXML private Button editBtnTour;
    @FXML private Button deleteBtnTour;
    @FXML private Button updateBtnTour;

    ObservableList<TourChecking> obListTour= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SaleManagementUtil.initDrawerToolBar(drawerMenu, menu, getClass().getResource("/hamburgerMenu.fxml"));

        try {
            showTableView();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void handleDeleteBtnTour(ActionEvent event) throws SQLException {
        deleteDataByMongoDB();
        deleteDataBySqlite();

        //ObservableList (Table View) : Delete data
        obListTour.remove(tableTourCheck.getSelectionModel().getSelectedItem());
    }

    @FXML
    void handleEditBtnTour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/reservationForEditPage.fxml"));
            Parent parent = (Parent) loader.load();
            ReserveEditPageController reserveEditPageController = loader.getController();
            reserveEditPageController.setText("TAIWAN");
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.show();
            stage.setScene(new Scene(parent));
        } catch (IOException e) {
            e.printStackTrace();
        }


        //MongoDB : Update database
        String reserv_codeEdit = tableTourCheck.getSelectionModel().getSelectedItem().getReservCode();
        System.out.println(reserv_codeEdit);
//        BasicDBObject searchQuery = new BasicDBObject().append("Reservation_code",reserv_codeEdit );
//
//        BasicDBObject newData = new BasicDBObject();
//        newData.append("$set", new BasicDBObject().append("password", 22222222));
//
//        reserve_card.update(searchQuery, newData);

    }

    @FXML
    void handleSearchBtnTour(ActionEvent event) {

    }

    @FXML
    void handleUpdateBtnTour(ActionEvent event) {

    }


    public void showTableView() throws SQLException {

        //find data base for show on table view.
        Connection connection = DbConnect.getConnection();
        try {

            String sql = "select * from reserve_card_database";
            ResultSet rs = connection.createStatement().executeQuery(sql);
            while (rs.next()) {
                obListTour.add(new TourChecking(rs.getString("Reservation_code"), rs.getString("FirstnameTH"), rs.getString("LastnameTH")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connection.close();
        }

        reservCodeColumn.setCellValueFactory(new PropertyValueFactory<TourChecking, String>("reservCode"));
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<TourChecking, String>("firstName"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<TourChecking, String>("lastName"));

        reservCodeColumn.setCellFactory(TextFieldTableCell.<TourChecking>forTableColumn());
        firstnameColumn.setCellFactory(TextFieldTableCell.<TourChecking>forTableColumn());
        lastnameColumn.setCellFactory(TextFieldTableCell.<TourChecking>forTableColumn());
        tableTourCheck.setItems(obListTour);


    }

    public class TourChecking{
        private String reservCode;
        private String firstName;
        private String lastName;
        private String memberStatus;
        private String depositStatus;
        private String arrearsStatus;

        public TourChecking(String reservCode, String firstName, String lastName, String memberStatus, String depositStatus, String arrearsStatus) {
            this.reservCode = reservCode;
            this.firstName = firstName;
            this.lastName = lastName;
            this.memberStatus = memberStatus;
            this.depositStatus = depositStatus;
            this.arrearsStatus = arrearsStatus;
        }

        public TourChecking(String reservCode, String firstName, String lastName) {
            this.reservCode = reservCode;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getReservCode() {
            return reservCode;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getMemberStatus() {
            return memberStatus;
        }

        public String getDepositStatus() {
            return depositStatus;
        }

        public String getArrearsStatus() {
            return arrearsStatus;
        }
    }

    public void deleteDataByMongoDB(){
        //MongoDB : Delete data
        String reserv_codeDelete = tableTourCheck.getSelectionModel().getSelectedItem().getReservCode();

        BasicDBObject delete = new BasicDBObject("Reservation_code",reserv_codeDelete);
        reserve_card.remove(delete);    //delete from MongoDB

        Cursor cursor = reserve_card.find();
        while (cursor.hasNext())
            System.out.println(cursor.next());
    }

    public void deleteDataBySqlite(){
        //SQLite : Delete data

        Connection connection = DbConnect.getConnection();
        PreparedStatement pst ;
        String reserv_codeDelete = tableTourCheck.getSelectionModel().getSelectedItem().getReservCode();
        String sql = "Delete from reserve_card_database where Reservation_code = ?";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1,reserv_codeDelete);
            pst.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

