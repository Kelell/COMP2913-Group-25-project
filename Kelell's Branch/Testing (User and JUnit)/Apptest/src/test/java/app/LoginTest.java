package app;

import static org.junit.Assert.assertNotNull;

import java.util.concurrent.TimeoutException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class LoginTest extends ApplicationTest {

	/* The widgets of the GUI used for the tests. */
	TextField usernameField;
    TextField passwordField;
    Label status;
    Button button;
    Parent mainNode;
    
    
    /* This operation comes from ApplicationTest and loads the GUI to test. */
    @Override
    public void start(Stage stage) throws Exception {
        mainNode = FXMLLoader.load(Main.class.getResource("fxml/loginPage.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        /* Do not forget to put the GUI in front of windows. Otherwise, the robots may interact with another
        window, the one in front of all the windows... */
        stage.toFront();
 }
    
    /* Just a shortcut to retrieve widgets in the GUI. */
    public <T extends Node> T find(final String query) {
        /** TestFX provides many operations to retrieve elements from the loaded GUI. */
        return lookup(query).query();
    }
    
    @Before
    public void setUp() {
        /* Just retrieving the tested widgets from the GUI. */
    	usernameField = find("#usernameField");
    	passwordField = find("#passwordField");
    	status = find("#status");
        button = find("#button");
    }
    
    /* IMO, it is quite recommended to clear the ongoing events, in case of. */
    @After
    public void tearDown() throws TimeoutException {
        /* Close the window. It will be re-opened at the next test. */
        FxToolkit.hideStage();
        release(new KeyCode[] {});
        release(new MouseButton[] {});
    }

    @Test
    public void testWidgetsExist() {
    	final String errMsg = "One of the widget cannot be retrieved anymore";
    	assertNotNull(errMsg, usernameField);
    	assertNotNull(errMsg, passwordField);
    	assertNotNull(errMsg, button);
    	assertNotNull(errMsg, status);
    }
}
