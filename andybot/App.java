package andybot;

import cs15.prj.andyBotSupport.AndyBot;
import cs15.prj.andyBotSupport.AndyBotFactory;
import cs15.prj.andyBotSupport.AndyBotHelper;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Welcome to your andybot App class.
 * Please do not modify this file at all.
 *
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        AndyBotFactory factory = new AndyBotFactory();
        AndyBot andybot = factory.makeAndyBot();
        new AndyBotHelper(stage, new MazeSolver(andybot));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
