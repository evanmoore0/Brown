package tetris;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//Add panes/quit button to the border pane
public class PaneOrganizer {

    //Background for quit button
    Rectangle background;

    //Declare BorderPane, panes, and my tetris handler
    BorderPane _borderPane;
    Pane _board;
    Pane _piece;
    Pane _quit;
    TetrisHandler _tetris;

    //PaneOrganizer constructor
    public PaneOrganizer() {

        background = new Rectangle();

        //Initialize borderPane and other panes
        _borderPane = new BorderPane();
        _board = new Pane();
        _quit = new Pane();

        //Create an instance of my tetris handler
        _tetris = new TetrisHandler(_board);

        //Setup quit button
        this.setUpQuit();

        //Set the background of the board to black
        _board.setStyle("-fx-background-color: black;");


        //Add Panes to borderPane
        _borderPane.setCenter(_board);
        //_borderPane.getChildren().add(_piece);
        _borderPane.setBottom(_quit);
    }

    public BorderPane getRoot(){
        return _borderPane;
    }

    public void setUpQuit(){
        Button quit = new Button();
        quit.setLayoutX(Constants.GAME_WIDTH/2 - Constants.BUTTON_WIDTH/2);
        quit.setLayoutY(_quit.getLayoutY());
        quit.setText("Quit");
        quit.setOnAction(new buttonHandler());
        quit.setStyle("-fx-fill: white");
        _quit.getChildren().add(quit);
    }

    private class buttonHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event){
            System.exit(0);

        }

    }





}
