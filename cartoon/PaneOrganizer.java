package cartoon;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/*
Creates the borderpane and other panes, adds the panes to the borderpane, and creates an instance of the cartoon class
 */
public class PaneOrganizer {

    //Declare borderpane
    private BorderPane borderPane;

    //PaneOrganizer constructor- instantiates borderpane and other panes, sets the background of the panes to
    //be black, and creates an instance of the cartoon class passing it the Panes that were just created
    public PaneOrganizer() {

        borderPane = new BorderPane();
        Pane rocketPane = new Pane();
        Pane label = new Pane();


        label.setStyle("-fx-background-color: black");
        rocketPane.setStyle("-fx-background-color: black");


        borderPane.setTop(label);
        borderPane.setCenter(rocketPane);

        new Cartoon(rocketPane, label);
    }
    public BorderPane getRoot(){
        return borderPane;
    }


}
