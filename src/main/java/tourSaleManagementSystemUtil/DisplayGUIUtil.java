package tourSaleManagementSystemUtil;

import Table.Employee;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import createReport.CreateReport;
import createReport.ReportPDF;
import databaseConnection.ManageableDatabase;
import databaseConnection.SpringJDBC_DB;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DisplayGUIUtil {

    public static ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
    public static ManageableDatabase manageableDatabase = context.getBean("SpringJDBC_DB", SpringJDBC_DB.class);
    public static CreateReport createReport = context.getBean("ReportPDF", ReportPDF.class);

    public static Employee loginEmployee = new Employee();

    public static void loadWindow(URL loc, String title){
        try {
            Parent parent = FXMLLoader.load(loc);
            Stage stage = null;
            stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
            stage.centerOnScreen();
            stage.setResizable(false);

        } catch (IOException e) {
            Logger.getLogger(FXMLLoader.class.getName()).log(Level.SEVERE,null, e);
        }
    }

    public static void loadWindowWithSetSize(URL loc, String title){
        try {
            Parent parent = FXMLLoader.load(loc);
            Stage stage = null;
            stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent, 890, 690));
            stage.show();
            stage.centerOnScreen();
            stage.setResizable(false);

        } catch (IOException e) {
            Logger.getLogger(FXMLLoader.class.getName()).log(Level.SEVERE,null, e);
        }
    }

    public static void initDrawerToolBar(JFXDrawer drawerMenu, JFXHamburger menu, URL loc){
        try {
            VBox toolbar = FXMLLoader.load(loc);
            drawerMenu.setSidePane(toolbar);
            drawerMenu.setDefaultDrawerSize(100);
        } catch (IOException e) {
            Logger.getLogger(FXMLLoader.class.getName()).log(Level.SEVERE,null, e);
        }

        HamburgerNextArrowBasicTransition hamMenu = new HamburgerNextArrowBasicTransition(menu);
        hamMenu.setRate(-1);
        menu.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            hamMenu.setRate(hamMenu.getRate()*-1);
            hamMenu.play();
            if (drawerMenu.isHidden()) drawerMenu.open();
            else drawerMenu.close();

        });
    }

}
