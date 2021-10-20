package evolution;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/*
Sets up the timeline, event handlers, scoreboard, and key handlers.
Also contains methods that update the scoreboard, restart the game, and update velocity
 */
public class Game {

    //Declare instances of Bird, Pipe, and SmartBird classes
    Bird _bird;
    Pipe _pipe;
    SmartBird _smartBird;

    //Declare instance of game pane and scoreboard Hbox
    Pane _game;
    HBox _scoreBoard;

    Boolean _manuelFlappy;

    //Declare Labels in manuel flappy
    Label _highScore;
    Label _currentScore;

    //Declare Labels in smart flappy
    Label _alive;
    Label _avgFitness;
    Label _bestFitnessLastGen;
    Label _bestFitness;
    Label _generation;
    Label _currentFitness;

    //Buttons that control the smart flappy bird speed
    Button oneX;
    Button twoX;
    Button fiveX;
    Button max;

    //Contains the closest top and bottom pipe
    Rectangle[] _closestPipe;

    //Timeline and keyframe
    Timeline _flappyBird;
    KeyFrame _kf;

    //Keep track of the manuel flappy scores
    int _highScoreCount;
    int _currentScoreCount;

    //Keep track of the smart flappy scores
    int _avgFitnessCount;
    int _bestFitnessLastGenCount;
    int _bestFitnessCount;
    int _generationCount;

    //Game constructor
    public Game(Pane game, HBox scoreBoard, Boolean manuelFlappy) {

        //Initialize values
        _avgFitnessCount = 0;
        _bestFitnessLastGenCount = 0;
        _bestFitnessCount = 0;
        _generationCount = 0;

        //Instantiate buttons
        oneX = new Button();
        twoX = new Button();
        fiveX = new Button();
        max = new Button();

        _closestPipe = new Rectangle[Constants.SIZE_PIPE_ARRAY];

        //Determines which methods should be called based on which button the user pressed
        _manuelFlappy = manuelFlappy;

        _scoreBoard = scoreBoard;

        _highScoreCount = 0;
        _currentScoreCount = 0;

        _game = game;

        //Creates an instance of the pipe class
        _pipe = new Pipe(_game);

        //Instantiates labels
        _highScore = new Label();
        _currentScore = new Label();

        _alive =  new Label();
        _avgFitness = new Label();
        _bestFitnessLastGen = new Label();
        _bestFitness = new Label();
        _generation = new Label();
        _currentFitness = new Label();

        _game.addEventHandler(KeyEvent.KEY_PRESSED, new MoveBird());

        _game.setFocusTraversable(true);

        //Sets up the score, buttons, and timeline
        this.setUpScore();

        this.setUpButtons();

        this.setUpFlappyBirdTimeline();

        //If manuel flappy is true creates an instance of Bird class, otherwise creates an instance of SmartBird
        this.smartBird();


    }

    //Sets up the timeline
    public void setUpFlappyBirdTimeline() {
        _kf = new KeyFrame(Duration.seconds(Constants.DURATION), new FlappyHandler());
        _flappyBird = new Timeline(_kf);
        _flappyBird.setCycleCount(Animation.INDEFINITE);
        _flappyBird.play();
    }

    /*
    Methods that should be called every keyframe
     */
    private class FlappyHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            //If the user pressed the manuel flappy button
            if(_manuelFlappy) {

                //Checks to see if the game should be reset
                restartGame();

                //Updates the scoreboard
                updateScore();

                //Moves the pipe and sets the birds velocity
                _pipe.movePipe();
                _bird.velocity();

                //If the user pressed the smart flappy button
            } else {

                //Update the scoreboard
                updateSmartScore();

                //Check to see if a new generation should be generated
                resetSmartFlappy();

                //Update the smart birds velocities
                _smartBird.setVelocity();

                //Move pipes
                _pipe.movePipe();

                //Returns the pipe closest to the bird
                getClosestTopPipe();

                //Sets the inputs of each bird
                _smartBird.setInputs(_closestPipe);

                //Checks to see if a bird intersected with the closest pipe
                _smartBird.checkIntersect(_closestPipe);

            }

            //Generates pipes
            _pipe.generatePipe();

        }
    }

    /*
    Key handler
     */
    private class MoveBird implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            KeyCode input = event.getCode();

            //If the user pressed space and they are playing manuel flappy
            if(input == KeyCode.SPACE && _manuelFlappy) {

                //Update the birds velocity to be the rebound velocity
                updateVelocity();

                //If the user presses the kill switch and are playing smart flappy
            } else if(input == KeyCode.K && !_manuelFlappy){

                //Removes birds logically and graphically
                while(!_smartBird._smartBirds.isEmpty()) {

                    _game.getChildren().removeAll(_smartBird._smartBirds.get(0).getBird());
                    _smartBird._smartBirds.remove(0);

                }
                //Resets smart flappy game
                resetSmartFlappy();

            }
            event.consume();
        }
    }

    //Restarts manuel flappy if the bird intersects with a pipe
    public void restartGame() {

        for(int i = 0; i < _pipe._pipes.size(); i++) {

            Rectangle pipe = _pipe._pipes.get(i);

            //If the bird is above the game set its location to the top of the screen
            if(_bird._bird.getLayoutY() < 0) {
                _bird._bird.setLayoutY(0);
                _bird._eye.setLayoutY(0);
                _bird._middleEye.setLayoutY(0);
            }

            //If the bird intersects with a pipe or the floor restart the game
            if(pipe.intersects(_bird._bird.getLayoutX(), _bird._bird.getLayoutY() - _bird._bird.getRadius(),
                    _bird._bird.getRadius(), _bird._bird.getRadius()*2) || _bird._bird.getLayoutY() > 500) {

                //Graphically remove the bird
                _game.getChildren().removeAll(_bird._bird, _bird._eye, _bird._middleEye);

                //Reset the bird location
                _bird.resetBird();

                //Reset the pipes
                _pipe.resetPipes();

                //Update the birds velocity to be initial velocity
                _bird._velocity = Constants.GRAVITY* Constants.DURATION;
                _bird._updatedVelocity = 0;

                //Update high score if it is less than current score
                if(_currentScoreCount > _highScoreCount) {
                    _highScoreCount = _currentScoreCount;

                }
                _currentScoreCount = 0;
                _pipe._checkIncrement = true;
            }

        }

    }

    //Resets the smart flappy game if there are no more birds on the screen
    public void resetSmartFlappy() {

        //If there are no more birds left, or a birds fitness is 9999
        if(_smartBird._smartBirds.size() == 0 || _bestFitnessLastGenCount == 9999) {

            //Update generation score
            _generationCount++;

            //Reset the pipes
            _pipe.resetPipes();

            //Generate a new population of birds
            _smartBird.generateSmartBirds();

            //Set the weight of the birds
            _smartBird.setWeight();

            //Update the labels
            _avgFitness.setText("  Avg Fitness Last Gen: " + _avgFitnessCount/Constants.NUM_SMART_BIRDS);
            _bestFitnessLastGen.setText("  Best Fitness Last Gen: " + _bestFitnessLastGenCount);
            _generation.setText("  Generation: " + _generationCount);

            _avgFitnessCount = 0;
            _bestFitnessLastGenCount = 0;

        }

    }

    //Setup scoreboard
    public void setUpScore() {

        //Store labels in a VBox to align correctly
        VBox labelContainer = new VBox();
        labelContainer.setPrefSize(Constants.LABEL_CONTAINER_WIDTH, Constants.SCOREBOARD_HEIGHT);

        //Setup size of scoreboard and background color
        _scoreBoard.setPrefSize(Constants.SCREEN_WIDTH, Constants.SCOREBOARD_HEIGHT);
        _scoreBoard.setStyle("-fx-background-color: black;");

        //If user is playing manuel flappy
        if(_manuelFlappy) {

            //Setup labels
            _highScore.setTextFill(Color.WHITE);
            _highScore.setText("  Your highest score is: 0");

            _currentScore.setTextFill(Color.WHITE);
            _currentScore.setText("  Your current score is: 0");

            labelContainer.getChildren().addAll(_highScore, _currentScore);

            //If user is playing smart flappy
        } else {

            //Setup labels
            _alive.setTextFill(Color.WHITE);
            _alive.setText("  Alive:");


            _avgFitness.setTextFill(Color.WHITE);
            _avgFitness.setText("  Avg Fitness Last Gen:");

            _bestFitnessLastGen.setTextFill(Color.WHITE);
            _bestFitnessLastGen.setText("  Best Fitness Last Gen:");

            _bestFitness.setTextFill(Color.WHITE);
            _bestFitness.setText("  Best Fitness All Time:");

            _generation.setTextFill(Color.WHITE);
            _generation.setText("  Generation:");

            _currentFitness.setTextFill(Color.WHITE);
            _currentFitness.setText("  Current Fitness: ");

            labelContainer.getChildren().addAll(_alive, _avgFitness, _bestFitnessLastGen, _bestFitness, _generation,
                    _currentFitness);

        }
        _scoreBoard.getChildren().add(labelContainer);
    }

    //Updates the scoreboard for smart flappy
    public void updateSmartScore() {

        _bestFitnessLastGenCount++;

        //Increment average fitness once for each bird
        for(int i = 0; i < _smartBird._smartBirds.size(); i++) {

            _avgFitnessCount++;

        }

        //Updates the best fitness if it is less than the best fitness of the last gen
        if(_bestFitnessCount < _bestFitnessLastGenCount) {

            _bestFitnessCount = _bestFitnessLastGenCount;

        }

        _bestFitness.setText("  Best Fitness: " + _bestFitnessCount);

        _currentFitness.setText("  Current Fitness: " + _bestFitnessLastGenCount);

        _alive.setText("  Alive :" + _smartBird._smartBirds.size());

    }

    //Creates an instance of Bird class if user is playing manuel flappy, otherwise creates an instance of SmartBird
    public void smartBird() {

        if(_manuelFlappy) {
            _bird = new Bird(_game);
        } else {
            _smartBird = new SmartBird(_game);
        }
    }

    //Updates the birds velocity if space is pressed
    public void updateVelocity() {

        if(_manuelFlappy) {

            _bird._velocity = Constants.REBOUND_VELOCITY;
        }
    }

    //Updates manuel flappy scoreboard
    public void updateScore() {

        for(int i = 0; i < _pipe._pipes.size(); i++) {

            if(_bird._bird.getLayoutX() > _pipe._pipes.get(i).getX() && _pipe._checkIncrement) {

                //Update the high score if it is the same as the current score
                if(_currentScoreCount == _highScoreCount) {
                    _highScoreCount++;
                }
                _currentScoreCount++;

                //Prevents currentScoreCount and highScoreCount from being incremented twice
                _pipe._checkIncrement = false;
            }

        }

        _highScore.setText("Your high score is: " + _highScoreCount);
        _currentScore.setText("Your current score is: " + _currentScoreCount);

    }


    //Returns the closest pipe to the bird
    public Rectangle[] getClosestTopPipe() {

        //Increment i by 2 each time because only need x location of the top or the bottom pipe
        for(int i = 0; i < _pipe._pipes.size(); i+=2) {

            //If the birds x location is less than the pipes x location
            if(_smartBird._smartBirds.size() != 0 && _smartBird._smartBirds.get(0).getBird()[0].getLayoutX() <
                    _pipe._pipes.get(i).getX() || _smartBird._smartBirds.get(0).getBird()[0].getLayoutX() <
                    _pipe._pipes.get(i).getX() + Constants.PIPE_WIDTH) {

                _closestPipe[0] = _pipe._pipes.get(i);
                _closestPipe[1] = _pipe._pipes.get(i+1);

                return _closestPipe;

            }

        }

        return null;


    }

    //Sets up speed up buttons
    public void setUpButtons() {

        if(!_manuelFlappy) {

            HBox buttonContainer = new HBox();
            buttonContainer.setSpacing(4);

            //Made the buttons instance variables so I could use getSource() in my button handler


            oneX.setMinSize(Constants.BUTTON_WIDTH_HEIGHT, Constants.BUTTON_WIDTH_HEIGHT);
            oneX.setText("1x");
            oneX.setOnAction(new ButtonHandler());
            oneX.setFocusTraversable(false);

            twoX.setMinSize(Constants.BUTTON_WIDTH_HEIGHT, Constants.BUTTON_WIDTH_HEIGHT);
            twoX.setText("2x");
            twoX.setOnAction(new ButtonHandler());
            twoX.setFocusTraversable(false);

            fiveX.setMinSize(Constants.BUTTON_WIDTH_HEIGHT, Constants.BUTTON_WIDTH_HEIGHT);
            fiveX.setText("5x");
            fiveX.setOnAction(new ButtonHandler());
            fiveX.setFocusTraversable(false);

            max.setMinSize(Constants.BUTTON_WIDTH_HEIGHT, Constants.BUTTON_WIDTH_HEIGHT);
            max.setText("Max");
            max.setOnAction(new ButtonHandler());
            max.setFocusTraversable(false);

            buttonContainer.getChildren().addAll(oneX, twoX, fiveX, max);
            _scoreBoard.getChildren().add(buttonContainer);

        }

    }

    /*
    Speed up the timeline based on which button the user presses
     */
    private class ButtonHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            KeyFrame kf;

            if(event.getSource().equals(oneX)) {

                kf = new KeyFrame(Duration.seconds(Constants.DURATION), new FlappyHandler());

                _flappyBird.stop();
                _flappyBird.getKeyFrames().clear();
                _flappyBird.getKeyFrames().add(kf);
                _flappyBird.play();

            } else if(event.getSource().equals(twoX)) {

                kf = new KeyFrame(Duration.seconds(Constants.DURATION/2), new FlappyHandler());

                _flappyBird.stop();
                _flappyBird.getKeyFrames().clear();
                _flappyBird.getKeyFrames().add(kf);
                _flappyBird.play();


            } else if(event.getSource().equals(fiveX)) {

                kf = new KeyFrame(Duration.seconds(Constants.DURATION/5), new FlappyHandler());

                _flappyBird.stop();
                _flappyBird.getKeyFrames().clear();
                _flappyBird.getKeyFrames().add(kf);
                _flappyBird.play();

            } else {

                kf = new KeyFrame(Duration.seconds(Constants.DURATION/10), new FlappyHandler());

                _flappyBird.stop();
                _flappyBird.getKeyFrames().clear();
                _flappyBird.getKeyFrames().add(kf);
                _flappyBird.play();

            }

        }
    }

}
