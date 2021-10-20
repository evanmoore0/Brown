package doodlejump;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
Sets up the doodle, adds it to the pane, and contains methods that affect the doodle
 */
public class Doodle {
    Rectangle doodle;

    //Doodle constructor, sets up the doodle and adds it to the game pane
    public Doodle(Pane game) {

        doodle = new Rectangle();
        doodle.setFill(Color.ROYALBLUE);
        doodle.setHeight(Constants.DOODLE_HEIGHT);
        doodle.setWidth(Constants.DOODLE_WIDTH);
        doodle.setY(Constants.DOODLE_START_Y);
        doodle.setX(Constants.DOODLE_START_X);

        game.getChildren().add(doodle);

    }

    //Moves the doodle based on the key input from MoveDoodle() in the Handler class
    public void moveDoodle(Boolean right) {
        if(right){
            doodle.setX(doodle.getX() + Constants.DISTANCE);
        } else {
            doodle.setX(doodle.getX() - Constants.DISTANCE);
        }
    }

    //Loops the doodle if it goes off the side of the screen
    public void loop() {
        if(doodle.getX()==-20) {
            doodle.setX(310);
        } else if(doodle.getX()==320) {
            doodle.setX(-10);
        }
    }

    //Returns the instance of the doodle's y position
    public double getDoodleY(){
        return doodle.getY();
    }

    //Returns the instance of the doodle's X position
    public double getDoodleX() {
        return doodle.getX();
    }

    //Sets the instance of the doodle's y position
    public void setPosition(double y){
        doodle.setY(y);
    }



}
