package doodlejump;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * My App class contains an instance of my PaneOrganizer which has an instance of the Handler class which has an
 * instance of the doodle and platform class. The app class sets up the scene for doodlejump
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        PaneOrganizer organizer = new PaneOrganizer();
        Scene scene = new Scene(organizer.getRoot(),Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Doodle Jump");
        stage.show();

    }

    public static void main(String[] argv) {
        launch(argv);
    }
}
