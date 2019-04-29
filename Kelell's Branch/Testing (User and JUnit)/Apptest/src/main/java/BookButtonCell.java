/**
 * @author Zahoor
 */
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

/**
 * This class handles all functions when Book Button is clicked
 * @param <S>
 */
public class BookButtonCell<S> extends TableCell<S, Button> {

    private final Button bookButton;
    private static Stage primaryStage;

    public BookButtonCell() {

        this.getStyleClass().add("action-button-table-cell");

        //initialise the button
        this.bookButton = new Button("Book Bike");

        this.bookButton.setMaxWidth(100.0);
        this.bookButton.setAlignment(Pos.BASELINE_CENTER);

         //Perform an action when the button is clicked
        this.bookButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //close the dashboard
                new LoginController().close();

                //Load the Booking Screen
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/books.fxml"));

                Parent root2 = null;
                try {

                    BikeModel bikeModel = (BikeModel) getCurrentItem();
                    System.out.println(bikeModel);
                    root2 = (Parent) fxmlLoader.load();
                    BookController controller = fxmlLoader.<BookController>getController();
                    controller.setBikeDetails(bikeModel);
                    Scene scene = new Scene(root2, 572, 528);
                    primaryStage = new Stage();
                    primaryStage.setScene(scene);
                    primaryStage.show();
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public S getCurrentItem() {
        return (S) getTableView().getItems().get(getIndex());
    }

    public static <S> Callback<TableColumn<S, Button>, TableCell<S, Button>> forTableColumn() {
        return param -> new BookButtonCell<>();
    }

    @Override
    public void updateItem(Button item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {
            BikeModel bikeModel = (BikeModel)getCurrentItem();
            String status = bikeModel.statusProperty().getValue().toString();
            if(status.equals("Free")){
                setGraphic(bookButton);
            }else{
                setGraphic(null);
            }
        }
    }

    public static void close(){
        if(primaryStage!= null)
           primaryStage.close();
    }
}
