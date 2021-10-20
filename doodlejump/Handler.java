package doodlejump;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import java.util.ArrayList;


public class Handler {
    Doodle _doodle;
    private double _velocity;
    private double _updatedVelocity;
    private double _position;
    private ArrayList<Platform> _platforms;
    //I decided to make the timeline an instance variable so I can access it from the gravityHandler()
    private Timeline doodleLocation;
    Label _endGame;
    Label _score;
    private int _scoreCounter;
    Pane _game;

    //Handler constructor- creates an instance of the arraylist of platforms, the velocity, the game pane (so it can be
    //accessed by the generatePlatform method), the end game label, platform, and doodle. Also sets KeyEvent to listen
    //for keys pressed and calls the method that sets up the timeline.
    public Handler(Pane game) {
        _platforms = new ArrayList<Platform>();

        _velocity = Constants.GRAVITY*Constants.DURATION;

        _game = game;

        _scoreCounter = 0;

        _score = new Label();
        _score.setFont(new Font("Verdana", Constants.FONT_SIZE));
        _score.setText("You have jumped on " + _scoreCounter + " platforms!");
        _score.setTextFill(Color.ROYALBLUE);
        _game.getChildren().add(_score);


        _endGame = new Label();
        _endGame.setFont(new Font("Verdana", Constants.FONT_SIZE));
        _endGame.setText(Constants.END_GAME_MESSAGE);
        _endGame.setLayoutX(90);
        _endGame.setTextFill(Color.ROYALBLUE);
        _endGame.setLayoutY(Constants.GAME_HEIGHT/2);

        //Create a platform an initial platform to be added to the arrayList so I don't get array index out of bound
        //when I call the generatePlatform() method.
        Platform platform = new Platform(_game);
        _platforms.add(platform);

        _doodle = new Doodle(game);

        setupDoodleLocationTimeline();

       _game.addEventHandler(KeyEvent.KEY_PRESSED, new MoveDoodle());
       _game.setFocusTraversable(true);

    }

    // Sets up the timeline that calls the GravityHandler()
    public void setupDoodleLocationTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(Constants.DURATION), new GravityHandler());
        doodleLocation = new Timeline(kf);
        doodleLocation.setCycleCount(Animation.INDEFINITE);
        doodleLocation.play();
    }

    /*
    GravityHandler()- generates the platforms, checks to see if the doodle intersects the platform, sets the doodles
    velocity, lowers the platforms and sets the doodles position to the midpoint if the goes above the midpoint, checks
    to see if the platforms are below the screen and deletes them if they are. Basically handles all functionality.
     */
    private class GravityHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            //generates the platforms
            generatePlatform();

            //Updates the doodles position based on the velocity
            velocity();

            //Makes the platforms scroll down
            scroll();

            //Checks to see if the lowest platform is still on the screen, if it isn't the platform gets removed
            //graphically and logically.
            checkPlatform();

            //Displays the users score
            score();

            //Stops the timeline/displays the end game label
            quitGame();

        }
    }

    /*
    Moves the doodle based on the user input, and loops the doodle from one side of the screen to the other if it
    goes too far.
     */
    private class MoveDoodle implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            KeyCode input = event.getCode();
            _doodle.loop();
            //Checks to see if the doodle is off the screen
            if(removeEventHandler()){
                _game.removeEventHandler(KeyEvent.KEY_PRESSED, this);
                _game.setFocusTraversable(false);
                _game.setOnKeyPressed(null);
            } else if(input == KeyCode.LEFT) {
                _doodle.moveDoodle(false);
            } else if (input == KeyCode.RIGHT) {
                    _doodle.moveDoodle(true);
            }
            event.consume();
        }
    }

    //Generates the platforms while the top platform is on the screen
    public void generatePlatform() {

        //Sets the topPlatform to be the the last position in the arraylist
        Platform topPlatform = _platforms.get(_platforms.size()-1);

        //Checks to see if the topPlatform is on the screen
        while(topPlatform.getPlatformY() >= 0) {
            //Generates a new platform and gets added graphically (added graphically in the Platform constructor)
            Platform platform = new Platform(_game);

            //Sets the random x and y positions within certain bounds
            platform.setPlatformX(( 20 + Math.random()* 240));
            platform.setPlatformY(topPlatform.getPlatformY()-70 - Math.random()*65);

            //Adds the platforms logically
            _platforms.add(platform);

            //Sets the most recently created platform to be the topPlatform
            topPlatform = platform;

        }
    }

    //Sets the position of the doodle based on the velocity
    public void velocity(){
        if(intersect()) {
            _velocity = Constants.REBOUND_VELOCITY;
        }
        //Sets the updated velocity to the current velocity + duration * gravity, sets the new position of the
        //doodle, then changes the current velocity to be the updated velocity.
        this.setVelocity(_velocity);
        _position = (_doodle.getDoodleY() + _velocity*Constants.DURATION);
        _velocity = _updatedVelocity;
        _doodle.setPosition(_position);

    }

    //Updates updatedVelocity to be the current velocity + duration * gravity
    public void setVelocity(double v) {
        _updatedVelocity = (v + Constants.DURATION*Constants.GRAVITY);
    }

    //Checks to see if any of the platforms in the arrayList intersect with the doodle
    public boolean intersect() {
        for(int i = 0; i < _platforms.size(); i++) {
            if(_platforms.get(i).getPlatform().intersects(_doodle.getDoodleX(), _doodle.getDoodleY() + Constants.PLATFORM_HEIGHT, Constants.DOODLE_WIDTH, Constants.DOODLE_HEIGHT)) {
                return true;
            }
        }
        return false;
    }

    //Checks to see if the doodle is above the midpoint and sets its position to the midpoint if it is
    public void scroll(){
        // If the doodle is above the midpoint lower all the platforms and set the doodles position to the midpoint.
        if(_doodle.getDoodleY() < Constants.GAME_HEIGHT/2) {
            lowerPlatform(_doodle.getDoodleY() - Constants.GAME_HEIGHT/2);
            _doodle.setPosition(Constants.GAME_HEIGHT/2);
        }
    }

    //Shifts all of the platforms in the array by shiftDown (distance above the midpoint)
    public void lowerPlatform(double shiftDown) {
        for(int i = 0; i < _platforms.size(); i++) {
            _platforms.get(i).setPlatformY(_platforms.get(i).getPlatformY() - shiftDown);
        }
    }

    //Checks to see if the platform is still visible on the screen, if not it removes it graphically and logically
    public void checkPlatform() {
        if(_platforms.get(0).getPlatformY() > Constants.GAME_HEIGHT - Constants.BACKGROUND_HEIGHT) {
            _platforms.remove(0);
            _game.getChildren().remove(_platforms.get(0));
        }
    }

    //If the doodle falls of the screen it stops the timeline and displays the endgame label
    public void quitGame() {
        if(_doodle.getDoodleY() > Constants.GAME_HEIGHT) {
            _game.getChildren().add(_endGame);
            doodleLocation.stop();

        }
    }

    //Return true if the doodle is off the screen
    public boolean removeEventHandler(){
        if(_doodle.getDoodleY() > Constants.GAME_HEIGHT){
            return true;
        }
        return false;
    }

    //Updates the users score after every time they bounce on a platform
    public void score(){
        if(intersect()){
            //Remove the _score pane from _game to prevent error
            _game.getChildren().remove(_score);
            _scoreCounter++;
            _score.setText("Your score is " + _scoreCounter + " !");
            _game.getChildren().add(_score);
        }
    }
}
