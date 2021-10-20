package evolution;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

/*
Sets the pipes up, moves the pipes, generates the pipes, resets the pipes, and sets up the bottom
 */
public class Pipe {

    //Declare an instance of game pane, pipes ArrayList
    Pane _game;
    ArrayList<Rectangle> _pipes;

    //Checks to see if the second pipe has been generated
    Boolean _secondPipe;

    //Keeps track of how many pipes have been removed
    int _pipesRemoved;

    //Checks to see if two pipes have been removed
    Boolean _checkIncrement;

    //Pipe constructor
    public Pipe(Pane game) {

        //Initialize values/ instantiate pipes arrayList
        _pipesRemoved = 0;

        _checkIncrement = true;

        _secondPipe = true;

        _game = game;

        _pipes = new ArrayList<>();

        //Set up the pipes and the bottom
        this.setUpBottom();
        this.setUpPipe();

    }

    //Sets up the top and bottom pipe, and adds them to the _pipes ArrayList
    public void setUpPipe() {

        //Setup bottom pipe
        Rectangle bottomPipe = new Rectangle();
        bottomPipe.setWidth(Constants.PIPE_WIDTH);
        bottomPipe.setHeight((int)(Math.random() * 200) + 100);
        bottomPipe.setX(Constants.SCREEN_WIDTH);
        bottomPipe.setY(Constants.BOTTOM_GAME - bottomPipe.getHeight());
        bottomPipe.setFill(Color.WHITE);

        _pipes.add(bottomPipe);

        //Setup top pipe
        Rectangle topPipe = new Rectangle();
        topPipe.setWidth(Constants.PIPE_WIDTH);
        topPipe.setHeight(Constants.GAME_HEIGHT - bottomPipe.getHeight() - Constants.PIPE_GAPE);
        topPipe.setX(Constants.SCREEN_WIDTH);
        topPipe.setY(0);
        topPipe.setFill(Color.WHITE);

        _pipes.add(topPipe);

        _game.getChildren().addAll(bottomPipe, topPipe);


    }

    //Generates pipes based on their location
    public void generatePipe() {

        //Loop through _pipes ArrayList
        for(int i =0; i < _pipes.size(); i++) {

            //If the pipe is past half and their is only one set of pipes on the screen
            if(_pipes.get(i).getX() < Constants.SCREEN_WIDTH/2 && _pipes.size() == 2) {

                this.setUpPipe();
            }

            //If the pipe is off the screen remove it
            if(_pipes.get(i).getX() < -50) {

                _game.getChildren().remove(_pipes.get(i));

                _pipes.remove(i);

                _pipesRemoved++;

                //If one set of pipes has been removed
                if(_pipesRemoved == 2) {
                    _checkIncrement = true;
                    _pipesRemoved = 0;
                }

                //If the pipe is off the screen and there are two other sets of pipes on the screen
            } else if(_pipes.get(i).getX() < 0 && _pipes.size() == 4) {

                this.setUpPipe();
            }
        }
    }
    
    //Moves the pipes by 3 pixels every keyframe
    public void movePipe() {

        for(int i = 0; i < _pipes.size(); i++) {

            _pipes.get(i).setX(_pipes.get(i).getX() - Constants.MOVE_PIPE);

        }

    }

    //Sets up the bottom of the screen
    public void setUpBottom() {

        Rectangle bottom = new Rectangle();
        bottom.setFill(Color.WHITE);

        bottom.setHeight(Constants.BOTTOM_HEIGHT);
        bottom.setWidth(Constants.SCREEN_WIDTH);

        bottom.setY(Constants.BOTTOM_GAME);

        _game.getChildren().add(bottom);

    }

    //Removes pipes logically and graphically, then sets the pipes up and generates them
    public void resetPipes() {

        //Need to store size of _pipes here because when I remove the pipe from the array the size decreases by one
        //int numPipes = _pipes.size();

        while(!_pipes.isEmpty()) {

            _game.getChildren().remove(_pipes.get(0));
            _pipes.remove(0 );

        }

//        for(int i = 0; i < numPipes; i++) {
//
//            _game.getChildren().remove(_pipes.get(0));
//            _pipes.remove(0 );
//
//        }

        this.setUpPipe();
        this.generatePipe();

    }
}
