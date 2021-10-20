package doodlejump;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
Sets up the platforms, adds them to the game pane, and contains methods that set/get the platforms position
 */
public class Platform {

    public Rectangle _platform;

    //Platform constructor, sets up the platform and adds it to the game pane
    public Platform(Pane game){


        _platform = new Rectangle();
        _platform.setFill(Color.ORANGE);
        _platform.setWidth(Constants.PLATFORM_WIDTH);
        _platform.setHeight(Constants.PLATFORM_HEIGHT);
        _platform.setX(Constants.GAME_WIDTH/2);
        _platform.setY(425);

        game.getChildren().add(_platform);

    }

    //Returns the Rectangle of the platform, allows to call certain methods in Handler that we cannot call on Platforms
    //but can call on Rectangles
    public Rectangle getPlatform(){
        return _platform;
    }

    //Sets the instance of the platform's y position
    public void setPlatformY(double yPos){
        _platform.setY(yPos);
    }

    //Sets the instance of the platform's x position
    public void setPlatformX(double xPos){
        _platform.setX(xPos);
    }

    //Returns the Y position of the instance of the platform
    public double getPlatformY(){
        return _platform.getY();
    }

}
