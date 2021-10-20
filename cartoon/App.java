package cartoon;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *Basic flow of my cartoon: App class creates a PaneOrganizer which creates the necessary panes for my project and the
 * border pane that the panes will go on and creates an instance of the Cartoon class. The cartoon class contains all of
 * the timelines and eventhandlers for my cartoon. It also creates an instance of my CartoonHandler class which is my
 * composite shape.
 */

public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Instantiates PaneOrganizer and sets the scene up
        PaneOrganizer organizer = new PaneOrganizer();
        Scene scene = new Scene(organizer.getRoot(), Constants.APP_HEIGHT, Constants.APP_WIDTH, Color.BLACK);
        stage.setScene(scene);
        stage.setTitle("Rocket");
        stage.show();
    }

    public static void main(String[] argv) {
        launch(argv);
    }
}
