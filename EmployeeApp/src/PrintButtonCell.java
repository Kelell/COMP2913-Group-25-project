/**
 * @author Zahoor
 */

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
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
import java.util.HashMap;

/**
 * This class sets up the actions when print button is clicked
 * @param <S>
 */
public class PrintButtonCell<S> extends TableCell<S, Button> {

    private final Button printButton;

    public PrintButtonCell() {
        this.getStyleClass().add("action-button-table-cell");

        //initialises the print button
        this.printButton = new Button("Print Ticket");

        this.printButton.setMaxWidth(100.0);
        this.printButton.setAlignment(Pos.BASELINE_CENTER);

        //When clicked prints pdf using jasper reports
        this.printButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                TicketModel ticketModel = (TicketModel) getCurrentItem();

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            //Jasper reports loads the xml
                            JasperDesign jd = JRXmlLoader.load(new File("").getAbsolutePath()+"/src/posreceipt.jrxml");
                            HashMap params = new HashMap();

                            //Gets the bike id
                            String id = ticketModel.bike_idProperty().getValue().toString();

                            Connection con = DbConnect.getDbConnect();

                            //Generates receipt from the database and passes to jasper reports
                            JRDesignQuery newQuery = new JRDesignQuery();
                            newQuery.setText("SELECT *, concat(\"£ \",price) as f_price ,   concat(\"£ \",TRUNCATE((days * price),2))  as total, concat(\"£ \",TRUNCATE((cash  - (days * price)),2))  as changec,concat(\"£ \",cash) as cash_c, DATE_FORMAT(start_date, \"%d %M %Y\") as start,DATE_FORMAT(end_date, \"%d %M %Y\") as end from hires LEFT JOIN bike ON hires.bike_id = bike.BIKE_ID LEFT JOIN customer ON customer.CUSTOMER_ID = hires.customer_id where hires.BIKE_ID = "+id+";");
                            jd.setQuery(newQuery);

                            //Compiles the jasper report
                            JasperReport jr = JasperCompileManager.compileReport(jd);
                            JasperPrint jp = JasperFillManager.fillReport(jr,params, con);

                            //Displays the pdf on the window
                            JFrame frame = new JFrame("Print");
                            frame.getContentPane().add(new JRViewer(jp));
                            frame.setSize(600,450);
                            frame.setVisible(true);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
				
            }
			
        });
    }

    public S getCurrentItem() {
        return (S) getTableView().getItems().get(getIndex());
    }

    public static <S> Callback<TableColumn<S, Button>, TableCell<S, Button>> forTableColumn() {
        return param -> new PrintButtonCell<>();
    }

    @Override
    public void updateItem(Button item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(printButton);
        }
    }
}