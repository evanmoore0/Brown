package doodlejump;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//Sets up the panes, button, and background for the button on the borderPane, and creates an instance of the Handler()
//class
public class PaneOrganizer {
    BorderPane borderPane;
    Pane game;
    Button end;
    Pane endButton;
    Rectangle background;

    //Creates instances of the BorderPane, other panes, and sets up the button and button background
    public PaneOrganizer() {
        borderPane = new BorderPane();
        game = new Pane();
        endButton = new Pane();
        end = new Button();
        background = new Rectangle();

        this.setUpBackground(endButton);
        this.setUpButton(endButton);

        new Handler(game);
        borderPane.setCenter(game);
        borderPane.setBottom(endButton);

    }

    //Allows borderPane to be accessed in App class
    public BorderPane getRoot() {
        return borderPane;
    }

    //Sets up the background and adds it to the endButton pane
    public void setUpBackground(Pane endButton) {
        background.setX(Constants.BACKGROUND_X);
        background.setWidth(Constants.GAME_WIDTH);
        background.setHeight(Constants.BACKGROUND_HEIGHT);
        background.setFill(Color.ORANGE);
        endButton.getChildren().add(background);

    }

    //Sets up the button and adds it to the endButton pane
    public void setUpButton(Pane endButton) {
        end.setMinSize(Constants.BUTTON_WIDTH,Constants.BUTTON_HEIGHT);
        end.setLayoutX(Constants.GAME_WIDTH/2 - Constants.BUTTON_WIDTH/2);
        end.setLayoutY(end.getLayoutY()+Constants.BUTTON_WIDTH);
        end.setTextFill(Color.ROYALBLUE);
        end.setText(Constants.QUIT);
        end.setOnAction(new buttonHandler());
        endButton.getChildren().addAll(end);
    }

    /*
    If the user presses the quit button it quits the game
     */
    private class buttonHandler implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event){
            System.exit(0);

        }

    }
}
