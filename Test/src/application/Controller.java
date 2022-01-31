package application;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private TextField movieName;

    @FXML
    private TextField ticketPrice;

    @FXML
    private TextField numberSeats;

    @FXML
    private Text errorAdmin;
    
    @FXML
    private DatePicker timing;
    
    @FXML
    void onAdminSubmit(ActionEvent event) throws InterruptedException {
    		
    	String name = movieName.getText();
    	String price = ticketPrice.getText();
    	String seats = numberSeats.getText();
    	LocalDate date = timing.getValue();
    	int priceInt = 0;
    	int seatsInt = 0;
    	
    	if(name.equals("") || price.equals("") || seats.equals("") || date==null) {
    		errorAdmin.setText("Above fields can not be empty.");
    		return;
    	}
    	
    	try {
			priceInt = Integer.parseInt(price);
		}
    	catch (Exception e) {
			errorAdmin.setText("Price must be numeric.");
			return;
		}
    	
    	try {
			seatsInt = Integer.parseInt(seats);
		}
    	catch (Exception e) {
			errorAdmin.setText("Seats must be numeric.");
			return;
		}
    	
    	try {
    		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("User.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            UserController controller = fxmlLoader.getController();
            controller.initData(name,seats,price,date.toString());
            stage.show();
	    } 
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}
