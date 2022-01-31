package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class UserController {

	@FXML
    private Text movieNameUser;

    @FXML
    private Text ticketPriceUser;

    @FXML
    private Text timingUser;

    @FXML
    private TextField amountUser;

    @FXML
    private TextField ticketsUser;

    @FXML
    private Text balanceUser;

    @FXML
    private Text housefull;

    @FXML
    private Text errorUser;
    
    @FXML
    private Text seatsUser;
    
    public void initData(String name,String seats,String price,String date) {
    	movieNameUser.setText(name);
    	ticketPriceUser.setText(price);
		timingUser.setText(date);
		seatsUser.setText(seats);
		balanceUser.setText("0");
		if(Integer.parseInt(seats)<1) {
			housefull.setText("housefull");
		}
	}
    
    @FXML
    void buyTicket(ActionEvent event) {
    	String tickets = ticketsUser.getText();
    	int seatsInt = 0;
    	
    	if(tickets.equals("")) {
    		errorUser.setText("Tickets number can not be empty.");
    		return;
    	}
    	
    	try {
			seatsInt = Integer.parseInt(tickets);
		}
    	catch (Exception e) {
    		errorUser.setText("Tickets must be numeric.");
			return;
		}
    	
    	int totalSeats = Integer.parseInt(seatsUser.getText());
    	
    	if(seatsInt>totalSeats) {
    		errorUser.setText("Seats remaining are less than what you asked for");
    		return;
    	}
    	
    	int moviePrice = Integer.parseInt(ticketPriceUser.getText());
    	int balance = 0;
    	if(!balanceUser.getText().equals("")) {
    		balance = Integer.parseInt(balanceUser.getText());
    	}
    	if(moviePrice*seatsInt>balance) {
    		errorUser.setText("Please insert more money to buy tickets");
    		return;
    	}
    	
    	balance = balance - moviePrice*seatsInt;
    	balanceUser.setText(balance+"");
    	
    	totalSeats = totalSeats - seatsInt;
    	seatsUser.setText(totalSeats+"");
    	
    	if(totalSeats==0) {
    		housefull.setText("housefull");
    	}
    	
    	errorUser.setText("tickets purchased");
    }

    @FXML
    void returnChange(ActionEvent event) {
    	int balance = 0;
    	if(!balanceUser.getText().equals("")) {
    		balance = Integer.parseInt(balanceUser.getText());
    	}
    	if(balance==0) {
    		errorUser.setText("You have to no balance left");
    	}
    	else {
    		errorUser.setText("Balance returned");
    		balanceUser.setText("0");
    	}
    }
    
    @FXML
    void insertMoney(ActionEvent event) {
    	String price = amountUser.getText();
    	int priceInt = 0;
    	
    	if(price.equals("")) {
    		errorUser.setText("Amount can not be empty.");
    		return;
    	}
    	
    	try {
			priceInt = Integer.parseInt(price);
		}
    	catch (Exception e) {
			errorUser.setText("Amount must be numeric.");
			return;
		}
    	
    	errorUser.setText("Amount Inserted");
    	int balance = 0;
    	if(!balanceUser.getText().equals("")) {
    		balance = Integer.parseInt(balanceUser.getText());
    	}
    	balanceUser.setText((balance+priceInt)+"");
    }
    
    @FXML
    void nextUser(ActionEvent event) {
    	int balance = 0;
    	if(!balanceUser.getText().equals("")) {
    		balance = Integer.parseInt(balanceUser.getText());
    	}
    	if(balance!=0) {
    		errorUser.setText("Please take your change.");
    		return;
    	}
    	balanceUser.setText("0");
    	amountUser.setText("");
    	ticketsUser.setText("");
    	errorUser.setText("");
    }
    
}
