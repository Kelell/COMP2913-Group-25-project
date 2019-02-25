import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static  Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/loginPage.fxml"));
        primaryStage.setScene(new Scene(root, 738, 508));
        primaryStage.setTitle("Bike App");
        mainStage = primaryStage;
        mainStage.centerOnScreen();
        mainStage.show();

    }
    public void close(){
        mainStage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
