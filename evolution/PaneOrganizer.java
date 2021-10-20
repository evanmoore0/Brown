package evolution;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/*
Sets the screen up based on the button the user presses
 */
public class PaneOrganizer {

    //Declare an instance of the border pane, startScreen pane, game pane, and scoreboard HBox
    BorderPane _borderPane;
    Pane _startScreen;
    Pane _game;
    HBox _scoreBoard;

    //PaneOrganizer constructor
    public PaneOrganizer() {

        //Instantiate Panes
        _borderPane = new BorderPane();
        _startScreen = new Pane();
        _game = new Pane();
        _scoreBoard = new HBox();

        //Set the background color to be black
        _game.setStyle("-fx-background-color: black;");

        //Setup/display the start screen
        this.setUpStartScreen();
        _borderPane.setCenter(_startScreen);

    }

    //Set the start screen up
    public void setUpStartScreen() {

        _startScreen.setPrefSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        Button manuelFlappy = new Button();
        manuelFlappy.setText("Manuel Flappy Bird");
        manuelFlappy.setMinSize(70, 20);
        manuelFlappy.setLayoutY(Constants.SCREEN_HEIGHT/2);
        manuelFlappy.setLayoutX(Constants.MANUEL_FLAPPY_BUTTON_X);
        manuelFlappy.setOnAction(new ManuelFlappy());

        Button smartFlappy = new Button();
        smartFlappy.setText("Smart Flappy Bird");
        smartFlappy.setMinSize(70, 20);
        smartFlappy.setLayoutX(Constants.SMART_FLAPPY_BUTTON_X);
        smartFlappy.setLayoutY(Constants.SCREEN_HEIGHT/2);
        smartFlappy.setOnAction(new SmartFlappy());

        _startScreen.getChildren().addAll(manuelFlappy, smartFlappy);


    }

    /*
    Creates an instance of game class, sets up the border pane, and true boolean to game constructor
     */
    private class ManuelFlappy implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event){

            new Game(_game, _scoreBoard, true);

            _borderPane.setCenter(_game);
            _borderPane.setBottom(_scoreBoard);

        }

    }

    /*
    Creates an instance of game class, sets up the border pane, and passes false to game constructor
     */
    private class SmartFlappy implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event){

            new Game(_game, _scoreBoard, false);

            _borderPane.setCenter(_game);
            _borderPane.setBottom(_scoreBoard);

        }

    }

    //Returns borderpane
    public BorderPane getRoot(){
        return  _borderPane;
    }

}
