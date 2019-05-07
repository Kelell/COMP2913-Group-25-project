

/** * @author Zahoor
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Start of Application, runs the application instance
 */

public class Main extends Application {

    private static  Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        //Load Login Page
    	Parent root = FXMLLoader.load(getClass().getResource("fxml/loginPage.fxml"));
        primaryStage.setScene(new Scene(root, 738, 508));

        //Displays App Name 
        primaryStage.setTitle("Bike World");
        //Display App Icon
        primaryStage.getIcons().add(new Image(ClassLoader.getSystemResourceAsStream("CSS/icon.png")));
        mainStage = primaryStage;
        mainStage.centerOnScreen();
        mainStage.show();
        

    }

    //This method is called to close the window
    public void close(){
        mainStage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
