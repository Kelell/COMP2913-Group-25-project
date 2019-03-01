/**
 * Created by Zahoor on 2/23/2019.
 */

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JRViewer;

import javax.swing.*;
import java.io.File;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.function.Function;

public class PrintButtonCell<S> extends TableCell<S, Button> {

    private final Button actionButton;
    private static Stage primaryStage;


    public PrintButtonCell(String label, Function< S, S> function) {
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

                TicketModel ticketModel = (TicketModel) getCurrentItem();

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        DateFormat dfmt = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date dt = new java.util.Date();
                        String date = dfmt.format(dt);
                        DateFormat tfmt = new SimpleDateFormat("hh:mm:ss");
                        java.util.Date tm = new java.util.Date();
                        String time = tfmt.format(tm);

                        try {

                            JasperDesign jd = JRXmlLoader.load(new File("").getAbsolutePath()+"/src/posreceipt.jrxml");

                            HashMap params = new HashMap();
                            //params.put("phone", "9123456789");

                            String id = ticketModel.bike_idProperty().getValue().toString();

                            Connection con = DbConnect.getDbConnect();

                            JRDesignQuery newQuery = new JRDesignQuery();
                            newQuery.setText("SELECT *,  DATE_FORMAT(start_date, \"%d %M %Y\") as start,DATE_FORMAT(end_date, \"%d %M %Y\") as end from hires LEFT JOIN bike ON hires.bike_id = bike.BIKE_ID LEFT JOIN customer ON customer.CUSTOMER_ID = hires.customer_id where hires.BIKE_ID = "+id+";");
                            jd.setQuery(newQuery);

                            JasperReport jr = JasperCompileManager.compileReport(jd);
                            JasperPrint jp = JasperFillManager.fillReport(jr,params, con);

                            JFrame frame = new JFrame("Print");
                            frame.getContentPane().add(new JRViewer(jp));
                            frame.setSize(600,450);
                            frame.setVisible(true);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                      /*  try {

                            InputStream stream = getClass().getResourceAsStream("posreceipt.jrxml");

                            JasperDesign jd = JRXmlLoader.load(stream);
                            JasperReport jr = JasperCompileManager.compileReport(jd);
                            JasperPrint jp = JasperFillManager.fillReport(jr, null, con);

                            JasperPrintManager.printReport(jp, false);
                        } catch (JRException e1) {
                            e1.printStackTrace();
                        }*/
                    }
                });



            }

        });
    }

    public S getCurrentItem() {
        return (S) getTableView().getItems().get(getIndex());
    }

    public static <S> Callback<TableColumn<S, Button>, TableCell<S, Button>> forTableColumn(String label, Function< S, S> function) {
        return param -> new PrintButtonCell<>(label, function);
    }

    @Override
    public void updateItem(Button item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(actionButton);
        }
    }

    public static void close(){
        primaryStage.close();
    }
}
