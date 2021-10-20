package evolution;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/*
Sets up the bird, sets its velocity, resets the bird, and returns an instance of the bird's circle
 */
public class Bird {

    //Declare instance of bird composite shapes, game pane, velocity, updated velocity, and position
    Circle _bird;
    Circle _eye;
    Circle _middleEye;

    Pane _game;

    public double _velocity;
    public double _updatedVelocity;
    private double _position;

    //Bird constructor
    public Bird(Pane game) {

        //Initial velocity
        _velocity = Constants.GRAVITY* Constants.DURATION;

        _game = game;

        //Sets the bird up
        this.setUpBird();

    }

    //Updates the birds velocity
    public void velocity(){

        _updatedVelocity = (_velocity + Constants.DURATION* Constants.GRAVITY);
        _position = (_bird.getLayoutY() + _velocity*Constants.DURATION);
        _velocity = _updatedVelocity;
        _bird.setLayoutY(_position);
        if(_velocity > 0) {
            _eye.setLayoutY(_position + 2);
            _middleEye.setLayoutY(_position + 2);
        } else {
            _eye.setLayoutY(_position - 2);
            _middleEye.setLayoutY(_position - 2);
        }

    }

    //Sets the bird up
    public void setUpBird() {

        _bird = new Circle();
        _bird.setFill(Color.RED);
        _bird.setRadius(Constants.BIRD_RADIUS);
        _bird.setLayoutY(Constants.GAME_HEIGHT/2);
        _bird.setLayoutX(Constants.BIRD_START_X);

        _eye = new Circle();
        _eye.setFill(Color.WHITE);
        _eye.setRadius(Constants.EYE_RADIUS);
        _eye.setLayoutY(Constants.GAME_HEIGHT/2);
        _eye.setLayoutX(Constants.EYE_START_X);

        _middleEye = new Circle();
        _middleEye.setFill(Color.BLACK);
        _middleEye.setRadius(Constants.MIDDLE_EYE_RADIUS);
        _middleEye.setLayoutY(Constants.GAME_HEIGHT/2);
        _middleEye.setLayoutX(Constants.EYE_START_X);

        _game.getChildren().addAll(_bird, _eye, _middleEye);

    }

    //Returns an array of the composite shape
    public Circle[] getBird() {
        Circle[] compositeBird = new Circle[Constants.NUM_SHAPES];

        compositeBird[0] = _bird;
        compositeBird[1] = _eye;
        compositeBird[2] = _middleEye;

        return compositeBird;
    }

    //Adds the composite shape back to the pane, and sets the location of the composite shape
    public void resetBird() {

        _game.getChildren().addAll(_bird, _eye, _middleEye);
        _bird.setLayoutY(Constants.SMART_BIRD_START);
        _eye.setLayoutY(Constants.SMART_BIRD_START);
        _middleEye.setLayoutY(Constants.SMART_BIRD_START);

    }

}
