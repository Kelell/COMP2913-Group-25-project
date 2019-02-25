/**
 * @author Zahoor
 */
import java.io.IOException;
import java.util.function.Function;
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

public class BookButtonCell<S> extends TableCell<S, Button> {

    private final Button actionButton;
    private static Stage primaryStage;


    public BookButtonCell(String label, Function< S, S> function) {
        this.getStyleClass().add("action-button-table-cell");

        this.actionButton = new Button(label);
        this.actionButton.setOnAction((ActionEvent e) -> {
            function.apply(getCurrentItem());
        });
        this.actionButton.setMaxWidth(100.0);
        this.actionButton.setAlignment(Pos.BASELINE_CENTER);

        this.actionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/book.fxml"));

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

    public static <S> Callback<TableColumn<S, Button>, TableCell<S, Button>> forTableColumn(String label, Function< S, S> function) {
        return param -> new BookButtonCell<>(label, function);
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
                setGraphic(actionButton);
            }else{
                setGraphic(null);
            }

        }
    }

    public static void close(){
        primaryStage.close();
    }
}
