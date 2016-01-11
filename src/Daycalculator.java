import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
//finds the day of the week on a given date in the 20th and 21st century
public class Daycalculator extends Application{

	TextField date = new TextField(); 
	Label Date = new Label("Date");

	TextField month = new TextField(); 
	Label Month = new Label("Month");

	TextField year = new TextField(); 
	Label Year = new Label("Year");

	Button calculate = new Button("Calculate");
	Label answer = new Label();

	public void start(Stage primaryStage){
		GridPane window = new GridPane();
		window.add(date, 0, 0);window.add(Date,0,1); //date text and label field
		window.add(month, 1, 0);window.add(Month,1,1); //month text and label field
		window.add(year, 2, 0);window.add(Year,2,1); //year text and label field
		window.add(calculate,1,2); //calculate button
		window.add(answer,1,4); //answer text field which is initially empty is filled using event handler
		calculate.setStyle("-fx-border-color: black");
		calculate.setOnAction(e -> find());
		Scene scene = new Scene(window,300,300);

		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args){
		launch(args);
	}

	private void find(){
		boolean validInputs = true; //to check if the user provided inputs are valid

		int userYear = 0, userMonth = 0, userDate = 0;

		try{
			userYear = Integer.parseInt(year.getText()); //user entered year
			userMonth = Integer.parseInt(month.getText()); //user entered month
			userDate = Integer.parseInt(date.getText()); //user entered date
		}catch(Exception e){
			validInputs = false;

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Input");
			alert.setHeaderText("INVALID INPUT");
			alert.setContentText("Invalid Input");
			alert.showAndWait();
		}

		String[] days = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};//Array for finding the answer
		int[] monthCodes = {6,2,2,5,0,3,5,1,4,6,2,4}; //year codes for non-leap year
		int[] leapmonthCodes = {5,1,2,5,0,3,5,1,4,6,2,4}; //year codes for leap year
		boolean leap = userYear%4==0?true:false; //to check leap year
		int numAns = 0; //numeric representation of the answer



		if(userYear <= 0 || userMonth <= 0 || userDate <= 0 ){
			validInputs = false;

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Input");
			alert.setHeaderText("INVALID INPUT");
			alert.setContentText("Values can't be negative");
			alert.showAndWait();
		}

		if(userYear < 1900 || userYear >= 2100 || userMonth > 12){
			validInputs = false;

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Input");
			alert.setHeaderText("INVALID INPUT");
			alert.setContentText("Year must be in range 1900-2099 (both inclusive) and months smaller than 12");
			alert.showAndWait();
		}

		//do the calculation iff the inputs are valid
		if(validInputs){
			if(!leap && userYear >= 2000)
				numAns = (userDate + monthCodes[userMonth-1] + (userYear%2000) + ((userYear%2000)/4))%7;
			else if(leap && userYear < 2000)
				numAns = (userDate + leapmonthCodes[userMonth-1] + (userYear%1900) + ((userYear%1900)/4) + 1)%7;
			else if(leap && userYear >= 2000)
				numAns = (userDate + leapmonthCodes[userMonth-1] + (userYear%2000) + ((userYear%2000)/4))%7;
			else if(!leap && userYear < 2000)
				numAns = (userDate + monthCodes[userMonth-1] + (userYear%1900) + ((userYear%1900)/4) + 1)%7;

			answer.setText(days[numAns]);	
		}}}
